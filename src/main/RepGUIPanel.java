package main;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JTabbedPane;

import framework.ServiceLocator;
import framework.Session;
import order_management.Crd;
import order_management.Rep;
import order_management.RepState;

import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.FlowLayout;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class RepGUIPanel extends JPanel {
	private String selected;
	JButton btnDelete;
	JList list;
	JButton btnSendToAdmin;
	JButton btnSendToFm;
	JButton btnApprove;
	JButton btnReject;
	JButton btnSendForExecution;
	private JTextField scsoCommentField;
	private JLabel lblNewLabel;
	private JTextField fmCommentsTxtField;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	
	

	/**
	 * Create the panel.
	 */
	public RepGUIPanel(Login login, Session session) {
		final Login prev = login;
		final Session sess = session;
		
		ArrayList<String> roles = ServiceLocator.getSecurityService().getRoles(sess.getCurrentUser());
		ArrayList<Rep> repList = new ArrayList<Rep>();
		
		//get work list based on the role
		if(roles.contains("scso") || roles.contains("vp")){
			repList = ServiceLocator.getRepService().listRep("all");
		}else if(roles.contains("cso")){
			repList = ServiceLocator.getRepService().listRep(sess.getCurrentUser());
		}else if(roles.contains("fm")){
			repList = ServiceLocator.getRepService().getRep(RepState.REVIEWED_BY_SCSO);
		}else if(roles.contains("admin")){
			repList = ServiceLocator.getRepService().getRep(RepState.REVIEWED_BY_FM);
		}
		
		final DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		refreshListModel(listModel, repList);
		setLayout(null);
		
		list = new JList();
		
		//enable buttons which are relevant only for selected REP
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedIndex() != -1){
					
					btnDelete.setEnabled(true);
					btnSendToAdmin.setEnabled(true);
					btnSendToFm.setEnabled(true);
					btnApprove.setEnabled(true);
					btnReject.setEnabled(true);
					
					btnSendForExecution.setEnabled(true);
					
					String select = list.getSelectedValue().toString();				
					String[] arr = select.split(",");
					String id = ((arr[0].trim().split(":"))[1]).trim();
					Rep rep = ServiceLocator.getRepService().getRep(Integer.parseInt(id));
					StringBuffer buff = new StringBuffer();
					String scsoComm = rep.getComment("scso");
					String fmComm = rep.getComment("fm");
					if(scsoComm != null && !scsoComm.isEmpty())
						scsoCommentField.setText(scsoComm);
					else
						scsoCommentField.setText("");
					
					if(fmComm != null && !fmComm.isEmpty())
						fmCommentsTxtField.setText(fmComm);
					else
						fmCommentsTxtField.setText("");
								
					
				}else{					
					btnDelete.setEnabled(false);
					btnSendToAdmin.setEnabled(false);
					btnSendToFm.setEnabled(false);
					btnApprove.setEnabled(false);
					btnReject.setEnabled(false);
					btnSendForExecution.setEnabled(false);
					
				}
			}
		});
		list.setBounds(92, 66, 436, 100);
		list.setPreferredSize(new Dimension(71, 100));
		list.setAutoscrolls(true);
		list.setModel(listModel);
		add(list);
		
		JPanel Btnpanel = new JPanel();
		Btnpanel.setBounds(0, 348, 619, 65);
		add(Btnpanel);
		
		JButton btnNewButton = new JButton("Sign out");
		btnNewButton.setBounds(0, 11, 93, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.reinitialize();
			}
		});
		Btnpanel.setLayout(null);
		Btnpanel.add(btnNewButton);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rep rep = ServiceLocator.getRepService().createRep(sess.getCurrentUser(), "XYZ corp");
				ServiceLocator.getRepService().addRep(rep);
				refreshListModel(listModel, ServiceLocator.getRepService().listRep(sess.getCurrentUser()=="scso"?"all":sess.getCurrentUser()));
			}
		});		
		btnCreate.setBounds(103, 11, 89, 23);
		
		Btnpanel.add(btnCreate);
		if(!roles.contains("cso") && !roles.contains("scso") ){
			btnCreate.setVisible(false);
		}
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = list.getSelectedValue().toString();
				int index = list.getSelectedIndex();
				String[] arr = select.split(",");
				String id = ((arr[0].trim().split(":"))[1]).trim();
				ServiceLocator.getRepService().deleteRep(Integer.parseInt(id));
				list.clearSelection();
				listModel.remove(index);
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setBounds(301, 11, 89, 23);		
		
		Btnpanel.add(btnDelete);
		
		btnSendToFm = new JButton("Send to Fm");
		btnSendToFm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = list.getSelectedValue().toString();				
				String[] arr = select.split(",");
				String id = ((arr[0].trim().split(":"))[1]).trim();
				Rep rep = ServiceLocator.getRepService().getRep(Integer.parseInt(id));
				rep.setState(RepState.REVIEWED_BY_SCSO);
				String comment = scsoCommentField.getText();
				rep.addComment("scso", comment);
				list.clearSelection();
				listModel.clear();
				
				ArrayList<Rep> reps = ServiceLocator.getRepService().listRep("all");
				refreshListModel(listModel, reps);
			}
		});
		btnSendToFm.setBounds(92, 263, 116, 23);
		btnSendToFm.setEnabled(false);
		btnSendToFm.setVisible(false);
		add(btnSendToFm);
		
		btnSendToAdmin = new JButton("Send to Admin");
		btnSendToAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = list.getSelectedValue().toString();				
				String[] arr = select.split(",");
				String id = ((arr[0].trim().split(":"))[1]).trim();
				Rep rep = ServiceLocator.getRepService().getRep(Integer.parseInt(id));
				rep.setState(RepState.REVIEWED_BY_FM);
				String comment = fmCommentsTxtField.getText();
				rep.addComment("fm", comment);
				list.clearSelection();
				refreshListModel(listModel, ServiceLocator.getRepService().getRep(RepState.REVIEWED_BY_SCSO));
			}
		});
		btnSendToAdmin.setBounds(236, 263, 124, 23);
		btnSendToAdmin.setEnabled(false);
		btnSendToAdmin.setVisible(false);
		add(btnSendToAdmin);
		
		btnReject = new JButton("Reject");
		btnReject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = list.getSelectedValue().toString();				
				String[] arr = select.split(",");
				String id = ((arr[0].trim().split(":"))[1]).trim();
				Rep rep = ServiceLocator.getRepService().getRep(Integer.parseInt(id));
				rep.setState(RepState.REJECTED);
				list.clearSelection();
				btnReject.setEnabled(false);
				if (sess.getCurrentUser().equals("admin")){
					refreshListModel(listModel, ServiceLocator.getRepService().getRep(RepState.REVIEWED_BY_FM));
				}else{
					refreshListModel(listModel, ServiceLocator.getRepService().listRep("all"));
				}
				
			}
		});
		btnReject.setEnabled(false);
		btnReject.setBounds(370, 263, 89, 23);
		btnReject.setVisible(false);
		add(btnReject);
		
		btnApprove = new JButton("Approve");
		btnApprove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = list.getSelectedValue().toString();				
				String[] arr = select.split(",");
				String id = ((arr[0].trim().split(":"))[1]).trim();
				Rep rep = ServiceLocator.getRepService().getRep(Integer.parseInt(id));
				rep.setState(RepState.APPROVED);
				list.clearSelection();
				btnApprove.setEnabled(false);
				refreshListModel(listModel, ServiceLocator.getRepService().getRep(RepState.REVIEWED_BY_FM));
			}
		});
		btnApprove.setEnabled(false);
		btnApprove.setBounds(469, 263, 89, 23);
		btnApprove.setVisible(false);
		add(btnApprove);
		
		btnSendForExecution = new JButton("Send For Execution");
		btnSendForExecution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = list.getSelectedValue().toString();				
				String[] arr = select.split(",");
				String id = ((arr[0].trim().split(":"))[1]).trim();
				Rep rep = ServiceLocator.getRepService().getRep(Integer.parseInt(id));
				Crd crd = ServiceLocator.getCrdService().createCrd(rep);	
				list.clearSelection();
				btnSendForExecution.setEnabled(false);
			}
		});
		btnSendForExecution.setEnabled(false);
		btnSendForExecution.setBounds(92, 297, 151, 23);
		btnSendForExecution.setVisible(false);
		add(btnSendForExecution);
		
		scsoCommentField = new JTextField();
		scsoCommentField.setBounds(193, 199, 203, 20);
		scsoCommentField.setEditable(false);
		add(scsoCommentField);
		scsoCommentField.setColumns(10);
		
		lblNewLabel = new JLabel("SCSO says");
		lblNewLabel.setBounds(86, 199, 77, 20);
		add(lblNewLabel);
		
		fmCommentsTxtField = new JTextField();
		fmCommentsTxtField.setBounds(190, 230, 206, 20);
		fmCommentsTxtField.setEditable(false);
		add(fmCommentsTxtField);
		fmCommentsTxtField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("FM Says");
		lblNewLabel_1.setBounds(82, 230, 61, 22);
		add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("REP GUI");
		lblNewLabel_2.setForeground(Color.BLUE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(92, 11, 328, 31);
		add(lblNewLabel_2);
		
		if(!roles.contains("cso") &&  !roles.contains("scso")){			
			btnDelete.setVisible(false);			
		}
		
		if(roles.contains("scso")){
			btnSendToFm.setVisible(true);
			btnReject.setVisible(true);
			btnSendForExecution.setVisible(true);
			scsoCommentField.setEditable(true);
		}
		
		if(roles.contains("fm")){
			btnSendToAdmin.setVisible(true);
			fmCommentsTxtField.setEditable(true);
		}
		
		if(roles.contains("admin")){
			btnReject.setVisible(true);
			btnApprove.setVisible(true);
		}	
		

	}
	
	private void refreshListModel(DefaultListModel<String> model, ArrayList<Rep> reps){
		model.clear();
		for(Rep rep:reps){
			model.addElement("Id: " + rep.getIdentifier() + ", Client: "+ rep.getClientName() + ", createdBy: " + rep.getUname()+ ", Status: " + rep.getState().toString());
		}
		
	}
	
}
