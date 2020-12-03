package com.tpc.second.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
			// https://www.poswel.co.kr/fmenu/todaymenu.php?area_code=A1&nyear=2020&nmonth=12&reqday=03
			// 동촌플라자대식당 메뉴...
			String url = "https://www.poswel.co.kr/fmenu/todaymenu.php?area_code=A1&nyear=2020&nmonth=12&reqday=03";
			Document doc = Jsoup.connect(url).get();
			// System.out.println(doc);
			
			Elements today_menu_list_id = doc.select("#today_menu_list");
			// System.out.println(today_menu_list_id);
			Elements today_menu_list01_id = today_menu_list_id.select("#today_menu_list01");
			// System.out.println(today_menu_list01_id);
			Elements breakfastKorean = today_menu_list01_id.select(".menu_list_icon02 > span");		// .first();
			// String breakfastKoreanStr = breakfastKorean.text();
			// System.out.println(breakfastKorean);
			List<String> breakfastList = new ArrayList<String>();
			for(Element breakfastElement : breakfastKorean) {
				breakfastList.add(breakfastElement.text());
				// System.out.println(breakfastElement);
			}
			System.out.println(breakfastList.get(0));
			System.out.println("가격 : " + breakfastList.get(1));
			Element breakfastKcal = today_menu_list01_id.select(".menu_list_icon02").select(".gray04").first();
			String breakfastKcalStr = breakfastKcal.text();
			System.out.println("열량 : " + breakfastKcalStr);
			Element breakfastKoreanMenu = today_menu_list01_id.select(".today_menu_list01_box02").select(".list_menu_explain").first();
			String breakfastKoreanMenuStr = breakfastKoreanMenu.text();
			System.out.println("메뉴 : " + breakfastKoreanMenuStr);
			Elements today_menu_list02_class = today_menu_list_id.select(".today_menu_list02");
			// System.out.println(today_menu_list02_class);
			Elements today_menu_list01_box01_class = today_menu_list02_class.select(".today_menu_list01_box01");
			// System.out.println("today_menu_list01_box01_class => \n " + today_menu_list01_box01_class);
			Elements lunchOrDinnerMenu = today_menu_list02_class.select(".today_menu_list01_box02").select(".list_menu_explain");
			// System.out.println("lunchOrDinnerMenu => \n " + lunchOrDinnerMenu);
			Elements lunchOrDinnerElements = today_menu_list01_box01_class.select(".menu_list_icon02 > span");
			// System.out.println(lunchOrDinnerElements);
			List<String> lunchOrDinnerList = new ArrayList<String>();
			for(Element lunchOrDinnerElement : lunchOrDinnerElements) {
				lunchOrDinnerList.add(lunchOrDinnerElement.text());
				// System.out.println(breakfastElement);
			}
			// System.out.println(lunchOrDinnerList);
			Elements lunchOrDinnerKcal = today_menu_list02_class.select(".menu_list_icon02").select(".gray04");
			// System.out.println(lunchOrDinnerKcal);
			List<String> lunchOrDinnerKcalList = new ArrayList<String>();
			for(Element lunchOrDinnerKcalElement : lunchOrDinnerKcal) {
				lunchOrDinnerKcalList.add(lunchOrDinnerKcalElement.text());
			}
			// System.out.println(lunchOrDinnerKcalList);
			// System.out.println("lunchOrDinnerMenu => \n " + lunchOrDinnerMenu);
			List<String> lunchOrDinnerMenuList = new ArrayList<String>();
			for(Element lunchOrDinnerMenuElement : lunchOrDinnerMenu) {
				lunchOrDinnerMenuList.add(lunchOrDinnerMenuElement.text());
			}
			// System.out.println(lunchOrDinnerMenuList);
			
			System.out.println(lunchOrDinnerList.get(0));
			System.out.println("가격 : " + lunchOrDinnerList.get(1));
			System.out.println("열량 : " + lunchOrDinnerKcalList.get(0));
			System.out.println("메뉴 : " + lunchOrDinnerMenuList.get(0));
			System.out.println(lunchOrDinnerList.get(2));
			System.out.println("가격 : " + lunchOrDinnerList.get(3));
			System.out.println("열량 : " + lunchOrDinnerKcalList.get(1));
			System.out.println("메뉴 : " + lunchOrDinnerMenuList.get(1));
			System.out.println(lunchOrDinnerList.get(4));
			System.out.println("가격 : " + lunchOrDinnerList.get(5));
			System.out.println("열량 : " + lunchOrDinnerKcalList.get(2));
			System.out.println("메뉴 : " + lunchOrDinnerMenuList.get(2));
			System.out.println(lunchOrDinnerList.get(6));
			// System.out.println("가격 : " + lunchOrDinnerList.get(5));
			System.out.println("열량 : " + lunchOrDinnerKcalList.get(3));
			System.out.println("메뉴 : " + lunchOrDinnerMenuList.get(3));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}