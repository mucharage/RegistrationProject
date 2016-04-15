package com.github.fantastic_five.Logic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.github.fantastic_five.StudentRegistrationMain;

public class DatabasePackage implements Serializable
{
	private static final long serialVersionUID = 3196174762246889899L;
	public CourseManager courseManager;
	public UserProfileDatabase profiles;
	public FinancialRecordsOffice financialRecords;

	public DatabasePackage(CourseManager courseManager, UserProfileDatabase profiles, FinancialRecordsOffice financialRecords)
	{
		this.courseManager = courseManager;
		this.profiles = profiles;
		this.financialRecords = financialRecords;
	}

	public static void serializeData()
	{		
		try
		{
			FileOutputStream fileOut = new FileOutputStream("big.dat");
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
}
