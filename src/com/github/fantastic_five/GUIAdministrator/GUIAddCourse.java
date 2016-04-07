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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.Course;
import com.github.fantastic_five.Logic.Course.Day;
import com.github.fantastic_five.Logic.MiscUtils;

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

	public GUIAddCourse()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		// Course Name
		JLabel lblCourseName = new JLabel("Course Name:");
		lblCourseName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseName.setHorizontalAlignment(SwingConstants.LEFT);
		lblCourseName.setBounds(102, 100, 111, 14);
		add(lblCourseName);

		fieldCourseName = new JTextField();
		fieldCourseName.setColumns(10);
		fieldCourseName.setBounds(240, 98, 217, 20);
		add(fieldCourseName);

		// Days Offered
		JLabel lblDaysOffered = new JLabel("Days Offered:");
		lblDaysOffered.setHorizontalAlignment(SwingConstants.LEFT);
		lblDaysOffered.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDaysOffered.setBounds(102, 131, 111, 14);
		add(lblDaysOffered);

		fieldDays = new JTextField();
		fieldDays.setColumns(10);
		fieldDays.setBounds(240, 129, 217, 20);
		add(fieldDays);

		// Time started
		JLabel lblTimeStart = new JLabel("Start Time:");
		lblTimeStart.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimeStart.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTimeStart.setBounds(102, 162, 111, 14);
		add(lblTimeStart);

		fieldTimeStart = new JTextField();
		fieldTimeStart.setColumns(10);
		fieldTimeStart.setBounds(240, 160, 217, 20);
		add(fieldTimeStart);

		// Time ended
		JLabel lblTimeEnd = new JLabel("End Time:");
		lblTimeEnd.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimeEnd.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTimeEnd.setBounds(102, 193, 111, 14);
		add(lblTimeEnd);

		fieldTimeEnd = new JTextField();
		fieldTimeEnd.setColumns(10);
		fieldTimeEnd.setBounds(240, 191, 217, 20);
		add(fieldTimeEnd);

		// Student Capacity
		JLabel lblStudentCapacity = new JLabel("Student Capacity:");
		lblStudentCapacity.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentCapacity.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStudentCapacity.setBounds(102, 224, 111, 14);
		add(lblStudentCapacity);

		fieldCapacity = new JTextField();
		fieldCapacity.setColumns(10);
		fieldCapacity.setBounds(240, 222, 217, 20);
		add(fieldCapacity);

		// Course Description
		JLabel lblCourseDesc = new JLabel("Course Description:");
		lblCourseDesc.setHorizontalAlignment(SwingConstants.LEFT);
		lblCourseDesc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCourseDesc.setBounds(102, 255, 128, 14);
		add(lblCourseDesc);

		fieldCourseDesc = new JTextArea();
		fieldCourseDesc.setLineWrap(true);
		fieldCourseDesc.setFont(new Font("Tahoma", Font.PLAIN, 11));
		fieldCourseDesc.setColumns(10);
		fieldCourseDesc.setBounds(240, 253, 217, 66);
		fieldCourseDesc.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		add(fieldCourseDesc);

		// Panel Label
		JLabel lblCreateCourse = new JLabel("Add Course");
		lblCreateCourse.setForeground(Color.GRAY);
		lblCreateCourse.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCreateCourse.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateCourse.setBounds(214, 44, 243, 21);
		add(lblCreateCourse);

		// Back button & implementation
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAdmin());
			}
		});
		btnBack.setBounds(10, 386, 128, 23);
		add(btnBack);

		// Button with logic for creation
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Temporary Variables for creating the course object
				String title = fieldCourseName.getText();
				String description = fieldCourseDesc.getText();
				int CRN = MiscUtils.getCRN();
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
				StudentRegistrationMain.mainCourseManager.addCourse(c);

				// Resets the fields
				clearFields();
			}
		});
		btnCreate.setBounds(240, 330, 217, 23);
		add(btnCreate);

		// Adds the login panel to this window
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);
	}

	/**
	 * Clears all of the text fields in the window
	 */
	void clearFields()
	{
		fieldCourseName.setText("");
		fieldCourseDesc.setText("");
		fieldDays.setText("");
		fieldTimeStart.setText("");
		fieldTimeEnd.setText("");
		fieldCapacity.setText("");
	}
}
