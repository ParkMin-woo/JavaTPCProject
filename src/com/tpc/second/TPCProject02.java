package com.tpc.second;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import kr.bit.structure.MemberDTO;

public class TPCProject02 {
	public static void main(String[] args) {
		// 멤버로 실습
		// 멤버는 이름, 나이, 성별, 주소, 전화번호를 포함하고 있음
		// MemberDTO(String name, int age, String gender, String address, String phone)
		MemberDTO member = new MemberDTO("박민우" , 29 , "M" , "경북 포항시" , "01048015126");
		System.out.println("member => " + member);
		// OBJECT -> JSON
		Gson gson = new Gson();
		String memberJSON = gson.toJson(member);
		System.out.println("memberJSON => " + memberJSON);
		// JSON -> OBJECT
		MemberDTO returnMemberJSON = gson.fromJson(memberJSON, MemberDTO.class);
		System.out.println("returnMemberJSON => " + returnMemberJSON);
		// LIST -> JSON
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		memberList.add(new MemberDTO("박민우" , 29 , "M" , "경북 포항시" , "01048015126"));
		memberList.add(new MemberDTO("강희애" , 28 , "F" , "경기 수원시" , "01048768395"));
		memberList.add(new MemberDTO("정구" , 49 , "M" , "경기 하남시" , "01028723197"));
		System.out.println("memberList => " + memberList);
		String memberListJSON = gson.toJson(memberList);
		System.out.println("memberListJSON => " + memberListJSON);
		// JSON -> LIST
		// List<MemberDTO> returnMemberList = gson.fromJson(memberJSON , new TypeToken<ArrayList<MemberDTO>>() {}.getType());
		List<MemberDTO> returnMemberList = gson.fromJson(memberListJSON , new TypeToken<ArrayList<MemberDTO>>() {}.getType());
		System.out.println("returnMemberList => " + returnMemberList);
	}
}
