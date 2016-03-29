package com.github.fantastic_five.GUIMisc;
/**
 * @author Fantastic Five (Jose Stovall)
 * A JPanel displaying all teachers in the university
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.fantastic_five.StudentRegistrationMain;
import com.github.fantastic_five.GUI.GUILogin;
import com.github.fantastic_five.GUIAdministrator.GUIAdmin;
import com.github.fantastic_five.GUIStudent.GUIStudent;
import com.github.fantastic_five.GUITeacher.GUITeacher;

@SuppressWarnings("serial")
public class GUIWIP extends JPanel
{
	public GUIWIP()
	{
		setLayout(null);
		setBounds(0, 0, 618, 434);
		
		JLabel lblThisGuiIs = new JLabel("This GUI is a WIP Stand-In");
		lblThisGuiIs.setForeground(Color.RED);
		lblThisGuiIs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblThisGuiIs.setBounds(188, 42, 225, 32);
		add(lblThisGuiIs);
		
		JButton btnViewLoginGui = new JButton("View Login GUI");
		btnViewLoginGui.setBounds(101, 146, 122, 23);
		btnViewLoginGui.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUILogin());
			}
		});
		add(btnViewLoginGui);
		
		JButton btnViewAdminGui = new JButton("View Admin GUI");
		btnViewAdminGui.setBounds(101, 241, 122, 23);
		btnViewAdminGui.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIAdmin());
			}
		});
		add(btnViewAdminGui);
		
		JButton btnViewStudentGui = new JButton("View Student GUI");
		btnViewStudentGui.setBounds(373, 241, 117, 23);
		btnViewStudentGui.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUIStudent());
			}
		});
		add(btnViewStudentGui);
		
		JButton btnViewTeacherGui = new JButton("View Teacher GUI");
		btnViewTeacherGui.setBounds(373, 146, 117, 23);
		btnViewTeacherGui.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				StudentRegistrationMain.replaceMainWindowContents(new GUITeacher());
			}
		});
		add(btnViewTeacherGui);
	}
}
