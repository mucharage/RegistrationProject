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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUI.UniversalBackButton;
import com.github.fantastic_five.GUIMisc.GUILogStatus;
import com.github.fantastic_five.Logic.UserProfile;

@SuppressWarnings("serial")
public class GUIChangePerm extends JPanel
{
	JTable table;
	String[] headers = new String[] { "Last", "First", "UserID", "Perm Level" };

	public GUIChangePerm()
	{
		setBounds(0, 0, 618, 434);
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 64, 587, 311);
		add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(getTable(), headers)
		{
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		});

		// Adds the back button
		JButton btnBack = new UniversalBackButton();
		btnBack.setBounds(10, 386, 128, 23);
		add(btnBack);

		// Adds the login panel
		JPanel loginPanel = new GUILogStatus();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		JLabel lblNewPermissionLevel = new JLabel("New Permission Level:");
		lblNewPermissionLevel.setBounds(304, 390, 106, 14);
		add(lblNewPermissionLevel);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, UserProfile.STUDENT, UserProfile.ADMIN, 1));
		spinner.setBounds(420, 387, 29, 20);
		add(spinner);

		JButton btnApply = new JButton("Apply");
		btnApply.setBounds(469, 386, 128, 23);
		btnApply.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int rowSel = table.getSelectedRow();
				if (rowSel > -1)
				{
					UserProfile selectedUser = StudentRegistrationMain.profiles.getUserProfile((String) table.getModel().getValueAt(rowSel, 2));
					if (selectedUser != null)
					{
					}
				}
			}
		});
		add(btnApply);
		
		// Panel label, essentially
		JLabel lblAdministration = new JLabel("Change Permission Levels");
		lblAdministration.setForeground(Color.GRAY);
		lblAdministration.setFont(new Font("Verdana", Font.BOLD, 16));
		lblAdministration.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministration.setBounds(0, 30, 618, 23);
		add(lblAdministration);
	}

	public Object[][] getTable()
	{
		Set<UserProfile> users = StudentRegistrationMain.profiles.copyUserProfiles();
		Object[][] cells = new Object[users.size()][headers.length];
		int row = 0;
		for (UserProfile u : users)
		{
			if (!u.equals(StudentRegistrationMain.getCurrentLoggedInUser()))
			{
				cells[row][0] = u.getLastName();
				cells[row][1] = u.getFirstName();
				cells[row][2] = u.getUserID();
				cells[row][3] = u.getPermLevel();
				row++;
			}
		}
		return cells;
	}
}