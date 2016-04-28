package com.github.fantastic_five.GUITeacher;

/**
 * @author Christian Phillips
 * This GUI that shall display teacher's individual schedule of courses he/she is teaching 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUI.UneditableTableModel;
import com.github.fantastic_five.GUI.UniversalBackButton;
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.Course;
import com.github.fantastic_five.Logic.Course.Day;
import com.github.fantastic_five.Logic.UserProfile;

@SuppressWarnings("serial")
public class GUIViewSchedule extends JPanel
{
	private String[] headers = new String[] { "CRN", "Class", "Capacity", "Remaining", "Teacher", "Day", "Time" };

	public GUIViewSchedule()
	{
		setBounds(0, 0, 618, 434);
		setLayout(null);

		// Adds a ScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 119, 587, 106);
		add(scrollPane);

		// Adds a table which will display list of courses that user have chose
		JTable addedTable = new JTable();
		addedTable.setModel(new UneditableTableModel(getClassTable(), headers));
		addedTable.setAutoCreateRowSorter(true);
		addedTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					Course selectedCourse = StudentRegistrationMain.mainCourseManager.getCourse((int) addedTable.getModel().getValueAt(addedTable.convertRowIndexToModel(addedTable.getSelectedRow()), 0));

					JDialog popup = new JDialog(StudentRegistrationMain.mainWindow, selectedCourse.getTitle() + " - Description");
					popup.setBounds(200, 200, 447, 147);
					popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					popup.setLocationRelativeTo(StudentRegistrationMain.mainWindow);
					popup.setResizable(false);
					popup.setVisible(true);
					popup.setAlwaysOnTop(true);

					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(10, 11, 421, 96);
					popup.getContentPane().add(scrollPane);

					JTextArea desc = new JTextArea();
					desc.setText(selectedCourse.getDescription());
					desc.setWrapStyleWord(true);
					desc.setLineWrap(true);
					desc.setFont(new Font("Verdana", Font.PLAIN, 12));
					desc.setBounds(10, 11, 421, 96);
					desc.setEditable(false);
					scrollPane.setViewportView(desc);
				}
			}
		});
		scrollPane.setViewportView(addedTable);

		// Button & logic for back button
		JButton btnBack = new UniversalBackButton();
		btnBack.setBounds(10, 386, 128, 23);
		add(btnBack);

		// Button and logic for printing functionality
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(469, 386, 128, 23);
		btnPrint.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MessageFormat header = new MessageFormat("Schedule");
				String name = StudentRegistrationMain.getCurrentLoggedInUser().getFirstName() + " " + StudentRegistrationMain.getCurrentLoggedInUser().getLastName();
				String userID = StudentRegistrationMain.getCurrentLoggedInUser().getUserID();
				MessageFormat footer = new MessageFormat("Name: " + name + "                                                                User ID: " + userID);
				try
				{
					addedTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				}
				catch (PrinterException e1)
				{
				}
			}
		});
		add(btnPrint);

		// Adds login status bar
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// Adds a JLabel named "View Schedule"
		JLabel lblViewSchedule = new JLabel("View Schedule");
		lblViewSchedule.setForeground(Color.GRAY);
		lblViewSchedule.setFont(new Font("Verdana", Font.BOLD, 16));
		lblViewSchedule.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewSchedule.setBounds(10, 54, 587, 23);
		add(lblViewSchedule);
	}

	/**
	 * @return a two-dimensional object array for the table containing all classes taught by the instructor
	 */
	Object[][] getClassTable()
	{
		Set<Course> taughtCourses = StudentRegistrationMain.mainCourseManager.getCoursesWithInstructor(StudentRegistrationMain.getCurrentLoggedInUser());
		Object[][] cells = new Object[taughtCourses.size()][7];
		int row = 0;
		for (Course c : taughtCourses)
		{
			UserProfile teacher = StudentRegistrationMain.mainCourseManager.getInstructorWithCourse(c.getCRN());

			cells[row][0] = c.getCRN();
			cells[row][1] = c.getTitle();
			cells[row][2] = c.getStudentCap();
			cells[row][3] = c.getRemainingCap();
			cells[row][4] = teacher == null ? "TBA" : teacher.getFirstName().substring(0, 1) + " " + teacher.getLastName();
			cells[row][5] = getFormattedDays(c.getDays());
			cells[row][6] = c.getStartTime(Course.TWENTYFOUR_HR_CLOCK) + "-" + c.getEndTime(Course.TWENTYFOUR_HR_CLOCK);
			row++;
		}
		return cells;
	}

	/**
	 * @param days
	 *            a TreeSet of days which needs to be formatted
	 * @return a formatted string with the day abbreviations from the TreeSet
	 */
	static String getFormattedDays(TreeSet<Day> days)
	{
		String rVal = "";
		for (Day d : days)
			rVal += d.getAbbreviation() + " ";
		return rVal;
	}
}
