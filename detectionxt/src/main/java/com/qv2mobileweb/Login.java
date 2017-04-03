package com.qv2mobileweb;


import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import jiconfont.icons.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class Login {

	private JFrame frmQvLogin;
	private JTextField textField;
	private final Action action = new SwingAction();
	private JPasswordField passwordField;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmQvLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws MalformedURLException 
	 */
	public Login() throws MalformedURLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws MalformedURLException 
	 */
	private void initialize() throws MalformedURLException {
		frmQvLogin = new JFrame();
		frmQvLogin.setTitle("QV2 Mobile Web Element Extractor - Login");
		frmQvLogin.setBackground(Color.WHITE);
		frmQvLogin.getContentPane().setBackground(Color.WHITE);
		frmQvLogin.setBounds(100, 100, 619, 483);
		frmQvLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQvLogin.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(191, 196, 245, 38);
		frmQvLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		//IconFontSwing.register(new FontAwesome().getFontFamily());
		
		
		//Icon icon = IconFontSwing.buildIcon(FontAwesome.SMILE_O, 40, new Color(0, 150, 0));
		
		//textField.setAction((Action) icon);
		IconFontSwing.register(FontAwesome.getIconFont());
		Icon icon1 = IconFontSwing.buildIcon(FontAwesome.USER, 20, new Color(0,0,0));
		Icon icon2 = IconFontSwing.buildIcon(FontAwesome.LOCK, 20, new Color(0,0,0));
	
		
		passwordField = new JPasswordField();
		passwordField.setAlignmentX(Component.LEFT_ALIGNMENT);
		passwordField.setBounds(191, 245, 245, 38);
		frmQvLogin.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Qv2 Logo");
		
		
		lblNewLabel_2.setIcon(new ImageIcon(new File("D:/NagarjunaData/EclipseWorkspace/QV2MobileWebElementExtractor/resources/images/qv2logo.png").toURL()));
		lblNewLabel_2.setBounds(135, 70, 312, 94);
		frmQvLogin.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Sign in");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname=textField.getText();
				String password=passwordField.getText();
				if(uname.equalsIgnoreCase("user")&&password.equalsIgnoreCase("user")){
					/*UrlLaunch page;
					page = new UrlLaunch();
					frmQvLogin.dispose();
					page.next();*/
				}
				else
					JOptionPane.showMessageDialog(frmQvLogin, "Please Enter Username and Paswword");
				
			}
		});
		btnNewButton.setBackground(new Color(220, 20, 60));
		btnNewButton.setForeground(Color.WHITE);
		
		btnNewButton.setBounds(152, 309, 284, 38);
		frmQvLogin.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton();
		btnNewButton_1.setRequestFocusEnabled(false);
		btnNewButton_1.setBackground(new Color(211, 211, 211));
		btnNewButton_1.setRolloverEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setIcon(icon1);
		btnNewButton_1.setBounds(152, 196, 39, 38);
		btnNewButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);

		frmQvLogin.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.setBackground(SystemColor.scrollbar);
		btnNewButton_2.setIcon(icon2);
		btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(152, 245, 39, 38);
		frmQvLogin.getContentPane().add(btnNewButton_2);
		
		lblNewLabel = new JLabel("Forgot your password?");
		lblNewLabel.setBounds(152, 373, 284, 14);
		frmQvLogin.getContentPane().add(lblNewLabel);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
