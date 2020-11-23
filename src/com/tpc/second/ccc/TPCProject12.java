package com.tpc.second.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import kr.bit.api.DownloadBroker;

public class TPCProject12 {
	public static void main(String[] args) {
		// String url = "https://sum.su.or.kr:8888/bible/today/Ajax/Bible/BodyMatter?qt_ty=QT1";
		BufferedReader inputDataReader = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader reader = new BufferedReader(System.in);
		// try catch문을 이용하여 예외처리를 한다.
		// 주소를 입력받는다.
		try {
			System.out.print("[입력 -> 년(YYYY)-월(MM)-일(DD)] : ");
			String bibleDate = inputDataReader.readLine();
			// String YYYYMMDD = "(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])";
			// System.out.println(bibleDate == YYYYMMDD);
			// Pattern.compile(yyyyMMdd).matcher(bibleDate)
			// System.out.println(Pattern.compile(YYYYMMDD).matcher(bibleDate).matches());
			// if(Pattern.compile(YYYYMMDD).matcher(bibleDate).matches()) {
				/*
				System.out.println(bibleDate);
				System.out.println(bibleDate.substring(0, 4));
				System.out.println(bibleDate.substring(4, 6));
				System.out.println(bibleDate.substring(6, 8));
				System.out.println(bibleDate.substring(0, 4) + "-" + bibleDate.substring(4, 6) + "-" + bibleDate.substring(6, 8));
				*/
				String bible_date = bibleDate.substring(0, 4) + "-" + bibleDate.substring(4, 6) + "-" + bibleDate.substring(6, 8);
				// System.out.println(bible_date);
				String url = "https://sum.su.or.kr:8888/bible/today/Ajax/Bible/BodyMatter?qt_ty=QT1&base_de=" + bible_date + "&bibleType=1";
				// url = url + "&base_de=" + bibleDate + "&bibleType=1";
				Document doc = null;
				doc = Jsoup.connect(url).post();
				Element bible_text = doc.select(".bible_text").first();
				String bibleTextStr = bible_text.text();
				System.out.println(bibleTextStr);
				Element dailybible_info = doc.select("#dailybible_info").first();
				String dailybibleInfoStr = dailybible_info.ownText();
				System.out.println(dailybibleInfoStr);
				Element bibleinfo_box = doc.select(".bibleinfo_box").first();
				String bibleinfoBoxStr = bibleinfo_box.text();
				System.out.println(bibleinfoBoxStr);
				Elements bibleList = doc.select(".body_list > li");
				// System.out.println("bibleList => \n " + bibleList);
				for(Element bibleElement : bibleList) {
					String num = bibleElement.select(".num").text();
					String info = bibleElement.select(".info").text();
					System.out.println(num + " : " + info);
				}
				
				// audio(.mp3) 파일을 가져오는 실습
				Element audioSource = doc.select("audio>source").first();
				// System.out.println(audioSource);
				String audioUrl = audioSource.attr("src").trim();
				// System.out.println(audioUrl);
				String mp3FileName = audioUrl.substring(audioUrl.lastIndexOf("/") + 1);
				// System.out.println(mp3FileName);
				runner(audioUrl, mp3FileName);
				/*
				Runnable r = new DownloadBroker(audioUrl , mp3FileName);
				Thread dload = new Thread(r);
				dload.start();
				for(int i = 0 ; i < 10 ; i++) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("" + (i+1));
				}
				System.out.println();
				System.out.println("=======================================");
				*/
				
				// image(.jpg) 파일을 가져오는 실습
				Element imgSource = doc.select(".img>img").first();
				// System.out.println(imgSource);
				String imgUrl = "https://sum.su.or.kr:8888" + imgSource.attr("src").trim();
				// System.out.println(imgUrl);
				String jpgFileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
				// System.out.println(jpgFileName);
				runner(imgUrl, jpgFileName);
				/*
				Runnable r = new DownloadBroker(imgUrl , jpgFileName);
				Thread dload = new Thread(r);
				dload.start();
				for(int i = 0 ; i < 10 ; i++) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("" + (i+1));
				}
				System.out.println();
				System.out.println("=======================================");
				*/
			// }
			// else {
				// System.out.println("[YYYYMMDD] 형식으로 입력하지 않았습니다.\n다시 입력해 주세요.");
				// return;
			// }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 //이전
    function SelectPrevious(tab) {
         $.ajax({
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            url: "/Ajax/Bible/BodyMatter",
             //data: "{ 'qt_ty' : '" + $("#qt_ty").val() + "' , 'Base_de' : '" + $("#base_de").val() + "'}",
            data: "{ 'qt_ty' : '" + $("#qt_ty").val() + "' ,'SearchTxt1' : 'pre' , 'Base_de' : '" + $("#base_de").val() + "'}",
		 */
		// <input name="qt_ty" id="qt_ty" type="hidden" value="QT1">
		// <input name="base_de" id="base_de" type="hidden" value="2020-11-22">
		// <input name="bibleType" id="bibleType" type="hidden" value="1">
		
		/*
		String url = "https://sum.su.or.kr:8888/bible/today/Ajax/Bible/BodyMatter/qt_ty=QT1/base_de" + ;
		Document doc = null;
		try {
			doc = Jsoup.connect(url).post();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Element bible_text = doc.select("bible_text").first();
		System.out.println(bible_text);
		*/
	}
	
	public static void runner(String urlAddress , String fileName) {
		Runnable r = new DownloadBroker(urlAddress , fileName);
		Thread dload = new Thread(r);
		dload.start();
		for(int i = 0 ; i < 10 ; i++) {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("" + (i+1));
		}
		System.out.println();
		System.out.println("=======================================");
	}
}
