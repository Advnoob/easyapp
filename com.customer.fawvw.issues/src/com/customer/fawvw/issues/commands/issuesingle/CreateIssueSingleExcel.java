package com.customer.fawvw.issues.commands.issuesingle;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.eclipse.swt.graphics.Path;

import com.customer.fawvw.issues.exception.FawvmLoaderException;
import com.customer.fawvw.issues.utils.DateUtil;
import com.customer.fawvw.issues.utils.ImageCellInfo;
import com.customer.fawvw.issues.utils.ImageUtil;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

class Solution {
	String sol;
	String solDate;
}

public class CreateIssueSingleExcel {

	public static final String TEMPLATE_FILE_PATH = System.getenv("TPR") + "\\plugins\\Template\\IssueSingleReport_Template.xls"; 

	public static final String LOGO_FILE_PATH = "/xls/logo.JPG"; 

	private static final float PX_DEFAULT = 32.00f;
	
	private static final float PX_MODIFIED = 36.56f;

	private static final int PX_ROW = 15;
	
	public static void createExcel(InterfaceAIFComponent targetcompontent, 
			String file, TCSession session)	throws Exception{
		
		System.out.println("ģ������λ�ã�" + TEMPLATE_FILE_PATH);  
		
		FileOutputStream fileOut = null;

		try {
			fileOut = new FileOutputStream(new File(file));
			
			//ģ�岻�ڹ����У�ȡ����·��
			File excelTmp = new File(TEMPLATE_FILE_PATH);
			InputStream inputStream = new FileInputStream(excelTmp);
			
			POIFSFileSystem fs = new POIFSFileSystem(inputStream);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFPatriarch patri = sheet.createDrawingPatriarch();
			
			System.out.println("��ȡģ��ɹ�"); 
			
			importData(workbook, sheet, patri, targetcompontent, session);
			
			workbook.write(fileOut);
			
			System.out.println("��������ɹ�"); 
			
		} catch (FileNotFoundException e) {
			
			System.out.println("Excelģ�岻����"); 
			e.printStackTrace();
			throw new FawvmLoaderException("Excelģ�岻����"); 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new FawvmLoaderException(e.getMessage());
			
		} finally {
			try {
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �������дExcel
	 * @throws Exception 
	 * 
	 * 
	 */
		@SuppressWarnings("deprecation")
		public static void importData(HSSFWorkbook wb, HSSFSheet sheet,
				HSSFPatriarch patri, InterfaceAIFComponent targetcompontent,
				TCSession session) throws Exception {
			
			try {
				System.out.println("��ʼ��ȡ����"); 
				Map<String, Object> values = ReportIssueSingleLoader.load(targetcompontent, session);
				System.out.println("��ȡ���ݽ���"); 
				
				System.out.println("��ʼд�뱨��"); 
				
				//Ĭ����ʽ�����֣����Ҿ������¾��У��Զ�����
				HSSFCellStyle cellStyle = wb.createCellStyle();   
				cellStyle.setWrapText(true);   
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				//���֣����Ҿ������¾��ϣ��Զ�����
				HSSFCellStyle cellTopStyle = wb.createCellStyle();
				cellTopStyle.setWrapText(true);
				cellTopStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
				cellTopStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);

				//���֣����Ҿ��У����¾��У��Զ�����
				HSSFCellStyle cellCenterStyle = wb.createCellStyle();
				cellCenterStyle.setWrapText(true);
				cellCenterStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				cellCenterStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				
				HSSFCellStyle borderCellStyle = wb.createCellStyle();
				borderCellStyle.setBorderBottom((short)1);
				borderCellStyle.setBorderLeft((short)1);
				borderCellStyle.setBorderRight((short)1);
				borderCellStyle.setBorderTop((short)1);
				
				HSSFRow row0 = sheet.getRow(0);
				HSSFRow row1 = sheet.getRow(1);
				HSSFRow row4 = sheet.getRow(4);
				HSSFRow row7 = sheet.getRow(7);
				HSSFRow row10 = sheet.getRow(10);
				HSSFRow row11 = sheet.getRow(11);
				HSSFRow row13 = sheet.getRow(13);
				HSSFRow row15 = sheet.getRow(15);
				HSSFRow row16 = sheet.getRow(16);
				HSSFRow row22 = sheet.getRow(22);
				HSSFRow row25 = sheet.getRow(25);
				HSSFRow row28 = sheet.getRow(28);
				HSSFRow row29 = sheet.getRow(29);
				HSSFRow row31 = sheet.getRow(31);
				HSSFRow row34 = sheet.getRow(34);
				HSSFRow row35 = sheet.getRow(35);
				HSSFRow row36 = sheet.getRow(36);
				HSSFRow row37 = sheet.getRow(37);
				HSSFRow row39 = sheet.getRow(39);
				
				//Bauteil
				HSSFCell bauteilCell = row0.getCell(4);
				if (bauteilCell == null) {
					bauteilCell = row0.createCell(4);
				}
				bauteilCell.setCellValue((String)values.get("fv9PartNumber")); 
				
				//Lifeerant
				HSSFCell lifeerantCell = row0.getCell(55);
				if (lifeerantCell == null) {
					lifeerantCell = row0.createCell(0);
				}
				lifeerantCell.setCellValue((String)values.get("fv9SupplierID")); 
				
				//Zustaendig
				HSSFCell zustaendigCell = row1.getCell(5);
				if (zustaendigCell == null) {
					zustaendigCell = row1.createCell(5);
				}
				zustaendigCell.setCellValue((String)values.get("fv9PartName")); 
				
				//��Ӧ������
				HSSFCell supplyCell = row1.getCell(49);
				if (supplyCell == null) {
					supplyCell = row1.createCell(49);
				}
				supplyCell.setCellValue((String)values.get("fv9SupplierName")); 
				
				//��������
				HSSFCell descCell = row4.getCell(0);
				if (descCell == null) {
					descCell = row4.createCell(0);
				}
				descCell.setCellValue((String)values.get("fv9IssueDesc")); 
				
				//��������ͼƬ	
				if (((HashMap)values.get("problemImage")).containsKey("FV9DescPhoto")) { 
					HSSFClientAnchor anchor = new HSSFClientAnchor();
					Map<String, Object> problemImage = (Map<String, Object>)values.get("problemImage"); 
					BufferedImage imageBuffer = (BufferedImage)problemImage.get("FV9DescPhoto"); 
					byte[] imageValue = ImageUtil.getImgValue((BufferedImage)(problemImage.get("FV9DescPhoto"))); 
	
					HSSFPicture picture = patri.createPicture(anchor, wb.addPicture(
							imageValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					ImageCellInfo problemCellnfo = new ImageCellInfo(13, 0, 30, 18);	
					ImageUtil.GenerateImage(wb, sheet, patri, problemCellnfo, imageBuffer);
					System.out.println("д����������ͼƬ"); 
				} 
				
				List<Solution> solutionList = new ArrayList<Solution>();
				
				//��ʱ��ʩ
				if ((String)values.get("fv9IssueTempSolution") != null &&
						!"".equals((String)values.get("fv9IssueTempSolution"))) {
					Solution solution = new Solution();
					solution.sol = "��ʱ��ʩ:" + (String)values.get("fv9IssueTempSolution");
					
					if (!"".equals((String)values.get("fv9TempSolutionDL"))) { 
						solution.solDate = DateUtil.getWeekOfYear((String)values.get("fv9TempSolutionDL")) + "/" +  
								((String)values.get("fv9TempSolutionDL")).substring(0, 4); 
					} else {
						solution.solDate = ""; 
					}
					solutionList.add(solution);
				}
				
				//TE��ʩ
				if ((String)values.get("fv9SolutionTE") != null && 
						!"".equals((String)values.get("fv9SolutionTE"))) {
					Solution solution = new Solution();
					solution.sol = "TE��ʩ:" + (String)values.get("fv9SolutionTE");
					if (!"".equals((String)values.get("fv9SlDLDateTE"))) { 
						solution.solDate = DateUtil.getWeekOfYear((String)values.get("fv9SlDLDateTE")) + "/" +  
								((String)values.get("fv9SlDLDateTE")).substring(0, 4); 
					} else {
						solution.solDate = ""; 
					}
					solutionList.add(solution);
				}
				
				//QAPP��ʩ
				if ((String)values.get("fv9SolutionQAPP") != null && 
						!"".equals((String)values.get("fv9SolutionQAPP"))) {
					Solution solution = new Solution();
					solution.sol = "QAPP��ʩ:" + (String)values.get("fv9SolutionQAPP");
					if (!"".equals((String)values.get("fv9SlDLDateQAPP"))) { 
						solution.solDate = DateUtil.getWeekOfYear((String)values.get("fv9SlDLDateQAPP")) + "/" +  
								((String)values.get("fv9SlDLDateQAPP")).substring(0, 4); 
					} else {
						solution.solDate = ""; 
					}
					solutionList.add(solution);
				}
				
				//SU��ʩ
				if ((String)values.get("fv9SolutionSU") != null && 
						!"".equals((String)values.get("fv9SolutionSU"))) {
					Solution solution = new Solution();
					solution.sol = "SU��ʩ:" + (String)values.get("fv9SolutionSU");
					if (!"".equals((String)values.get("fv9SlDLDateSU"))) { 
						solution.solDate = DateUtil.getWeekOfYear((String)values.get("fv9SlDLDateSU")) + "/" +  
								((String)values.get("fv9SlDLDateSU")).substring(0, 4); 
					} else {
						solution.solDate = ""; 
					}
					solutionList.add(solution);
				}
				
				//PL��ʩ
				if ((String)values.get("fv9SolutionPL") != null && 
						!"".equals((String)values.get("fv9SolutionPL"))) {
					Solution solution = new Solution();
					solution.sol = "PL��ʩ:" + (String)values.get("fv9SolutionPL");
					if (!"".equals((String)values.get("fv9SlDLDatePL"))) { 
						solution.solDate = DateUtil.getWeekOfYear((String)values.get("fv9SlDLDatePL")) + "/" +  
								((String)values.get("fv9SlDLDatePL")).substring(0, 4); 
					} else {
						solution.solDate = ""; 
					}
					solutionList.add(solution);
				}
				
				//VSC��ʩ
				if ((String)values.get("fv9SolutionVSC") != null && 
						!"".equals((String)values.get("fv9SolutionVSC"))) {
					Solution solution = new Solution();
					solution.sol = "VSC��ʩ:" + (String)values.get("fv9SolutionVSC");
					if (!"".equals((String)values.get("fv9SlDLDateVSC"))) { 
						solution.solDate = DateUtil.getWeekOfYear((String)values.get("fv9SlDLDateVSC")) + "/" +  
								((String)values.get("fv9SlDLDateVSC")).substring(0, 4); 
					} else {
						solution.solDate = ""; 
					}
					solutionList.add(solution);
				}
				
				//LO��ʩ
				if ((String)values.get("fv9SolutionLO") != null && 
						!"".equals((String)values.get("fv9SolutionLO"))) {
					Solution solution = new Solution();
					solution.sol = "LO��ʩ:" + (String)values.get("fv9SolutionLO");
					if (!"".equals((String)values.get("fv9SlDLDateLO"))) { 
						solution.solDate = DateUtil.getWeekOfYear((String)values.get("fv9SlDLDateLO")) + "/" +  
								((String)values.get("fv9SlDLDateLO")).substring(0, 4); 
					} else {
						solution.solDate = ""; 
					}
					solutionList.add(solution);
				}
				
				//CP1-ME��ʩ
				if ((String)values.get("fv9SolutionCP1_ME") != null && 
						!"".equals((String)values.get("fv9SolutionCP1_ME"))) {
//			System.out.println("CP1-ME��ʩ");
					Solution solution = new Solution();
					solution.sol = "CP1-ME��ʩ:" + (String)values.get("fv9SolutionCP1_ME");
					if (!"".equals((String)values.get("fv9SlDLDateCP1_ME"))) { 
						solution.solDate = DateUtil.getWeekOfYear((String)values.get("fv9SlDLDateCP1_ME")) + "/" +  
								((String)values.get("fv9SlDLDateCP1_ME")).substring(0, 4); 
					} else {
						solution.solDate = ""; 
					}
					solutionList.add(solution);
				}
				
				//CP2-ME��ʩ
				if ((String)values.get("fv9SolutionCP2_ME") != null && 
						!"".equals((String)values.get("fv9SolutionCP2_ME"))) {
//			System.out.println("CP2-ME��ʩ");
					Solution solution = new Solution();
					solution.sol = "CP2-ME��ʩ:" + (String)values.get("fv9SolutionCP2_ME");
					if (!"".equals((String)values.get("fv9SlDLDateCP2_ME"))) { 
						solution.solDate = DateUtil.getWeekOfYear((String)values.get("fv9SlDLDateCP2_ME")) + "/" +  
								((String)values.get("fv9SlDLDateCP2_ME")).substring(0, 4); 
					} else {
						solution.solDate = ""; 
					}
					solutionList.add(solution);
				}
				
				//CP1-BS��ʩ
				if ((String)values.get("fv9SolutionBS") != null && 
						!"".equals((String)values.get("fv9SolutionBS"))) {
					Solution solution = new Solution();
					solution.sol = "CP1-BS��ʩ:" + (String)values.get("fv9SolutionBS");
					if (!"".equals((String)values.get("fv9SlDLDateBS"))) { 
						solution.solDate = DateUtil.getWeekOfYear((String)values.get("fv9SlDLDateBS")) + "/" +  
								((String)values.get("fv9SlDLDateBS")).substring(0, 4); 
					} else {
						solution.solDate = ""; 
					}
					solutionList.add(solution);
				}
				
				//CP1-PA��ʩ
				if ((String)values.get("fv9SolutionPA") != null && 
						!"".equals((String)values.get("fv9SolutionPA"))) {
					Solution solution = new Solution();
					solution.sol = "CP1-PA��ʩ:" + (String)values.get("fv9SolutionPA");
					if (!"".equals((String)values.get("fv9SlDLDatePA"))) { 
						solution.solDate = DateUtil.getWeekOfYear((String)values.get("fv9SlDLDatePA")) + "/" +  
								((String)values.get("fv9SlDLDatePA")).substring(0, 4); 
					} else {
						solution.solDate = ""; 
					}
					solutionList.add(solution);
				}

				//CP1-CA��ʩ
				if ((String)values.get("fv9SolutionCA") != null && 
						!"".equals((String)values.get("fv9SolutionCA"))) {
					Solution solution = new Solution();
					solution.sol = "CP1-CA��ʩ:" + (String)values.get("fv9SolutionCA");
					if (!"".equals((String)values.get("fv9SlDLDateCA"))) { 
						solution.solDate = DateUtil.getWeekOfYear((String)values.get("fv9SlDLDateCA")) + "/" +  
								((String)values.get("fv9SlDLDateCA")).substring(0, 4); 
					} else {
						solution.solDate = ""; 
					}
					solutionList.add(solution);
				}
				
				//CP2-BS��ʩ
				if ((String)values.get("fv9SolutionCP2BS") != null && 
						!"".equals((String)values.get("fv9SolutionCP2BS"))) {
					Solution solution = new Solution();
					solution.sol = "CP2-BS��ʩ:" + (String)values.get("fv9SolutionCP2BS");
					if (!"".equals((String)values.get("fv9SlDLDateCP2BS"))) { 
						solution.solDate = DateUtil.getWeekOfYear((String)values.get("fv9SlDLDateCP2BS")) + "/" +  
								((String)values.get("fv9SlDLDateCP2BS")).substring(0, 4); 
					} else {
						solution.solDate = ""; 
					}
					solutionList.add(solution);
				}
				
				//CP2-PA��ʩ
				if ((String)values.get("fv9SolutionCP2PA") != null && 
						!"".equals((String)values.get("fv9SolutionCP2PA"))) {
					Solution solution = new Solution();
					solution.sol = "CP2-PA��ʩ:" + (String)values.get("fv9SolutionCP2PA");
					if (!"".equals((String)values.get("fv9SlDLDateCP2PA"))) { 
						solution.solDate = DateUtil.getWeekOfYear((String)values.get("fv9SlDLDateCP2PA")) + "/" +  
								((String)values.get("fv9SlDLDateCP2PA")).substring(0, 4); 
					} else {
						solution.solDate = ""; 
					}
					solutionList.add(solution);
				}

				//CP2-CA��ʩ
				if ((String)values.get("fv9SolutionCP2CA") != null && 
						!"".equals((String)values.get("fv9SolutionCP2CA"))) {
					Solution solution = new Solution();
					solution.sol = "CP2-CA��ʩ:" + (String)values.get("fv9SolutionCP2CA");
					if (!"".equals((String)values.get("fv9SlDLDateCP2CA"))) { 
						solution.solDate = DateUtil.getWeekOfYear((String)values.get("fv9SlDLDateCP2CA")) + "/" +  
								((String)values.get("fv9SlDLDateCP2CA")).substring(0, 4); 
					} else {
						solution.solDate = ""; 
					}
					solutionList.add(solution);
				}
				
				if (solutionList != null && solutionList.size() > 0) {
					
					String lastSol = "";
					String lastSolDate = "";
					
					for (int index = 0; index < solutionList.size(); index++) {
						Solution tempSol = solutionList.get(index);
						if (index < 4) {
							HSSFRow tempRow = sheet.getRow(4+index*5);
							//��ʩ
							HSSFCell tempSolCell = tempRow.getCell(19);
							if (tempSolCell == null) {
								tempSolCell = tempRow.createCell(19);
							}
							tempSolCell.setCellValue(tempSol.sol); 
							//��ʩ�������
							HSSFCell tempSolDateCell = tempRow.getCell(53);
							if (tempSolDateCell == null) {
								tempSolDateCell = tempRow.createCell(53);
							}
							tempSolDateCell.setCellValue(tempSol.solDate); 
						} else {
							lastSol += tempSol.sol + "\r\n";
							lastSolDate += tempSol.solDate + "\r\n";
						}

					}
					
					//���ڵ����еĴ�ʩ
					if (!"".equals(lastSol)) {
						HSSFRow row24 = sheet.getRow(24);
						//���һ���ʩ
						HSSFCell tempSolCell = row24.getCell(19);
						if (tempSolCell == null) {
							tempSolCell = row24.createCell(19);
						}
						tempSolCell.setCellValue(lastSol); 
						//���һ���ʩ�������
						HSSFCell tempSolDateCell = row24.getCell(53);
						if (tempSolDateCell == null) {
							tempSolDateCell = row24.createCell(53);
						}
						tempSolDateCell.setCellValue(lastSolDate); 
					}
					
				}
				
				
				//ASW
				ImageCellInfo ASW = new ImageCellInfo(32, 3, 32, 3);
				ImageUtil.GenerateImage(wb, sheet, patri, ASW, ImageUtil.getASW());
				System.out.println("д��ͼƬ��ASW.jpg"); 
				
				//WZG-Erst
				ImageCellInfo WZGErst = new ImageCellInfo(32, 6, 32, 6);
				ImageUtil.GenerateImage(wb, sheet, patri, WZGErst, ImageUtil.getWZGErst());
				System.out.println("д��ͼƬ��WZGErst.jpg"); 
				
				//WZG-Verl
				ImageCellInfo WZGVerl = new ImageCellInfo(32, 9, 32, 9);
				ImageUtil.GenerateImage(wb, sheet, patri, WZGVerl, ImageUtil.getWZGVerl());
				System.out.println("д��ͼƬ��WZG-Verl.jpg"); 
				
				//SWZ-Teile
				ImageCellInfo SWZTeile = new ImageCellInfo(32, 12, 32, 12);
				ImageUtil.GenerateImage(wb, sheet, patri, SWZTeile, ImageUtil.getSWZTeile());
				System.out.println("д��ͼƬ��SWZ-Teile.jpg"); 
				
				//EMTAnl
				ImageCellInfo EMTAnl = new ImageCellInfo(32, 15, 32, 15);
				ImageUtil.GenerateImage(wb, sheet, patri, EMTAnl, ImageUtil.getEMTAnl());
				System.out.println("д��ͼƬ��EMTAnl.jpg"); 
				
				//Note3
				ImageCellInfo Note3 = new ImageCellInfo(32, 18, 32, 18);
				ImageUtil.GenerateImage(wb, sheet, patri, Note3, ImageUtil.getNote3());
				System.out.println("д��ͼƬ��Note3.jpg"); 
				
				//BMG
				ImageCellInfo BMG = new ImageCellInfo(32, 21, 32, 21);
				ImageUtil.GenerateImage(wb, sheet, patri, BMG, ImageUtil.getBMG());
				System.out.println("д��ͼƬ��BMG.jpg"); 
				
				//Note1
				ImageCellInfo Note1 = new ImageCellInfo(32, 24, 32, 24);
				ImageUtil.GenerateImage(wb, sheet, patri, Note1, ImageUtil.getNote1());
				System.out.println("д��ͼƬ��Note1.jpg"); 
				
				//д�����ź���̵�ͼƬ
				ImageCellInfo redCell = new ImageCellInfo(4, 75, 16, 75);
				ImageUtil.GenerateImage(wb, sheet, patri, redCell, ImageUtil.getRedLight());
				System.out.println("д����ͼƬ��redLight.jpg"); 
				
				ImageCellInfo yellowCell = new ImageCellInfo(4, 76, 16, 76);
				ImageUtil.GenerateImage(wb, sheet, patri, yellowCell, ImageUtil.getYellowLight());
				System.out.println("д��Ƶ�ͼƬ��yellowLight.jpg"); 
				
				ImageCellInfo greenCell = new ImageCellInfo(4, 77, 16, 77);
				ImageUtil.GenerateImage(wb, sheet, patri, greenCell, ImageUtil.getGreenLight());
				System.out.println("д���̵�ͼƬ��greenLight.jpg"); 
				
				//��ȡ������̱���ʱ���
				Date VFF = (Date)values.get("PH_VFF"); 
				Date PVS = (Date)values.get("PH_PVS"); 
				Date OS = (Date)values.get("PH_0S"); 
				Date SOP = (Date)values.get("PH_SOP"); 
				Date TBT_VFF = (Date)values.get("TBT_VFF"); 
				Date TBT_PVS = (Date)values.get("TBT_PVS"); 
				Date TBT_0S = (Date)values.get("TBT_0S"); 
				
				HashMap<String, Object> result = null;
				//����ʱ��㶼��ֵ����ʾ�ײ���ͼ�Σ�������ʾ
				if (VFF != null && PVS != null && OS != null && SOP != null && 
						TBT_VFF != null && TBT_PVS != null && TBT_0S != null) {
					
					//�������ʱ������ڵ��ꡢ��
					//���������53�ܣ�������-1
					int year_tbtvff = DateUtil.getYearByDate(TBT_VFF);
					int kw_tbtvff = DateUtil.getWeekOfYear2(TBT_VFF);
					if (kw_tbtvff == 53) {
						year_tbtvff -= 1;
					}
					
					int year_tbtpvs = DateUtil.getYearByDate(TBT_PVS);
					int kw_tbtpvs = DateUtil.getWeekOfYear2(TBT_PVS);
					if (kw_tbtpvs == 53) {
						year_tbtpvs -= 1;
					}
					
					int year_tbt0s = DateUtil.getYearByDate(TBT_0S);
					int kw_tbt0s = DateUtil.getWeekOfYear2(TBT_0S);
					if (kw_tbt0s == 53) {
						year_tbt0s -= 1;
					}
					
					int year_vff = DateUtil.getYearByDate(VFF);
					int kw_vff = DateUtil.getWeekOfYear2(VFF);
					if (kw_vff == 53) {
						year_vff -= 1;
					}
					
					int year_pvs = DateUtil.getYearByDate(PVS);
					int kw_pvs = DateUtil.getWeekOfYear2(PVS);
					if (kw_pvs == 53) {
						year_pvs -= 1;
					}
					
					int year_0s = DateUtil.getYearByDate(OS);
					int kw_0s = DateUtil.getWeekOfYear2(OS);
					if (kw_0s == 53) {
						year_0s -= 1;
					}
					
					int year_sop = DateUtil.getYearByDate(SOP); 
					int kw_sop = DateUtil.getWeekOfYear2(SOP);
					if (kw_sop == 53) {
						year_sop -= 1;
					}
					
					//����SOP���ں��������꣬����74�ܵ�����
					result = getKWArray(SOP, year_sop);
					int[] kws = (int[])result.get("kwnos"); 
					
					//J�кϲ���Ԫ��
					int begin = 1;
					if (Integer.parseInt(result.get("cols_before2") + "") > 0) {  
						sheet.addMergedRegion(new Region(31, (short)begin, 31, (short)(begin+Integer.parseInt(result.get("cols_before2") + "")-1)));   
						HSSFCell before2Cell = row31.getCell(begin);
						if (before2Cell == null) {
							before2Cell = row31.createCell(begin);
						}
						before2Cell.setCellValue(result.get("year_before2") + "");  
						begin += Integer.parseInt(result.get("cols_before2") + "");  
					}
					if (Integer.parseInt(result.get("cols_before1") + "") > 0) {  
						sheet.addMergedRegion(new Region(31, (short)begin, 31, (short)(begin+Integer.parseInt(result.get("cols_before1") + "")-1)));   
						
						HSSFCell before1Cell = row31.getCell(begin);
						if (before1Cell == null) {
							before1Cell = row31.createCell(begin);
						}
						before1Cell.setCellValue(result.get("year_before1") + "");  
						begin += Integer.parseInt(result.get("cols_before1") + "");  
					}
					if (Integer.parseInt(result.get("cols_current") + "") > 0) {  
						sheet.addMergedRegion(new Region(31, (short)begin, 31, (short)(begin+Integer.parseInt(result.get("cols_current") + "")-1)));   
						
						HSSFCell currentCell = row31.getCell(begin);
						if (currentCell == null) {
							currentCell = row31.createCell(begin);
						}
						currentCell.setCellValue(result.get("year_current") + "");  
						begin += Integer.parseInt(result.get("cols_current") + "");  
					}
					if (Integer.parseInt(result.get("cols_after") + "") > 0) {  
						sheet.addMergedRegion(new Region(31, (short)begin, 31, (short)(begin+Integer.parseInt(result.get("cols_after") + "")-1)));   
						
						HSSFCell afterCell = row31.getCell(begin);
						if (afterCell == null) {
							afterCell = row31.createCell(begin);
						}
						afterCell.setCellValue(result.get("year_after") + "");  
						begin += Integer.parseInt(result.get("cols_current") + "");  
					}
					
					//����Soll��TBT�͸�����̱���ʱ�����ڵ�Ԫ��
					/*
					 * ����˼·Ϊ��
					 * ����74�ܵĵ�һ�ܵ���һ�����ڣ�
					 * �ټ������ʱ��������ܵ���һ������
					 * ���������̱����һ��֮����������
					 * */
					//���ȼ����������еĵ�һ�ܵ���һ������
					int firstKW = kws[0];
					int firstYear = 0; //�������еĵ�һ��
					if (Integer.parseInt(result.get("cols_after") + "") > 0) {  
						firstYear = (Integer)result.get("year_after"); 
					}
					if (Integer.parseInt(result.get("cols_current") + "") > 0) {  
						firstYear = (Integer)result.get("year_current"); 
					}
					if (Integer.parseInt(result.get("cols_before1") + "") > 0) {  
						firstYear = (Integer)result.get("year_before1"); 
					}
					if (Integer.parseInt(result.get("cols_before2") + "") > 0) {  
						firstYear = (Integer)result.get("year_before2"); 
					}
					Date firstDate = DateUtil.getFirstDayOfWeek(firstYear, firstKW); //��һ�ܵ���һ����
					
					//�������ʱ��������ܵ���һ������
					Date mon_tbtvff = DateUtil.getFirstDayOfWeek(TBT_VFF); //TBTVFF�����ܵ���һ����
					Date mon_tbtpvs = DateUtil.getFirstDayOfWeek(TBT_PVS);//TBTPVS�����ܵ���һ����
					Date mon_tbt0s = DateUtil.getFirstDayOfWeek(TBT_0S);//TBT0S�����ܵ���һ����
					Date mon_vff = DateUtil.getFirstDayOfWeek(VFF);
					Date mon_pvs = DateUtil.getFirstDayOfWeek(PVS);
					Date mon_0s = DateUtil.getFirstDayOfWeek(OS);
					Date mon_sop = DateUtil.getFirstDayOfWeek(SOP);
					
					//�������ʱ������һ����������
					int vff = DateUtil.dateDiff(firstDate, mon_vff)/7;
					int pvs = DateUtil.dateDiff(firstDate, mon_pvs)/7;
					int os = DateUtil.dateDiff(firstDate, mon_0s)/7;
					int sop = DateUtil.dateDiff(firstDate, mon_sop)/7;
					int tbt_vff = DateUtil.dateDiff(firstDate, mon_tbtvff)/7;
					int tbt_pvs = DateUtil.dateDiff(firstDate, mon_tbtpvs)/7;
					int tbt_0s = DateUtil.dateDiff(firstDate, mon_tbt0s)/7;
					int tbt_sop = os + 8; //ϵͳ��û��SOP��TBTʱ�䣬Ĭ��Ϊ0S��ʼ��10��
					
					//ѭ��д��74��
					for (int i = 0; i < kws.length; i++) {
						HSSFCell kwCell = row36.getCell(i+1);
//				System.out.println("i = " + i);
						if (kwCell == null) {
							kwCell = row36.createCell(i+1);
						}
						if (i == tbt_vff) {
							ImageCellInfo vffCell = new ImageCellInfo(34, i+1, 34, i+1);
							ImageUtil.GenerateImage(wb, sheet, patri, vffCell, ImageUtil.getTBTVFF());
							System.out.println("д��ͼƬ��TBT-VFF.jpg"); 
						}
						if (i == vff) {
							ImageCellInfo vffCell = new ImageCellInfo(33, i, 33, tbt_pvs);
							ImageUtil.GenerateImage(wb, sheet, patri, vffCell, ImageUtil.getVFF());
							System.out.println("д��ͼƬ��VFF.jpg"); 
						}
						if (i == tbt_pvs) {
							ImageCellInfo pvsCell = new ImageCellInfo(34, i+1, 34, i+1);
							ImageUtil.GenerateImage(wb, sheet, patri, pvsCell, ImageUtil.getTBTPVS());
							System.out.println("д��ͼƬ��TBT-PVS.jpg"); 
						}
						if (i == pvs) {
							ImageCellInfo vffCell = new ImageCellInfo(33, i, 33, tbt_0s);
							ImageUtil.GenerateImage(wb, sheet, patri, vffCell, ImageUtil.getPVS());
							System.out.println("д��ͼƬ��PVS.jpg"); 
						}
						if (i == tbt_0s) {
							ImageCellInfo osCell = new ImageCellInfo(34, i+1, 34, i+1);
							ImageUtil.GenerateImage(wb, sheet, patri, osCell, ImageUtil.getTBT0S());
							System.out.println("д��ͼƬ��TBT-0S.jpg"); 
						}
						if (i == os) {
							ImageCellInfo vffCell = new ImageCellInfo(33, i, 33, os+6);
							ImageUtil.GenerateImage(wb, sheet, patri, vffCell, ImageUtil.get0S());
							System.out.println("д��ͼƬ��0S.jpg"); 
						}
						if (i == tbt_sop) {
							ImageCellInfo sopCell = new ImageCellInfo(34, i+1, 34, i+1);
							ImageUtil.GenerateImage(wb, sheet, patri, sopCell, ImageUtil.getTBTSOP());
							System.out.println("д��ͼƬ��TBT-SOP.jpg"); 
						}
						if (i == sop) {
							ImageCellInfo vffCell = new ImageCellInfo(33, i, 33, sop+6);
							ImageUtil.GenerateImage(wb, sheet, patri, vffCell, ImageUtil.getSOP());
							System.out.println("д��ͼƬ��SOP.jpg"); 
						}
						//д��TBT������ĸ
						if ((i==tbt_vff+1) || (i==tbt_pvs+1) || (i==tbt_0s+1) || (i==tbt_sop+1)) {
							HSSFCell vffCell = row34.getCell(i+1);
							if (vffCell == null) {
								vffCell = row34.createCell(i+1);
							}
							vffCell.setCellValue("T");
						}
						if ((i==tbt_vff+2) || (i==tbt_pvs+2) || (i==tbt_0s+2) || (i==tbt_sop+2)) {
							HSSFCell vffCell = row34.getCell(i+1);
							if (vffCell == null) {
								vffCell = row34.createCell(i+1);
							}
							vffCell.setCellValue("B");
						}
						if ((i==tbt_vff+3) || (i==tbt_pvs+3) || (i==tbt_0s+3) || (i==tbt_sop+3)) {
							HSSFCell vffCell = row34.getCell(i+1);
							if (vffCell == null) {
								vffCell = row34.createCell(i+1);
							}
							vffCell.setCellValue("T");
						}
						kwCell.setCellValue(kws[i] + ""); 
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new FawvmLoaderException(e.getMessage());
			}		


		}

		/*
		 * ����SOPʱ�����ʱ����
		 * ʱ���ᡪ����74�ܣ���SOP���12��Ϊ��ֹ��
		 */
		public static HashMap getKWArray(Date SOP, int year_sop) {
			int[] kwnos = new int[74];//ʱ��������
			
			int sop_days = DateUtil.getMaxWeekNumOfYear(year_sop); //sop�������������
			int sop_before1 = year_sop - 1; //SOPǰһ��
			int sop_before1_days = DateUtil.getMaxWeekNumOfYear(sop_before1); //sopǰһ���������
			int sop_before2 = year_sop - 2; //SOPǰ����
			int sop_before2_days = DateUtil.getMaxWeekNumOfYear(sop_before2); //sopǰ�����������

			int cols_current = 0; //sop�����������
			int cols_after = 0; //sop֮���������
			int cols_before1 = 0;// sopǰһ�������
			int cols_before2 = 0; //sopǰ���������
			
			int year_before1 = 0, year_before2 = 0, year_current = 0, year_after = 0;
			
			int kw_sop = DateUtil.getWeekOfYear2(SOP); 
			System.out.println("SOP�������� : " + kw_sop); 
			System.out.println("SOP��������������� " + sop_days); 
			
			//SOP��ǰ��
			int[] current = new int[kw_sop];
			for (int i=1; i<kw_sop+1; i++) {
				current[i-1] = i;
			}
			System.out.println("sop current length = " + current.length); 
			System.out.println("sop current = " + Arrays.toString(current)); 
			cols_current += kw_sop;
			year_current = year_sop;
			
			//sop֮���12��
			int[] aftersop = new int[12];
			if ((sop_days - kw_sop) > 12) {
				//sop֮���12��û�п���
				for (int i = 0; i < 12; i++) {
					aftersop[i] = kw_sop+1;
					kw_sop++;
				}
				cols_current += 12;
			} else {
				//sop֮���12�ܿ���
				int sep = sop_days - kw_sop; //��ǰ��SOP���ж�����
				for (int i=0; i<sep; i++) {
					aftersop[i] = kw_sop + 1;
					kw_sop++;
				}
				cols_current += sep;
				
				//����֮�������
				int last = 12 - sep;
				int begin = 1;
				for (int i=sep; i<12; i++) {
					aftersop[i] = begin;
					begin++;
				}
				cols_after += last;
				year_after = year_sop + 1;
			}
			System.out.println("aftersop length = " + aftersop.length); 
			System.out.println("aftersop = " + Arrays.toString(aftersop)); 
			
			//sopǰһ��
			int before = 74 - current.length - aftersop.length;
			int[] beforesop = new int[before];
			if (before > sop_before1_days) {
				//ʣ����������һ����
				cols_before1 = sop_before1_days;
				year_before1 = sop_before1;
				int before1 = before - sop_before1_days;
				for (int k = 0; k < sop_before1_days; k++) {
					beforesop[before1+k] = k+1;
				}
				
				cols_before2 += before1;
				year_before2 = sop_before2;
				for (int m = 0; m < before1; m++) {
					beforesop[m] = sop_before2_days - before1 + m + 1;
				}
				
			} else {
				for (int i = 0; i < before; i++) {
					beforesop[i] = sop_before1_days-before+i+1;
				}
				cols_before1 = before;
				year_before1 = sop_before1;
			}
			System.out.println("beforesop length = " + beforesop.length); 
			System.out.println("beforesop = " + Arrays.toString(beforesop)); 
			
			System.arraycopy(beforesop, 0, kwnos, 0, beforesop.length);
			System.arraycopy(current, 0, kwnos, beforesop.length, current.length);
			System.arraycopy(aftersop, 0, kwnos, beforesop.length + current.length, aftersop.length);
			
			System.out.println(Arrays.toString(kwnos));
			
			HashMap map = new HashMap<String, Object>();
			map.put("kwnos", kwnos); 
			map.put("cols_before1", cols_before1); 
			map.put("cols_before2", cols_before2); 
			map.put("cols_current", cols_current); 
			map.put("cols_after", cols_after); 
			
			map.put("year_before2", year_before2); 
			map.put("year_before1", year_before1); 
			map.put("year_current", year_current); 
			map.put("year_after", year_after); 
			
			return map;
		}
		
		
}
