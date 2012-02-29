package com.customer.fawvw.issues.commands.impart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.customer.fawvw.issues.exception.FawvmLoaderException;
import com.customer.fawvw.issues.utils.ComponentUtils;
import com.customer.fawvw.issues.utils.StringUtil;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCComponentProject;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

public class ReportImPartIssueLoader {

	public static final String Property_FV9ISTRACKED = "Yes"; //$NON-NLS-1$

	private ReportImPartIssueLoader() {
	}

	public static Map<String, Object> load(TCSession session,
			HashMap<String, Object> parameters) throws Exception {

		Map<String, Object> values = new HashMap<String, Object>();

		try {
			System.out.println("��ʼ��ȡ����"); //$NON-NLS-1$

			//��ѯFV9Component���͵������
			TCComponent[] tcCompontents = ComponentUtils
					.findTCComponentItemByType(session, "FV9Component"); //$NON-NLS-1$
			
			List<ImPartVO> imparts = new LinkedList<ImPartVO>();
			
			//ɸѡ�������������
			if (tcCompontents.length > 0) {
				//��ȡ�ص��
				imparts = getReportvalues(tcCompontents, session, parameters);
			}
			
			values.put("imparts", imparts);  //$NON-NLS-1$
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException(e.getMessage());
		}

		return values;
	}

	/*
	 * ��������ɸѡ������������ѡ����Ŀ�����ص��
	 * @param tcCompontents
	 *            �������
	 * @param parameters
	 *            ɸѡ����           
	 */
	public static List<ImPartVO> getReportvalues(TCComponent[] tcCompontents,
			TCSession session, HashMap<String, Object> parameters) throws Exception {
		//�����ص��
		List<ImPartVO> imParts = new ArrayList<ImPartVO>();
		String currentProjectID = (String)parameters.get("project_id");//ѡ����ĿID //$NON-NLS-1$
		try {
			String log = "�ص���У�\r\n"; //$NON-NLS-1$
			for (int i = 0; i < tcCompontents.length; i++) {
	
				try {
	
					TCComponent tcComponent = tcCompontents[i];
					TCComponentItemRevision itemRevision = ((TCComponentItem) tcCompontents[i])
							.getLatestItemRevision();
					
					//ѡ����Ŀ��Ϊ��
					//�����������Ŀ����Ϊ��
					//���������Ŀ����ѡ�е���Ŀ
					//����ġ��Ƿ���١�����Ϊyes
					if (!"".equals(currentProjectID) && //$NON-NLS-1$
							!" ".equals(itemRevision.getProperty("project_ids")) &&  //$NON-NLS-1$ //$NON-NLS-1$
							!"".equals(itemRevision.getProperty("project_ids"))	&& //$NON-NLS-1$ //$NON-NLS-1$
							StringUtil.containsNo(itemRevision.getProperty("project_ids"), currentProjectID) && //$NON-NLS-1$
							Property_FV9ISTRACKED.equals(itemRevision.getProperty("fv9IsTracked"))){ //$NON-NLS-1$
							
						log += itemRevision + "\r\n"; //$NON-NLS-1$
						//�����ص��������
						imParts.add(ImPartVO.loadImPart(itemRevision, session, parameters));
							
					}
						
				} catch (TCException e) {
					// TODO Auto-generated catch block
					throw new FawvmLoaderException("����ִ��ʧ�ܣ���ο���־"); //$NON-NLS-1$
					
				}
			}
			System.out.println(log);
		} catch (TCException e1) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("����ִ��ʧ�ܣ���ο���־"); //$NON-NLS-1$
		}

		return imParts;
	}
}

