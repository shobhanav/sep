package main;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.SwingConstants;

import framework.ServiceLocator;
import framework.Session;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class UserInterface extends JPanel {
	
	JButton buttonRep;
	JButton buttonCrd;
	JButton buttonRecruitment;
	JButton buttonFinancial;
	JButton buttonSignout;
	private JButton buttonSubTask;
	private JButton buttonTask;
	
	/**
	 * Create the panel.
	 */
	public UserInterface(Login login, Session session) {
		final Login prev = login;
		final Session sess = session;
		final UserInterface me = this;
		final ArrayList<String> roles = ServiceLocator.getSecurityService().getRoles(sess.getCurrentUser());
		
		buttonRep = new JButton("REP");
		buttonRep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.rep(sess,me);
			}
		});
		buttonRep.setAlignmentY(Component.TOP_ALIGNMENT);
		buttonRep.setVisible(false);
		setLayout(null);
		buttonRep.setBounds(0, 0, 60, 25);
		add(buttonRep);
		
		buttonCrd = new JButton("Crd");
		buttonCrd.setBounds(0, 37, 58, 25);
		buttonCrd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.crd(sess,me);
			}
		});
		buttonCrd.setAlignmentY(Component.TOP_ALIGNMENT);
		buttonCrd.setHorizontalAlignment(SwingConstants.LEFT);
		buttonCrd.setVisible(false);
		add(buttonCrd);
		
		buttonFinancial = new JButton("Finance");
		buttonFinancial.setBounds(0, 74, 89, 25);
		buttonFinancial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.financial(sess,me);
			}
		});
		buttonFinancial.setAlignmentY(Component.TOP_ALIGNMENT);
		buttonFinancial.setVisible(false);
		add(buttonFinancial);
		
		buttonRecruitment = new JButton("Recruitment");
		buttonRecruitment.setBounds(0, 111, 120, 25);
		buttonRecruitment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.recruitment(sess,me);
			}
		});
		buttonRecruitment.setAlignmentY(Component.TOP_ALIGNMENT);
		buttonRecruitment.setVisible(false);
		add(buttonRecruitment);
		
		buttonSignout = new JButton("signout");
		buttonSignout.setBounds(73, 228, 88, 25);
		buttonSignout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.reinitialize();
			}
		});
		buttonSignout.setAlignmentY(Component.TOP_ALIGNMENT);
		add(buttonSignout);
		
		buttonSubTask = new JButton("SubTask");
		buttonSubTask.setBounds(-5, 191, 94, 25);
		buttonSubTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.subtask(sess,me);
			}
		});
		buttonSubTask.setVisible(false);
		buttonSubTask.setAlignmentY(Component.TOP_ALIGNMENT);
		add(buttonSubTask);
		
		buttonTask = new JButton("Task");
		buttonTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.task(sess,me);
			}
		});
		buttonTask.setBounds(0, 154, 117, 25);
		buttonTask.setVisible(false);
		add(buttonTask);
		
		
		//visibility for the different function
		if(roles.contains("cso") ||  roles.contains("scso") || roles.contains("admin") || roles.contains("fm") ){			
			buttonRep.setVisible(true);			
		}
		if(roles.contains("pm") || roles.contains("sm")){
			buttonCrd.setVisible(true);
		}
		if(roles.contains("pm") || roles.contains("sm") ||roles.contains("fm")){
			buttonFinancial.setVisible(true);
		}
		if(roles.contains("pm") || roles.contains("sm") ||roles.contains("rt")){
			buttonTask.setVisible(true);
		}
		if(roles.contains("pm") || roles.contains("sm") || roles.contains("rh")){
			buttonRecruitment.setVisible(true);
		}
		if(roles.contains("pm") || roles.contains("sm") || roles.contains("tm") || roles.contains("rt")){
			buttonSubTask.setVisible(true);
		}
	}	
}