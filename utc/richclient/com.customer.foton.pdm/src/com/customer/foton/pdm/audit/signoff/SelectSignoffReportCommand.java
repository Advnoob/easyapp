package com.customer.foton.pdm.audit.signoff;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.Random;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.AbstractAIFCommand;
import com.teamcenter.rac.aif.kernel.AIFComponentContext;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCComponentProcess;
import com.teamcenter.rac.kernel.TCComponentSignoff;
import com.teamcenter.rac.kernel.TCComponentTask;
import com.teamcenter.rac.kernel.TCComponentType;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCProperty;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.providers.delegates.type.EPMTaskLabelProviderDelegate;
import com.teamcenter.rac.util.MessageBox;
import com.teamcenter.rac.util.Utilities;

public class SelectSignoffReportCommand extends AbstractAIFCommand 
	implements PropertyChangeListener{
	
	private AbstractAIFApplication application;
	private TCSession session;
	private InterfaceAIFComponent targetArray[];
	
	public SelectSignoffReportCommand(AbstractAIFApplication currentApplication) {
		
		this.application = currentApplication;
		this.session = (TCSession) application.getSession();
		
		try {
			targetArray = super.checkComponents(application
					.getTargetComponents());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Job job = new Job("������...") { 
			protected IStatus run(IProgressMonitor monitor) {
				try {
					if (doValidate()) {
						operation(targetArray[0]);
						MessageBox.post(
								"�ɹ����", 
								getTitle(), 
								MessageBox.INFORMATION); 
					}
					
				} catch (Exception exception) {
					MessageBox.post(Utilities.getCurrentFrame(), exception);
				}
				
				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}
	
	private void operation(InterfaceAIFComponent target) {
		if (target instanceof TCComponentItemRevision) {
			TCComponentItemRevision itemrevision = (TCComponentItemRevision)target;
			try {
//				��ȡ���������
//				TCProperty[] properties = itemrevision.getAllTCProperties();
//				if(properties != null && properties.length > 0) {
//					for (int i=0; i<properties.length; i++) {
//						System.out.println(properties[i].toString());
//						System.out.println(properties[i].getName());
//					}
//				}
				
//				��ȡ�δ�����
				AIFComponentContext where_references[] = itemrevision.whereReferenced();
				if (where_references != null && where_references.length > 0) {
					for (int i=0; i<where_references.length; i++) {
						AIFComponentContext context = where_references[i];
						InterfaceAIFComponent component = context.getComponent();
						System.out.println(context + " " + component.getType());
//						��ȡ����
						if (component instanceof TCComponentProcess) {
							TCComponentProcess task = (TCComponentProcess)component;
//							����ʵ��
							TCComponentTask root = task.getRootTask();
							System.out.println(root.getName());
							AIFComponentContext[] children = root.getChildren();
							if (children != null && children.length > 0) {
								for (int k=0; k<children.length; k++) {
									System.out.println(k);
//									AIFComponentContext child = children[k];
//									child.
								}
							}
							
//							TCComponentSignoff[] signoffs = root.getUserSignoffs();
//							if (signoffs != null && signoffs.length > 0) {
//								for (int j=0; j<signoffs.length; j++) {
//									TCComponentSignoff signoff = signoffs[j];
//								}
//							}
							
							
//							component.
						}
					}
				}

				
				
//				TCComponent[] commponents = itemrevision.getRelatedComponents();
//				itemrevision.getRelatedComponents("");
//				if(commponents != null && commponents.length > 0) {
//					for (int i=0; i<commponents.length; i++) {
//						System.out.println(commponents[i].getClassType());
//					}
//				}
				
//				TCComponentType[] tccompoent_types = itemrevision.getAssignableParticipantTypes();
//				if (tccompoent_types != null && tccompoent_types.length > 0) {
//					for (int l = 0; l<tccompoent_types.length; l++){
//						TCComponentType type = tccompoent_types[l];
////						if (type instanceof ProposedReviewer)
//
//					}
//				}
				
			} catch (TCException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private Boolean doValidate(){
		if(targetArray.length > 1){
			MessageBox.post(
					"ֻ��ѡ��һ������", 
					getTitle(), 
					MessageBox.INFORMATION); 
			return false;
		}
		
		if (targetArray.length < 1) {
			MessageBox.post(
					"��ѡ��һ�������ʹ�ñ�����", 
					getTitle(), 
					MessageBox.INFORMATION);
			return false;
		}
				
		return true;
	}

	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public String getTitle() {
		return "����ļ�"; //$NON-NLS-1$
	}

}
