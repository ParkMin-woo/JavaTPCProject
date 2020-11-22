package com.tpc.second.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
			String url = "https://sum.su.or.kr:8888/bible/today/Ajax/Bible/BodyMatter?qt_ty=QT1&base_de=" + bibleDate + "&bibleType=1";
			// System.out.print(bibleDate);
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
}
