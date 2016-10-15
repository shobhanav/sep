package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import framework.ServiceLocator;
import framework.Session;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Shape;

public class Login {

	private JFrame frmSwedishEventPlanner;
	private JTextField UnameTxtField;
	private JTextField passwordTxtField;
	private JPanel loginPanel;
	private Session session;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmSwedishEventPlanner.setSize(1000, 600);
					window.frmSwedishEventPlanner.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		session = new Session();
		frmSwedishEventPlanner = new JFrame();
		frmSwedishEventPlanner.setTitle("Swedish Event Planner");
		frmSwedishEventPlanner.setBounds(100, 100, 450, 300);
		frmSwedishEventPlanner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loginPanel = new JPanel();
		frmSwedishEventPlanner.getContentPane().add(loginPanel, BorderLayout.CENTER);
		loginPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel CentrePnl = new JPanel();
		loginPanel.add(CentrePnl, BorderLayout.CENTER);
		GridBagLayout gbl_CentrePnl = new GridBagLayout();
		gbl_CentrePnl.columnWidths = new int[]{52, 87, 50, 0};
		gbl_CentrePnl.rowHeights = new int[]{20, 20, 0};
		gbl_CentrePnl.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_CentrePnl.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		CentrePnl.setLayout(gbl_CentrePnl);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		CentrePnl.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		UnameTxtField = new JTextField("fm");
		GridBagConstraints gbc_UnameTxtField = new GridBagConstraints();
		gbc_UnameTxtField.fill = GridBagConstraints.BOTH;
		gbc_UnameTxtField.insets = new Insets(0, 0, 5, 5);
		gbc_UnameTxtField.gridx = 1;
		gbc_UnameTxtField.gridy = 0;
		CentrePnl.add(UnameTxtField, gbc_UnameTxtField);
		UnameTxtField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 0, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 1;
		CentrePnl.add(lblPassword, gbc_lblPassword);
		
		passwordTxtField = new JTextField("password");
		GridBagConstraints gbc_passwordTxtField = new GridBagConstraints();
		gbc_passwordTxtField.fill = GridBagConstraints.BOTH;
		gbc_passwordTxtField.insets = new Insets(0, 0, 0, 5);
		gbc_passwordTxtField.gridx = 1;
		gbc_passwordTxtField.gridy = 1;
		CentrePnl.add(passwordTxtField, gbc_passwordTxtField);
		passwordTxtField.setColumns(10);
		
		JPanel ImagePanel = new JPanel();
		loginPanel.add(ImagePanel, BorderLayout.WEST);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/resources/images.jpg")));
		ImagePanel.add(lblNewLabel_2);
		
		JPanel Btnpanel = new JPanel();
		loginPanel.add(Btnpanel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isAuthenticated = ServiceLocator.getSecurityService().isAuthenticated(UnameTxtField.getText(), passwordTxtField.getText());
				if(isAuthenticated){
					session.setCurrentUser(UnameTxtField.getText());
					UnameTxtField.setText("");
					passwordTxtField.setText("");
					frmSwedishEventPlanner.remove(loginPanel);	
					ArrayList<String> roles = ServiceLocator.getSecurityService().getRoles(session.getCurrentUser());
					if(roles.contains("pm") || roles.contains("sm")){
						frmSwedishEventPlanner.getContentPane().add(new CrdGUIPanel(Login.this, session));
					}else{
						frmSwedishEventPlanner.getContentPane().add(new RepGUIPanel(Login.this, session));
					}
										
					frmSwedishEventPlanner.getContentPane().revalidate();
					frmSwedishEventPlanner.getContentPane().repaint();
							
				}else{
					JOptionPane.showMessageDialog(frmSwedishEventPlanner, "Invalid username or password");
				}
			}
		});
		Btnpanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSwedishEventPlanner.dispose();
			}
		});
		Btnpanel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Please login with username and password");
		loginPanel.add(lblNewLabel, BorderLayout.NORTH);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public void reinitialize(){
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				frmSwedishEventPlanner.getContentPane().remove(frmSwedishEventPlanner.getContentPane().getComponent(0));
				frmSwedishEventPlanner.getContentPane().add(loginPanel);
				frmSwedishEventPlanner.getContentPane().revalidate();
				frmSwedishEventPlanner.getContentPane().repaint();				
			}
		});
	}

}
