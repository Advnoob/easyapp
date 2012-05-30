package com.customer.fawvw.issues.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelUtil {

	public static void fillTheCellColor(HSSFWorkbook wb, HSSFCell cell, 
			String color, String borderFlag){
		short c = -1;
		
		if ("��".equals(color)){ //$NON-NLS-1$
			c = HSSFColor.RED.index;
		}
		if ("��".equals(color)){ //$NON-NLS-1$
			c = HSSFColor.YELLOW.index;
		}
		if ("��".equals(color)){ //$NON-NLS-1$
			c = HSSFColor.GREEN.index;
		}
		if (c > 0) {
			HSSFCellStyle style = wb.createCellStyle();
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			if ("yes".equals(borderFlag)) { //$NON-NLS-1$
				style.setBorderBottom((short)1);
				style.setBorderLeft((short)1);
				style.setBorderRight((short)1);
				style.setBorderTop((short)1);
			}
			style.setFillForegroundColor(c);
			cell.setCellStyle(style);  //cell �� HSSFCell ����
		}
	}
	
//	��Ԫ����д��������ֵ
	public static void writeIntValueToCell(HSSFRow row, int col, int value) {
		HSSFCell cell = row.getCell(col);
		if (cell == null) {
			cell = row.createCell(col);
		}
		cell.setCellValue(value); 
	}
	
//	��Ԫ����д�빫ʽ
	public static void writeFormulaToCell(HSSFRow row, int col, String formula) {
		HSSFCell cell = row.getCell(col);
		if (cell == null) {
			cell = row.createCell(col);
		}
		cell.setCellFormula(formula); 
	}
}
