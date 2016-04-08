package com.github.fantastic_five.GUITeacher;

/**
 * @author Christian Phillips
 */
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILogStatus;

@SuppressWarnings("serial")
public class GUIPrint extends JPanel
{	
	// This GUI that shall display a print preview of the teacher's individual schedule	 
	public GUIPrint()
	{
		// Adds a scroll pane
		setBounds(0, 0, 618, 434);
		setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 587, 107);
		add(scrollPane);

		
		// Adds a table which will display list of courses the user has chosen
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, { null, null, null, null, null, null, null }, }, new String[] { "CRN", "Class", "Capacity", "Remaining", "Time", "Day", "Teacher", "Room" })
		{
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		});
		scrollPane.setViewportView(table);
		

		// Adds a scroll pane
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 178, 587, 197);
		add(scrollPane_1);

		// Adds a JLabel named "Print Preview"
		JLabel lblPrintPreview = new JLabel("Print Preview");
		lblPrintPreview.setForeground(Color.GRAY);
		lblPrintPreview.setFont(new Font("Verdana", Font.BOLD, 16));
		lblPrintPreview.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrintPreview.setBounds(177, 30, 243, 23);
		add(lblPrintPreview);
	}
}