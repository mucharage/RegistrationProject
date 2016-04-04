package com.github.fantastic_five.GUITA;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUILoggedIn;
import com.github.fantastic_five.GUIStudent.GUIStudent;
import com.github.fantastic_five.GUITeacher.GUITeacher;

@SuppressWarnings("serial")
public class GUITeacherAssistant extends JPanel
{
	public GUITeacherAssistant()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		// Adds the login panel to this window
		JPanel loginPanel = new GUILoggedIn();
		loginPanel.setBounds(0, 0, 618, 24);
		add(loginPanel);

		JButton btnTeacherView = new JButton("Teacher View");
		btnTeacherView.setBounds(223, 168, 177, 23);
		btnTeacherView.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				createTeacherWindow();
			}
		});
		add(btnTeacherView);

		JButton btnStudentView = new JButton("Student View");
		btnStudentView.setBounds(223, 202, 177, 23);
		btnStudentView.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				createStudentWindow();
			}
		});
		add(btnStudentView);

		// Panel label
		JLabel lblAdministration = new JLabel("Teacher's Assistant");
		lblAdministration.setForeground(Color.GRAY);
		lblAdministration.setFont(new Font("Verdana", Font.BOLD, 16));
		lblAdministration.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministration.setBounds(191, 97, 243, 23);
		add(lblAdministration);
	}

	public void createStudentWindow()
	{
		JFrame studentView = new JFrame("Student Perspective");

		studentView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		studentView.setPreferredSize(StudentRegistrationMain.mainWindowDimension);
		studentView.setResizable(false);
		studentView.pack();
		studentView.setVisible(true);
		studentView.setLocation(0, 133);
		studentView.getContentPane().add(new GUIStudent());
		studentView.pack();
	}

	public void createTeacherWindow()
	{
		JFrame teacherView = new JFrame("Teacher Perspective");
		teacherView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		teacherView.setPreferredSize(StudentRegistrationMain.mainWindowDimension);
		teacherView.setResizable(false);
		teacherView.pack();
		teacherView.setVisible(true);
		teacherView.setLocation(750,133);
		teacherView.getContentPane().add(new GUITeacher());
		teacherView.pack();
	}
}