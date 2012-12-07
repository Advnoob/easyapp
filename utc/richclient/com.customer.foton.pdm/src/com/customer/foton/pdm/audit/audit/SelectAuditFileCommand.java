package com.customer.foton.pdm.audit.audit;

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
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCComponentType;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;
import com.teamcenter.rac.util.Utilities;

public class SelectAuditFileCommand extends AbstractAIFCommand 
	implements PropertyChangeListener{
	
	private AbstractAIFApplication application;
	private TCSession session;
	private InterfaceAIFComponent targetArray[];
	
	public SelectAuditFileCommand(AbstractAIFApplication currentApplication) {
		
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
//				��ȡ����ļ����ַ���
				String audit = itemrevision.getAuditInfo(true);
				System.out.println(audit);
//				��������ļ����ַ���
//				todo
				
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
