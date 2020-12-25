package com.tpc.second.eee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import kr.bit.structure.ExcelVO;

public class TPCProject26 {
	public static void main(String[] args) {
		// Excel의 data를 읽어서 다시 PDF File로 만드는 예제 실습.
		String filePath = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/file/myExcelPractice.xlsx";
		
		List<ExcelVO> bookList = new ArrayList<ExcelVO>();
		try (FileInputStream fis = new FileInputStream(new File(filePath))) {
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
					if(i==5) break;
				}	// while
				ExcelVO vo = new ExcelVO(excelColumn[0] , excelColumn[1] , excelColumn[2] , excelColumn[3] , excelColumn[4]);
				bookList.add(vo);
			}	// while
			// System.out.println(bookList);
			createPdf(bookList);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// showExcelData(bookList);
	}

	private static void createPdf(List<ExcelVO> bookList) {
		// iText API 가져와서 PDF 파일에 Table을 만들기
		String[] header = new String[] {
			"제목"
			, "저자"
			, "출판사"
			, "이미지"
		};
		
		String pdfFilePath = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/file/bookList02.pdf";
		String pdfFontPath = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/font/MALGUN.TTF";
		
		// PDF파일을 Memory에 임시로 저장하기 위한 Document 객체 생성
		Document document = new Document(PageSize.A4);
		try {
			PdfWriter.getInstance(document, new FileOutputStream(new File(pdfFilePath)));
			document.open();
			
			BaseFont bf = BaseFont.createFont(pdfFontPath , BaseFont.IDENTITY_H , BaseFont.NOT_EMBEDDED);
			Font fontTitle = new Font(bf, 12);
			Font fontContents = new Font(bf, 10);
			
			// TABLE 만들기
			PdfPTable table = new PdfPTable(header.length);
			table.setWidthPercentage(100);	// Table의 폭%
			
			// TABLE Coulmn Width 조절
			// float[] columnWidths = new float[] {20f, 15f, 15f, 30f};
			// table.setWidths(columnWidths);
			
			for(String headerElement : header) {
				// Header Cell 만들기
				PdfPCell cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setPadding(10);
				cell.setGrayFill(0.9f);
				cell.setPhrase(new Phrase(headerElement.toUpperCase(), fontTitle));
				table.addCell(cell);
			}
			table.completeRow();
			
			for(ExcelVO bookElement : bookList) {
				Phrase phrase = new Phrase(bookElement.getTitle() , fontContents);
				table.addCell(new PdfPCell(phrase));
				
				phrase = new Phrase(bookElement.getAuthor(), fontContents);
				table.addCell(new PdfPCell(phrase));
				
				phrase = new Phrase(bookElement.getCompany(), fontContents);
				table.addCell(new PdfPCell(phrase));
				
				Image image = Image.getInstance(bookElement.getImageUrl());
				table.addCell(image);
				table.completeRow();
			}
			
			// table.completeRow();
			document.addTitle("PDFBookList");
			document.add(table);
			System.out.println("테이블 생성!!!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
	}
}
