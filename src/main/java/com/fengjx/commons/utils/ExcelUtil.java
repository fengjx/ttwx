package com.fengjx.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * excel文件操作类
 */
public class ExcelUtil {
	
	public static List<List<String>> excelList(InputStream input)
			throws IOException {
		List<List<String>> list = new ArrayList<List<String>>();
		POIFSFileSystem fs = new POIFSFileSystem(input);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		for (Row row : sheet) {
			if (isBlankRow(row)) {
				continue;
			}
			List<String> stringList = new ArrayList<String>();
			for (Cell cell : row) {
				String str = "";
				switch (cell.getCellType()) {
				case HSSFCell.CELL_TYPE_NUMERIC:
					str = String.valueOf((long) cell.getNumericCellValue());
					break;

				case HSSFCell.CELL_TYPE_STRING:
					str = cell.getStringCellValue();
					break;

				case HSSFCell.CELL_TYPE_BOOLEAN:
					str = cell.getBooleanCellValue() + "";
					break;

				case HSSFCell.CELL_TYPE_FORMULA:
					str = cell.getCellFormula();
					break;
				default:
					break;
				}
				Integer n = cell.getColumnIndex() - stringList.size();
				for (int i = 0; i < n; i++) {
					stringList.add("");// some cell is empty
				}
				stringList.add(str);
			}
			list.add(stringList);
		}
		return list;
	}
	
	public static boolean isBlankRow(Row row) {
		if (row == null) {
			return true;
		}

		for (Cell cell : row) {
			if (cell != null
					&& cell.getCellType() != Cell.CELL_TYPE_BLANK
					&& !(cell.getCellType() == Cell.CELL_TYPE_STRING && StringUtils.isEmpty(cell.getStringCellValue()))) {
				// 有一个单元格非空
				return false;
			}
		}
		return true;
	}
	
}
