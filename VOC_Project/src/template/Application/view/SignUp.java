package template.Application.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import template.Application.controller.GUICalendarFrame;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import template.Application.controller.DB_Connect;
import template.Application.controller.DB_UserInfo;
import template.Application.controller.DB_UserDbMgr;

public class SignUp extends JFrame {

	
	private JPanel contentPane;
	private JTextField txt_userId;
	private JTextField txt_userName;
	private JTextField txt_emailAdd;
	private JPasswordField pwf_userPw2;
	private JPasswordField pwf_userPw1;
	private JTextField txt_phone3;
	private JTextField txt_phone1;
	private JTextField txt_phone2;
	private final ButtonGroup genderGrp = new ButtonGroup();
	SignUp mj;
	JButton btn_userJoin;
	GUICalendarFrame frm;
	Login mln;
	Connection conn;
	DB_UserInfo Dbui;
	DB_UserDbMgr mgr;
	JLabel lb_NoDup;
	
	
//	public SignUp() {
//		this.conn = DB_Connect.getConn();
//	}
	
	/**
	 * Launch the application.
	 */
// public static void main(String[] args) {
// EventQueue.invokeLater(new Runnable() {
// public void run() {
// try {
// MemberJoin frame = new MemberJoin();
// frame.setVisible(true);
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
// });
// }

	/**
	 * Create the frame.
	 * 
	 * @param mln
	 */
	
	
	public SignUp(Login mln) {

		this.mln = mln;

//
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\dev2021\\java_ws\\GUICafeProject\\icons\\car.png"));
		setTitle("VOC \uD68C\uC6D0\uAC00\uC785::");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 618);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lb_userJoin = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		lb_userJoin.setBounds(116, 17, 508, 36);
		lb_userJoin.setFont(new Font("굴림", Font.BOLD, 35));
		lb_userJoin.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lb_userJoin);

		JPanel panel = new JPanel();
		panel.setBounds(-12, 63, 453, 416);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lb_userId = new JLabel("ID:");
		lb_userId.setFont(new Font("굴림", Font.PLAIN, 14));
		lb_userId.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lb_userId);

		txt_userId = new JTextField();
		panel.add(txt_userId);
		txt_userId.setColumns(10);

		JLabel lb_userPw1 = new JLabel("\uBE44\uBC00\uBC88\uD638:");
		lb_userPw1.setFont(new Font("굴림", Font.PLAIN, 14));
		lb_userPw1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lb_userPw1);

		pwf_userPw1 = new JPasswordField();
		pwf_userPw1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				pwf_userPw1.setBackground(Color.yellow);
			}

			@Override
			public void focusLost(FocusEvent e) {
				pwf_userPw1.setBackground(Color.white);
			}
		});
		panel.add(pwf_userPw1);

		JLabel lb_userPw2 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778:");
		lb_userPw2.setFont(new Font("굴림", Font.PLAIN, 14));
		lb_userPw2.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lb_userPw2);

		pwf_userPw2 = new JPasswordField();
		pwf_userPw2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				pwf_userPw2.setBackground(Color.yellow);
				String strPw1 = new String(pwf_userPw1.getPassword());// 문자배열 => 문자열
				if (strPw1.isEmpty()) {
					pwf_userPw1.requestFocusInWindow();
// 첫번째 pw필드로 포커스를 강제 이동 요청..  
// 자동으로 두번� pw필드의 focusLost()가 일어남..
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				pwf_userPw2.setBackground(Color.white);
				String strPw1 = new String(pwf_userPw1.getPassword());
				String strPw2 = new String(pwf_userPw2.getPassword());
				if (strPw1.length() > 0 && strPw2.isEmpty()) {

//
				} else {
					if (strPw1.length() > 0 && strPw2.length() > 0) {
						if (strPw2.equals(strPw1)) {
							System.out.println("암호 일치");

							checkJoinAvailable();
						} else {
							System.out.println("암호 불일치");
							pwf_userPw1.requestFocusInWindow();
						}
					}
				}
			}
		});
		panel.add(pwf_userPw2);

		JLabel lb_userName = new JLabel("\uC774\uB984:");
		lb_userName.setFont(new Font("굴림", Font.PLAIN, 14));
		lb_userName.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lb_userName);

		txt_userName = new JTextField();
		txt_userName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("focus get");
				txt_userName.setForeground(Color.black);
				txt_userName.setBackground(Color.yellow);
				if (txt_userName.getText().equals("ex) 홍길동"))
					txt_userName.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("focus lost");
				txt_userName.setForeground(Color.LIGHT_GRAY);
				txt_userName.setBackground(Color.WHITE);
				if (txt_userName.getText().isEmpty())
					txt_userName.setText("ex) 홍길동");
			}
		});
		txt_userName.setForeground(Color.LIGHT_GRAY);
		txt_userName.setFont(new Font("굴림", Font.PLAIN, 14));
		txt_userName.setText("ex) \uD64D\uAE38\uB3D9");
		panel.add(txt_userName);
		txt_userName.setColumns(10);

		JLabel lb_Gender = new JLabel("\uC131\uBCC4:");
		lb_Gender.setFont(new Font("굴림", Font.PLAIN, 14));
		lb_Gender.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lb_Gender);

		JPanel panel_Gender = new JPanel();
		panel.add(panel_Gender);
		panel_Gender.setLayout(new GridLayout(0, 2, 0, 0));

		JRadioButton rd_Female = new JRadioButton("\uC5EC\uC131");
		genderGrp.add(rd_Female);
		rd_Female.setSelected(true);
		rd_Female.setFont(new Font("굴림", Font.PLAIN, 14));
		rd_Female.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Gender.add(rd_Female);

		JRadioButton rd_Male = new JRadioButton("\uB0A8\uC131");
		genderGrp.add(rd_Male);
		rd_Male.setFont(new Font("굴림", Font.PLAIN, 14));
		rd_Male.setHorizontalAlignment(SwingConstants.CENTER);
		panel_Gender.add(rd_Male);

		JLabel lb_DoB = new JLabel("\uC0DD\uB144\uC6D4\uC77C:");
		lb_DoB.setFont(new Font("굴림", Font.PLAIN, 14));
		lb_DoB.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lb_DoB);

		JPanel panel_bday = new JPanel();
		panel.add(panel_bday);
		panel_bday.setLayout(null);

		JLabel lb_Calendar = new JLabel("");
		lb_Calendar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				GUICalendarFrame cal = new GUICalendarFrame();
				cal.setVisible(true);

				Point pt = lb_Calendar.getLocationOnScreen();

				cal.setLocation(pt.x - 100, pt.y - 50);
			}
		});
		lb_Calendar.setIcon(new ImageIcon("C:\\dev2021\\java_ws\\DraftProject\\icons\\calendar.gif"));
		lb_Calendar.setBounds(110, 10, 31, 25);
		panel_bday.add(lb_Calendar);

		txt_DoB = new JTextField();

		txt_DoB.setBounds(5, 10, 93, 32);
		panel_bday.add(txt_DoB);
		txt_DoB.setColumns(10);

		JLabel lb_Email = new JLabel("\uC774\uBA54\uC77C:");
		lb_Email.setFont(new Font("굴림", Font.PLAIN, 14));
		lb_Email.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lb_Email);

		JPanel panel_Email = new JPanel();
		panel.add(panel_Email);
		panel_Email.setLayout(null);

		txt_emailAdd = new JTextField();
		txt_emailAdd.setBounds(5, 8, 93, 32);
		panel_Email.add(txt_emailAdd);
		txt_emailAdd.setColumns(10);

		JComboBox combo_emailAddress = new JComboBox();
		combo_emailAddress.setEditable(true);
		combo_emailAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				combo_emailAddress.getSelectedIndex();
				System.out.println(combo_emailAddress.getSelectedIndex());
				System.out.println(combo_emailAddress.getSelectedItem());
			}
		});

		combo_emailAddress.setBounds(121, 7, 93, 32);
		combo_emailAddress.setModel(new DefaultComboBoxModel(
				new String[] { "메일 주소", "hanmail.net", "naver.com", "gmail.com", "nate.com", "직접 입력" }));
		panel_Email.add(combo_emailAddress);
		String emailAdd = (String) combo_emailAddress.getSelectedItem();
		if (emailAdd.equals("직접 입력")) {
			combo_emailAddress.setVisible(false);
// txt_fillEmail.setVisible(true);
		}

		JLabel lblNewLabel_3 = new JLabel("@");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(99, 8, 19, 29);
		panel_Email.add(lblNewLabel_3);

		JLabel lb_phoneNumber = new JLabel("\uC804\uD654\uBC88\uD638:");
		lb_phoneNumber.setFont(new Font("굴림", Font.PLAIN, 14));
		lb_phoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lb_phoneNumber);

		JPanel panel_phoneNum = new JPanel();
		panel.add(panel_phoneNum);
		panel_phoneNum.setLayout(new GridLayout(1, 5, 0, 0));

		txt_phone1 = new JTextField();
		txt_phone1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("focus get");
				txt_phone1.setForeground(Color.black);
				txt_phone1.setBackground(Color.yellow);
				if (txt_phone1.getText().equals("010"))
					txt_phone1.setText(""); // 안내 문구일 때만 지움.
			}

			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("focus lost");
				txt_phone1.setForeground(Color.LIGHT_GRAY);
				txt_phone1.setBackground(Color.WHITE);
				if (txt_phone1.getText().isEmpty())
					txt_phone1.setText("010");
			}
		});
		txt_phone1.setHorizontalAlignment(SwingConstants.CENTER);
		txt_phone1.setForeground(Color.LIGHT_GRAY);
		txt_phone1.setText("010");
		panel_phoneNum.add(txt_phone1);
		txt_phone1.setColumns(10);

		JLabel lb_phoneNum1 = new JLabel("-");
		lb_phoneNum1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_phoneNum.add(lb_phoneNum1);

		txt_phone2 = new JTextField();
		txt_phone2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("focus get");
				txt_phone2.setForeground(Color.black);
				txt_phone2.setBackground(Color.yellow);
				if (txt_phone2.getText().equals("1234"))
					txt_phone2.setText(""); // 안내 문구일 때만 지움.
			}

			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("focus lost");
				txt_phone2.setForeground(Color.LIGHT_GRAY);
				txt_phone2.setBackground(Color.WHITE);
				if (txt_phone2.getText().isEmpty())
					txt_phone2.setText("1234");
			}
		});
		txt_phone2.setHorizontalAlignment(SwingConstants.CENTER);
		txt_phone2.setForeground(Color.LIGHT_GRAY);
		txt_phone2.setText("1234");
		panel_phoneNum.add(txt_phone2);
		txt_phone2.setColumns(10);

		JLabel lb_phoneNum2 = new JLabel(" -");
		lb_phoneNum2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_phoneNum.add(lb_phoneNum2);

		txt_phone3 = new JTextField();
		txt_phone3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("focus get");
				txt_phone3.setForeground(Color.black);
				txt_phone3.setBackground(Color.yellow);
				if (txt_phone3.getText().equals("5678"))
					txt_phone3.setText(""); // 안내 문구일 때만 지움.
			}

			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("focus lost");
				txt_phone3.setForeground(Color.LIGHT_GRAY);
				txt_phone3.setBackground(Color.WHITE);
				if (txt_phone3.getText().isEmpty())
					txt_phone3.setText("5678");
			}
		});
		txt_phone3.setHorizontalAlignment(SwingConstants.CENTER);
		txt_phone3.setForeground(Color.LIGHT_GRAY);
		txt_phone3.setText("5678");
		panel_phoneNum.add(txt_phone3);
		txt_phone3.setColumns(10);

		JButton btn_DupCheck = new JButton("\uC911\uBCF5\uCCB4\uD06C");
		btn_DupCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String inLogin = txt_userId.getText();
				DB_UserInfo mb = mgr.selectOneMemberByUserId(inLogin);
					if( mb == null ) { // 사용가능
						bLoginAvail = true;// 일단 더미 중복x
						JOptionPane.showMessageDialog(null, "사용가능한 id 입니다");
					
					} else {
						bLoginAvail = false;
						JOptionPane.showMessageDialog(null, "사용불가능한 id 입니다");

					}			
			}
		});
		
		btn_DupCheck.setFont(new Font("굴림", Font.PLAIN, 14));
		btn_DupCheck.setBounds(454, 73, 96, 36);
		contentPane.add(btn_DupCheck);
		
		
	
		
		JButton btn_cancel = new JButton("\uCDE8\uC18C");
		btn_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btn_cancel.setBackground(Color.LIGHT_GRAY);
		btn_cancel.setBounds(269, 503, 136, 50);
		contentPane.add(btn_cancel);

		JButton btn_userJoin = new JButton("가입 완료");
		btn_userJoin.setForeground(Color.BLACK);
		btn_userJoin.setBackground(new Color(255, 175, 175));
		btn_userJoin.setFont(new Font("굴림", Font.BOLD, 14));
		btn_userJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("가입완료 클릭");
				//회원가입 DB
				//insertNewMember(String UserId, String UserPw, String UserName, int Gender, Date UserDoB,
//				String UserEmail, int UserPhoneNum)
				String UserId = txt_userId.getText();
				String UserPw = new String (pwf_userPw1.getPassword());
				String UserName = txt_userId.getText();
				int Gender = rd_Female.isSelected() ? DB_UserInfo.GENDER_FEMALE: DB_UserInfo.GENDER_MALE;
				String UserDoB = txt_DoB.getText();
				String UserEmail = txt_emailAdd.getText();
				String UserPhoneNum = txt_phone1.getText()+txt_phone2.getText()+txt_phone3.getText();
				DB_UserInfo newUI = new DB_UserInfo( UserId, UserPw,  UserName, Gender, UserDoB, UserEmail, UserPhoneNum);
				boolean r = mgr.insertNewMember(newUI);
				JOptionPane.showMessageDialog(null, "가입이 완료되었습니다.");
				System.out.println(combo_emailAddress.getSelectedItem());
				//insertNewMember();
			}
		});
		
		btn_userJoin.setEnabled(false);
		btn_userJoin.setBounds(417, 503, 136, 50);
		contentPane.add(btn_userJoin);

	}

	private boolean bLoginAvail; // false(중복)
	private JTextField txt_DoB;

	protected void checkJoinAvailable() {
// 가입 가능한 상태의 입력들이 준비되었는지 체크...
// 길이, 중복유무, 범위, 구성.. 체크  => 검증 validation (필터링)

		String strPw1 = new String(pwf_userPw1.getPassword());
		String strPw2 = new String(pwf_userPw2.getPassword());
		if ((strPw1.length() > 0 && strPw2.length() > 0) && bLoginAvail == true
				&& (!txt_emailAdd.getText().isEmpty() == false)
				&& (txt_userName.getText().isEmpty() == false && txt_userName.getText().equals("ex) 홍길동") == false)) {
// 두개 암호 필드값의 내용 비교 일치/불일치
			if (strPw2.equals(strPw1)) {

				btn_userJoin.setEnabled(true);

			} else {
				btn_userJoin.setEnabled(false);
			}
		}

	}
}


