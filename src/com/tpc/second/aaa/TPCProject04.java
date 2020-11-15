package com.tpc.second.aaa;

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
		
		// json파일이 저장된 경로를 불러올 파일명을 담을 수 있는 String형 객체를 선언한다.
		// 만약 json파일이 저장된 위치와 main 메서드를 실행하는 파일이 저장된 위치가 같다면은 파일 이름만 작성하고,
		// 두 위치가 다르다면 json파일이 저장된 경로까지 작성한다.
		// 이 때, \(역슬래쉬)가 들어가면 안되고 /(슬래쉬)가 포함된 경로로 작성해준다.
		String src = "/kr/bit/file/bookList.json";
		// IO -> Stream
		
		// ※ Java에서의 Stream이란...
		// 기본적으로는 '데이터의 흐름'이라고 함.
		// 사실 자바8에서 추가되었다고 하는데, 인터넷을 써칭하면 개념이 많이 어렵다...
		// 여기서는 Input Stream, Output Steam을 다뤄보자. 그래야지 여기서 쓰이는 개념들이 이해가 될 것 같다.
		// Stream은 단일 방향으로의 데이터 흐름을 의미하는데, 이때문에 단방향성을 가지고,
		// Input Stream(입력 스트림), Output Stream(출력 스트림)으로 나뉘어진다.
		// 입력 스트림(Input Stream)의 출발지는 키보드, 마우스, 모니터, 또는 프로그램의 파일이며, 이는 출력 스트림(Output Stream)의 도착지이기도 한다.
		// 프로그램에서 데이터를 요청하면 다른 프로그램에서 데이터를 받아와서 다시 요청 프로그램에 데이터를 전달해야 되므로
		// 데이터의 입출력이 필요할 때는 Input Stream, Output Stream이 꼭 있어야 한다.
		
		// InputStream is = TPCProject04.class.getClass().getResourceAsStream(src);
		
		// getResourceAsStream(FileName) 메서드의 역할
		// 지금 내 생각에는 FileName을 호출하고자 하는 class에 올려주는 역할을 하는 메서드일 것이라 추측해본다.
		InputStream is = TPCProject04.class.getResourceAsStream(src);
		System.out.println("is => " + is);
		if(is == null) {
			// 예외를 메서드 밖으로 던지기 위해서 사용하는 throw
			// 여기서는 불러오고자 하는 파일이 없을 시 NullPointerException 에러를 내고,
			// 프로그램의 실행을 멈추게 한다.
			throw new NullPointerException("Cannot Find Resource File.");
		}
		else {
			// 파일 부르기를 성공했다면
			// 해당 파일을 자바에서 처리할 수 있게
			// JSONTokener로 파일에 있는 json을 읽도록 한다.
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
