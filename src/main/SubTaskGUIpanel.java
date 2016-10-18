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
import order_management.FinancialRequest;
import order_management.FinancialRequestState;
import order_management.SubTask;
import order_management.SubTaskState;

public class SubTaskGUIpanel extends JPanel {

	JList list;
	JButton btnSignOut;
	JButton btnFinish;
	JButton btnprevious;
	JButton btnDelete;
	JTextField descriptionField;
	private JLabel lblComment;

	/**
	 * Create the panel.
	 */
	public SubTaskGUIpanel(Login login, Session session) {

		final Login prev = login;
		final Session sess = session;
		final SubTaskGUIpanel me = this;
		final ArrayList<String> roles = ServiceLocator.getSecurityService().getRoles(sess.getCurrentUser());

		setLayout(null);

		JLabel lblNewLabel = new JLabel("SubTask GUI");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(70, 11, 288, 34);
		add(lblNewLabel);

		final DefaultListModel<String> listModel = new DefaultListModel<String>();
		ArrayList<SubTask> subtasks;
		if (roles.contains("pm") || roles.contains("sm")) {
			subtasks = ServiceLocator.getSubTaskService().getAllSubTask();
		} else {
			subtasks = ServiceLocator.getSubTaskService().getSubTaskTeamMember(sess.getCurrentUser());
			if (roles.contains("rt")) {
				subtasks.addAll(ServiceLocator.getSubTaskService().getSubTaskTeam(sess.getCurrentUser()));
			}
		}
		for (SubTask subtask : subtasks) {
			listModel.addElement("Id: " + subtask.getIdentifier() + ", Department: " + subtask.getFromDate_subtask());
		}
		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (list.getSelectedIndex() != -1) {
					btnFinish.setEnabled(false);
					btnDelete.setEnabled(false);

					String select = list.getSelectedValue().toString();
					String[] arr = select.split(",");
					String id = ((arr[0].trim().split(":"))[1]).trim();
					SubTask subtask = ServiceLocator.getSubTaskService().getSubTask(Integer.parseInt(id));
					if (subtask.getState() == SubTaskState.CREATED
							&& subtask.getTeamMember().equals(sess.getCurrentUser())) {
						btnFinish.setEnabled(true);
					} else if (subtask.getState() == SubTaskState.FINISH) {
						btnDelete.setEnabled(true);
					}
					StringBuffer buff = new StringBuffer();
					String description = subtask.getDescription();
					if (description != null && !description.isEmpty())
						descriptionField.setText(description);
					else
						descriptionField.setText("");

				} else {
					btnFinish.setEnabled(false);
					btnDelete.setEnabled(false);
				}
			}
		});
		list.setPreferredSize(new Dimension(71, 100));
		list.setAutoscrolls(true);
		list.setModel(listModel);
		list.setBounds(28, 74, 367, 73);
		add(list);

		// button sign out
		btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.reinitialize();
			}
		});
		btnSignOut.setBounds(226, 265, 104, 23);
		add(btnSignOut);

		// button create task
		btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = list.getSelectedValue().toString();
				String[] arr = select.split(",");
				String id = ((arr[0].trim().split(":"))[1]).trim();
				SubTask subtask = ServiceLocator.getSubTaskService().getSubTask(Integer.parseInt(id));
				subtask.setState(SubTaskState.FINISH);
				refreshListModel(listModel, sess);
			}
		});
		btnFinish.setBounds(28, 185, 148, 23);
		btnFinish.setVisible(false);
		btnFinish.setEnabled(false);
		add(btnFinish);

		// button previous
		btnprevious = new JButton("previous ");
		btnprevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.user(sess, me);
			}
		});
		btnprevious.setBounds(70, 264, 99, 24);
		add(btnprevious);

		// button Delete
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = list.getSelectedValue().toString();
				String[] arr = select.split(",");
				String id = ((arr[0].trim().split(":"))[1]).trim();
				SubTask subtask = ServiceLocator.getSubTaskService().getSubTask(Integer.parseInt(id));
				ServiceLocator.getSubTaskService().deleteSubTask(subtask.getIdentifier());
				refreshListModel(listModel, sess);
			}
		});
		btnDelete.setBounds(12, 220, 175, 24);
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

		if (roles.contains("rm")) {
			btnDelete.setVisible(true);
		}

		if (roles.contains("tm")) {
			btnFinish.setVisible(true);
		}
	}

	private void refreshListModel(DefaultListModel<String> model, Session sess) {
		ArrayList<String> roles = ServiceLocator.getSecurityService().getRoles(sess.getCurrentUser());
		ArrayList<SubTask> subtasks;
		if (roles.contains("pm") || roles.contains("sm")) {
			subtasks = ServiceLocator.getSubTaskService().getAllSubTask();
		} else {
			subtasks = ServiceLocator.getSubTaskService().getSubTaskTeamMember(sess.getCurrentUser());
			if (roles.contains("rt")) {
				subtasks.addAll(ServiceLocator.getSubTaskService().getSubTaskTeam(sess.getCurrentUser()));
			}
		}

		model.clear();
		for (SubTask subtask : subtasks) {
			model.addElement("Id: " + subtask.getIdentifier() + ", Department: " + subtask.getFromDate_subtask());
		}

	}
}
