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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

public class CreateIssueSingleExcel {

	public static final String TEMPLATE_FILE_PATH = System.getenv("TPR") + "\\plugins\\Template\\IssueSingleReport_Template.xls"; //$NON-NLS-1$ //$NON-NLS-2$

	public static final String LOGO_FILE_PATH = "/xls/logo.JPG"; //$NON-NLS-1$

	private static final float PX_DEFAULT = 32.00f;
	
	private static final float PX_MODIFIED = 36.56f;

	private static final int PX_ROW = 15;

	public static void createExcel(InterfaceAIFComponent targetcompontent, 
			String file, TCSession session)	throws Exception{
		
		System.out.println("ģ������λ�ã�" + TEMPLATE_FILE_PATH);  //$NON-NLS-1$
		
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
			
			System.out.println("��ȡģ��ɹ�"); //$NON-NLS-1$
			
			importData(workbook, sheet, patri, targetcompontent, session);
			
			workbook.write(fileOut);
			
			System.out.println("��������ɹ�"); //$NON-NLS-1$
			
		} catch (FileNotFoundException e) {
			
			System.out.println("Excelģ�岻����"); //$NON-NLS-1$
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
				System.out.println("��ʼ��ȡ����"); //$NON-NLS-1$
				Map<String, Object> values = ReportIssueSingleLoader.load(targetcompontent, session);
				System.out.println("��ȡ���ݽ���"); //$NON-NLS-1$
				
				System.out.println("��ʼд�뱨��"); //$NON-NLS-1$
				
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
				HSSFRow row10 = sheet.getRow(10);
				HSSFRow row16 = sheet.getRow(16);
				HSSFRow row22 = sheet.getRow(22);
				HSSFRow row28 = sheet.getRow(28);
				HSSFRow row34 = sheet.getRow(34);
				HSSFRow row35 = sheet.getRow(35);
				HSSFRow row36 = sheet.getRow(36);
				HSSFRow row38 = sheet.getRow(38);
				
				//Bauteil
				HSSFCell bauteilCell = row0.getCell(4);
				if (bauteilCell == null) {
					bauteilCell = row0.createCell(4);
				}
				bauteilCell.setCellValue((String)values.get("fv9PartNumber")); //$NON-NLS-1$
				bauteilCell.setCellStyle(cellStyle);
				
				//Lifeerant
				HSSFCell lifeerantCell = row0.getCell(55);
				if (lifeerantCell == null) {
					lifeerantCell = row0.createCell(0);
				}
				lifeerantCell.setCellValue((String)values.get("fv9SupplierID")); //$NON-NLS-1$
				lifeerantCell.setCellStyle(cellStyle);
				
				//Zustaendig
				HSSFCell zustaendigCell = row1.getCell(5);
				if (zustaendigCell == null) {
					zustaendigCell = row1.createCell(5);
				}
				zustaendigCell.setCellValue((String)values.get("fv9PartName")); //$NON-NLS-1$
				zustaendigCell.setCellStyle(cellStyle);
				
				//��Ӧ������
				HSSFCell supplyCell = row1.getCell(49);
				if (supplyCell == null) {
					supplyCell = row1.createCell(49);
				}
				supplyCell.setCellValue((String)values.get("fv9SupplierName")); //$NON-NLS-1$
				supplyCell.setCellStyle(cellCenterStyle);
				
				//��������
				HSSFCell descCell = row4.getCell(0);
				if (descCell == null) {
					descCell = row4.createCell(0);
				}
				descCell.setCellValue((String)values.get("fv9IssueDesc")); //$NON-NLS-1$
				descCell.setCellStyle(cellTopStyle);
				
				//��������ͼƬ	
				if (((HashMap)values.get("problemImage")).containsKey("FV9DescPhoto")) { //$NON-NLS-1$ //$NON-NLS-2$
					HSSFClientAnchor anchor = new HSSFClientAnchor();
					Map<String, Object> problemImage = (Map<String, Object>)values.get("problemImage"); //$NON-NLS-1$
					BufferedImage imageBuffer = (BufferedImage)problemImage.get("FV9DescPhoto"); //$NON-NLS-1$
					byte[] imageValue = ImageUtil.getImgValue((BufferedImage)(problemImage.get("FV9DescPhoto"))); //$NON-NLS-1$
	
					HSSFPicture picture = patri.createPicture(anchor, wb.addPicture(
							imageValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					ImageCellInfo problemCellnfo = new ImageCellInfo(15, 0, 33, 18);	
					ImageUtil.GenerateImage(wb, sheet, patri, problemCellnfo, imageBuffer);
					System.out.println("д����������ͼƬ"); //$NON-NLS-1$
				} 
				
				//��ʩ1
				HSSFCell solCell1 = row4.getCell(19);
				if (solCell1 == null) {
					solCell1 = row4.createCell(19);
				}
				solCell1.setCellValue((String)values.get("fv9Solution1")); //$NON-NLS-1$
				solCell1.setCellStyle(cellTopStyle);
				
				//��ʩ1�������
				HSSFCell solTerminCell1 = row4.getCell(53);
				if (solTerminCell1 == null) {
					solTerminCell1 = row4.createCell(53);
				}
				if (!"".equals((String)values.get("fv9SlDLDate1"))) { //$NON-NLS-1$ //$NON-NLS-2$
					solTerminCell1.setCellValue(DateUtil.getWeekOfYear((String)values.get("fv9SlDLDate1")) + "/" +  //$NON-NLS-1$ //$NON-NLS-2$
							((String)values.get("fv9SlDLDate1")).substring(0, 4)); //$NON-NLS-1$
				} else {
					solTerminCell1.setCellValue(""); //$NON-NLS-1$
				}
				
				solTerminCell1.setCellStyle(cellTopStyle);
				
				//��ʩ2
				HSSFCell solCell2 = row10.getCell(19);
				if (solCell2 == null) {
					solCell2 = row10.createCell(19);
				}
				solCell2.setCellValue((String)values.get("fv9Solution2")); //$NON-NLS-1$
				solCell2.setCellStyle(cellTopStyle);
				
				//��ʩ2�������
				HSSFCell solTerminCell2 = row10.getCell(53);
				if (solTerminCell2 == null) {
					solTerminCell2 = row10.createCell(53);
				}
				if (!"".equals((String)values.get("fv9SlDLDate2"))) { //$NON-NLS-1$ //$NON-NLS-2$
					solTerminCell2.setCellValue(DateUtil.getWeekOfYear((String)values.get("fv9SlDLDate2")) + "/" +  //$NON-NLS-1$ //$NON-NLS-2$
							((String)values.get("fv9SlDLDate2")).substring(0, 4)); //$NON-NLS-1$
				} else {
					solTerminCell2.setCellValue(""); //$NON-NLS-1$
				}
				solTerminCell2.setCellStyle(cellTopStyle);
				
				//��ʩ3
				HSSFCell solCell3 = row16.getCell(19);
				if (solCell3 == null) {
					solCell3 = row16.createCell(19);
				}
				solCell3.setCellValue((String)values.get("fv9Solution3")); //$NON-NLS-1$
				solCell3.setCellStyle(cellTopStyle);
				
				//��ʩ3�������
				HSSFCell solTerminCell3 = row16.getCell(53);
				if (solTerminCell3 == null) {
					solTerminCell3 = row16.createCell(53);
				}
				if (!"".equals((String)values.get("fv9SlDLDate3"))) { //$NON-NLS-1$ //$NON-NLS-2$
					solTerminCell3.setCellValue(DateUtil.getWeekOfYear((String)values.get("fv9SlDLDate3")) + "/" +  //$NON-NLS-1$ //$NON-NLS-2$
							((String)values.get("fv9SlDLDate3")).substring(0, 4)); //$NON-NLS-1$
				} else {
					solTerminCell3.setCellValue(""); //$NON-NLS-1$
				}
				solTerminCell3.setCellStyle(cellTopStyle);
				
				//��ʩ4
				HSSFCell solCell4 = row22.getCell(19);
				if (solCell4 == null) {
					solCell4 = row22.createCell(19);
				}
				solCell4.setCellValue((String)values.get("fv9Solution4")); //$NON-NLS-1$
				solCell4.setCellStyle(cellTopStyle);
				
				//��ʩ4�������
				HSSFCell solTerminCell4 = row22.getCell(53);
				if (solTerminCell4 == null) {
					solTerminCell4 = row22.createCell(53);
				}
				if (!"".equals((String)values.get("fv9SlDLDate4"))) { //$NON-NLS-1$ //$NON-NLS-2$
					solTerminCell4.setCellValue(DateUtil.getWeekOfYear((String)values.get("fv9SlDLDate4")) + "/" +  //$NON-NLS-1$ //$NON-NLS-2$
							((String)values.get("fv9SlDLDate4")).substring(0, 4)); //$NON-NLS-1$
				} else {
					solTerminCell4.setCellValue(""); //$NON-NLS-1$
				}
				solTerminCell4.setCellStyle(cellTopStyle);
				
				//��ʩ5
				HSSFCell solCell5 = row28.getCell(19);
				if (solCell5 == null) {
					solCell5 = row28.createCell(19);
				}
				solCell5.setCellValue((String)values.get("fv9Solution5")); //$NON-NLS-1$
				solCell5.setCellStyle(cellTopStyle);
				
				//��ʩ5�������
				HSSFCell solTerminCell5 = row28.getCell(53);
				if (solTerminCell5 == null) {
					solTerminCell5 = row28.createCell(53);
				}
				if (!"".equals((String)values.get("fv9SlDLDate5"))) { //$NON-NLS-1$ //$NON-NLS-2$
					solTerminCell5.setCellValue(DateUtil.getWeekOfYear((String)values.get("fv9SlDLDate5")) + "/" +  //$NON-NLS-1$ //$NON-NLS-2$
							((String)values.get("fv9SlDLDate5")).substring(0, 4)); //$NON-NLS-1$
				} else {
					solTerminCell5.setCellValue(""); //$NON-NLS-1$
				}
				solTerminCell5.setCellStyle(cellTopStyle);
				
				//ASW
				ImageCellInfo ASW = new ImageCellInfo(35, 3, 35, 3);
				ImageUtil.GenerateImage(wb, sheet, patri, ASW, ImageUtil.getASW());
				System.out.println("д��ͼƬ��ASW.jpg"); //$NON-NLS-1$
				
				//WZG-Erst
				ImageCellInfo WZGErst = new ImageCellInfo(35, 6, 35, 6);
				ImageUtil.GenerateImage(wb, sheet, patri, WZGErst, ImageUtil.getWZGErst());
				System.out.println("д��ͼƬ��WZGErst.jpg"); //$NON-NLS-1$
				
				//WZG-Verl
				ImageCellInfo WZGVerl = new ImageCellInfo(35, 9, 35, 9);
				ImageUtil.GenerateImage(wb, sheet, patri, WZGVerl, ImageUtil.getWZGVerl());
				System.out.println("д��ͼƬ��WZG-Verl.jpg"); //$NON-NLS-1$
				
				//SWZ-Teile
				ImageCellInfo SWZTeile = new ImageCellInfo(35, 12, 35, 12);
				ImageUtil.GenerateImage(wb, sheet, patri, SWZTeile, ImageUtil.getSWZTeile());
				System.out.println("д��ͼƬ��SWZ-Teile.jpg"); //$NON-NLS-1$
				
				//EMTAnl
				ImageCellInfo EMTAnl = new ImageCellInfo(35, 15, 35, 15);
				ImageUtil.GenerateImage(wb, sheet, patri, EMTAnl, ImageUtil.getEMTAnl());
				System.out.println("д��ͼƬ��EMTAnl.jpg"); //$NON-NLS-1$
				
				//Note3
				ImageCellInfo Note3 = new ImageCellInfo(35, 18, 35, 18);
				ImageUtil.GenerateImage(wb, sheet, patri, Note3, ImageUtil.getNote3());
				System.out.println("д��ͼƬ��Note3.jpg"); //$NON-NLS-1$
				
				//BMG
				ImageCellInfo BMG = new ImageCellInfo(35, 21, 35, 21);
				ImageUtil.GenerateImage(wb, sheet, patri, BMG, ImageUtil.getBMG());
				System.out.println("д��ͼƬ��BMG.jpg"); //$NON-NLS-1$
				
				//Note1
				ImageCellInfo Note1 = new ImageCellInfo(35, 24, 35, 24);
				ImageUtil.GenerateImage(wb, sheet, patri, Note1, ImageUtil.getNote1());
				System.out.println("д��ͼƬ��Note1.jpg"); //$NON-NLS-1$
				
				Date SOP = (Date)values.get("PH_SOP"); //$NON-NLS-1$
				Date TBT_VFF = (Date)values.get("TBT_VFF"); //$NON-NLS-1$
				Date TBT_PVS = (Date)values.get("TBT_PVS"); //$NON-NLS-1$
				Date TBT_0S = (Date)values.get("TBT_0S"); //$NON-NLS-1$
				
				HashMap<String, Object> result = null;
				if (SOP != null && TBT_VFF != null && TBT_PVS != null && TBT_0S != null) {
					Calendar cc = Calendar.getInstance();
					int sop_year = cc.get(Calendar.YEAR); //SOP������
					result = getKWArray(SOP, sop_year);

					int[] kws = (int[])result.get("kwnos"); //$NON-NLS-1$
					
					int year_vff = cc.get(Calendar.YEAR);
					int kw_vff = DateUtil.getWeekOfYear2(TBT_VFF);
					
					int year_pvs = cc.get(Calendar.YEAR);
					int kw_pvs = DateUtil.getWeekOfYear2(TBT_PVS);
					
					int year_0s = cc.get(Calendar.YEAR);
					int kw_0s = DateUtil.getWeekOfYear2(TBT_0S);
					
					//J�кϲ���Ԫ��
					int begin = 1;
					if (Integer.parseInt(result.get("cols_before2") + "") > 0) { //$NON-NLS-1$ //$NON-NLS-2$
						sheet.addMergedRegion(new Region(34, (short)begin, 34, (short)(begin+Integer.parseInt(result.get("cols_before2") + "")-1)));  //$NON-NLS-1$ //$NON-NLS-2$
						HSSFCell before2Cell = row34.getCell(begin);
						if (before2Cell == null) {
							before2Cell = row34.createCell(begin);
						}
						before2Cell.setCellValue(result.get("year_before2") + ""); //$NON-NLS-1$ //$NON-NLS-2$
						begin += Integer.parseInt(result.get("cols_before2") + ""); //$NON-NLS-1$ //$NON-NLS-2$
					}
					if (Integer.parseInt(result.get("cols_before1") + "") > 0) { //$NON-NLS-1$ //$NON-NLS-2$
						sheet.addMergedRegion(new Region(34, (short)begin, 34, (short)(begin+Integer.parseInt(result.get("cols_before1") + "")-1)));  //$NON-NLS-1$ //$NON-NLS-2$
						
						HSSFCell before1Cell = row34.getCell(begin);
						if (before1Cell == null) {
							before1Cell = row34.createCell(begin);
						}
						before1Cell.setCellValue(result.get("year_before1") + ""); //$NON-NLS-1$ //$NON-NLS-2$
						begin += Integer.parseInt(result.get("cols_before1") + ""); //$NON-NLS-1$ //$NON-NLS-2$
					}
					if (Integer.parseInt(result.get("cols_current") + "") > 0) { //$NON-NLS-1$ //$NON-NLS-2$
						sheet.addMergedRegion(new Region(34, (short)begin, 34, (short)(begin+Integer.parseInt(result.get("cols_current") + "")-1)));  //$NON-NLS-1$ //$NON-NLS-2$
						
						HSSFCell currentCell = row34.getCell(begin);
						if (currentCell == null) {
							currentCell = row34.createCell(begin);
						}
						currentCell.setCellValue(result.get("year_current") + ""); //$NON-NLS-1$ //$NON-NLS-2$
						begin += Integer.parseInt(result.get("cols_current") + ""); //$NON-NLS-1$ //$NON-NLS-2$
					}
					if (Integer.parseInt(result.get("cols_after") + "") > 0) { //$NON-NLS-1$ //$NON-NLS-2$
						sheet.addMergedRegion(new Region(34, (short)begin, 34, (short)(begin+Integer.parseInt(result.get("cols_after") + "")-1)));  //$NON-NLS-1$ //$NON-NLS-2$
						
						HSSFCell afterCell = row34.getCell(begin);
						if (afterCell == null) {
							afterCell = row34.createCell(begin);
						}
						afterCell.setCellValue(result.get("year_after") + ""); //$NON-NLS-1$ //$NON-NLS-2$
						begin += Integer.parseInt(result.get("cols_current") + ""); //$NON-NLS-1$ //$NON-NLS-2$
					}
					
					//����Soll��TBT��ʱ�����ڵ�Ԫ��
					//���ȼ����������еĵ�һ�ܵ���һ������
					int firstKW = kws[0];
					int firstYear = 0; //�������еĵ�һ��
					
					if (Integer.parseInt(result.get("cols_after") + "") > 0) { //$NON-NLS-1$ //$NON-NLS-2$
						firstYear = (Integer)result.get("year_after"); //$NON-NLS-1$
					}
					if (Integer.parseInt(result.get("cols_current") + "") > 0) { //$NON-NLS-1$ //$NON-NLS-2$
						firstYear = (Integer)result.get("year_current"); //$NON-NLS-1$
					}
					if (Integer.parseInt(result.get("cols_before1") + "") > 0) { //$NON-NLS-1$ //$NON-NLS-2$
						firstYear = (Integer)result.get("year_before1"); //$NON-NLS-1$
					}
					if (Integer.parseInt(result.get("cols_before2") + "") > 0) { //$NON-NLS-1$ //$NON-NLS-2$
						firstYear = (Integer)result.get("year_before2"); //$NON-NLS-1$
					}
					Date firstDate = DateUtil.getFirstDayOfWeek(firstYear, firstKW); //��һ�ܵ���һ����
					
					//VFF/PVS/0S TBT�����ܵ���һ������
					Date mon_vff = DateUtil.getFirstDayOfWeek(year_vff, kw_vff); //TBTVFF�����ܵ���һ����
					Date mon_pvs = DateUtil.getFirstDayOfWeek(year_pvs, kw_pvs);//TBTPVS�����ܵ���һ����
					Date mon_0s = DateUtil.getFirstDayOfWeek(year_0s, kw_0s);//TBT0S�����ܵ���һ����
					
					int sep_vff = DateUtil.dateDiff(firstDate, mon_vff)/7;
					int sep_pvs = DateUtil.dateDiff(firstDate, mon_pvs)/7;
					int sep_0s = DateUtil.dateDiff(firstDate, mon_0s)/7;
					
					//ѭ��д��74��
					for (int i = 0; i < kws.length; i++) {
						HSSFCell kwCell = row38.getCell(i+1);
						if (kwCell == null) {
							kwCell = row38.createCell(i+1);
						}
						if (i == sep_vff) {
//							HSSFCell vffCell = row36.getCell(i+1);
//							if (vffCell == null) {
//								vffCell = row36.createCell(i+1);
//							}
//							vffCell.setCellValue("VFF");
							ImageCellInfo vffCell = new ImageCellInfo(36, i+1, 36, i+1);
							ImageUtil.GenerateImage(wb, sheet, patri, vffCell, ImageUtil.getTBTVFF());
							System.out.println("д��ͼƬ��TBT-VFF.jpg"); //$NON-NLS-1$
						}
						if (i == sep_pvs) {
//							HSSFCell pvsCell = row36.getCell(i+1);
//							if (pvsCell == null) {
//								pvsCell = row36.createCell(i+1);
//							}
//							pvsCell.setCellValue("PVS");
							ImageCellInfo pvsCell = new ImageCellInfo(36, i+1, 36, i+1);
							ImageUtil.GenerateImage(wb, sheet, patri, pvsCell, ImageUtil.getTBTPVS());
							System.out.println("д��ͼƬ��TBT-PVS.jpg"); //$NON-NLS-1$
						}
						if (i == sep_0s) {
//							HSSFCell osCell = row36.getCell(i+1);
//							if (osCell == null) {
//								osCell = row36.createCell(i+1);
//							}
//							osCell.setCellValue("0S");
							ImageCellInfo osCell = new ImageCellInfo(36, i+1, 36, i+1);
							ImageUtil.GenerateImage(wb, sheet, patri, osCell, ImageUtil.getTBT0S());
							System.out.println("д��ͼƬ��TBT-0S.jpg"); //$NON-NLS-1$
						}
						kwCell.setCellValue(kws[i] + ""); //$NON-NLS-1$
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new FawvmLoaderException(e.getMessage());
			}		


		}

		/*
		 * ����SOPʱ�����ʱ����
		 * ʱ���ᡪ����74�ܣ���SOP���12��Ϊ��ֹ��
		 */
		public static HashMap getKWArray(Date SOP, int sop_year) {
			int[] kwnos = new int[74];//ʱ��������
			
			int sop_days = DateUtil.getMaxWeekNumOfYear(sop_year); //sop�������������
			int sop_before1 = sop_year - 1; //SOPǰһ��
			int sop_before1_days = DateUtil.getMaxWeekNumOfYear(sop_before1); //sopǰһ���������
			int sop_before2 = sop_year - 2; //SOPǰ����
			int sop_before2_days = DateUtil.getMaxWeekNumOfYear(sop_before2); //sopǰ�����������

			int cols_current = 0; //sop�����������
			int cols_after = 0; //sop֮���������
			int cols_before1 = 0;// sopǰһ�������
			int cols_before2 = 0; //sopǰ���������
			
			int year_before1 = 0, year_before2 = 0, year_current = 0, year_after = 0;
			
			int kw_sop = DateUtil.getWeekOfYear2(SOP); 
			System.out.println("SOP�������� : " + kw_sop); //$NON-NLS-1$
			System.out.println("SOP��������������� " + sop_days); //$NON-NLS-1$
			
			//SOP��ǰ��
			int[] current = new int[kw_sop];
			for (int i=1; i<kw_sop+1; i++) {
				current[i-1] = i;
			}
			System.out.println("sop current length = " + current.length); //$NON-NLS-1$
			System.out.println("sop current = " + Arrays.toString(current)); //$NON-NLS-1$
			cols_current += kw_sop;
			year_current = sop_year;
			
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
				year_after = sop_year + 1;
			}
			System.out.println("aftersop length = " + aftersop.length); //$NON-NLS-1$
			System.out.println("aftersop = " + Arrays.toString(aftersop)); //$NON-NLS-1$
			
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
			System.out.println("beforesop length = " + beforesop.length); //$NON-NLS-1$
			System.out.println("beforesop = " + Arrays.toString(beforesop)); //$NON-NLS-1$
			
			System.arraycopy(beforesop, 0, kwnos, 0, beforesop.length);
			System.arraycopy(current, 0, kwnos, beforesop.length, current.length);
			System.arraycopy(aftersop, 0, kwnos, beforesop.length + current.length, aftersop.length);
			
			System.out.println(Arrays.toString(kwnos));
			
			HashMap map = new HashMap<String, Object>();
			map.put("kwnos", kwnos); //$NON-NLS-1$
			map.put("cols_before1", cols_before1); //$NON-NLS-1$
			map.put("cols_before2", cols_before2); //$NON-NLS-1$
			map.put("cols_current", cols_current); //$NON-NLS-1$
			map.put("cols_after", cols_after); //$NON-NLS-1$
			
			map.put("year_before2", year_before2); //$NON-NLS-1$
			map.put("year_before1", year_before1); //$NON-NLS-1$
			map.put("year_current", year_current); //$NON-NLS-1$
			map.put("year_after", year_after); //$NON-NLS-1$
			
			return map;
		}
}
