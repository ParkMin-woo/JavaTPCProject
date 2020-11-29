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

			Element breakfastClass = today_menu_list_id.select("#today_menu_list01").select(".menu_list_icon02 > span").first();
			// System.out.println(breakfastClass);
			String breakfast = breakfastClass.text();
			System.out.println(breakfast);
			Element breakfastMenuClass = today_menu_list_id.select("#today_menu_list01").select(".today_menu_list01_box02 > div").first();
			String breakfastMenu = breakfastMenuClass.text();
			System.out.println(breakfastMenu);
			Element lunchClass = today_menu_list_id.select(".today_menu_list02").select(".menu_list_icon02 > span").first();
			// System.out.println(lunchClass);
			String lunch = lunchClass.text();
			System.out.println(lunch);
			Element lunchMenuClass = today_menu_list_id.select(".today_menu_list02").select(".today_menu_list01_box02 > div").first();
			String lunchMenu = lunchMenuClass.text();
			System.out.println(lunchMenu);
			Element dinnerClass = today_menu_list_id.select(".today_menu_list02").select(".today_menu_list01_box01").select(".menu_list_icon02 > span").first();
			System.out.println(dinnerClass);
			/*
			String lunch = lunchClass.text();
			System.out.println(lunch);
			Element lunchMenuClass = today_menu_list_id.select(".today_menu_list02").select(".today_menu_list01_box02 > div").first();
			String lunchMenu = lunchMenuClass.text();
			System.out.println(lunchMenu);
			*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}