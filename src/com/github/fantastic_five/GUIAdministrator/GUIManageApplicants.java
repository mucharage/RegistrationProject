package com.github.fantastic_five.GUIAdministrator;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GUIManageApplicants extends JPanel
{
	public GUIManageApplicants()
	{
		// Panel label, essentially
		JLabel lblAdministration = new JLabel("Manage Applications");
		lblAdministration.setForeground(Color.GRAY);
		lblAdministration.setFont(new Font("Verdana", Font.BOLD, 16));
		lblAdministration.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministration.setBounds(0, 83, 618, 23);
		add(lblAdministration);
	}
}