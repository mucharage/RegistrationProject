package com.github.fantastic_five.GUITA;

import java.awt.event.KeyEvent;

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
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		tabbedPane.addTab("Teacher", new GUITeacher());
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		add(tabbedPane);
	}
}
