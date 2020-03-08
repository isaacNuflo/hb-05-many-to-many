package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Course;
import com.luv2code.hibernate.entity.Instructor;
import com.luv2code.hibernate.entity.InstructorDetail;
import com.luv2code.hibernate.entity.Review;
import com.luv2code.hibernate.entity.Student;

public class AddCoursesForStudentDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			int idStudent = 1;
			Student tempStudent = session.get(Student.class, idStudent);

			System.out.println("student loaded: " + tempStudent);
			System.out.println("Courses: " + tempStudent.getCourses());

			Course tempCourse = new Course("Ruby Course");
			Course tempCourse1 = new Course("Python Course");

			tempCourse.addStudent(tempStudent);
			tempCourse1.addStudent(tempStudent);

			System.out.println("saving the courses");
			session.save(tempCourse);
			session.save(tempCourse1);
			System.out.println("saved the courses");

			session.getTransaction().commit();
			System.out.println("done");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
