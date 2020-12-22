package com.tpc.second.ddd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import kr.bit.structure.ExcelVO;

public class TPCProject19 {
	public static void main(String[] args) {
		// String filePath = "/kr/bit/file/bookList.xlsx";
		// String filePath = "bookList.xlsx";
		String filePath = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/file/bookList.xlsx";
		
		List<ExcelVO> bookList = new ArrayList<ExcelVO>();
		// try (FileInputStream fis = new FileInputStream(filePath)) {
		// try (POIFSFileSystem fis = new POIFSFileSystem(new FileInputStream(filePath))) {
		// try(OPCPackage fis = OPCPackage.open(new File(filePath));) {
		try (FileInputStream fis = new FileInputStream(new File(filePath))) {
			// HSSFWorkbook workbook = new HSSFWorkbook(fis);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			// opcPackage.close();
			// HSSFSheet sheet = workbook.getSheetAt(0);
			XSSFSheet sheet = workbook.getSheetAt(0);
			// Iterator rows = sheet.rowIterator();
			Iterator<Row> rows = sheet.rowIterator();
			String[] excelColumn = new String[5];
			rows.next();
			while(rows.hasNext()) {
				// HSSFRow row = (HSSFRow) rows.next();
				XSSFRow row = (XSSFRow) rows.next();
				// Iterator cells = row.cellIterator();
				Iterator<Cell> cells = row.cellIterator();
				int i = 0;
				while(cells.hasNext()) {
					// HSSFCell cell = (HSSFCell) cells.next();
					XSSFCell cell = (XSSFCell) cells.next();
					excelColumn[i] = cell.toString();
					i++;
				}	// while
				ExcelVO vo = new ExcelVO(excelColumn[0] , excelColumn[1] , excelColumn[2] , excelColumn[3] , excelColumn[4]);
				bookList.add(vo);
			}	// while
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// showExcelData(bookList);
		showIsbnAndImage(bookList);
	}
	
	private static void showIsbnAndImage(List<ExcelVO> dataList) {
		String URL_SEARCH_BOOK = "https://openapi.naver.com/v1/search/book_adv.xml?";
		String clientId = "clientId";
		String clientSecret = "clientSecret";
		try {
			for(ExcelVO dataElement : dataList) {
				System.out.println(dataElement);
				// System.out.println("dataElement.getTitle() => " + dataElement.getTitle());
				// System.out.println("dataElement.getAuthor() => " + dataElement.getAuthor());
				// System.out.println("dataElement.getCompany() => " + dataElement.getCompany());
				URL_SEARCH_BOOK = URL_SEARCH_BOOK
				+ "d_titl="+ URLEncoder.encode(dataElement.getTitle(), "UTF-8")
				+ "&d_auth=" + URLEncoder.encode(dataElement.getAuthor(), "UTF-8")
				+ "&d_publ=" + URLEncoder.encode(dataElement.getCompany(), "UTF-8");
				// System.out.println("URL_SEARCH_BOOK => " + URL_SEARCH_BOOK);
				// try {
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
				// System.out.println("responseCode => " + responseCode);
				// System.out.println(connection.getResponseMessage());
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
				// System.out.println(responseXmlReader.toString());
				// Document doc = Jsoup.parse(responseXmlReader.toString());
				// System.out.println(doc.toString());
				// System.out.println("responseXmlReader => " + responseXmlReader);
				// Document doc = Jsoup.parse(responseXmlReader.toString());
				// System.out.println("doc.toString() => " + doc.toString());
				String readLine;
				StringBuffer responseXml = new StringBuffer();
				while((readLine = responseXmlReader.readLine()) != null) {
					responseXml.append(readLine);
				}
				
				responseXmlReader.close();
				System.out.println(responseXml.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
