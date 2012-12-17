package com.saturn.tc.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.saturn.ph.PH;
import com.saturn.tc.clientx.TCSession;
import com.saturn.tc.utils.server.EasyDataManagementService;
import com.teamcenter.services.strong.core.DataManagementService;
import com.teamcenter.services.strong.query._2007_06.SavedQuery.SavedQueryResults;
import com.teamcenter.soa.client.model.ModelObject;
import com.teamcenter.soa.client.model.strong.Folder;
import com.teamcenter.soa.client.model.strong.Group;
import com.teamcenter.soa.client.model.strong.Item;
import com.teamcenter.soa.client.model.strong.TC_Project;
import com.teamcenter.soa.client.model.strong.User;
import com.teamcenter.soa.client.model.strong.WorkspaceObject;
import com.teamcenter.soa.exceptions.NotLoadedException;

public class WorkspaceUtils {
	
	public static String HOST = "http://localhost:7001/tc";
	
	public static String DatasetType = "FV9PHImage";
	public static String OtherUid = "fv9otherUid";
	public static String DatasetPageName = "fv9PageName";
	public static String BackUpType = "FV9PHBackup";
	
	
	public static Folder getHome(TCSession session, String userId) {
		EasyDataManagementService service = new EasyDataManagementService(session);
		
		User user = (User) service.loadModelObject(userId);

		Folder home = null;

		if (user != null) {
			try {
				home = user.get_home_folder();
				service.getProperties(home, "current_name",
						"object_name", "contents");

			} catch (NotLoadedException e) {
				e.printStackTrace();
			}
		}

		return home;
	}

	public static ModelObject[] getChildren(TCSession session, Folder root) {
		EasyDataManagementService service = new EasyDataManagementService(session);
		ModelObject[] contents = null;

		try {
			root = (Folder)service.refreshObjects(root);
			service.getProperties(root, "current_name",
					"object_name", "contents");

			contents = root.get_contents();
		} catch (NotLoadedException e) {
			e.printStackTrace();
		}

		return contents;
	}
	
	public static List<Map<String,Item>> initPHItemlist(
			TCSession session, 
			User user,
			ModelObject[] projectList){
		
		List<Map<String,Item>> itemList = new ArrayList<Map<String,Item>>();
		
		String userUid = user.getUid();
		
		EasyDataManagementService service = new EasyDataManagementService(
				session);
		
		DataManagementService dataService = DataManagementService.getService(session.getConnection());
					
		try {
			//调用自定义查询，查找属于此项目的ITEM
			for (int k=0; k<projectList.length; k++) {
				
				TC_Project project = (TC_Project)projectList[k];

				dataService.getProperties(
						new ModelObject[] { project },
						new String[] { "project_id" });
				
				String[] condition = {"Project ID"};
				String[] values = {project.get_project_id()};
				try {
					SavedQueryResults result = QueryUtils.queryCustom(
							session, 
							condition, 
							values,
							"QueryPHItemByProject");
				
					ModelObject[] moEveryProject = result.objects;
					
					for (int i = 0; i < moEveryProject.length; i++) {
						Map<String,Item>  phItemMap = new HashMap<String,Item>();//PHItem对象
						
						Item phitem = (Item)moEveryProject[i];
						service.refreshObjects(phitem);
						service.getProperties(phitem, "uid", "object_name",
								"object_type", "contents");
						
						phItemMap.put(phitem.getUid(), phitem);
						
						itemList.add(phItemMap);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (NotLoadedException e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		
		return itemList;
	}

	private static boolean ItemInProject(WorkspaceObject wo,
			ModelObject[] projectList,
			EasyDataManagementService service) {
		try {
			service.getProperties(wo, "project_ids");
			
			String project_ids = wo.get_project_ids();
			
			for (int k=0; k<projectList.length; k++) {
				
				TC_Project project = (TC_Project)projectList[k];
				
				service.getProperties(project, "project_id");
				
				if (project_ids.contains(project.get_project_id())) {
					return true;
				}
			}
		} catch (NotLoadedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private WorkspaceUtils() {

	}
}
