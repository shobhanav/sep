package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import framework.ServiceLocator;
import framework.Session;
import order_management.Crd;
import order_management.FinancialRequest;
import order_management.FinancialRequestManagementImpl;
import order_management.Task;
import javax.swing.JLabel;

public class CreateFinancial extends JPanel {
	JButton btnSignOut;
	JButton btnCreateFinancial;
	JButton btnprevious;
	JTextField DepartmentField;
	JTextField CommentField;
	private JLabel label;
	private JLabel label_1;
	
	/**
	 * Create the panel.
	 */
	public CreateFinancial(Login login, Session session, Crd crdarg) {
		final Login prev = login;
		final Session sess = session;
		final Crd crd = crdarg;
		final CreateFinancial me = this;
		
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
		btnCreateFinancial = new JButton("Create");
		btnCreateFinancial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FinancialRequest request = ServiceLocator.getFinancialService().createFinancialRequest(crd, sess.getCurrentUser());
				request.setDepartment(DepartmentField.getText());
				request.setComment(CommentField.getText());
				ServiceLocator.getFinancialService().addFinancialRequest(request);
				prev.crd(sess, me);
			}
		});
		btnCreateFinancial.setBounds(143, 231, 82, 25);
		add(btnCreateFinancial);
		
		//button previous
		btnprevious = new JButton("previous ");
		btnprevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.crd(sess, me);
			}
		});
		btnprevious.setBounds(258, 231, 99, 25);
		add(btnprevious);
		
		DepartmentField = new JTextField();
		DepartmentField.setEditable(true);
		DepartmentField.setBounds(111, 108, 195, 83);
		add(DepartmentField);
		DepartmentField.setColumns(10);
		
		CommentField = new JTextField();
		CommentField.setEditable(true);
		CommentField.setBounds(124, 55, 114, 23);
		add(CommentField);
		CommentField.setColumns(10);
		
		label = new JLabel("Departement");
		label.setBounds(12, 57, 107, 19);
		add(label);
		
		label_1 = new JLabel("Comment");
		label_1.setBounds(23, 108, 70, 15);
		add(label_1);
	}
}
