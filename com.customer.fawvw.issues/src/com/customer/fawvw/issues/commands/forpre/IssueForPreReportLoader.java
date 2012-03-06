package com.customer.fawvw.issues.commands.forpre;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.customer.fawvw.issues.exception.FawvmLoaderException;
import com.customer.fawvw.issues.utils.ComponentUtils;
import com.customer.fawvw.issues.utils.DateUtil;
import com.customer.fawvw.issues.utils.StringUtil;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

public class IssueForPreReportLoader {

	private IssueForPreReportLoader() {

	}

	public static Map<String, Object> load(
			HashMap<String, Object> parameters) throws Exception {
		List<Map<String, Object>> issues = new ArrayList<Map<String, Object>>();
		Map<String, Object> values = new HashMap<String, Object>();

		try {
			TCSession session = (TCSession) parameters.get("session"); 
			String curr_prj = (String)parameters.get("project_id"); 
			
			System.out.println("ѡ����ĿIdΪ��" + curr_prj); 
			
			System.out.println("��ʼ��ȡ����"); 
			
			TCComponent[] tcComponents = ComponentUtils
					.findTCComponentItemByType(session, "FV9Issue"); 
			if (tcComponents.length > 0) {
				for (TCComponent component : tcComponents) {
					TCComponentItemRevision itemRevision = ((TCComponentItem) component)
							.getLatestItemRevision();
					//�漰�������
					String relCarNo = itemRevision.getProperty("fv9IssueReqCarNo"); 
					//��ȡ��ĿID
					TCComponent[] projects = ComponentUtils.getItemRevisionProjectIds(
							itemRevision, "fv9ProjectLov"); 
					String[] projectIds = ComponentUtils.getProjectInfos(projects, "project_id"); 
					String projectInfos = StringUtil.ArrayToString(projectIds);

					//�������Ŀ�б������ĿID
					// TODO:�жϡ��漰������ţ�IssueReqCarNo��������ֵ�����û������Ԥ���������
					if (!"".equals(projectInfos) && 
							StringUtil.containsNo(projectInfos, curr_prj) &&
							StringUtil.containsNo(relCarNo, (String) parameters.get("carNumber"))) { 
						System.out.println("-------------yes------------");					 
				
						System.out.println("��ȡ����" + itemRevision + "����Ϣ");
						
						Map<String, Object> issue = new HashMap<String, Object>();
						
						issue.put("item_id", itemRevision.getProperty("item_id")); // ���ⵥ�� 
						issue.put("fv9IssueDesc", itemRevision.getProperty("fv9IssueDesc"));// �������� 
						
						issue.put("fv9SolutionBS", itemRevision.getProperty("fv9SolutionBS"));
						issue.put("fv9SolutionCA", itemRevision.getProperty("fv9SolutionCA"));
						issue.put("fv9SolutionLO", itemRevision.getProperty("fv9SolutionLO"));
						issue.put("fv9SolutionPA", itemRevision.getProperty("fv9SolutionPA"));
						issue.put("fv9SolutionPL", itemRevision.getProperty("fv9SolutionPL"));
						issue.put("fv9SolutionQAPP", itemRevision.getProperty("fv9SolutionQAPP"));
						issue.put("fv9SolutionSU", itemRevision.getProperty("fv9SolutionSU"));
						issue.put("fv9SolutionVSC", itemRevision.getProperty("fv9SolutionVSC"));
						issue.put("fv9SolutionTE", itemRevision.getProperty("fv9SolutionTE"));
						
//						issue.put("fv9SlResDepBS", itemRevision.getProperty("fv9SlResDepBS"));
//						issue.put("fv9SlResDepCA", itemRevision.getProperty("fv9SlResDepCA"));
//						issue.put("fv9SlResDepLO", itemRevision.getProperty("fv9SlResDepLO"));
//						issue.put("fv9SlResDepPA", itemRevision.getProperty("fv9SlResDepPA"));
//						issue.put("fv9SlResDepPL", itemRevision.getProperty("fv9SlResDepPL"));
//						issue.put("fv9SlResDepQAPP", itemRevision.getProperty("fv9SlResDepQAPP"));
//						issue.put("fv9SlResDepSU", itemRevision.getProperty("fv9SlResDepSU"));
//						issue.put("fv9SlResDepVSC", itemRevision.getProperty("fv9SlResDepVSC"));
						
						issue.put("fv9RGStatus", itemRevision.getProperty("fv9RGStatus"));// ���̵�״̬ 
						
						
						if (!"".equals(itemRevision.getProperty("fv9ProposedDate"))) {  
							issue.put("fv9ProposedDate",  
									DateUtil.transformTime(itemRevision.getProperty("fv9ProposedDate"), "MM.dd"));// ���ʱ��  
						} else {
							issue.put("fv9ProposedDate", "");// ���ʱ��  
						}
						
						if (!"".equals(itemRevision.getProperty("fv9SolDeadlineDate"))) {  
							issue.put("fv9SolDeadlineDate", DateUtil.getWeekOfYear(itemRevision 
									.getProperty("fv9SolDeadlineDate")));// ������� 
						} else {
							issue.put("fv9SolDeadlineDate", "");// �������  
						}
						
						issue.put("fv9SlResDep1", itemRevision 
								.getProperty("fv9SlResDep1"));// ���β��� 
						
						issue.put("fv9IssueReqCarNo", itemRevision.getProperty("fv9IssueReqCarNo"));// ����  
						
						issue.put("itemRevision", itemRevision); 
						
						issues.add(issue);
					}
				}
					
			}
			
			
			values.put("Issues", issues); 
			
			values.put("ProjectName", (String)parameters.get("project_name"));// TODO:��Ŀ����  
			
			values.put("CreatTime", new DateUtil().getSysDate());// TODO:�Ʊ����� 
			System.out.println("��ȡ���ݳɹ�"); 
						
			
System.out.println("values = \n" + values);			 
		} catch (Exception e) {
			
			throw new FawvmLoaderException("����ִ��ʧ�ܣ���ο���־"); 
			
		} 
		return values;
	}
	
	
}
