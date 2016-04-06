/**
 * @author Fantastic Five (Alay Patel)
 * This GUI displays all of the available courses that our University offers. 
 */
package com.github.fantastic_five.GUIAdministrator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

@SuppressWarnings("serial")
public class GUIViewCourses extends JPanel
{
	public GUIViewCourses()
	{
		setBounds(0, 0, 618, 434);
		setLayout(null);

		/**
		 * Adds a ScrollPane
		 */

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 587, 311);
		add(scrollPane);

		/**
		 * Adds a table displaying important details for each courses.
		 * 
		 */
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(getCourseTable(), new String[] { "CRN", "Class", "Capacity", "Remaining", "Teacher", "Time", "Room" })
		{
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		});
		scrollPane.setViewportView(table);

		/**
		 * Button & Logic for View Schedule
		 */
		JButton btnBack = new JButton("Back");
		btnBack.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAdmin());
			}// end of mouseClicked
		});// end of MouseListener
		btnBack.setBounds(10, 386, 128, 23);
		add(btnBack);

		/**
		 * Adds a login Panel
		 */
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		/**
		 * Adds a Label named View Courses.
		 */
		JLabel lblCourseRemoval = new JLabel("View Courses");
		lblCourseRemoval.setForeground(Color.GRAY);
		lblCourseRemoval.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCourseRemoval.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseRemoval.setBounds(179, 21, 243, 23);
		add(lblCourseRemoval);

	}// end of GuiViewCourses()

	public Object[][] getCourseTable()
	{
		TreeSet<Course> courseOfferings = StudentRegistrationMain.mainCourseManager.getCourses();
		int numCourses = StudentRegistrationMain.mainCourseManager.getCourses().size();
		Object[][] items = new Object[numCourses][6];

		int row = 0;
		for (Course c : courseOfferings)
		{
			items[row][0] = c.getCRN();
			items[row][1] = c.getTitle();
			items[row][2] = c.getStudentCap();
			items[row][3] = c.getRemainingCap();
			items[row][4] = c.getTeacherName();
			items[row][5] = c.getStartTime(Course.TWENTYFOUR_HR_CLOCK) + "-" + c.getEndTime(Course.TWENTYFOUR_HR_CLOCK);
			row++;
		}

		return items;
	}
}// end of JPanel extension of GuiViewCourses