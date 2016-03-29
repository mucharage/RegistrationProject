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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUIMisc.GUIWIP;

@SuppressWarnings("serial")
public class GUITeacher extends JPanel
{
	/**
	 * The GUITeacher class extends the JPanel. This is the first 
	 * display that the Teacher user will see when he/she logs in
	 * to his/her account.
	 * @return A window Containing the main actions a Teacher can do 
	 */
	public GUITeacher()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);

		//Button & Logic for View Course
		JButton btnView = new JButton("View Course");
		btnView.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIViewCourse());
			}
		});

		btnView.setBounds(178, 186, 243, 23);
		add(btnView);

		// Button & logic for removing courses
		JButton btnAddremoveCourse = new JButton("Add / Remove Course");
		btnAddremoveCourse.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAddRemoveClass());
			}
		});

		btnAddremoveCourse.setBounds(178, 220, 243, 23);
		add(btnAddremoveCourse);

		// Button for viewing schedules
		JButton btnViewSchedule = new JButton("View Schedule");
		btnViewSchedule.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIViewSchedule());
			}
		});
		btnViewSchedule.setBounds(178, 254, 243, 23);
		add(btnViewSchedule);

		// Panel label
		JLabel lblTeacher = new JLabel("Teacher");
		lblTeacher.setBounds(232, 78, 46, 14);
		lblTeacher.setForeground(Color.GRAY);
		lblTeacher.setFont(new Font("Verdana", Font.BOLD, 16));
		lblTeacher.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeacher.setBounds(178, 96, 243, 23);
		add(lblTeacher);

		//@TODO: Remove this later:
		JButton btnBackToWip = new JButton("Back to WIP Menu");
		btnBackToWip.setBounds(489, 400, 119, 23);
		btnBackToWip.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIWIP());
			}
		});		
		add(btnBackToWip);
		
	}
}
