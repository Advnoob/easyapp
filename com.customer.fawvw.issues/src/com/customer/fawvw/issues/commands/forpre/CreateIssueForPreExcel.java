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
			System.out.println("ģ��·����" + TEMPLATE_FILE_PATH); 
			System.out.println("��ȡģ��ɹ�"); 
			
			//���ڷ�������������
			if (((ArrayList<Map<String, Object>>)values.get("Issues")).size() > 0 
					&& ((ArrayList<Map<String, Object>>)values.get("Issues")) != null) { 
				
				
				
				HSSFWorkbook workbook = new HSSFWorkbook(fs);
				HSSFSheet sheet = workbook.getSheetAt(0);
				HSSFPatriarch patri = sheet.createDrawingPatriarch();
				
				System.out.println("��ʼд�뱨��"); 
				
				importData(workbook, sheet, patri, values);

				workbook.write(fileOut);
				
				System.out.println("����Excel����ɹ�"); 
				
				flag = true;
				
			} else {
				
				MessageBox.post("�����ڷ�������������", "Ԥ����������", MessageBox.INFORMATION);  
			}
			
		} catch (FileNotFoundException e) {
			MessageBox.post("Excelģ�岻����", 
					"Ԥ����������", 
					MessageBox.INFORMATION);
			
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
			System.out.println("д�뱨��logoͼƬ"); 

			HSSFRow baserow = sheet.getRow(1);
			if (baserow == null) {
				baserow = sheet.createRow(1);
			}

			HSSFCell namecell = baserow.createCell(1);// ��Ŀ����
			namecell.setCellValue(new HSSFRichTextString(values.get("ProjectName") 
					.toString()));
			System.out.println("д����Ŀ����"); 
			
			HSSFCell timecell1 = baserow.createCell(6);// �Ʊ�����
			timecell1.setCellValue(new HSSFRichTextString(values.get("CreatTime") 
					.toString()));
			System.out.println("д���Ʊ�����"); 

			List<Map<String, Object>> Issues = (List<Map<String, Object>>) values
					.get("Issues"); 
			int i = 3;

			for (Map<String, Object> Issue : Issues) {
				HSSFRow row = sheet.getRow(i);
				HSSFRow rowStyle = sheet.getRow(3);
				if (row == null) {
					row = sheet.createRow(i);
				}
				row.createCell(0).setCellValue(
						new HSSFRichTextString(Issue.get("item_id").toString())); 
				row.getCell(0).setCellStyle(rowStyle.getCell(0).getCellStyle());

				row.createCell(1).setCellValue(
								new HSSFRichTextString(Issue.get("fv9IssueDesc") 
										.toString()));
				row.getCell(1).setCellStyle(rowStyle.getCell(1).getCellStyle());
				
				//��ʩ
				String fv9Solution = ""; 
				if (!"".equals(Issue.get("fv9SolutionBS"))) {  
					fv9Solution += "BS:" + ((String)Issue.get("fv9SolutionBS")).replaceAll("\n", ";");   //$NON-NLS-3$ //$NON-NLS-4$ 
				}
				if (!"".equals(Issue.get("fv9SolutionCA"))) {  
					fv9Solution += "\r\n" + "CA:" + ((String)Issue.get("fv9SolutionCA")).replaceAll("\n", ";");   //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
				}
				if (!"".equals(Issue.get("fv9SolutionLO"))) {  
					fv9Solution += "\r\n" + "LO:" + ((String)Issue.get("fv9SolutionLO")).replaceAll("\n", ";");   //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
				}
				if (!"".equals(Issue.get("fv9SolutionPA"))) {  
					fv9Solution += "\r\n" + "PA:" + ((String)Issue.get("fv9SolutionPA")).replaceAll("\n", ";");   //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
				}
				if (!"".equals(Issue.get("fv9SolutionPL"))) {  
					fv9Solution += "\r\n" + "PL:" + ((String)Issue.get("fv9SolutionPL")).replaceAll("\n", ";");   //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
				}
				if (!"".equals(Issue.get("fv9SolutionQAPP"))) {  
					fv9Solution += "\r\n" + "QAPP:" + ((String)Issue.get("fv9SolutionQAPP")).replaceAll("\n", ";");   //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
				}
				if (!"".equals(Issue.get("fv9SolutionSU"))) {  
					fv9Solution += "\r\n" + "SU:" + ((String)Issue.get("fv9SolutionSU")).replaceAll("\n", ";");   //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
				}
				if (!"".equals(Issue.get("fv9SolutionVSC"))) {  
					fv9Solution += "\r\n" + "VSC:" + ((String)Issue.get("fv9SolutionVSC")).replaceAll("\n", ";");   //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
				}
				row.createCell(2).setCellValue(
								new HSSFRichTextString(fv9Solution));
				row.getCell(2).setCellStyle(rowStyle.getCell(2).getCellStyle());
				
				row.createCell(3).setCellValue(new HSSFRichTextString());
				row.getCell(3).setCellStyle(rowStyle.getCell(3).getCellStyle());
				ExcelUtil.fillTheCellColor(wb,row.getCell(3),Issue.get("fv9RGStatus") 
						.toString(), ""); 
				
				row.createCell(4).setCellValue(
						new HSSFRichTextString(Issue.get("fv9ProposedDate") 
								.toString()));
				row.getCell(4).setCellStyle(rowStyle.getCell(4).getCellStyle());
				
				row.createCell(5).setCellValue(
						new HSSFRichTextString(Issue.get("fv9SolDeadlineDate") 
								.toString()));
				row.getCell(5).setCellStyle(rowStyle.getCell(5).getCellStyle());
				
				String slResDep = "";
				if (!"".equals(Issue.get("fv9SlResDepBS"))) {  
					slResDep += ((String)Issue.get("fv9SlResDepBS")).replaceAll("\n", ";");   //$NON-NLS-3$ //$NON-NLS-4$ 
				}
				if (!"".equals(Issue.get("fv9SlResDepCA"))) {  
					slResDep += "\r\n" + ((String)Issue.get("fv9SlResDepCA")).replaceAll("\n", ";");   //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
				}
				if (!"".equals(Issue.get("fv9SlResDepLO"))) {  
					slResDep += "\r\n" + ((String)Issue.get("fv9SlResDepLO")).replaceAll("\n", ";");   //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
				}
				if (!"".equals(Issue.get("fv9SlResDepPA"))) {  
					slResDep += "\r\n" + ((String)Issue.get("fv9SlResDepPA")).replaceAll("\n", ";");   //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
				}
				if (!"".equals(Issue.get("fv9SlResDepPL"))) {  
					slResDep += "\r\n" + ((String)Issue.get("fv9SlResDepPL")).replaceAll("\n", ";");   //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
				}
				if (!"".equals(Issue.get("fv9SlResDepQAPP"))) {  
					slResDep += "\r\n" + ((String)Issue.get("fv9SlResDepQAPP")).replaceAll("\n", ";");   //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
				}
				if (!"".equals(Issue.get("fv9SlResDepSU"))) {  
					slResDep += "\r\n" + ((String)Issue.get("fv9SlResDepSU")).replaceAll("\n", ";");   //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
				}
				if (!"".equals(Issue.get("fv9SlResDepVSC"))) {  
					slResDep += "\r\n" + ((String)Issue.get("fv9SlResDepVSC")).replaceAll("\n", ";");   //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ 
				}
				row.createCell(6).setCellValue(slResDep);
				row.getCell(6).setCellStyle(rowStyle.getCell(6).getCellStyle());
				
				row.createCell(7).setCellValue(
						new HSSFRichTextString(Issue.get("fv9IssueReqCarNo") 
								.toString()));//����
				row.getCell(7).setCellStyle(rowStyle.getCell(7).getCellStyle());
				
				System.out.println("д������" + Issue.get("itemRevision") + "��Ϣ�ɹ�");   

				i++;
			}
			
		} catch (Exception e) {
			
			throw new FawvmLoaderException(e.getMessage());
			
		}
	}
	
}
