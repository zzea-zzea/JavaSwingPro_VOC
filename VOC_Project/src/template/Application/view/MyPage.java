package template.Application.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Color;

import template.Application.controller.MyPage_DB;
import template.Application.controller.MyPage_Data;
import template.Application.controller.Notice_data;
import template.Application.controller.RoundedButtonD;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import template.Application.view.Mypage_writePage;

public class MyPage extends JFrame {

	JPanel contentPane;
	JLabel lbTitle;
	JTextField nameField;
	JLabel idField;
	JTextField phone;
	JLabel genField;
	JLabel birField;
	JTextField currField;
	JTextField newpwField;
	JTextField newpwckField;
	Panel header;
	MyPage frm;
	RoundedButtonD btn_logout;
	RoundedButtonD wirte_review;
	Mypage_writePage wirteP;
	Point fPt;
	Main mafrm;
	MyPage_Data MyPage;
	JLabel errortxt;
	MyPage_DB MDB;
	int memberID = 34;

	public MyPage(Main mafrm) {
		this.frm = this;
		ArrayList<MyPage_Data> MyArr = MDB.SelectMemberID(memberID);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		header = new Panel();
		header.setLayout(null);
		header.setBackground(new Color(169, 169, 169));
		header.setBounds(0, 0, 484, 55);
		contentPane.add(header);

		RoundedButtonD btn_main = new RoundedButtonD("HOME");
		btn_main.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn_main.setFont(new Font("Candara Light", Font.PLAIN, 20));
		btn_main.setBounds(12, 10, 100, 35);
		header.add(btn_main);

		JPanel content = new JPanel();
		content.setBackground(Color.WHITE);
		content.setBounds(0, 55, 484, 705);
		contentPane.add(content);
		content.setLayout(null);

		Panel detail = new Panel();
		detail.setBackground(Color.WHITE);
		detail.setBounds(10, 0, 464, 705);
		content.add(detail);
		detail.setLayout(null);

		JPanel mem_panel = new JPanel();
		mem_panel.setBackground(new Color(220, 220, 220));
		mem_panel.setBounds(10, 40, 444, 655);
//		detail.add(mem_panel);
		mem_panel.setLayout(null);

		JPanel res_panel = new JPanel();
		res_panel.setBackground(new Color(211, 211, 211));
		res_panel.setBounds(10, 40, 444, 655);
		detail.add(res_panel);
		res_panel.setLayout(null);

		JPanel resdetail_panel = new JPanel();
		resdetail_panel.setBackground(Color.WHITE);
		resdetail_panel.setBounds(10, 10, 424, 635);
		res_panel.add(resdetail_panel);
		resdetail_panel.setLayout(null);

		Panel reser_box = new Panel();
		reser_box.setBackground(new Color(245, 245, 245));
		reser_box.setBounds(10, 80, 404, 174);
		resdetail_panel.add(reser_box);
		reser_box.setLayout(null);

		Panel poster = new Panel();
		poster.setBackground(new Color(255, 228, 196));
		poster.setBounds(10, 10, 117, 155);
		reser_box.add(poster);

		JLabel txt_reser_num = new JLabel("예약번호 : ");
		txt_reser_num.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 14));
		txt_reser_num.setBounds(133, 20, 90, 15);
		reser_box.add(txt_reser_num);

		JLabel resernum_sett = new JLabel("");
		resernum_sett.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 14));
		resernum_sett.setBounds(213, 20, 179, 15);
		reser_box.add(resernum_sett);

		JLabel lblTitle = new JLabel("영화제목 : ");
		lblTitle.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 14));
		lblTitle.setBounds(133, 45, 90, 15);
		reser_box.add(lblTitle);

		JLabel title_sett = new JLabel("");
		title_sett.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 14));
		title_sett.setBounds(212, 45, 175, 15);
		reser_box.add(title_sett);

		JLabel lblTime = new JLabel("상영시간 : ");
		lblTime.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 14));
		lblTime.setBounds(133, 70, 90, 15);
		reser_box.add(lblTime);

		JLabel time_sett = new JLabel("");
		time_sett.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 14));
		time_sett.setBounds(210, 70, 179, 15);
		reser_box.add(time_sett);

		JLabel lblSeat = new JLabel("좌석번호 : ");
		lblSeat.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 14));
		lblSeat.setBounds(133, 95, 90, 15);
		reser_box.add(lblSeat);

		JLabel seat_sett = new JLabel("");
		seat_sett.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 14));
		seat_sett.setBounds(209, 95, 176, 15);
		reser_box.add(seat_sett);

		wirte_review = new RoundedButtonD("Go to write a review");
		wirte_review.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 16));
		wirte_review.setText("한줄평 작성하기");
		wirte_review.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wirteP = new Mypage_writePage(frm);
				fPt = frm.getLocationOnScreen();
				wirteP.setLocation(fPt.x + frm.getWidth() + 20, fPt.y);
				wirteP.setVisible(true);
			}
		});
		wirte_review.setBounds(133, 131, 259, 34);
		reser_box.add(wirte_review);

		JLabel lblReservation = new JLabel("Reservation");
		lblReservation.setHorizontalAlignment(SwingConstants.CENTER);
		lblReservation.setFont(new Font("Candara Light", Font.BOLD, 36));
		lblReservation.setBounds(14, 19, 400, 55);
		resdetail_panel.add(lblReservation);

		JPanel none_resdetail_panel = new JPanel();
		none_resdetail_panel.setBackground(Color.WHITE);
		none_resdetail_panel.setBounds(10, 10, 424, 635);
		res_panel.add(none_resdetail_panel);
		none_resdetail_panel.setLayout(null);

		JLabel nonereservatiompanel = new JLabel("There are currently no reservations");
		nonereservatiompanel.setForeground(new Color(165, 42, 42));
		nonereservatiompanel.setFont(new Font("Candara Light", Font.PLAIN, 20));
		nonereservatiompanel.setHorizontalAlignment(SwingConstants.CENTER);
		nonereservatiompanel.setBounds(51, 10, 324, 15);
		none_resdetail_panel.add(nonereservatiompanel);

		RoundedButtonD btn_meminfo = new RoundedButtonD("member info");
		btn_meminfo.setBackground(new Color(128, 128, 128));
		btn_meminfo.setFont(new Font("Candara Light", Font.PLAIN, 20));
		btn_meminfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mem_panel.setVisible(true);
				res_panel.setVisible(false);
				lbTitle.setText("Member Info");
			}
		});
		btn_meminfo.setBounds(18, 10, 130, 35);
		detail.add(btn_meminfo);

		RoundedButtonD btn_reser = new RoundedButtonD("reservtion");
		btn_reser.setFont(new Font("Candara Light", Font.PLAIN, 20));
		btn_reser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mem_panel.setVisible(false);
				res_panel.setVisible(true);
				lbTitle.setText("Reservtion");
			}
		});
		btn_reser.setBounds(151, 10, 105, 35);
		detail.add(btn_reser);

		Panel memdetail_panel = new Panel();
		memdetail_panel.setLayout(null);
		memdetail_panel.setBackground(Color.WHITE);
		memdetail_panel.setBounds(10, 10, 424, 635);
		mem_panel.add(memdetail_panel);

		RoundedButtonD btn_edit = new RoundedButtonD("Edit");
		btn_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!nameField.getText().isEmpty() && !phone.getText().isEmpty() && !currField.getText().isEmpty()
						&& !newpwField.getText().isEmpty() && !newpwckField.getText().isEmpty()) {
					if (currField.getText().equals(MyArr.get(0).getPassword()) && newpwField.getText().equals(newpwckField.getText())) {
						errortxt.setForeground(Color.blue);
						errortxt.setText("성공!");
						MDB.UpdateMemberInfo(memberID,nameField.getText(), phone.getText(), newpwField.getText());
						System.out.println("업데이트 성공");
					} else {
						if(!currField.getText().equals(MyArr.get(0).getPassword()) ) {
							errortxt.setText("현재 비밀번호가 불일치 합니다");
							System.out.println("비밀번호 불일치");
						}else if(!newpwField.getText().equals(newpwckField.getText())) {
							errortxt.setText("새 비밀번호끼리 불일치 합니다");
							System.out.println("비밀번호 필드끼리 불일치");
						}
					}
					System.out.println("빈칸 없음");
				} else {
					errortxt.setText("빈칸이 존재 합니다");
					System.out.println("빈칸있음");
				}
			}
		});
		btn_edit.setFont(new Font("Candara Light", Font.PLAIN, 20));
		btn_edit.setBounds(12, 584, 400, 40);
		memdetail_panel.add(btn_edit);

		Panel lbPanel = new Panel();
		lbPanel.setBounds(0, 119, 162, 373);
		memdetail_panel.add(lbPanel);
		lbPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel label = new JLabel("이름 : ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 16));
		lbPanel.add(label);

		JLabel label_1 = new JLabel("아이디 : ");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 16));
		lbPanel.add(label_1);

		JLabel label_2 = new JLabel("전화번호 : ");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 16));
		lbPanel.add(label_2);

		JLabel label_3 = new JLabel("성별 : ");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 16));
		lbPanel.add(label_3);

		JLabel label_4 = new JLabel("생년월일 : ");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 16));
		lbPanel.add(label_4);

		JLabel label_5 = new JLabel("현재 비밀번호 : ");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 16));
		lbPanel.add(label_5);

		JLabel label_6 = new JLabel("세 비밀번호 : ");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 16));
		lbPanel.add(label_6);

		JLabel label_7 = new JLabel("새 비밀번호 확인 : ");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 16));
		lbPanel.add(label_7);

		Panel fieldPanel = new Panel();
		fieldPanel.setLayout(null);
		fieldPanel.setBounds(168, 119, 256, 373);
		memdetail_panel.add(fieldPanel);

		nameField = new JTextField();
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setColumns(10);
		nameField.setBounds(0, 5, 246, 40);
		nameField.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		nameField.setText(MyArr.get(0).getName());
		fieldPanel.add(nameField);

		idField = new JLabel();
		idField.setHorizontalAlignment(SwingConstants.CENTER);
		idField.setBounds(0, 52, 246, 40);
		idField.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		idField.setText(MyArr.get(0).getId());
		fieldPanel.add(idField);

		phone = new JTextField();
		phone.setHorizontalAlignment(SwingConstants.CENTER);
		phone.setColumns(10);
		phone.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		phone.setText(MyArr.get(0).getPhoneNumber());
		phone.setBounds(0, 98, 246, 40);
		fieldPanel.add(phone);

		genField = new JLabel();
		genField.setHorizontalAlignment(SwingConstants.CENTER);
		genField.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		String gen = "";
		if (MyArr.get(0).getGender() == 0) {
			gen = "남자";
		} else {
			gen = "여자";
		}
		genField.setText(gen);
		genField.setBounds(0, 142, 246, 40);
		fieldPanel.add(genField);

		birField = new JLabel();
		birField.setHorizontalAlignment(SwingConstants.CENTER);
		birField.setText(MyArr.get(0).getBirthday());
		birField.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		birField.setBounds(0, 186, 246, 40);
		fieldPanel.add(birField);

		currField = new JTextField();
		currField.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		currField.setHorizontalAlignment(SwingConstants.CENTER);
		currField.setColumns(10);
		currField.setBounds(0, 235, 246, 40);
		fieldPanel.add(currField);

		newpwField = new JTextField();
		newpwField.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		newpwField.setHorizontalAlignment(SwingConstants.CENTER);
		newpwField.setColumns(10);
		newpwField.setBounds(0, 279, 246, 40);
		fieldPanel.add(newpwField);

		newpwckField = new JTextField();
		newpwckField.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		newpwckField.setHorizontalAlignment(SwingConstants.CENTER);
		newpwckField.setColumns(10);
		newpwckField.setBounds(0, 324, 246, 40);
		fieldPanel.add(newpwckField);

		lbTitle = new JLabel("Member Info");
		lbTitle.setBounds(12, 25, 400, 55);
		memdetail_panel.add(lbTitle);
		lbTitle.setFont(new Font("Candara Light", Font.BOLD, 36));
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);

		errortxt = new JLabel("");
		errortxt.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 10));
		errortxt.setForeground(Color.RED);
		errortxt.setBounds(168, 498, 244, 24);
		memdetail_panel.add(errortxt);

		btn_logout = new RoundedButtonD("LOGOUT");
		btn_logout.setBounds(347, 10, 100, 35);
		detail.add(btn_logout);
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				Login loginpage = new Login();
				loginpage.setVisible(true);
			}
		});
		btn_logout.setFont(new Font("Candara Light", Font.PLAIN, 20));

	}
}
