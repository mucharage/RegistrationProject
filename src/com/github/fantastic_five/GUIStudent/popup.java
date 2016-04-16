package com.github.fantastic_five.GUIStudent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class popup
{

	public popup()
	{
		JFrame popup = new JFrame("Course Desctiption");	
		popup.setBounds(200,200,307,107);
		popup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popup.setLocationRelativeTo(null);
		popup.getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 11, 281, 56);
		popup.getContentPane().add(textPane);
		textPane.setEditable(false);
		popup.setResizable(false);
		popup.setVisible(true);
		
		
	}
}
