package com.tpc.second.ddd;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TPCProject17 {
	public static void main(String[] args) {
		String pictureFilePath = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/file/jenny.jpg";
		String excelFilePath = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/file/myFile.xlsx";
		try {
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("My Simple Excel");
			InputStream is = new FileInputStream(pictureFilePath);
			byte[] bytes = IOUtils.toByteArray(is);
			int pictureIndex = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
			is.close();
			
			CreationHelper creationHelper = workbook.getCreationHelper();
			Drawing drawing = sheet.createDrawingPatriarch();
			
			ClientAnchor clientAnchor = creationHelper.createClientAnchor();
			
			clientAnchor.setCol1(1);	// Column B
			clientAnchor.setRow1(2);	// Row3
			clientAnchor.setCol2(2);	// Column C
			clientAnchor.setRow2(3);	// Row4
			
			Picture picture = drawing.createPicture(clientAnchor, pictureIndex);
			
			Cell cell = sheet.createRow(2).createCell(1);
			// set width to n character widths = count characters * 256
			int widthUnits = 20*256;
			sheet.setColumnWidth(1, widthUnits);
			// set height to n points in twips = n*20
			short heightUnits = 120*20;
			cell.getRow().setHeight(heightUnits);
			
			FileOutputStream fos = null;
			fos = new FileOutputStream(excelFilePath);
			workbook.write(fos);
			fos.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
