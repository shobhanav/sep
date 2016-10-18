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
import order_management.Rep;
import order_management.RepState;
import order_management.Task;

public class TaskCreate extends JPanel {
	JList list;
	JList listCrd;
	JButton btnSignOut;
	JButton btnCreateTask;
	JButton btnprevious;
	JTextField descriptionField;
	JTextField descriptionCrdField;
	JTextField teamField;
	private JButton buttonUpdate;
	private JButton buttonDelete;
	private JLabel label_1;
	
	/**
	 * Create the panel.
	 */
	public TaskCreate(Login login, Session session, Crd crdarg ) {
		final Login prev = login;
		final Session sess = session;
		final Crd crd = crdarg;
		final TaskCreate me = this;

		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Task GUI");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(70, 11, 288, 34);
		add(lblNewLabel);
		
		final DefaultListModel<String> listModel = new DefaultListModel<String>();
		ArrayList<Task> tasks = ServiceLocator.getTaskService().getTaskCrd(crd);
		
		for(Task task: tasks){
			listModel.addElement("Id: " + task.getIdentifier() + ", Client: " + task.getClientName()+", state: " +task.getState());
		}		
		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(list.getSelectedIndex() != -1){
					String select = list.getSelectedValue().toString();				
					String[] arr = select.split(",");
					String id = ((arr[0].trim().split(":"))[1]).trim();
					
					Task task = ServiceLocator.getTaskService().getTask(Integer.parseInt(id));
					StringBuffer buff = new StringBuffer();
					String description = task.getDescription();
					if(description != null && !description.isEmpty())
						descriptionField.setText(description);
					else
						descriptionField.setText("");
					String team = task.getTeam();
					if(team != null && !team.isEmpty())
						teamField.setText(team);
					else
						teamField.setText("");
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
		list.setBounds(275, 36, 172, 83);
		add(list);
		
		//list description crd
		final DefaultListModel<String> listModelCrd = new DefaultListModel<String>();
		int index =0;
		for(String team: crd.getTask().keySet()){
			listModelCrd.addElement("team :" + team);
		}	
		listModelCrd.addElement("team :" + "empty");
		listCrd = new JList();
		listCrd.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(listCrd.getSelectedIndex() != -1){
					String select = listCrd.getSelectedValue().toString();				
					String[] arr = select.split(",");
					String team = ((arr[0].trim().split(":"))[1]).trim();
					
					String description = crd.getTask(team);
					if(description != null && !description.isEmpty())
						descriptionCrdField.setText(description);
					else
						descriptionCrdField.setText("");
					btnCreateTask.setEnabled(true);
					
				}else{					
					btnCreateTask.setEnabled(false);
					
				}
			}
		});
		listCrd.setPreferredSize(new Dimension(71, 100));
		listCrd.setAutoscrolls(true);
		listCrd.setModel(listModelCrd);
		listCrd.setBounds(60, 47, 172, 83);
		add(listCrd);
		
		
		//button sign out
		btnSignOut = new JButton("Sign Out");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.reinitialize();
			}
		});
		btnSignOut.setBounds(343, 243, 104, 23);
		add(btnSignOut);
		
		//button create task
		btnCreateTask = new JButton("Create Tasks");
		btnCreateTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Task task = ServiceLocator.getTaskService().createTasks(crd, teamField.getText());
				task.setDescription(descriptionField.getText());
				ServiceLocator.getTaskService().addTask(task);
				refreshListModel(listModel, crd);
			}
		});
		btnCreateTask.setBounds(72, 243, 148, 23);
		add(btnCreateTask);
		btnCreateTask.setEnabled(false);
		
		//button previous
		btnprevious = new JButton("previous ");
		btnprevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.crd(sess, me);
			}
		});
		btnprevious.setBounds(232, 242, 99, 24);
		add(btnprevious);
		
		
		descriptionField = new JTextField();
		descriptionField.setBounds(275, 153, 172, 50);
		descriptionField.setEditable(true);
		add(descriptionField);
		descriptionField.setColumns(10);
		
		descriptionCrdField = new JTextField();
		descriptionCrdField.setEditable(false);
		descriptionCrdField.setBounds(60, 157, 172, 62);
		add(descriptionCrdField);
		descriptionCrdField.setColumns(10);
		
		teamField = new JTextField();
		teamField.setEditable(true);
		teamField.setBounds(322, 131, 114, 23);
		add(teamField);
		teamField.setColumns(10);
		
		buttonUpdate = new JButton("Update Task");
		buttonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = list.getSelectedValue().toString();				
				String[] arr = select.split(",");
				String id = ((arr[0].trim().split(":"))[1]).trim();
				Task task = ServiceLocator.getTaskService().getTask(Integer.parseInt(id));
				task.setDescription(descriptionField.getText());
			}
		});
		buttonUpdate.setBounds(232, 209, 139, 25);
		buttonUpdate.setEnabled(false);
		add(buttonUpdate);


		buttonDelete = new JButton("Delete Task");
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String select = list.getSelectedValue().toString();				
				String[] arr = select.split(",");
				String id = ((arr[0].trim().split(":"))[1]).trim();
				Task task = ServiceLocator.getTaskService().getTask(Integer.parseInt(id));
				ServiceLocator.getTaskService().deleteTask(task.getIdentifier());
				refreshListModel(listModel, crd);
			}
		});
		buttonDelete.setBounds(384, 209, 130, 25);
		buttonDelete.setEnabled(false);
		add(buttonDelete);
		
		JLabel label = new JLabel("Description");
		label.setBounds(60, 142, 99, 15);
		add(label);
		
		label_1 = new JLabel("team :");
		label_1.setBounds(261, 135, 70, 15);
		add(label_1);

	}
	
	private void refreshListModel(DefaultListModel<String> model,Crd crd){
		model.clear();
		ArrayList<Task> tasks = ServiceLocator.getTaskService().getTaskCrd(crd);
		
		for(Task task: tasks){
			model.addElement("Id: " + task.getIdentifier() + ", Client: " + task.getClientName()+", state: " +task.getState());
		}	
		
	}
}
