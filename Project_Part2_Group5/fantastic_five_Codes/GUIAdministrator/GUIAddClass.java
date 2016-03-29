package com.github.fantastic_five.GUIAdministrator;

/**
 * @author Fantastic Five (Jose Stovall)
 * A JPanel containing all fields required to create a class
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILoggedIn;

@SuppressWarnings("serial")
public class GUIAddClass extends JPanel
{
	// Private instance variables
	private JTextField fieldCRN;
	private JTextField fieldCourseName;
	private JTextField fieldSection;
	private JTextField fieldCourseDesc;
	private JTextField fieldDays;
	private JTextField fieldTimes;
	private JTextField fieldCapacity;

	public GUIAddClass()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		// All Labels and their positions:
		JLabel lblCrn = new JLabel("CRN:");
		lblCrn.setHorizontalAlignment(SwingConstants.LEFT);
		lblCrn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCrn.setBounds(119, 115, 111, 21);
		add(lblCrn);

		JLabel lblCourseName = new JLabel("Course Name:");
		lblCourseName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCourseName.setBounds(119, 140, 111, 14);
		add(lblCourseName);

		JLabel lblSection = new JLabel("Section  Number:");
		lblSection.setHorizontalAlignment(SwingConstants.LEFT);
		lblSection.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSection.setBounds(119, 165, 111, 14);
		add(lblSection);

		JLabel lblCourseDesc = new JLabel("Course Desc:");
		lblCourseDesc.setHorizontalAlignment(SwingConstants.LEFT);
		lblCourseDesc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseDesc.setBounds(119, 190, 111, 14);
		add(lblCourseDesc);

		JLabel lblDaysOffered = new JLabel("Days Offered:");
		lblDaysOffered.setHorizontalAlignment(SwingConstants.LEFT);
		lblDaysOffered.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDaysOffered.setBounds(119, 215, 111, 14);
		add(lblDaysOffered);

		JLabel lblTimeOffered = new JLabel("Time Offered:");
		lblTimeOffered.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimeOffered.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTimeOffered.setBounds(119, 240, 111, 14);
		add(lblTimeOffered);

		JLabel lblStudentCapacity = new JLabel("Student Capacity:");
		lblStudentCapacity.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentCapacity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStudentCapacity.setBounds(119, 265, 111, 14);
		add(lblStudentCapacity);

		// All modifiable text fields and their positions:
		fieldCRN = new JTextField();
		fieldCRN.setColumns(10);
		fieldCRN.setBounds(240, 113, 217, 20);
		add(fieldCRN);

		fieldCourseName = new JTextField();
		fieldCourseName.setColumns(10);
		fieldCourseName.setBounds(240, 138, 217, 20);
		add(fieldCourseName);

		fieldSection = new JTextField();
		fieldSection.setColumns(10);
		fieldSection.setBounds(240, 163, 217, 20);
		add(fieldSection);

		fieldCourseDesc = new JTextField();
		fieldCourseDesc.setColumns(10);
		fieldCourseDesc.setBounds(240, 188, 217, 20);
		add(fieldCourseDesc);

		fieldDays = new JTextField();
		fieldDays.setColumns(10);
		fieldDays.setBounds(240, 213, 217, 20);
		add(fieldDays);

		fieldTimes = new JTextField();
		fieldTimes.setColumns(10);
		fieldTimes.setBounds(240, 238, 217, 20);
		add(fieldTimes);

		fieldCapacity = new JTextField();
		fieldCapacity.setColumns(10);
		fieldCapacity.setBounds(240, 263, 217, 20);
		add(fieldCapacity);

		// Panel label, basically
		JLabel lblCreateCourse = new JLabel("Create Course");
		lblCreateCourse.setForeground(Color.GRAY);
		lblCreateCourse.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCreateCourse.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateCourse.setBounds(188, 56, 243, 21);
		add(lblCreateCourse);

		// Back button & implementation
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAdmin());
			}
		});
		btnNewButton.setBounds(10, 386, 128, 23);
		add(btnNewButton);

		// Create button TODO: add logic
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Not yet implemented");
			}
		});
		btnCreate.setBounds(240, 300, 217, 23);
		add(btnCreate);

		// Adds the login panel to this window
		JPanel loginPanel = new GUILoggedIn();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

	}

	/**
	 * Clears all of the text fields in the window
	 */
	public void clearFields()
	{
		fieldCRN.setText("");
		fieldCourseName.setText("");
		fieldSection.setText("");
		fieldCourseDesc.setText("");
		fieldDays.setText("");
		fieldTimes.setText("");
		fieldCapacity.setText("");
	}
}
