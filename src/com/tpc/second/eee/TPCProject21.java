package com.tpc.second.eee;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class TPCProject21 {
	public static void main(String[] args) {
		// iText API 가져와서 PDF 파일에 Table을 만들기
		String[] header = new String[] {
			"제목"
			, "저자"
			, "출판사"
			, "이미지URL"
		};
		String[][] rows = new String[][] {
			{"클라우스 슈밥의 제4차 산업혁명", "클라우스 슈밥", "새로운현재", "https://bookthumb-phinf.pstatic.net/cover/104/536/10453686.jpg"}
			, {"사람은 어떻게 생각하고 배우고 기억하는가", "제레드 쿠니 호바스", "토네이도", "https://bookthumb-phinf.pstatic.net/cover/162/830/16283016.jpg"}
			, {"생각하지 않는 사람들", "니콜라스 카", "청림출판", "https://bookthumb-phinf.pstatic.net/cover/167/105/16710543.jpg"}
			, {"어록으로 본 이낙연", "이제이", "삼인", "https://bookthumb-phinf.pstatic.net/cover/163/011/16301149.jpg"}
		};
		
		String pdfFilePath = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/file/bookList01.pdf";
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
			float[] columnWidths = new float[] {20f, 15f, 15f, 30f};
			table.setWidths(columnWidths);
			
			for(String headerElement : header) {
				// Header Cell 만들기
				PdfPCell cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setPadding(10);
				cell.setGrayFill(0.9f);
				cell.setPhrase(new Phrase(headerElement, fontTitle));
				table.addCell(cell);
			}
			table.completeRow();
			
			for(String[] rowsElement1 : rows) {
				for(String rowsElement2 : rowsElement1) {
					// Row Cell 만들기
					PdfPCell cell = new PdfPCell();
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setPadding(10);
					// cell.setGrayFill(0.9f);
					cell.setPhrase(new Phrase(rowsElement2, fontContents));
					table.addCell(cell);
				}
				table.completeRow();
			}
			
			/*
			 * setColspan() 메서드 이용
			PdfPCell cell1 = new PdfPCell(new Phrase("imsi1", fontContents));
			cell1.setColspan(2);
			table.addCell(cell1);
			// table.completeRow();
			PdfPCell cell2 = new PdfPCell(new Phrase("imsi2", fontContents));
			cell2.setColspan(2);
			table.addCell(cell2);
			table.completeRow();
			*/
			
			// table.completeRow();
			document.addTitle("PDF Table Demo");
			document.add(table);
			System.out.println("테이블 생성!!!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
	}
}
