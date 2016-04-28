package com.github.fantastic_five;

import java.util.TreeSet;

import javax.swing.SwingUtilities;

import com.github.fantastic_five.Logic.Course;
import com.github.fantastic_five.Logic.Course.Day;
import com.github.fantastic_five.Logic.CourseManager;
import com.github.fantastic_five.Logic.FinancialRecordsOffice;
import com.github.fantastic_five.Logic.UserProfile;
import com.github.fantastic_five.Logic.UserProfileDatabase;

public class StudentRegistrationDemo
{
	public static void main(String[] args)
	{
		Runnable r = new Runnable()
		{
			@Override
			public void run()
			{
				StudentRegistrationMain.main(args);

				UserProfileDatabase profiles = StudentRegistrationMain.profiles;
				CourseManager mainCourseManager = StudentRegistrationMain.mainCourseManager;
				FinancialRecordsOffice office = StudentRegistrationMain.financialRecords;

				TreeSet<Day> MWF = new TreeSet<Day>();
				TreeSet<Day> TR = new TreeSet<Day>();
				TR.add(Day.TUESDAY);
				TR.add(Day.THURSDAY);
				MWF.add(Day.MONDAY);
				MWF.add(Day.WEDNESDAY);
				MWF.add(Day.FRIDAY);

				profiles.addUser(new UserProfile("student", "pass", UserProfile.STUDENT, "Christian", "", "Phillips"));
				profiles.addUser(new UserProfile("teacher", "pass", UserProfile.TEACHER, "Steven", "", "Hullander"));
				profiles.addUser(new UserProfile("admin", "pass", UserProfile.ADMIN, "Stephen", "", "Clark"));
				profiles.addUser(new UserProfile("ta", "pass", UserProfile.TA, "Alay", "", "Patel"));
				profiles.addUser(new UserProfile("mward", "pass", UserProfile.TEACHER, "Michael", "", "Ward"));

				office.addUser(new UserProfile("student", "pass", UserProfile.STUDENT, "Christian", "", "Phillips"));
				office.addUser(new UserProfile("ta", "pass", UserProfile.TA, "Alay", "", "Patel"));

				mainCourseManager.addCourse(new Course("ENGL1000", "English I: Introduction to English", mainCourseManager.generateNewCRN(1000, 9999), 30, MWF, 9, 0, 9, 50));
				mainCourseManager.addCourse(new Course("ENGL2000", "English II: English Composition", mainCourseManager.generateNewCRN(1000, 9999), 26, TR, 14, 0, 15, 40));
				mainCourseManager.addCourse(new Course("SPAN1000", "Spanish I: Introduction to Spanish", mainCourseManager.generateNewCRN(1000, 9999), 21, MWF, 10, 0, 10, 50));
				mainCourseManager.addCourse(new Course("CPSC2100", "Software Design", mainCourseManager.generateNewCRN(1000, 9999), 17, TR, 13, 40, 14, 55));
				mainCourseManager.addCourse(new Course("CPSC2800", "Introduction to Operating System", mainCourseManager.generateNewCRN(1000, 9999), 37, TR, 10, 40, 11, 55));
			}
		};
		SwingUtilities.invokeLater(r);
	}
}