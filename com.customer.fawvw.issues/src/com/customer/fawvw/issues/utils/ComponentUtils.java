package com.customer.fawvw.issues.utils;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.JComboBox;

import com.teamcenter.rac.aif.AIFDesktop;
import com.teamcenter.rac.aif.kernel.AIFComponentContext;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.commands.paste.PasteCommand;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentDataset;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCComponentItemType;
import com.teamcenter.rac.kernel.TCComponentProject;
import com.teamcenter.rac.kernel.TCComponentQuery;
import com.teamcenter.rac.kernel.TCComponentQueryType;
import com.teamcenter.rac.kernel.TCComponentTcFile;
import com.teamcenter.rac.kernel.TCComponentType;
import com.teamcenter.rac.kernel.TCComponentUser;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCPreferenceService;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

/**
 * TCComponent������
 * 
 * @author LeslieGu
 */
public class ComponentUtils {
	
	
	public static Map<String, Object> loadComponentItemRelatedImage1(
			TCComponentItem componentItem, String key) {

		Map<String, Object> relateMap = new HashMap<String, Object>();
		try {
			TCComponentItemRevision itemRevision = componentItem
					.getLatestItemRevision();

			TCComponentType componentType[] = itemRevision.getTypeComponent()
					.getRelationTypes();
			if (componentType != null) {
				for (TCComponentType relateType : componentType) {
					String relateKey = relateType.toString();

					if (relateKey.equals(key)) { //$NON-NLS-1$
						TCComponent component = itemRevision
								.getRelatedComponent(relateKey);
						
						//�汾�´���ĳ��ϵ��ͼƬ
						if (component != null) {
							if (component instanceof TCComponentDataset) {
								
								TCComponentDataset componentDataset = (TCComponentDataset) component;
									
								relateMap.put("datasetName", componentDataset.getProperty("object_name")); //$NON-NLS-1$ //$NON-NLS-2$
									
								TCComponentTcFile[] componentTcFiles = componentDataset.getTcFiles();
								
								if (componentTcFiles != null && componentTcFiles.length > 0) {
									TCComponentTcFile tcFile = componentTcFiles[0];
		
									BufferedImage value = ImageUtil
											.readBufferedImg(tcFile
													.getFmsFile());
		
									relateMap.put(relateKey, value);
								}
							}
						} 
						

					}
				}
			}
		} catch (TCException tcexception) {
			tcexception.printStackTrace();
		}

		return relateMap;
	}

	public static Map<String, BufferedImage> loadComponentItemRelatedImage(
			TCComponentItem componentItem) {

		Map<String, BufferedImage> relateMap = new HashMap<String, BufferedImage>();
		try {
			TCComponentItemRevision itemRevision = componentItem
					.getLatestItemRevision();

			TCComponentType componentType[] = itemRevision.getTypeComponent()
					.getRelationTypes();

			if (componentType != null) {
				for (TCComponentType relateType : componentType) {
					String relateKey = relateType.toString();

					if (relateKey.startsWith("FAWBM")) { //$NON-NLS-1$
						TCComponent component = itemRevision
								.getRelatedComponent(relateKey);

						if (component instanceof TCComponentDataset) {
							TCComponentDataset componentDataset = (TCComponentDataset) component;

							if (componentDataset != null) {
								TCComponentTcFile[] componentTcFiles = componentDataset
										.getTcFiles();

								if (componentTcFiles != null
										&& componentTcFiles.length > 0) {
									TCComponentTcFile tcFile = componentTcFiles[0];

									BufferedImage value = ImageUtil
											.readBufferedImg(tcFile
													.getFmsFile());

									relateMap.put(relateKey, value);
								}
							}
						}
					}
				}
			}
		} catch (TCException tcexception) {
			tcexception.printStackTrace();
		}

		return relateMap;
	}
	
	/*
	 * �����û���ѯ��Ŀ
	 */
	public static TCComponent[] findTCComponentProject(TCSession session)
	{
		
		try {
			TCComponentQueryType queryType = (TCComponentQueryType) session
					.getTypeComponent("ImanQuery");  //$NON-NLS-1$
			TCComponentQuery query = (TCComponentQuery) queryType
					.find("�����û�����Ŀ");  //$NON-NLS-1$
	
			return query.execute(new String[] {"ID"}, new String[] {((TCComponentUser)session.getUser()).getUserId()}); //$NON-NLS-1$
		} catch (TCException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static TCComponent[] findTCComponentItemByType(TCSession session,
			String type) {
		try {
			TCComponentQueryType queryType = (TCComponentQueryType) session
					.getTypeComponent("ImanQuery"); //$NON-NLS-1$
			TCComponentQuery query = (TCComponentQuery) queryType
					.find("Item..."); //$NON-NLS-1$

			return query.execute(new String[] { "����" }, new String[] { type }); //$NON-NLS-1$
		} catch (TCException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * ����Item id������TCComponentItem
	 * 
	 * @param session
	 * @param id
	 * @return �����ӦId������Item, ����null
	 */
	public static TCComponentItem findTCComponentItemById(TCSession session,
			String id) {
		try {
			TCComponentItemType componentItemType = (TCComponentItemType) session
					.getTypeComponent("Item"); //$NON-NLS-1$

			return componentItemType.find(id);
		} catch (TCException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * ���ճ������, ʵ�ְ�����components�ֱ�ճ�������е�pasteTargetComponents��
	 * 
	 * @param components
	 * @param pasteTargetComponents
	 * @param desktop
	 */
	public static void paste(TCComponent[] components,
			TCComponent[] pasteTargetComponents, AIFDesktop desktop) {

		try {
			PasteCommand pastecommand = new PasteCommand(components,
					pasteTargetComponents, desktop);

			pastecommand.setFailBackFlag(true);
			pastecommand.executeModal();
		} catch (Exception exception) {
			MessageBox.post(null, exception);
		}
	}

	/**
	 * ���ճ������, ʵ�ְ�component�ֱ�ճ����pasteTargetComponent��
	 * 
	 * @param component
	 * @param pasteTargetComponent
	 * @param desktop
	 */
	public static void paste(InterfaceAIFComponent component,
			InterfaceAIFComponent pasteTargetComponent, AIFDesktop desktop) {

		TCComponent[] components = { (TCComponent) component };
		TCComponent[] pasteTargeComponent = { (TCComponent) pasteTargetComponent };

		paste(components, pasteTargeComponent, desktop);
	}

	/**
	 * ���ճ������, ʵ�ְ�component�ֱ�ճ�������е�pasteTargetComponents��
	 * 
	 * @param components
	 * @param pasteTargetComponents
	 * @param desktop
	 */
	public static void paste(InterfaceAIFComponent component,
			InterfaceAIFComponent pasteTargetComponent[], AIFDesktop desktop) {

		TCComponent[] components = { (TCComponent) component };
		TCComponent[] pasteTargeComponents = new TCComponent[pasteTargetComponent.length];

		for (int i = 0; i < pasteTargetComponent.length; ++i) {
			pasteTargeComponents[i] = (TCComponent) pasteTargetComponent[i];
		}

		paste(components, pasteTargeComponents, desktop);
	}
	
	/**
	 * ���ݸ�������Ͳ���������type�����������
	 * 
	 * @param parentComponent
	 *            �����
	 * @param type
	 *            ���������
	 */
	public static LinkedList<InterfaceAIFComponent> getChildComponents(
			InterfaceAIFComponent parentComponent, String type) {

		LinkedList<InterfaceAIFComponent> queue = new LinkedList<InterfaceAIFComponent>();
		addComponentChildren(queue, parentComponent);
		LinkedList<InterfaceAIFComponent> components = new LinkedList<InterfaceAIFComponent>();

		for (InterfaceAIFComponent component : queue) {
			String componentType = component.getType();
			if (componentType.equals(type)) {
				components.add(component);
			}
		}
		return components;
	}

	/**
	 * ����������ͺͶ�Ӧ���Ե�key-valueֵ,���������
	 * 
	 * @param parentComponent
	 *            �����
	 * @param type
	 *            ���������
	 * @param propertyKey
	 *            ���������key
	 * @param propertyValue
	 *            ���������value
	 * 
	 * @return �����ڷ���null
	 */
	public static InterfaceAIFComponent getChildComponent(
			InterfaceAIFComponent parentComponent, String type,
			String propertyKey, String propertyValue) {

		LinkedList<InterfaceAIFComponent> queue = new LinkedList<InterfaceAIFComponent>();
		addComponentChildren(queue, parentComponent);

		while (!queue.isEmpty()) {
			InterfaceAIFComponent component = queue.removeFirst();
			String componentType = component.getType();

			if (componentType.equals(type)) {
				if (propertyKey != null && propertyValue != null) {
					try {
						String name = component.getProperty(propertyKey);
						if (propertyValue.equals(name)) {
							return component;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					return component;
				}
			}

			addComponentChildren(queue, component);
		}

		return null;
	}
	
	/**
	 * �����������,���ҵ�һ�������
	 * 
	 * @param parentComponent
	 *            �����
	 * @param type
	 *            ���������
	 * @param propertyKey
	 *            ���������key
	 * @param propertyValue
	 *            ���������value
	 * 
	 * @return �����ڷ���null
	 */
	public static InterfaceAIFComponent getChildComponentByType(
			InterfaceAIFComponent parentComponent, String type) {

		LinkedList<InterfaceAIFComponent> queue = new LinkedList<InterfaceAIFComponent>();
		addComponentChildren(queue, parentComponent);

		while (!queue.isEmpty()) {
			InterfaceAIFComponent component = queue.removeFirst();
			String componentType = component.getType();

			if (componentType.equals(type)) {
					return component;
			}
			addComponentChildren(queue, component);
		}

		return null;
	}

	/**
	 * ���������Ӧ���Ե�key-valueֵ,���������
	 * 
	 * @param parentComponent
	 *            �����
	 * @param propertyKey
	 *            ���������key
	 * @param propertyValue
	 *            ���������value
	 * 
	 * @return �����ڷ���null
	 */
	public static InterfaceAIFComponent getChildComponent(
			InterfaceAIFComponent parentComponent, String propertyKey,
			String propertyValue) {
		LinkedList<InterfaceAIFComponent> queue = new LinkedList<InterfaceAIFComponent>();
		addComponentChildren(queue, parentComponent);

		while (!queue.isEmpty()) {
			InterfaceAIFComponent component = queue.removeFirst();

			if (propertyKey != null && propertyValue != null) {
				try {
					String name = component.getProperty(propertyKey);

					if (propertyValue.equals(name)) {
						return component;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			addComponentChildren(queue, component);
		}

		return null;
	}

	private static void addComponentChildren(
			LinkedList<InterfaceAIFComponent> queue,
			InterfaceAIFComponent component) {

		try {
			for (AIFComponentContext componentContext : component.getChildren()) {
				InterfaceAIFComponent childComponent = componentContext
						.getComponent();

				queue.add(childComponent);
			}

			if (component instanceof TCComponentItemRevision) {
				TCComponentItemRevision componentItemRevision = (TCComponentItemRevision) component;
				TCComponent[] views = ((TCComponent) componentItemRevision)
						.getReferenceListProperty("view"); //$NON-NLS-1$
				if (views != null) {
					for (TCComponent view : views) {
						queue.add(view);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * ����ϵͳ���Զ����ѯ
	 */
	public static TCComponent[] findTCComponentByQuery(TCSession session,
			String queryName, String[] conditions, String[] values) {
		try {
			TCComponentQueryType queryType = (TCComponentQueryType) session
					.getTypeComponent("ImanQuery"); //$NON-NLS-1$
			TCComponentQuery query = (TCComponentQuery) queryType
					.find(queryName); //$NON-NLS-1$

			return query.execute(conditions, values); //$NON-NLS-1$
		} catch (TCException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	/*
	 * ������ѡ�����Ʋ�ѯ��ѡ��ֵ
	 */
	public static String getPreferenceByName(TCSession session, String preferenceName) {
		TCPreferenceService preferences = session.getPreferenceService();
		return preferences.getString(4, preferenceName);
		
	}
	
	/*
	 * ��ʼ����Ŀ����ѯϵͳ�����е���Ŀ,Ĭ��ѡ��ǰ�û����õĵ�ǰ��Ŀ
	 */
	public static void initializeProject(TCSession session, JComboBox projectComboBox) {
		String[] conditions = new String[] {
				"��Ŀ ID", //$NON-NLS-1$
				"�"}; //$NON-NLS-1$
		String[] inputs = new String[] {"*", "true"};  //$NON-NLS-1$ //$NON-NLS-2$
		TCComponent[] projects = findTCComponentByQuery(
					session, 
					"��Ŀ...", //$NON-NLS-1$
					conditions, inputs);
		System.out.println("projects.length = " + projects.length);			 //$NON-NLS-1$
		for (int i = 0; i < projects.length; i++) {
			projectComboBox.addItem(projects[i]);
		}
		TCComponentProject currentPrj = (TCComponentProject)session.getCurrentProject();
		projectComboBox.setSelectedItem(currentPrj);

	}
	
	/*
	 * ��ȡϵͳ�����м������Ŀ
	 * @author le.liu
	 * 
	 */
	public static String[] getAllProjects(TCSession session) {
		
		String[] conditions = new String[] {
				"��Ŀ ID", //$NON-NLS-1$
				"�"}; //$NON-NLS-1$
		String[] inputs = new String[] {"*", "true"};  //$NON-NLS-1$ //$NON-NLS-2$
		TCComponent[] projects = findTCComponentByQuery(
				session, 
				"��Ŀ...", //$NON-NLS-1$
				conditions, 
				inputs);
System.out.println("projects.length = " + projects.length);	 //$NON-NLS-1$
		String[] projectIds = new String[projects.length];
		
		for (int i = 0; i < projects.length; i++) {
			try {
				projectIds[i] = projects[i].getProperty("project_id"); //$NON-NLS-1$
			} catch (TCException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return projectIds;
	}
	
	/*
	 * ��ȡ�汾ĳһ���Թ����Ķ���
	 */
	public static TCComponent[] getItemRevisionProjectIds(InterfaceAIFComponent revision,
			String property) {
		TCComponent[] projects = null;
		
		try {
		
			projects = ((TCComponentItemRevision)revision).
					getReferenceListProperty(property);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return projects;
	}
	
	/*
	 * ���ݶ��������Ҫ��ȡ���������ƻ�ȡ��Ӧ������ֵ���飻
	 * @param projects 
	 *         ҵ�����
	 * @param  property
	 *         ��������
	 * @author le.liu
	 */
	public static String[] getProjectInfos(TCComponent[] projects, String property) {
		String[] projectInfos = new String[projects.length];
		try {
			for (int i = 0; i < projects.length; i++) {
				projectInfos[i] = projects[i].getProperty(property);
			}
		} catch (TCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return projectInfos;
	}
	
}
