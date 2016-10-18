package main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import framework.ServiceLocator;
import framework.Session;
import order_management.Crd;
import order_management.CrdState;
import order_management.Rep;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CrdGUIPanel extends JPanel {

	JList list;
	JButton btnSignOut;
	JButton btnCreateTask;
	JButton btnprevious;
	JButton btnFinancialrequest;
	JButton btnRecruitmentRequest;
	
	/**
	 * Create the panel.
	 */
	public CrdGUIPanel(Login login, Session session) {
		
		final Login prev = login;
		final Session sess = session;
		final CrdGUIPanel me = this;
		
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CRD GUI");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(70, 11, 288, 34);
		add(lblNewLabel);
		
		final DefaultListModel<String> listModel = new DefaultListModel<String>();
		ArrayList<Crd> crds = ServiceLocator.getCrdService().getAllCrd();
		
		for(Crd crd: crds){
			listModel.addElement("Id: " + crd.getIdentifier() + ", Budget:" + crd.getExp_budget() + ", Client: " + crd.getClientName());
		}		
		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedIndex() != -1){
					
					btnCreateTask.setEnabled(true);
					btnFinancialrequest.setEnabled(true);
					btnRecruitmentRequest.setEnabled(true);				
				}else{					
					btnCreateTask.setEnabled(false);
					btnFinancialrequest.setEnabled(false);
					btnRecruitmentRequest.setEnabled(false);
					
				}
			}
		});
		list.setPreferredSize(new Dimension(71, 100));
		list.setAutoscrolls(true);
		list.setModel(listModel);
		list.setBounds(70, 70, 367, 134);
		add(list);
		
		//button sign out
		btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.reinitialize();
			}
		});
		btnSignOut.setBounds(231, 321, 104, 23);
		add(btnSignOut);
		
		//button create task
		btnCreateTask = new JButton("Tasks");
		btnCreateTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = list.getSelectedValue().toString();				
				String[] arr = select.split(",");
				String id = ((arr[0].trim().split(":"))[1]).trim();
				Crd crd = ServiceLocator.getCrdService().getCrd(Integer.parseInt(id));
				crd.setState(CrdState.WAITING);
				prev.createtask(sess, me, crd);
			}
		});
		btnCreateTask.setBounds(70, 243, 148, 23);
		add(btnCreateTask);
		btnCreateTask.setEnabled(false);
		
		//button previous
		btnprevious = new JButton("previous ");
		btnprevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.user(sess, me);
			}
		});
		btnprevious.setBounds(70, 320, 99, 24);
		add(btnprevious);
		
		//button Financial
		btnFinancialrequest = new JButton("Financial Request");
		btnFinancialrequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = list.getSelectedValue().toString();				
				String[] arr = select.split(",");
				String id = ((arr[0].trim().split(":"))[1]).trim();
				Crd crd = ServiceLocator.getCrdService().getCrd(Integer.parseInt(id));
				prev.createFinancial(sess, me, crd);
			}
		});
		btnFinancialrequest.setBounds(230, 242, 175, 24);
		btnFinancialrequest.setEnabled(false);
		add(btnFinancialrequest);
		
		//button Recruitement
		btnRecruitmentRequest = new JButton("recruitment Request");
		btnRecruitmentRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = list.getSelectedValue().toString();				
				String[] arr = select.split(",");
				String id = ((arr[0].trim().split(":"))[1]).trim();
				Crd crd = ServiceLocator.getCrdService().getCrd(Integer.parseInt(id));
				prev.createRecruitment(sess, me);
			}
		});
		btnRecruitmentRequest.setBounds(229, 274, 180, 25);
		btnRecruitmentRequest.setEnabled(false);
		add(btnRecruitmentRequest);

	}

}
