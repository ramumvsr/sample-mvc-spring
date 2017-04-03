package com.qv2mobileweb;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * This class will execute the background thread continuously to check whether device is connected or not.
 */
public class USBConnect {
	private JFrame frame;
	private JPanel panel;
	private JLabel imageLabel;
	private JLabel connectLabel;

	private JLabel androidLabel;

	private AutoDetect autoDetect;
	private UrlLaunch urlLaunch;

	static USBConnect usb;

	/**
	 * Launch the application.
	 * 
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		usb = new USBConnect();
		usb.initialize();
		Thread.currentThread().sleep(3000);
		usb.checkDevice();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 945, 624);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(true);

		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(596, 0, 343, 595);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		imageLabel = new JLabel("");
		imageLabel.setIcon(new ImageIcon("resources/images/phonemobile.PNG"));
		imageLabel.setBounds(128, 164, 117, 290);
		imageLabel.setVisible(true);
		panel.add(imageLabel);

		connectLabel = new JLabel(
				"Connect your device to perform\r\n extraction >>");
		connectLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		connectLabel.setBounds(22, 419, 561, 122);
		connectLabel.setVisible(true);

		frame.getContentPane().add(connectLabel);
		androidLabel = new JLabel("Android");

		androidLabel.setForeground(new Color(70, 130, 180));
		androidLabel.setFont(new Font("Tahoma", Font.PLAIN, 42));
		androidLabel.setBounds(207, 124, 200, 103);
		androidLabel.setVisible(true);
		frame.getContentPane().add(androidLabel);
		frame.setVisible(true);

	}

	/**
	 * This method checks whether the device connected or not
	 */
	public void checkDevice() {

		autoDetect = new AutoDetect();

		if (autoDetect.checkDevice()) {

			JOptionPane.showConfirmDialog(frame,
					"Your Device is Connected.Click \"Yes\" to Continue.");

			frame.dispose();
			goToUrlLaunch();

		} else {
			checkDevice();
		}

	}

	/**
	 * This method navigates to UrlLaunch page
	 */
	public void goToUrlLaunch() {

		String[] capabilities = autoDetect.getDeviceProperties();

		frame.setVisible(false);

		urlLaunch = new UrlLaunch(capabilities);

	}

}
