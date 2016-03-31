package com.github.fantastic_five.GUIAdministrator;

/**
 * @author Fantastic Five (Jose Stovall)
 * A JPanel containing all fields required to create a class
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILoggedIn;
import com.github.fantastic_five.Logic.Course;
import com.github.fantastic_five.Logic.Course.Day;
import com.github.fantastic_five.Logic.CourseLib;

@SuppressWarnings("serial")
public class GUIAddCourse extends JPanel
{
	// Private instance variables
	private JTextField fieldCourseName;
	private JTextArea fieldCourseDesc;
	private JTextField fieldDays;
	private JTextField fieldTimeStart;
	private JTextField fieldCapacity;
	private JTextField fieldTimeEnd;

	public GUIAddClass()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		JLabel lblCourseName = new JLabel("Course Name:");
		lblCourseName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCourseName.setBounds(119, 90, 111, 14);
		add(lblCourseName);

		JLabel lblCourseDesc = new JLabel("Course Desc:");
		lblCourseDesc.setHorizontalAlignment(SwingConstants.LEFT);
		lblCourseDesc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseDesc.setBounds(119, 221, 111, 14);
		add(lblCourseDesc);

		JLabel lblDaysOffered = new JLabel("Days Offered:");
		lblDaysOffered.setHorizontalAlignment(SwingConstants.LEFT);
		lblDaysOffered.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDaysOffered.setBounds(119, 117, 111, 14);
		add(lblDaysOffered);

		JLabel lblTimeStart = new JLabel("Start Time:");
		lblTimeStart.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimeStart.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTimeStart.setBounds(119, 144, 111, 14);
		add(lblTimeStart);
		
		JLabel lblTimeEnd = new JLabel("End Time:");
		lblTimeEnd.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimeEnd.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTimeEnd.setBounds(119, 171, 111, 14);
		add(lblTimeEnd);

		JLabel lblStudentCapacity = new JLabel("Student Capacity:");
		lblStudentCapacity.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentCapacity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStudentCapacity.setBounds(119, 196, 111, 14);
		add(lblStudentCapacity);

		// All modifiable text fields and their positions:
		fieldCourseName = new JTextField();
		fieldCourseName.setColumns(10);
		fieldCourseName.setBounds(240, 88, 217, 20);
		add(fieldCourseName);

		fieldCourseDesc = new JTextArea();
		fieldCourseDesc.setLineWrap(true);
		fieldCourseDesc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fieldCourseDesc.setColumns(10);
		fieldCourseDesc.setBounds(240, 223, 217, 66);
		add(fieldCourseDesc);

		fieldDays = new JTextField();
		fieldDays.setColumns(10);
		fieldDays.setBounds(240, 115, 217, 20);
		add(fieldDays);

		fieldTimeStart = new JTextField();
		fieldTimeStart.setColumns(10);
		fieldTimeStart.setBounds(240, 142, 217, 20);
		add(fieldTimeStart);
		
		fieldTimeEnd = new JTextField();
		fieldTimeEnd.setColumns(10);
		fieldTimeEnd.setBounds(240, 169, 217, 20);
		add(fieldTimeEnd);

		fieldCapacity = new JTextField();
		fieldCapacity.setColumns(10);
		fieldCapacity.setBounds(240, 194, 217, 20);
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

		// Button with logic for creation
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Temporary Variables for creating the course object
				String title = fieldCourseName.getText();
				String description = fieldCourseDesc.getText();
				int CRN = getCRN();
				int studentCap = Integer.parseInt(fieldCapacity.getText());
				HashSet<Day> days = new HashSet<>();
				String[] startTimeParts = fieldTimeStart.getText().split("[\\W]");
				int startHour = Integer.parseInt(startTimeParts[0]);
				int startMinute = Integer.parseInt(startTimeParts[1]);
				String[] endTimeParts = fieldTimeEnd.getText().split("[\\W]");
				int endHour = Integer.parseInt(endTimeParts[0]);
				int endMinute = Integer.parseInt(endTimeParts[1]);

				String[] dayParts = fieldDays.getText().split(" ");
				for (String s : dayParts)
					days.add(Day.getDayFromAbbreviation(s));

				// Creates course and adds it to the course list
				Course c = new Course(title, description, CRN, studentCap, days, startHour, startMinute, endHour, endMinute);
				CourseLib.addCourseToCourseList(c);

				// Resets the fields
				clearFields();
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
		fieldCourseName.setText("");
		fieldCourseDesc.setText("");
		fieldDays.setText("");
		fieldTimeStart.setText("");
		fieldTimeEnd.setText("");
		fieldCapacity.setText("");
	}

	/**
	 * @return A random, non-conflicting CRN from 1000 - 9999
	 */
	int getCRN()
	{
		Random rand = new Random();
		// Gets a value between 0 and 8999 (inclusive) then adds 1000
		int ret = rand.nextInt(9000) + 1000;
		// Recursive call to get a CRN that is available
		if (!CourseLib.doesCRNExist(ret))
			return ret;
		else
			return getCRN();
	}
}
