package com.github.fantastic_five;

import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.github.fantastic_five.GUI.GUILogin;
import com.github.fantastic_five.Logic.Course.Day;
import com.github.fantastic_five.Logic.Course;
import com.github.fantastic_five.Logic.CourseDatabase;
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
				while (courseIn.hasNextLine())
				{
					// Separates the output file into smaller bits
					String line = courseIn.nextLine();
					String[] lineParts = line.split("_");
					if (lineParts.length == 7)
					{
						// Handles a few parts of the Course obj
						String title = lineParts[0];
						String description = lineParts[1];
						int CRN = Integer.parseInt(lineParts[2]);
						int studentCap = Integer.parseInt(lineParts[3]);
						// Handles the Days
						HashSet<Day> days = new HashSet<>();
						String[] tempParts = lineParts[4].split("[\\W]");
						for (String s : tempParts)
						{
							if (Day.getDayFromName(s) != null)
								days.add(Day.getDayFromName(s));
						}
						// Handles Start time
						tempParts = lineParts[5].split(":");
						int startHour = Integer.parseInt(tempParts[0]);
						int startMinute = Integer.parseInt(tempParts[1]);
						// Handles end time
						tempParts = lineParts[6].split(":");
						int endHour = Integer.parseInt(tempParts[0]);
						int endMinute = Integer.parseInt(tempParts[1]);

						Course c = new Course(title, description, CRN, studentCap, days, startHour, startMinute, endHour, endMinute);
						CourseDatabase.addCourseToCourseList(c);
					}
				}
				courseIn.close();
			}
			catch (FileNotFoundException e)
			{
				// doing nothing with this because no file found is to be expected at least once
			}
		}
	}

	/**
	 * Creates a default user profile set that is absolutely necessary
	 */
	private static void createBaseUsers()
	{
		UserProfile teacherUser = new UserProfile("teacher", "pass", UserProfile.TEACHER, "Group", "Five", "Teacher");
		UserProfile studentUser = new UserProfile("student", "pass", UserProfile.STUDENT, "Group", "Five", "Student");
		UserProfile adminUser = new UserProfile("admin", "pass", UserProfile.ADMIN, "Group", "Five", "Administrator");
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