package com.github.fantastic_five.GUITeacher;

/**
 * Just a test class to help with the creation of other classes with similar functions, serves no purpose to the final product
 * /
 


/**
 * @author Christian Phillips
 * Group 5 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILogStatus;

@SuppressWarnings("serial")
public class GUIAddCourse extends JPanel
{
	/**
	 * private instant variables.
	 */
	private JTextField textField;
	private JButton btnAddCourse;
	private JButton btnBack;
	private JLabel lblCrn;
	private JTable table;

	/**
	 * This GUI class displays the panel for adding and removing courses that the teacher is teaching. Here teacher can search course by CRN that he/she wants to add or remove, and would allow them to do so.
	 *
	 */
	public GUIAddCourse()
	{
		setBounds(0, 0, 618, 434);
		setLayout(null);

		textField = new JTextField();
		textField.setBounds(88, 82, 206, 20);
		add(textField);
		textField.setColumns(10);

		// Button & Logic for Add Course
		btnAddCourse = new JButton("Add Course");
		btnAddCourse.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		btnAddCourse.setBounds(180, 310, 254, 23);
		add(btnAddCourse);


		// adds a back button
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUITeacher());
			}
		});
		btnBack.setBounds(41, 389, 128, 23);
		add(btnBack);

		// adds a label named "Search By"
		JLabel lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setBounds(41, 56, 116, 20);
		lblSearchBy.setForeground(Color.GRAY);
		lblSearchBy.setFont(new Font("Verdana", Font.BOLD, 13));
		add(lblSearchBy);

		// adds a label named "CRN:"
		lblCrn = new JLabel("CRN:");
		lblCrn.setBounds(43, 84, 46, 14);
		lblCrn.setFont(new Font("Verdana", Font.BOLD, 12));
		add(lblCrn);


		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 113, 539, 187);
		add(scrollPane);

		// adds a table
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, }, new String[] { "CRN", "Class", "Capacity", "Remaining", "Time", "Day", "Teacher", "Room" })
		{
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// adds a label named "Add Courses"
		JLabel lblCourseAdd = new JLabel("Add Course");
		lblCourseAdd.setForeground(Color.GRAY);
		lblCourseAdd.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCourseAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseAdd.setBounds(177, 30, 243, 23);
		add(lblCourseAdd);
	}
}
