package com.github.fantastic_five;

import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.github.fantastic_five.GUI.GUILogin;
import com.github.fantastic_five.Logic.UserProfile;
import com.github.fantastic_five.Logic.UserProfileDatabase;

/**
 * @author Fantastic Five (Jose Stovall)
 */

public class StudentRegistrationMain
{
	// Kept public for a reason - may be needed by other classes
	public static JFrame mainWindow = new JFrame("FF Student Registration");
	public static Dimension mainWindowDimension = new Dimension(618, 458);
	public static ArrayList<UserProfile> loggedIn = new ArrayList<UserProfile>();

	public static void main(String[] args)
	{
		// Attempts to stylized the GUIs and Buttons using the Operating Systems' style
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e)
		{
			System.out.println("There was an error using the System's Look and Feel");
			System.out.println(e.getMessage());
		}
		Runnable r = new Runnable()
		{
			@Override
			public void run()
			{
				readInFromFiles();
				createBaseUsers();
				createMainWindow();
				replaceMainWindowContents(new GUILogin());
			}
		};
		SwingUtilities.invokeLater(r);
	}

	/**
	 * initializes variables using data stored in our .dat files
	 */
	private static void readInFromFiles()
	{
		File coursesFile = new File("courses.dat");
		if (coursesFile != null)
		{
			try
			{
				Scanner courseIn = new Scanner(new File("courses.dat"));
				while(courseIn.hasNextLine())
				{
					String line = courseIn.nextLine();
					String[] lineParts = line.split(" | ");
					String title = lineParts[0];
					String description = lineParts[1];
					int CRN = Integer.parseInt(lineParts[2]);
					int cap = Integer.parseInt(lineParts[3]);
					
				}
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Creates a default user profile set that is absolutely necessary
	 */
	private static void createBaseUsers()
	{
		UserProfile teacherUser = new UserProfile("teacher", "password", UserProfile.TEACHER, "Group", "Five", "Teacher");
		UserProfile studentUser = new UserProfile("student", "password", UserProfile.STUDENT, "Group", "Five", "Student");
		UserProfile adminUser = new UserProfile("admin", "password", UserProfile.ADMIN, "Group", "Five", "Administrator");
		UserProfileDatabase.addUser(teacherUser);
		UserProfileDatabase.addUser(studentUser);
		UserProfileDatabase.addUser(adminUser);
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
		mainWindow.getContentPane().removeAll();
		mainWindow.getContentPane().add(newComponent);
		mainWindow.pack();
	}
}