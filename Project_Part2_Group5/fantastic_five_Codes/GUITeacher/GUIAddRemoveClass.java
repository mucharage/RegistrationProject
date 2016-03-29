package com.github.fantastic_five.GUITeacher;
/**
 * @author Christian Phillips
 * Group 5 
 */
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILoggedIn;
import com.github.fantastic_five.GUIStudent.GUIRemove;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class GUIAddRemoveClass extends JPanel
{
	/**
	 * private instant variables.
	 */
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnBack;
	private JLabel lblCrn;
	private JTable table_1;
	private JTable table;

	/**
	 * This GUI class displays the panel for adding and removing 
	 * courses that the teacher is teaching. 
	 * Here teacher can search course by CRN that he/she wants to 
	 * add or remove, and would allow them to do so. 
	 *
	 */
	public GUIAddRemoveClass()
	{
		setBounds(0, 0, 618, 434);
		setLayout(null);

		textField = new JTextField();
		textField.setBounds(88, 82, 206, 20);
		add(textField);
		textField.setColumns(10);

		//Button & Logic for Add courses
		btnNewButton = new JButton("Add");
		btnNewButton.setBounds(180, 183, 254, 23);
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		});
		add(btnNewButton);

		//Button & Logic for Remove Courses
		btnNewButton_1 = new JButton("Remove");
		btnNewButton_1.setBounds(180, 345, 254, 23);
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GUIRemove.main(null);
			}
		});
		add(btnNewButton_1);

		//adds a back button 
		btnBack = new JButton("Back");
		btnBack.setBounds(41, 389, 128, 23);
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		});
		btnBack.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUITeacher());
			}
		});
		add(btnBack);

		//adds a label named "Search By"
		JLabel lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setBounds(41, 56, 116, 20);
		lblSearchBy.setForeground(Color.GRAY);
		lblSearchBy.setFont(new Font("Verdana", Font.BOLD, 13));
		add(lblSearchBy);

		//adds a label named "CRN:"
		lblCrn = new JLabel("CRN:");
		lblCrn.setBounds(43, 84, 46, 14);
		lblCrn.setFont(new Font("Verdana", Font.BOLD, 12));
		add(lblCrn);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(41, 227, 540, 107);
		add(scrollPane_1);

		//adds a table
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, }, new String[] { "CRN", "Class", "Capacity", "Remaining", "Time", "Day", "Teacher", "Room" }));
		scrollPane_1.setViewportView(table_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 113, 539, 59);
		add(scrollPane);

		//adds another table 
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, }, new String[] { "CRN", "Class", "Capacity", "Remaining", "Time", "Day", "Teacher", "Room" }));
		scrollPane.setViewportView(table);
		
		JPanel loginPanel = new GUILoggedIn();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);
		
		//adds a label named "Add or Remove Courses
		JLabel lblCourseRemoval = new JLabel("Add or Remove Courses");
		lblCourseRemoval.setForeground(Color.GRAY);
		lblCourseRemoval.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCourseRemoval.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseRemoval.setBounds(177, 30, 243, 23);
		add(lblCourseRemoval);
	}
}