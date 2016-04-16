package com.github.fantastic_five.GUITeacher;

/**
 * @author Christian Phillips
 * Group 5 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

import javax.swing.AbstractButton;
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
import com.github.fantastic_five.Logic.MiscUtils;
import com.github.fantastic_five.Logic.UserProfile;

@SuppressWarnings("serial")
public class GUIAddRemoveClass extends JPanel
{
	// private instance variables.
	private JTextField searchField;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnBack;
	private JButton btnSearch;
	private JLabel lblCrn;
	private JTable searchTable;
	private JTable addedTable;
	
	private int CRNToSearch;
	ArrayList<Course> courseSearchResult;

	/**
	 * This GUI class displays the panel for adding and removing courses that the teacher is teaching.
	 */
	public GUIAddRemoveClass()
	{
		setBounds(0, 0, 618, 434);
		setLayout(null);

		searchField = new JTextField();
		searchField.setBounds(88, 82, 206, 20);
		add(searchField);
		searchField.setColumns(10);
		searchField.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
				{
					btnSearch.doClick();
				}
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				/** Do Nothing */
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				/** Do Nothing */
			}
		});
		
		// Creates another scroll pane
		JScrollPane addedScrollPane = new JScrollPane();
		addedScrollPane.setBounds(41, 227, 540, 107);
		add(addedScrollPane);
		
		// Button & Logic for Remove Courses
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(180, 345, 254, 23);
		btnRemove.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFrame popup = new JFrame("Confirmation");
				popup.setBounds(100, 100, 307, 107);
				popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				popup.setLocationRelativeTo(null);
				popup.getContentPane().setLayout(null);
				popup.setResizable(false);
				popup.setVisible(true);
				JLabel txtpnAreYouSure = new JLabel();
				txtpnAreYouSure.setText("Are you sure?");
				txtpnAreYouSure.setForeground(Color.RED);
				txtpnAreYouSure.setFont(new Font("Verdana", Font.BOLD, 16));
				txtpnAreYouSure.setBounds(86, 11, 127, 20);
				popup.getContentPane().add(txtpnAreYouSure);

				// Logic for Yes button on popup
				JButton btnYes = new JButton("Yes");
				btnYes.setBounds(10, 49, 100, 23);
				btnYes.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						// Gets the selected row
						int rowSel = addedTable.getSelectedRow();
						if (rowSel > -1)
						{
							StudentRegistrationMain.mainCourseManager.removeInstructorFromCourse(MiscUtils.getCurrentLoggedInUser(), (int) searchTable.getModel().getValueAt(rowSel, 0));

							addedTable.setModel(new DefaultTableModel(getClassTable(), new String[] { "CRN", "Class", "Capacity", "Remaining", "Time", "Day", "Teacher", "Room" })
							{
								@Override
								public boolean isCellEditable(int row, int column)
								{
									return false;
								}
							});
							addedScrollPane.setViewportView(addedTable);
							revalidate();
							repaint();
						}
						popup.dispose();
					}
				});
				popup.getContentPane().add(btnYes);

				JButton btnNo = new JButton("No");
				btnNo.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						popup.dispose();
					}
				});
				btnNo.setBounds(191, 49, 100, 23);
				popup.getContentPane().add(btnNo);
			}
		});
		add(btnRemove);

		// adds a back button
		btnBack = new JButton("Back");
		btnBack.setBounds(41, 389, 128, 23);
		btnBack.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUITeacher());
			}
		});
		add(btnBack);

		// adds a label named "Search By"
		JLabel lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setBounds(41, 56, 116, 20);
		lblSearchBy.setForeground(Color.GRAY);
		lblSearchBy.setFont(new Font("Verdana", Font.BOLD, 13));
		add(lblSearchBy);

		// adds a label named "CRN:"
		lblCrn = new JLabel("CRN:");
		lblCrn.setBounds(43, 84, 46, 14);
		lblCrn.setFont(new Font("Verdana", Font.BOLD, 12));
		add(lblCrn);

		// adds a scroll pane
		JScrollPane searchScrollPane = new JScrollPane();
		searchScrollPane.setBounds(41, 132, 539, 50);
		add(searchScrollPane);

	    // adds a table to display searched classes
		searchTable = new JTable();
		searchTable.setModel(new DefaultTableModel(getSearchResultTable(0), new String[] { "CRN", "Class", "Capacity", "Remaining", "Time", "Day", "Teacher", "Room" })
		{
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		});
		searchScrollPane.setViewportView(searchTable);

		btnSearch = new JButton("Search");
		btnSearch.setBounds(300, 82, 128, 23);
		btnSearch.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					int CRN = Integer.parseInt(searchField.getText());
					searchTable.setModel(new DefaultTableModel(getSearchResultTable(CRN), new String[] { "CRN", "Class", "Capacity", "Remaining", "Time", "Day", "Teacher", "Room" })
					{
						@Override
						public boolean isCellEditable(int row, int column)
						{
							return false;
						}
					});
					searchScrollPane.setViewportView(searchTable);
					revalidate();
					repaint();
				}
				catch (NumberFormatException exception)
				{
					JLabel notNumbers = new JLabel();
					notNumbers.setForeground(Color.RED);
					notNumbers.setText("CRN Must be Numbers Only");
					notNumbers.setBounds(88, 102, 206, 20);
					revalidate();
					repaint();
					add(notNumbers);
					//TODO: Notification for incorrect CRN
				}
			}
		});
		add(btnSearch);

		// adds another table that displays courses the user has added
		addedTable = new JTable();
		addedTable.setModel(new DefaultTableModel(getClassTable(), new String[] { "CRN", "Class", "Capacity", "Remaining", "Time", "Day", "Teacher", "Room" }));
		addedScrollPane.setViewportView(addedTable);
		
		// Button & Logic for Add courses
		btnAdd = new JButton("Add");
		btnAdd.setBounds(180, 183, 254, 23);
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int rowSel = searchTable.getSelectedRow();

				if (rowSel > -1)
				{
					StudentRegistrationMain.mainCourseManager.addInstructorToCourse(MiscUtils.getCurrentLoggedInUser(), (int) searchTable.getModel().getValueAt(rowSel, 0));
					addedTable.setModel(new DefaultTableModel(getClassTable(), new String[] { "CRN", "Class", "Capacity", "Remaining", "Time", "Day", "Teacher", "Room" })
					{
						@Override
						public boolean isCellEditable(int row, int column)
						{
							return false;
						}
					});
					addedScrollPane.setViewportView(addedTable);
					revalidate();
					repaint();
				}
			}
		});
		add(btnAdd);

		// adds a GUILogin
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// adds a label named "Add or Remove Courses
		JLabel lblCourseRemoval = new JLabel("Add or Remove Courses");
		lblCourseRemoval.setForeground(Color.GRAY);
		lblCourseRemoval.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCourseRemoval.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseRemoval.setBounds(177, 30, 243, 23);
		add(lblCourseRemoval);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(304, 81, 89, 23);
		btnSearch.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				AbstractButton textField = null;
				CRNToSearch = Integer.parseInt(textField.getText());
				courseSearchResult = new ArrayList<Course>();
				for (Course c : StudentRegistrationMain.mainCourseManager.copyCourseOfferings())
					if (c.getCRN() == CRNToSearch)
						courseSearchResult.add(c);
				searchTable.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null } }, new String[] { "CRN", "Class", "Capacity", "Remaining", "Teacher", "Days", "Time" })
				{
					@Override
					public boolean isCellEditable(int row, int column)
					{
						return false;
					}
				});
				JScrollPane scrollPane_1 = null;
				scrollPane_1.setViewportView(searchTable);
				revalidate();
				repaint();
			}
		});
		add(btnSearch);
	}
	
	private Object[][] getClassTable()
	{
		Set<Course> enrolledCourses = StudentRegistrationMain.mainCourseManager.getCoursesWithInstructor(MiscUtils.getCurrentLoggedInUser());
		Object[][] cells = new Object[enrolledCourses.size()][7];
		int row = 0;
		for (Course c : enrolledCourses)
		{
			UserProfile teacher = StudentRegistrationMain.mainCourseManager.getInstructorWithCourse(c.getCRN());

			cells[row][0] = c.getCRN();
			cells[row][1] = c.getTitle();
			cells[row][2] = c.getStudentCap();
			cells[row][3] = c.getRemainingCap();
			if (teacher != null)
				cells[row][4] = teacher.getFirstName().substring(0, 1) + " " + teacher.getLastName();
			cells[row][5] = c.getDays();
			cells[row][6] = c.getStartTime(Course.TWENTYFOUR_HR_CLOCK) + "-" + c.getEndTime(Course.TWENTYFOUR_HR_CLOCK);
			row++;
		}
		return cells;
	}

	/**
	 * @return a two-dimensional object array for the table with properly pre-filled info
	 */
	private Object[][] getSearchResultTable(int CRN)
	{
		// Some local variables that help me later. Wastes memory, maybe - but saves typing a lot
		TreeSet<Course> courseOfferings = StudentRegistrationMain.mainCourseManager.copyCourseOfferings();
		courseOfferings.removeIf(new Predicate<Course>()
		{
			@Override
			public boolean test(Course toTest)
			{
				return toTest.getCRN() != CRN;
			}
		});
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
			if (teacher != null)
				cells[row][4] = teacher.getFirstName().substring(0, 1) + " " + teacher.getLastName();
			cells[row][5] = c.getDays();
			cells[row][6] = c.getStartTime(Course.TWENTYFOUR_HR_CLOCK) + "-" + c.getEndTime(Course.TWENTYFOUR_HR_CLOCK);
			row++;
		}
		return cells;
	}
}
