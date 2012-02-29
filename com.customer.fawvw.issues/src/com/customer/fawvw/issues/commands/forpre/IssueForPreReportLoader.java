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
			TCSession session = (TCSession) parameters.get("session"); //$NON-NLS-1$
			String curr_prj = (String)parameters.get("project_id"); //$NON-NLS-1$
			
			System.out.println("ѡ����ĿIdΪ��" + curr_prj); //$NON-NLS-1$
			
			System.out.println("��ʼ��ȡ����"); //$NON-NLS-1$
			
			TCComponent[] tcComponents = ComponentUtils
					.findTCComponentItemByType(session, "FV9Issue"); //$NON-NLS-1$
System.out.println("tcComponents.length = " + tcComponents.length); //$NON-NLS-1$
			if (tcComponents.length > 0) {
				for (TCComponent component : tcComponents) {
					TCComponentItemRevision itemRevision = ((TCComponentItem) component)
							.getLatestItemRevision();
					//�漰�������
					String relCarNo = itemRevision.getProperty("fv9IssueReqCarNo"); //$NON-NLS-1$
					//��ȡ��ĿID
					TCComponent[] projects = ComponentUtils.getItemRevisionProjectIds(
							itemRevision, "fv9ProjectLov"); //$NON-NLS-1$
					String[] projectIds = ComponentUtils.getProjectInfos(projects, "project_id"); //$NON-NLS-1$
					String projectInfos = StringUtil.ArrayToString(projectIds);
System.out.println("projectInfos = " + projectInfos);					 //$NON-NLS-1$

					//�������Ŀ�б������ĿID
					// TODO:�жϡ��漰������ţ�IssueReqCarNo��������ֵ�����û������Ԥ���������
					if (!"".equals(projectInfos) && //$NON-NLS-1$
							StringUtil.containsNo(projectInfos, curr_prj) &&
							StringUtil.containsNo(relCarNo, (String) parameters.get("carNumber"))) { //$NON-NLS-1$
						System.out.println("-------------yes------------");					 //$NON-NLS-1$
				
						System.out.println("��ȡ����" + itemRevision + "����Ϣ");
						
						Map<String, Object> issue = new HashMap<String, Object>();
						
						issue.put("item_id", itemRevision //$NON-NLS-1$
								.getProperty("item_id")); // ���ⵥ�� //$NON-NLS-1$
						
						issue.put("fv9IssueDesc", itemRevision //$NON-NLS-1$
								.getProperty("fv9IssueDesc"));// �������� //$NON-NLS-1$
						
						issue.put("fv9Solution1", itemRevision //$NON-NLS-1$
								.getProperty("fv9Solution1"));// ��ʩ 1//$NON-NLS-1$
						
						issue.put("fv9Solution2", itemRevision //$NON-NLS-1$
								.getProperty("fv9Solution2"));// ��ʩ 2//$NON-NLS-1$
						
						issue.put("fv9Solution3", itemRevision //$NON-NLS-1$
								.getProperty("fv9Solution3"));// ��ʩ 1//$NON-NLS-1$
						
						issue.put("fv9Solution4", itemRevision //$NON-NLS-1$
								.getProperty("fv9Solution4"));// ��ʩ 1//$NON-NLS-1$
						
						issue.put("fv9Solution5", itemRevision //$NON-NLS-1$
								.getProperty("fv9Solution5"));// ��ʩ 1//$NON-NLS-1$
						
						issue.put("fv9RGStatus", itemRevision //$NON-NLS-1$
								.getProperty("fv9RGStatus"));// ���̵�״̬ //$NON-NLS-1$
						
						
						if (!"".equals(itemRevision.getProperty("fv9ProposedDate"))) { //$NON-NLS-1$ //$NON-NLS-2$
							issue.put("fv9ProposedDate",  //$NON-NLS-1$
									DateUtil.transformTime(itemRevision.getProperty("fv9ProposedDate"), "MM.dd"));// ���ʱ�� //$NON-NLS-1$ //$NON-NLS-2$
						} else {
							issue.put("fv9ProposedDate", "");// ���ʱ�� //$NON-NLS-1$ //$NON-NLS-2$
						}
						
						if (!"".equals(itemRevision.getProperty("fv9SolDeadlineDate"))) { //$NON-NLS-1$ //$NON-NLS-2$
							issue.put("fv9SolDeadlineDate", DateUtil.getWeekOfYear(itemRevision //$NON-NLS-1$
									.getProperty("fv9SolDeadlineDate")));// ������� //$NON-NLS-1$
						} else {
							issue.put("fv9SolDeadlineDate", "");// ������� //$NON-NLS-1$ //$NON-NLS-2$
						}
						
						issue.put("fv9SlResDep1", itemRevision //$NON-NLS-1$
								.getProperty("fv9SlResDep1"));// ���β��� //$NON-NLS-1$
						
						issue.put("fv9IssueReqCarNo", itemRevision.getProperty("fv9IssueReqCarNo"));// ���� //$NON-NLS-1$ //$NON-NLS-2$
						
						issue.put("itemRevision", itemRevision); //$NON-NLS-1$
						
						issues.add(issue);
					}
				}
					
			}
			
			
			values.put("Issues", issues); //$NON-NLS-1$
			
			values.put("ProjectName", (String)parameters.get("project_name"));// TODO:��Ŀ���� //$NON-NLS-1$ //$NON-NLS-2$
			
			values.put("CreatTime", new DateUtil().getSysDate());// TODO:�Ʊ����� //$NON-NLS-1$
			System.out.println("��ȡ���ݳɹ�"); //$NON-NLS-1$
						
			
System.out.println("values = \n" + values);			 //$NON-NLS-1$
		} catch (Exception e) {
			
			throw new FawvmLoaderException("����ִ��ʧ�ܣ���ο���־"); //$NON-NLS-1$
			
		} 
		return values;
	}
	
	
}
