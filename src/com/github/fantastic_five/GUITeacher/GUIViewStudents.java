package com.github.fantastic_five.GUITeacher;

/**
 * @author Christian Phillips
 * Group 5 
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
public class GUIViewStudents extends JPanel
{
	private String[] classHeaders = new String[] { "CRN", "Class", "Capacity", "Remaining", "Day", "Time" };
	private String[] studentHeaders = new String[] { "Last", "First", "UserID", "Paid" };
	private static JTable classTable;
	private JTable studentTable;

	/**
	 * This GUI that shall display teacher's individual schedule of c31rses he/she is teaching
	 */
	public GUIViewStudents()
	{
		/**
		 * Adds a ScrollPane
		 */
		setBounds(0, 0, 618, 434);
		setLayout(null);

		JScrollPane classScrollPane = new JScrollPane();
		classScrollPane.setBounds(10, 62, 598, 106);
		add(classScrollPane);

		JScrollPane studentScrollPane = new JScrollPane();
		studentScrollPane.setBounds(10, 203, 598, 172);
		add(studentScrollPane);

		/**
		 * Adds a table which will display list of courses that the user chose
		 */
		classTable = new JTable();
		classTable.setModel(new UneditableTableModel(getClassTable(), classHeaders));
		classScrollPane.setViewportView(classTable);
		classTable.setAutoCreateRowSorter(true);

		studentTable = new JTable();
		studentTable.setModel(new UneditableTableModel(null, studentHeaders));
		studentScrollPane.setViewportView(studentTable);
		studentTable.setAutoCreateRowSorter(true);

		/**
		 * Button & logic for back button
		 */
		JButton btnBack = new UniversalBackButton();
		btnBack.setBounds(10, 386, 128, 23);
		add(btnBack);

		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(480, 386, 128, 23);
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
					classTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				}
				catch (PrinterException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		add(btnPrint);

		/**
		 * Adds a login GUI
		 */
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		/**
		 * Adds a JLabel named "View Students"
		 */
		JLabel lblViewSchedule = new JLabel("View Students");
		lblViewSchedule.setBounds(10, 175, 598, 23);
		lblViewSchedule.setForeground(Color.GRAY);
		lblViewSchedule.setFont(new Font("Verdana", Font.BOLD, 16));
		lblViewSchedule.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblViewSchedule);

		JLabel lblClasses = new JLabel("Classes");
		lblClasses.setHorizontalAlignment(SwingConstants.CENTER);
		lblClasses.setForeground(Color.GRAY);
		lblClasses.setFont(new Font("Verdana", Font.BOLD, 16));
		lblClasses.setBounds(10, 32, 598, 23);
		add(lblClasses);

		/**
		 * Displays currently enrolled students by double clicking selected course
		 */
		classTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					int CRN = (int) classTable.getModel().getValueAt(classTable.convertRowIndexToModel(classTable.getSelectedRow()), 0);

					Course selectedCourse = StudentRegistrationMain.mainCourseManager.getCourse(CRN);
					Set<UserProfile> students = StudentRegistrationMain.mainCourseManager.getLearnersWithCourse(CRN);

					JDialog popup = new JDialog(StudentRegistrationMain.mainWindow, selectedCourse.getTitle() + " - Students");
					popup.setBounds(200, 200, 447, 147);
					popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					popup.setLocationRelativeTo(StudentRegistrationMain.mainWindow);
					popup.setResizable(false);
					popup.setVisible(true);
					popup.setAlwaysOnTop(true);

					JTextArea desc = new JTextArea();
					String x = "";
					for (UserProfile student : students)
					{
						x += student.getFirstName() + " " + student.getLastName() + " - " + student.getUserID() + "\n";
					}

					// JTextArea desc = new JTextArea();
					desc.setText(selectedCourse.getDescription());
					desc.setText(x);
					desc.setWrapStyleWord(true);
					desc.setLineWrap(true);
					desc.setFont(new Font("Verdana", Font.PLAIN, 12));
					desc.setBounds(10, 11, 421, 96);
					desc.setEditable(false);

					JScrollPane scrollPane = new JScrollPane(desc);
					scrollPane.setBounds(10, 11, 421, 96);
					popup.getContentPane().add(scrollPane);
				}
				// Single click checking!
				else
				{
					int CRN = (int) classTable.getModel().getValueAt(classTable.convertRowIndexToModel(classTable.getSelectedRow()), 0);
					studentTable.setModel(new UneditableTableModel(getStudentTable(CRN), studentHeaders));
				}
			}
		});
	}

	public Object[][] getClassTable()
	{
		Set<Course> courses = StudentRegistrationMain.mainCourseManager.getCoursesWithInstructor(StudentRegistrationMain.getCurrentLoggedInUser());
		Object[][] cells = new Object[courses.size()][7];

		int row = 0;
		for (Course c : courses)
		{
			cells[row][0] = c.getCRN();
			cells[row][1] = c.getTitle();
			cells[row][2] = c.getStudentCap();
			cells[row][3] = c.getRemainingCap();
			cells[row][4] = getFormattedDays(c.getDays());
			cells[row][5] = c.getStartTime(Course.TWENTYFOUR_HR_CLOCK) + "-" + c.getEndTime(Course.TWENTYFOUR_HR_CLOCK);
			row++;
		}
		return cells;
	}

	private Object[][] getStudentTable(int CRN)
	{
		Set<UserProfile> students = StudentRegistrationMain.mainCourseManager.getLearnersWithCourse(CRN);
		Object[][] cells = new Object[students.size()][studentHeaders.length];

		int row = 0;
		for (UserProfile u : students)
		{
			cells[row][0] = u.getLastName();
			cells[row][1] = u.getFirstName();
			cells[row][2] = u.getUserID();
			cells[row][3] = getPaymentUnicode(StudentRegistrationMain.financialRecords.userHasCharges(u.getUserID()));
			row++;
		}
		return cells;
	}

	/**
	 * @param days
	 *            a TreeSet of days which needs to be formatted
	 * @return a formatted string with the day abbreviations from the TreeSet
	 */
	String getFormattedDays(TreeSet<Day> days)
	{
		String rVal = "";
		for (Day d : days)
			rVal += d.getAbbreviation() + " ";
		return rVal;
	}

	/**
	 * @param hasDues
	 *            whether or not the Student owes still
	 * @return a formatted Unicode String based on dues
	 */
	String getPaymentUnicode(boolean hasDues)
	{
		return hasDues ? "   \u2713" : "   \u2717";
	}
}
