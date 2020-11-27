package kr.bit.gui;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class InitGUICrawling extends JFrame implements ActionListener, ItemListener  {
	private Choice chYear, chMonth;
	private JLabel yLabel, mLabel;
	private JTextArea area;
	GregorianCalendar gc;
	private int year, month;
	private JLabel[] dayLabel = new JLabel[7];
	private String[] day = {"일" , "월" , "화" , "수" , "목" , "금" , "토"};
	private JButton[] days = new JButton[42];		// 1주는 7일, 한 달은 4 ~ 6주 정도의 기간을 차지하므로 42개의 버튼이 필요
	private JPanel selectPanel = new JPanel();
	private GridLayout grid = new GridLayout(7 , 7 , 5 , 5);	// 행, 열 , 수평갭 , 수직갭
	private Calendar ca = Calendar.getInstance();
	private Dimension dimension1, dimension2;
	private int xPos, yPos;
	
	public InitGUICrawling() {
		/*
		setTitle("오늘의 QT : " + ca.get(Calendar.YEAR) + "/" + (ca.get(Calendar.MONTH) + 1) + "/" + ca.get(Calendar.DATE));
		setSize(900 , 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.dimension1 = Toolkit.getDefaultToolkit().getScreenSize();
		this.dimension2 = this.getSize();
		this.xPos = (int)(dimension1.getWidth()/2 - dimension2.getWidth()/2);
		this.yPos = (int)(dimension1.getHeight()/2 - dimension2.getHeight()/2);
		setLocation(xPos, yPos);	// 화면의 가운데에 출력
		setResizable(false);
		setVisible(true);
		this.chYear = new Choice();
		this.chMonth = new Choice();
		this.yLabel = new JLabel("년");
		this.mLabel = new JLabel("월");
		this.init();
		*/
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.area.setText("");
		String year = chYear.getSelectedItem();
		String month = chMonth.getSelectedItem();
		JButton button = (JButton) e.getSource();
		String day = button.getText();
		System.out.println(year + "-" + month + "-" + day);
		String bible_date = year + "-" + month + "-" + day;
		String url = "https://sum.su.or.kr:8888/bible/today/Ajax/Bible/BodyMatter?qt_ty=QT1&base_de=" + bible_date + "&bibleType=1&SearchTxt1=CalDer";
		try {
			Document doc = null;
			doc = Jsoup.connect(url).post();
			Element bible_text = doc.select(".bible_text").first();
			String bibleTextStr = bible_text.text();
			System.out.println(bibleTextStr);
			Element dailybible_info = doc.select("#dailybible_info").first();
			String dailybibleInfoStr = dailybible_info.text();
			System.out.println(dailybibleInfoStr);
			Element bibleinfo_box = doc.select(".bibleinfo_box").first();
			String bibleinfoBoxStr = bibleinfo_box.text();
			System.out.println(bibleinfoBoxStr);
			this.area.append(bibleTextStr + "\n");
			this.area.append(dailybibleInfoStr + "\n");
			this.area.append(bibleinfoBoxStr + "\n");
			Elements bibleList = doc.select(".body_list > li");
			// System.out.println("bibleList => \n " + bibleList);
			for(Element bibleElement : bibleList) {
				String num = bibleElement.select(".num").text();
				String info = bibleElement.select(".info").text();
				// System.out.println(num + " : " + info);
				if(info.length() > 65) {
					info = info.substring(0, 36) + "\n" + info.substring(36, 66) + "\n" + info.substring(66) + "\n";
					this.area.append(num + ":" + info);
				}
				else if(info.length() > 35) {
					info = info.substring(0, 36) + "\n" + info.substring(36) + "\n";
					this.area.append(num + ":" + info);
				}
				else {
					this.area.append(num + ":" + info + "\n");
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	public void init() {
		setTitle("오늘의 QT : " + ca.get(Calendar.YEAR) + "/" + (ca.get(Calendar.MONTH) + 1) + "/" + ca.get(Calendar.DATE));
		setSize(900 , 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.dimension1 = Toolkit.getDefaultToolkit().getScreenSize();
		this.dimension2 = this.getSize();
		this.xPos = (int)(dimension1.getWidth()/2 - dimension2.getWidth()/2);
		this.yPos = (int)(dimension1.getHeight()/2 - dimension2.getHeight()/2);
		setLocation(xPos, yPos);	// 화면의 가운데에 출력
		setResizable(false);
		setVisible(true);
		this.chYear = new Choice();
		this.chMonth = new Choice();
		this.yLabel = new JLabel("년");
		this.mLabel = new JLabel("월");
		this.select();
		this.calendar();
	}
	
	public void select() {
		JPanel panel = new JPanel(this.grid);	// 7행 7열의 GridLayout
		for(int i = 2020 ; i >= 2000 ; i--) {
			this.chYear.add(String.valueOf(i));
		}
		for(int i = 1 ; i <= 12 ; i++) {
			this.chMonth.add(String.valueOf(i));
		}
		for(int i = 0 ; i < this.day.length ; i++) {	// 요일 이름을 레이블에 출력
			this.dayLabel[i] = new JLabel(this.day[i], JLabel.CENTER);
			panel.add(this.dayLabel[i]);
			this.dayLabel[i].setBackground(Color.GRAY);	// 사실상 의미가 없음. 바뀌지 않음.
		}
		this.dayLabel[6].setForeground(Color.BLUE);	// 토요일 색상
		this.dayLabel[0].setForeground(Color.RED);	// 일요일 색상
		for(int i = 0 ; i < 42 ; i++) {	// 모두 42개의 버튼을 생성
			this.days[i] = new JButton("");	// 제목이 없는 버튼 생성
			if(i % 7 == 0) {
				this.days[i].setForeground(Color.RED);	// 일요일 버튼의 색
			}
			else if(i % 7 == 6) {
				this.days[i].setForeground(Color.BLUE);	// 토요일 버튼의 색
			}
			else {
				this.days[i].setForeground(Color.BLACK);
			}
			this.days[i].addActionListener(this);
			panel.add(days[i]);
		}
		this.selectPanel.add(chYear);
		this.selectPanel.add(yLabel);
		this.selectPanel.add(chMonth);
		this.selectPanel.add(mLabel);
		
		this.area = new JTextArea(60 , 40);
		this.area.setCaretPosition(this.area.getDocument().getLength());
		JScrollPane scrollPane = new JScrollPane(this.area);
		this.add(this.selectPanel , "North");	// 연도와 월을 선택할 수 있는 ComboBox를 상단에 출력
		this.add(panel , "Center");
		this.add(scrollPane , "East");
		
		String m = (this.ca.get(Calendar.MONTH) + 1) + "";
		String y = ca.get(Calendar.YEAR) + "";
		this.chYear.select(y);
		this.chMonth.select(m);
		this.chYear.addItemListener(this);
		this.chMonth.addItemListener(this);
	}
	
	public void calendar() {
		this.year = Integer.parseInt(this.chYear.getSelectedItem());
		this.month = Integer.parseInt(this.chMonth.getSelectedItem());
		this.gc = new GregorianCalendar(this.year , this.month - 1 , 1);
		int max = this.gc.getActualMaximum(this.gc.DAY_OF_MONTH);	// 해당 월의 최대 일 수 획등
		int week = this.gc.get(this.gc.DAY_OF_WEEK);	// 해당 월의 시작 요일
		String today = Integer.toString(this.ca.get(Calendar.DATE));	// 오늘 날짜 획득
		String today_month = Integer.toString(this.ca.get(Calendar.MONTH) + 1);	// 오늘의 달 획득
		
		for(int i = 0 ; i < this.days.length ; i++) {
			this.days[i].setEnabled(true);
		}
		for(int i = 0 ; i < week - 1 ; i++) {	// 시작일 이전의 버튼을 비활성화
			this.days[i].setEnabled(false);
		}
		for(int i = week ; i < max + week ; i++) {
			this.days[i-1].setText((String.valueOf(i-week+1)));
			this.days[i-1].setBackground(Color.WHITE);
			if(today_month.equals(String.valueOf(this.month))) {	// 오늘이 속한 달과 같은 달인 경우
				if(today.equals(this.days[i-1].getText())) {	// 버튼의 날짜와 오늘 날짜가 일치하는 경우
					this.days[i-1].setBackground(Color.CYAN);	// 버튼의 배경색 지정
				}
			}
		}
		for(int i = (max+week-1) ; i < this.days.length ; i++) {	// 날짜가 없는 버튼을 비활성화
			this.days[i].setEnabled(false);
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		Color color = this.getBackground();
		if(e.getStateChange() == ItemEvent.SELECTED) {
			// 년이나 월이 선택되면 기존의 달력을 지우고 새로 그린다.
			for(int i = 0 ; i < 42 ; i++) {
				if( !this.days[i].getText().equals("") ) {
					this.days[i].setText("");	// 기존의 날짜를 지움
					// 달력의 배경색과 동일한 색으로 버튼의 배경색을 설정함.
					this.days[i].setBackground(color);
				}
			}
			this.calendar();
		}
	}
}
