package com.customer.fawvw.issues.commands.issuestatistic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.customer.fawvw.issues.commands.common.loader.DeptLoader;
import com.customer.fawvw.issues.commands.common.loader.MajorLoader;
import com.customer.fawvw.issues.commands.common.loader.TimeLoader;

public class IssueReportLoaderUserSevice {
	
	public static HashMap<String, Object> loadTxtData(File txt) {
		HashMap<String, Object> values = null;
		try {
			values = new HashMap<String, Object>();

			ArrayList<HashMap<String, Object>> issuelist = new ArrayList<HashMap<String, Object>>();

			String encoding = "GB2312";  //$NON-NLS-1$
			String s = null;
			StringBuffer result=new StringBuffer();
			FileInputStream in=new FileInputStream(txt);
			InputStreamReader r = new InputStreamReader(in, encoding);
			BufferedReader rin=new BufferedReader(r);
			while((s=rin.readLine())!=null){
			    result.append(s);
			}
			String printstr = result.toString();
			issuelist = stringToList(printstr);
			
			Map<String,Object> department = DeptLoader.load(issuelist);
			Map<String,Object> mMvalues = MajorLoader.load(issuelist);
			Map<String, Object> TimeIssues = TimeLoader.load(issuelist);
			Map<String, Object> assPlacement = AssPlacementLoader.load(issuelist);
			
			values.put("department",department); //$NON-NLS-1$
			values.put("mMvalues",mMvalues); //$NON-NLS-1$
			values.put("issues",issuelist); //$NON-NLS-1$
			values.put("TimeIssues", TimeIssues); //$NON-NLS-1$
			values.put("assPlacement", assPlacement); //$NON-NLS-1$
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
		return values;
	}
	
	public static ArrayList<HashMap<String, Object>> stringToList(String str) {
		
		ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>();
		String item_id;//���ⵥ��
		String fv9PartNumber;//�漰�����
		String fv9PartName;//�漰�������
		String fv9IssueDesc;//��������
		String fv9IssueReqCarNo;//�漰�������
		String fv9IssueVeriCarNo;//��֤�������
		String fv9IssueCause;//����ԭ��
		String fv9SolDeadlineDate;//�������
		String fv9CompletedDate;//ʵ���������
		String fv9IssueTempSolution;//��ʱ��ʩ
		String fv9Priority;//��Ҫ�̶�
		String fv9Solution1;//��ʩ1
		String fv9SlDLDate1;//��ʩ1�������
		String fv9SlResDep1;//��ʩ1���β���
		String fv9SlResOwner1;//��ʩ1������
		String fv9Solution2;//��ʩ2
		String fv9SlDLDate2;//��ʩ2�������
		String fv9SlResDep2;//��ʩ2���β���
		String fv9SlResOwner2;//��ʩ2������
		String fv9Solution3;//��ʩ3
		String fv9SlDLDate3;//��ʩ3�������
		String fv9SlResDep3;//��ʩ3���β���
		String fv9SlResOwner3;//��ʩ3������
		String fv9Solution4;//��ʩ4
		String fv9SlDLDate4;//��ʩ4�������
		String fv9SlResDep4;//��ʩ4���β���
		String fv9SlResOwner4;//��ʩ4������
		String fv9Solution5;//��ʩ5
		String fv9SlDLDate5;//��ʩ5�������
		String fv9SlResDep5;//��ʩ5���β���
		String fv9SlResOwner5;//��ʩ5������
		String fv9AssPlacement;//װ��λ��
		String fv9ManagingMajor;//����רҵ
		String fv9RGStatus;//���̵�״̬
		String fv9IssueStatus;//���⴦��״̬
		String fv9TimeChangeRed;//�����ύ����
		String fv9TimeAnalyzed;//����������� 
		String fv9TimeDispatched;//�����������
		String fv9TimeChangeYellow;//��������������
		String fv9TimeImplemented;//���ⷽ��ʵʩ����
		String fv9TimeValidated;//������֤����
		String fv9TimeChangeGreen;//����ر�����
		String fv9IssueFindMileStone;//��̱��׶�
		String fv9IsRelToAuditScore;//�Ƿ���Audit�������
		String fv9AuditScoreDecreased;//�ɽ�Audit��ֵ
		String fv9FactoryName;//�ֳ�����
		String fv9WorkshopName;//��������
		String fv9WorkSegmentName;//��������
		String fv9Proposer;//�����
		String fv9TelOfProposer;//����˵绰
		String fv9ProposedDate;//���ʱ��
		String fv9Submiter;//��д��
		String fv9SupplierID;//��Ӧ�̺�
		String fv9SupplierName;//��Ӧ������
		String fv9Verifier;//��֤��
		String fv9IssueComments;//��ע
		
		String[] issueArr = str.split("#"); //$NON-NLS-1$
		for (int i = 1; i < issueArr.length; i++) {
			
			HashMap<String, Object> item = new HashMap<String, Object>();
			String[] issueInfo = issueArr[i].split("@"); //$NON-NLS-1$
			
			item_id = issueInfo[0];
			fv9PartNumber = issueInfo[1];
			fv9PartName = issueInfo[2];
			fv9IssueDesc = issueInfo[3];
			fv9IssueReqCarNo = issueInfo[4];
			fv9IssueVeriCarNo = issueInfo[5];
			fv9IssueCause = issueInfo[6];
			fv9SolDeadlineDate = issueInfo[7];
			fv9CompletedDate = issueInfo[8];
			fv9IssueTempSolution = issueInfo[9];
			fv9Priority = issueInfo[10];
			fv9Solution1 = issueInfo[11];
			fv9SlDLDate1 = issueInfo[12];
			fv9SlResDep1 = issueInfo[13];
			fv9SlResOwner1 = issueInfo[14];
			fv9Solution2 = issueInfo[15];
			fv9SlDLDate2 = issueInfo[16];
			fv9SlResDep2 = issueInfo[17];
			fv9SlResOwner2 = issueInfo[18];
			fv9Solution3 = issueInfo[19];
			fv9SlDLDate3 = issueInfo[20];
			fv9SlResDep3 = issueInfo[21];
			fv9SlResOwner3 = issueInfo[22];
			fv9Solution4 = issueInfo[23];
			fv9SlDLDate4 = issueInfo[24];
			fv9SlResDep4 = issueInfo[25];
			fv9SlResOwner4 = issueInfo[26];
			fv9Solution5 = issueInfo[27];
			fv9SlDLDate5 = issueInfo[28];
			fv9SlResDep5 = issueInfo[29];
			fv9SlResOwner5 = issueInfo[30];
			fv9AssPlacement = issueInfo[31];
			fv9ManagingMajor = issueInfo[32];
			fv9RGStatus = issueInfo[33];
			fv9IssueStatus = issueInfo[34];
			fv9TimeChangeRed = issueInfo[35];
			fv9TimeAnalyzed = issueInfo[36];
			fv9TimeDispatched = issueInfo[37];
			fv9TimeChangeYellow = issueInfo[38];
			fv9TimeImplemented = issueInfo[39];
			fv9TimeValidated = issueInfo[40];
			fv9TimeChangeGreen = issueInfo[41];
			fv9IssueFindMileStone = issueInfo[42];
			fv9IsRelToAuditScore = issueInfo[43];
			fv9AuditScoreDecreased = issueInfo[44];
			fv9FactoryName = issueInfo[45];
			fv9WorkshopName = issueInfo[46];
			fv9WorkSegmentName = issueInfo[47];
			fv9Proposer = issueInfo[48];
			fv9TelOfProposer = issueInfo[49];
			fv9ProposedDate = issueInfo[50];
			fv9Submiter = issueInfo[51];
			fv9SupplierID = issueInfo[52];
			fv9SupplierName = issueInfo[53];
			fv9Verifier = issueInfo[54];
			fv9IssueComments = issueInfo[55];
			
			item.put("item_id", item_id); //$NON-NLS-1$
			item.put("fv9PartNumber", fv9PartNumber); //$NON-NLS-1$
			item.put("fv9PartName", fv9PartName); //$NON-NLS-1$
			item.put("fv9IssueDesc", fv9IssueDesc); //$NON-NLS-1$
			item.put("fv9IssueReqCarNo", fv9IssueReqCarNo); //$NON-NLS-1$
			item.put("fv9IssueVeriCarNo", fv9IssueVeriCarNo); //$NON-NLS-1$
			item.put("fv9IssueCause", fv9IssueCause); //$NON-NLS-1$
			item.put("fv9SolDeadlineDate", fv9SolDeadlineDate); //$NON-NLS-1$
			item.put("fv9CompletedDate", fv9CompletedDate); //$NON-NLS-1$
			item.put("fv9IssueTempSolution", fv9IssueTempSolution); //$NON-NLS-1$
			item.put("fv9Priority", fv9Priority); //$NON-NLS-1$
			item.put("fv9Solution1", fv9Solution1); //$NON-NLS-1$
			item.put("fv9SlDLDate1", fv9SlDLDate1); //$NON-NLS-1$
			item.put("fv9SlResDep1", fv9SlResDep1); //$NON-NLS-1$
			item.put("fv9SlResOwner1", fv9SlResOwner1); //$NON-NLS-1$
			item.put("fv9Solution2", fv9Solution2); //$NON-NLS-1$
			item.put("fv9SlDLDate2", fv9SlDLDate2); //$NON-NLS-1$
			item.put("fv9SlResDep2", fv9SlResDep2); //$NON-NLS-1$
			item.put("fv9SlResOwner2", fv9SlResOwner2); //$NON-NLS-1$
			item.put("fv9Solution3", fv9Solution3); //$NON-NLS-1$
			item.put("fv9SlDLDate3", fv9SlDLDate3); //$NON-NLS-1$
			item.put("fv9SlResDep3", fv9SlResDep3); //$NON-NLS-1$
			item.put("fv9SlResOwner3", fv9SlResOwner3); //$NON-NLS-1$
			item.put("fv9Solution4", fv9Solution4); //$NON-NLS-1$
			item.put("fv9SlDLDate4", fv9SlDLDate4); //$NON-NLS-1$
			item.put("fv9SlResDep4", fv9SlResDep4); //$NON-NLS-1$
			item.put("fv9SlResOwner4", fv9SlResOwner4); //$NON-NLS-1$
			item.put("fv9Solution5", fv9Solution5); //$NON-NLS-1$
			item.put("fv9SlDLDate5", fv9SlDLDate5); //$NON-NLS-1$
			item.put("fv9SlResDep5", fv9SlResDep5); //$NON-NLS-1$
			item.put("fv9SlResOwner5", fv9SlResOwner5); //$NON-NLS-1$
			item.put("fv9AssPlacement", fv9AssPlacement); //$NON-NLS-1$
			item.put("fv9ManagingMajor", fv9ManagingMajor); //$NON-NLS-1$
			item.put("fv9RGStatus", fv9RGStatus); //$NON-NLS-1$
			item.put("fv9IssueStatus", fv9IssueStatus); //$NON-NLS-1$
			item.put("fv9TimeChangeRed", fv9TimeChangeRed); //$NON-NLS-1$
			item.put("fv9TimeAnalyzed", fv9TimeAnalyzed); //$NON-NLS-1$
			item.put("fv9TimeDispatched", fv9TimeDispatched); //$NON-NLS-1$
			item.put("fv9TimeChangeYellow", fv9TimeChangeYellow); //$NON-NLS-1$
			item.put("fv9TimeImplemented", fv9TimeImplemented); //$NON-NLS-1$
			item.put("fv9TimeValidated", fv9TimeValidated); //$NON-NLS-1$
			item.put("fv9TimeChangeGreen", fv9TimeChangeGreen); //$NON-NLS-1$
			item.put("fv9IssueFindMileStone", fv9IssueFindMileStone); //$NON-NLS-1$
			item.put("fv9IsRelToAuditScore", fv9IsRelToAuditScore); //$NON-NLS-1$
			item.put("fv9AuditScoreDecreased", fv9AuditScoreDecreased); //$NON-NLS-1$
			item.put("fv9FactoryName", fv9FactoryName); //$NON-NLS-1$
			item.put("fv9WorkshopName", fv9WorkshopName); //$NON-NLS-1$
			item.put("fv9WorkSegmentName", fv9WorkSegmentName); //$NON-NLS-1$
			item.put("fv9Proposer", fv9Proposer); //$NON-NLS-1$
			item.put("fv9TelOfProposer", fv9TelOfProposer); //$NON-NLS-1$
			item.put("fv9ProposedDate", fv9ProposedDate); //$NON-NLS-1$
			item.put("fv9Submiter", fv9Submiter); //$NON-NLS-1$
			item.put("fv9SupplierID", fv9SupplierID); //$NON-NLS-1$
			item.put("fv9SupplierName", fv9SupplierName); //$NON-NLS-1$
			item.put("fv9Verifier", fv9Verifier); //$NON-NLS-1$
			item.put("fv9IssueComments", fv9IssueComments);		 //$NON-NLS-1$
			
			result.add(item);
		}
		return result;
	}
}
