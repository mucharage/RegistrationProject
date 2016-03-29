/**
 * @author Alay Patel (leader)
 * Group 5 
 */

package com.github.fantastic_five.GUIStudent;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILoggedIn;

@SuppressWarnings("serial")
public class GUIAddRemoveCourse extends JPanel
{
	/** 
	 * Private instant variables 
	 */
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnBack;
	private JLabel lblCrn;
	private JTable table_1;
	private JTable table;

	/**
	 * This GUI class displays the panel for adding and removing courses.
	 * Here student can search course by CRN that he/she want to
	 * add or remove, and would allow them to do so. 
	 */
	public GUIAddRemoveCourse()
	{
		setBounds(0, 0, 618, 434);
		setLayout(null);

		textField = new JTextField();
		textField.setBounds(88, 82, 206, 20);
		add(textField);
		textField.setColumns(10);
		
		/**
		 * Button & Logic for Add Courses to list below.
		 */
		btnNewButton = new JButton("Add");
		btnNewButton.setBounds(180, 183, 254, 23);
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}//end of the actionPerformed
		});//end of the addActionListener
		add(btnNewButton);
		
		/**
		 * Button & Logic for Remove for the list below
		 */

		btnNewButton_1 = new JButton("Remove");
		btnNewButton_1.setBounds(180, 345, 254, 23);
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GUIRemove.main(null);
			}//end of the actionPerformed 
		});//end of the actionPerformed 
		add(btnNewButton_1);

		/**
		 * adds a back button. 
		 */
		btnBack = new JButton("Back");
		btnBack.setBounds(41, 389, 128, 23);		
		btnBack.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIStudent());
			}//end of mouseClicked
		});//end of addMouseListener
		add(btnBack);

		/**
		 * adds a label named, "Search By"
		 */
		JLabel lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setBounds(41, 56, 116, 20);
		lblSearchBy.setForeground(Color.GRAY);
		lblSearchBy.setFont(new Font("Verdana", Font.BOLD, 13));
		add(lblSearchBy);
		
		/**
		 * Adds a label named, "CRN:"
		 */
		lblCrn = new JLabel("CRN:");
		lblCrn.setBounds(43, 84, 46, 14);
		lblCrn.setFont(new Font("Verdana", Font.BOLD, 12));
		add(lblCrn);

		/**
		 * Adds a ScrollPane
		 */
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(41, 227, 540, 107);
		add(scrollPane_1);
		
		/**
		 * Creates a Table which shall display result of the course that 
		 * user has searched for 
		 */
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, }, new String[] { "CRN", "Class", "Capacity", "Remaining", "Time", "Day", "Teacher", "Room" }));
		scrollPane_1.setViewportView(table_1);
		 
		/**
		 * Creates an another ScrollPane
		 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 113, 539, 59);
		add(scrollPane);
		
		/**
		 * Creates an another Table which shall course that user has added. 
		 */
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, }, new String[] { "CRN", "Class", "Capacity", "Remaining", "Time", "Day", "Teacher", "Room" }));
		scrollPane.setViewportView(table);
		
		/**
		 * Adds a GUILogIn
		 */
		JPanel loginPanel = new GUILoggedIn();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);
		
		/**
		 * Adds a Label named, "Add or Remove Courses."
		 */
		JLabel lblCourseRemoval = new JLabel("Add or Remove Courses");
		lblCourseRemoval.setForeground(Color.GRAY);
		lblCourseRemoval.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCourseRemoval.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseRemoval.setBounds(177, 30, 243, 23);
		add(lblCourseRemoval);
		
	}//end of GUIAddorRemoveCourse()
}//end of JPanel extension of GUIAddorRemoveCourse()