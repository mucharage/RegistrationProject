package com.github.fantastic_five;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.github.fantastic_five.GUI.GUILogin;
import com.github.fantastic_five.Logic.CourseManager;
import com.github.fantastic_five.Logic.MiscUtils;
import com.github.fantastic_five.Logic.UserProfile;

/**
 * @author Fantastic Five (Jose Stovall)
 */

public class StudentRegistrationMain
{
	// Kept public for a reason - may be needed by other classes
	public static JFrame mainWindow = new JFrame("FF Student Registration");
	public static Dimension mainWindowDimension = new Dimension(618, 458);
	public static ArrayList<UserProfile> loggedIn = new ArrayList<UserProfile>();
	public static CourseManager mainCourseManager = new CourseManager();

	public static void main(String[] args)
	{
		// Attempts to stylized the GUIs and Buttons using the Operating Systems' style
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			// We do nothing as there's nothing else we can do.
		}
		Runnable r = new Runnable()
		{
			@Override
			public void run()
			{
				MiscUtils.loadCoursesFromFile();
				MiscUtils.loadUsersFromFile();
				createMainWindow();
				replaceMainWindowContents(new GUILogin());
			}
		};
		SwingUtilities.invokeLater(r);
	}

	/**
	 * Creates the main window with required functionality
	 */
	private static void createMainWindow()
	{
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainWindow.setPreferredSize(mainWindowDimension);
		mainWindow.setResizable(false);

		mainWindow.pack();
		mainWindow.setVisible(true);
		mainWindow.setLocationRelativeTo(null);
	}

	/**
	 * @param newComponent
	 *            The Component to replace with in the main window
	 */
	public static void replaceMainWindowContents(Component newComponent)
	{
		// Gets all active Frames - this has been adapted specifically for the TA view
		Frame[] frames = Frame.getFrames();
		// If there is only one active window (i.e. the user is NOT a TA)
		if (frames.length == 1)
		{
			mainWindow.getContentPane().removeAll();
			mainWindow.getContentPane().add(newComponent);
			mainWindow.pack();
		}
		// Else they are a TA and will have multiple windows open
		else
		{
			for (Frame f : frames)
			{
				// Checks to see if the selected frame is the one currently being used, and IS a JFrame
				if (f.isActive() && f instanceof JFrame)
				{
					// Casts the frame to a temporary JFrame object (this is safe because of the instanceof check)
					JFrame tempFrame = (JFrame) f;
					// Does the same thing as above, but to the currently active window instead
					tempFrame.getContentPane().removeAll();
					tempFrame.getContentPane().add(newComponent);
					tempFrame.pack();
				}
			}
		}
	}
}