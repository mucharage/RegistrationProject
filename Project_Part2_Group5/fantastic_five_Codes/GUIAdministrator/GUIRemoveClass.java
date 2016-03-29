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
import com.github.fantastic_five.GUIMisc.GUILoggedIn;
import com.github.fantastic_five.Logic.Lib;

@SuppressWarnings("serial")
public class GUIRemoveClass extends JPanel
{
	// Private instance variables
	private JTextField fieldCRN;

	public GUIRemoveClass()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

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
				popup.setPreferredSize(new Dimension(452, 186));
				popup.setResizable(false);

				JPanel GUI = new JPanel();
				GUI.setLayout(null);

				// Confirmation label
				JLabel lblAreYouSure = new JLabel("Are you sure? Cannot be undone!");
				lblAreYouSure.setForeground(Color.RED);
				lblAreYouSure.setFont(new Font("Verdana", Font.BOLD, 16));
				lblAreYouSure.setBounds(86, 24, 294, 52);
				GUI.add(lblAreYouSure);

				// Yes button should remove the CRN from the list
				JButton btnYes = new JButton("No");
				btnYes.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						popup.dispose();
					}
				});
				btnYes.setBounds(234, 96, 89, 23);
				GUI.add(btnYes);

				// No button closes the window
				JButton btnNo = new JButton("Yes");
				btnNo.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						Lib.removeMatchingCRN(Integer.parseInt(fieldCRN.getText()));
						popup.dispose();
					}
				});
				btnNo.setBounds(135, 96, 89, 23);
				GUI.add(btnNo);

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
		JPanel loginPanel = new GUILoggedIn();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// Panel Title, basically
		JLabel lblCourseRemoval = new JLabel("Course Removal");
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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 136, 545, 59);
		add(scrollPane);

		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, }, new String[] { "CRN", "Class", "Capacity", "Remaining", "Time", "Day", "Teacher", "Room" }));
		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(41, 247, 545, 131);
		add(scrollPane_1);
	}

}
