package com.github.fantastic_five.GUIAdministrator;

/**
 * @author Fantastic Five (Jose Stovall)
 * A JPanel displaying all teachers in the university
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.MiscUtils;
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
		table.setModel(new DefaultTableModel(getTable(), new String[] { "Last", "First", "User ID", "Courses", "Avail." })
		{
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		});
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
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(469, 386, 128, 23);
		btnPrint.addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent e)
			{			
				MessageFormat header = new MessageFormat ("Master Teacher List");				
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
	public Object[][] getTable()
	{
		// Some local variables that help me later. Wastes memory, maybe - but saves typing a lot
		Set<UserProfile> teacherUsers = StudentRegistrationMain.profiles.copyUserProfiles();
		teacherUsers.removeIf(new Predicate<UserProfile>()
		{
			@Override
			public boolean test(UserProfile t)
			{
				return !(t.getPermLevel() == UserProfile.TEACHER || t.getPermLevel() == UserProfile.TA);
			}
		});
		Object[][] cells = new Object[teacherUsers.size()][7];
		int row = 0;
		// Loops through all courses and sets the columns in each row appropriately
		for (UserProfile u : teacherUsers)
		{
			cells[row][0] = u.getLastName();
			cells[row][1] = u.getFirstName();
			cells[row][2] = u.getUserID();
			// TODO: needs a way to actually access CRNs teacher teaches
			cells[row][3] = "WIP";
			// TODO: needs a way to actually check availability
			cells[row][4] = "\u2713";
			row++;

		}

		return cells;
	}
}