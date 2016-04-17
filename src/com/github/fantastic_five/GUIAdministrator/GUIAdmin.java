package com.github.fantastic_five.GUIAdministrator;

/**
 * @author Fantastic Five (Jose Stovall)
 * A JFrame containing the main actions an Administrator can take
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILogStatus;

@SuppressWarnings("serial")
public class GUIAdmin extends JPanel
{
	public GUIAdmin()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		// Button & Logic for View Courses
		JButton btnView = new JButton("View Courses");
		btnView.setBounds(335, 248, 154, 23);
		btnView.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIViewCourses());
			}
		});
		add(btnView);

		// Button & logic for adding classes
		JButton btnCreateCourseOffering = new JButton("Add Course");
		btnCreateCourseOffering.setBounds(335, 180, 154, 23);
		btnCreateCourseOffering.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAddCourse());
			}
		});
		add(btnCreateCourseOffering);

		// Button & logic for removing courses
		JButton btnRemoveCourse = new JButton("Remove Course");
		btnRemoveCourse.setBounds(335, 214, 154, 23);
		btnRemoveCourse.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIRemoveCourse());
			}
		});
		add(btnRemoveCourse);

		// Button & Logic for Student Addition
		JButton btnAddStudent = new JButton("Add User");
		btnAddStudent.setBounds(150, 180, 154, 23);
		btnAddStudent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAddUser());
			}
		});
		add(btnAddStudent);

		// Button & Logic for Student Report
		JButton btnViewStudents = new JButton("View Student Report");
		btnViewStudents.setBounds(150, 214, 154, 23);
		btnViewStudents.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIViewStudentReport());
			}
		});
		add(btnViewStudents);

		// Button & Logic for Teacher Report
		JButton btnViewTeachers = new JButton("View Teacher Report");
		btnViewTeachers.setBounds(150, 248, 154, 23);
		btnViewTeachers.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIViewTeacherReport());
			}
		});
		add(btnViewTeachers);

		// Adds the login panel
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// Panel label, essentially
		JLabel lblAdministration = new JLabel("Administration");
		lblAdministration.setForeground(Color.GRAY);
		lblAdministration.setFont(new Font("Verdana", Font.BOLD, 16));
		lblAdministration.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministration.setBounds(0, 83, 618, 23);
		add(lblAdministration);
	}

	// TODO list below:
	/**
	 * - View Student Report - View Teacher Report - View Enrolled Students needs a way to see if they've paid - View Enrolled Teachers - needs to find availability, and is the "Courses" heading req'd?
	 */
}