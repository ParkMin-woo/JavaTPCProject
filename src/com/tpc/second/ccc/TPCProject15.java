package com.tpc.second.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TPCProject15 {
	public static void main(String[] args) {
		// BufferedReader inputDataReader = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader reader = new BufferedReader(System.in);
		// try catch문을 이용하여 예외처리를 한다.
		try {
			String url = "https://www.poswel.co.kr/fmenu/todaymenu.php?area_code=A1&nyear=2020&nmonth=11&reqday=28";
			Document doc = Jsoup.connect(url).get();
			// System.out.println(doc);
			
			Elements today_menu_list_id = doc.select("#today_menu_list");
			// System.out.println(today_menu_list_id);
			
			Element breakfastClass = today_menu_list_id.select(".menu_list_icon02 > span").first();
			String breakfast = breakfastClass.text();
			System.out.println(breakfast);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}