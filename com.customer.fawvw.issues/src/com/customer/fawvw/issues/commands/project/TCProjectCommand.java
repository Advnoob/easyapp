package com.customer.fawvw.issues.commands.project;

import com.customer.fawvw.issues.AbstractFawvwPepCommand;
import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.common.AbstractTCCommandDialog;
import com.teamcenter.rac.kernel.TCComponentProject;
import com.teamcenter.rac.kernel.TCComponentUser;
import com.teamcenter.rac.util.MessageBox;

public class TCProjectCommand extends AbstractFawvwPepCommand {

	public TCProjectCommand(AbstractAIFApplication abstractaifapplication) {
		super(abstractaifapplication);
		
	}

	@Override
	public AbstractTCCommandDialog getCommandDialog() {
		try {
			if (super.getTargetArray().length != 1) {
				MessageBox.post("��ѡ��ǰҪ��չ����Ŀ", "��Ŀ����", MessageBox.INFORMATION);
			} else {
				InterfaceAIFComponent selected = (InterfaceAIFComponent)super.getTargetArray()[0];
				if (selected instanceof TCComponentProject) {
					
					
					TCComponentUser paUser = ((TCComponentProject)selected).getPAUser(); //������Ŀ���û�
					TCComponentUser currUser = this.getSession().getUser();  //��ǰ�û�
					//������Ŀ���û��ž���дȨ��
					if (paUser == currUser) {
						System.out.println("------------��Ŀ������չ-------------");  //$NON-NLS-1$
						System.out.println("����д��Ȩ��"); //$NON-NLS-1$
						
						return new TCProjectDialog(this);
					} else {
						System.out.println("------------��Ŀ������չ-------------");   //$NON-NLS-1$
						System.out.println("�㲻�߱�д��Ȩ��"); //$NON-NLS-1$
						MessageBox.post("", "", MessageBox.INFORMATION); //$NON-NLS-1$ //$NON-NLS-1$ //$NON-NLS-2$
						
						return null;
					}
		
				} else{
					 MessageBox.post("��ѡ��ǰҪ��չ����Ŀ", "��Ŀ����", MessageBox.INFORMATION); //$NON-NLS-1$ //$NON-NLS-1$
					 return null;
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return null;
	}
}
