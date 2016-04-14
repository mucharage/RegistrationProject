package com.github.fantastic_five.Logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

public class SerializationTest
{
	public static void main(String[] args)
	{
		HashSet<Course.Day> days = new HashSet<>();
		days.add(Course.Day.TUESDAY);
		days.add(Course.Day.THURSDAY);
		
		Course testCourse1 = new Course("CPSC 2100", "", 123456, 30, days, 13, 40, 14, 55);
		Course testCourse2;
		
		CourseManager testCourseManager1 = new CourseManager();
		CourseManager testCourseManager2;
		
		try
		{
			FileOutputStream fileOut = new FileOutputStream("testcourse.txt");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(testCourse1);
			out.close();
			fileOut.close();
			
			testCourseManager1.addCourse(testCourse1);
			
			fileOut = new FileOutputStream("testCourseManager.txt");
			out = new ObjectOutputStream(fileOut);
			out.writeObject(testCourseManager1);
			out.close();
			fileOut.close();

		}
		catch (IOException i)
		{
			i.printStackTrace();
		}

		try
		{
			FileInputStream fileIn = new FileInputStream("testCourse.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			testCourse2 = (Course) in.readObject();
			in.close();
			fileIn.close();
			
			fileIn = new FileInputStream("testCourseManager.txt");
			in = new ObjectInputStream(fileIn);
			testCourseManager2 = (CourseManager) in.readObject();
			in.close();
			fileIn.close();
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
		
		System.out.println(testCourse1);
		System.out.println(testCourse2);
	}
}
