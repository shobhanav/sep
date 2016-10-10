package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Login {

	private JFrame frmSwedishEventPlanner;
	private JTextField UnameTxtField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
		frmSwedishEventPlanner = new JFrame();
		frmSwedishEventPlanner.setTitle("Swedish Event Planner");
		frmSwedishEventPlanner.setBounds(100, 100, 450, 300);
		frmSwedishEventPlanner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("Please login with username and password");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frmSwedishEventPlanner.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel Btnpanel = new JPanel();
		frmSwedishEventPlanner.getContentPane().add(Btnpanel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		Btnpanel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Close");
		Btnpanel.add(btnNewButton_1);
		
		JPanel CentrePnl = new JPanel();
		frmSwedishEventPlanner.getContentPane().add(CentrePnl, BorderLayout.CENTER);
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
		
		UnameTxtField = new JTextField();
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
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		CentrePnl.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JPanel ImagePanel = new JPanel();
		frmSwedishEventPlanner.getContentPane().add(ImagePanel, BorderLayout.WEST);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\eavnvya\\workspace\\SEP\\images\\images.jpg"));
		ImagePanel.add(lblNewLabel_2);
	}

}
