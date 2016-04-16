package com.github.fantastic_five.GUITeacher;

/**
 * @author Christian Phillips
 * Group 5 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.Course;
import com.github.fantastic_five.Logic.MiscUtils;
import com.github.fantastic_five.Logic.UserProfile;

@SuppressWarnings("serial")
public class GUIViewCourse extends JPanel
{
	// Private instance variables
	private JTable table;

	// This GUI shall display all the available courses that our University offers.
	public GUIViewCourse()
	{
		setBounds(0, 0, 618, 434);
		setLayout(null);

		// Adds a ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 587, 311);
		add(scrollPane);

		// Adds a table displaying important details for each courses.
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(getCourseTable(), new String[] { "CRN", "Class", "Capacity", "Remaining", "Teacher", "Days", "Time" })
		{
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		});
		scrollPane.setViewportView(table);

		// Button & Logic for View Schedule
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUITeacher());
			}
		});
		btnBack.setBounds(10, 386, 128, 23);
		add(btnBack);

		// Adds a lgin panel
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// Adds a label "View Courses"
		JLabel lblCourseRemoval = new JLabel("View Courses");
		lblCourseRemoval.setForeground(Color.GRAY);
		lblCourseRemoval.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCourseRemoval.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseRemoval.setBounds(179, 21, 243, 23);
		add(lblCourseRemoval);

	}// end of GuiViewCourses()

	/**
	 * @return a two-dimensional object array for the table with properly pre-filled info
	 */
	public Object[][] getCourseTable()
	{
		// Some local variables that help me later. Wastes memory, maybe - but saves typing a lot
		TreeSet<Course> courseOfferings = StudentRegistrationMain.mainCourseManager.copyCourseOfferings();
		int numCourses = courseOfferings.size();
		Object[][] cells = new Object[numCourses][7];

		int row = 0;
		// Loops through all courses and sets the columns in each row appropriately
		for (Course c : courseOfferings)
		{
			UserProfile teacher = StudentRegistrationMain.mainCourseManager.getInstructorWithCourse(c.getCRN());
			cells[row][0] = c.getCRN();
			cells[row][1] = c.getTitle();
			cells[row][2] = c.getStudentCap();
			cells[row][3] = c.getRemainingCap();
			if (teacher != null)
				cells[row][4] = teacher.getFirstName().substring(0, 1) + " " + teacher.getLastName();
			cells[row][5] = c.getDays();
			cells[row][6] = c.getStartTime(Course.TWENTYFOUR_HR_CLOCK) + "-" + c.getEndTime(Course.TWENTYFOUR_HR_CLOCK);
			row++;
		}
		return cells;
	}
}