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
import order_management.TaskState;

public class TaskGUIPanel extends JPanel {

	JList list;
	JButton btnSignOut;
	JButton btnCreateSubTask;
	JButton btnprevious;
	JButton btnReview;
	JTextField commentField;
	private JLabel lblComment;

	/**
		 * Create the panel.
		 */
		public TaskGUIPanel(Login login, Session session) {
			
			final Login prev = login;
			final Session sess = session;
			final TaskGUIPanel me = this;
			
			setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Task GUI");
			lblNewLabel.setForeground(Color.BLUE);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
			lblNewLabel.setBounds(70, 11, 288, 34);
			add(lblNewLabel);
			
			final DefaultListModel<String> listModel = new DefaultListModel<String>();
			ArrayList<Task> tasks = ServiceLocator.getTaskService().getTaskTeam(sess.getCurrentUser());
			
			for(Task task : tasks){
				listModel.addElement("Id: " + task.getIdentifier()+ ", Client: " + task.getClientName());
			}		
			list = new JList();
			list.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if(list.getSelectedIndex() != -1){
						btnCreateSubTask.setEnabled(false);
						btnReview.setEnabled(false);
						commentField.setEditable(false);
						
						String select = list.getSelectedValue().toString();				
						String[] arr = select.split(",");
						String id = ((arr[0].trim().split(":"))[1]).trim();
						Task task = ServiceLocator.getTaskService().getTask(Integer.parseInt(id));
						if (task.getState() == TaskState.CREATED){
							btnReview.setEnabled(true);
							commentField.setEditable(true);
						} else if (task.getState() != TaskState.CREATED && task.getState() != TaskState.FINISH){
							btnCreateSubTask.setEnabled(true);
						}
					}else{					
						btnCreateSubTask.setEnabled(false);
						btnReview.setEnabled(false);
						commentField.setEditable(false);
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
			btnCreateSubTask = new JButton("SubTasks");
			btnCreateSubTask.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String select = list.getSelectedValue().toString();				
					String[] arr = select.split(",");
					String id = ((arr[0].trim().split(":"))[1]).trim();
					Task task = ServiceLocator.getTaskService().getTask(Integer.parseInt(id));
					//prev.createsubtask(sess, me, task);
				}
			});
			btnCreateSubTask.setBounds(28, 175, 148, 23);
			add(btnCreateSubTask);
			btnCreateSubTask.setEnabled(false);
			
			//button previous
			btnprevious = new JButton("previous ");
			btnprevious.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					prev.user(sess, me);
				}
			});
			btnprevious.setBounds(70, 264, 99, 24);
			add(btnprevious);
			
			//button Financial
			btnReview = new JButton("Review");
			btnReview.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String select = list.getSelectedValue().toString();				
					String[] arr = select.split(",");
					String id = ((arr[0].trim().split(":"))[1]).trim();
					Task task = ServiceLocator.getTaskService().getTask(Integer.parseInt(id));
					task.setReview(commentField.getText());
					task.setState(TaskState.REVIEW);
					refreshListModel(listModel, sess);
				}
			});
			btnReview.setBounds(12, 201, 175, 24);
			btnReview.setEnabled(false);
			add(btnReview);
			
			commentField = new JTextField();
			commentField.setBounds(199, 169, 206, 56);
			commentField.setEditable(false);
			add(commentField);
			commentField.setColumns(10);
			
			lblComment = new JLabel("Comment");
			lblComment.setBounds(106, 159, 70, 15);
			add(lblComment);
		}
		
		private void refreshListModel(DefaultListModel<String> model, Session sess){
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
				repList = ServiceLocator.getRepService().listRep("all");
			}
			model.clear();
			for(Rep rep:repList){
				model.addElement("Id: " + rep.getIdentifier() + ", Client: "+ rep.getClientName() + ", createdBy: " + rep.getUname()+ ", Status: " + rep.getState().toString());
			}
			
		}
}
