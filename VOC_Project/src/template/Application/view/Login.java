package template.Application.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import template.Application.controller.ImagePanel;
import template.Application.controller.Login_DB;
import template.Application.controller.Login_data;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login extends JFrame {

//	static JPanel panel = new JPanel() {
//		Image background = new ImageIcon(Login.class.getResource("‪C:\\Users\\82102\\Desktop\\movie\\movie.jpg"))
//				.getImage();
//
////		Image background = new ImageIcon(Main.class.getResource("‪../Reference/icons/movie.jpg"))
////				.getImage();
//		public void paint(Graphics g) {
//			g.drawImage(background, 0, 0, null);
//		}
//	};
	JTextField txt_id;
	public JPasswordField txt_pw;
	Login ln;
	Login_FindID FindId;
	Login_FindPW FindPw;
	ImagePanel contentPane;
	Main main ;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Login() {
		this.ln = this;
		setTitle("Vehicle Outdoor Cinema");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\82102\\javaPro\\VOC_Project\\src\\template\\Reference\\icons\\camera.png"));
		setBounds(100, 100, 472, 573);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		ImageIcon icBack = new ImageIcon("C:\\Users\\82102\\javaPro\\VOC_Project\\src\\template\\Reference\\icons\\movie2.jpg");
		Image bgImg = icBack.getImage();
		contentPane = new ImagePanel(bgImg,"bg1.jpg",456,534 );
				
		contentPane.setBounds(0, 0, 456, 534);
		contentPane.setBackground(new Color(220, 220, 220));
		getContentPane().add(contentPane);
		contentPane.setLayout(null);

		JLabel lb_id = new JLabel("");
		lb_id.setHorizontalAlignment(SwingConstants.CENTER);
		lb_id.setIcon(new ImageIcon(
				"C:\\Users\\82102\\javaPro\\VOC_Project\\src\\template\\Reference\\icons\\status_online.png"));
		lb_id.setBackground(SystemColor.activeCaptionText);
		lb_id.setBounds(59, 254, 35, 31);

		contentPane.add(lb_id);

		txt_id = new JTextField();
		txt_id.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_id.setForeground(Color.black);
				txt_id.setBackground(Color.yellow);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_id.setForeground(Color.LIGHT_GRAY);
				txt_id.setBackground(Color.WHITE);

			}
		});
		txt_id.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 16));
		txt_id.setBounds(116, 254, 185, 35);
		contentPane.add(txt_id);
		txt_id.setColumns(10);

		JLabel lb_pw = new JLabel("");
		lb_pw.setIcon(
				new ImageIcon("C:\\Users\\82102\\javaPro\\VOC_Project\\src\\template\\Reference\\icons\\key.png"));
		lb_pw.setHorizontalAlignment(SwingConstants.CENTER);
		lb_pw.setBackground(Color.WHITE);
		lb_pw.setBounds(59, 300, 35, 31);
		contentPane.add(lb_pw);
		
		Login_data LD = new Login_data();
		JButton btn_Login = new JButton("Login");
		btn_Login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btn_Login.setBackground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btn_Login.setBackground(Color.blue);
			}
		});
		btn_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = txt_id.getText();
				String pw = new String(txt_pw.getPassword());
				Login_DB mgr = new Login_DB();
				int r = mgr.loginProcess(login, pw);
				switch (r) {
				case Login_DB.LOGIN_SUCCESS:
					main = new Main(ln, LD);
					main.setVisible(true);
					dispose();
					break;
				case Login_DB.LOGIN_FAIL_NOT_FOUND:
					JOptionPane.showMessageDialog(null, "로그인 회원 계정명 없음!!");
					break;
				case Login_DB.LOGIN_FAIL_PW_MISMATCH:
					JOptionPane.showMessageDialog(null, "로그인 암호가 불일치!!");

					break;
				case Login_DB.LOGIN_ERROR:
					JOptionPane.showMessageDialog(null, "로그인 인증 입력/DB에러!!");
					break;

				default:
					System.out.println("지원하지않습니다.");
					break;
				}
			}
		});

		btn_Login.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 25));
		btn_Login.setForeground(new Color(255, 255, 255));

		btn_Login.setBackground(Color.BLUE);
		btn_Login.setBounds(324, 254, 112, 77);
		contentPane.add(btn_Login);

		JLabel lb_FindID = new JLabel("\uC544\uC774\uB514");
		lb_FindID.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("아이디 찾기 누름");
				Login_FindID fid = new Login_FindID(ln);
				Point fPt = ln.getLocationOnScreen();
				fid.setLocation(fPt.x + ln.getWidth() + 20, fPt.y);
				fid.setVisible(true);
//				lb_FindID.setOpaque(true);
//				lb_FindID.setBackground(Color.LIGHT_GRAY);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lb_FindID.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lb_FindID.setForeground(Color.black);
			}
		});
		lb_FindID.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		lb_FindID.setBounds(116, 353, 56, 25);
		contentPane.add(lb_FindID);

		JLabel lb_SignUp = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		lb_SignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("회원가입 누름");
				lb_SignUp.setOpaque(true);//
				lb_SignUp.setBackground(Color.LIGHT_GRAY);
				SignUp su = new SignUp(ln);
				su.setLocation(750, 100);
				su.setVisible(true);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lb_SignUp.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lb_SignUp.setForeground(Color.black);
			}
		});
		lb_SignUp.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		lb_SignUp.setBounds(324, 353, 70, 25);
		contentPane.add(lb_SignUp);

		txt_pw = new JPasswordField();
		txt_pw.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txt_pw.setForeground(Color.black);
				txt_pw.setBackground(Color.yellow);
			}

			@Override
			public void focusLost(FocusEvent e) {
				txt_pw.setForeground(Color.LIGHT_GRAY);
				txt_pw.setBackground(Color.WHITE);
			}
		});
		txt_pw.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 16));
		txt_pw.setBounds(116, 299, 185, 35);
		contentPane.add(txt_pw);

		JLabel lb_FindPW = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lb_FindPW.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login_FindPW fpw = new Login_FindPW(ln);
				Point fPt = ln.getLocationOnScreen();
				fpw.setLocation(fPt.x + ln.getWidth() + 20, fPt.y);
				fpw.setVisible(true);

//				lb_FindPW.setOpaque(true);
//				lb_FindPW.setBackground(Color.LIGHT_GRAY);
//				fpw.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lb_FindPW.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lb_FindPW.setForeground(Color.black);
			}
		});
		lb_FindPW.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		lb_FindPW.setBounds(184, 353, 78, 25);
		contentPane.add(lb_FindPW);

		JLabel lblNewLabel_5 = new JLabel("/");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		lblNewLabel_5.setBounds(163, 350, 20, 31);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("\uCC3E\uAE30");
		lblNewLabel_6.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 16));
		lblNewLabel_6.setBounds(254, 353, 40, 25);
		contentPane.add(lblNewLabel_6);
	}
}
