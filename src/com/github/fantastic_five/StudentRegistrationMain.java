package com.github.fantastic_five;

import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
	public static PrintWriter classOutput;
	public static PrintWriter userOutput;

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
				// Put anything that needs to run in main here!
				createBaseUsers();
				createMainWindow();
				initializeOutFiles();
				replaceMainWindowContents(new GUILogin());
			}
		};
		SwingUtilities.invokeLater(r);
	}
	
	private static void initializeOutFiles()
	{
		try
		{
			classOutput = new PrintWriter(new File("classes.dat"));
			userOutput = new PrintWriter(new File("users.dat"));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void clearUserOutput()
	{
		try
		{
			userOutput = new PrintWriter(new File("class.dat"));
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void clearClassOutput()
	{
		try
		{
			classOutput = new PrintWriter(new File("class.dat"));
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Creates a default user profile set that is absolutely necessary
	 */
	private static void createBaseUsers()
	{
		UserProfile adminUser = new UserProfile("admin", "password", UserProfile.ADMIN, "Group", "Five", "Administrator");
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