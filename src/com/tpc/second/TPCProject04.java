package com.tpc.second;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class TPCProject04 {
	public static void main(String[] args) {
		// String src = "C:\\JavaTPC\\newWorkspace\\JavaTPCProject\\src\\bookList.json";
		// C:\JavaTPC\newWorkspace\JavaTPCProject\src\kr\bit\file\bookList.json
		// String src = "bookList.json";
		// String src = "C:\\JavaTPC\\newWorkspace\\JavaTPCProject\\src\\kr\\bit\\file\\bookList.json";
		// Thread.currentThread().getContextClassLoader().getResource(파일이름)
		// System.out.println(ClassLoader.getSystemResource("bookList.json"));
		String src = "/kr/bit/file/bookList.json";
		// IO -> Stream
		// InputStream is = TPCProject04.class.getClass().getResourceAsStream(src);
		InputStream is = TPCProject04.class.getResourceAsStream(src);
		System.out.println("is => " + is);
		if(is == null) {
			throw new NullPointerException("Cannot Find Resource File.");
		}
		else {
			JSONTokener jsonTokener = new JSONTokener(is);
			System.out.println("jsonTokener => " + jsonTokener);
			JSONObject object = new JSONObject(jsonTokener);
			System.out.println("object => " + object.toString(2));
			JSONArray bookList = object.getJSONArray("bookList");
			System.out.println("bookList => " + bookList);
			// JSONObject book = bookList.getJSONObject(0);
			// JSONObject book = bookList;
			// System.out.println("book => " + book);
			for(int i = 0 ; i < bookList.length() ; i++) {
				JSONObject book = bookList.getJSONObject(i);
				System.out.println("book => " + book);
				System.out.println("title => " + book.get("title"));
				System.out.println("page => " + book.get("page"));
				System.out.println("company => " + book.get("company"));
				System.out.println("price => " + book.get("price"));
			}
		}
	}
}
