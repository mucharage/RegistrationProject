/**
 * @author Alay Patel (leader)
 * This GUI class displays the panel for adding and removing courses. Here student can search course by CRN that he/she want to add or Drop, and would allow them to do so. 
 */

package com.github.fantastic_five.GUIStudent;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUI.UneditableTableModel;
import com.github.fantastic_five.GUI.UniversalBackButton;
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.Course;
import com.github.fantastic_five.Logic.Course.Day;
import com.github.fantastic_five.Logic.ScheduleManager;
import com.github.fantastic_five.Logic.UserProfile;

@SuppressWarnings("serial")
public class GUIAddDropCourse extends JPanel
{
	private JTextField searchField;
	private JButton btnAdd;
	private JButton btnDrop;
	private JButton btnSearch;
	private JLabel lblCrn;
	private JTable searchTable;
	private JTable addedTable;
	private JLabel lblClassReq;
	private String[] headers = new String[] { "CRN", "Class", "Capacity", "Remaining", "Teacher", "Day", "Time" };

	public GUIAddDropCourse()
	{
		setBounds(0, 0, 618, 434);
		setLayout(null);

		searchField = new JTextField();
		searchField.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				searchField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			}
		});
		searchField.setBounds(98, 95, 128, 20);
		searchField.setColumns(10);
		searchField.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				searchField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				if (e.getKeyChar() == KeyEvent.VK_ENTER)
					btnSearch.doClick();
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
			}

			@Override
			public void keyReleased(KeyEvent arg0)
			{
			}
		});
		add(searchField);

		// Creates an another ScrollPane for the added courses
		JScrollPane addedScrollPane = new JScrollPane();
		addedScrollPane.setBounds(10, 216, 598, 107);
		add(addedScrollPane);

		// Button & Logic for Remove for the list below
		btnDrop = new JButton("Drop");
		btnDrop.setBounds(242, 335, 128, 23);
		btnDrop.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JDialog popup = new JDialog(StudentRegistrationMain.mainWindow, "Confirmation");
				popup.setBounds(100, 100, 307, 107);
				popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				popup.setLocationRelativeTo(null);
				popup.getContentPane().setLayout(null);
				popup.setResizable(false);
				popup.setVisible(true);
				popup.setAlwaysOnTop(true);
				JLabel txtpnAreYouSure = new JLabel();
				txtpnAreYouSure.setText("Are you sure?");
				txtpnAreYouSure.setForeground(Color.RED);
				txtpnAreYouSure.setFont(new Font("Verdana", Font.BOLD, 16));
				txtpnAreYouSure.setBounds(86, 11, 127, 20);
				popup.getContentPane().add(txtpnAreYouSure);

				// Logic for Yes button inside Confirmation Pop-up
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
							int CRN = (int) addedTable.getModel().getValueAt(addedTable.convertRowIndexToModel(rowSel), 0);
							StudentRegistrationMain.mainCourseManager.removeLearnerFromCourse(StudentRegistrationMain.getCurrentLoggedInUser(), CRN);
							StudentRegistrationMain.mainCourseManager.getCourse(CRN).decrRemainingCap();

							addedTable.setModel(new UneditableTableModel(getClassTable(), headers));
							lblClassReq.setText(getErrorText());
							try
							{
								searchTable.setModel(new UneditableTableModel(getSearchResultTable(Integer.parseInt(searchField.getText())), headers));
							}
							catch (NumberFormatException ex)
							{
								searchTable.setModel(new UneditableTableModel(getSearchResultTable(0), headers));
							}

							revalidate();
							repaint();
						}
						popup.dispose();
					}
				});
				popup.getContentPane().add(btnYes);

				// Logic for No Button inside Confirmation Pop-up
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
		add(btnDrop);

		// adds a universal back button with logic behind it.
		JButton btnBack = new UniversalBackButton();
		btnBack.setBounds(10, 388, 128, 23);
		add(btnBack);

		// adds a label named, "Search By"
		JLabel lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setBounds(10, 64, 116, 20);
		lblSearchBy.setForeground(Color.GRAY);
		lblSearchBy.setFont(new Font("Verdana", Font.BOLD, 13));
		add(lblSearchBy);

		// Adds a label named, "CRN:"
		lblCrn = new JLabel("CRN:");
		lblCrn.setBounds(42, 97, 46, 14);
		lblCrn.setFont(new Font("Verdana", Font.BOLD, 12));
		add(lblCrn);

		// Adds the error label
		lblClassReq = new JLabel(getErrorText());
		lblClassReq.setFont(new Font("Monospaced", Font.PLAIN, 12));
		lblClassReq.setHorizontalAlignment(SwingConstants.CENTER);
		lblClassReq.setForeground(Color.RED);
		lblClassReq.setBounds(10, 368, 598, 14);
		add(lblClassReq);

		// Adds a ScrollPane
		JScrollPane searchScrollPane = new JScrollPane();
		searchScrollPane.setBounds(10, 128, 598, 43);
		add(searchScrollPane);

		// Creates a Table which shall display result of the course that user has searched for
		searchTable = new JTable();
		searchTable.setModel(new UneditableTableModel(getSearchResultTable(0), headers));
		searchScrollPane.setViewportView(searchTable);

		// Added a Button named "Search" would search for the entered CRN from the Course data.
		btnSearch = new JButton("Search");
		btnSearch.setBounds(242, 95, 128, 23);
		btnSearch.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					int CRN = Integer.parseInt(searchField.getText());
					if (StudentRegistrationMain.mainCourseManager.getCourse(CRN) != null)
					{
						searchTable.setModel(new UneditableTableModel(getSearchResultTable(CRN), headers));
						searchScrollPane.setViewportView(searchTable);
					}
					else
					{
						searchField.setBorder(BorderFactory.createLineBorder(Color.RED));
						revalidate();
						repaint();
					}
				}
				catch (NumberFormatException exception)
				{
					searchField.setBorder(BorderFactory.createLineBorder(Color.RED));
					revalidate();
					repaint();
				}
			}
		});
		add(btnSearch);

		// Creates an another Table which shall course that user has added.
		addedTable = new JTable();
		addedTable.setModel(new UneditableTableModel(getClassTable(), headers));
		addedScrollPane.setViewportView(addedTable);
		addedTable.setAutoCreateRowSorter(true);

		// Button & Logic for Add Courses to list below.
		btnAdd = new JButton("Add");
		btnAdd.setBounds(242, 183, 128, 23);
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int rowSel = searchTable.getSelectedRow();

				if (rowSel > -1)
				{
					Set<Course> conflicts = ScheduleManager.getConflictingCourses((int) searchTable.getModel().getValueAt(searchTable.convertRowIndexToModel(rowSel), 0), StudentRegistrationMain.getCurrentLoggedInUser());
					if (conflicts.size() > 0)
					{
						JDialog conflict = new JDialog(StudentRegistrationMain.mainWindow, "Course Conflict");
						conflict.setBounds(100, 100, 343, 87);
						conflict.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						conflict.setLocationRelativeTo(null);
						conflict.getContentPane().setLayout(null);
						conflict.setResizable(false);
						conflict.setVisible(true);
						conflict.setAlwaysOnTop(true);

						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setBounds(0, 0, 339, 59);
						conflict.getContentPane().add(scrollPane);

						JTable table = new JTable();
						table.setModel(new UneditableTableModel(getConflictTable((int) searchTable.getModel().getValueAt(table.convertRowIndexToModel(rowSel), 0)), new String[] { "CRN", "Class", "Time" }));
						scrollPane.setViewportView(table);
					}
					// Checks to make sure the user isn't taking MORE than 5 classes
					else if (StudentRegistrationMain.mainCourseManager.getCoursesWithLearner(StudentRegistrationMain.getCurrentLoggedInUser()).size() <= 5)
					{
						int CRN = (int) searchTable.getModel().getValueAt(searchTable.convertRowIndexToModel(rowSel), 0);
						// Checks to make sure the student isn't also the teacher: this is a check for TA cases:
						if (StudentRegistrationMain.mainCourseManager.getInstructorWithCourse(CRN).getUserID() != StudentRegistrationMain.getCurrentLoggedInUser().getUserID())
						{
							StudentRegistrationMain.mainCourseManager.addLearnerToCourse(StudentRegistrationMain.getCurrentLoggedInUser(), CRN);
							addedTable.setModel(new UneditableTableModel(getClassTable(), headers));
							searchTable.setModel(new UneditableTableModel(getSearchResultTable(Integer.parseInt(searchField.getText())), headers));
							lblClassReq.setText(getErrorText());
							revalidate();
							repaint();
						}
					}
				}
			}
		});
		add(btnAdd);

		// Adds login status bar
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// Adds a Label named, "Add or Remove Courses."
		JLabel lblCourseRemoval = new JLabel("Register/Drop Courses");
		lblCourseRemoval.setForeground(Color.GRAY);
		lblCourseRemoval.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCourseRemoval.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseRemoval.setBounds(10, 30, 598, 23);
		add(lblCourseRemoval);
		revalidate();
		repaint();
	}

	/**
	 * 
	 * @return fills or removes information from the table when user adds or drops course.
	 */
	public static Object[][] getClassTable()
	{
		Set<Course> enrolledCourses = StudentRegistrationMain.mainCourseManager.getCoursesWithLearner(StudentRegistrationMain.getCurrentLoggedInUser());
		Object[][] cells = new Object[enrolledCourses.size()][7];
		int row = 0;
		for (Course c : enrolledCourses)
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
	 * @param CRN
	 *            CRN to check for conflicts with
	 * @return a two-dimensional object array for the table containing all classes conflicting with the CRN provided
	 */
	Object[][] getConflictTable(int CRN)
	{
		Set<Course> courseConflict = StudentRegistrationMain.mainCourseManager.getCoursesWithLearner(StudentRegistrationMain.getCurrentLoggedInUser());
		Object[][] cells = new Object[courseConflict.size()][3];
		int row = 0;

		for (Course c : courseConflict)
		{
			cells[row][0] = c.getCRN();
			cells[row][1] = c.getTitle();
			cells[row][2] = c.getStartTime(Course.TWENTYFOUR_HR_CLOCK) + "-" + c.getEndTime(Course.TWENTYFOUR_HR_CLOCK);
			row++;
		}
		return cells;
	}

	/**
	 * @return a two-dimensional object array for the table with properly pre-filled info
	 */
	Object[][] getSearchResultTable(int CRN)
	{
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

	/**
	 * @return a text error for when the student is not registered for enough classes.
	 */
	String getErrorText()
	{
		return (StudentRegistrationMain.mainCourseManager.getCoursesWithLearner(StudentRegistrationMain.getCurrentLoggedInUser()).size() < 3) ? "You are not registered for enough classes. (Minimum of Three)" : "";
	}

}