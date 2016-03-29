package com.github.fantastic_five;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.github.fantastic_five.GUI.GUILogin;
import com.github.fantastic_five.Logic.UserProfile;
import com.github.fantastic_five.Logic.UserProfileLib;

/**
 * @author Clark Stephen Group 5 Main class containing the Window itself and functions to control its contents
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
				// Put anything that needs to run in main here!
				createBaseUsers();
				createMainWindow();
				replaceMainWindowContents(new GUILogin());
			}
		};
		SwingUtilities.invokeLater(r);
	}

	/**
	 * Creates a default user profile set that is absolutely necessary
	 */
	private static void createBaseUsers()
	{
		UserProfile adminUser = new UserProfile("admin", "password", UserProfile.ADMIN, "Group", "Five", "Administrator");
		UserProfileLib.addUser(adminUser);
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