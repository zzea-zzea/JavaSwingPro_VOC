package template.Application.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
> CAFE.members TBL 테이블
> db.data.Member 데이터 클래스
> db.mgr.MemberDBMgr 데이터 DB중개자 클래스 
 */
//import java.sql.Connection;
//import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class DB_UserDbMgr {
	
	Connection conn;
	
	public DB_UserDbMgr() {
		this.conn = DB_Connect.getConn();
	}
	
	// - 신규 회원 가입 할 수 있다. C Member 객체를 입력
		public boolean insertNewMember(DB_UserInfo ui) {
			if (this.conn != null && ui != null) {
				String sql 
						= "INSERT INTO member VALUES (" + "MEMBER_SEQ.nextval,?,?, ?, ?, ?, ?,abc, ?,? )";
				System.out.println(sql); 
				try {
	//
					PreparedStatement pstmt = conn.prepareStatement(sql); // 사전문장준비
	// ?서식자의 순서, 개수, 타입 set...
					pstmt.setInt(1, ui.getId());
					pstmt.setString(2, ui.getUserId());
					pstmt.setString(3, ui.getUserPw());
					pstmt.setString(4, ui.getUserName());
					pstmt.setInt(5, ui.getGender());
					pstmt.setString(6, ui.getUserPhoneNum());
					pstmt.setInt(7,ui.getIsMember());
					pstmt.setString(8, ui.getUserDoB());
					pstmt.setString(9, ui.getUserEmail());
					int r = pstmt.executeUpdate(); // 전송
	// 데이터 변화(DML insert, update, delete)
	// 변화 없이 단순 데이터 조회는 stmt.executeQuery() select
					if (r == 1) {
						System.out.println("DBMgr: 회원 가입 성공! " + ui);
					} else {
						System.out.println("DBMgr: 회원 가입 실패! " + ui);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("DB 통신 에러!!");
			}
			return false;
		}

		public boolean insertNewMember2(DB_UserInfo ui) {
			if (this.conn != null && ui != null) {
				String sql // 순서, 개수, 타입.. 띄어쓰기
						= "INSERT INTO members VALUES (" + "MEMBER_SEQ.nextval, '" + ui.getUserId() + "', " + "'"
								+ ui.getUserPw() + "', '" + ui.getUserName() + "'" + ", " + ui.getGender() + "', '"
								+ ui.getUserDoB() + "', '" + ui.getUserEmail() + "', '" + ui.getUserPhoneNum() + "'";
				System.out.println(sql);
				try {
					Statement stmt = conn.createStatement();
					int r = stmt.executeUpdate(sql);
	// 데이터 변화(DML insert, update, delete)
	// 변화 없이 단순 데이터 조회는 stmt.executeQuery() select
					if (r == 1) {
						System.out.println("DBMgr: 회원 가입 성공! " + ui);
					} else {
						System.out.println("DBMgr: 회원 가입 실패! " + ui);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("DB error!!");
			}
			return false;
		}

	// - 신규 회원 가입 할 수 있다. C 회원 스키마의 재료들 입력
		public boolean insertNewMember(String UserId, String UserPw, String UserName, int Gender, String UserDoB,
				String UserEmail, String UserPhoneNum) {
			if (this.conn != null) {
				String sql // 순서, 개수, 타입.. 띄어쓰기
						= "INSERT INTO members VALUES (" + "MEMBER_SEQ.nextval, '" + UserId + "', " + "'" + UserPw + "', '"
								+ UserName + "'" + ", " + Gender + "', '" + UserDoB + "', '" + UserEmail + "', '"
								+ UserPhoneNum + "'";
				System.out.println(sql);
				try {
					Statement stmt = conn.createStatement();
					int r = stmt.executeUpdate(sql);

					if (r == 1) {
						System.out.println("DBMgr: 회원 가입 성공! " + UserId);
					} else {
						System.out.println("DBMgr: 회원 가입 실패! " + UserId);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("DB error !!");
			}
			return false;
		}
	
	
//	- 기존 회원이 자신의 정보를 편집수정 (갱신) 할 수 있다. U
//		이름, 암호, 나이, 마일리지 만..!! (* 편집가능)
//	- 기존 회원의 총 명수를 계산하여 조회할 수 있다.
	public int checkTotalNumberOfMembers() {
		if( this.conn != null ) {
//			String sql = "select COUNT(*) from members";
			String sql = "select COUNT(*) "
					+ "as member_cnt from member";
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				if( rs.next() ) {
					//int mbCnt = rs.getInt("COUNT(*)");
//					int mbCnt = rs.getInt(1); // 첫번째 컬럼
					int mbCnt = rs.getInt("member_cnt");
					return mbCnt;
				} else 
					return 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("DB error!!");
		}
		return -1;
	}
	
//	- 기존 회원이 마일리지 (갱신) 할 수 있다. U
//	- 기존 회원이 암호 (갱신) 할 수 있다. U
//	- 모든 기존 회원들을 조회할 수 있다. R (범위, 조건, 검색, 정렬, 페이지네이션화)
	public ArrayList<DB_UserInfo> selectAllMembers() {
		if( this.conn != null ) {
			ArrayList<DB_UserInfo> uiList = new ArrayList<>();
			String sql = "select * from member ORDER BY id desc";
			try {
				Statement stmt =  conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while( rs.next() ) {	
					String userPhoneNum = rs.getString("userPhoneNum");
						// varchar --> date
//					java.sql.Date joinDay 
//						= (java.sql.Date)rs.getDate("joined_at");
					DB_UserInfo ui 
					= new DB_UserInfo(rs.getInt("id"),
							rs.getString("userId"), 
							rs.getString("userPw"),
							rs.getString("userName"),
							rs.getInt("gender"),
							rs.getString("userDoB"),
							rs.getString("userEmail"),
							rs.getString("userPhoneNum"));
					
				uiList.add(ui);
				}
				System.out.println("DBMgr: 회원 조회 명수 => " + uiList.size());
				return uiList;
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		} else {
			System.out.println("DB error!!");
		}
		return null;
	}
	
//	- 특정 기존 회원 한 명을 조회할 수 있다. R (로그인명, 관리번호)	
	public DB_UserInfo selectOneMemberById(String dbui) {
		if( this.conn != null ) {
			String sql = "select * from member"
					+ " where id = " + dbui;
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				if( rs.next() ) { // 한 개의 레코드만...
					DB_UserInfo ui 
					= new DB_UserInfo(rs.getInt("id"),
							rs.getString("userId"), 
							rs.getString("userPw"),
							rs.getString("userName"),
							rs.getInt("gender"),
							rs.getString("userDoB"),
							rs.getString("userEmail"),
							rs.getString("userPhoneNum"),
							rs.getInt("isMember"));
					return ui;
				} else {
					System.out.println(dbui + 
							"번호의 회원 레코드 조회 DB 에러!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("DB 통신 에러!!");
		}		
		return null;
	}
	public DB_UserInfo selectOneMemberByUserId(String usid) {
		if( this.conn != null ) {
//			String sql = "select * from members"
//				+ " where login = '" + mbLogin + "'";
			String sql = "select * from members"
					+ " where login = ?";
			try {
//				Statement stmt = conn.createStatement();
				PreparedStatement pstmt =
							conn.prepareStatement(sql);
//				ResultSet rs = stmt.executeQuery(sql);
				pstmt.setString(1, usid);				
				ResultSet rs = pstmt.executeQuery();
				if( rs.next() ) { // 한 개의 레코드만...
					DB_UserInfo ui 
					= new DB_UserInfo(rs.getInt("id"),
							rs.getString("userId"), 
							rs.getString("userPw"),
							rs.getString("userName"),
							rs.getInt("gender"),
							rs.getString("userDoB"),
							rs.getString("userEmail"),
							rs.getString("userPhoneNum"));
					return ui;
				} else {
					System.out.println(usid + 
							" 아이디명의 회원 레코드 조회 DB 에러!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("DB 통신 에러!!");
		}		
		return null;
	}

//	- 기존 회원들을 키워드 기반 등으로 조건으로 검색 할 수 있다. R
	
//	- 기존 회원이 탈퇴할 수 있다. (삭제? => 탈퇴된 상태로 특정컬럼 갱신) D
//	- 특정 활동에 대한 보상으로 회원 마일리지 적립할 수 있다.
//	- 회원이 다른 회원을 추천/좋아요/팔로잉..할 수 있다.

//	- 회원이 로그인/로그아웃... 할 수 있다.(비암호화)
	public static final int LOGIN_SUCCESS = 1; // 
	public static final int LOGIN_FAIL_PW_MISMATCH = 2; // 
	public static final int LOGIN_FAIL_NOT_FOUND = 3; // 
	public static final int LOGIN_ERROR = 4; // 
	public int loginProcess(String login, String pw) {
		if( login == null || pw == null || 
				login.isEmpty() || pw.isEmpty() ) {
			System.out.println("�α��� ������ ���� �Է� ����!!");
			return LOGIN_ERROR;
		}
		if( this.conn != null ) {
			DB_UserInfo dbui = selectOneMemberByUserId(login); // UQ
			if( dbui != null ) { // ���Ե� ȸ�����ڵ� ã��!
				String mbPw = dbui.getUserPw();
				if( mbPw != null && !mbPw.isEmpty() ) {
					if( mbPw.equals(pw) ) {
						System.out.println("로그인 성공!!");
						return LOGIN_SUCCESS;
					} else {
						System.out.println("패스워드 불일치!!");
						return LOGIN_FAIL_PW_MISMATCH;
					}
				} else {
					System.out.println("로그인 실패!!");
					return LOGIN_ERROR;
				}						
			} else {
				System.out.println("로그인실패/ 찾을 수 없음!");
				return LOGIN_FAIL_NOT_FOUND;
			}
		}
		else {
			System.out.println("DB 에러!!");
		}		
		return LOGIN_ERROR;
	}
	
	
//	- 회원 통계
	
	public static void main(String[] args) {
		DB_UserDbMgr dbmgr = new DB_UserDbMgr();
		System.out.println("기존 -----------");
		ArrayList<DB_UserInfo> oldList = dbmgr.selectAllMembers();
		for (DB_UserInfo ui : oldList) {
			System.out.println(ui.toString());
		}
		
		System.out.println("레코드 2개 추가....");
		dbmgr.insertNewMember(
				new DB_UserInfo(1, "olaf4", "1234","올라프", DB_UserInfo.GENDER_MALE, "950116", "zczxc", "0105641234"));
		DB_UserInfo newMB = new DB_UserInfo(2, "sven4", "1234",
				"스벤", DB_UserInfo.GENDER_MALE,"921021","asdasda", "01012315462");
		dbmgr.insertNewMember(newMB);
//		mbMgr.insertNewMember("올라프", "olaf", "1234",
//				18, Member.GENDER_MALE);
//		mbMgr.insertNewMember("스벤", "sven", "1234",
//				33, Member.GENDER_MALE);
		
		System.out.println("추가 -----------");
		ArrayList<DB_UserInfo> newList = dbmgr.selectAllMembers();
		for (DB_UserInfo ui : newList) {
			System.out.println(ui);
		}
		
		System.out.println(">> 최종 회원 레코드 수: " 
				+ dbmgr.checkTotalNumberOfMembers() + "명");
		Scanner sc = new Scanner(System.in);
		System.out.print(">> ���̵�: " );
		String mbInputLogin = sc.next();
		System.out.print(">> ��ȣ: " );
		String mbInputPW = sc.next();
		
		int loginR = dbmgr.loginProcess(mbInputLogin, mbInputPW);
		if( loginR == LOGIN_SUCCESS ) {
			DB_UserInfo mb = dbmgr.selectOneMemberByUserId(mbInputLogin);
//			System.out.println(mbInputLogin + " ȸ���� ���� ����!");
			System.out.println(mbInputLogin + "("+ 
						mb.getUserName() +") 님 로그인중!");
			System.out.println("... 로그인!!");
		}
		
		DB_Connect.endConnection();
	}
}


