package com.customer.fawvw.issues.commands.impart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.customer.fawvw.issues.exception.FawvmLoaderException;
import com.customer.fawvw.issues.utils.ComponentUtils;
import com.customer.fawvw.issues.utils.StringUtil;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;

public class ImPartVO {
	
	
	
	private String object_name; //�ܳ�����
	private List<HashMap<String, Object>> impart_Issue;
	public static final String PROPERTY_ISSUESTATUS = "��ʵʩ"; //$NON-NLS-1$

	
	//�����ص���������ص������֤������
	public static ImPartVO loadImPart (TCComponentItemRevision itemRevision, 
			TCSession session, HashMap<String, Object> parameters) throws Exception {
		ImPartVO imPartVO = new ImPartVO();
		
		try {
			//��õ�ǰ�����
			imPartVO.object_name = itemRevision.getProperty("object_name"); //$NON-NLS-1$
			//��õ�ǰ����������
			String PartNo  = itemRevision.getProperty("fv9PartNo"); //$NON-NLS-1$
			//��õ�ǰ�����Ӧ������
			imPartVO.impart_Issue = getImPartissue(session, PartNo, itemRevision, parameters);
	
		} catch (TCException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException(e.getMessage());
		}
		
		return  imPartVO;
		
	}
	
	/**
	 * ��õ�ǰ�����������ļ���
	 * */
	public static List<HashMap<String, Object>> getImPartissue(TCSession session, 
			String PartNo, TCComponentItemRevision itemRevision,
			HashMap<String, Object> parameters) throws Exception {

		TCComponent[] tcCompontents = ComponentUtils.findTCComponentItemByType(session, "FV9Issue"); //$NON-NLS-1$
		List<HashMap<String, Object>> imPartIssues = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> imPartIssue;
		List<String> solutions;
		//ѡ����ĿID
		String project_id = (String)parameters.get("project_id"); //$NON-NLS-1$
			for (int i = 0; i < tcCompontents.length; i++) {
				
				try {
					solutions = new ArrayList<String>();
					imPartIssue = new HashMap<String, Object>();
					TCComponent tcComponent = tcCompontents[i];
					TCComponentItemRevision IssueItemRevision = ((TCComponentItem) tcCompontents[i])
							.getLatestItemRevision();

					//��ȡ��ĿID
					TCComponent[] projects = ComponentUtils.getItemRevisionProjectIds(
							IssueItemRevision, "fv9ProjectLov"); //$NON-NLS-1$
					String[] projectIds = ComponentUtils.getProjectInfos(projects, "project_id"); //$NON-NLS-1$
					String projectInfos = StringUtil.ArrayToString(projectIds);
System.out.println("projectInfos = " + projectInfos);	 //$NON-NLS-1$

					//�������������Ű���ĳһ�ص���������
					//����Ĵ���״̬Ϊ����ʵʩ��
					//�����������ĿΪѡ����Ŀ
					if (StringUtil.containsNo(IssueItemRevision.getProperty("fv9PartNumber"), PartNo) &&  //$NON-NLS-1$
							PROPERTY_ISSUESTATUS.equals(IssueItemRevision.getProperty("fv9IssueStatus")) && //$NON-NLS-1$
							StringUtil.containsNo(projectInfos, project_id)) {
						
						imPartIssue.put("fv9IssueDesc", IssueItemRevision //$NON-NLS-1$
								.getProperty("fv9IssueDesc")); //$NON-NLS-1$
						
						imPartIssue.put("fv9AuditScoreDecreased", IssueItemRevision //$NON-NLS-1$
								.getProperty("fv9AuditScoreDecreased")); //$NON-NLS-1$

						solutions.add(IssueItemRevision
								.getProperty("fv9Solution1")); //$NON-NLS-1$
						solutions.add(IssueItemRevision
								.getProperty("fv9Solution2")); //$NON-NLS-1$
						solutions.add(IssueItemRevision
								.getProperty("fv9Solution3")); //$NON-NLS-1$
						solutions.add(IssueItemRevision
								.getProperty("fv9Solution4")); //$NON-NLS-1$
						solutions.add(IssueItemRevision
								.getProperty("fv9Solution5")); //$NON-NLS-1$
						
						imPartIssue.put("FV9Solution", solutions); //$NON-NLS-1$
						imPartIssue.put("itemRevision", IssueItemRevision); //$NON-NLS-1$
						imPartIssues.add(imPartIssue);
							
					}
					
				} catch (TCException e) {
					// TODO Auto-generated catch block
					throw new FawvmLoaderException("����ִ��ʧ�ܣ���ο���־"); //$NON-NLS-1$
				}
			}
		return imPartIssues;
	}
	
	public String getObject_name() {
		return object_name;
	}

	public void setObject_name(String objectName) {
		object_name = objectName;
	}

	public List<HashMap<String, Object>> getImpart_Issue() {
		return impart_Issue;
	}

	public void setImpart_Issue(List<HashMap<String, Object>> impartIssue) {
		impart_Issue = impartIssue;
	}

	
}
