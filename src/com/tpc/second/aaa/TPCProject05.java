package com.tpc.second.aaa;

import org.json.JSONArray;
import org.json.JSONObject;

public class TPCProject05 {
	public static void main(String[] args) {
		// MemberDTO를 가지고 JSONObject를 만들어 보자.
		// 멤버는 이름, 나이, 성별, 주소, 번호로 구성
		JSONObject member = new JSONObject();
		member.put("name", "박민우");
		member.put("age", 29);
		member.put("gender", "M");
		member.put("address", "경북 포항시");
		member.put("phone", "01048015126");
		System.out.println("member1 => " + member);
		JSONArray memberArray = new JSONArray();
		memberArray.put(member);
		System.out.println("memberArray1 => " + memberArray);
		// memberList.add(new MemberDTO("강희애" , 28 , "F" , "경기 수원시" , "01048768395"));
		// memberList.add(new MemberDTO("정구" , 49 , "M" , "경기 하남시" , "01028723197"));
		
		member = new JSONObject();
		member.put("name", "강희애");
		member.put("age", 28);
		member.put("gender", "F");
		member.put("address", "경기 수원시");
		member.put("phone", "01048768395");
		System.out.println("member2 => " + member);
		memberArray.put(member);
		System.out.println("memberArray2 => " + memberArray);
		
		member = new JSONObject();
		member.put("name", "정구");
		member.put("age", 49);
		member.put("gender", "M");
		member.put("address", "경기 하남시");
		member.put("phone", "01028723197");
		System.out.println("member3 => " + member);
		memberArray.put(member);
		System.out.println("memberArray3 => " + memberArray);
		JSONObject memberList = new JSONObject();
		memberList.put("memberList", memberArray);
		System.out.println("memberList => \n" + memberList.toString(2));
	}
}
