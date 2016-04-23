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
						Course selectedCourse = StudentRegistrationMain.mainCourseManager.getCourse((int) addedTable.getModel().getValueAt(addedTable.getSelectedRow(), 0));
						Set<UserProfile> student = StudentRegistrationMain.mainCourseManager.getLearnersWithCourse(selectedCourse.getCRN());
						
						JDialog popup = new JDialog(StudentRegistrationMain.mainWindow, selectedCourse.getTitle() + " - Students");
						popup.setBounds(200, 200, 447, 147);
						popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						popup.setLocationRelativeTo(StudentRegistrationMain.mainWindow);
						popup.setResizable(false);
						popup.setVisible(true);
						popup.setAlwaysOnTop(true);
						
						JTextArea desc = new JTextArea();
						Iterator<UserProfile> names = student.iterator();		
						String x = names.toString();
						while(names.hasNext())
						{									
					
							
							System.out.println(names.next());
						}
						
						//JTextArea desc = new JTextArea();
						desc.setText(selectedCourse.getDescription());
						desc.setText(x);
						desc.setWrapStyleWord(true);
						desc.setLineWrap(true);
						desc.setFont(new Font("Verdana", Font.PLAIN, 12));
						desc.setBounds(10, 11, 421, 96);						
						desc.setEditable(false);
						scrollPane.setViewportView(desc);
						
						JScrollPane scrollPane = new JScrollPane(desc);	
						scrollPane.setBounds(10, 11, 421, 96);
						popup.getContentPane().add(scrollPane);		
						
					}
				}
			}
		});
	}
}
