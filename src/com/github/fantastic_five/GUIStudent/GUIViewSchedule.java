/**
 * @author Alay Patel (leader)
 * Group 5 
 */

package com.github.fantastic_five.GUIStudent;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;

import javax.swing.JButton;
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
	
	/**
	 * This GUI that shall display student's individual schedule of courses that he/she has chosen
	 */
	public GUIViewSchedule()
	{
		/**
		 * adds a ScrollPane
		 */
		setBounds(0, 0, 618, 434);
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 109, 587, 106);
		add(scrollPane);

		/**
		 * adds a table which would displays list of courses that user have chosen
		 */
		JTable addedTable = new JTable();
		addedTable.setModel(new DefaultTableModel(GUIAddRemoveCourse.getClassTable(), new String[] { "CRN", "Class", "Capacity", "Remaining", "Teacher", "Day", "Time", "Room" }));
		scrollPane.setViewportView(addedTable);
//		addedTable = new JTable();
//		addedTable.setModel(new DefaultTableModel(getClassTable(), new String[] { "CRN", "Class", "Capacity", "Remaining", "Time", "Day", "Teacher", "Room" }));
//		scrollPane.setViewportView(addedTable);

		/**
		 * Button & logic for back button
		 */
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(10, 386, 128, 23);
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIStudent());
			}
		});
		add(btnBack);

		/**
		 * Button & Logic for print button
		 */
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(498, 386, 99, 23);
		btnPrint.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MessageFormat header = new MessageFormat("Schedule");

				String name = MiscUtils.getCurrentLoggedInUser().getFirstName() + " " + MiscUtils.getCurrentLoggedInUser().getLastName();
				String userID = MiscUtils.getCurrentLoggedInUser().getUserID();
				MessageFormat footer = new MessageFormat("Name: " + name + "                                                                User ID: " + userID);
				try
				{
					addedTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				}
				catch (PrinterException e1)
				{
					e1.printStackTrace();
				}

			}// end of actionPerformed
		});// end of actionListener
		add(btnPrint);

		/**
		 * adds a login GUI
		 */
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		/**
		 * adds a JLabel named "View Schedule"
		 */
		JLabel lblCourseRemoval = new JLabel("View Schedule");
		lblCourseRemoval.setBounds(178, 54, 243, 23);
		lblCourseRemoval.setForeground(Color.GRAY);
		lblCourseRemoval.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCourseRemoval.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCourseRemoval);

	}// end of GUIViewSchedule()	
}// end of JPanel extension of GUIViewSchedule
