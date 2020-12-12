package com.tpc.second.ddd;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import kr.bit.structure.ExcelVO;

public class TPCProject16 {
	public static void main(String[] args) {
		// String filePath = "/kr/bit/file/bookList.xlsx";
		// String filePath = "bookList.xlsx";
		String filePath = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/file/bookList.xlsx";
		
		List<ExcelVO> bookList = new ArrayList<ExcelVO>();
		System.out.println("try 진입 전 1111111111");
		// try (FileInputStream fis = new FileInputStream(filePath)) {
		// try (POIFSFileSystem fis = new POIFSFileSystem(new FileInputStream(filePath))) {
		// try(OPCPackage fis = OPCPackage.open(new File(filePath));) {
		try (FileInputStream fis = new FileInputStream(new File(filePath))) {
			System.out.println("try 진입 1111111111");
			// HSSFWorkbook workbook = new HSSFWorkbook(fis);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			System.out.println("try 진입 222222222");
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
		
		showExcelData(bookList);
	}
	
	private static void showExcelData(List<ExcelVO> dataList) {
		for(ExcelVO dataElement : dataList) {
			System.out.println(dataElement);
		}
	}
}
