package com.github.fantastic_five.GUIAdministrator;

/**
 * @author Fantastic Five (Jose Stovall)
 * A JPanel containing the field necessary to remove a course offering from the University
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.Course;
import com.github.fantastic_five.Logic.Course.Day;
import com.github.fantastic_five.Logic.UserProfile;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
		scrollPane.setBounds(10, 95, 598, 283);
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
		lblCrnToRemove.setBounds(340, 64, 46, 20);
		add(lblCrnToRemove);

		// The box where the CRN entered should be accessed
		fieldCRN = new JTextField();		
		fieldCRN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				fieldCRN.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			}
		});
		fieldCRN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				fieldCRN.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			}
		});
//		fieldCRN.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent arg0) {
//				fieldCRN.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
//			}
//		});
		fieldCRN.setBounds(384, 64, 107, 20);
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
		btnBack.setBounds(10, 389, 128, 23);
		add(btnBack);

		// Remove Course Button and all of its actions
		JButton btnRemoveCourseOffering = new JButton("Remove");
		btnRemoveCourseOffering.addActionListener(new ActionListener()
		{
			// Makes a pop-up dialog window
			public void actionPerformed(ActionEvent e)
			{
				// Confirmation label
				Course searchedCourse = null;
				try
				{
					searchedCourse = StudentRegistrationMain.mainCourseManager.getCourse(Integer.parseInt(fieldCRN.getText()));
					fieldCRN.setBackground(Color.WHITE);
				}
				catch (NumberFormatException exception)
				{
					fieldCRN.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				}

				if (fieldCRN.getText().length() > 0 && searchedCourse != null)
				{
					// Resets the background text appropriately
					fieldCRN.setBackground(Color.WHITE);
					// Creates a pop-up window
					JDialog popup = new JDialog(StudentRegistrationMain.mainWindow, "Confirmation");
					popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					popup.setPreferredSize(new Dimension(300, 123));
					popup.setResizable(false);
					popup.setAlwaysOnTop(true);

					JPanel GUI = new JPanel();
					GUI.setLayout(null);

					JTextArea confirmation = new JTextArea();
					confirmation.setText("Are you sure you want to remove " + "\n" + "                   "  + searchedCourse.getTitle() + "?");
					confirmation.setForeground(Color.RED);
					confirmation.setFont(new Font("Tahoma", Font.BOLD, 16));
					confirmation.setBounds(7, 11, 322, 44);
					confirmation.setLineWrap(true);
					confirmation.setEditable(false);
					confirmation.setAlignmentX(CENTER_ALIGNMENT);
					confirmation.setBackground(popup.getBackground());
					GUI.add(confirmation);

					// No button should remove the CRN from the list
					JButton btnNo = new JButton("No");
					btnNo.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							popup.dispose();
						}
					});
					btnNo.setBounds(185, 60, 100, 23);
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
					btnYes.setBounds(10, 60, 100, 23);
					GUI.add(btnYes);

					// Finalizes the popup window
					popup.getContentPane().add(GUI);
					popup.pack();
					popup.setVisible(true);
					popup.setLocationRelativeTo(StudentRegistrationMain.mainWindow);
				}
				else
					fieldCRN.setBorder(BorderFactory.createLineBorder(Color.RED));
			}
		});
		btnRemoveCourseOffering.setBounds(501, 61, 107, 23);
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
		lblCourseRemoval.setBounds(10, 26, 598, 23);
		add(lblCourseRemoval);

		JLabel lblAllClasses = new JLabel("All Courses:");
		lblAllClasses.setBounds(10, 68, 116, 20);
		lblAllClasses.setForeground(Color.GRAY);
		lblAllClasses.setFont(new Font("Verdana", Font.BOLD, 13));
		add(lblAllClasses);

		/**
		 * Displays Course Description by double Clicking selected Course
		 */
		table.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					Course selectedCourse = StudentRegistrationMain.mainCourseManager.getCourse((int) table.getModel().getValueAt(table.getSelectedRow(), 0));

					JDialog popup = new JDialog(StudentRegistrationMain.mainWindow, selectedCourse.getTitle() + " - Description");
					popup.setBounds(200, 200, 447, 147);
					popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					popup.setLocationRelativeTo(null);
					popup.setResizable(false);
					popup.setVisible(true);

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
	}

	/**
	 * @return a two-dimensional object array for the table with properly pre-filled info
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
			cells[row][4] = teacher == null ? "TBA" : teacher.getFirstName().substring(1) + ". " + teacher.getLastName();
			cells[row][5] = getFormattedDays(c.getDays());
			cells[row][6] = c.getStartTime(Course.TWENTYFOUR_HR_CLOCK) + "-" + c.getEndTime(Course.TWENTYFOUR_HR_CLOCK);
			row++;
		}

		return cells;
	}

	String getFormattedDays(TreeSet<Day> days)
	{
		String rVal = "";
		for (Day d : days)
			rVal += d.getAbbreviation() + " ";
		return rVal;
	}
}