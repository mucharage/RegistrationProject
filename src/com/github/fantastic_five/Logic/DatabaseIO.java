package com.github.fantastic_five.Logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.github.fantastic_five.StudentRegistrationMain;

public class DatabaseIO
{
	public static String fileLocation = getCoursesFileName();
	
	public static void serializeEverything()
	{		
		try
		{
			FileOutputStream fileOut = new FileOutputStream(fileLocation);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(new DatabasePackage(StudentRegistrationMain.mainCourseManager, StudentRegistrationMain.profiles, StudentRegistrationMain.financialRecords));
			out.close();
			fileOut.close();
		}
		catch (IOException i)
		{
			i.printStackTrace();
			return;
		}
	}
	
	public static void deserializeEverything()
	{
		try
		{
			FileInputStream fileIn = new FileInputStream(fileLocation);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			DatabasePackage databases = (DatabasePackage) in.readObject();
			in.close();
			fileIn.close();
			
			StudentRegistrationMain.profiles = databases.profiles;
			StudentRegistrationMain.mainCourseManager = databases.courseManager;
			StudentRegistrationMain.financialRecords = databases.financialRecords;
		}
		catch (IOException i)
		{
			i.printStackTrace();
			return;
		}
		catch (ClassNotFoundException c)
		{
			c.printStackTrace();
			return;
		}
	}
	
	private static class DatabasePackage implements Serializable
	{
		private static final long serialVersionUID = -4633912253381011843L;
		
		public CourseManager courseManager;
		public UserProfileDatabase profiles;
		public FinancialRecordsOffice financialRecords;
		
		public DatabasePackage(CourseManager courseManager, UserProfileDatabase profiles, FinancialRecordsOffice financialRecords)
		{	
			this.courseManager = courseManager;
			this.profiles = profiles;
			this.financialRecords = financialRecords;
		}
	}
	
	/**
	 * @return A String with the proper location of the .dat file
	 */
	private static String getCoursesFileName()
	{
		// Gets the OS name
		String OS = System.getProperty("os.name").toLowerCase();

		// Puts the file in %appdata% if Windows
		if (OS.indexOf("win") >= 0)
			return System.getenv("APPDATA") + File.separator + "big.dat";
		// Puts the file in Application Support if OSX
		else if (OS.indexOf("mac") >= 0)
			return System.getProperty("user.home") + File.separator + "Library" + File.separator + "Application Support" + File.separator + "Student Registration" + File.separator + "big.dat";
		// Puts the file in Temp if Linux
		else if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") >= 0)
			return "/tmp/big.dat";
		else
			return "big.dat";
	}
}
