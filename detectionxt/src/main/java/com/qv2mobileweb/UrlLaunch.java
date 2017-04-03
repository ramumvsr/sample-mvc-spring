package com.qv2mobileweb;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class UrlLaunch {

	private JFrame frame;
	JScrollPane scrollPane;
	private JTextField urlTextField;
	private JTree tree;

	private JTextField deviceOSVersion;
	private JTextField deviceName;
	private JTextField browserName;

	private ButtonGroup group;

	private Set<String> keysList;
	private Map<String, List<String>> finalElementList;

	public UrlLaunch(String[] capabilities){
		initializeFrame(capabilities);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	public void initializeFrame(String[] capabilities) {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 9));
		frame.setBackground(Color.WHITE);
		frame.setTitle("Element Extractor");
		frame.setBounds(100, 100, 1316, 829);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception ex) {
			System.out.println("Failed loading L&F: ");
			System.out.println(ex);
		}
		
		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(Color.LIGHT_GRAY);
		headerPanel.setBounds(10, 11, 1280, 145);
		frame.getContentPane().add(headerPanel);
		headerPanel.setLayout(null);

		JPanel headingPanel = new JPanel();

		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);

		JLabel imagelabel = new JLabel();
		imagelabel.setIcon(new ImageIcon("resources/images/qv2logo.png"));

		imagelabel.setBounds(0, 0, 347, 74);
		headerPanel.add(imagelabel);

		JLabel deviceNameLabel = new JLabel("Device Name");
		deviceNameLabel.setBounds(349, 11, 123, 14);
		headerPanel.add(deviceNameLabel);

		JLabel deviceOSLabel = new JLabel("Device OS Version");
		deviceOSLabel.setBounds(349, 43, 123, 14);
		headerPanel.add(deviceOSLabel);

		JLabel browserNameLabel = new JLabel("Browser Name");
		browserNameLabel.setBounds(349, 75, 123, 14);
		headerPanel.add(browserNameLabel);

		JLabel urlLabel = new JLabel("Enter URL");
		urlLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		urlLabel.setForeground(Color.BLACK);
		urlLabel.setBounds(349, 100, 123, 14);
		headerPanel.add(urlLabel);
		
		
		
		   String [] header={ "Element Type", "Element Name", "Event Type", "Data Value", "Content"};
	        String [][] datat={{"null","null","null","null","null"},{"null","null","null","null","null"},{"null","null","null","null","null"}};


	        DefaultTableModel model = new DefaultTableModel(datat,header);
	        
		
		
		

		urlTextField = new JTextField();
		urlTextField.setBounds(482, 100, 248, 19);
		headerPanel.add(urlTextField);
		urlTextField.setColumns(10);

		deviceOSVersion = new JTextField();
		deviceOSVersion.setBounds(482, 40, 248, 20);
		headerPanel.add(deviceOSVersion);
		deviceOSVersion.setColumns(10);
		deviceOSVersion.setText(capabilities[2]);
		deviceOSVersion.setEnabled(false);
		deviceOSVersion.setForeground(Color.blue);
		

		deviceName = new JTextField();
		deviceName.setBounds(482, 8, 248, 20);
		headerPanel.add(deviceName);
		deviceName.setColumns(10);
		deviceName.setText(capabilities[1]);
		deviceName.setEnabled(false);
		deviceName.setForeground(Color.black);
		
		
		browserName = new JTextField();
		browserName.setBounds(482, 70, 248, 20);
		headerPanel.add(browserName);
		browserName.setColumns(10);
		browserName.setText("Chrome");
		browserName.setEnabled(false);
		browserName.setForeground(Color.black);
		
		JRadioButton platFormAndriod = new JRadioButton("Android",true);
		platFormAndriod.setBackground(Color.LIGHT_GRAY);
		platFormAndriod.setBounds(793, 34, 109, 23);
		headerPanel.add(platFormAndriod);

		
		
		JRadioButton platFormIOS = new JRadioButton("IOS");
		platFormIOS.setBackground(Color.LIGHT_GRAY);
		platFormIOS.setBounds(793, 58, 109, 23);
		headerPanel.add(platFormIOS);

		group = new ButtonGroup();
		group.add(platFormAndriod);
		group.add(platFormIOS);
		
		
		
		 JButton record = new JButton("Record");
	       
	        record.setBounds(772, 90, 70, 30);
	        headerPanel.add(record);
	        
	        JButton stopbutton = new JButton("Stop");
	        stopbutton.setBounds(877, 90, 70, 30);
	        headerPanel.add(stopbutton);

		headingPanel.setBorder(null);
		headingPanel.setFocusTraversalKeysEnabled(false);
		headingPanel.setBounds(0, 135, 1290, 615);
		frame.getContentPane().add(headingPanel);
		headingPanel.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(10, 30, 1280, 41);
		headingPanel.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblWebPageElements = new JLabel(" Mobile Web Page Elements");
		lblWebPageElements.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWebPageElements.setBounds(10, 11, 232, 19);
		panel_2.add(lblWebPageElements);

		scrollPane.setBounds(22, 114, 473, 495);
		headingPanel.add(scrollPane);

		JButton launchButton = new JButton("Launch");
		launchButton.setBorderPainted(false);

		ImageIcon launchImg = new ImageIcon("resources/images/launch_btn.png");
		launchButton.setIcon(launchImg);
		//launchButton.setBackground(new Color(220, 20, 60));
		//launchButton.setForeground(Color.WHITE);
		launchButton.setBounds(1000, 90, 70, 30);
		headerPanel.add(launchButton);

		launchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setVisible(true);
				launchButton.setEnabled(false);

				getElements();

			}
		});

		JPanel footerPanel = new JPanel();
		footerPanel.setBackground(Color.GRAY);
		footerPanel.setBounds(0, 752, 1290, 38);
		frame.getContentPane().add(footerPanel);
		footerPanel.setLayout(null);

		JLabel lblCopyRights = new JLabel(
				"2013-2017 Inc.Copy rights reserved.2.0.0.");
		lblCopyRights.setForeground(Color.WHITE);
		lblCopyRights.setBounds(41, 11, 338, 14);
		footerPanel.add(lblCopyRights);
		frame.setVisible(true);
		
		
		
		
		
		

		 record.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		
	        		
	        		
	        		
	        		

                   JScrollPane js=new JScrollPane();
                   js.setViewportBorder(null);
                   js.setBorder(null);
                   js.setAlignmentX(Component.LEFT_ALIGNMENT);
                   js.setFocusable(false);
                   scrollPane.setColumnHeaderView(js);
                   js.setBackground(Color.WHITE);
                   js.setPreferredSize(new Dimension(469, 120));
                   
                           JTable table = new JTable(model);
                           table.setFillsViewportHeight(true);
                           table.setFocusCycleRoot(true);
                           table.setEnabled(false);
                           scrollPane.setViewportView(table);
                           table.setRowSelectionAllowed(false);
                           table.setBackground(Color.WHITE);
                           table.setBorder(null);
                           
                                   table.setPreferredScrollableViewportSize(new Dimension(450,63));
                                   
                                   table.setShowGrid(false);
                                   table.setShowVerticalLines(false);
                                   table.setShowHorizontalLines(false);

	        		
	        		
	        		
	        		
	        		
	        	}
	        });
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	/**
	 * This method gets Elements and appends to the view
	 */
	private void getElements() {

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("HTML");

		String url = urlTextField.getText();
		String mobileVersion = deviceOSVersion.getText();
		String mobileName = deviceName.getText();
		String devicePlatformName = "";

		String deviceBrowsername = browserName.getText();

		Enumeration<AbstractButton> allRadioButton = group.getElements();
		while (allRadioButton.hasMoreElements()) {
			JRadioButton temp = (JRadioButton) allRadioButton.nextElement();
			if (temp.isSelected()) {
				devicePlatformName = temp.getText();
			}
		}
		ElementExtractor extractElements = ElementExtractor.getInstance(
				deviceBrowsername, mobileName, mobileVersion,
				devicePlatformName);

		finalElementList = extractElements.extarctElements(url);

		keysList = finalElementList.keySet();

		for (String key : keysList) {
			root.add(new DefaultMutableTreeNode(key.toUpperCase()));

		}

		Enumeration childNodes = root.children();

		while (childNodes.hasMoreElements()) {
			DefaultMutableTreeNode child = (DefaultMutableTreeNode) childNodes
					.nextElement();

			for (Map.Entry<String, List<String>> entry : finalElementList
					.entrySet()) {
				List<String> xpaths = entry.getValue();

				if (entry.getKey().equalsIgnoreCase(
						(String) child.getUserObject())) {
					for (String xpath : xpaths) {
						if (xpath.isEmpty()) {
							continue;
						}
						child.add(new DefaultMutableTreeNode(xpath));

					}
				}

			}

		}

		tree = new JTree(root);

		tree.setBackground(Color.WHITE);
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		JScrollPane jsp = new JScrollPane(tree, v, h);

		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {

				TreePath path = tree.getPathForLocation(me.getX(), me.getY());
				if (path != null) {
					String xpath = path.getLastPathComponent().toString();

					extractElements.highlightElement(xpath);

				}

			}
		});

		scrollPane.setViewportView(tree);

	}

	
}
