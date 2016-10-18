package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import framework.ServiceLocator;
import framework.Session;
import order_management.Crd;
import order_management.EmployeeRequest;
import order_management.FinancialRequest;
import javax.swing.JRadioButton;

public class CreateRecruitment extends JPanel {

		JButton btnSignOut;
		JButton btnCreateRecruitment;
		JButton btnprevious;
		JTextField departmentField;
		JTextField descriptionField;
		JTextField jobTitleField;
		JTextField yearsOfExpField;
		private JLabel label;
		private JLabel label_1;
		private JLabel label_2;
		private JLabel label_3;
		private JRadioButton partialTime;
		private JRadioButton fullTime;
		
		/**
		 * Create the panel.
		 */
		public CreateRecruitment(Login login, Session session) {
			final Login prev = login;
			final Session sess = session;
			final CreateRecruitment me = this;
			
			//button sign out
			btnSignOut = new JButton("Sign Out");
			btnSignOut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					prev.reinitialize();
				}
			});
			setLayout(null);
			btnSignOut.setBounds(37, 231, 95, 25);
			add(btnSignOut);
			
			//button create task
			btnCreateRecruitment = new JButton("Create");
			btnCreateRecruitment.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					EmployeeRequest request = ServiceLocator.getEmployeeService().createEmployeeRequest(sess.getCurrentUser());
					request.setDepartment(departmentField.getText());
					request.setJobDescription(descriptionField.getText());
					request.setJobTitle(jobTitleField.getText());
					request.setYearsOfExperience(Integer.parseInt(yearsOfExpField.getText()));
					request.setFullTime(fullTime.isSelected());
					ServiceLocator.getEmployeeService().addEmployeeRequest(request);
					prev.crd(sess, me);
				}
			});
			btnCreateRecruitment.setBounds(143, 231, 82, 25);
			add(btnCreateRecruitment);
			
			//button previous
			btnprevious = new JButton("previous ");
			btnprevious.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					prev.crd(sess, me);
				}
			});
			btnprevious.setBounds(258, 231, 99, 25);
			add(btnprevious);
			
			departmentField = new JTextField();
			departmentField.setEditable(true);
			departmentField.setBounds(100, 53, 195, 25);
			add(departmentField);
			departmentField.setColumns(10);
			
			descriptionField = new JTextField();
			descriptionField.setEditable(true);
			descriptionField.setBounds(100, 160, 206, 70);
			add(descriptionField);
			descriptionField.setColumns(10);
			
			yearsOfExpField = new JTextField();
			yearsOfExpField .setEditable(true);
			yearsOfExpField .setBounds(100, 87, 63, 25);
			add(yearsOfExpField );
			yearsOfExpField .setColumns(10);
			
			jobTitleField = new JTextField();
			jobTitleField.setEditable(true);
			jobTitleField.setBounds(100, 123, 195, 25);
			add(jobTitleField );
			jobTitleField.setColumns(10);
			
			label = new JLabel("Departement");
			label.setBounds(0, 56, 107, 19);
			add(label);
			
			label_1 = new JLabel("Description");
			label_1.setBounds(0, 164, 95, 15);
			add(label_1);
			
			label_2 = new JLabel("year of Exp");
			label_2.setBounds(10, 90, 95, 15);
			add(label_2);
			
			label_3 = new JLabel("job title");
			label_3.setBounds(12, 128, 70, 15);
			add(label_3);
			
			fullTime = new JRadioButton("full time");
			fullTime.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(partialTime.isSelected()){
						partialTime.setSelected(false);
					}
				}
			});
			fullTime.setBounds(171, 88, 82, 23);
			add(fullTime);
			
			partialTime = new JRadioButton("Partial time");
			partialTime.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(fullTime.isSelected()){
						fullTime.setSelected(false);
					}
				}
			});
			partialTime.setBounds(259, 88, 115, 23);
			add(partialTime);
		}
	}
