package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;   
	String path;

	public ExcelUtility(String path) {
	    this.path = path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount;
		
	}
	
	public int getCellCount(String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellCount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;
	}
	
	
	// Read Data From Cell
	public String getCellData(String sheetName,int rowNum,int colNum) throws IOException {
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		
		// DataFormatter object banata hai jo cell value ko human-readable string banane me help karta hai.
		DataFormatter formatter = new DataFormatter();
		
		String data;
		try {
			data = formatter.formatCellValue(cell);
		} catch(Exception e) {
			data = "";
		}
		
		workbook.close();
		fi.close();
		return data;
	}
	
	
	// Write Data From Cell
	
	public void setCellData(String sheetName, int rowNum,int colNum, String data) throws IOException {
		File xlfile = new File(path);
		
		if(!xlfile.exists()) {
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
		}
		
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		
		if(workbook.getSheetIndex(sheetName) == -1){
			workbook.createSheet(sheetName);
		}
		
		sheet = workbook.getSheet(sheetName);
		
		if(sheet.getRow(rowNum)==null) {
			sheet.createRow(rowNum);
		}
		row=sheet.getRow(rowNum);
		
		cell = row.createCell(colNum);
		cell.setCellValue(data);
		
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
	    fi = new FileInputStream(path);
	    workbook = new XSSFWorkbook(fi);
	    sheet = workbook.getSheet(sheetName);

	    row = sheet.getRow(rownum);
	    cell = row.getCell(colnum);

	    style = workbook.createCellStyle();
	    style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    cell.setCellStyle(style);
	    workbook.write(fo);
	    workbook.close();
	    fi.close();
	    fo.close();

	}

	public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
	    fi = new FileInputStream(path);
	    workbook = new XSSFWorkbook(fi);
	    sheet = workbook.getSheet(sheetName);
	    row = sheet.getRow(rownum);
	    cell = row.getCell(colnum);

	    style = workbook.createCellStyle();
	    style.setFillForegroundColor(IndexedColors.RED.getIndex());
	    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    cell.setCellStyle(style);
	    workbook.write(fo);
	    workbook.close();
	    fi.close();
	    fo.close();
	}


}
