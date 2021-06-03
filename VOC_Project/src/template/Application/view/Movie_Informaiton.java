package template.Application.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import template.Application.controller.DB_Connect;
import template.Application.controller.Movie_DB;
import template.Application.controller.Movie_Data;
import template.Application.controller.Review_DB;
import template.Application.controller.Review_Data;
import template.Application.controller.RoundedButtonD;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.TextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Movie_Informaiton extends JFrame {

	private JPanel contentPane;
	private final Panel header_panel = new Panel();
	private Panel content_panel;
	private Panel div_panel;
	private Panel mem_panel;
	private Panel mem_detail;
	private RoundedButtonD btn_edit;
	private Panel txt_panel;
	private JLabel lb_name;
	private JLabel lb_ld;
	private JLabel lb_phone;
	private JLabel lb_bir;
	private JLabel lb_cp;
	private JLabel lb_np;
	private JLabel lb_cnp;
	private Panel txtF_panel;
	private JLabel lb_gen;
	private JTextField textField_1;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JPanel pn_Poster;
	private JPanel panel_1;
	private JPanel panel_2;
	private JScrollPane scrollPane;
	private JPanel panel_4;
	private JPanel panel_7;
	private JLabel lb_Title;
	private JLabel lb_MVTitle;
	private JLabel lb_Directer;
	private JLabel lb_MVDirecter;
	private JLabel lb_Genre;
	private JLabel lb_MVGenre;
	private JLabel lb_Rate;
	private JLabel lb_MVRate;
	private JLabel lb_producte;
	private JLabel lb_MVProducte;
	private JLabel lb_Age;
	private JLabel lb_MVAge;
	private JLabel lb_Character;
	private JLabel lb_MVCharacter;
	private JLabel lb_Year;
	private JLabel lb_MVYear;
	private JLabel lb_MovieDate;
	private JLabel lb_Movietime;
	private JLabel lb_MovieInfomaiton;
	private JPanel panel_9;
	private JScrollPane scrollPane_1;
	private JPanel pn_Review;

	DB_Connect connect;
	Movie_Data Movie;
	ArrayList<Movie_Data> MovieList = new ArrayList<>();
	Main refrm;
	Movie_Informaiton frm;
	ArrayList<Review_Data> ReviewList = new ArrayList<>();
	Review_DB Review;
	private JLabel lblNewLabel_3;
	private JLabel label_2;
	private JLabel lblNewLabel_1;

	public Movie_Informaiton(Main refrm, Movie_Data movie) {
		setResizable(false);
		this.frm = this;
		Movie_DB MDB = new Movie_DB();
		MovieList = MDB.getMovieData();
		int Num = MDB.getMovieIDFromImage(movie.getImagefilename());
		ReviewList = Review.getReviewData(Num);
		int PK = 0;
		for (int i = 0; i < MovieList.size(); i++) {
			if (MovieList.get(i).getMoviesid() == Num)
				PK = i;
		}
		String dummyDate = "" + MovieList.get(PK).getScheduledate();
		String[] date = dummyDate.split("-");

		int itime = 17 + (2 * MovieList.get(PK).getScheduletime());
		String time = "";
		if (itime > 24) {
			time = "" + (itime - 24);
		} else {
			time = "" + itime;
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 800);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		header_panel.setBackground(new Color(169, 169, 169));
		header_panel.setBounds(0, 0, 494, 55);
		contentPane.add(header_panel);
		header_panel.setLayout(null);

		RoundedButtonD roundedButtonD = new RoundedButtonD("HOME");
		roundedButtonD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		roundedButtonD.setFont(new Font("Candara Light", Font.PLAIN, 20));
		roundedButtonD.setBounds(12, 10, 100, 35);
		header_panel.add(roundedButtonD);

		content_panel = new Panel();
		content_panel.setBackground(new Color(255, 255, 255));
		content_panel.setBounds(0, 55, 494, 716);
		contentPane.add(content_panel);
		content_panel.setLayout(null);

		div_panel = new Panel();
		div_panel.setBackground(Color.LIGHT_GRAY);
		div_panel.setBounds(0, 0, 494, 260);
		content_panel.add(div_panel);
		div_panel.setLayout(null);

		pn_Poster = new JPanel();
		pn_Poster.setBounds(10, 10, 187, 240);
		div_panel.add(pn_Poster);
		pn_Poster.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("" + MovieList.get(PK).getImagefilename()));
		pn_Poster.add(lblNewLabel, BorderLayout.CENTER);

		panel_1 = new JPanel();
		panel_1.setBounds(204, 10, 278, 35);
		div_panel.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));

		lblNewLabel_3 = new JLabel("상영날짜 : ");
		lblNewLabel_3.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 13));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_3);

		lb_MovieDate = new JLabel(date[1] + "/" + date[2]);
		lb_MovieDate.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 13));
		panel_1.add(lb_MovieDate);

		label_2 = new JLabel("상영시간 : ");
		label_2.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 13));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label_2);

		lb_Movietime = new JLabel(time + ":00");
		lb_Movietime.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 13));
		panel_1.add(lb_Movietime);

		panel_2 = new JPanel();
		panel_2.setBounds(204, 75, 278, 175);
		div_panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_2.add(scrollPane, BorderLayout.CENTER);

		JTextArea txtrAsd = new JTextArea();
		txtrAsd.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 13));
		txtrAsd.setText(MovieList.get(PK).getStory());
		txtrAsd.setEditable(false);
		txtrAsd.setLineWrap(true);
		scrollPane.setViewportView(txtrAsd);

		lblNewLabel_1 = new JLabel("-- 줄거리 --");
		lblNewLabel_1.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(209, 55, 77, 15);
		div_panel.add(lblNewLabel_1);

		panel_4 = new JPanel();
		panel_4.setBounds(10, 266, 472, 252);
		content_panel.add(panel_4);
		panel_4.setLayout(null);

		panel_7 = new JPanel();
		panel_7.setBounds(0, 40, 472, 212);
		panel_4.add(panel_7);
		panel_7.setLayout(new GridLayout(9, 2, 0, 0));

		lb_Title = new JLabel("제목");
		lb_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Title.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		panel_7.add(lb_Title);

		lb_MVTitle = new JLabel(MovieList.get(PK).getTitle());
		lb_MVTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lb_MVTitle.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		panel_7.add(lb_MVTitle);

		lb_Genre = new JLabel("장르");
		lb_Genre.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Genre.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		panel_7.add(lb_Genre);

		lb_MVGenre = new JLabel(MovieList.get(PK).getGee());
		lb_MVGenre.setHorizontalAlignment(SwingConstants.CENTER);
		lb_MVGenre.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		panel_7.add(lb_MVGenre);

		lb_Directer = new JLabel("감독");
		lb_Directer.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Directer.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		panel_7.add(lb_Directer);

		lb_MVDirecter = new JLabel(MovieList.get(PK).getDirector());
		lb_MVDirecter.setHorizontalAlignment(SwingConstants.CENTER);
		lb_MVDirecter.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		panel_7.add(lb_MVDirecter);

		lb_Age = new JLabel("연령등급");
		lb_Age.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Age.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		panel_7.add(lb_Age);

		lb_MVAge = new JLabel("" + MovieList.get(PK).getAgegroup());
		lb_MVAge.setHorizontalAlignment(SwingConstants.CENTER);
		lb_MVAge.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		panel_7.add(lb_MVAge);

		lb_Rate = new JLabel("별점");
		lb_Rate.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Rate.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		panel_7.add(lb_Rate);

		lb_MVRate = new JLabel("" + MovieList.get(PK).getAverageScore());
		lb_MVRate.setHorizontalAlignment(SwingConstants.CENTER);
		lb_MVRate.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		panel_7.add(lb_MVRate);

		lb_Character = new JLabel("등장인물");
		lb_Character.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Character.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		panel_7.add(lb_Character);

		lb_MVCharacter = new JLabel(MovieList.get(PK).getGee());
		lb_MVCharacter.setHorizontalAlignment(SwingConstants.CENTER);
		lb_MVCharacter.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		panel_7.add(lb_MVCharacter);

		lb_Year = new JLabel("개봉일");
		lb_Year.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Year.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		panel_7.add(lb_Year);

		lb_MVYear = new JLabel("" + MovieList.get(PK).getOpendate());
		lb_MVYear.setHorizontalAlignment(SwingConstants.CENTER);
		lb_MVYear.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		panel_7.add(lb_MVYear);

		lb_producte = new JLabel("제작사");
		lb_producte.setHorizontalAlignment(SwingConstants.CENTER);
		lb_producte.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		panel_7.add(lb_producte);

		lb_MVProducte = new JLabel(MovieList.get(PK).getProduction());
		lb_MVProducte.setHorizontalAlignment(SwingConstants.CENTER);
		lb_MVProducte.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		panel_7.add(lb_MVProducte);

		JLabel lb_Runningtime = new JLabel("영화 상영 시간");
		lb_Runningtime.setHorizontalAlignment(SwingConstants.CENTER);
		lb_Runningtime.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		panel_7.add(lb_Runningtime);

		JLabel lb_MVRunningTime = new JLabel(MovieList.get(PK).getRunningtime());
		lb_MVRunningTime.setFont(new Font("맑은 고딕 Semilight", Font.PLAIN, 15));
		lb_MVRunningTime.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lb_MVRunningTime);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 472, 41);
		panel_4.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Infomation");
		lblNewLabel_4.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_4);

		panel_9 = new JPanel();
		panel_9.setBounds(10, 528, 472, 178);
		content_panel.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));

		scrollPane_1 = new JScrollPane();
		panel_9.add(scrollPane_1, BorderLayout.CENTER);

		pn_Review = new JPanel();
		scrollPane_1.setViewportView(pn_Review);
		pn_Review.setLayout(new GridLayout(0, 1, 0, 0));

		lb_MovieInfomaiton = new JLabel("내용");
		lb_MovieInfomaiton.setBounds(262, 321, 232, 40);
		contentPane.add(lb_MovieInfomaiton);
		lb_MovieInfomaiton.setFont(new Font("굴림", Font.BOLD, 20));
		lb_MovieInfomaiton.setHorizontalAlignment(SwingConstants.CENTER);
		mem_panel = new Panel();
		mem_panel.setBackground(new Color(255, 228, 196));
		mem_panel.setBounds(10, 120, 444, 570);
//		div_panel.add(mem_panel);
		mem_panel.setLayout(null);

		mem_detail = new Panel();
		mem_detail.setBackground(new Color(255, 255, 255));
		mem_detail.setBounds(10, 10, 424, 550);
		mem_panel.add(mem_detail);
		mem_detail.setLayout(null);
		mem_panel.setVisible(false);
		btn_edit = new RoundedButtonD("Edit");
		btn_edit.setFont(new Font("SansSerif", Font.BOLD, 15));
		btn_edit.setBounds(162, 505, 100, 35);
		mem_detail.add(btn_edit);

		txt_panel = new Panel();
		txt_panel.setBounds(0, 0, 162, 462);
		mem_detail.add(txt_panel);
		txt_panel.setLayout(new GridLayout(0, 1, 0, 0));

		lb_name = new JLabel("Name");
		lb_name.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lb_name.setHorizontalAlignment(SwingConstants.CENTER);
		txt_panel.add(lb_name);

		lb_ld = new JLabel("ID");
		lb_ld.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lb_ld.setHorizontalAlignment(SwingConstants.CENTER);
		txt_panel.add(lb_ld);

		lb_phone = new JLabel("Phone");
		lb_phone.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lb_phone.setHorizontalAlignment(SwingConstants.CENTER);
		txt_panel.add(lb_phone);

		lb_gen = new JLabel("Gender");
		lb_gen.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lb_gen.setHorizontalAlignment(SwingConstants.CENTER);
		txt_panel.add(lb_gen);

		lb_bir = new JLabel("BirthDay");
		lb_bir.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lb_bir.setHorizontalAlignment(SwingConstants.CENTER);
		txt_panel.add(lb_bir);

		lb_cp = new JLabel("Current PW");
		lb_cp.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lb_cp.setHorizontalAlignment(SwingConstants.CENTER);
		txt_panel.add(lb_cp);

		lb_np = new JLabel("New PW");
		lb_np.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lb_np.setHorizontalAlignment(SwingConstants.CENTER);
		txt_panel.add(lb_np);

		lb_cnp = new JLabel("Confirm New PW");
		lb_cnp.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lb_cnp.setHorizontalAlignment(SwingConstants.CENTER);
		txt_panel.add(lb_cnp);

		txtF_panel = new Panel();
		txtF_panel.setBounds(168, 0, 256, 462);
		mem_detail.add(txtF_panel);
		txtF_panel.setLayout(null);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(0, 10, 246, 40);
		txtF_panel.add(textField_1);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(0, 65, 246, 40);
		txtF_panel.add(textField);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(0, 123, 246, 40);
		txtF_panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(0, 183, 246, 40);
		txtF_panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(0, 242, 246, 40);
		txtF_panel.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(0, 302, 246, 40);
		txtF_panel.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(0, 358, 246, 40);
		txtF_panel.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(0, 412, 246, 40);
		txtF_panel.add(textField_7);

		for (int i = 0; i < ReviewList.size(); i++) { // 리뷰 개수
			String text = ReviewList.get(i).getContent();
			JLabel Review = new JLabel(text);
			Review.setFont(new Font("한컴 고딕", Font.PLAIN, 15));
			Review.setSize(new Dimension(400, 50));
			pn_Review.add(Review);
		}

	}
}
