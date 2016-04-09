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
	private JLabel confirmation;

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

		// Example Labels
		JLabel lblExampleDays = new JLabel("(ex. M T W TR F S SU)");
		lblExampleDays.setForeground(Color.GRAY);
		lblExampleDays.setHorizontalAlignment(SwingConstants.CENTER);
		lblExampleDays.setBounds(467, 132, 107, 14);
		add(lblExampleDays);

		JLabel lblhrFormatEx = new JLabel("(24hr format, ex: 13:00)");
		lblhrFormatEx.setForeground(Color.GRAY);
		lblhrFormatEx.setBounds(467, 163, 141, 14);
		add(lblhrFormatEx);

		JLabel lblhrFormatEx2 = new JLabel("(24hr format, ex: 13:00)");
		lblhrFormatEx2.setForeground(Color.GRAY);
		lblhrFormatEx2.setBounds(467, 194, 141, 14);
		add(lblhrFormatEx2);

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
				parseFields();
			}
		});
		btnCreate.setBounds(240, 330, 217, 23);
		add(btnCreate);

		// Adds the confirmation area
		confirmation = new JLabel("");
		confirmation.setFont(new Font("Monospaced", Font.PLAIN, 32));
		confirmation.setHorizontalAlignment(SwingConstants.CENTER);
		confirmation.setBounds(240, 364, 217, 20);
		add(confirmation);

		// Adds the login panel to this window
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);
	}

	/**
	 * Takes all text fields and properly tries to create a course object from their contents, catching errors along the way
	 */
	void parseFields()
	{
		// Temporary Variables for creating the course object
		String title = fieldCourseName.getText();
		String description = fieldCourseDesc.getText();
		int CRN = MiscUtils.getCRN();
		int studentCap = -1;
		try
		{
			studentCap = Integer.parseInt(fieldCapacity.getText());
		}
		catch (NumberFormatException numberException)
		{
			displayError(this.fieldCapacity);
			return;
		}
		// Handles the starting time
		String[] startTimeParts = fieldTimeStart.getText().split(":");
		String[] endTimeParts = fieldTimeEnd.getText().split(":");

		if (startTimeParts.length != 2 || endTimeParts.length != 2)
		{
			displayError(this.fieldTimeStart);
			displayError(this.fieldTimeEnd);
			return;
		}

		int startHour = Integer.parseInt(startTimeParts[0]);
		int startMinute = Integer.parseInt(startTimeParts[1]);
		// Handles the ending time
		int endHour = Integer.parseInt(endTimeParts[0]);
		int endMinute = Integer.parseInt(endTimeParts[1]);
		// Handles the days of the week
		HashSet<Day> days = new HashSet<>();
		String[] dayParts = fieldDays.getText().split(" ");
		for (String s : dayParts)
		{
			if (Day.getDayFromAbbreviation(s) != null)
			{
				days.add(Day.getDayFromAbbreviation(s));
			}
			else
			{
				displayError(this.fieldDays);
				return;
			}
		}

		// Creates course and adds it to the course list
		Course c = new Course(title, description, CRN, studentCap, days, startHour, startMinute, endHour, endMinute);
		StudentRegistrationMain.mainCourseManager.addCourse(c);
		displaySuccess();

		// Resets the fields
		resetFields();
	}

	/**
	 * Sets the background of the passed text field to be red to alert the user, as well as a red text notifier
	 * 
	 * @param erroredField
	 *            The text field to set the background red of
	 */
	void displayError(JTextField erroredField)
	{
		resetFieldColors();
		erroredField.setBackground(Color.RED);
		confirmation.setText("\u2717");
		confirmation.setForeground(Color.RED);
		revalidate();
		repaint();
	}

	/**
	 * Shows a green notifier to the user that the class was added okay
	 */
	void displaySuccess()
	{
		confirmation.setText("\u2713");
		confirmation.setForeground(Color.GREEN);
		revalidate();
		repaint();
	}

	/**
	 * sets all field colors to white - separate because sometimes I need their colors reset but not their text
	 */
	void resetFieldColors()
	{
		fieldCourseName.setBackground(Color.WHITE);
		fieldDays.setBackground(Color.WHITE);
		fieldTimeStart.setBackground(Color.WHITE);
		fieldCapacity.setBackground(Color.WHITE);
		fieldTimeEnd.setBackground(Color.WHITE);
	}

	/**
	 * Clears all of the text fields in the window
	 */
	void resetFields()
	{
		fieldCourseName.setText("");
		fieldCourseDesc.setText("");
		fieldDays.setText("");
		fieldTimeStart.setText("");
		fieldTimeEnd.setText("");
		fieldCapacity.setText("");
		resetFieldColors();
	}
}
