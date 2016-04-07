package com.github.fantastic_five.GUIAdministrator;

/**
 * @author Fantastic Five (Jose Stovall)
 * A JPanel displaying all teachers in the university
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.UserProfile;
import com.github.fantastic_five.Logic.UserProfileDatabase;

@SuppressWarnings("serial")
public class GUIViewTeachers extends JPanel
{
	// Private instance variable
	private JTable table;

	public GUIViewTeachers()
	{
		setBounds(0, 0, 618, 434);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 587, 311);
		add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(getTable(), new String[] { "Last", "First", "Courses", "Avail." }));
		scrollPane.setViewportView(table);

		// Back button to return to previous GUI
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAdmin());				
			}
		});
		btnBack.setBounds(10, 386, 128, 23);
		add(btnBack);

		// Adds the log in panel
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// Panel label
		JLabel lblCourseRemoval = new JLabel("View Teacher Staff");
		lblCourseRemoval.setForeground(Color.GRAY);
		lblCourseRemoval.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCourseRemoval.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseRemoval.setBounds(179, 21, 243, 23);
		add(lblCourseRemoval);
	}

	/**
	 * @return a two-dimensional object array for the table with properly pre-filled info
	 */
	public Object[][] getTable()
	{
		// Some local variables that help me later. Wastes memory, maybe - but saves typing a lot
		ArrayList<UserProfile> allUsers = UserProfileDatabase.users;
		Object[][] cells = new Object[allUsers.size()][7];

		int row = 0;
		// Loops through all courses and sets the columns in each row appropriately
		for (UserProfile u : allUsers)
		{
			if (u.getPermLevel() == UserProfile.TEACHER)
			{
				cells[row][0] = u.getLastName();
				cells[row][1] = u.getFirstName();
				cells[row][2] = "WIP";
				cells[row][3] = "\u2713";
				row++;
			}
		}

		return cells;
	}
}