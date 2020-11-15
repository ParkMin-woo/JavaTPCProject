package com.tpc.second.aaa;

import org.json.JSONArray;
import org.json.JSONObject;

public class TPCProject03 {
	public static void main(String[] args) {
		// JSON-Java(org.json)
		JSONObject book = new JSONObject();
		JSONArray bookList = new JSONArray();
		JSONObject books = new JSONObject();
		System.out.println("생성되었을 시 book => " + book);
		System.out.println("생성되었을 시 bookList => " + bookList);
		// new BookDTO("이낙연의 언어", 15000, "타래", 350);
		// new BookDTO("제3의 물결", 17000, "홍신문화사", 500);
		book.put("title", "이낙연의 언어");
		book.put("price", 15000);
		book.put("company", "타래");
		book.put("page", 350);
		// bookList.put("bookList" , bookList);
		// bookList.put("title", "제3의 물결");
		System.out.println("book => " + book);
		System.out.println("title => " + book.get("title"));
		System.out.println("price => " + book.get("price"));
		System.out.println("company => " + book.get("company"));
		System.out.println("page => " + book.get("page"));
		bookList.put(book);
		System.out.println("bookList1 => " + bookList);
		/*
		Object[] objArray[] {
			bookList
		};
		*/
		// objArray = {bookList};
		
		book = new JSONObject();
		book.put("title", "제3의 물결");
		book.put("price", 17000);
		book.put("company", "홍신문화사");
		book.put("page", 500);
		System.out.println("book => " + book);
		System.out.println("title => " + book.get("title"));
		System.out.println("price => " + book.get("price"));
		System.out.println("company => " + book.get("company"));
		System.out.println("page => " + book.get("page"));
		bookList.put(book);
		System.out.println("bookList2 => " + bookList);
		
		books.put("bookList", bookList);
		System.out.println("books => " + books);
		System.out.println("books => " + books.toString(2));
		JSONArray getBookList = (JSONArray) books.get("bookList");
		System.out.println("getBookList => " + getBookList);
		for(Object getBook : getBookList) {
			System.out.println("getBook => " + getBook);
		}
		for(int i = 0 ; i < getBookList.length() ; i++) {
			System.out.println("getBook => " + getBookList.get(i));
		}
	}
}
