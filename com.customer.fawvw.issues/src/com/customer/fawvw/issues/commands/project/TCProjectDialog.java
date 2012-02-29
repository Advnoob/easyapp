package com.customer.fawvw.issues.commands.project;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.customer.fawvw.issues.AbstractFawvwPepCommand;
import com.teamcenter.rac.aif.InterfaceAIFOperationListener;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.common.AbstractTCCommandDialog;
import com.teamcenter.rac.common.TCConstants;
import com.teamcenter.rac.common.lov.LOVComboBox;
import com.teamcenter.rac.kernel.TCComponentProject;
import com.teamcenter.rac.kernel.TCComponentUser;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.DateButton;
import com.teamcenter.rac.util.FilterDocument;
import com.teamcenter.rac.util.HorizontalLayout;
import com.teamcenter.rac.util.MessageBox;
import com.teamcenter.rac.util.Painter;
import com.teamcenter.rac.util.PropertyLayout;
import com.teamcenter.rac.util.Registry;
import com.teamcenter.rac.util.VerticalLayout;

public class TCProjectDialog extends AbstractTCCommandDialog implements
		InterfaceAIFOperationListener, PropertyChangeListener {

	private JPanel inputPanel;

	private JPanel leftPanel;

	private JLabel platformLabel;

	private LOVComboBox platformComboBox;

	private JLabel levelLabel;

	private LOVComboBox levelComboBox;

	private JLabel typeLabel;

	private LOVComboBox typeComboBox;

	private JButton stopButton;
	
	private InterfaceAIFComponent[] pasteTargets;

	private TCSession session;

	private Registry registry;
	
	public  final String LOV_PLATFORM = "FV9PlatformType"; //$NON-NLS-1$
	
	public  final String LOV_LEVEL = "FV9CarRank"; //$NON-NLS-1$
	
	public  final String LOV_TYPE = "FV9DevelopmentType"; //$NON-NLS-1$

	private JLabel modelCode;

	private LOVComboBox modelCodeComboBox;

	private JLabel intProjName;

	private JTextField intProjNameField;

	private JLabel SOPPlTimeLabel;

	private DateButton SOPPlTime;

	private JLabel SOPActTimeLabel;

	private DateButton SOPActTime;

	public TCProjectDialog(AbstractFawvwPepCommand command) {
		super(command.getFrame(), command);
		this.session = command.getSession();
		this.registry = Registry.getRegistry(TCProjectDialog.class);
		pasteTargets = command.getTargetArray();
		initUI(pasteTargets);
	}

	private static final long serialVersionUID = -8124904173460000037L;


	public void initUI(InterfaceAIFComponent[] projectInfos) {
		super.initUI();
		stopTimer();
		setTitle(this.getTitle());
		inputPanel = new JPanel(new PropertyLayout());

		try {
			TCComponentProject projectInfo = (TCComponentProject)projectInfos[0];
			
			String fv9PrjProductNo = projectInfo.getProperty("fv9PrjProductNo"); //$NON-NLS-1$
			System.out.println("ԭ��Ʒ��ţ�" + fv9PrjProductNo); //$NON-NLS-1$
			
			String fv9PrjPlatform = projectInfo.getProperty("fv9PrjPlatform"); //$NON-NLS-1$
			System.out.println("ԭ����ƽ̨��" + fv9PrjPlatform); //$NON-NLS-1$
			
			String fv9PrjProductName = projectInfo.getProperty("fv9PrjProductName"); //$NON-NLS-1$
			System.out.println("ԭ��Ʒ���ƣ�" + fv9PrjProductName); //$NON-NLS-1$
			
			String fv9PrjProductRank = projectInfo.getProperty("fv9PrjProductRank");  //$NON-NLS-1$
			System.out.println("ԭ���ͼ���" + fv9PrjProductRank); //$NON-NLS-1$
			
			String fv9PrjDevelopmentType = projectInfo.getProperty("fv9PrjDevelopmentType"); //$NON-NLS-1$
			System.out.println("ԭ��Ŀ���ͣ�" + fv9PrjDevelopmentType); //$NON-NLS-1$
			
			String fv9ModelCode = projectInfo.getProperty("fv9ModelCode"); //$NON-NLS-1$
			System.out.println("ԭ���ʹ��룺" + fv9ModelCode); //$NON-NLS-1$
			
			String fv9IntProjName = projectInfo.getProperty("fv9IntProjName"); //$NON-NLS-1$
			System.out.println("ԭ��Ŀ�ڲ����ƣ�" + fv9IntProjName); //$NON-NLS-1$
			
			Date fv9SOPPlTime = projectInfo.getDateProperty("fv9SOPPlTime"); //$NON-NLS-1$
			System.out.println("SOP�ƻ�ʱ�䣺" + fv9SOPPlTime); //$NON-NLS-1$
			
			Date fv9SOPActTime = projectInfo.getDateProperty("fv9SOPActTime"); //$NON-NLS-1$
			System.out.println("SOPʵ�����ʱ�䣺" + fv9SOPActTime); //$NON-NLS-1$

			/* ����ƽ̨ */
			platformLabel = new JLabel("����ƽ̨��"); //$NON-NLS-1$
			platformComboBox = new LOVComboBox(session,this.LOV_PLATFORM);
			if (!"".equals(fv9PrjPlatform)) { //$NON-NLS-1$
				platformComboBox.setSelectedString(fv9PrjPlatform);
			}
			
			/*���ʹ���*/
			modelCode = new JLabel("���ʹ��룺"); //$NON-NLS-1$
			modelCodeComboBox = new LOVComboBox(session, "FV9ModelCode"); //$NON-NLS-1$
			if (!"".equals(fv9ModelCode)) { //$NON-NLS-1$
				modelCodeComboBox.setSelectedString(fv9ModelCode);
			}
			
			/*��Ŀ�ڲ�����*/
			intProjName = new JLabel("��Ŀ�ڲ����ƣ�"); //$NON-NLS-1$
			if (!"".equals(fv9IntProjName)) { //$NON-NLS-1$
				intProjNameField = createTextField(fv9IntProjName, 20, TCConstants
						.getDefaultMaxNameSize(session), false);	
			} else {
				intProjNameField = createTextField("", 20, TCConstants //$NON-NLS-1$
						.getDefaultMaxNameSize(session), false);	
			}

			/* ���ͼ��� */
			levelLabel = new JLabel("���ͼ���");	 //$NON-NLS-1$
			levelComboBox = new LOVComboBox(this.session,this.LOV_LEVEL);
			if (!"".equals(fv9PrjProductRank)) { //$NON-NLS-1$
				levelComboBox.setSelectedString(fv9PrjProductRank);
			} 

			/* ��Ŀ���� */
			typeLabel = new JLabel("��Ŀ���ͣ�"); //$NON-NLS-1$
			typeComboBox = new LOVComboBox(this.session,this.LOV_TYPE);
			if (!"".equals(fv9PrjDevelopmentType)) { //$NON-NLS-1$
				typeComboBox.setSelectedString(fv9PrjDevelopmentType);
			}
			
			/*SOP�ƻ�ʱ��*/
			SOPPlTimeLabel = new JLabel("SOP�ƻ�ʱ�䣺"); //$NON-NLS-1$
			SOPPlTime = new DateButton(new SimpleDateFormat("yyyy-MM-dd")); //$NON-NLS-1$
			if (fv9SOPPlTime != null) { //$NON-NLS-1$
				SOPPlTime.setDate(fv9SOPPlTime);
			}
			
			/*SOPʵ�����ʱ��*/
			SOPActTimeLabel = new JLabel("SOPʵ�����ʱ�䣺"); //$NON-NLS-1$
			SOPActTime = new DateButton(new SimpleDateFormat("yyyy-MM-dd")); //$NON-NLS-1$
			if (fv9SOPActTime != null) { //$NON-NLS-1$
				SOPActTime.setDate(fv9SOPActTime);
			}

		} catch (TCException e) {
			// TODO Auto-generated catch block
			System.out.println("------------------load TCProject error--------------------"); //$NON-NLS-1$
			e.printStackTrace();
		}

		stopButton = new JButton(registry.getString("stop")); //$NON-NLS-1$
		stopButton.setVisible(false);
		stopButton.setMnemonic(registry.getString("stop.MNEMONIC").charAt(0)); //$NON-NLS-1$
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				stopPressed();
			}
		});

		inputPanel.add("1.1.right.top.preferred.preferred", platformLabel); //$NON-NLS-1$
		inputPanel.add("1.2.center.center.preferred.preferred", //$NON-NLS-1$
				platformComboBox);
		
		inputPanel.add("2.1.right.top.preferred.preferred", modelCode); //$NON-NLS-1$
		inputPanel.add("2.2.center.center.preferred.preferred", //$NON-NLS-1$
				modelCodeComboBox);
		
		inputPanel.add("3.1.right.top.preferred.preferred", intProjName); //$NON-NLS-1$
		inputPanel.add("3.2.center.center.preferred.preferred", //$NON-NLS-1$
				intProjNameField);
		
		inputPanel.add("4.1.right.top.preferred.preferred", levelLabel); //$NON-NLS-1$
		inputPanel.add("4.2.center.center.preferred.preferred", levelComboBox); //$NON-NLS-1$
		
		inputPanel.add("5.1.right.top.preferred.preferred", typeLabel); //$NON-NLS-1$
		inputPanel.add("5.2.center.center.preferred.preferred", typeComboBox); //$NON-NLS-1$
	
		inputPanel.add("6.1.right.top.preferred.preferred", SOPPlTimeLabel); //$NON-NLS-1$
		inputPanel.add("6.2.center.center.preferred.preferred", SOPPlTime); //$NON-NLS-1$
		
		inputPanel.add("7.1.right.top.preferred.preferred", SOPActTimeLabel); //$NON-NLS-1$
		inputPanel.add("7.2.center.center.preferred.preferred", SOPActTime); //$NON-NLS-1$
		
		buttonPanel.add(stopButton);
		leftPanel = new JPanel(new VerticalLayout());
		leftPanel.add("unbound.bind.center.top", inputPanel); //$NON-NLS-1$
		mainPanel.setLayout(new HorizontalLayout());
		mainPanel.add("unbound.bind.left.top", leftPanel); //$NON-NLS-1$

		pack();
		centerToScreen(1.0D, 0.75D);
//		nameTextField.requestFocusInWindow();
		startTimer();
	}

	/* ��֤�ĶԻ��� */
	private JTextField createTextField(String s, int i, int j, boolean flag) {
		int k = i;
		if (k <= 0) {
			k = 10;
		}
		String s1 = s;
		if (s1 == null) {
			s1 = ""; //$NON-NLS-1$
		}

		FilterDocument filterdocument = new FilterDocument(j, TCSession
				.getServerEncodingName(session));

		if (flag) {
			return new JTextField(filterdocument, s1, k) {
				private static final long serialVersionUID = -1096092937246723413L;

				public void paint(Graphics g) {
					super.paint(g);
					Painter.paintIsRequired(this, g);
				}
			};
		} else {
			return new JTextField(filterdocument, s1, k);
		}
	}

	@Override
	public boolean showCloseButton() {
		return false;
	}

	@Override
	/* ���ȷ����Ӧ�ô������¼� */
	public void startCommandOperation() {
		
		Job job = new Job("������...") {  //$NON-NLS-1$
			protected IStatus run(IProgressMonitor monitor) {
				try {
					if(doValidate()){
						
						//�жϵ�ǰ�û��Ƿ�߱�д��Ȩ��
						if (doOperation()) {
							MessageBox.post("����ִ�гɹ�", "��Ŀ������չ", MessageBox.INFORMATION); //$NON-NLS-1$ //$NON-NLS-1$
						} else {
							MessageBox.post("�����߱�����Ŀ��д��Ȩ�ޣ�", "��Ŀ������չ", MessageBox.INFORMATION); //$NON-NLS-1$ //$NON-NLS-1$
						}
						
						if(okOrApply == 0){
							disposeDialog();
						}
					}
					
				} catch (Exception e) {
					MessageBox.post(e.getMessage(),"����", MessageBox.ERROR);    //$NON-NLS-1$
				}

				return Status.OK_STATUS;
			}
		};
		job.schedule();
		
	}
	/*
	 * ��ʼִ�д����߼�
	 * 
	 * */
	private boolean doOperation() {
		try {
//			System.out.println("TCComponentProject--"+((TCComponentProject)pasteTargets[0]).getType()); //$NON-NLS-1$
//			TCComponentProject currentPro = (TCComponentProject)pasteTargets[0];
//			TCComponentUser paUser = currentPro.getPAUser(); //������Ŀ���û�
//			TCComponentUser currUser = session.getUser();  //��ǰ�û�
			//������Ŀ���û��ž���дȨ��
//			if (paUser == currUser) {
//				System.out.println("--------yes--------"); //$NON-NLS-1$
//				System.out.println(Messages.TCProjectDialog_32);
//				
//				((TCComponentProject)pasteTargets[0]).setProperty("fv9PrjProductNo", IdTextField.getText().trim()); //$NON-NLS-1$
//				((TCComponentProject)pasteTargets[0]).setProperty("fv9PrjPlatform", platformComboBox.getSelectedItem().toString().trim()); //$NON-NLS-1$
//				((TCComponentProject)pasteTargets[0]).setProperty("fv9PrjProductName", nameTextField.getText().trim()); //$NON-NLS-1$
//				((TCComponentProject)pasteTargets[0]).setProperty("fv9PrjProductRank", levelComboBox.getSelectedItem().toString().trim()); //$NON-NLS-1$
//				((TCComponentProject)pasteTargets[0]).setProperty("fv9PrjDevelopmentType", typeComboBox.getSelectedItem().toString().trim()); //$NON-NLS-1$
//				
//				System.out.println("----------successful---------------"); //$NON-NLS-1$
//				return true;
//				
//			} else {
//				System.out.println("-------error---------"); //$NON-NLS-1$
//				System.out.println(Messages.TCProjectDialog_39);
//				return false;
//			}
			System.out.println("������ƽ̨��" + platformComboBox.getSelectedItem().toString()); //$NON-NLS-1$
			System.out.println("�µĳ��ʹ��룺" + modelCodeComboBox.getSelectedItem().toString()); //$NON-NLS-1$
			System.out.println("�µ���Ŀ�ڲ����ƣ�" + intProjNameField.getText()); //$NON-NLS-1$
			System.out.println("�³��͵ȼ���" + levelComboBox.getSelectedItem().toString()); //$NON-NLS-1$
			System.out.println("����Ŀ���ͣ�" + typeComboBox.getSelectedItem().toString()); //$NON-NLS-1$
			System.out.println("SOP�ƻ�ʱ�䣺" + SOPPlTime.getDateString()); //$NON-NLS-1$
			System.out.println("SOPʵ�����ʱ�䣺" + SOPActTime.getDateString()); //$NON-NLS-1$
			
			((TCComponentProject)pasteTargets[0]).setProperty("fv9PrjPlatform", platformComboBox.getSelectedItem().toString()); //$NON-NLS-1$
			((TCComponentProject)pasteTargets[0]).setProperty("fv9ModelCode", modelCodeComboBox.getSelectedItem().toString()); //$NON-NLS-1$
			((TCComponentProject)pasteTargets[0]).setProperty("fv9IntProjName", intProjNameField.getText()); //$NON-NLS-1$
			((TCComponentProject)pasteTargets[0]).setProperty("fv9PrjProductRank", levelComboBox.getSelectedItem().toString()); //$NON-NLS-1$
			((TCComponentProject)pasteTargets[0]).setProperty("fv9PrjDevelopmentType", typeComboBox.getSelectedItem().toString()); //$NON-NLS-1$
			
			if (SOPPlTime.getDate() != null) {
				((TCComponentProject)pasteTargets[0]).setDateProperty("fv9SOPPlTime", SOPPlTime.getDate()); //$NON-NLS-1$
			} else {
				((TCComponentProject)pasteTargets[0]).setDateProperty("fv9SOPPlTime", null); //$NON-NLS-1$
			}
			
			if (SOPActTime.getDate() != null) {
				((TCComponentProject)pasteTargets[0]).setDateProperty("fv9SOPActTime", SOPActTime.getDate()); //$NON-NLS-1$
			} else {
				((TCComponentProject)pasteTargets[0]).setDateProperty("fv9SOPActTime", null); //$NON-NLS-1$
			}
			
			
			System.out.println("д��ɹ�"); //$NON-NLS-1$
			
			return true;
			
		} catch (TCException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	
	public void propertyChange(PropertyChangeEvent evt) {
		String s = evt.getPropertyName();
		if (s.equals("Tc Stop Operation")) { //$NON-NLS-1$
			Thread thread = new Thread() {

				public void run() {
					stopButton.setEnabled(true);
					stopButton.doClick();
				}

			};
			thread.start();
		}
	}

	/* ��֤ */
	public boolean isPerformable() {
//		return nameTextField.getText().length() > 0;
		
		return true;
	}

	/* ȡ����ť */
	public void stopPressed() {
		Registry registry = Registry.getRegistry(this);

		stopButton.setText(registry.getString("aborting")); //$NON-NLS-1$
		stopButton.setEnabled(false);
		setCursor(Cursor.getPredefinedCursor(3));

//		nameTextField.setEnabled(false);
		setCursor(Cursor.getPredefinedCursor(0));

		MessageBox.post(parentFrame, registry.getString("aborted"), registry //$NON-NLS-1$
				.getString("aborted.TITLE"), MessageBox.INFORMATION); //$NON-NLS-1$

		fireStopOperation();
		disposeDialog();
	}

	/* �رմ���ĶԻ��� */
	public void windowClosingPressed() {
		if (stopButton.isVisible()) {
			stopButton.doClick();
		} else {
			cancelButton.doClick();
		}
	}
	
	/*ִ�в���ǰ����֤
	 * 
	 * 1���Ƿ�ѡ��Ҫ�����Ĺ��̶���
	 * 2������LOV�Ŀؼ��Ƿ��ܻ��ϵͳ����
	 * 3��ѡ�еĹ��������Ƿ�����޸�
	 * */
	public boolean doValidate() {
		//����ѡ��һ����Ŀ
		if(pasteTargets.length!=1){
			MessageBox.post("��ѡ��Ҫ��չ����Ŀ", "��ʾ", MessageBox.INFORMATION); //$NON-NLS-1$ //$NON-NLS-1$
			return false;
		}
		
		//����ƽ̨����Ϊ��
		if ("".equals(platformComboBox.getSelectedItem().toString()) ||  //$NON-NLS-1$
				platformComboBox.getSelectedItem().toString() == null) {
			MessageBox.post(
					"��ѡ����Ŀ����ƽ̨",  //$NON-NLS-1$
					getTitle(), 
					MessageBox.INFORMATION);
			return false;
		}
		
		//���ʹ��벻��Ϊ��
		if ("".equals(modelCodeComboBox.getSelectedItem().toString()) || //$NON-NLS-1$
				modelCodeComboBox.getSelectedItem().toString() == null) {
			MessageBox.post(
					"��ѡ����Ŀ���ʹ���",  //$NON-NLS-1$
					getTitle(), 
					MessageBox.INFORMATION);
			return false;
		}
		
		//���ͼ�����Ϊ��
		if ("".equals(levelComboBox.getSelectedItem().toString()) || //$NON-NLS-1$
				levelComboBox.getSelectedItem().toString() == null) {
			MessageBox.post(
					"��ѡ����Ŀ���ͼ���",  //$NON-NLS-1$
					getTitle(), 
					MessageBox.INFORMATION);
			return false;
		}
		
		//��Ŀ���Ͳ���Ϊ��
		if ("".equals(typeComboBox.getSelectedItem().toString()) || //$NON-NLS-1$
				typeComboBox.getSelectedItem().toString() == null) {
			MessageBox.post(
					"��ѡ����Ŀ����",  //$NON-NLS-1$
					getTitle(), 
					MessageBox.INFORMATION);
			return false;
		}
		
		return true;
	}
	
//	public void endOperation() {
//		
//			if (okOrApply == 0) {
//				disposeDialog();
//			}
//		
//	}
	
	public String getTitle() {
		return "��Ŀ������չ"; //$NON-NLS-1$
	}
	
}
