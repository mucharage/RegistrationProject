package com.github.fantastic_five.GUIAdministrator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.Set;
import java.util.function.Predicate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUI.UneditableTableModel;
import com.github.fantastic_five.GUI.UniversalBackButton;
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.Course;
import com.github.fantastic_five.Logic.UserProfile;

@SuppressWarnings("serial")
public class GUIViewTeacherReport extends JPanel
{
	private JTable table;
	private String[] headers = new String[] { "Last", "First", "UserID", "Available", "TA", "CRNs" };

	public GUIViewTeacherReport()
	{
		setBounds(0, 0, 618, 434);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 587, 311);
		add(scrollPane);

		table = new JTable();
		table.setModel(new UneditableTableModel(getTable(), headers));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(75);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(25);
		table.getColumnModel().getColumn(5).setPreferredWidth(225);
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);

		// Adds the login panel
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		JButton btnBack = new UniversalBackButton();
		btnBack.setBounds(10, 386, 128, 23);
		add(btnBack);

		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(498, 386, 99, 23);
		btnPrint.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MessageFormat header = new MessageFormat("Master Teacher Report");

				String name = StudentRegistrationMain.getCurrentLoggedInUser().getFirstName() + " " + StudentRegistrationMain.getCurrentLoggedInUser().getLastName();
				String userID = StudentRegistrationMain.getCurrentLoggedInUser().getUserID();
				MessageFormat footer = new MessageFormat("Name: " + name + "                                                                User ID: " + userID);
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

		// Panel label, essentially
		JLabel lblAdministration = new JLabel("View Teacher Report");
		lblAdministration.setForeground(Color.GRAY);
		lblAdministration.setFont(new Font("Verdana", Font.BOLD, 16));
		lblAdministration.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministration.setBounds(191, 37, 243, 23);
		add(lblAdministration);
	}

	/**
	 * @return a two-dimensional object array containing information about teachers
	 */
	Object[][] getTable()
	{
		// Some local variables that help me later. Wastes memory, maybe - but saves typing a lot
		Set<UserProfile> users = StudentRegistrationMain.profiles.copyUserProfiles();
		users.removeIf(new Predicate<UserProfile>()
		{
			@Override
			public boolean test(UserProfile t)
			{
				// Returns true (i.e. removes if:) permLevel DOESN'T match these criteria:
				return ((t.getPermLevel() != UserProfile.TEACHER && t.getPermLevel() != UserProfile.TA));
			}
		});

		Object[][] cells = new Object[users.size()][headers.length];

		int row = 0;
		// Loops through all courses and sets the columns in each row appropriately
		for (UserProfile u : users)
		{
			cells[row][0] = u.getLastName();
			cells[row][1] = u.getFirstName();
			cells[row][2] = u.getUserID();
			cells[row][3] = getAvailibilityUnicode(u);
			cells[row][4] = u.getPermLevel() == UserProfile.TA ? "\u2713" : "\u2717";
			cells[row][5] = getCRNS(u);

			row++;
		}

		return cells;
	}

	/**
	 * @param profile
	 *            the user profile to check for availability
	 * @return a unicode String determined by the user's availability
	 */
	String getAvailibilityUnicode(UserProfile profile)
	{
		int numCourses = 0;
		if (profile != null)
			numCourses = StudentRegistrationMain.mainCourseManager.getCoursesWithInstructor(profile).size();

		return numCourses >= 5 ? "\u2717" : "\u2713";
	}

	/**
	 * @param profile
	 *            the profile to populate the String for
	 * @return a formatted string of all CRNs the teacher is enrolled in
	 */
	String getCRNS(UserProfile profile)
	{
		String rVal = "";
		Set<Course> courses = StudentRegistrationMain.mainCourseManager.getCoursesWithInstructor(profile);

		for (Course c : courses)
			rVal += c.getCRN() + ", ";

		return rVal.length() > 0 ? rVal.substring(0, rVal.length() - 2) : "";
	}
}