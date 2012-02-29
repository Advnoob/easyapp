package com.customer.fawvw.issues.commands.issueRel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import com.teamcenter.rac.aif.kernel.AIFComponentContext;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCException;

public class IssueRelComponentReportQueryStructNo {

	
	static ArrayList<String> queryStructNo(
			InterfaceAIFComponent[] targetArray) {
		ArrayList<String> arrayList = new ArrayList<String>();
		System.out.println(" before targetArray length = " + targetArray.length); //$NON-NLS-1$
		//ɸѡѡ�ж���
		targetArray = selectTarget(targetArray);
		System.out.println(" after  targetArray length = " + targetArray.length); //$NON-NLS-1$
		
		//��ȡѡ�ж����רҵ����
		LinkedList<InterfaceAIFComponent> queue = new LinkedList<InterfaceAIFComponent>();
		try {
			//ѭ��ѡ�еļ�������
			for (int i = 0; i < targetArray.length; i++) {
				//ѡ�еĶ�����ITEM
				if (targetArray[i] != null && targetArray[i] instanceof TCComponentItem){
					LinkedList<InterfaceAIFComponent> tempQueue = getChildComponent(targetArray[i]);
					for (InterfaceAIFComponent temp : tempQueue) {
						System.out.println("  type = " + temp.getType() //$NON-NLS-1$
							+ " name = " + temp.getProperty("object_name")); //$NON-NLS-1$ //$NON-NLS-2$
						queue.add(temp);
					}
				}
				//ѡ�еĶ�����ITEMREVISION
				if (targetArray[i] != null && targetArray[i] instanceof TCComponentItemRevision){
					
					LinkedList<InterfaceAIFComponent> tempQueue = getChildComponent(((TCComponentItemRevision) targetArray[i]).getItem());
					for (InterfaceAIFComponent temp : tempQueue) {
						System.out.println("  type = " + temp.getType() //$NON-NLS-1$
							+ " name = " + temp.getProperty("object_name")); //$NON-NLS-1$ //$NON-NLS-2$
						queue.add(temp);
					}
				}
				
//				if (targetArray[i] != null){
//					LinkedList<InterfaceAIFComponent> tempQueue = getChildComponent(targetArray[i]);
//					for (InterfaceAIFComponent temp : tempQueue) {
//						System.out.println("  type = " + temp.getType()
//							+ " name = " + temp.getProperty("object_name"));
//						queue.add(temp);
//					}
//				}
				
			}
			
			//��ȡ���Ķ�������ƺ��漰רҵ����ȡ�������arrayList
			for (InterfaceAIFComponent item : queue) {
				String str = ((TCComponentItemRevision)item).getProperty("fv9StructNo") + " " +  //$NON-NLS-1$ //$NON-NLS-2$
							((TCComponentItemRevision)item).getProperty("object_name"); //$NON-NLS-1$
				System.out.println("str = " + str); //$NON-NLS-1$
				arrayList.add(str);
			}
			
		} catch (TCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayList;
	}

	private static LinkedList<InterfaceAIFComponent> getChildComponent(InterfaceAIFComponent component) {
		LinkedList<InterfaceAIFComponent> queue = new LinkedList<InterfaceAIFComponent>();

		try {
			//�������FV9Component
			if ("FV9Component".equals(component.getType()) && hasChildCount(component) == 0) { //$NON-NLS-1$
				for (AIFComponentContext child : component.getChildren()) {
					InterfaceAIFComponent childComponent = child.getComponent();
					
					//��ȡitemRevision���ӽ��
					if ((InterfaceAIFComponent)childComponent instanceof TCComponentItemRevision){
						queue.add(childComponent);
						TCComponent[] views = ((TCComponent)childComponent).getReferenceListProperty("view"); //$NON-NLS-1$
						if (views.length > 0) {
							for (TCComponent view : views) {
								queue.add(view);
								
							}
						}
					}
				}
			}
			
			//�ܳɼ���FV9Component
			if ("FV9Component".equals(component.getType()) && hasChildCount(component) > 0){ //$NON-NLS-1$
				for (AIFComponentContext child : component.getChildren()) {
					InterfaceAIFComponent childComponent = child.getComponent();
					
					//��ȡitemRevision���ӽ��
					if ((InterfaceAIFComponent)childComponent instanceof TCComponentItemRevision){
						queue.add(childComponent);
						TCComponent[] views = ((TCComponent)childComponent).getReferenceListProperty("view"); //$NON-NLS-1$
						if (views.length > 0) {
							for (TCComponent view : views) {
//								queue.add(view);
								
								for (AIFComponentContext child2 : view.getChildren()) {
									InterfaceAIFComponent childComponent2 = child2.getComponent();
									
									//��ȡitemRevision���ӽ��
									if ((InterfaceAIFComponent)childComponent2 instanceof TCComponentItemRevision){
										queue.add(childComponent2);
										TCComponent[] views2 = ((TCComponent)childComponent2).getReferenceListProperty("view"); //$NON-NLS-1$
										if (views.length > 0) {
											for (TCComponent view2 : views2) {
												queue.add(view2);
												
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			//��ϵͳ����FV9SubSystem
			if ("FV9SubSystem".equals(component.getType()) && hasChildCount(component) > 0) { //$NON-NLS-1$
				//��ȡ��ϵͳ��������
				for (AIFComponentContext child : component.getChildren()) {
					InterfaceAIFComponent childComponent = child.getComponent();
					if ((InterfaceAIFComponent)childComponent instanceof TCComponentItemRevision){
						queue.add(childComponent);
						TCComponent[] views = ((TCComponent)childComponent).getReferenceListProperty("view"); //$NON-NLS-1$
						if (views.length > 0) {
							for (TCComponent view : views) {
								//��ȡ�ܳɼ�������
								for (AIFComponentContext child2 : view.getChildren()) {
									InterfaceAIFComponent childComponent2 = child2.getComponent();
									if ((InterfaceAIFComponent)childComponent2 instanceof TCComponentItemRevision){
										queue.add(childComponent2);
										TCComponent[] views2 = ((TCComponent)childComponent2).getReferenceListProperty("view"); //$NON-NLS-1$
										if (views2.length > 0) {
											for (TCComponent view2 : views2) {
												//��ȡ�����������
												for (AIFComponentContext child3 : view2.getChildren()) {
													InterfaceAIFComponent childComponent3 = child3.getComponent();
													if ((InterfaceAIFComponent)childComponent3 instanceof TCComponentItemRevision){
														queue.add(childComponent3);
														TCComponent[] views3 = ((TCComponent)childComponent3).getReferenceListProperty("view"); //$NON-NLS-1$
														if (views3.length > 0) {
															for (TCComponent view3 : views3) {
																queue.add(view3);
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
		} catch (TCException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return queue;
	}
	
	private static int hasChildCount(InterfaceAIFComponent component) {
		try {
			for (AIFComponentContext child : component.getChildren()) {
				InterfaceAIFComponent childComponent = child.getComponent();
				
				if ((InterfaceAIFComponent)childComponent instanceof TCComponentItemRevision){
					TCComponent[] views = ((TCComponent)childComponent).getReferenceListProperty("view"); //$NON-NLS-1$
					return views.length;
				}
					
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static InterfaceAIFComponent[] selectTarget(InterfaceAIFComponent[] targetArray) {
		try {
			for (int i = 0; i < targetArray.length; i++) {
				System.out.println(" select = i " + i); //$NON-NLS-1$
//				InterfaceAIFComponent[] child  = (InterfaceAIFComponent[]) targetArray[i].getChildren();
				for (int j = i+1; j < targetArray.length; j++) {
					System.out.println(" select j = " + j ); //$NON-NLS-1$
					if ((targetArray[j]!= null) && isParent(targetArray[i], targetArray[j])) {
						//�Ƴ�targetArray[j]
						targetArray[j] = null;
					}
				}
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return targetArray;
	}
	
	public static void isChild(InterfaceAIFComponent child, InterfaceAIFComponent[] targets) {
		for (int i = 0; i < targets.length; i++) {
			if (child == targets[i]){
//				targets.
			}
		}
		
	}
	
	public static boolean isParent(InterfaceAIFComponent parent,
			InterfaceAIFComponent child) {
		
		try {
			for (AIFComponentContext children : parent.getChildren()) {
				InterfaceAIFComponent childComponent = children.getComponent();
				if ((InterfaceAIFComponent)childComponent instanceof TCComponentItemRevision){
					TCComponent[] views = ((TCComponent)childComponent).getReferenceListProperty("view"); //$NON-NLS-1$
					if (views.length > 0) {
						for (TCComponent view : views) {
							if (view == child){
								System.out.println(" is parent "); //$NON-NLS-1$
								return true;
							}
							
						}
					}
				}
			}
		} catch (TCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" is not a parent "); //$NON-NLS-1$
		return false;
	}
}
