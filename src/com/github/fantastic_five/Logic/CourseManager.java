package com.github.fantastic_five.Logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

public class CourseManager
{
	private TreeSet<Course> courseOfferings;
	private Set<Connector> network;
	private PrintStream courseOutput;

	public static final int COURSE_INSTRUCTOR_RELATIONSHIP = Connector.COURSE_INSTRUCTOR_RELATIONSHIP;
	public static final int COURSE_LEARNER_RELATIONSHIP = Connector.COURSE_LEARNER_RELATIONSHIP;

	public static final int MAX_COURSES_PER_LEARNER = 5;

	/**
	 * Constructs a new instance of CourseManager
	 */
	public CourseManager()
	{
		courseOfferings = new TreeSet<>(new Comparator<Course>()
		{
			public int compare(Course arg0, Course arg1)
			{
				int rVal = Integer.compare(arg0.getCRN(), arg1.getCRN());
				return rVal;
			}
		});

		network = new HashSet<>();
	}

	/**
	 * Gets the full list of course offerings
	 * 
	 * @return the full list of course offerings
	 */
	public TreeSet<Course> getCourses()
	{
		return this.courseOfferings;
	}

	/**
	 * Checks the catalog to see if it contains a course with a specified CRN
	 * 
	 * @param course
	 *            The CRN that is being searched for
	 * @return true iff the catalog contains a course e, such that (e.getCRN() == crn)
	 */
	public boolean containsCourse(int crn)
	{

		boolean rVal;

		Course testCourse = dummyCourse(crn);

		rVal = courseOfferings.contains(testCourse);

		return rVal;
	}

	/**
	 * Remove a specified course from the catalog if the catalog contains a course with a CRN which is identical to the one specified
	 * 
	 * @param crn
	 *            The CRN of the course to be removed
	 * @return true iff the course is successfully removed from the catalog
	 */
	public boolean removeCourse(int crn)
	{
		boolean rVal;

		Course dummy = dummyCourse(crn);

		if (courseOfferings.contains(dummy))
		{
			rVal = true;

			courseOfferings.remove(dummy);
			
			updateCourseListFile();

			network.removeIf(new Predicate<Connector>()
			{
				public boolean test(Connector connector)
				{
					return (connector.courseCRN == crn);
				}
			});
		}
		else
		{
			rVal = false;
		}

		return rVal;
	}

	/**
	 * Add a specified course to the catalog if the catalog does not contain a course with a CRN which is identical to that of the specified course
	 * 
	 * @param addition
	 *            The course to be added
	 * @return true iff the course is successfully added to the catalog
	 */
	public boolean addCourse(Course addition)
	{
		boolean rVal;

		if (courseOfferings.contains(addition))
		{
			rVal = false;
		}
		else
		{
			rVal = true;
			courseOfferings.add(addition);
			updateCourseListFile();
		}

		return rVal;
	}

	/**
	 * Attempts to return a course in the catalog with a specified CRN.
	 * 
	 * @param crn
	 *            The CRN of the desired course
	 * @return The course with the specified CRN, or null if no such course exists in the catalog
	 */
	public Course getCourse(int crn)
	{
		Course rVal;

		Course testKey = dummyCourse(crn);
		Course possibleRVal = courseOfferings.floor(testKey);

		if (possibleRVal.equals(testKey))
		{
			rVal = possibleRVal;
		}
		else
		{
			rVal = null;
		}

		return rVal;
	}

	/**
	 * Returns a set of courses representing the course schedule of a specified learner
	 * 
	 * @param learner
	 *            The UserProfile whose course schedule is being viewed. Its permLevel must be equal to STUDENT or TA
	 * @return A set of courses representing the course schedule of a specified learner, or null if learner's permLevel is invalid
	 */
	public Set<Course> getCoursesWithLearner(UserProfile learner)
	{
		Set<Course> rVal;

		if ((learner.getPermLevel() == UserProfile.STUDENT) || (learner.getPermLevel() == UserProfile.TA))
		{
			rVal = new HashSet<Course>();

			for (Connector e : network)
			{
				if (e.relationship == COURSE_LEARNER_RELATIONSHIP)
				{
					if (e.person.equals(learner))
					{
						rVal.add(getCourse(e.courseCRN));
					}
				}
			}
		}
		else
		{
			rVal = null;
		}

		return rVal;
	}

	/**
	 * Returns a set of UserProfiles representing the people who are enrolled in the course with a specified CRN
	 * 
	 * @param courseCRN
	 *            The CRN of the course that is being looked at
	 * @return A set of UserProfiles representing the people who are enrolled in the course with a specified CRN, or null iff (!this.containsCourse(courseCRN))
	 */
	public Set<UserProfile> getLearnersWithCourse(int courseCRN)
	{
		Set<UserProfile> rVal;

		if (containsCourse(courseCRN))
		{
			rVal = new HashSet<UserProfile>();

			for (Connector e : network)
			{
				if (e.relationship == COURSE_LEARNER_RELATIONSHIP)
				{
					if (e.courseCRN == courseCRN)
					{
						rVal.add(e.person);
					}
				}
			}
		}
		else
		{
			rVal = null;
		}

		return rVal;
	}

	/**
	 * Attempts to enroll a specified person in a course with a specified crn. Fails if the learner's permLevel is not STUDENT or TA, no courses with the CRN exist in the catalog, the person is already enrolled in the maximum number of classes allowed, or the desired course is full.
	 * 
	 * @param learner
	 *            The person who is being enrolled
	 * @param courseCRN
	 *            The crn of the course which is being enrolled in
	 * @return true iff the learner is successfully enrolled in the course
	 */
	public boolean addLearnerToCourse(UserProfile learner, int courseCRN)
	{
		boolean rVal = false;
		if ((learner.getPermLevel() == UserProfile.STUDENT) || (learner.getPermLevel() == UserProfile.TA))
		{
			if (containsCourse(courseCRN))
			{
				Course course = getCourse(courseCRN);

				Connector connector = new Connector(COURSE_LEARNER_RELATIONSHIP, courseCRN, learner);
				if (!network.contains(connector) && !network.contains(new Connector(COURSE_INSTRUCTOR_RELATIONSHIP, courseCRN, learner)))
				{
					Set<Course> coursesWithLearner = getCoursesWithLearner(learner);
					Set<UserProfile> learnersWithCourse = getLearnersWithCourse(courseCRN);

					if ((coursesWithLearner.size() < MAX_COURSES_PER_LEARNER) && (learnersWithCourse.size() < course.getStudentCap()))
					{
						rVal = true;
						network.add(connector);
					}
				}
			}
		}

		if (rVal)
		{
			// update network file
		}
		return rVal;
	}

	/**
	 * Attempts to remove a specified person in a course with a specified crn.
	 * 
	 * @param learner
	 *            The person being removed
	 * @param courseCRN
	 *            The course the person is being dropped from
	 * @return true iff the learner was present and then removed from the course
	 */
	public boolean removeLearnerFromCourse(UserProfile learner, int courseCRN)
	{
		boolean rVal = false;

		Connector connector = new Connector(COURSE_LEARNER_RELATIONSHIP, courseCRN, learner);
		if (network.contains(connector))
		{
			rVal = true;
			network.remove(connector);
		}

		if (rVal)
		{
			// update network file
		}
		return rVal;
	}

	// public boolean addLearnerToCourse(UserProfile learner, int courseCRN)
	// {
	// boolean rVal;
	// if((learner.getPermLevel() == UserProfile.STUDENT) || (learner.getPermLevel() == UserProfile.TA))
	// {
	// if (containsCourse(courseCRN))
	// {
	// Course course = getCourse(courseCRN);
	//
	// int learnersEnrolledInCourse = 0;
	// int coursesLearnerIsTaking = 0;
	//
	// for(Connector e: network)
	// {
	// if(e.relationship == COURSE_LEARNER_RELATIONSHIP)
	// {
	// if (e.courseCRN == courseCRN)
	// {
	// learnersEnrolledInCourse +=1;
	// if(e.person.equals(learner))
	// {
	// rVal = false;
	// break;
	// }
	// }
	// else if(e.person.equals(learner))
	// {
	// coursesLearnerIsTaking += 1;
	// }
	// }
	// }
	//
	// if((learnersEnrolledInCourse < course.getStudentCap()) &&
	// (coursesLearnerIsTaking < MAX_COURSES_PER_LEARNER))
	// {
	// rVal = true;
	// network.add(new Connector(COURSE_LEARNER_RELATIONSHIP ,courseCRN, learner));
	// }
	// else
	// {
	// rVal = false;
	// }
	// }
	// else
	// {
	// rVal = false;
	// }
	// }
	// else
	// {
	// rVal = false;
	// }
	//
	// return rVal;
	// }

	private Course dummyCourse(int crn)
	{
		Course rVal = new Course(null, null, crn, 0, null, 0, 0, 0, 1);
		return rVal;
	}

	private static class Connector
	{
		public final int relationship;
		public final int courseCRN;
		public final UserProfile person;

		public static final int COURSE_INSTRUCTOR_RELATIONSHIP = 0;
		public static final int COURSE_LEARNER_RELATIONSHIP = 1;

		public Connector(int relationship, int courseCRN, UserProfile person)
		{
			this.relationship = relationship;
			this.courseCRN = courseCRN;
			this.person = person;
		}

		public boolean equals(Object o)
		{
			boolean rVal;
			if (o instanceof Connector)
			{
				Connector other = (Connector) o;

				rVal = (this.relationship == other.relationship);
				rVal = (this.courseCRN == other.courseCRN) && rVal;
				rVal = (this.person.equals(other.person)) && rVal;
			}
			else
			{
				rVal = false;
			}
			return rVal;
		}
	}

	/**
	 * Updates the course list file by resetting it and re-writing the contents
	 */
	public void updateCourseListFile()
	{
		try
		{
			courseOutput = new PrintStream(new File(MiscUtils.getCoursesFileName()));
			for (Course c : courseOfferings)
				courseOutput.println(c.toString());
		}
		catch (FileNotFoundException e)
		{
		}
	}
}
