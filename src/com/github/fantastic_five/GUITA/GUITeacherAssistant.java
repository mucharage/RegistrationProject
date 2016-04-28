package com.github.fantastic_five.GUITA;

/**
 * @author Fantastic Five (Jose Stovall)
 * A JPanel containing a tabbed view for both student and teacher abilities
 */

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.github.fantastic_five.GUIStudent.GUIStudent;
import com.github.fantastic_five.GUITeacher.GUITeacher;

@SuppressWarnings("serial")
public class GUITeacherAssistant extends JPanel
{
	public GUITeacherAssistant()
	{
		setBounds(0, 0, 568, 391);
		setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(0, 0, 618, 434);
		tabbedPane.addTab("Student", new GUIStudent());
		tabbedPane.addTab("Teacher", new GUITeacher());
		add(tabbedPane);
	}
}
