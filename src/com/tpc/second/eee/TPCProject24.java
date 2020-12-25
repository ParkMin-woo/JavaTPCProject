package com.tpc.second.eee;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class TPCProject24 {
	public static void main(String[] args) {
		// iText API를 이용한 PDF에 이미지 삽입하는 실습 진행
		String pdfFilePath = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/file/paragraph_image_01.pdf";
		// String pdfFontPath = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/font/MALGUN.TTF";
		
		// PDF파일을 Memory에 임시로 저장하기 위한 Document 객체 생성
		Document document = new Document(PageSize.A4);
		try {
			PdfWriter.getInstance(document, new FileOutputStream(new File(pdfFilePath)));
			document.open();
			
			// 파일에서 이미지 생성
			String imageFilePath = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/file/jenny.jpg";
			Image image01 = Image.getInstance(imageFilePath);
			document.add(image01);
			
			// URL에서 이미지 생성
			String imageFileUrl = "https://search.pstatic.net/common?type=o&size=150x112&quality=100&direct=true&src=http%3A%2F%2Fdbscthumb.phinf.naver.net%2F3924_000_1%2F20171219172005652_Z1TCBCARR.png%2F%25ED%2581%25AC%25EB%25A6%25AC.png%3Ftype%3Df150_112_fst";
			Image image02 = Image.getInstance(imageFileUrl);
			document.add(image02);
			document.addTitle("PDF Image Demo");
			System.out.println("Image 생성!!!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
	}
}
