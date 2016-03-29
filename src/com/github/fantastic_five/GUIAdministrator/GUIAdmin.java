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
import com.github.fantastic_five.GUIMisc.GUIWIP;

@SuppressWarnings("serial")
public class GUIAdmin extends JPanel
{
	public GUIAdmin()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		// Button & Logic for View Courses
		JButton btnView = new JButton("View Courses");
		btnView.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIViewCourses());
			}
		});

		btnView.setBounds(178, 152, 243, 23);
		add(btnView);

		// Button & logic for adding classes
		JButton btnCreateCourseOffering = new JButton("Create Course Offering");
		btnCreateCourseOffering.setBounds(178, 186, 243, 23);
		btnCreateCourseOffering.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAddClass());
			}
		});
		add(btnCreateCourseOffering);

		// Button & logic for removing courses
		JButton btnRemoveCourseOffering = new JButton("Remove Course Offering");
		btnRemoveCourseOffering.setBounds(178, 220, 243, 23);
		btnRemoveCourseOffering.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIRemoveClass());
			}
		});
		add(btnRemoveCourseOffering);

		// Button & Logic for reports view
		JButton btnViewReport = new JButton("View Report");
		btnViewReport.setBounds(178, 254, 243, 23);
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
		btnViewEnrolledStudents.setBounds(178, 290, 243, 23);
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
		btnViewTeacher.setBounds(178, 326, 243, 23);
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
		lblAdministration.setBounds(178, 96, 243, 23);
		add(lblAdministration);

		// @TODO: Remove this later:
		JButton btnBackToWip = new JButton("Back to WIP Menu");
		btnBackToWip.setBounds(489, 400, 119, 23);
		btnBackToWip.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIWIP());
			}
		});
		add(btnBackToWip);
	}
}
