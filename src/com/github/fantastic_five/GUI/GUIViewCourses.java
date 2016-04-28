package com.github.fantastic_five.GUI;

/**
 * @author Fantastic Five (Alay Patel & Jose Stovall)
 * This GUI displays all of the available courses that our University offers. 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
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
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.Course;
import com.github.fantastic_five.Logic.Course.Day;
import com.github.fantastic_five.Logic.UserProfile;

@SuppressWarnings("serial")
public class GUIViewCourses extends JPanel
{
	private String[] headers = new String[] { "CRN", "Class", "Capacity", "Remaining", "Teacher", "Days", "Time" };

	public GUIViewCourses()
	{
		setBounds(0, 0, 618, 434);
		setLayout(null);

		// Main table scrollpane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 587, 311);
		add(scrollPane);

		// Adds a table including proper headers and table content
		JTable table = new JTable();
		table.setModel(new UneditableTableModel(getCourseTable(), headers));
		table.setAutoCreateRowSorter(true);
		table.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				// Checks for double-click event
				if (e.getClickCount() == 2)
				{
					Course selectedCourse = StudentRegistrationMain.mainCourseManager.getCourse((int) table.getModel().getValueAt(table.convertRowIndexToModel(table.getSelectedRow()), 0));

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

		scrollPane.setViewportView(table);

		JButton btnBack = new UniversalBackButton();
		btnBack.setBounds(10, 386, 128, 23);
		add(btnBack);

		// Adds a Label named View Courses.
		JLabel lblCourseRemoval = new JLabel("View Courses");
		lblCourseRemoval.setForeground(Color.GRAY);
		lblCourseRemoval.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCourseRemoval.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseRemoval.setBounds(179, 21, 243, 23);
		add(lblCourseRemoval);

		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(469, 386, 128, 23);
		btnPrint.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				MessageFormat header = new MessageFormat("Master Course List");
				MessageFormat footer = new MessageFormat("Guest");
				try
				{
					table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				}
				catch (PrinterException e1)
				{
				}
			}
		});
		add(btnPrint);

		// Adds the login status panel
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);
	}

	/**
	 * @return a two-dimensional object array for the table containing contents about ALL university courses
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
			cells[row][4] = teacher == null ? "TBA" : teacher.getFirstName().substring(0, 1) + ". " + teacher.getLastName();
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
	String getFormattedDays(TreeSet<Day> days)
	{
		String rVal = "";
		for (Day d : days)
			rVal += d.getAbbreviation() + " ";
		return rVal;

	}
}