package com.customer.fawvw.issues.commands.issuesingle;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.customer.fawvw.issues.exception.FawvmLoaderException;
import com.customer.fawvw.issues.utils.ComponentUtils;
import com.customer.fawvw.issues.utils.DateUtil;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCComponentGroup;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCComponentRole;
import com.teamcenter.rac.kernel.TCComponentUser;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCProperty;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

public class ReportIssueSingleLoader {

	private ReportIssueSingleLoader() {
	}

	public static Map<String, Object> load(
			InterfaceAIFComponent targetcompontent,
			TCSession session) throws Exception {

		Map<String, Object> values = new HashMap<String, Object>();

		InterfaceAIFComponent fv9IssueRevision;
		
		try {
			fv9IssueRevision = ((TCComponentItem) targetcompontent).getLatestItemRevision();
			values.put("fv9IssueRevision", fv9IssueRevision); //$NON-NLS-1$
			
			try {
				Map<String, Object> problemImage = ComponentUtils
					.loadComponentItemRelatedImage1(
						(TCComponentItem) targetcompontent, "FV9DescPhoto"); //$NON-NLS-1$
				values.put("problemImage", problemImage); //$NON-NLS-1$
				
				if (problemImage.containsKey("datasetName")) { //$NON-NLS-1$
					System.out.println("�����ϵΪFV9DescPhoto��Image���ݼ�Ϊ��" + problemImage.get("datasetName")); //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-1$
				}
				
				values.put("fv9PartNumber", fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9PartNumber")); //$NON-NLS-1$
				System.out.println("�漰�����ţ�" + fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9PartNumber")); //$NON-NLS-1$
				
				values.put("fv9PartName", fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9PartName")); //$NON-NLS-1$
				System.out.println("������ƣ�" + fv9IssueRevision  //$NON-NLS-1$
						.getProperty("fv9PartName")); //$NON-NLS-1$
				
				values.put("fv9IssueDesc", fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9IssueDesc")); //$NON-NLS-1$
				System.out.println("����������" + fv9IssueRevision  //$NON-NLS-1$
						.getProperty("fv9IssueDesc")); //$NON-NLS-1$
				
				values.put("fv9IssueTempSolution", "��ʱ��ʩ��" + fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9IssueTempSolution")); //$NON-NLS-1$
				System.out.println("��ʱ��ʩ��" + fv9IssueRevision 
						.getProperty("fv9IssueTempSolution")); //$NON-NLS-1$
				
				values.put("fv9TempSolutionDL", fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9TempSolutionDL")); //$NON-NLS-1$
				System.out.println("��ʱ��ʩ������ޣ�" + fv9IssueRevision 
						.getProperty("fv9TempSolutionDL")); //$NON-NLS-1$
				
				values.put("fv9Solution1", fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9Solution1")); //$NON-NLS-1$
				System.out.println("��ʩ1��" + fv9IssueRevision  //$NON-NLS-1$
						.getProperty("fv9Solution1")); //$NON-NLS-1$
				
				values.put("fv9SlDLDate1", fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9SlDLDate1")); //$NON-NLS-1$
				System.out.println("��ʩ1������ڣ� " + fv9IssueRevision  //$NON-NLS-1$
						.getProperty("fv9SlDLDate1")); //$NON-NLS-1$
			
				values.put("fv9Solution2", fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9Solution2")); //$NON-NLS-1$
				System.out.println("��ʩ2��" + fv9IssueRevision  //$NON-NLS-1$
						.getProperty("fv9Solution2")); //$NON-NLS-1$
				
				values.put("fv9SlDLDate2", fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9SlDLDate2")); //$NON-NLS-1$
				System.out.println("��ʩ2������ڣ�" + fv9IssueRevision  //$NON-NLS-1$
						.getProperty("fv9SlDLDate2")); //$NON-NLS-1$
								
				values.put("fv9Solution3", fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9Solution3")); //$NON-NLS-1$
				System.out.println("��ʩ3��" + fv9IssueRevision  //$NON-NLS-1$
						.getProperty("fv9Solution3")); //$NON-NLS-1$
				
				values.put("fv9SlDLDate3", fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9SlDLDate3")); //$NON-NLS-1$
				System.out.println("��ʩ3������ڣ�" + fv9IssueRevision  //$NON-NLS-1$
						.getProperty("fv9SlDLDate3")); //$NON-NLS-1$
				
				values.put("fv9Solution4", fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9Solution4")); //$NON-NLS-1$
				System.out.println("��ʩ4��" + fv9IssueRevision  //$NON-NLS-1$
						.getProperty("fv9Solution4")); //$NON-NLS-1$
				
				values.put("fv9SlDLDate4", fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9SlDLDate4")); //$NON-NLS-1$
				System.out.println("��ʩ4������ڣ� " + fv9IssueRevision  //$NON-NLS-1$
						.getProperty("fv9SlDLDate4")); //$NON-NLS-1$
				
				values.put("fv9Solution5", fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9Solution5")); //$NON-NLS-1$
				System.out.println("��ʩ5��" + fv9IssueRevision  //$NON-NLS-1$
						.getProperty("fv9Solution5")); //$NON-NLS-1$
				
				values.put("fv9SlDLDate5", fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9SlDLDate5")); //$NON-NLS-1$
				System.out.println("��ʩ5������ڣ�" + fv9IssueRevision  //$NON-NLS-1$
						.getProperty("fv9SlDLDate5")); //$NON-NLS-1$
				
				values.put("fv9RGStatus", fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9RGStatus")); //$NON-NLS-1$
			
				System.out.println("���̵�״̬��" + fv9IssueRevision  //$NON-NLS-1$
						.getProperty("fv9RGStatus")); //$NON-NLS-1$
				
				values.put("fv9SupplierID", fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9SupplierID")); //$NON-NLS-1$
				System.out.println("��Ӧ�̺ţ�" + fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9SupplierID")); //$NON-NLS-1$
				
				values.put("fv9SupplierName", fv9IssueRevision //$NON-NLS-1$
						.getProperty("fv9SupplierName")); //$NON-NLS-1$
				
				System.out.println("��Ӧ�����ƣ�" + fv9IssueRevision  //$NON-NLS-1$
						.getProperty("fv9SupplierName")); //$NON-NLS-1$
				
				values.put("itemId", fv9IssueRevision.getProperty("item_id")); //$NON-NLS-1$ //$NON-NLS-2$
				
				//��Ŀ������ĿID
				TCComponent[] projects = ComponentUtils.getItemRevisionProjectIds(
						fv9IssueRevision, "fv9ProjectLov"); //$NON-NLS-1$
				String project_id = ""; //$NON-NLS-1$
				//ȡ��һ����Ŀ��SOP TBT��ʱ��
				if (projects.length > 0) {
					project_id = projects[0].getProperty("project_id"); //$NON-NLS-1$
				}
				//�����������ĳ����Ŀ����ȡ���ڸ���Ŀ��FV9PHReportItemRev
				//�����ǣ�FV9PHReportItemRev�ѷ��ţ�Owner�Ľ�ɫ�ǲ�Ʒ�����Ҵ���ʱ������
				String[] condition = {"��Ŀ ID", "����"}; //$NON-NLS-1$ //$NON-NLS-2$
				String[] value = {project_id, "TCM Released"}; //$NON-NLS-1$
				TCComponent[] PHReportItemRevs = ComponentUtils.findTCComponentByQuery(
						session, "QueryLastPHReportRevision", condition, value); //$NON-NLS-1$
				
				List<TCComponent> list = new ArrayList<TCComponent>();
				//��������������owning_user���ҵ������߽�ɫΪ"��Ʒ����"��PHReportItemRevs
				//������������ӵ�list��
				for (int i = 0; i < PHReportItemRevs.length; i++) {
					
					TCComponent itemRev = (TCComponent)PHReportItemRevs[i];
					TCComponent owning_user = itemRev.getReferenceProperty("owning_user"); //$NON-NLS-1$
					if (owning_user instanceof TCComponentUser) {
						TCComponentUser user = (TCComponentUser)owning_user;
						Map groupRoles = user.getGroupRolesTable();
						System.out.println("groupRoles = " + groupRoles); //$NON-NLS-1$
						
						Collection<List<TCComponentRole>> allRoles = groupRoles.values();
						
						Iterator it = allRoles.iterator();
						
						for (; it.hasNext(); ) {
							List<TCComponentRole> roleList = (List<TCComponentRole>)it.next();
							for (TCComponentRole role : roleList) {
								if ("��Ʒ����".equals(role.getProperty("role_name"))) { //$NON-NLS-1$ //$NON-NLS-2$
									list.add(itemRev);
								}
							}
						}

					}
				}
				
				//�����ڷ���������PHReportItemRevs
				if (list.size() <= 0) {
					values.put("PH_SOP", null); //$NON-NLS-1$
					values.put("TBT_VFF", null); //$NON-NLS-1$
					values.put("TBT_PVS", null); //$NON-NLS-1$
					values.put("TBT_0S", null); //$NON-NLS-1$
				} else {
					//��ȡ�������������
					//FV9_11ProjectTermin��fv9SOPMLDate
					//FV9_11VorserienTer��fv9VFFTBTZP7��fv9PVSTBTZP7��fv90STBTZP7
					TCComponent PHReportRev = QueryLastCreateTCComponent(list);
//					InterfaceAIFComponent FV9_11ProjectTermin = ComponentUtils.
//						getChildComponentByType(PHReportRev, "FV9_11ProjectTermin"); //$NON-NLS-1$
					if ((List<TCComponent>)ComponentUtils.getComponentByRelationAndType(
							PHReportRev, "FV9PMPH_Rel", "FV9_11ProjectTermin") != null &&
						((List<TCComponent>)ComponentUtils.getComponentByRelationAndType(
							PHReportRev, "FV9PMPH_Rel", "FV9_11ProjectTermin")).size() > 0) {
						
						TCComponent FV9_11ProjectTermin = ((List<TCComponent>)ComponentUtils.getComponentByRelationAndType(
								PHReportRev, "FV9PMPH_Rel", "FV9_11ProjectTermin")).get(0);
						
						if (FV9_11ProjectTermin instanceof TCComponentForm) {
							TCComponentForm form = (TCComponentForm)FV9_11ProjectTermin;
							Date vff = form.getDateProperty("fv9VFFMLDate");
							Date pvs = form.getDateProperty("fv9PVSMLDate");
							Date os = form.getDateProperty("fv90SMLDate");
							Date sop = form.getDateProperty("fv9SOPMLDate"); 
							if (vff == null) {
								values.put("PH_VFF", null);
							} else {
								values.put("PH_VFF", vff);
							}
							if (pvs == null) {
								values.put("PH_PVS", null);
							} else {
								values.put("PH_PVS", pvs);
							}
							if (os == null) {
								values.put("PH_0S", null);
							} else {
								values.put("PH_0S", os);
							}
							if (sop == null) {
								values.put("PH_SOP", null); 
							} else {
								values.put("PH_SOP", sop); 
							}
							
						}
					}
					
//					InterfaceAIFComponent FV9_11VorserienTer = ComponentUtils.
//						getChildComponentByType(PHReportRev, "FV9_11VorserienTer"); 
					if ((List<TCComponent>)ComponentUtils.getComponentByRelationAndType(
							PHReportRev, "FV9PMPH_Rel", "FV9_11VorserienTer") != null &&
						((List<TCComponent>)ComponentUtils.getComponentByRelationAndType(
							PHReportRev, "FV9PMPH_Rel", "FV9_11VorserienTer")).size() > 0) {
						
						TCComponent FV9_11VorserienTer = ((List<TCComponent>)ComponentUtils.getComponentByRelationAndType(
								PHReportRev, "FV9PMPH_Rel", "FV9_11VorserienTer")).get(0);
						
						if (FV9_11VorserienTer instanceof TCComponentForm) {
							TCComponentForm form = (TCComponentForm)FV9_11VorserienTer;
							Date TBT_VFF = form.getDateProperty("fv9VFFTBTZP7"); 
							if (TBT_VFF == null) {
								values.put("TBT_VFF", null); 
							} else {
								values.put("TBT_VFF", TBT_VFF); 
							}
							
							Date TBT_PVS = form.getDateProperty("fv9PVSTBTZP7"); 
							if (TBT_PVS == null) {
								values.put("TBT_PVS", null); 
							} else {
								values.put("TBT_PVS", TBT_PVS); 
							}
							
							Date TBT_0S = form.getDateProperty("fv90STBTZP7"); 
							if (TBT_0S == null) {
								values.put("TBT_0S", null); 
							} else {
								values.put("TBT_0S", TBT_0S); 
							}
							
						}
					}
					
				}
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new FawvmLoaderException("����ִ��ʧ��,��ο���־"); 
			}

		} catch (TCException e) {
			e.printStackTrace();
			throw new FawvmLoaderException("����ִ��ʧ��,��ο���־"); 
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new FawvmLoaderException("����ִ��ʧ��,��ο���־"); 
			
		}

		return values;
	}

	private static TCComponent QueryLastCreateTCComponent(List<TCComponent> list) {
		
		//���ֻ��һ��PHReportItemRevs����ȡ��PHReportItemRevs
		//�������һ�����ϵ�PHReportItemRevs����ȡ����ʱ�������
		if (list.size() > 0) {
			//������ʱ�䵹������(����>��)
			Collections.sort(list, new Comparator<TCComponent>() {
				public int compare(TCComponent tc1, TCComponent tc2) {
					Date date1;
					Date date2;
					try {
						date1 = tc1.getDateProperty("creation_date"); 
						date2 = tc2.getDateProperty("creation_date"); 
						return DateUtil.dateDiffSecond(date1,date2);
					} catch (TCException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return 0;
					
				}
			});

		}
		return list.get(0);
	}
	
}
