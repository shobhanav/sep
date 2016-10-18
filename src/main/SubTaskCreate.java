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
import order_management.Crd;
import order_management.SubTask;
import order_management.Task;

public class SubTaskCreate extends JPanel {
	JList list;
	JButton btnSignOut;
	JButton btnCreateSubTask;
	JButton btnprevious;
	JTextField descriptionField;
	JTextField descriptionTaskField;
	JTextField teamMemberField;
	private JButton buttonUpdate;
	private JButton buttonDelete;
	private JLabel label_1;
	private JLabel label_2;
	
	/**
	 * Create the panel.
	 */
	public SubTaskCreate(Login login, Session session, Task taskarg ) {
		final Login prev = login;
		final Session sess = session;
		final Task task = taskarg;
		final SubTaskCreate me = this;

		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SubTask GUI");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(70, 11, 288, 34);
		add(lblNewLabel);
		
		final DefaultListModel<String> listModel = new DefaultListModel<String>();
		ArrayList<SubTask> subtasks = ServiceLocator.getSubTaskService().getSubTaskTask(task);
		
		for(SubTask subtask: subtasks){
			listModel.addElement("Id: " + subtask.getIdentifier() + ", teamMember : " + subtask.getTeamMember()+", state: " +subtask.getState());
		}		
		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedIndex() != -1){
					String select = list.getSelectedValue().toString();				
					String[] arr = select.split(",");
					String id = ((arr[0].trim().split(":"))[1]).trim();
					
					SubTask subtask = ServiceLocator.getSubTaskService().getSubTask(Integer.parseInt(id));
					StringBuffer buff = new StringBuffer();
					String description = subtask.getDescription();
					if(description != null && !description.isEmpty())
						descriptionField.setText(description);
					else
						descriptionField.setText("");
					String teamMember = subtask.getTeamMember();
					if(teamMember != null && !teamMember.isEmpty())
						teamMemberField.setText(teamMember);
					else
						teamMemberField.setText("");
					buttonUpdate.setEnabled(true);
					buttonDelete.setEnabled(true);
				}else{					
					buttonUpdate.setEnabled(false);
					buttonDelete.setEnabled(false);
				}
			}
		});
		list.setPreferredSize(new Dimension(71, 100));
		list.setAutoscrolls(true);
		list.setModel(listModel);
		list.setBounds(44, 131, 172, 83);
		add(list);
		

		//button sign out
		btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.reinitialize();
			}
		});
		btnSignOut.setBounds(343, 243, 104, 23);
		add(btnSignOut);
		
		//button create subtask
		btnCreateSubTask = new JButton("Create subTasks");
		btnCreateSubTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SubTask subtask = ServiceLocator.getSubTaskService().createSubTasks(task, teamMemberField.getText());
				subtask.setDescription(descriptionField.getText());
				ServiceLocator.getSubTaskService().addSubTask(subtask);
				refreshListModel(listModel, task);
			}
		});
		btnCreateSubTask.setBounds(56, 243, 164, 23);
		add(btnCreateSubTask);
		
		//button previous
		btnprevious = new JButton("previous ");
		btnprevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.task(sess, me);
			}
		});
		btnprevious.setBounds(232, 242, 99, 24);
		add(btnprevious);
		
		
		descriptionField = new JTextField();
		descriptionField.setBounds(266, 114, 172, 83);
		descriptionField.setEditable(true);
		add(descriptionField);
		descriptionField.setColumns(10);
		
		descriptionTaskField = new JTextField();
		descriptionTaskField.setEditable(false);
		descriptionTaskField.setText(task.getDescription());
		descriptionTaskField.setBounds(34, 57, 172, 62);
		add(descriptionTaskField);
		descriptionTaskField.setColumns(10);
		
		teamMemberField = new JTextField();
		teamMemberField.setEditable(true);
		teamMemberField.setBounds(333, 57, 114, 23);
		add(teamMemberField);
		teamMemberField.setColumns(10);
		
		buttonUpdate = new JButton("Update SubTask");
		buttonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = list.getSelectedValue().toString();				
				String[] arr = select.split(",");
				String id = ((arr[0].trim().split(":"))[1]).trim();
				SubTask subtask = ServiceLocator.getSubTaskService().getSubTask(Integer.parseInt(id));
				subtask.setDescription(descriptionField.getText());
				refreshListModel(listModel, task);
			}
		});
		buttonUpdate.setBounds(219, 206, 150, 25);
		buttonUpdate.setEnabled(false);
		add(buttonUpdate);
		
		buttonDelete = new JButton("Delete SubTask");
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = list.getSelectedValue().toString();				
				String[] arr = select.split(",");
				String id = ((arr[0].trim().split(":"))[1]).trim();
				SubTask subtask = ServiceLocator.getSubTaskService().getSubTask(Integer.parseInt(id));
				ServiceLocator.getTaskService().deleteTask(subtask.getIdentifier());
				refreshListModel(listModel, task);
			}
		});
		buttonDelete.setBounds(371, 209, 157, 25);
		buttonDelete.setEnabled(false);
		add(buttonDelete);
		
		JLabel label = new JLabel("task");
		label.setBounds(34, 37, 99, 15);
		add(label);
		
		label_1 = new JLabel("team member :");
		label_1.setBounds(224, 57, 116, 15);
		add(label_1);
		
		label_2 = new JLabel("subtask");
		label_2.setBounds(234, 87, 70, 15);
		add(label_2);

	}
	
	private void refreshListModel(DefaultListModel<String> model,Task task){
		model.clear();
		ArrayList<SubTask> subtasks = ServiceLocator.getSubTaskService().getSubTaskTask(task);
		
		for(SubTask subtask: subtasks){
			model.addElement("Id: " + subtask.getIdentifier() + ", teamMember : " + subtask.getTeamMember()+", state: " +subtask.getState());
		}	
		
	}
}
