package com.github.fantastic_five.Logic;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

/**
 * 
 * @author Fantastic Five (Stephen Clark)
 *
 */
public class CourseManager implements Serializable
{
	private static final long serialVersionUID = 217804861480820004L;

	private TreeSet<Course> courseOfferings;
	private TreeSet<Connector> network;

	private static final int COURSE_INSTRUCTOR_RELATIONSHIP = Connector.COURSE_INSTRUCTOR_RELATIONSHIP;
	private static final int COURSE_LEARNER_RELATIONSHIP = Connector.COURSE_LEARNER_RELATIONSHIP;

	private static final int MAX_COURSES_PER_LEARNER = 5;

	/**
	 * Constructs a new instance of CourseManager
	 */
	public CourseManager()
	{
		courseOfferings = new TreeSet<>(new CourseComparator());

		network = new TreeSet<>();
	}

	/**
	 * Return a treeSet containing all of the courses being offered, ordered by CRN
	 * 
	 * @return The treeSet of all courses being offered, ordered by CRN
	 */
	@SuppressWarnings("unchecked")
	public TreeSet<Course> copyCourseOfferings()
	{
		TreeSet<Course> rVal = (TreeSet<Course>) courseOfferings.clone();
		return rVal;
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
		Course dummy = dummyCourse(crn);
		return courseOfferings.contains(dummy);
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
		boolean rVal = false;

		Course dummy = dummyCourse(crn);

		if (courseOfferings.contains(dummy))
		{
			rVal = true;

			courseOfferings.remove(dummy);

			network.removeIf(new Predicate<Connector>()
			{
				public boolean test(Connector connector)
				{
					return (connector.courseCRN == crn);
				}
			});

			DatabaseIO.serializeEverything();
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
		boolean rVal = false;

		if (!courseOfferings.contains(addition))
		{
			rVal = true;
			courseOfferings.add(addition);
			DatabaseIO.serializeEverything();
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
		Course rVal = null;

		Course testKey = dummyCourse(crn);
		Course possibleRVal = courseOfferings.floor(testKey);

		if (possibleRVal != null && possibleRVal.equals(testKey))
		{
			rVal = possibleRVal;
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
		Set<Course> rVal = null;

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

		return rVal;
	}

	/**
	 * Returns a set of courses representing the teaching schedule of a specified instructor
	 * 
	 * @param instructor
	 *            The UserProfile whose teaching schedule is being viewed. Its permLevel must be greater than or equal to TA and less than or equal to ADMIN
	 * @return A set of courses representing the teaching schedule of a specified instructor, or null if instructor's permLevel is invalid
	 */
	public Set<Course> getCoursesWithInstructor(UserProfile instructor)
	{
		Set<Course> rVal = null;

		if ((instructor.getPermLevel() >= UserProfile.TA) && (instructor.getPermLevel() <= UserProfile.ADMIN))
		{
			rVal = new HashSet<Course>();

			for (Connector e : network)
			{
				if (e.relationship == COURSE_INSTRUCTOR_RELATIONSHIP)
				{
					if (e.person.equals(instructor))
					{
						rVal.add(getCourse(e.courseCRN));
					}
				}
			}
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
		Set<UserProfile> rVal = null;

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

		return rVal;
	}

	/**
	 * Returns a set of UserProfiles representing the people who are teaching the course with a specified CRN
	 * 
	 * @param courseCRN
	 *            The CRN of the course that is being looked at
	 * @return A set of UserProfiles representing the people who are teaching the course with a specified CRN, or null iff (!this.containsCourse(courseCRN))
	 */
	@Deprecated
	public Set<UserProfile> getInstructorsWithCourse(int courseCRN)
	{
		Set<UserProfile> rVal = null;

		if (containsCourse(courseCRN))
		{
			rVal = new HashSet<UserProfile>();

			for (Connector e : network)
			{
				if (e.relationship == COURSE_INSTRUCTOR_RELATIONSHIP)
				{
					if (e.courseCRN == courseCRN)
					{
						rVal.add(e.person);
					}
				}
			}
		}

		return rVal;
	}

	/**
	 * Returns a set of UserProfiles representing the people who are teaching the course with a specified CRN
	 * 
	 * @param courseCRN
	 *            The CRN of the course that is being looked at
	 * @return A set of UserProfiles representing the people who are teaching the course with a specified CRN, or null iff (!this.containsCourse(courseCRN))
	 */
	public UserProfile getInstructorWithCourse(int courseCRN)
	{
		UserProfile rVal = null;

		if (containsCourse(courseCRN))
		{
			for (Connector e : network)
			{
				if (e.relationship == COURSE_INSTRUCTOR_RELATIONSHIP)
				{
					if (e.courseCRN == courseCRN)
					{
						rVal = e.person;
					}
				}
			}
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
						course.incrRemainingCap();
						DatabaseIO.serializeEverything();
					}
				}
			}
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
			DatabaseIO.serializeEverything();
		}

		return rVal;
	}

	/**
	 * Attempts to register a specified person to teach a course with a specified crn. Fails if the instructors's permLevel is not TA, TEACHER, or ADMIN, no courses with the CRN exist in the catalog, or the course already has an instructor.
	 * 
	 * @param instructor
	 *            The person who is being registered to teach
	 * @param courseCRN
	 *            The crn of the course which is being enrolled in
	 * @return true iff the instructor is successfully signed up for the course
	 */
	public boolean addInstructorToCourse(UserProfile instructor, int courseCRN)
	{
		boolean rVal = false;
		if ((instructor.getPermLevel() >= UserProfile.TA) && (instructor.getPermLevel() <= UserProfile.ADMIN))
		{
			if (containsCourse(courseCRN))
			{
				if (getInstructorWithCourse(courseCRN) == null)
				{
					Connector connector = new Connector(COURSE_INSTRUCTOR_RELATIONSHIP, courseCRN, instructor);

					if (!network.contains(connector) && !network.contains(new Connector(COURSE_LEARNER_RELATIONSHIP, courseCRN, instructor)))
					{
						rVal = true;
						network.add(connector);
						DatabaseIO.serializeEverything();
					}
				}
			}
		}
		return rVal;
	}

	/**
	 * Attempts to remove a specified person from teaching a course with a specified crn.
	 * 
	 * @param learner
	 *            The person being removed
	 * @param courseCRN
	 *            The course the person is being no longer teach
	 * @return true iff the instructor was successfully removed from the course
	 */
	public boolean removeInstructorFromCourse(UserProfile instructor, int courseCRN)
	{
		boolean rVal = false;

		Connector connector = new Connector(COURSE_INSTRUCTOR_RELATIONSHIP, courseCRN, instructor);
		if (network.contains(connector))
		{
			rVal = true;
			network.remove(connector);
			DatabaseIO.serializeEverything();
		}
		return rVal;
	}

	/**
	 * Generates an integer representing the lowest integer not currently used as a crn in the catalog, within specified bounds
	 * 
	 * @param min
	 *            The lower bound for the return value; must be lower than max
	 * @param max
	 *            The upper bound for the return value; must be higher than min
	 * @return A crn which is not currently in use, or max+1 if every value in the bounded region is already in use
	 */
	public int generateNewCRN(int min, int max)
	{
		if (max <= min)
		{
			throw new IllegalArgumentException("max must be greater than min");
		}

		int rVal = min;

		while (containsCourse(rVal) && (rVal <= max))
		{
			rVal += 1;
		}

		return rVal;
	}

	/**
	 * Creates a course whose only meaningful value is its crn
	 * 
	 * @param crn
	 *            The crn of the dummy course
	 * @return The dummy course
	 */
	private Course dummyCourse(int crn)
	{
		Course rVal = new Course(null, null, crn, 0, null, 0, 0, 0, 1);
		return rVal;
	}

	public static class Connector implements Serializable, Comparable<Connector>
	{
		private static final long serialVersionUID = -1125763548284346166L;
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
			if (this == o)
				return true;

			boolean rVal = false;

			if (o instanceof Connector)
			{
				Connector other = (Connector) o;

				rVal = (this.relationship == other.relationship);
				if (rVal)
				{
					rVal = (this.courseCRN == other.courseCRN);
					if (rVal)
					{
						rVal = (this.person.equals(other.person));
					}
				}
			}

			return rVal;
		}

		@Override
		public int compareTo(Connector other)
		{
			if (this.equals(other))
				return 0;

			int rVal = Integer.compare(this.relationship, other.relationship);
			if (rVal == 0)
			{
				rVal = Integer.compare(this.courseCRN, other.courseCRN);
				if (rVal == 0)
				{
					rVal = this.person.getUserID().compareTo(other.person.getUserID());
				}
			}

			return rVal;
		}
	}

	public static class CourseComparator implements Comparator<Course>, Serializable
	{
		private static final long serialVersionUID = -3870133739880141697L;

		public int compare(Course arg0, Course arg1)
		{
			return Integer.compare(arg0.getCRN(), arg1.getCRN());
		}

	}
}