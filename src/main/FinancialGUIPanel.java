package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import framework.ServiceLocator;
import framework.Session;
import order_management.EmployeeRequest;
import order_management.EmployeeRequestState;
import order_management.FinancialRequest;
import order_management.FinancialRequestState;

public class FinancialGUIPanel extends JPanel {



	JList list;
	JButton btnSignOut;
	JButton btnReject;
	JButton btnprevious;
	JButton btnAccept;
	JButton btnDelete;
	JTextField descriptionField;
	private JLabel lblComment;

	/**
		 * Create the panel.
		 */
		public FinancialGUIPanel(Login login, Session session) {
			
			final Login prev = login;
			final Session sess = session;
			final FinancialGUIPanel me = this;
			final ArrayList<String> roles = ServiceLocator.getSecurityService().getRoles(sess.getCurrentUser());
			
			setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Financial GUI");
			lblNewLabel.setForeground(Color.BLUE);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
			lblNewLabel.setBounds(70, 11, 288, 34);
			add(lblNewLabel);
			
			final DefaultListModel<String> listModel = new DefaultListModel<String>();
			ArrayList<FinancialRequest> requests;
			if (roles.contains("rh")){
				requests = ServiceLocator.getFinancialService().getAllFinancialRequest();
			}else{
				requests = ServiceLocator.getFinancialService().getFinancialRequestUser(sess.getCurrentUser());
			}
			for(FinancialRequest request : requests){
				listModel.addElement("Id: " + request.getIdentifier()+ ", Department: " + request.getDepartment());
			}		
			list = new JList();
			list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if(list.getSelectedIndex() != -1){
						btnAccept.setEnabled(false);
						btnReject.setEnabled(false);
						btnDelete.setEnabled(false);

						
						String select = list.getSelectedValue().toString();				
						String[] arr = select.split(",");
						String id = ((arr[0].trim().split(":"))[1]).trim();
						FinancialRequest request = ServiceLocator.getFinancialService().getFinancialRequest(Integer.parseInt(id));
						if (request.getState() == FinancialRequestState.CREATED){
							btnAccept.setEnabled(true);
							btnReject.setEnabled(true);
						} else if (request.getState() != FinancialRequestState.CREATED){
							btnDelete.setEnabled(true);
						}
						StringBuffer buff = new StringBuffer();
						String description = request.getComment();
						if(description != null && !description.isEmpty())
							descriptionField.setText(description);
						else
							descriptionField.setText("");
						
					}else{					
						btnAccept.setEnabled(false);
						btnReject.setEnabled(false);
						btnDelete.setEnabled(false);
					}
				}
			});
			list.setPreferredSize(new Dimension(71, 100));
			list.setAutoscrolls(true);
			list.setModel(listModel);
			list.setBounds(28, 74, 367, 73);
			add(list);
			
			//button sign out
			btnSignOut = new JButton("Sign Out");
			btnSignOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					prev.reinitialize();
				}
			});
			btnSignOut.setBounds(226, 265, 104, 23);
			add(btnSignOut);
			
			//button create task
			btnAccept = new JButton("Accept");
			btnAccept.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String select = list.getSelectedValue().toString();				
					String[] arr = select.split(",");
					String id = ((arr[0].trim().split(":"))[1]).trim();
					FinancialRequest request = ServiceLocator.getFinancialService().getFinancialRequest(Integer.parseInt(id));
					request.setState(FinancialRequestState.ACCEPTED);
					refreshListModel(listModel,sess);
				}
			});
			btnAccept.setBounds(28, 202, 148, 23);
			btnAccept.setVisible(false);
			btnAccept.setEnabled(false);
			add(btnAccept);

			
			//button previous
			btnprevious = new JButton("previous ");
			btnprevious.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					prev.user(sess, me);
				}
			});
			btnprevious.setBounds(70, 264, 99, 24);
			add(btnprevious);
			
			//button Delete
			btnDelete = new JButton("Delete");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String select = list.getSelectedValue().toString();				
					String[] arr = select.split(",");
					String id = ((arr[0].trim().split(":"))[1]).trim();
					FinancialRequest request= ServiceLocator.getFinancialService().getFinancialRequest(Integer.parseInt(id));
					ServiceLocator.getFinancialService().deleteFinancialRequest(request.getIdentifier());
					refreshListModel(listModel,sess);
				}
			});
			btnDelete.setBounds(12, 228, 175, 24);
			btnDelete.setEnabled(false);
			btnDelete.setVisible(false);
			add(btnDelete);
			
			descriptionField = new JTextField();
			descriptionField.setBounds(199, 169, 206, 56);
			descriptionField.setEditable(false);
			add(descriptionField);
			descriptionField.setColumns(10);
			
			lblComment = new JLabel("Comment");
			lblComment.setBounds(106, 159, 70, 15);
			add(lblComment);
			
			//button create task
			btnReject = new JButton("Reject");
			btnReject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String select = list.getSelectedValue().toString();				
					String[] arr = select.split(",");
					String id = ((arr[0].trim().split(":"))[1]).trim();
					FinancialRequest request = ServiceLocator.getFinancialService().getFinancialRequest(Integer.parseInt(id));
					request.setState(FinancialRequestState.REJECTED);
					refreshListModel(listModel,sess);
				}
			});
			btnReject.setBounds(28, 175, 148, 23);
			btnReject.setVisible(false);
			btnReject.setEnabled(false);
			add(btnReject);
			
			
			
			if(roles.contains("fm")){
				btnDelete.setVisible(true);
				btnAccept.setVisible(true);
			}
		}
		
		private void refreshListModel(DefaultListModel<String> model,Session sess){
			ArrayList<FinancialRequest> requests;
			ArrayList<String> roles = ServiceLocator.getSecurityService().getRoles(sess.getCurrentUser());
			if (roles.contains("rh")){
				requests = ServiceLocator.getFinancialService().getAllFinancialRequest();
			}else{
				requests = ServiceLocator.getFinancialService().getFinancialRequestUser(sess.getCurrentUser());
			}
	
			model.clear();
			for(FinancialRequest request : requests){
				model.addElement("Id: " + request.getIdentifier()+ ", Department: " + request.getDepartment());
			}
			
		}

}

