package com.github.fantastic_five.GUIAdministrator;

/**
 * @author Fantastic Five (Jose Stovall)
 * A JFrame containing the field necessary to remove a course offering from the University
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.Course;
import com.github.fantastic_five.Logic.Course.Day;

@SuppressWarnings("serial")
public class GUIRemoveCourse extends JPanel
{
	// Private instance variables
	private JTextField fieldCRN;

	public GUIRemoveCourse()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		// Table with auto-generated content!
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 136, 545, 59);
		add(scrollPane);

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

		// Label for the CRN box
		JLabel lblCrnToRemove = new JLabel("CRN:");
		lblCrnToRemove.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCrnToRemove.setBounds(41, 98, 46, 14);
		add(lblCrnToRemove);

		// The box where the CRN entered should be accessed
		fieldCRN = new JTextField();
		fieldCRN.setBounds(99, 96, 176, 20);
		add(fieldCRN);
		fieldCRN.setColumns(10);

		// Back Button and all of its actions
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAdmin());
			}
		});
		btnBack.setBounds(41, 389, 128, 23);
		add(btnBack);

		// Remove Course Button and all of its actions
		JButton btnRemoveCourseOffering = new JButton("Remove");
		btnRemoveCourseOffering.addActionListener(new ActionListener()
		{
			// Makes a pop-up dialog window
			public void actionPerformed(ActionEvent e)
			{
				// Creates a pop-up window
				JFrame popup = new JFrame("Confirmation");
				popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				popup.setPreferredSize(new Dimension(307, 107));
				popup.setResizable(false);

				JPanel GUI = new JPanel();
				GUI.setLayout(null);

				// Confirmation label
				JLabel lblAreYouSure = new JLabel("Are you sure?");
				lblAreYouSure.setForeground(Color.RED);
				lblAreYouSure.setFont(new Font("Tahoma", Font.BOLD, 16));
				lblAreYouSure.setBounds(86, 11, 127, 20);
				GUI.add(lblAreYouSure);

				// No button should remove the CRN from the list
				JButton btnNo = new JButton("No");
				btnNo.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						popup.dispose();
					}
				});
				btnNo.setBounds(191, 49, 100, 23);
				GUI.add(btnNo);

				// Yes button closes the window
				JButton btnYes = new JButton("Yes");
				btnYes.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						// Removes the class
						StudentRegistrationMain.mainCourseManager.removeCourse(Integer.parseInt(fieldCRN.getText()));
						// Refreshes the table
						table.setModel(new DefaultTableModel(getCourseTable(), new String[] { "CRN", "Class", "Capacity", "Remaining", "Teacher", "Days", "Time" })
						{
							@Override
							public boolean isCellEditable(int row, int column)
							{
								return false;
							}
						});
						scrollPane.setViewportView(table);
						revalidate();
						repaint();
						// Removes the popup
						popup.dispose();
					}
				});
				btnYes.setBounds(10, 49, 100, 23);
				GUI.add(btnYes);

				// Finalizes the popup window
				popup.getContentPane().add(GUI);
				popup.pack();
				popup.setVisible(true);
				popup.setLocationRelativeTo(null);
			}
		});
		btnRemoveCourseOffering.setBounds(212, 206, 190, 23);
		add(btnRemoveCourseOffering);

		// Adds the loginPanel to our panel
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// Panel Title, basically
		JLabel lblCourseRemoval = new JLabel("Remove Course");
		lblCourseRemoval.setForeground(Color.GRAY);
		lblCourseRemoval.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCourseRemoval.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseRemoval.setBounds(188, 26, 243, 23);
		add(lblCourseRemoval);

		JLabel lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setBounds(41, 67, 116, 20);
		lblSearchBy.setForeground(Color.GRAY);
		lblSearchBy.setFont(new Font("Verdana", Font.BOLD, 13));
		add(lblSearchBy);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(41, 247, 545, 131);
		add(scrollPane_1);

	}

	/**
	 * @return a two-dimensional object array for the table with properly pre-filled info
	 */
	public Object[][] getCourseTable()
	{
		// Some local variables that help me later. Wastes memory, maybe - but saves typing a lot
		TreeSet<Course> courseOfferings = StudentRegistrationMain.mainCourseManager.getCourses();
		int numCourses = StudentRegistrationMain.mainCourseManager.getCourses().size();
		Object[][] table = new Object[numCourses][7];

		int row = 0;
		// Loops through all courses and sets the columns in each row appropriately
		for (Course c : courseOfferings)
		{
			table[row][0] = c.getCRN();
			table[row][1] = c.getTitle();
			table[row][2] = c.getStudentCap();
			table[row][3] = c.getRemainingCap();
			table[row][4] = c.getTeacherName();
			table[row][5] = getDaysFormatted(c.getDays());
			table[row][6] = c.getStartTime(Course.TWENTYFOUR_HR_CLOCK) + "-" + c.getEndTime(Course.TWENTYFOUR_HR_CLOCK);
			row++;
		}

		return table;
	}

	/**
	 * 
	 * @param daySet
	 *            the HashSet of days that needs to be formatted
	 * @return a formatted string with all the days
	 */
	String getDaysFormatted(HashSet<Day> daySet)
	{
		String ret = "";
		for (Day d : daySet)
			ret += d.getAbbreviation() + " ";
		return ret;
	}
}
