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
import jdk.nashorn.internal.ir.RuntimeNode.Request;
import order_management.EmployeeRequest;
import order_management.EmployeeRequestState;
import order_management.Rep;
import order_management.RepState;
import order_management.SubTask;
import order_management.Task;
import order_management.TaskState;

public class RecruitmentGUIPanel extends JPanel {


	JList list;
	JButton btnSignOut;
	JButton btnClose;
	JButton btnprevious;
	JButton btnDelete;
	JTextField descriptionField;
	private JLabel lblComment;

	/**
		 * Create the panel.
		 */
		public RecruitmentGUIPanel(Login login, Session session) {
			
			final Login prev = login;
			final Session sess = session;
			final RecruitmentGUIPanel me = this;
			final ArrayList<String> roles = ServiceLocator.getSecurityService().getRoles(sess.getCurrentUser());
			
			setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Recruitment GUI");
			lblNewLabel.setForeground(Color.BLUE);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
			lblNewLabel.setBounds(70, 11, 288, 34);
			add(lblNewLabel);
			
			final DefaultListModel<String> listModel = new DefaultListModel<String>();
			ArrayList<EmployeeRequest> requests = ServiceLocator.getEmployeeService().getAllEmployeeRequest();
			
			for(EmployeeRequest request : requests){
				listModel.addElement("Id: " + request.getIdentifier()+ ", Title: " + request.getJobTitle());
			}		
			list = new JList();
			list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if(list.getSelectedIndex() != -1){
						btnClose.setEnabled(false);
						btnDelete.setEnabled(false);
						
						String select = list.getSelectedValue().toString();				
						String[] arr = select.split(",");
						String id = ((arr[0].trim().split(":"))[1]).trim();
						EmployeeRequest request = ServiceLocator.getEmployeeService().getEmployeeRequest(Integer.parseInt(id));
						if (request.getState() == EmployeeRequestState.CREATED){
							btnClose.setEnabled(true);
						} else if (request.getState() != EmployeeRequestState.CLOSED){
							btnDelete.setEnabled(true);
						}
						StringBuffer buff = new StringBuffer();
						String description = request.getJobDescription();
						if(description != null && !description.isEmpty())
							descriptionField.setText(description);
						else
							descriptionField.setText("");
						
					}else{					
						btnClose.setEnabled(false);
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
			btnClose = new JButton("close");
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String select = list.getSelectedValue().toString();				
					String[] arr = select.split(",");
					String id = ((arr[0].trim().split(":"))[1]).trim();
					EmployeeRequest request = ServiceLocator.getEmployeeService().getEmployeeRequest(Integer.parseInt(id));
					request.setState(EmployeeRequestState.CLOSED);
					refreshListModel(listModel);
				}
			});
			btnClose.setBounds(28, 175, 148, 23);
			btnClose.setVisible(false);
			btnClose.setEnabled(false);
			add(btnClose);

			
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
					EmployeeRequest request= ServiceLocator.getEmployeeService().getEmployeeRequest(Integer.parseInt(id));
					ServiceLocator.getEmployeeService().deleteEmployeeRequest(request.getIdentifier());
					refreshListModel(listModel);
				}
			});
			btnDelete.setBounds(12, 201, 175, 24);
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
			
			if(roles.contains("rh")){
				btnDelete.setVisible(true);
				btnClose.setVisible(true);
			}
		}
		
		private void refreshListModel(DefaultListModel<String> model){
			ArrayList<EmployeeRequest> requests = ServiceLocator.getEmployeeService().getAllEmployeeRequest();

			model.clear();
			for(EmployeeRequest request : requests){
				model.addElement("Id: " + request.getIdentifier()+ ", Title: " + request.getJobTitle());
			}	
			
		}

}
