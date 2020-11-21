package com.tpc.second.ccc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// JSoup를 이용하여 연합뉴스 속보 Crawling
public class TPCProject10 {
	public static void main(String[] args) {
		// JSoup이란...
		// 데이터를 쉽게 가져오기 위해서
		// html브라우저의 요소들을 자바에서 jquery처럼 사용할 수 있는
		// API이다.
		// 위에 4줄은 내 생각이고...
		// 원문은 다음과 같다.
		/*
		 jsoup: Java HTML Parser
		 jsoup is a Java library for working with real-world HTML.
		 It provides a very convenient API for fetching URLs and extracting and manipulating data,
		 using the best of HTML5 DOM methods and CSS selectors.
		 */
		String url = "https://news.naver.com/main/list.nhn?mode=LPOD&mid=sec&sid1=001&sid2=140&oid=001&isYeonhapFlash=Y&aid=0012030554";
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
			// System.out.println("document => " + document);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// String title = document.select("h3");
		Elements elements = document.select("td.content");
		// .select("h3")
		// title.select("h3");
		// System.out.println("elements => \n" + elements);
		String title = elements.select("h3").text();
		// System.out.println("title => " + title);
		System.out.println("======================================================");
		System.out.println(title);
		System.out.println("======================================================");
		elements = elements.select("ul.type02");
		// System.out.println("elements => \n" + elements.select("li._rcount a"));
		
		for(Element element : elements.select("li._rcount a")) {
			System.out.println(element.text());
		}
		
	}
}
