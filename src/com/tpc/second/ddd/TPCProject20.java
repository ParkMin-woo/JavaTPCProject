package com.tpc.second.ddd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import kr.bit.dao.ExcelDAO;
import kr.bit.structure.ExcelVO;

public class TPCProject20 {
	public static void main(String[] args) {
		ExcelDAO dao = new ExcelDAO();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.print("입력처리(I)/종료(E) : ");
			String ie = br.readLine();
			switch(ie) {
				case "I" :
					dao.excelInput();
					break;
				
				case "E" :
					System.out.println("프로그랭 종료");
					System.exit(0);
					break;
				default :
					System.out.println("I or E input!!!");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		/*
		try {
			System.out.print("책제목 : ");
			String title = inputDataReader.readLine();
			System.out.print("작가 : ");
			String author = inputDataReader.readLine();
			System.out.print("출판사 : ");
			String company = inputDataReader.readLine();
			ExcelVO vo = new ExcelVO(title, author, company);
			
			// Isbn과 이미지 URL을 얻어오기 위한 메서드 선언.
			getIsbnAndImageUrl(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getIsbnAndImageUrl(ExcelVO vo) {
		String URL_SEARCH_BOOK = "https://openapi.naver.com/v1/search/book_adv.xml?";
		String clientId = "XS8MwJv59E13b3S_lEMX";
		String clientSecret = "kA42WlfPLl";
		System.out.println("vo1 => " + vo);
		try {
			URL_SEARCH_BOOK = URL_SEARCH_BOOK
			+ "d_titl=" + URLEncoder.encode(vo.getTitle(), "UTF-8")
			+ "&d_auth=" + URLEncoder.encode(vo.getAuthor(), "UTF-8")
			+ "&d_publ=" + URLEncoder.encode(vo.getCompany(), "UTF-8");
			URL url = new URL(URL_SEARCH_BOOK);
			// openConnection() -> 연결해주는 메서드
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			// 네이버 클라우드 플랫폼의 Geocoding application이 API URL을 GET방식으로 지원
			connection.setRequestMethod("GET");
			// 요청 헤더로 클라이언트id, secret key를 보내준다.
			connection.setRequestProperty("X-Naver-Client-Id", clientId);
			connection.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			// }
			int responseCode = connection.getResponseCode();
			BufferedReader responseXmlReader;	// = new BufferedReader(in)
			if(responseCode == 200) {
				// 성공이면 responseJsonReader 변수에 연결되어있는 connection에 String형 데이터를 얻어와야 한다.
				// 이떼, 문자 stream과 byte stream을 연결해주는 InputStreamReader()를 이용해서 stream을 변경.
				// 변경할 때, 한글 데이터가 있다면 한글이 깨지지 않기 위해서 "UTF-8"로 Character Set을 설정한다.
				responseXmlReader = new BufferedReader(new InputStreamReader(connection.getInputStream() , "UTF-8"));
			}
			else {
				// 에러가 나면 error stream으로 받는다.
				System.out.println("error 발생!");
				responseXmlReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
			}
			String readLine;
			StringBuffer responseXml = new StringBuffer();
			while((readLine = responseXmlReader.readLine()) != null) {
				responseXml.append(readLine);
			}
			responseXmlReader.close();
			
			Document document = Jsoup.parse(responseXml.toString());
			System.out.println(document.toString());
			Element totalElement = document.select("total").first();
			if(!(totalElement.text().equals("0"))) {
				Element isbnElement = document.select("isbn").first();
				String isbn = isbnElement.text();
				isbn = isbn.split(" ")[1];
				String imageTag = document.toString().substring(document.toString().indexOf("<img>") + 5);
				String img = imageTag.substring(0, imageTag.indexOf("?"));
				vo.setIsbn(isbn);
				vo.setImageUrl(img);
				System.out.println("vo2 => " + vo);
			}	// if
			else {
				System.out.println("검색데이터가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
}