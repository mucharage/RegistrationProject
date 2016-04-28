/**
 * @author Alay Patel (leader)
 * This is the first display that the Student user will see when he/she logs in to his/her account. Contains the main actions a student can do
 */

package com.github.fantastic_five.GUIStudent;

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
public class GUIStudent extends JPanel
{
	public GUIStudent()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		// Button & Logic for View Courses
		JButton btnView = new JButton("View Courses");
		btnView.setBounds(178, 186, 243, 23);
		btnView.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIViewCourses());
			}
		});
		add(btnView);

		// Button & Logic for Add or Remove Course
		JButton btnAddremoveCourse = new JButton("Register / Drop Course");
		btnAddremoveCourse.setBounds(178, 220, 243, 23);
		btnAddremoveCourse.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAddDropCourse());
			}
		});
		add(btnAddremoveCourse);

		// Button & Logic for for View Schedule
		JButton btnViewSchedule = new JButton("View Schedule");
		btnViewSchedule.setBounds(178, 254, 243, 23);
		btnViewSchedule.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIViewSchedule());
			}
		});
		add(btnViewSchedule);

		// Panel Label, essentially
		JLabel lblStudent = new JLabel("Student");
		lblStudent.setBounds(232, 78, 46, 14);
		lblStudent.setForeground(Color.GRAY);
		lblStudent.setFont(new Font("Verdana", Font.BOLD, 16));
		lblStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudent.setBounds(178, 96, 243, 23);
		add(lblStudent);

		// Adds the login status bar
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		if (StudentRegistrationMain.mainCourseManager.getCoursesWithLearner(StudentRegistrationMain.getCurrentLoggedInUser()).size() < 3)
		{
			JLabel lblClassReq = new JLabel(getErrorText());
			lblClassReq.setFont(new Font("Monospaced", Font.PLAIN, 12));
			lblClassReq.setHorizontalAlignment(SwingConstants.CENTER);
			lblClassReq.setForeground(Color.RED);
			lblClassReq.setBounds(10, 368, 598, 14);
			add(lblClassReq);
		}
	}

	/**
	 * @return a text error for when the student is not registered for enough classes.
	 */
	String getErrorText()
	{
		return (StudentRegistrationMain.mainCourseManager.getCoursesWithLearner(StudentRegistrationMain.getCurrentLoggedInUser()).size() < 3) ? "You are not registered for enough classes. (Minimum of Three)" : "";
	}
}