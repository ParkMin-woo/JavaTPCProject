package com.tpc.second.ddd;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import kr.bit.structure.ExcelVO;

public class TPCProject18 {
	public static void main(String[] args) {
		String filePath = "C:/JavaTPC/newWorkspace/JavaTPCProject/src/kr/bit/file/cellDataTypeExample.xlsx";

		try (FileInputStream fis = new FileInputStream(new File(filePath))) {
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();
			// rows.next();
			while(rows.hasNext()) {
				XSSFRow row = (XSSFRow) rows.next();
				Iterator<Cell> cells = row.cellIterator();
				// int i = 0;
				while(cells.hasNext()) {
					XSSFCell cell = (XSSFCell) cells.next();
					CellType cellType = cell.getCellTypeEnum();
					if(cellType == CellType.STRING) {
						System.out.println("[" + cell.getRowIndex() + ", " + cell.getColumnIndex() + "] = STRING; Value = " + cell.getRichStringCellValue().toString());
					}
					else if (cellType == CellType.NUMERIC) {
						System.out.println("[" + cell.getRowIndex() + ", " + cell.getColumnIndex() + "] = NUMERIC; Value = " + cell.getNumericCellValue());
					}
					else if (cellType == CellType.BOOLEAN) {
						System.out.println("[" + cell.getRowIndex() + ", " + cell.getColumnIndex() + "] = BOOLEAN; Value = " + cell.getBooleanCellValue());
					}
					else if (cellType == CellType.BLANK) {
						System.out.println("[" + cell.getRowIndex() + ", " + cell.getColumnIndex() + "] = BLANK CELL");
					}
					// i++;
				}	// while
			}	// while
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
