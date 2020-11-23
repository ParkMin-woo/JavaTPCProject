package kr.bit;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kr.bit.api.naver.NaverMapAPI;
import kr.bit.structure.NaverClientKey;

public class InitGUI extends NaverClientKey {
	public JTextField address;	// 실제 창이 뜨면 주소를 입력받는 변수
	public JLabel roadAddress, jibunAddress, englishAddress, x, y, imageLabel;	// 주소를 검색하면 지도 밑에 뿌려줄 변수들을 담고 있음.
																				// Naver geocoding API에서 가져온 JSON 요소들을 담고 있다.
	/**
	 * @param clientId
	 * @param clientSecretKey
	 */
	public InitGUI(String clientId, String clientSecretKey) {
		super(clientId, clientSecretKey);
		// TODO Auto-generated constructor stub
		// System.out.println("clientId => " + clientId);
		// System.out.println("clientSecretKey => " + clientSecretKey);
	}

	public void initGUI() {
		// System.out.println("super.getClientId() => " + super.getClientId());
		// System.out.println("super.getClientSecretKey() => " + super.getClientSecretKey());
		String clientId = super.getClientId();
		String clientSecretKey = super.getClientSecretKey();
		
		JFrame jFrame = new JFrame("Map View");
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) 메서드 설명
		// 창이 뜨면 우측 상단에 최소화, 최대화, 닫기 아이콘이 있는데, 닫기 아이콘을 누르면 닫히도록 설정해주는 메서드
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
		btn.addActionListener(new NaverMapAPI(this , clientId , clientSecretKey));
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
