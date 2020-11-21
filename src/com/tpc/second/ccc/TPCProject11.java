package com.tpc.second.ccc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TPCProject11 {
	public static void main(String[] args) {
		String url = "https://sports.news.naver.com/kbaseball/index.nhn";
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
			// System.out.println("document => " + document);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Elements elements = document.select("div.home_news");
		// System.out.println("elements => \n" + elements);
		String title = elements.select("h2").text();
		// System.out.println("title => " + title);
		System.out.println("======================================================");
		System.out.println(title);
		System.out.println("======================================================");
		// System.out.println("elements => \n " + elements.select("li").text());
		for(Element element : elements.select("li")) {
			String news = element.text();
			System.out.println(news);
		}
	}
}
