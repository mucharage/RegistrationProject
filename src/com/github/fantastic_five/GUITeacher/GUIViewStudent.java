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
import java.util.Iterator;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUI.UniversalBackButton;
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.Course;
import com.github.fantastic_five.Logic.UserProfile;

@SuppressWarnings("serial")
public class GUIViewStudent extends JPanel
{
	private JTable table;
	private JTable table_1;
	private static JTable addedTable;
	/**
	 * This GUI that shall display teacher's individual schedule of c31rses he/she is teaching
	 */
	public GUIViewStudent()
	{
		/**
		 * Adds a ScrollPane
		 */
		setBounds(0, 0, 618, 434);
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 62, 598, 106);
		add(scrollPane);

		/**
		 * Adds a table which will display list of courses that the user chose
		 */
		addedTable = new JTable();
		addedTable.setModel(new DefaultTableModel(GUIAddDropClass.getClassTable(), new String[] { "CRN", "Class", "Capacity", "Remaining", "Teacher", "Day", "Time" })
		{
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}

		});
		scrollPane.setViewportView(addedTable);
		addedTable.setAutoCreateRowSorter(true);
		
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
					addedTable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
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
		lblViewSchedule.setBounds(10, 35, 587, 23);
		lblViewSchedule.setForeground(Color.GRAY);
		lblViewSchedule.setFont(new Font("Verdana", Font.BOLD, 16));
		lblViewSchedule.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblViewSchedule);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 179, 598, 196);
		add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"User ID", "Last Name", "First Name"
			}
		));
		scrollPane_1.setViewportView(table_1);
		
		/**
		 * Displays currently enrolled students by double clicking selected course
		 */
		addedTable.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					int rowSel = addedTable.getSelectedRow();
					
					if (rowSel > -1)
					{
						StudentRegistrationMain.mainCourseManager.getLearnersWithCourse((int) addedTable.getModel().getValueAt(rowSel, 0));
						table_1.setModel(new DefaultTableModel(getStudentTable(), new String[] { "User ID", "Last Name", "First Name" })
						{
							@Override
							public boolean isCellEditable(int row, int column)
							{
								return false;
							}
						});
						scrollPane_1.setViewportView(table_1);
						revalidate();
						repaint();	
						//System.out.println(StudentRegistrationMain.mainCourseManager.getLearnersWithCourse((int) addedTable.getModel().getValueAt(rowSel, 0)));
						
						Course selectedCourse = StudentRegistrationMain.mainCourseManager.getCourse((int) addedTable.getModel().getValueAt(addedTable.getSelectedRow(), 0));
						Set<UserProfile> student = StudentRegistrationMain.mainCourseManager.getLearnersWithCourse(selectedCourse.getCRN());
						
						Iterator<UserProfile> names = student.iterator();
						while(names.hasNext())
						{
							System.out.println(names.next());
						}
					
					}
				}
			}
		});
	}	
	
	/**
	 * @return fills or removes information from the table when a student adds or drops the selected course.
	 */
	public static Object[][] getStudentTable()
	{
		Set<Course> selectedCourse = StudentRegistrationMain.mainCourseManager.getCoursesWithInstructor(StudentRegistrationMain.getCurrentLoggedInUser());
		Object[][] cells = new Object[selectedCourse.size()][3];
		int row = 0;		
		for (Course c : selectedCourse)
		{
			Set<UserProfile> student = StudentRegistrationMain.mainCourseManager.getLearnersWithCourse(c.getCRN());
			
			Iterator<UserProfile> names = student.iterator();
			while(names.hasNext())
			{
				System.out.println(names.next());
			}
			//student.getUserID();
			//cells[row][0] = c.getUserID();
			cells[row][1] = c.getTitle();
			cells[row][2] = c.getStartTime(Course.TWENTYFOUR_HR_CLOCK) + "-" + c.getEndTime(Course.TWENTYFOUR_HR_CLOCK);
			row++;
		}
		return cells;
	}
}
