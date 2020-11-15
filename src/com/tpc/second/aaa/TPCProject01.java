package com.tpc.second.aaa;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.bit.structure.BookDTO;

public class TPCProject01 {
	public static void main(String[] args) {
		// System.out.println("Welcome To TPCProject01!");
		// 2명의 회원정보를 표현
		// 화원은 이름, 나이, 주소, 전화번호를 가지고 있음.
		// 아스키코드가 출력되버림
		/*
		String memberJSON = "[{ 'name' : \"박민우\" , 'age' : 29 , 'address' : \"경북 포항시\" , 'phone' : \"010.4801.5126\" }"
				+ "\n, { 'name' : \"강희애\" , 'age' : 28 , 'address' : \"경기 수원시\" , 'phone' : \"010.4876.8395\"}]";
		System.out.println("memberJSON => " + memberJSON);
		Gson gson = new Gson();
		System.out.println("gson => " + gson.toJson(memberJSON));
		*/
		
		// Object -> JSON
		// BookDTO(String title, int price, String company, int page)
		BookDTO book1 = new BookDTO("이낙연의 언어", 15000, "타래", 350);
		Gson gson = new Gson();
		String bookJSONObject = gson.toJson(book1);
		System.out.println("bookJSONObject => " + bookJSONObject);
		
		// JSON -> Object
		BookDTO returnBook1 = gson.fromJson(bookJSONObject , BookDTO.class);
		System.out.println("returnBook1 => " + returnBook1);
		System.out.println(returnBook1.getTitle() + "\t" + returnBook1.getPrice() + "\t" + returnBook1.getCompany() + "\t" + returnBook1.getPage());
		
		// List -> JSON
		List<BookDTO> bookList = new ArrayList<BookDTO>();
		bookList.add(new BookDTO("제3의 물결", 17000, "홍신문화사", 500));
		bookList.add(new BookDTO("클라우스 슈밥의 제4차 산업혁명", 15000, "메가스터디북스", 350));
		bookList.add(new BookDTO("어록으로 본 이낙연", 15000, "삼인출판사", 325));
		String bookListJSON = gson.toJson(bookList);
		System.out.println("bookListJSON => " + bookListJSON);
		
		// JSON -> List
		List<BookDTO> returnBookList = gson.fromJson(bookListJSON, new TypeToken<List<BookDTO>>() {}.getType());
		System.out.println("returnBookList => " + returnBookList);
		for(BookDTO returnBookElement : returnBookList) {
			System.out.println("returnBookElement => " + returnBookElement);
		}
	}
}
