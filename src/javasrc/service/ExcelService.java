package javasrc.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javasrc.component.ExportedObject;

public class ExcelService {
	public static String webcontentpath=""; 
	/**
	 * 创建Excel文件。
	 * @param data Excel数据。
	 * @param title Excel表头。
	 * @return 文件路径。*/
	public static String createexcel(List<List<String>> data,String[] title){
		String filepath="temporary/";
		String filename=UUID.randomUUID().toString()+".xlsx";
		filepath=filepath+filename;
		filepath=webcontentpath+filepath;
		File excelfile=new File(filepath);
		FileOutputStream out=null;
		try {
			excelfile.createNewFile();
			out = new FileOutputStream(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SXSSFWorkbook wb=new SXSSFWorkbook(100);
		Sheet s = wb.createSheet();
		Row r = null;
		Cell c = null;
		r=s.createRow(0);
		for (int i = 0; i < title.length; i++) {
			c=r.createCell(i);
			c.setCellType(Cell.CELL_TYPE_STRING);
			c.setCellValue(title[i]);
		}
		for (int i = 0; i < data.size(); i++) {
			r=s.createRow(i+1);
			for (int j = 0; j < data.get(i).size(); j++) {
				c=r.createCell(j);
				c.setCellType(Cell.CELL_TYPE_STRING);
				c.setCellValue(data.get(i).get(j));
			}
		}
		try {
			wb.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "/temporary/"+filename;
	}
	
	
	public static String createexcel(ExportedObject exportedObject,String[] title){
		int Startrownum=0;
		List<List<String>> data=null;
		String filepath="temporary/";
		String filename=UUID.randomUUID().toString()+".xlsx";
		filepath=filepath+filename;
		filepath=webcontentpath+filepath;
		File excelfile=new File(filepath);
		FileOutputStream out=null;
		try {
			excelfile.createNewFile();
			out = new FileOutputStream(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SXSSFWorkbook wb=new SXSSFWorkbook(1000);
		Sheet s = wb.createSheet();
		Row r = null;
		Cell c = null;
		r=s.createRow(0);
		for (int i = 0; i < title.length; i++) {
			c=r.createCell(i);
			c.setCellType(Cell.CELL_TYPE_STRING);
			c.setCellValue(title[i]);
		}
		while (exportedObject.haveNext()) {
			Startrownum=exportedObject.getStartrownum();
			data=exportedObject.getData();
			for (int i = 0; i < data.size(); i++) {
				r=s.createRow(i+1+Startrownum);
				for (int j = 0; j < data.get(i).size(); j++) {
					c=r.createCell(j);
					c.setCellType(Cell.CELL_TYPE_STRING);
					c.setCellValue(data.get(i).get(j));
				}
			}
		}

		try {
			wb.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "/temporary/"+filename;
	}
	
	/**
	 * 创建Excel文件。
	 * @param data Excel数据。
	 * @return 文件路径。*/
	public static String createexcel(List<List<String>> data){
		String filepath="temporary/";
		filepath=filepath+UUID.randomUUID().toString()+".xlsx";
		FileOutputStream out=null;
		try {
			out = new FileOutputStream(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Workbook wb = new HSSFWorkbook();
		SXSSFWorkbook wb=new SXSSFWorkbook(100);
		Sheet s = wb.createSheet();
		Row r = null;
		Cell c = null;
		for (int i = 0; i < data.size(); i++) {
			r=s.createRow(i+1);
			for (int j = 0; j < data.get(i).size(); j++) {
				c=r.createCell(j);
				c.setCellType(Cell.CELL_TYPE_STRING);
				c.setCellValue(data.get(i).get(j));
			}
		}
		try {
			wb.write(out);
			out.close();
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filepath;
	}
	
	/**
	 * 获取Excel中的行。
	 * @param start 开始行，最小为0。
	 * @param size 行数。
	 * @param excelfile Excel文件。
	 * @return Excel中的行。*/
	public static List<Row> getrows(Integer start,Integer size,File excelfile){
		List<Row> rows=new ArrayList<Row>();
		Workbook wb=null;
		wb=createWorkbook(excelfile);
		Sheet sheet = wb.getSheetAt(0);
		if ((sheet.getLastRowNum()+2)<(start+size)) {
			size=sheet.getLastRowNum()-start+1;
		}
		for(int i=0;i<size;i++){
			rows.add(sheet.getRow(start));
			start++;
		}
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	
	/**
	 * 获取Excel中的单元格。
	 * @param start 开始行，最小为0。
	 * @param size 行数。
	 * @param excelfile Excel文件。
	 * @return Excel中的单元格。*/
	public static List<List<String>> getcells(Integer start,Integer size,File excelfile){
		List<Row> rows=getrows(start,size,excelfile);
		List<List<String>> cells=new ArrayList<List<String>>();
		for (int i = 0; i < rows.size(); i++) {
			Row row=rows.get(i);
			List<String> cellrow=new ArrayList<String>();
			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
				cellrow.add(row.getCell(j).toString());
			}
			cells.add(cellrow);
		}
		return cells;

	}
	
	/**
	 * 获取Excel中的所有行。
	 * @param excelfile Excel文件。
	 * @return Excel中的所有行。*/
	public static List<Row> getrows(File excelfile){
		List<Row> rows=new ArrayList<Row>();
		Workbook wb=null;
		wb=createWorkbook(excelfile);
		Sheet sheet = wb.getSheetAt(0);
		for(int i=0;i<=sheet.getLastRowNum();i++){
			rows.add(sheet.getRow(i));
		}
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	/**
	 * 获取Excel中的所有单元格。
	 * @param excelfile Excel文件。
	 * @return Excel中的所有单元格。*/
	public static List<List<String>> getcells(File excelfile){
		List<Row> rows=getrows(excelfile);
		List<List<String>> cells=new ArrayList<List<String>>();
		for (int i = 0; i < rows.size(); i++) {
			Row row=rows.get(i);
			List<String> cellrow=new ArrayList<String>();
			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
				Cell cell=row.getCell(j);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cellrow.add(cell.getStringCellValue());
			}
			cells.add(cellrow);
		}
		return cells;

	}
	
	/**
	 * 获取Excel中的所有行。
	 * @param inputStream Excel文件。
	 * @return Excel中的所有行。*/
	public static List<Row> getrows(InputStream inputStream){
		List<Row> rows=new ArrayList<Row>();
		Workbook wb=null;
		wb=createWorkbook(inputStream);
		Sheet sheet = wb.getSheetAt(0);
		for(int i=0;i<=sheet.getLastRowNum();i++){
			rows.add(sheet.getRow(i));
		}
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	/**
	 * 获取Excel中的所有单元格。
	 * @param inputStream Excel文件。
	 * @return Excel中的所有单元格。*/
	public static List<List<String>> getcells(InputStream inputStream){
		List<Row> rows=getrows(inputStream);
		List<List<String>> cells=new ArrayList<List<String>>();
		for (int i = 0; i < rows.size(); i++) {
			Row row=rows.get(i);
			List<String> cellrow=new ArrayList<String>();
			for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
				Cell cell=row.getCell(j);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cellrow.add(cell.getStringCellValue());
			}
			cells.add(cellrow);
		}
		return cells;

	}
	
	/**
	 * 创建Excel工作簿。
	 * @param inputStream Excel文件。
	 * @return Excel工作簿。*/
	private static Workbook createWorkbook(InputStream inputStream){
		Workbook wb=null;
		try {
			wb = WorkbookFactory.create(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} 
		return wb;
	}
	
	/**
	 * 创建Excel工作簿。
	 * @param excelfile Excel文件。
	 * @return Excel工作簿。*/
	private static Workbook createWorkbook(File excelfile){
		Workbook wb=null;
		try {
			wb = WorkbookFactory.create(excelfile);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} 
		return wb;
	}
}
