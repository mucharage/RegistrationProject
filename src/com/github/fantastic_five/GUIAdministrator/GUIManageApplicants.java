package com.github.fantastic_five.GUIAdministrator;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUI.UneditableTableModel;
import com.github.fantastic_five.GUI.UniversalBackButton;
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.PendingApplication;
import com.github.fantastic_five.Logic.UserProfile;

@SuppressWarnings("serial")
public class GUIManageApplicants extends JPanel
{
	private JTable table;
	private String[] headers = new String[] { "Last", "First", "Middle", "UserID" };

	public GUIManageApplicants()
	{
		setBounds(0, 0, 618, 434);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 587, 311);
		add(scrollPane);

		table = new JTable();
		table.setModel(new UneditableTableModel(getTable(), headers));
		table.setAutoCreateRowSorter(true);

		scrollPane.setViewportView(table);

		// Adds the back button
		JButton btnBack = new UniversalBackButton();
		btnBack.setBounds(10, 386, 128, 23);
		add(btnBack);

		// Adds the login panel
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		// Panel label, essentially
		JLabel lblAdministration = new JLabel("Pending Applicants ");
		lblAdministration.setForeground(Color.GRAY);
		lblAdministration.setFont(new Font("Verdana", Font.BOLD, 16));
		lblAdministration.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministration.setBounds(0, 30, 618, 23);
		add(lblAdministration);

		JButton btnDeny = new JButton("Deny");
		btnDeny.setBounds(469, 386, 128, 23);
		btnDeny.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int[] rowsSel = table.getSelectedRows();
				int tally = 0;
				for (int row : rowsSel)
				{
					String userName = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row - tally), 3);
					StudentRegistrationMain.pendingApplications.removeApplication(userName);
					table.setModel(new UneditableTableModel(getTable(), headers));
					tally++;
				}
			}
		});
		add(btnDeny);

		JButton btnAccept = new JButton("Accept");
		btnAccept.setBounds(331, 386, 128, 23);
		btnAccept.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int[] rowsSel = table.getSelectedRows();
				int tally = 0;
				for (int row : rowsSel)
				{
					String userName = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row - tally), 3);
					PendingApplication app = StudentRegistrationMain.pendingApplications.getPendingApplication(userName);
					if (app != null)
					{
						UserProfile newProf = new UserProfile(app, UserProfile.STUDENT);
						StudentRegistrationMain.profiles.addUser(newProf);
						StudentRegistrationMain.financialRecords.addUser(newProf);
						StudentRegistrationMain.pendingApplications.removeApplication(userName);
						table.setModel(new UneditableTableModel(getTable(), headers));
					}
					tally++;
				}
			}
		});
		add(btnAccept);
	}

	/**
	 * @return a table with all users except for the currently logged in user
	 */
	Object[][] getTable()
	{
		Set<PendingApplication> applicants = StudentRegistrationMain.pendingApplications.copyDatabase();
		Object[][] cells = new Object[applicants.size()][headers.length];
		int row = 0;
		for (PendingApplication app : applicants)
		{
			cells[row][0] = app.getLastName();
			cells[row][1] = app.getFirstName();
			cells[row][2] = app.getMiddleName();
			cells[row][3] = app.getUserID();
			row++;
		}

		return cells;
	}
}