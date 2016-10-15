package main;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import framework.ServiceLocator;
import framework.Session;
import order_management.Crd;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CrdGUIPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CrdGUIPanel(Login login, Session session) {
		
		final Login prev = login;
		final Session sess = session;
		
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CRD GUI");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(70, 11, 288, 34);
		add(lblNewLabel);
		
		JList list = new JList();
		list.setBounds(70, 70, 367, 134);
		add(list);
		
		final DefaultListModel<String> listModel = new DefaultListModel<String>();
		ArrayList<Crd> crds = ServiceLocator.getCrdService().getAllCrd();
		
		for(Crd crd: crds){
			listModel.addElement("Id: " + crd.getIdentifier() + ", Budget:" + crd.getExp_budget() + ", Client: " + crd.getClientName());
		}
		
		list.setModel(listModel);
		
		JButton btnNewButton = new JButton("Sign Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prev.reinitialize();
			}
		});
		btnNewButton.setBounds(66, 254, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Create Tasks");
		btnNewButton_1.setBounds(181, 254, 117, 23);
		add(btnNewButton_1);

	}

}
