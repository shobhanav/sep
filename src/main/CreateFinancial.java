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
	JTextField departmentField;
	JTextField commentField;
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
				request.setDepartment(departmentField.getText());
				request.setComment(commentField.getText());
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
		
		departmentField = new JTextField();
		departmentField.setEditable(true);
		departmentField.setBounds(113, 62, 132, 19);
		add(departmentField);
		departmentField.setColumns(10);
		
		commentField = new JTextField();
		commentField.setEditable(true);
		commentField.setBounds(113, 115, 180, 97);
		add(commentField);
		commentField.setColumns(10);
		
		label = new JLabel("Departement");
		label.setBounds(12, 57, 107, 19);
		add(label);
		
		label_1 = new JLabel("Comment");
		label_1.setBounds(23, 108, 70, 15);
		add(label_1);
	}
}
