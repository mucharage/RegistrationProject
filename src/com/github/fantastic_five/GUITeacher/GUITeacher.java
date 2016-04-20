package com.github.fantastic_five.GUITeacher;

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
import javax.swing.SwingConstants;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUI.GUIViewCourses;
import com.github.fantastic_five.GUIMisc.GUILogStatus;

@SuppressWarnings("serial")
public class GUITeacher extends JPanel
{
	/**
	 * The GUITeacher class extends the JPanel. This is the first display that the Teacher user will see when he/she logs in to his/her account.
	 * 
	 * @return A window Containing the main actions a Teacher can do
	 */
	public GUITeacher()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		// Button & Logic for View Course
		JButton btnView = new JButton("View Courses");
		btnView.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIViewCourses());
			}
		});
		
		btnView.setBounds(178, 186, 243, 23);
		add(btnView);

		// Button & logic for removing courses
		JButton btnAddRemoveCourse = new JButton("Add / Remove Courses");
		btnAddRemoveCourse.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAddRemoveClass());
			}
		});
		btnAddRemoveCourse.setBounds(178, 220, 243, 23);
		add(btnAddRemoveCourse);

		// Button for viewing schedules
		JButton btnViewSchedule = new JButton("View Schedule");
		btnViewSchedule.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIViewSchedule());
			}
		});
		btnViewSchedule.setBounds(178, 254, 243, 23);
		add(btnViewSchedule);
		
		// Button & logic for Add Course & Remove Course buttons
		JButton btnAddCourse = new JButton("Add Courses");
		btnAddCourse.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAddCourse());
			}
		});
		btnAddCourse.setBounds(178, 284, 243, 23);
		add(btnAddCourse);
		
		JButton btnRemoveCourse = new JButton("Remove Courses");
		btnRemoveCourse.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIRemoveCourse());
			}
		});
		btnRemoveCourse.setBounds(178, 314, 243, 23);
		add(btnRemoveCourse);
		
		
		// Button & logic for viewing students currently enrolled in classes you are teaching
		JButton btnViewStudents = new JButton("View Students");
		btnViewStudents.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO: add setBounds & button; make GUIViewStudents class
				//StudentRegistrationMain.replaceMainWindowContents(new GUIViewStudents());;
			}
		});
		
		
		// Panel label
		JLabel lblTeacher = new JLabel("Teacher");
		lblTeacher.setBounds(232, 78, 46, 14);
		lblTeacher.setForeground(Color.GRAY);
		lblTeacher.setFont(new Font("Verdana", Font.BOLD, 16));
		lblTeacher.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeacher.setBounds(178, 96, 243, 23);
		add(lblTeacher);
		
		// Adds the login panel
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);
	}
}