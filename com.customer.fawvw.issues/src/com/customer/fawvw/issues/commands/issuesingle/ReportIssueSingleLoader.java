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
			values.put("fv9IssueRevision", fv9IssueRevision); 
			
			try {
				Map<String, Object> problemImage = ComponentUtils
					.loadComponentItemRelatedImage1(
						(TCComponentItem) targetcompontent, "FV9DescPhoto"); 
				values.put("problemImage", problemImage); 
				
				if (problemImage.containsKey("datasetName")) { 
					System.out.println("�����ϵΪFV9DescPhoto��Image���ݼ�Ϊ��" + problemImage.get("datasetName")); //$NON-NLS-2$         
				}
				
				values.put("fv9PartNumber", fv9IssueRevision 
						.getProperty("fv9PartNumber")); 
				System.out.println("�漰�����ţ�" + fv9IssueRevision 
						.getProperty("fv9PartNumber")); 
				
				values.put("fv9PartName", fv9IssueRevision 
						.getProperty("fv9PartName")); 
				System.out.println("������ƣ�" + fv9IssueRevision  
						.getProperty("fv9PartName")); 
				
				values.put("fv9IssueDesc", fv9IssueRevision 
						.getProperty("fv9IssueDesc")); 
				System.out.println("����������" + fv9IssueRevision  
						.getProperty("fv9IssueDesc")); 
				
				values.put("fv9IssueTempSolution", fv9IssueRevision 
						.getProperty("fv9IssueTempSolution")); 
				System.out.println("��ʱ��ʩ��" + fv9IssueRevision 
						.getProperty("fv9IssueTempSolution")); 
				
				values.put("fv9TempSolutionDL", fv9IssueRevision 
						.getProperty("fv9TempSolutionDL")); 
				System.out.println("��ʱ��ʩ������ޣ�" + fv9IssueRevision 
						.getProperty("fv9TempSolutionDL")); 
				
				values.put("fv9SolutionBS", fv9IssueRevision 
						.getProperty("fv9SolutionBS")); 
				System.out.println("BS��ʩ��" + fv9IssueRevision  
						.getProperty("fv9SolutionBS")); 
				
				values.put("fv9SlDLDateBS", fv9IssueRevision 
						.getProperty("fv9SlDLDateBS")); 
				System.out.println("BS��ʩ������ޣ� " + fv9IssueRevision  
						.getProperty("fv9SlDLDateBS")); 
			
				values.put("fv9SolutionCA", fv9IssueRevision 
						.getProperty("fv9SolutionCA")); 
				System.out.println("CA��ʩ��" + fv9IssueRevision  
						.getProperty("fv9SolutionCA")); 
				
				values.put("fv9SlDLDateCA", fv9IssueRevision 
						.getProperty("fv9SlDLDateCA")); 
				System.out.println("CA��ʩ������ޣ�" + fv9IssueRevision  
						.getProperty("fv9SlDLDateCA")); 
								
				values.put("fv9SolutionLO", fv9IssueRevision 
						.getProperty("fv9SolutionLO")); 
				System.out.println("LO��ʩ��" + fv9IssueRevision  
						.getProperty("fv9SolutionLO")); 
				
				values.put("fv9SlDLDateLO", fv9IssueRevision 
						.getProperty("fv9SlDLDateLO")); 
				System.out.println("LO��ʩ������ޣ�" + fv9IssueRevision  
						.getProperty("fv9SlDLDateLO")); 
				
				values.put("fv9SolutionPA", fv9IssueRevision 
						.getProperty("fv9SolutionPA")); 
				System.out.println("PA��ʩ��" + fv9IssueRevision  
						.getProperty("fv9SolutionPA")); 
				
				values.put("fv9SlDLDatePA", fv9IssueRevision 
						.getProperty("fv9SlDLDatePA")); 
				System.out.println("PA��ʩ������ޣ� " + fv9IssueRevision  
						.getProperty("fv9SlDLDatePA")); 
				
				values.put("fv9SolutionPL", fv9IssueRevision 
						.getProperty("fv9SolutionPL")); 
				System.out.println("PL��ʩ��" + fv9IssueRevision  
						.getProperty("fv9SolutionPL")); 
				
				values.put("fv9SlDLDatePL", fv9IssueRevision 
						.getProperty("fv9SlDLDatePL")); 
				System.out.println("PL��ʩ������ޣ�" + fv9IssueRevision  
						.getProperty("fv9SlDLDatePL")); 
				
				values.put("fv9SolutionQAPP", fv9IssueRevision 
						.getProperty("fv9SolutionQAPP")); 
				System.out.println("QAPP��ʩ��" + fv9IssueRevision  
						.getProperty("fv9SolutionQAPP")); 
				
				values.put("fv9SlDLDateQAPP", fv9IssueRevision 
						.getProperty("fv9SlDLDateQAPP")); 
				System.out.println("QAPP��ʩ������ޣ�" + fv9IssueRevision  
						.getProperty("fv9SlDLDateQAPP")); 
				
				values.put("fv9SolutionSU", fv9IssueRevision 
						.getProperty("fv9SolutionSU")); 
				System.out.println("SU��ʩ��" + fv9IssueRevision  
						.getProperty("fv9SolutionSU")); 
				
				values.put("fv9SlDLDateSU", fv9IssueRevision 
						.getProperty("fv9SlDLDateSU")); 
				System.out.println("SU��ʩ������ޣ�" + fv9IssueRevision  
						.getProperty("fv9SlDLDateSU")); 
				
				values.put("fv9SolutionVSC", fv9IssueRevision 
						.getProperty("fv9SolutionVSC")); 
				System.out.println("VSC��ʩ��" + fv9IssueRevision  
						.getProperty("fv9SolutionVSC")); 
				
				values.put("fv9SlDLDateVSC", fv9IssueRevision 
						.getProperty("fv9SlDLDateVSC")); 
				System.out.println("VSC��ʩ������ޣ�" + fv9IssueRevision  
						.getProperty("fv9SlDLDateVSC")); 
				
				values.put("fv9RGStatus", fv9IssueRevision 
						.getProperty("fv9RGStatus")); 
			
				System.out.println("���̵�״̬��" + fv9IssueRevision  
						.getProperty("fv9RGStatus")); 
				
				values.put("fv9SupplierID", fv9IssueRevision 
						.getProperty("fv9SupplierID")); 
				System.out.println("��Ӧ�̺ţ�" + fv9IssueRevision 
						.getProperty("fv9SupplierID")); 
				
				values.put("fv9SupplierName", fv9IssueRevision 
						.getProperty("fv9SupplierName")); 
				
				System.out.println("��Ӧ�����ƣ�" + fv9IssueRevision  
						.getProperty("fv9SupplierName")); 
				
				values.put("itemId", fv9IssueRevision.getProperty("item_id"));  //$NON-NLS-2$
				
				//��Ŀ������ĿID
				TCComponent[] projects = ComponentUtils.getItemRevisionProjectIds(
						fv9IssueRevision, "fv9ProjectLov"); 
				String project_id = ""; 
				//ȡ��һ����Ŀ��SOP TBT��ʱ��
				if (projects.length > 0) {
					project_id = projects[0].getProperty("project_id"); 
				}
				//�����������ĳ����Ŀ����ȡ���ڸ���Ŀ��FV9PHReportItemRev
				//�����ǣ�FV9PHReportItemRev�ѷ��ţ�Owner�Ľ�ɫ�ǲ�Ʒ�����Ҵ���ʱ������
				String[] condition = {"��Ŀ ID", "����"};  //$NON-NLS-2$
				String[] value = {project_id, "TCM Released"}; 
				TCComponent[] PHReportItemRevs = ComponentUtils.findTCComponentByQuery(
						session, "QueryLastPHReportRevision", condition, value); 
				
				List<TCComponent> list = new ArrayList<TCComponent>();
				//��������������owning_user���ҵ������߽�ɫΪ"��Ʒ����"��PHReportItemRevs
				//������������ӵ�list��
				for (int i = 0; i < PHReportItemRevs.length; i++) {
					
					TCComponent itemRev = (TCComponent)PHReportItemRevs[i];
					TCComponent owning_user = itemRev.getReferenceProperty("owning_user"); 
					if (owning_user instanceof TCComponentUser) {
						TCComponentUser user = (TCComponentUser)owning_user;
						Map groupRoles = user.getGroupRolesTable();
						System.out.println("groupRoles = " + groupRoles); 
						
						Collection<List<TCComponentRole>> allRoles = groupRoles.values();
						
						Iterator it = allRoles.iterator();
						
						for (; it.hasNext(); ) {
							List<TCComponentRole> roleList = (List<TCComponentRole>)it.next();
							for (TCComponentRole role : roleList) {
								if ("��Ʒ����".equals(role.getProperty("role_name"))) {  //$NON-NLS-2$
									list.add(itemRev);
								}
							}
						}

					}
				}
				
				//�����ڷ���������PHReportItemRevs
				if (list.size() <= 0) {
					values.put("PH_SOP", null); 
					values.put("TBT_VFF", null); 
					values.put("TBT_PVS", null); 
					values.put("TBT_0S", null); 
				} else {
					//��ȡ�������������
					//FV9_11ProjectTermin��fv9SOPMLDate
					//FV9_11VorserienTer��fv9VFFTBTZP7��fv9PVSTBTZP7��fv90STBTZP7
					TCComponent PHReportRev = QueryLastCreateTCComponent(list);
//					InterfaceAIFComponent FV9_11ProjectTermin = ComponentUtils.
//						getChildComponentByType(PHReportRev, "FV9_11ProjectTermin"); 
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
