package com.customer.fawvw.issues;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.customer.fawvw.issues.commands.project.TCProjectDialog;
import com.teamcenter.rac.aif.InterfaceAIFOperationListener;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.common.AbstractTCCommandDialog;
import com.teamcenter.rac.common.TCConstants;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.FilterDocument;
import com.teamcenter.rac.util.HorizontalLayout;
import com.teamcenter.rac.util.MessageBox;
import com.teamcenter.rac.util.Painter;
import com.teamcenter.rac.util.PropertyLayout;
import com.teamcenter.rac.util.Registry;
import com.teamcenter.rac.util.VerticalLayout;

public abstract class AbstractFawvwPepDialog extends AbstractTCCommandDialog
		implements InterfaceAIFOperationListener, PropertyChangeListener {

	private JPanel inputPanel2;

	private JPanel leftPanel;

	private JLabel savePathLabel;

	private JTextField savePathTextField;

	private JButton savePathButton;

	private JCheckBox openOnCreateCheckBox;

	private JButton stopButton;

	private TCSession session;

	private Registry registry;

	/**
	 * ���ڶ���������swing���,�Ի������Ķ����ʼ������
	 */
	public abstract void doInitUI();
	/**
	 * ���ɶ�Ӧҵ���Excel
	 * 
	 * @throws Exception
	 */
	public abstract void generateExcel() throws Exception;

	/**
	 * ��öԻ��������
	 */
	public abstract String getTitle();

	/**
	 * ��֤�û�������Ϣ��Ч��
	 * 
	 * @return ����trueʱִ������Excel����
	 */
	public abstract boolean doValidate();

	/**
	 * ���õ�swing���,��ɶԻ����ʼ������
	 */

	
	protected InterfaceAIFComponent[] targets;

	private static final long serialVersionUID = 134446190871322870L;

	public AbstractFawvwPepDialog(AbstractFawvwPepCommand command) {
		super(command.getFrame(), command);
		this.session = command.getSession();
		this.targets = command.getTargetArray();
		this.registry = Registry.getRegistry(TCProjectDialog.class);
		initUI();
	}

	public void initUI() {
		super.initUI();
		stopTimer();
		setTitle(this.getTitle());
		inputPanel2 = new JPanel(new PropertyLayout());
		dialogIcon.setIcon(registry.getImageIcon("newDataset.ICON")); //$NON-NLS-1$

		/* ����λ�� */
//		savePathLabel = new JLabel(Messages.IssueReportDialog_savePath);
//		savePathTextField = createTextField("", 20, TCConstants //$NON-NLS-1$
//				.getDefaultMaxNameSize(session), true);
//		savePathTextField.setEditable(false);
//		savePathButton = new JButton(Messages.IssueReportDialog_view);
//		savePathButton.addActionListener(new ActionListener() {
//
//			public void actionPerformed(ActionEvent arg0) {
//				JFileChooser jChooser = new JFileChooser();
//				// ����Ĭ�ϵĴ�Ŀ¼,�������Ļ�����window��Ĭ��Ŀ¼(�ҵ��ĵ�)
//				jChooser.setCurrentDirectory(new File(
//						Messages.IssueReportDialog_defaultPath));
//				// ���ô��ļ�����,�˴����ó�ֻ��ѡ���ļ��У�����ѡ���ļ�
//				jChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);// ֻ�ܴ��ļ���
//				// ��һ���Ի���
//				int index = jChooser.showDialog(null,
//						Messages.IssueReportDialog_Open);
//				if (index == JFileChooser.APPROVE_OPTION) {
//					// �ѻ�ȡ�����ļ��ľ���·����ʾ���ı��༭����
//					savePathTextField.setText(jChooser.getSelectedFile()
//							.getAbsolutePath());
//				}
//
//			}
//		});
		
		//������ɺ������־
//		openOnCreateCheckBox = new JCheckBox(Messages.IssueReportDialog_OpenLog);
//		boolean isOpenCreate = false;
//		try {
//			String s2 = session.getPreferenceService().getString(0,
//					"OpenAfterCreatePref"); //$NON-NLS-1$
//			isOpenCreate = s2.equalsIgnoreCase("1"); //$NON-NLS-1$
//		} catch (Exception exception) {
//		}
//		openOnCreateCheckBox.setSelected(isOpenCreate);

		stopButton = new JButton(registry.getString("stop")); //$NON-NLS-1$
		stopButton.setVisible(false);
		stopButton.setMnemonic(registry.getString("stop.MNEMONIC").charAt(0)); //$NON-NLS-1$
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionevent) {
				stopPressed();
			}
		});

//		inputPanel2.add("1.1.right.top.preferred.preferred", savePathLabel); //$NON-NLS-1$
//		inputPanel2.add("1.2.center.center.preferred.preferred", savePathTextField); //$NON-NLS-1$
//		inputPanel2.add("1.3.right.top.preferred.preferred", savePathButton); //$NON-NLS-1$

		buttonPanel.add(stopButton);
		leftPanel = new JPanel(new VerticalLayout());
//		leftPanel.add("bottom.nobind.right", openOnCreateCheckBox); //$NON-NLS-1$
		leftPanel.add("bottom.nobind.center", inputPanel2); //$NON-NLS-1$
		mainPanel.setLayout(new HorizontalLayout());
		mainPanel.add("unbound.bind.left.top", leftPanel); //$NON-NLS-1$
		doInitUI();
		pack();
		centerToScreen(1.0D, 0.75D);
		startTimer();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.teamcenter.rac.common.AbstractTCCommandDialog#showCloseButton()
	 */

	@Override
	public boolean showCloseButton() {
		return false;
	}

	@Override
	/* ���ȷ����Ӧ�ô������¼� */
	public void startCommandOperation() {
		Job job = new Job("����������") { //$NON-NLS-1$
			protected IStatus run(IProgressMonitor monitor) {
				try {
					if (doValidate()) {
						if (okOrApply == 0) {
							
							generateExcel();
							
							MessageBox.post(
									"����ִ�гɹ�", //$NON-NLS-1$
									getTitle(),
									MessageBox.INFORMATION);
							
							disposeDialog();
							
						}
						
					}
				} catch (Exception e) {
					e.printStackTrace();
					MessageBox.post(e.getMessage(),//  "����ִ��ʧ��"
							"����ִ��ʧ��", MessageBox.ERROR); //$NON-NLS-1$
				}
				return Status.OK_STATUS;
			}
		};
		job.schedule();
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
		return true;
	}

	/* ȡ����ť */
	public void stopPressed() {
		Registry registry = Registry.getRegistry(this);

		stopButton.setText(registry.getString("aborting")); //$NON-NLS-1$
		stopButton.setEnabled(false);
		setCursor(Cursor.getPredefinedCursor(3));

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

	/* ��֤�ĶԻ��� */
	public JTextField createTextField(String s, int i, int j, boolean flag) {
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

	public TCSession getSession() {
		return session;
	}

	public JTextField getSavePathTextField() {
		return savePathTextField;
	}

	public JPanel getLeftPanel() {
		return leftPanel;
	}
	
	public boolean getOpenOnCreateFlag() {
		return openOnCreateCheckBox.isSelected();
	}
}
