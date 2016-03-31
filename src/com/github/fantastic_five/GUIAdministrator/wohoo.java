package com.github.fantastic_five.GUIAdministrator;

/**
 * @author Fantastic Five (Jose Stovall)
 * A JFrame containing the main actions an Administrator can take
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILoggedIn;
import com.github.fantastic_five.GUIMisc.GUIViewReport;

@SuppressWarnings("serial")
public class wohoo extends JPanel
{
	public wohoo()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		// Button & Logic for View Courses
		JButton btnView = new JButton("View Courses");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnView.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIViewCourses());
			}
		});

		btnView.setBounds(233, 180, 156, 23);
		add(btnView);

		// Button & logic for adding classes
		JButton btnCreateCourseOffering = new JButton("Add Course");
		btnCreateCourseOffering.setBounds(233, 214, 156, 23);
		btnCreateCourseOffering.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAddClass());
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
				StudentRegistrationMain.replaceMainWindowContents(new GUIRemoveClass());
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
				StudentRegistrationMain.replaceMainWindowContents(new GUIViewReport(new wohoo()));
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
		
		JButton btnNewButton = new JButton("Add Student");
		btnNewButton.setBounds(48, 214, 154, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remove Student");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(50, 248, 154, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("View Student Report");
		btnNewButton_2.setBounds(50, 282, 154, 23);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Add Teacher");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setBounds(415, 214, 156, 23);
		add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Remove Teacher");
		btnNewButton_4.setBounds(415, 248, 156, 23);
		add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("View Teacher Report");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_5.setBounds(415, 282, 156, 23);
		add(btnNewButton_5);
	}
}
