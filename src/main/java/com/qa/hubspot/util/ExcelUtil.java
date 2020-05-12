package com.qa.hubspot.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	public static Workbook book;
	public static Sheet sheet;
	
	
	public static String TESTDATA_SHEET_PATH="D:\\Users\\asingh6766\\eclipse-workspace\\SeleniumPOMFramework\\src\\main\\java\\com\\qa\\hubspot\\testdata\\HubSpotTestData.xlsx";

	public static Object[][] getTestData(String sheetName)
	{
		try {
			FileInputStream ip = new FileInputStream(TESTDATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			System.out.println("Row is: "+sheet.getLastRowNum());
			System.out.println("Column is: "+sheet.getRow(0).getLastCellNum());

			Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i < sheet.getLastRowNum(); i++) {

				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {

					System.out.println("Value of I: "+i);
					System.out.println("value of K: "+k);
					
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
					System.out.println(data[i][k]);
				}
			}

			return data;

		} catch (FileNotFoundException e) {
			System.out.println("File not foundException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();
		}

		return null;

	}
	
	  public static void main(String args[]) {
		  Object data[][]=	  getTestData("contacts"); System.out.println(" ");
		  
		  for(int i=0 ; i<data.length; i++)
		  { 
			  for(int j=0; j<data.length ; j++) 
			  { System.out.println(data[i][j]);
	  } } }
	 
	
	
}
