package com.customer.fawvw.issues.status;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.teamcenter.rac.aif.kernel.AIFComponentContext;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.providers.delegates.DefaultLabelProviderDelegate;

public class UpdateItemPic extends DefaultLabelProviderDelegate
{

	//Itemǰ��ͼƬ
	public Image getImage(Object arg0) {
		return AbstractUIPlugin.imageDescriptorFromPlugin(
				"com.customer.fawvw.issues", "coins/itemrevision.png").createImage();
	}

	//Item���ͼƬ
	public Image[] getTrailingImages(Object object)  {

		TCComponentItemRevision ir = (TCComponentItemRevision) 
			((AIFComponentContext) object).getComponent(); 
	
		Image image = null;
		Image release = null;
		try {
			if (!"".equals(ir.getProperty("fv9RGStatus")) 
					&& ir.getProperty("fv9RGStatus") != null){ 
				if("��".equals(ir.getProperty("fv9RGStatus"))){  
					image = AbstractUIPlugin.imageDescriptorFromPlugin(
								"com.customer.fawvw.issues", "coins/redTag.png") 
								.createImage();
				}
				if("��".equals(ir.getProperty("fv9RGStatus"))){  
					image = AbstractUIPlugin.imageDescriptorFromPlugin(
								"com.customer.fawvw.issues", "coins/yellowTag.png") 
								.createImage();
				}
				if("��".equals(ir.getProperty("fv9RGStatus"))){ 
					image = AbstractUIPlugin.imageDescriptorFromPlugin(
								"com.customer.fawvw.issues", "coins/greenTag.png") 
								.createImage();
				}
			} else {
				  image = AbstractUIPlugin.imageDescriptorFromPlugin(
							"com.customer.fawvw.issues", "coins/white.gif") 
							.createImage();
			}

			if (!"".equals(ir.getProperty("release_status_list"))
					&& (ir.getProperty("release_status_list").contains("TCM �ѷ���")) ||
						(ir.getProperty("release_status_list").contains("TCM Released"))) {
				release = AbstractUIPlugin.imageDescriptorFromPlugin(
						"com.customer.fawvw.issues", "coins/released.png") 
						.createImage();
			}
			
		} catch (TCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (release == null) {
			//�޷���״̬
			return new Image[] { image};
		} else {
			//�ѷ���
			return new Image[] { image, release };
		}
		
	}
}
