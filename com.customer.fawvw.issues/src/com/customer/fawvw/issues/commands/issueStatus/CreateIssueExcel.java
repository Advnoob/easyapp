package com.customer.fawvw.issues.commands.issueStatus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.customer.fawvw.issues.utils.ComponentUtils;
import com.customer.fawvw.issues.utils.DateUtil;
import com.customer.fawvw.issues.utils.NetUtil;
import com.customer.fawvw.issues.utils.TextUtils;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

public class CreateIssueExcel {

	
	public static final String TEMPLATE_FILE_PATH1 = System.getenv("TPR") + "\\plugins\\Template\\IssueStatusReport_Template1.xls"; 
	public static final String TEMPLATE_FILE_PATH2 = System.getenv("TPR") + "\\plugins\\Template\\IssueStatusReport_Template2.xls"; 
	
	public static Boolean createExcel(HashMap<String, Object> parameters, 
			String path1, String path2, String project_id) throws Exception  {
		
		String server_ip = ComponentUtils.getPreferenceByName(
				(TCSession)parameters.get("session"), "server_ip");

		//�������
		if (NetUtil.pingIP(server_ip)) {
			//�������ӳɹ�
			String user_id = ((TCSession)parameters.get("session")).getUser().getUserId(); 
			String remote_template = "\\\\" + server_ip + "\\TCData\\IssueTemplate\\" + user_id + "_IssueStatusReport_" + project_id + ".txt";  
			
			FileOutputStream localFileOut1 = null; //��һ�ű���
			FileOutputStream localFileOut2 = null; //�ڶ��ű���
			String content = "";
			
			//Զ�̴洢��TXT�ļ�
			File remoteTxtTmp = new File(remote_template);
			if (!remoteTxtTmp.exists()) {
				TextUtils.creatTxtFile(remoteTxtTmp);
			} else {
				content = TextUtils.readTxtFile(remoteTxtTmp);
			}
			
			//��ǰ����+��ǰ����+Ԥ������+��������
			ArrayList<IssueStatus> statusList = new ArrayList<IssueStatus>(); //�µ�����
			
			//��ѯ������
			HashMap<String, Object> values = IssueStatusReportLoader.load(parameters);
			
			HashMap<String, Object> statusMap = (HashMap<String, Object>)values.get("status");
			IssueStatus current = new IssueStatus();
			current.kw = (String)parameters.get("week");
			current.red = (Integer)((HashMap<String, Object>)values.get("status")).get("red");
			current.yellow = (Integer)((HashMap<String, Object>)values.get("status")).get("yellow");
			current.green = (Integer)((HashMap<String, Object>)values.get("status")).get("green");
			current.sum = (Integer)((HashMap<String, Object>)values.get("status")).get("red") + 
							(Integer)((HashMap<String, Object>)values.get("status")).get("yellow") + 
							(Integer)((HashMap<String, Object>)values.get("status")).get("green");
			
			//���������ݸ��µ�txt��
			String newContent = current.kw + "/" + current.red + "/" + current.yellow + "/" + current.green + "/" + current.sum + ",";
			//�����µ��ַ���
			String beforeAndCurrent = TextUtils.writeTxtFile(remoteTxtTmp, newContent, content); 
			
			//�����ǰ������
			ArrayList<IssueStatus> beforeList = new ArrayList<IssueStatus>();
			beforeList = IssueStatus.getBeforeData(beforeList, beforeAndCurrent);
			if (beforeList != null && beforeList.size()>0) {
				for(IssueStatus issue : beforeList) {
					statusList.add(issue);
				}
			} 
			
			//���Ԥ�������
			int forecast = Integer.parseInt((String)parameters.get("forecast"));
			for (int j = 1; j < forecast+1; j++) {
				if ((HashMap<String, Object>)values.get("forecast"+j) != null) {
					IssueStatus future = new IssueStatus();
					future.kw = (String)((HashMap<String, Object>)values.get("forecast"+j)).get("forecastWeek");
					future.red = 0;
					future.yellow = 0;
					future.green = (Integer)((HashMap<String, Object>)values.get("forecast"+j)).get("forecastNum");
					future.sum = (Integer)((HashMap<String, Object>)values.get("forecast"+j)).get("forecastNum");
					statusList.add(future);
				}
			}
			
			//����30��
			if (statusList.size() < 30) {
				int last = 30 - statusList.size(); //�貹�������
				String lastTime = statusList.get(statusList.size()-1).kw; //��ǰʱ��
				int lastWeek = Integer.parseInt(lastTime.split("-")[0]); //��ǰ������
				int lastYear = Integer.parseInt(lastTime.split("-")[1]); //��ǰ��
				int sumWeek = DateUtil.getMaxWeekNumOfYear(lastYear); //��ǰ���������
				//�貹�������������
				if (lastWeek + last < sumWeek) {
					for(int m=0; m<last; m++) {
						IssueStatus add = new IssueStatus();
						add.kw = (lastWeek + m + 1) + "-" + lastYear;
						add.red = 0;
						add.yellow = 0;
						add.red = 0;
						statusList.add(add);
					}
				}
				//�貹�����������
				if (lastWeek + last > sumWeek) {
					//��ǰ�겿��
					for (int n = lastWeek+1; n <= sumWeek; n++) {
						IssueStatus add = new IssueStatus();
						add.kw = n + "-" + lastYear;
						add.red = 0;
						add.yellow = 0;
						add.red = 0;
						statusList.add(add);
					}
					//�ڶ��겿��
					for (int n = 1; n < 30-(sumWeek-lastWeek); n++) {
						IssueStatus add = new IssueStatus();
						add.kw = n + "-" + (lastYear+1);
						add.red = 0;
						add.yellow = 0;
						add.red = 0;
						statusList.add(add);
					}
				}
				
			}
			
			
			try {
				
				//д���һ��excel
				File file1 = new File(TEMPLATE_FILE_PATH1);
				InputStream inputStream1 = new FileInputStream(file1);
				localFileOut1 = new FileOutputStream(new File(path1));
				writeExcel1(parameters, inputStream1, localFileOut1, values, statusList);
				
				//д��ڶ���excel
				File file2 = new File(TEMPLATE_FILE_PATH2);
				InputStream inputStream2 = new FileInputStream(file2);
				localFileOut2 = new FileOutputStream(new File(path2));
				writeExcel2(parameters, inputStream2, localFileOut2, values);
				
				return true;

			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
				MessageBox.post("Excelģ�岻����", 
						"����㱨����", 
						MessageBox.ERROR);
				return false;
				
			} finally {
				try {
					localFileOut1.close();
					localFileOut2.close();
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
	
	public static void writeExcel1(HashMap<String, Object> parameters, InputStream inputStream,
			FileOutputStream localFileOut, HashMap<String, Object> values,
			ArrayList<IssueStatus> statusList) throws Exception{
		
		try {
			POIFSFileSystem fs = new POIFSFileSystem(inputStream);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			
			HSSFSheet sheetPage1 = workbook.getSheetAt(0);
			
			StatusWrite.importDataPage(workbook, sheetPage1, values, statusList); 
			
			workbook.write(localFileOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageBox.post("����ִ��ʧ��", 
					"����״̬�㱨����", 
					MessageBox.ERROR);
		}
		
	}
	
	public static void writeExcel2(HashMap<String, Object> parameters, InputStream inputStream,
			FileOutputStream localFileOut, HashMap<String, Object> values) throws Exception{
		
		try {
			POIFSFileSystem fs = new POIFSFileSystem(inputStream);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			
			HSSFSheet sheetPage1 = workbook.getSheetAt(0);
			
			AssPlacementWrite.importDataPage2(workbook, sheetPage1, values);
			
			workbook.write(localFileOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageBox.post("����ִ��ʧ��", 
					"����״̬�㱨����", 
					MessageBox.ERROR);
		}
		
	}
	
}
