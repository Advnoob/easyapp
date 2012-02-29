package com.customer.fawvw.issues.commands.forpre;

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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.customer.fawvw.issues.exception.FawvmLoaderException;
import com.customer.fawvw.issues.utils.ExcelUtil;
import com.customer.fawvw.issues.utils.ImageCellInfo;
import com.customer.fawvw.issues.utils.ImageUtil;
import com.teamcenter.rac.util.MessageBox;

public class CreateIssueForPreExcel {

	public static final String TEMPLATE_FILE_PATH = System.getenv("TPR") + "\\plugins\\Template\\IssueForPreReport_Template.xls"; //$NON-NLS-1$ //$NON-NLS-2$
	
	public static Boolean createExcel(HashMap<String, Object> parameters, String file) throws Exception {

		FileOutputStream fileOut = null;
		
		Boolean flag = false;
		
		try {
			Map<String, Object> values = IssueForPreReportLoader.load(parameters);
			
			fileOut = new FileOutputStream(new File(file));

			File excelTmp = new File(TEMPLATE_FILE_PATH);
			InputStream inputStream = new FileInputStream(excelTmp);
			POIFSFileSystem fs = new POIFSFileSystem(inputStream);
			System.out.println("ģ��·����" + TEMPLATE_FILE_PATH); //$NON-NLS-1$
			System.out.println("��ȡģ��ɹ�"); //$NON-NLS-1$
			
			//���ڷ�������������
			if (((ArrayList<Map<String, Object>>)values.get("Issues")).size() > 0 //$NON-NLS-1$
					&& ((ArrayList<Map<String, Object>>)values.get("Issues")) != null) { //$NON-NLS-1$
				
				
				
				HSSFWorkbook workbook = new HSSFWorkbook(fs);
				HSSFSheet sheet = workbook.getSheetAt(0);
				HSSFPatriarch patri = sheet.createDrawingPatriarch();
				
				System.out.println("��ʼд�뱨��"); //$NON-NLS-1$
				
				importData(workbook, sheet, patri, values);

				workbook.write(fileOut);
				
				System.out.println("����Excel����ɹ�"); //$NON-NLS-1$
				
				flag = true;
				
			} else {
				
				MessageBox.post("�����ڷ�������������", "Ԥ����������", MessageBox.INFORMATION); //$NON-NLS-1$ //$NON-NLS-1$
			}
			
		} catch (FileNotFoundException e) {
			MessageBox.post("Excelģ�岻����", //$NON-NLS-1$
					"Ԥ����������", //$NON-NLS-1$
					MessageBox.INFORMATION);
//			throw new FawvmLoaderException(Messages.CreateIssueForPreExcel_3);
			
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

	public static void importData(HSSFWorkbook wb, HSSFSheet sheet,
			HSSFPatriarch patri, Map<String, Object> values) throws Exception{

		try {
			//����logo
			ImageCellInfo imageCell = new ImageCellInfo(0, 6, 0, 7);
			ImageUtil.GenerateImage(wb, sheet, patri, imageCell, ImageUtil.getLogoImage());
			System.out.println("д�뱨��logoͼƬ"); //$NON-NLS-1$

			HSSFRow baserow = sheet.getRow(1);
			if (baserow == null) {
				baserow = sheet.createRow(1);
			}

			HSSFCell namecell = baserow.createCell(1);// ��Ŀ����
			namecell.setCellValue(new HSSFRichTextString(values.get("ProjectName") //$NON-NLS-1$
					.toString()));
			System.out.println("д����Ŀ����"); //$NON-NLS-1$
			
			HSSFCell timecell1 = baserow.createCell(6);// �Ʊ�����
			timecell1.setCellValue(new HSSFRichTextString(values.get("CreatTime") //$NON-NLS-1$
					.toString()));
			System.out.println("д���Ʊ�����"); //$NON-NLS-1$

			List<Map<String, Object>> Issues = (List<Map<String, Object>>) values
					.get("Issues"); //$NON-NLS-1$
			int i = 3;

			for (Map<String, Object> Issue : Issues) {
				HSSFRow row = sheet.getRow(i);
				HSSFRow rowStyle = sheet.getRow(3);
				if (row == null) {
					row = sheet.createRow(i);
				}
				row.createCell(0).setCellValue(
						new HSSFRichTextString(Issue.get("item_id").toString())); //$NON-NLS-1$
				row.getCell(0).setCellStyle(rowStyle.getCell(0).getCellStyle());

				row.createCell(1).setCellValue(
								new HSSFRichTextString(Issue.get("fv9IssueDesc") //$NON-NLS-1$
										.toString()));
				row.getCell(1).setCellStyle(rowStyle.getCell(1).getCellStyle());
				
				//��ʩ
				String fv9Solution = ""; //$NON-NLS-1$
				if (!"".equals(Issue.get("fv9Solution1"))) { //$NON-NLS-1$ //$NON-NLS-2$
					fv9Solution += "1��" + ((String)Issue.get("fv9Solution1")).replaceAll("\n", ";");  //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-1$
				}
				if (!"".equals(Issue.get("fv9Solution2"))) { //$NON-NLS-1$ //$NON-NLS-2$
					fv9Solution += "\r\n" + "2��" + ((String)Issue.get("fv9Solution2")).replaceAll("\n", ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-2$
				}
				if (!"".equals(Issue.get("fv9Solution3"))) { //$NON-NLS-1$ //$NON-NLS-2$
					fv9Solution += "\r\n" + "3��" + ((String)Issue.get("fv9Solution3")).replaceAll("\n", ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-2$
				}
				if (!"".equals(Issue.get("fv9Solution4"))) { //$NON-NLS-1$ //$NON-NLS-2$
					fv9Solution += "\r\n" + "4��" + ((String)Issue.get("fv9Solution4")).replaceAll("\n", ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-2$
				}
				if (!"".equals(Issue.get("fv9Solution5"))) { //$NON-NLS-1$ //$NON-NLS-2$
					fv9Solution += "\r\n" + "5��" + ((String)Issue.get("fv9Solution5")).replaceAll("\n", ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-2$
				}
				row.createCell(2).setCellValue(
								new HSSFRichTextString(fv9Solution));
				row.getCell(2).setCellStyle(rowStyle.getCell(2).getCellStyle());
				
				row.createCell(3).setCellValue(new HSSFRichTextString());
				row.getCell(3).setCellStyle(rowStyle.getCell(3).getCellStyle());
				ExcelUtil.fillTheCellColor(wb,row.getCell(3),Issue.get("fv9RGStatus") //$NON-NLS-1$
						.toString(), ""); //$NON-NLS-1$
				
				row.createCell(4).setCellValue(
						new HSSFRichTextString(Issue.get("fv9ProposedDate") //$NON-NLS-1$
								.toString()));
				row.getCell(4).setCellStyle(rowStyle.getCell(4).getCellStyle());
				
				row.createCell(5).setCellValue(
						new HSSFRichTextString(Issue.get("fv9SolDeadlineDate") //$NON-NLS-1$
								.toString()));
				row.getCell(5).setCellStyle(rowStyle.getCell(5).getCellStyle());
				
				row.createCell(6).setCellValue(
						new HSSFRichTextString(Issue.get("fv9SlResDep1") //$NON-NLS-1$
								.toString()));
				row.getCell(6).setCellStyle(rowStyle.getCell(6).getCellStyle());
				
				row.createCell(7).setCellValue(
						new HSSFRichTextString(Issue.get("fv9IssueReqCarNo") //$NON-NLS-1$
								.toString()));//����
				row.getCell(7).setCellStyle(rowStyle.getCell(7).getCellStyle());
				
				System.out.println("д������" + Issue.get("itemRevision") + "��Ϣ�ɹ�"); //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-1$

				i++;
			}
			
		} catch (Exception e) {
			
			throw new FawvmLoaderException(e.getMessage());
			
		}
	}
	
}
