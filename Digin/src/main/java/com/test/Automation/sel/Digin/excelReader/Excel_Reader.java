package com.test.Automation.sel.Digin.excelReader;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Reader {
	
	public FileInputStream fis;
	public String path;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
public Excel_Reader(String path){
	this.path = path;
	try {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public String[][] getDataFromExcel(String Excel, String sheetName){
	String dataSets[][]=null;
	try{
	sheet = workbook.getSheet(sheetName);
	int totalRows = sheet.getLastRowNum()+1;
	int totalColumn = sheet.getRow(0).getLastCellNum();
	dataSets = new String [totalRows-1][totalColumn];
	for(int i=1;i<totalRows;i++){
		row = sheet.getRow(i);
	for(int j=0;j<totalColumn;j++){
		cell = row.getCell(j);
	if(cell.getCellType()==cell.CELL_TYPE_STRING){
		dataSets[i-1][j]=cell.getStringCellValue();
	}else if(cell.getCellType()==cell.CELL_TYPE_NUMERIC){
		String cellText = String.valueOf(cell.getNumericCellValue());
		dataSets[i-1][j]=cellText;
	}else{
		dataSets[i-1][j]=String.valueOf(cell.getBooleanCellValue());
	}
	}
	}
	return dataSets;
}catch(Exception e){
	System.out.println("Exception in read excel" +e.getMessage());
	e.printStackTrace();
}
return dataSets;
}

}
