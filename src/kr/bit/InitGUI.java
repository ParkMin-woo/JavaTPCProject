package kr.bit;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kr.bit.api.NaverMapAPI;

public class InitGUI {
	public JTextField address;
	public JLabel roadAddress, jibunAddress, englishAddress, x, y, imageLabel;
	
	public void initGUI() {
		JFrame jFrame = new JFrame("Map View");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = jFrame.getContentPane();
		imageLabel = new JLabel("지도보기");
		JPanel panel1 = new JPanel();
		JLabel addressLbl = new JLabel("주소입력");
		address = new JTextField(50);
		JButton btn = new JButton("클릭");
		panel1.add(addressLbl);
		panel1.add(address);
		panel1.add(btn);
		btn.addActionListener(new NaverMapAPI(this));
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(5, 1));
		roadAddress = new JLabel("도로명 주소");
		jibunAddress = new JLabel("지번 주소");
		englishAddress = new JLabel("영문 주소");
		x = new JLabel("경도");
		y = new JLabel("위도");
		panel2.add(roadAddress);
		panel2.add(jibunAddress);
		panel2.add(englishAddress);
		panel2.add(x);
		panel2.add(y);
		container.add(BorderLayout.NORTH , panel1);
		container.add(BorderLayout.CENTER , imageLabel);
		container.add(BorderLayout.SOUTH , panel2);
		jFrame.setSize(730, 660);
		jFrame.setVisible(true);
	}
}
