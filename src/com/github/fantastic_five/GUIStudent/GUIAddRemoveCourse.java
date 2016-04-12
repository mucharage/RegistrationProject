/**
 * @author Alay Patel (leader)
 * Group 5 
 */

package com.github.fantastic_five.GUIStudent;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TreeSet;
import java.util.function.Predicate;

import javax.swing.JButton;
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

@SuppressWarnings("serial")
public class GUIAddRemoveCourse extends JPanel
{
	/**
	 * Private instant variables
	 */
	private JTextField searchField;
	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnBack;
	private JButton btnSearch;
	private JLabel lblCrn;
	private JTable searchTable;
	private JTable addedTable;

	/**
	 * This GUI class displays the panel for adding and removing courses. Here student can search course by CRN that he/she want to add or remove, and would allow them to do so.
	 */
	public GUIAddRemoveCourse()
	{
		setBounds(0, 0, 618, 434);
		setLayout(null);

		searchField = new JTextField();
		searchField.setBounds(88, 82, 206, 20);
		add(searchField);
		searchField.setColumns(10);

		/**
		 * Button & Logic for Remove for the list below
		 */

		btnRemove = new JButton("Remove");
		btnRemove.setBounds(180, 345, 254, 23);
		btnRemove.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				GUIRemove.main(null);
			}// end of the actionPerformed
		});// end of the actionPerformed
		add(btnRemove);

		/**
		 * adds a back button.
		 */
		btnBack = new JButton("Back");
		btnBack.setBounds(41, 389, 128, 23);
		btnBack.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIStudent());
			}// end of mouseClicked
		});// end of addMouseListener
		add(btnBack);

		/**
		 * adds a label named, "Search By"
		 */
		JLabel lblSearchBy = new JLabel("Search By:");
		lblSearchBy.setBounds(41, 56, 116, 20);
		lblSearchBy.setForeground(Color.GRAY);
		lblSearchBy.setFont(new Font("Verdana", Font.BOLD, 13));
		add(lblSearchBy);

		/**
		 * Adds a label named, "CRN:"
		 */
		lblCrn = new JLabel("CRN:");
		lblCrn.setBounds(43, 84, 46, 14);
		lblCrn.setFont(new Font("Verdana", Font.BOLD, 12));
		add(lblCrn);

		/**
		 * Adds a ScrollPane
		 */
		JScrollPane searchScrollPane = new JScrollPane();
		searchScrollPane.setBounds(41, 132, 539, 50);
		add(searchScrollPane);

		/**
		 * Creates a Table which shall display result of the course that user has searched for
		 */
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
					searchField.setText("CRN Must be numbers only");
				}
			}
		});
		add(btnSearch);

		/**
		 * Creates an another ScrollPane
		 */
		JScrollPane addedScrollPane = new JScrollPane();
		addedScrollPane.setBounds(41, 227, 540, 107);
		add(addedScrollPane);

		/**
		 * Creates an another Table which shall course that user has added.
		 */
		addedTable = new JTable();
		addedTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"CRN", "Class", "Capacity", "Remaining", "Time", "Day", "Teacher", "Room"
			}
		));
		addedScrollPane.setViewportView(addedTable);

		/**
		 * Button & Logic for Add Courses to list below.
		 */
		btnAdd = new JButton("Add");
		btnAdd.setBounds(180, 193, 254, 23);
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// TODO: needs something here? Not sure...
			}// end of the actionPerformed
		});// end of the addActionListener
		add(btnAdd);

		/**
		 * Adds a GUILogIn
		 */
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		/**
		 * Adds a Label named, "Add or Remove Courses."
		 */
		JLabel lblCourseRemoval = new JLabel("Add or Remove Courses");
		lblCourseRemoval.setForeground(Color.GRAY);
		lblCourseRemoval.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCourseRemoval.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseRemoval.setBounds(177, 30, 243, 23);
		add(lblCourseRemoval);

	}

	/**
	 * @return a two-dimensional object array for the table with properly pre-filled info
	 */
	public Object[][] getSearchResultTable(int CRN)
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
			cells[row][0] = c.getCRN();
			cells[row][1] = c.getTitle();
			cells[row][2] = c.getStudentCap();
			cells[row][3] = c.getRemainingCap();
			cells[row][4] = c.getTeacherName();
			cells[row][5] = MiscUtils.getDaysFormatted(c.getDays());
			cells[row][6] = c.getStartTime(Course.TWENTYFOUR_HR_CLOCK) + "-" + c.getEndTime(Course.TWENTYFOUR_HR_CLOCK);
			row++;
		}

		return cells;
	}
}// end of JPanel extension of GUIAddorRemoveCourse()