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
import com.github.fantastic_five.GUIMisc.GUILoggedIn;

@SuppressWarnings("serial")
public class GUIAdmin extends JPanel
{
	public GUIAdmin()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		// Button & Logic for View Courses
		JButton btnView = new JButton("View Courses");
		btnView.setBounds(233, 180, 156, 23);
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
		btnCreateCourseOffering.setBounds(233, 214, 156, 23);
		btnCreateCourseOffering.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAddCourse());
			}
		});
		add(btnCreateCourseOffering);

		// Button & logic for removing courses
		JButton btnRemoveCourseOffering = new JButton("Remove Course");
		btnRemoveCourseOffering.setBounds(233, 248, 156, 23);
		btnRemoveCourseOffering.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIRemoveCourse());
			}
		});
		add(btnRemoveCourseOffering);

		// Button & Logic for reports view
		JButton btnViewReport = new JButton("View Course Report");
		btnViewReport.setBounds(233, 282, 156, 23);
		btnViewReport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIViewReport(new GUIAdmin()));
			}
		});
		add(btnViewReport);

		// Button & Logic for Enrolled Student View
		JButton btnViewEnrolledStudents = new JButton("View Enrolled Students");
		btnViewEnrolledStudents.setBounds(48, 180, 156, 23);
		btnViewEnrolledStudents.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIViewStudent());
			}
		});
		add(btnViewEnrolledStudents);

		// Button & Logic for Teacher Staff View
		JButton btnViewTeacher = new JButton("View Teacher Staff");
		btnViewTeacher.setBounds(415, 180, 156, 23);
		btnViewTeacher.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIViewTeacher());
			}
		});
		add(btnViewTeacher);

		// Button & Logic for Student Addition
		JButton btnAddStudent = new JButton("Add Student");
		btnAddStudent.setBounds(48, 214, 154, 23);
		btnAddStudent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAddStudent());
			}
		});
		add(btnAddStudent);

		// Button & Logic for Student Removal
		JButton btnRemoveStudent = new JButton("Remove Student");
		btnRemoveStudent.setBounds(50, 248, 154, 23);
		btnRemoveStudent.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIRemoveStudent());
			}
		});
		add(btnRemoveStudent);

		// Button & Logic for Student Report
		JButton btnViewStudents = new JButton("View Student Report");
		btnViewStudents.setBounds(50, 282, 154, 23);
		btnViewStudents.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		});
		add(btnViewStudents);

		// Button & Logic for Teacher Addition
		JButton btnAddTeacher = new JButton("Add Teacher");
		btnAddTeacher.setBounds(415, 214, 156, 23);
		btnAddTeacher.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAddTeacher());
			}
		});
		add(btnAddTeacher);

		// Button & Logic for Teacher Removal
		JButton btnRemoveTeacher = new JButton("Remove Teacher");
		btnRemoveTeacher.setBounds(415, 248, 156, 23);
		btnRemoveTeacher.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIRemoveTeacher());
			}
		});
		add(btnRemoveTeacher);

		// Button & Logic for Teacher Report
		JButton btnViewTeachers = new JButton("View Teacher Report");
		btnViewTeachers.setBounds(415, 282, 156, 23);
		btnViewTeachers.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			}
		});
		add(btnViewTeachers);

		// Adds the login panel
		JPanel loginPanel = new GUILoggedIn();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// Panel label, essentially
		JLabel lblAdministration = new JLabel("Administration");
		lblAdministration.setForeground(Color.GRAY);
		lblAdministration.setFont(new Font("Verdana", Font.BOLD, 16));
		lblAdministration.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministration.setBounds(191, 97, 243, 23);
		add(lblAdministration);

	}
}
