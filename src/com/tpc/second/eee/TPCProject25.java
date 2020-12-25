package com.tpc.second.eee;

import java.io.File;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class TPCProject25 {
	public static void main(String[] args) {
		// iText API를 이용한 image 크기 조절 기능을 이용하여 PDF파일에 삽입하기.
		String pdfFilePath = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/file/paragraph_image_02.pdf";
		
		// PDF파일을 Memory에 임시로 저장하기 위한 Document 객체 생성
		Document document = new Document(PageSize.A4);
		try {
			PdfWriter.getInstance(document, new FileOutputStream(new File(pdfFilePath)));
			document.open();
			
			// 파일에서 이미지 생성
			String imageFilePath01 = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/file/google_christmas_logo.gif";
			Image image01 = Image.getInstance(imageFilePath01);
			document.add(image01);
			
			String imageFilePath02 = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/file/google_christmas_logo.gif";
			Image image02 = Image.getInstance(imageFilePath02);
			image02.scaleAbsolute(200f, 200f);
			document.add(image02);
			
			// URL에서 이미지 생성
			String imageFileUrl = "https://www.google.com/logos/doodles/2020/december-holidays-days-2-30-6753651837108830.3-law.gif";
			Image image03 = Image.getInstance(imageFileUrl);
			image03.scalePercent(200f);
			document.add(image03);
			
			image03 = Image.getInstance(imageFileUrl);
			image03.scaleToFit(100f, 200f);
			document.add(image03);
			
			document.addTitle("PDF Image Demo");
			System.out.println("Image 생성!!!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			document.close();
		}
	}
}
