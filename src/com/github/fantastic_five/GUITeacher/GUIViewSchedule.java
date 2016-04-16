package com.github.fantastic_five.GUITeacher;

/**
 * @author Christian Phillips
 * Group 5 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.MiscUtils;

@SuppressWarnings("serial")
public class GUIViewSchedule extends JPanel
{
	//This GUI that shall display teacher's individual schedule of courses he/she is teaching
	public GUIViewSchedule()
	{
		// Adds a ScrollPane
		setBounds(0, 0, 618, 434);
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 587, 107);
		add(scrollPane);

		
		// Adds a table which will display list of courses that user have chose
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, }, new String[] { "CRN", "Class", "Capacity", "Remaining", "Time", "Day", "Teacher", "Room" })
		{
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		});
		scrollPane.setViewportView(table);
				
		// Button & logic for back button
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUITeacher());
			}
		});
		btnBack.setBounds(10, 386, 128, 23);
		add(btnBack);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(469, 386, 128, 23);
		btnPrint.addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent e)
			{			
				MessageFormat header = new MessageFormat ("Schedule");				
				String name = MiscUtils.getCurrentLoggedInUser().getFirstName()+ " " + MiscUtils.getCurrentLoggedInUser().getLastName();
				String userID = MiscUtils.getCurrentLoggedInUser().getUserID();	
				MessageFormat footer = new MessageFormat("Name: "  + name + "                                                                User ID: " + userID);						
				try
				{
					table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				}
				catch (PrinterException e1)
				{					
					e1.printStackTrace();
				}
			}
		});
		add(btnPrint);

		// Button & Logic for print button
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				createPrintWindow();
			}// end of actionPerformed
		});// end of actionListener
		btnPrint.setBounds(498, 386, 99, 23);
		add(btnPrint);
		
	 	// Adds a scrollPane
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 178, 587, 197);
		add(scrollPane_1);

		// Adds a login GUI
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// Adds a JLabel named "View Schedule"
		JLabel lblCourseRemoval = new JLabel("View Schedule");
		lblCourseRemoval.setForeground(Color.GRAY);
		lblCourseRemoval.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCourseRemoval.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseRemoval.setBounds(177, 30, 243, 23);
		add(lblCourseRemoval);

	}// end of GUIViewSchedule()
	
	// Testing Print window
	public void createPrintWindow()
	{
		// Makes a new window
		JFrame printView = new JFrame("Print Preview");
		// Finds a starting point for it to be put at
		Point startingLoc = StudentRegistrationMain.mainWindow.getLocation();
		startingLoc.translate(40, 20);

		// Basic pop-up window initialization
		printView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		printView.setPreferredSize(StudentRegistrationMain.mainWindowDimension);
		printView.setResizable(false);
		printView.pack();
		printView.setVisible(true);
		printView.setLocation(startingLoc);
		printView.getContentPane().add(new GUIPrint());
		printView.pack();
	}
	
}// end of JPanel extension of GUIViewSchedule
