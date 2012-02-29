package com.customer.fawvw.issues.commands.issueStatus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.customer.fawvw.issues.utils.ComponentUtils;
import com.customer.fawvw.issues.utils.FileUtil;
import com.customer.fawvw.issues.utils.NetUtil;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

public class CreateIssueExcel {

	
	public static final String TEMPLATE_FILE_PATH = System.getenv("TPR") + "\\plugins\\Template\\IssueStatusReport_Template.xls"; //$NON-NLS-1$ //$NON-NLS-2$
	
	public static Boolean createExcel(HashMap<String, Object> parameters, String path) throws Exception  {
		
		String server_ip = ComponentUtils.getPreferenceByName(
				(TCSession)parameters.get("session"), "server_ip"); //$NON-NLS-1$ //$NON-NLS-2$
		System.out.println("server_ip = " + server_ip);		 //$NON-NLS-1$

		//�������
		if (NetUtil.pingIP(server_ip)) {
			//�������ӳɹ�
			System.out.println("path = " + path); //$NON-NLS-1$
			System.out.println("parameters = " + parameters); //$NON-NLS-1$
			String user_id = ((TCSession)parameters.get("session")).getUser().getUserId(); //$NON-NLS-1$
			String project_id = (String)parameters.get("project_id"); //$NON-NLS-1$
			String remote_template = "\\\\" + server_ip + "\\TCData\\IssueTemplate\\" + user_id + "_IssueStatusReport_" + project_id + ".xls"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
			String remote_temp = "\\\\" + server_ip + "\\TCData\\IssueTemplate\\" + user_id + "_IssueStatusReport_" + project_id + Math.abs(new Random().nextInt()) + ".xls";  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	System.out.println("remote_template = " + remote_template); //$NON-NLS-1$
	System.out.println("remote_temp = " + remote_temp); //$NON-NLS-1$
			//�½�һ����ʱexcel
			FileOutputStream remoteFileOut = null;
			FileOutputStream localFileOut = null;
			
			try {
				
//				��ȡ�������ϵ�ģ��
				File remoteExcelTmp = new File(remote_template);//�洢�ļ�
				File remoteExcelTemp = new File(remote_temp);//��ʱ�ļ�
				InputStream inputStream = new FileInputStream(remoteExcelTmp);
				remoteFileOut = new FileOutputStream(remoteExcelTemp);
				localFileOut = new FileOutputStream(new File(path));
				
				writeExcel(parameters, inputStream, remoteFileOut, localFileOut);
				
				FileUtil.delFile(remote_template);
				
				FileUtil.renameFile(remoteExcelTemp, remote_template);
				
				return true;

			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
				//���������޴�ģ�壬�ڱ��ػ�ȡģ��
				try {
					
					File localExcelTmp = new File(TEMPLATE_FILE_PATH);
					InputStream inputStream = new FileInputStream(localExcelTmp);
					remoteFileOut = new FileOutputStream(new File(remote_template));
					localFileOut = new FileOutputStream(new File(path));
					
					writeExcel(parameters, inputStream, remoteFileOut, localFileOut);
					
					return true;
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					MessageBox.post("Excelģ�岻����", 
							"����㱨����", 
							MessageBox.ERROR);
					
					return false;
				}
				
			} catch (Exception e) {

				e.printStackTrace();
				
				return false;
				
			} finally {
				try {
					remoteFileOut.close();
					localFileOut.close();
					//ɾ����ʱ�ļ�
					FileUtil.delFile(remote_temp);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} else {
			//������������
			MessageBox.post("�޷����ʣ���������", 
					"����㱨����", 
					MessageBox.ERROR);
			return false;
		}
		
		
	}
	
	public static void writeExcel(HashMap<String, Object> parameters, InputStream inputStream,
			FileOutputStream remoteFileOut, FileOutputStream localFileOut) throws Exception{
		
		try {
			POIFSFileSystem fs = new POIFSFileSystem(inputStream);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			
			HashMap<String, Object> values = IssueStatusReportLoader.load(parameters);
			
			HSSFSheet sheetPage1 = workbook.getSheetAt(0);
			HSSFSheet sheetPage2 = workbook.getSheetAt(1);
			
			StatusWrite.importDataPage(workbook, sheetPage1, values, Integer.parseInt((String)parameters.get("forecast"))); //$NON-NLS-1$
			AssPlacementWrite.importDataPage(workbook, sheetPage2, values);
			
			workbook.write(remoteFileOut);
			workbook.write(localFileOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
