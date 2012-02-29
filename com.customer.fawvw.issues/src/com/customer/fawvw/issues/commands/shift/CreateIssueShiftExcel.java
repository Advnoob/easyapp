package com.customer.fawvw.issues.commands.shift;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.customer.fawvw.issues.exception.FawvmLoaderException;
import com.customer.fawvw.issues.utils.ExcelUtil;
import com.customer.fawvw.issues.utils.ImageCellInfo;
import com.customer.fawvw.issues.utils.ImageUtil;
import com.teamcenter.rac.util.MessageBox;

public class CreateIssueShiftExcel {

	public static final String TEMPLATE_FILE_PATH = System.getenv("TPR") + "\\plugins\\Template\\IssueShiftReport_Template.xls"; //$NON-NLS-1$ //$NON-NLS-2$
	
	public static Boolean createExcel(HashMap<String, Object> parameters, String file) throws Exception{

		FileOutputStream fileOut = null;
		Boolean flag = false;
		
		try {
			System.out.println("ģ��λ�ã�" + TEMPLATE_FILE_PATH); //$NON-NLS-1$
			//�����µ�Excel
			fileOut = new FileOutputStream(new File(file));
			//�ж�ģ���Ƿ����
			File excelTmp = new File(TEMPLATE_FILE_PATH);
			InputStream inputStream = new FileInputStream(excelTmp);
			POIFSFileSystem fs = new POIFSFileSystem(inputStream);
			System.out.println("��ȡģ��ɹ�"); //$NON-NLS-1$
			
			Map<String, Object> issueShiftReportData =  IssueShiftReportLoader.load(parameters);
			
			//���ݴ���
			
			
			if (issueShiftReportData != null &&
					((ArrayList<Map<String, Object>>)issueShiftReportData.get("planIssues")).size() > 0 && //$NON-NLS-1$
					((ArrayList<Map<String, Object>>)issueShiftReportData.get("actualIssues")).size() > 0) { //$NON-NLS-1$
				
				HSSFWorkbook workbook = new HSSFWorkbook(fs);
				HSSFSheet sheet = workbook.getSheetAt(0);
				HSSFPatriarch patri = sheet.createDrawingPatriarch();
				importData(workbook, sheet, patri, issueShiftReportData);

				HSSFSheet sheet1 = workbook.getSheetAt(1);
				HSSFPatriarch patri1 = sheet1.createDrawingPatriarch();
				importData1(workbook, sheet1, patri1, issueShiftReportData);
				workbook.write(fileOut);
				
				System.out.println("����Excel�ɹ�"); //$NON-NLS-1$
				
				flag = true;
				
			} else {
				MessageBox.post("�����ڷ�������������", "����������ⱨ��", MessageBox.INFORMATION); //$NON-NLS-1$ //$NON-NLS-1$
			}
			
		} catch (FileNotFoundException e) {

			throw new FawvmLoaderException("Excelģ�岻����"); //$NON-NLS-1$
			
		} catch (Exception e) {
			throw new FawvmLoaderException(e.getMessage());
		} finally {
			try {
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	@SuppressWarnings( { "unchecked", "deprecation" })
	public static void importData(HSSFWorkbook wb, HSSFSheet sheet,
			HSSFPatriarch patri, Map<String, Object> values) throws Exception{
		if (values != null) {
			System.out.println("��ʼд��������μƻ�������ⱨ��"); //$NON-NLS-1$
			try {
				ImageCellInfo imageCell = new ImageCellInfo(0, 7, 0, 9);
				ImageUtil.GenerateImage(wb, sheet, patri, imageCell, ImageUtil.getLogoImage());
				System.out.println("д�뱨��logoͼƬ"); //$NON-NLS-1$
				
				/* �������� */
				HSSFRow baserow = sheet.getRow(1);
				if (baserow == null) {
					baserow = sheet.createRow(1);
				}
				
				HSSFCell namecell = baserow.getCell((short) 1);// ��Ŀ����
				namecell.setCellValue(new HSSFRichTextString(values.get("ProjectName") //$NON-NLS-1$
						.toString()));
				System.out.println("д����Ŀ����"); //$NON-NLS-1$
				
				HSSFCell timecell1 = baserow.getCell((short) 8);// �Ʊ�����
				timecell1.setCellValue(new HSSFRichTextString(values.get("CreatTime") //$NON-NLS-1$
						.toString()));
				System.out.println("д���Ʊ�����"); //$NON-NLS-1$

				List<Map<String, Object>> planIssues = (List<Map<String, Object>>) values
						.get("planIssues"); //$NON-NLS-1$
				
				if (planIssues.size() > 0) {
					int i = 3;

					for (Map<String, Object> planIssue : planIssues) {
						HSSFRow row = sheet.getRow(i);
						HSSFRow rowStyle = sheet.getRow(2);
						if (row == null) {
							row = sheet.createRow(i);
						}
						
						if (!"".equals(planIssue.get("item_id"))) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(0).setCellValue(
									new HSSFRichTextString(planIssue.get("item_id") //$NON-NLS-1$
											.toString()));
						} else {
							row.createCell(0).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(0).setCellStyle(rowStyle.getCell(0).getCellStyle());
	
						if (!"".equals(planIssue.get("fv9IssueDesc"))) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(1).setCellValue(
									new HSSFRichTextString((String)planIssue.get("fv9IssueDesc"))); //$NON-NLS-1$
						} else {
							row.createCell(1).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(1).setCellStyle(rowStyle.getCell(1).getCellStyle());
						
						String fv9Solution = ""; //$NON-NLS-1$
						if (!"".equals(planIssue.get("fv9Solution1"))) { //$NON-NLS-1$ //$NON-NLS-2$
							fv9Solution += "1��" + ((String)planIssue.get("fv9Solution1")).replaceAll("\n", ";");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-4$ //$NON-NLS-1$
						}
						if (!"".equals(planIssue.get("fv9Solution2"))) { //$NON-NLS-1$ //$NON-NLS-2$
							fv9Solution += "\r\n" + "2��" + ((String)planIssue.get("fv9Solution2")).replace("\n", ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-2$
						}
						if (!"".equals(planIssue.get("fv9Solution3"))) { //$NON-NLS-1$ //$NON-NLS-2$
							fv9Solution += "\r\n" + "3��" + ((String)planIssue.get("fv9Solution3")).replace("\n", ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-2$
						}
						if (!"".equals(planIssue.get("fv9Solution4"))) { //$NON-NLS-1$ //$NON-NLS-2$
							fv9Solution += "\r\n" + "4��" + ((String)planIssue.get("fv9Solution4")).replace("\n", ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-2$
						}
						if (!"".equals(planIssue.get("fv9Solution5"))) { //$NON-NLS-1$ //$NON-NLS-2$
							fv9Solution += "\r\n" + "5��" + ((String)planIssue.get("fv9Solution5")).replace("\n", ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-2$
						}
						
						if (!"".equals(fv9Solution)) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(2).setCellValue(
									new HSSFRichTextString(fv9Solution)); //$NON-NLS-1$
						} else {
							row.createCell(2).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(2).setCellStyle(rowStyle.getCell(2).getCellStyle());
						
						row.createCell(3).setCellValue(new HSSFRichTextString());
						row.getCell(3).setCellStyle(rowStyle.getCell(3).getCellStyle());
						if (!"".equals(planIssue.get("fv9RGStatus"))) { //$NON-NLS-1$ //$NON-NLS-2$
							ExcelUtil.fillTheCellColor(wb,row.getCell(3),planIssue.get("fv9RGStatus") //$NON-NLS-1$
									.toString(), ""); //$NON-NLS-1$
						}
						
						if (!"".equals(planIssue.get("carNumber"))) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(4).setCellValue(
									new HSSFRichTextString(planIssue.get("carNumber") //$NON-NLS-1$
											.toString()));
						} else {
							row.createCell(4).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(4).setCellStyle(rowStyle.getCell(4).getCellStyle());
						
						if (!"".equals(planIssue.get("fv9RoundNo"))) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(5).setCellValue(
									new HSSFRichTextString(planIssue.get("fv9RoundNo") //$NON-NLS-1$
											.toString()));
						} else {
							row.createCell(5).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(5).setCellStyle(rowStyle.getCell(5).getCellStyle());
						
						if (!"".equals(planIssue.get("fv9PlImTime"))) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(6).setCellValue(
									new HSSFRichTextString(planIssue.get("fv9PlImTime") //$NON-NLS-1$
											.toString()));
						} else {
							row.createCell(6).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(6).setCellStyle(rowStyle.getCell(6).getCellStyle());
						
						if (!"".equals(planIssue.get("fv9ProposedDate"))) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(7).setCellValue(
									new HSSFRichTextString(planIssue.get("fv9ProposedDate") //$NON-NLS-1$
											.toString()));
						} else {
							row.createCell(7).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(7).setCellStyle(rowStyle.getCell(7).getCellStyle());
						
						if (!"".equals(planIssue.get("fv9SolDeadlineDate"))) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(8).setCellValue(
									new HSSFRichTextString(planIssue.get("fv9SolDeadlineDate") //$NON-NLS-1$
											.toString()));
						} else {
							row.createCell(8).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(8).setCellStyle(rowStyle.getCell(8).getCellStyle());
						
						if (!"".equals(planIssue.get("fv9SlResDep1"))) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(9).setCellValue(
									new HSSFRichTextString(planIssue.get("fv9SlResDep1") //$NON-NLS-1$
											.toString()));
						} else {
							row.createCell(9).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(9).setCellStyle(rowStyle.getCell(9).getCellStyle());
						
						System.out.println("д��" + planIssue.get("itemRevision") + "��Ϣ"); //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$
						i++;
					}
				} else {
					System.out.println("ϵͳ�����ڷ�������������"); //$NON-NLS-1$
				}
				
				
				System.out.println("д��������μƻ�������ⱨ�����"); //$NON-NLS-1$
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
				throw new FawvmLoaderException("ִ�г���ʧ�ܣ���ο���־"); //$NON-NLS-1$
			}
		}
		
		
	}

	public static void importData1(HSSFWorkbook wb, HSSFSheet sheet,
			HSSFPatriarch patri, Map<String, Object> values) throws Exception{
		if (values != null) {
			System.out.println("��ʼд���������ʵ�ʽ�����ⱨ��"); //$NON-NLS-1$
			try {
				ImageCellInfo imageCell = new ImageCellInfo(0, 8, 0, 10);
				ImageUtil.GenerateImage(wb, sheet, patri, imageCell, ImageUtil.getLogoImage());
				System.out.println("д�뱨��logoͼƬ"); //$NON-NLS-1$
				
				/* �������� */
				HSSFRow baserow = sheet.getRow(1);
				if (baserow == null) {
					baserow = sheet.createRow(1);
				}

				HSSFCell namecell = baserow.getCell(1);// ��Ŀ����
				namecell.setCellValue(new HSSFRichTextString(values.get("ProjectName") //$NON-NLS-1$
						.toString()));
				System.out.println("д����Ŀ����"); //$NON-NLS-1$
				
				HSSFCell timecell1 = baserow.getCell(9);// �Ʊ�����
				timecell1.setCellValue(new HSSFRichTextString(values.get("CreatTime") //$NON-NLS-1$
						.toString()));
				System.out.println("д���Ʊ�����"); //$NON-NLS-1$

				List<Map<String, Object>> actualIssues = (List<Map<String, Object>>) values
						.get("actualIssues"); //$NON-NLS-1$
				
				if (actualIssues.size() > 0) {
					int i = 3;

					for (Map<String, Object> actualIssue : actualIssues) {
						HSSFRow row = sheet.getRow(i);
						HSSFRow rowStyle = sheet.getRow(2);
						if (row == null) {
							row = sheet.createRow(i);
						}
						//�����
						if (!"".equals(actualIssue.get("item_id"))) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(0).setCellValue(
									new HSSFRichTextString(actualIssue.get("item_id") //$NON-NLS-1$
											.toString()));
						} else {
							row.createCell(0).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(0).setCellStyle(rowStyle.getCell(0).getCellStyle());
						
						//��������
						if (!"".equals(actualIssue.get("fv9IssueDesc"))) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(1).setCellValue(
									new HSSFRichTextString(actualIssue.get("fv9IssueDesc") //$NON-NLS-1$
											.toString()));
						} else {
							row.createCell(1).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(1).setCellStyle(rowStyle.getCell(1).getCellStyle());
						
						//��ʩ
						String fv9Solution = ""; //$NON-NLS-1$
						if (!"".equals(actualIssue.get("fv9Solution1"))) { //$NON-NLS-1$ //$NON-NLS-2$
							fv9Solution += "1��" + ((String)actualIssue.get("fv9Solution1")).replaceAll("\n", ";");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-1$
						}
						if (!"".equals(actualIssue.get("fv9Solution2"))) { //$NON-NLS-1$ //$NON-NLS-2$
							fv9Solution += "\r\n" + "2��" + ((String)actualIssue.get("fv9Solution2")).replace("\n", ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-2$
						}
						if (!"".equals(actualIssue.get("fv9Solution3"))) { //$NON-NLS-1$ //$NON-NLS-2$
							fv9Solution += "\r\n" + "3��" + ((String)actualIssue.get("fv9Solution3")).replace("\n", ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-2$
						}
						if (!"".equals(actualIssue.get("fv9Solution4"))) { //$NON-NLS-1$ //$NON-NLS-2$
							fv9Solution += "\r\n" + "4��" + ((String)actualIssue.get("fv9Solution4")).replace("\n", ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-2$
						}
						if (!"".equals(actualIssue.get("fv9Solution5"))) { //$NON-NLS-1$ //$NON-NLS-2$
							fv9Solution += "\r\n" + "5��" + ((String)actualIssue.get("fv9Solution5")).replace("\n", ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-2$
						}
						if (!"".equals(fv9Solution)) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(2).setCellValue(
									new HSSFRichTextString(fv9Solution));
						} else {
							row.createCell(2).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(2).setCellStyle(rowStyle.getCell(2).getCellStyle());
							
						//״̬
						row.createCell(3).setCellValue(new HSSFRichTextString());
						row.getCell(3).setCellStyle(rowStyle.getCell(3).getCellStyle());
						if (!"".equals(actualIssue.get("fv9RGStatus"))) { //$NON-NLS-1$ //$NON-NLS-2$
							ExcelUtil.fillTheCellColor(wb,row.getCell(3),actualIssue.get("fv9RGStatus") //$NON-NLS-1$
									.toString(), ""); //$NON-NLS-1$
						}
						
						//�����
						if (!"".equals(actualIssue.get("carNumber"))) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(4).setCellValue(
									new HSSFRichTextString(actualIssue.get("carNumber") //$NON-NLS-1$
											.toString()));
						} else {
							row.createCell(4).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(4).setCellStyle(rowStyle.getCell(4).getCellStyle());
						
						//���κ�
						if (!"".equals(actualIssue.get("fv9RoundNo"))) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(5).setCellValue(
									new HSSFRichTextString(actualIssue.get("fv9RoundNo") //$NON-NLS-1$
											.toString()));
						} else {
							row.createCell(5).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(5).setCellStyle(rowStyle.getCell(5).getCellStyle());
						
						//�����Ż��ƻ��������
						if (!"".equals(actualIssue.get("fv9ReImTime"))) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(6).setCellValue(
									new HSSFRichTextString(actualIssue.get("fv9ReImTime") //$NON-NLS-1$
											.toString()));
						} else {
							row.createCell(6).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(6).setCellStyle(rowStyle.getCell(6).getCellStyle());
						
						//���ʱ��
						if (!"".equals(actualIssue.get("fv9ProposedDate"))) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(7).setCellValue(
									new HSSFRichTextString(actualIssue.get("fv9ProposedDate") //$NON-NLS-1$
											.toString()));
						} else {
							row.createCell(7).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(7).setCellStyle(rowStyle.getCell(7).getCellStyle());

						
						//�������
						if (!"".equals(actualIssue.get("fv9SolDeadlineDate"))) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(8).setCellValue(
									new HSSFRichTextString(actualIssue.get("fv9SolDeadlineDate") //$NON-NLS-1$
											.toString())); //$NON-NLS-1$
						} else {
							row.createCell(8).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(8).setCellStyle(rowStyle.getCell(8).getCellStyle());
						
						//ʵ�����ʱ��
						if (!"".equals(actualIssue.get("fv9CompletedDate"))) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(9).setCellValue(
									new HSSFRichTextString(actualIssue.get("fv9CompletedDate") //$NON-NLS-1$
											.toString()));
						} else {
							row.createCell(9).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(9).setCellStyle(rowStyle.getCell(9).getCellStyle());
						
						//���β���
						if (!"".equals(actualIssue.get("fv9SlResDep1"))) { //$NON-NLS-1$ //$NON-NLS-2$
							row.createCell(10).setCellValue(
									new HSSFRichTextString(actualIssue.get("fv9SlResDep1") //$NON-NLS-1$
											.toString()));
						} else {
							row.createCell(10).setCellValue(
									new HSSFRichTextString("")); //$NON-NLS-1$
						}
						row.getCell(10).setCellStyle(rowStyle.getCell(10).getCellStyle());
						
						System.out.println("д��" + actualIssue.get("itemRevision") + "��Ϣ"); //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$
						
						i++;
						
					}
				}
				
				System.out.println("д���������ʵ�ʽ�����ⱨ�����"); //$NON-NLS-1$
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new FawvmLoaderException("ִ�г���ʧ�ܣ���ο���־"); //$NON-NLS-1$
			}
		}
	}
}
