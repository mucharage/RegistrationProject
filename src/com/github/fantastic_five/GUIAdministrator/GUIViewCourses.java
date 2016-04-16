package com.github.fantastic_five.GUIAdministrator;

/**
 * @author Fantastic Five (Alay Patel & Jose Stovall)
 * This GUI displays all of the available courses that our University offers. 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
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
public class GUIViewCourses extends JPanel
{
	public GUIViewCourses()
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
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAdmin());
			}
		});
		btnBack.setBounds(10, 386, 128, 23);
		add(btnBack);

		// Adds a login Panel

		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// Adds a Label named View Courses.
		JLabel lblCourseRemoval = new JLabel("View Courses");
		lblCourseRemoval.setForeground(Color.GRAY);
		lblCourseRemoval.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCourseRemoval.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseRemoval.setBounds(180, 30, 243, 23);
		add(lblCourseRemoval);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(469, 386, 128, 23);
		btnPrint.addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent e)
			{			
				MessageFormat header = new MessageFormat ("Master Course List");				
				String name = MiscUtils.getCurrentLoggedInUser().getFirstName()+ " " + MiscUtils.getCurrentLoggedInUser().getLastName();
				String userID = MiscUtils.getCurrentLoggedInUser().getUserID();	
				MessageFormat footer = new MessageFormat("Name: "  + name + "                                                                User ID: " + userID);						
				try
				{
					table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				}
				catch (PrinterException e1)
				{					
					e1.printStackTrace();
				}
			}
		});
		add(btnPrint);
	}

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
			cells[row][4] = teacher == null ? "TBA" : teacher.getFirstName().substring(1) + ". " + teacher.getLastName();
			cells[row][5] = c.getDays();
			cells[row][6] = c.getStartTime(Course.TWENTYFOUR_HR_CLOCK) + "-" + c.getEndTime(Course.TWENTYFOUR_HR_CLOCK);
			row++;
		}

		return cells;
	}
}