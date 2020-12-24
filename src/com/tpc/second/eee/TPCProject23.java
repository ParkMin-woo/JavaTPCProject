package com.tpc.second.eee;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class TPCProject23 {
	public static void main(String[] args) {
		// iText API 가져와서 PDF 파일에 Paragraph 만들기
		String pdfFilePath = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/file/paragraph_demo_02.pdf";
		// String pdfFontPath = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/font/MALGUN.TTF";
		
		// PDF파일을 Memory에 임시로 저장하기 위한 Document 객체 생성
		Document document = new Document(PageSize.A4);
		try {
			PdfWriter.getInstance(document, new FileOutputStream(new File(pdfFilePath)));
			document.open();
			
			/*
			BaseFont bf = BaseFont.createFont(pdfFontPath , BaseFont.IDENTITY_H , BaseFont.NOT_EMBEDDED);
			Font fontTitle = new Font(bf, 12);
			Font fontContents = new Font(bf, 10);
			*/
			
			// Paragraph 만들기
			Paragraph paragraph1 = new Paragraph(32);
			String content = "Don’t judge a book by its cover.";
			paragraph1.setSpacingBefore(50);
			paragraph1.setSpacingAfter(50);
			
			for(int i = 0 ; i < 20 ; i++) {
				Chunk chunk = new Chunk(content);
				paragraph1.add(chunk);
			}
			document.add(paragraph1);
			
			Paragraph paragraph2 = new Paragraph();
			for(int i = 0 ; i < 20 ; i++) {
				Chunk chunk = new Chunk(content);
				paragraph2.add(chunk);
			}
			document.add(paragraph2);
			// paragraph1.addAll(chunk, fontContents);
			document.addTitle("PDF Paragraph Demo");
			System.out.println("Paragraph 생성!!!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
	}
}
