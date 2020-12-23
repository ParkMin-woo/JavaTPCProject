package kr.bit.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import kr.bit.api.DownloadBroker;
import kr.bit.structure.ExcelVO;

public class ExcelDAO {
	private List<ExcelVO> excelList;
	private XSSFWorkbook wb;
	
	/**
	 * 
	 */
	public ExcelDAO() {
		excelList = new ArrayList<ExcelVO>();
		wb = new XSSFWorkbook();
	}

	public void excelInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			XSSFSheet firstSheet = wb.createSheet("BOOK SHEET");
			XSSFRow rowA = firstSheet.createRow(0);
			XSSFCell cellA = rowA.createCell(0);
			cellA.setCellValue(new XSSFRichTextString("책제목"));
			XSSFCell cellB = rowA.createCell(1);
			cellB.setCellValue(new XSSFRichTextString("저자"));
			XSSFCell cellC = rowA.createCell(2);
			cellC.setCellValue(new XSSFRichTextString("출판사"));
			XSSFCell cellD = rowA.createCell(3);
			cellD.setCellValue(new XSSFRichTextString("ISBN"));
			XSSFCell cellE = rowA.createCell(4);
			cellE.setCellValue(new XSSFRichTextString("이미지이름"));
			XSSFCell cellF = rowA.createCell(5);
			cellF.setCellValue(new XSSFRichTextString("이미지"));
			
			int i = 1;
			while(true) {
				System.out.print("책제목 : ");
				String title = br.readLine();
				System.out.print("작가 : ");
				String author = br.readLine();
				System.out.print("출판사 : ");
				String company = br.readLine();
				XSSFRow rowRal = firstSheet.createRow(i);
				XSSFCell cellTitle = rowRal.createCell(0);
				cellTitle.setCellValue(new XSSFRichTextString(title));
				XSSFCell cellAuthor = rowRal.createCell(1);
				cellAuthor.setCellValue(new XSSFRichTextString(author));
				XSSFCell cellCompany = rowRal.createCell(2);
				cellCompany.setCellValue(new XSSFRichTextString(company));
				i++;
				ExcelVO vo = new ExcelVO(title, author, company);
				
				// ISBN, ImageUrl 검색
				ExcelVO excelData = naverBookSearch(vo);
				excelList.add(excelData);
				System.out.println("계속 입력 하시면 \"Y\", 입력종료를 하시려면 \"N\"을 입력해 주세요.");
				String key = br.readLine();
				if(key.equals("N")) break;
			}
			System.out.println("데이터 추출중입니다.................");
			excelSave();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void excelSave() {
		String excelFilePath = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/file/myExcelPractice.xlsx";
		try {
			XSSFSheet sheet = wb.getSheetAt(0);
			if(wb != null && sheet != null) {
				Iterator rows = sheet.rowIterator();
				rows.next();
				int i = 0;	// list의 index
				while(rows.hasNext()) {
					XSSFRow row = (XSSFRow) rows.next();
					XSSFCell cell = row.createCell(3);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(excelList.get(i).getIsbn());
					
					cell = row.createCell(4);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(excelList.get(i).getImageUrl());
					
					InputStream inputStream = new FileInputStream(excelList.get(i).getImageUrl());
					byte[] bytes = IOUtils.toByteArray(inputStream);
					int pictureIndex = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
					inputStream.close();
					
					CreationHelper creationHelper = wb.getCreationHelper();
					Drawing drawing = sheet.createDrawingPatriarch();
					
					ClientAnchor clientAnchor = creationHelper.createClientAnchor();
					
					clientAnchor.setCol1(5);	// Column B
					clientAnchor.setRow1(i+1);	// Row3
					clientAnchor.setCol2(6);	// Column C
					clientAnchor.setRow2(i+2);	// Row4
					
					Picture picture = drawing.createPicture(clientAnchor, pictureIndex);
					
					Cell cellImg = row.createCell(5);
					// set width to n character widths = count characters * 256
					int widthUnits = 20*256;
					sheet.setColumnWidth(5, widthUnits);
					// set height to n points in twips = n*20
					short heightUnits = 120*20;
					cellImg.getRow().setHeight(heightUnits);
					i++;
				}	// while
			
				FileOutputStream fos = new FileOutputStream(excelFilePath);
				wb.write(fos);
				fos.close();
				System.out.println("ISBN, ImageURL 저장성공!!!");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ExcelVO naverBookSearch(ExcelVO vo) {
		String URL_SEARCH_BOOK = "https://openapi.naver.com/v1/search/book_adv.xml?";
		String clientId = "clientId";
		String clientSecret = "clientSecret";
		// System.out.println("vo1 => " + vo);
		
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
			StringBuffer responseXml = new StringBuffer();	// 문자열 추가 변경 시 사용
			while((readLine = responseXmlReader.readLine()) != null) {
				responseXml.append(readLine);
			}
			responseXmlReader.close();
			
			Document document = Jsoup.parse(responseXml.toString());
			// System.out.println(document.toString());
			Element totalElement = document.select("total").first();
			if(!(totalElement.text().equals("0"))) {
				Element isbnElement = document.select("isbn").first();
				String isbn = isbnElement.text();
				isbn = isbn.split(" ")[1];
				String imageTag = document.toString().substring(document.toString().indexOf("<img>") + 5);
				String img = imageTag.substring(0, imageTag.indexOf("?"));
				String imgFileName = img.substring(img.lastIndexOf("/") + 1);
				vo.setIsbn(isbn);
				vo.setImageUrl(imgFileName);
				System.out.println(vo);
				
				// DownloadBroker
				Runnable downloadBroker = new DownloadBroker(img, imgFileName);
				Thread thread = new Thread(downloadBroker);
				thread.start();
			}	// if
			else {
				System.out.println("검색데이터가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
}
