package com.customer.fawvw.issues.commands.issueStatus;

import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class StatusWrite {

	static void importDataPage(HSSFWorkbook workbook,
			HSSFSheet sheetPage, HashMap<String, Object> values, int forecastDay) {
		
		HashMap<String, Object> statusMap = (HashMap<String, Object>)values.get("status"); //$NON-NLS-1$

		int red_sum = (Integer)statusMap.get("red"); //$NON-NLS-1$
		int yellow_sum = (Integer)statusMap.get("yellow"); //$NON-NLS-1$
		int green_sum = (Integer)statusMap.get("green"); //$NON-NLS-1$
		int sum = red_sum + yellow_sum + green_sum;
System.out.println("status = " + statusMap);	 //$NON-NLS-1$
System.out.println("today     = " + (String)values.get("week")); //$NON-NLS-1$ //$NON-NLS-2$
		HSSFRow row2 = sheetPage.getRow(2);//��ĿID
		HSSFRow row3 = sheetPage.getRow(3);//����
		HSSFRow row4 = sheetPage.getRow(4);//��
		HSSFRow row5 = sheetPage.getRow(5);//��
		HSSFRow row6 = sheetPage.getRow(6);//��
		HSSFRow row7 = sheetPage.getRow(7);//����
		HSSFRow row10 = sheetPage.getRow(10);//��������
		HSSFRow row15 = sheetPage.getRow(15);//�������
		HSSFRow row20 = sheetPage.getRow(20);//�Ƶ�����
		HSSFRow row25 = sheetPage.getRow(25);//�̵�����
		
		int i = 2;
		
		HSSFCell cell_2_2 = row2.getCell(2);
		cell_2_2.setCellValue((String)values.get("project_name")); //$NON-NLS-1$
		
		System.out.println("row3.getLastCellNum() = " + row3.getLastCellNum()); //$NON-NLS-1$
		//д�뵱ǰ�ܵ�������
		for (i = 2; i < row3.getLastCellNum()-2; i++) {
System.out.println("i = " + i); //$NON-NLS-1$
System.out.println("last week = " + row3.getCell(i).getRichStringCellValue().toString()); //$NON-NLS-1$
			
			//ԭģ���д˵�Ԫ��������
			if ("".equals(row3.getCell(i).getRichStringCellValue().toString()) //$NON-NLS-1$
					|| "0".equals(row3.getCell(i).getRichStringCellValue().toString())){ //$NON-NLS-1$
				row3.getCell(i).setCellValue((String)values.get("week")); //$NON-NLS-1$
				row4.getCell(i).setCellValue(red_sum);
				row5.getCell(i).setCellValue(yellow_sum);
				row6.getCell(i).setCellValue(green_sum);
				if (row7.getCell(i) == null){
					row7.createCell(i).setCellValue((Integer)statusMap.get("sum")); //$NON-NLS-1$
				} else {
					row7.getCell(i).setCellValue((Integer)statusMap.get("sum")); //$NON-NLS-1$
				}
				i++;
				break;
			} 
			
			if (((String)values.get("week")).equals((row3.getCell(i).getRichStringCellValue()).getString())) { //$NON-NLS-1$
				//ԭģ���д˵�Ԫ�������ݣ���ֵ�ǵ�ǰ��
				row3.getCell(i).setCellValue((String)values.get("week")); //$NON-NLS-1$
				row4.getCell(i).setCellValue(red_sum);
				row5.getCell(i).setCellValue(yellow_sum);
				row6.getCell(i).setCellValue(green_sum);
				if (row7.getCell(i) == null){
					row7.createCell(i).setCellValue((Integer)statusMap.get("sum")); //$NON-NLS-1$
				} else {
					row7.getCell(i).setCellValue((Integer)statusMap.get("sum")); //$NON-NLS-1$
				}
				i++;
				break;
			} 
		}
		
		//д��Ԥ���ܵ�������
		int count = 0; //��ʾ������Ԥ������
		for (int j = 1; j < forecastDay+1; j++) {
			if ((HashMap<String, Object>)values.get("forecast"+j) != null) { //$NON-NLS-1$
				row3.createCell(i+j-1);
				row3.getCell(i+j-1).setCellValue((String)((HashMap<String, Object>)values.get("forecast"+j)).get("forecastWeek")); //$NON-NLS-1$ //$NON-NLS-2$
				row6.createCell(i+j-1);
				row6.getCell(i+j-1).setCellValue((Integer)((HashMap<String, Object>)values.get("forecast"+j)).get("forecastNum")); //$NON-NLS-1$ //$NON-NLS-2$
				if (row7.getCell(i+j-1) == null){
					row7.createCell(i+j-1).setCellValue((Integer)((HashMap<String, Object>)values.get("forecast"+j)).get("forecastNum")); //$NON-NLS-1$ //$NON-NLS-2$
				} else {
					row7.getCell(i+j-1).setCellValue((Integer)((HashMap<String, Object>)values.get("forecast"+j)).get("forecastNum")); //$NON-NLS-1$ //$NON-NLS-2$
				}
				count++;
			}
		}
		//������30��
		if ((i+count) < 32) {
			for (int l = i+count; l<32; l++) {
				row3.createCell(l).setCellValue("0"); //$NON-NLS-1$
				row4.createCell(l).setCellValue(0);
				row5.createCell(l).setCellValue(0);
				row6.createCell(l).setCellValue(0);
				if (row7.getCell(l) == null){
					row7.createCell(l).setCellValue(0);
				} else {
					row7.getCell(l).setCellValue(0);
				}
			}
			        
		}
		
		//д����������Gesamt
//		HSSFCell cell_10_5 = row10.createCell(5);
//		cell_10_5.setCellValue(sum);
		
		//д�뱾�ܺ��̵Ƶ���������ʾ��ͼ���Ҳ�
		
		HSSFFont font = workbook.createFont();
		font.setBoldweight((short) 1);
		
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setWrapText(true);   
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setFont(font);
		
		HSSFCell cell_15_10 = row15.createCell(10);
		cell_15_10.setCellStyle(cellStyle);
		cell_15_10.setCellValue(red_sum);
		cell_15_10.setCellStyle(cellStyle);
		
		HSSFCell cell_20_10 = row20.createCell(10);
		cell_20_10.setCellValue(yellow_sum);
		cell_20_10.setCellStyle(cellStyle);
		
		HSSFCell cell_25_10 = row25.createCell(10);
		cell_25_10.setCellValue(green_sum);
		cell_25_10.setCellStyle(cellStyle);
		
	}
	
}
