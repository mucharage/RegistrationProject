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
import com.github.fantastic_five.GUITeacher.GUIAddRemoveClass;
import com.github.fantastic_five.Logic.MiscUtils;

@SuppressWarnings("serial")
public class GUIViewSchedule extends JPanel
{
	/**
	 * This GUI that shall display teacher's individual schedule of courses he/she is teaching
	 */
	public GUIViewSchedule()
	{
		// Adds a ScrollPane
		setBounds(0, 0, 618, 434);
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 587, 107);
		add(scrollPane);
		
		// Adds a table which will display list of courses that user have chose
		JTable addedTable = new JTable();
		addedTable.setModel(new DefaultTableModel(GUIAddRemoveClass.getClassTable(), new String[] { "CRN", "Class", "Capacity", "Remaining", "Teacher", "Day", "Time", "Room" }));
		scrollPane.setViewportView(addedTable);
				
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
					addedTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				}
				catch (PrinterException e1)
				{					
					e1.printStackTrace();
				}
			}
		});
		add(btnPrint);

		// Adds a login GUI
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// Adds a JLabel named "View Schedule"
		JLabel lblViewSchedule = new JLabel("View Schedule");
		lblViewSchedule.setForeground(Color.GRAY);
		lblViewSchedule.setFont(new Font("Verdana", Font.BOLD, 16));
		lblViewSchedule.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewSchedule.setBounds(177, 30, 243, 23);
		add(lblViewSchedule);
	}
}
