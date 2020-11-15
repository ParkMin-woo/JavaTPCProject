package com.tpc.second.aaa;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class TPCProject06 {
	public static void main(String[] args) {
		// kr.bit.file에 있는 memberList.json의 파일을 읽어서 파일 내의 데이터를 가져오는 실습을 해보자.
		String filePath = "/kr/bit/file/memberList.json";
		InputStream inputStream = TPCProject06.class.getResourceAsStream(filePath);
		System.out.println("inputStream => " + inputStream);
		if(inputStream == null) {
			throw new NullPointerException("Cannot Find Resource File.");
		}
		JSONTokener tokener = new JSONTokener(inputStream);
		System.out.println("tokener => " + tokener);
		JSONObject memberList = new JSONObject(tokener);
		System.out.println("memberList => " + memberList);
		JSONArray memberArray = (JSONArray) memberList.get("memberList");
		System.out.println("memberArray => " + memberArray);
		for(int i = 0 ; i < memberArray.length() ; i++) {
			JSONObject member = (JSONObject) memberArray.get(i);
			// System.out.println("member => " + member);
			System.out.print(member.get("name") + "\t");
			System.out.print(member.get("age") + "\t");
			System.out.print(member.get("gender") + "\t");
			System.out.print(member.get("address") + "\t");
			System.out.println(member.get("phone"));
		}
		
		/*
		for(JSONObject member : memberArray) {
			
		}
		*/
		
	}
}
