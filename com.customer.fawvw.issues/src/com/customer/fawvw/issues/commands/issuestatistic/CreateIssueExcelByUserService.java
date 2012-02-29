package com.customer.fawvw.issues.commands.issuestatistic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.customer.fawvw.issues.exception.FawvmLoaderException;
import com.teamcenter.rac.kernel.TCComponentProject;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;

public class CreateIssueExcelByUserService {
	
	public static final String TEMPLATE_FILE_PATH = System.getenv("TPR") + "\\plugins\\Template\\ProblemReport_Template.xls";  //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-2$
	private static final float PX_DEFAULT = 32.00f;
	private static final float PX_MODIFIED = 36.56f;
	private static final int PX_ROW = 15;
	
	public static void createExcel(String projectName, String file, File txt,
			HashMap<String, Object> parameters) throws Exception {
		
		FileOutputStream fileOut = null;

		try {
			fileOut = new FileOutputStream(new File(file));

			File excelTmp = new File(TEMPLATE_FILE_PATH);
			InputStream inputStream = new FileInputStream(excelTmp);
			System.out.println("" + TEMPLATE_FILE_PATH); 	 //$NON-NLS-1$
			System.out.println(""); //$NON-NLS-1$
			
			POIFSFileSystem fs = new POIFSFileSystem(inputStream);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			
			HashMap<String, Object> values = IssueReportLoaderUserSevice.loadTxtData(txt);

			System.out.println("��ʼд�룺�����б�ҳ"); //$NON-NLS-1$
			HSSFSheet sheetPage1 = workbook.getSheetAt(0);
			IssuesReportWrite.importDataPage(workbook, sheetPage1, values, parameters);
			
			System.out.println("��ʼд�룺������ͳ�Ʊ���"); //$NON-NLS-1$
			HSSFSheet sheetPage2 = workbook.getSheetAt(1);
			DepartmentStatusWrite.importDataPage(workbook, sheetPage2, values);
			
			System.out.println("��ʼд�룺��רҵ��ͳ�Ʊ���");			 //$NON-NLS-1$
			HSSFSheet sheetPage3 = workbook.getSheetAt(2);
			MajorWrite.importDataPage(workbook, sheetPage3, values);

			System.out.println("��ʼд�룺��ʱ��ͳ�Ʊ���");			 //$NON-NLS-1$
			HSSFSheet sheetPage4 = workbook.getSheetAt(3);
			TimeWrite.importDataPage(workbook, sheetPage4, values);

//			System.out.println(Messages.WriteIssueExcel_write_assplacement);			
//			HSSFSheet sheetPage5 = workbook.getSheetAt(4);
//			AssPlacementWrite.importDataPage(workbook, sheetPage5, values);

//			System.out.println(Messages.WriteIssueExcel_write_assplacement_new);			
//			HSSFSheet sheetPage6 = workbook.getSheetAt(5);
//			AssPlacementWriteNew.importDataPage(workbook, sheetPage6, values);

			workbook.write(fileOut);
			
			System.out.println(""); //$NON-NLS-1$
			
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
	}
	
	public static Map loadTxtData() {
		Map<String, Object> values = new HashMap<String, Object>();
		
		String txtPath = System.getenv("TEMP"); //$NON-NLS-1$
		txtPath += "\\issueReportTemp.txt"; //$NON-NLS-1$
		
		return values;
	}
	
}
