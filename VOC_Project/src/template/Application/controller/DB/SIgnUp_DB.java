package template.Application.controller.DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import template.Application.controller.Data.SignUp_data;

public class SIgnUp_DB {
	public static final int LOGIN_SUCCESS = 1;
	public static final int LOGIN_FAIL_PW_MISMATCH = 2;
	public static final int LOGIN_FAIL_NOT_FOUND = 3;
	public static final int LOGIN_ERROR = 4;
	SignUp_data su;
	DB_Connect conn;

	public  SIgnUp_DB(String userName, String userPhoneNum, String userDoB) {
		// TODO Auto-generated constructor stub
	}

	public SIgnUp_DB() {
		// TODO Auto-generated constructor stub
	}

	public boolean insertNewMember(SignUp_data ui) {
		conn = null;
		conn.beginConnection();
		if (conn.conn != null && ui != null) {
			String sql = "INSERT INTO member(member_id,id,password,name,gender,phone_number,is_member, birthday) VALUES (MEMBER_SEQ.nextval,"
					+ "'" + ui.getId() + "', '" + ui.getPassword() + "', '" + ui.getName() + "', '" + ui.getGender()
					+ "', '" + ui.getPhone_number() + "', '" + "0" + "', '" + ui.getBirthday() + "')";
			System.out.println(sql);
			try {
				PreparedStatement pstmt = conn.conn.prepareStatement(sql);
				int r = pstmt.executeUpdate();
				String memName = ui.getName();
				String phn = ui.getPhone_number();
				String bod = ui.getBirthday();
				String pw = ui.getPassword();
				if (r == 1 && memName != null && phn != null && bod != null && pw !=null) {
					System.out.println("DBMgr: 회원 가입 성공! " + ui);
					return true;
				} else {
					System.out.println("DBMgr: 회원 가입 실패! " + ui);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("DB error!!~~~~@");
		}
		conn.endConnection();
		return false;
	}

	public ArrayList<SignUp_data> selectAllMembers() {
		if (this.conn != null) {
			ArrayList<SignUp_data> uiList = new ArrayList<>();
			String sql = "select * from member ORDER BY id desc";
			try {
				Statement stmt = conn.conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String userDoB = rs.getString("");
					SignUp_data ui = new SignUp_data(rs.getInt("MEMBER_ID"), rs.getString("ID"),
							rs.getString("PASSWORD"), rs.getString("NAME"), rs.getInt("GENDER"),
							rs.getString("PHONE_NUMBER"), rs.getInt("IS_MEMBER"), rs.getString("BIRTHDAY"));

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

	public SignUp_data selectOneMemberById(int dbui) {
		if (this.conn != null) {
			String sql = "select * from member" + " where id = " + dbui;
			try {
				Statement stmt = conn.conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				if (rs.next()) {
					SignUp_data ui = new SignUp_data(rs.getInt("MEMBER_ID"), rs.getString("ID"),
							rs.getString("PASSWORD"), rs.getString("NAME"), rs.getInt("GENDER"),
							rs.getString("PHONE_NUMBER"), rs.getInt("IS_MEMBER"), rs.getString("BIRTHDAY"));
					return ui;
				} else {
					System.out.println(dbui + "번호의 회원 레코드 조회 DB 에러!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("DB error!!");
		}
		return null;
	}

	public SignUp_data selectOneMemberByUserId(String usid) {
		if (this.conn != null) {
			String sql = "select * from member" + " where Id = ?";
			try {
				PreparedStatement pstmt = conn.conn.prepareStatement(sql);
				pstmt.setString(2, usid);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					SignUp_data ui = new SignUp_data(rs.getInt("MEMBER_ID"), rs.getString("ID"),
							rs.getString("PASSWORD"), rs.getString("NAME"), rs.getInt("GENDER"),
							rs.getString("PHONE_NUMBER"), rs.getInt("IS_MEMBER"), rs.getString("BIRTHDAY"));
					return ui;
				} else {
					System.out.println(usid + " 아이디명의 회원 레코드 조회 DB 에러!");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("DB 통신 에러!!");
		}
		return null;
	}

//	public int loginProcess(String login, String pw) {
	public int nullCheck (String userName, String userPw, String userPhoneNum, String userDoB) {
		if (userName == null || userPhoneNum == null || userPw ==null ||
				userName.isEmpty() || userPw.isEmpty() || userPhoneNum.isEmpty() ) {
			System.out.println("빈칸이 존재 합니다");
			return LOGIN_ERROR;
		} else {
			System.out.println("빈칸이 다 채워졌습니다.");
			insertNewMember(su);
		}
		return LOGIN_ERROR;
	}

	public static void main(String[] args) {
		//SIgnUp_DB sudb = new SIgnUp_DB();
	//	boolean b = false;
	//	b = sudb.insertNewMember(new SignUp_data("olaf4", "1234", "올라프", 1, "01012345678", "950116"));
	//	System.out.println(b);
	}
}
