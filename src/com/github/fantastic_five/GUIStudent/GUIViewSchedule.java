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
import java.awt.print.PrinterException;
import java.text.MessageFormat;

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

@SuppressWarnings("serial")
public class GUIViewSchedule extends JPanel
{

	/**
	 * This GUI that shall display student's individual schedule of courses that he/she has chosen
	 */
	public GUIViewSchedule()
	{
		/**
		 * adds a ScrollPane
		 */
		setBounds(0, 0, 618, 434);
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 119, 587, 106);
		add(scrollPane);

		/**
		 * adds a table which would displays list of courses that user have ch osen
		 */
		JTable addedTable = new JTable();
		addedTable.setModel(new DefaultTableModel(GUIAddDropCourse.getClassTable(), new String[] { "CRN", "Class", "Capacity", "Remaining", "Teacher", "Day", "Time", "Room" })
		{
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}

		});
		scrollPane.setViewportView(addedTable);

		// addedTable = new JTable();
		// addedTable.setModel(new DefaultTableModel(getClassTable(), new String[] { "CRN", "Class", "Capacity", "Remaining", "Time", "Day", "Teacher", "Room" }));
		// scrollPane.setViewportView(addedTable);

		/**
		 * Button & logic for back button
		 */
//		JButton btnBack = new JButton("Back");
		JButton btnBack = new UniversalBackButton();
		
		btnBack.setBounds(10, 386, 128, 23);
//		btnBack.addActionListener(new ActionListener()
//		{
//			public void actionPerformed(ActionEvent e)
//			{
//				StudentRegistrationMain.replaceMainWindowContents(new GUIStudent());
//			}
//		});
		add(btnBack);

		/**
		 * Button & Logic for print button
		 */
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(498, 386, 99, 23);
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

			}// end of actionPerformed
		});// end of actionListener
		add(btnPrint);

		/**
		 * adds a login GUI
		 */
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		/**
		 * adds a JLabel named "View Schedule"
		 */
		JLabel lblCourseRemoval = new JLabel("View Schedule");
		lblCourseRemoval.setBounds(178, 54, 243, 23);
		lblCourseRemoval.setForeground(Color.GRAY);
		lblCourseRemoval.setFont(new Font("Verdana", Font.BOLD, 16));
		lblCourseRemoval.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCourseRemoval);	
		
		/**
		 * Displays Course Description by  double Clicking selected Course 
		 */
			addedTable.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent e)
				{
					if (e.getClickCount() == 2)
					{
						Course selectedCourse = StudentRegistrationMain.mainCourseManager.getCourse((int) addedTable.getModel().getValueAt(addedTable.getSelectedRow(), 0));

						JDialog popup = new JDialog(StudentRegistrationMain.mainWindow, selectedCourse.getTitle() + " - Description");
						popup.setBounds(200, 200, 447, 147);
						popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						popup.setLocationRelativeTo(null);
						popup.setResizable(false);
						popup.setVisible(true);
						popup.setAlwaysOnTop(true);
						
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
						
					}//end of if statement
				}//end of mouseClicked
			});//end of addMouseLisener
		
	}// end of GUIViewSchedule()
}// end of JPanel extension of GUIViewSchedule
