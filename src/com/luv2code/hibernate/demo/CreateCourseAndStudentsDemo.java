package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.entity.Course;
import com.luv2code.hibernate.entity.Instructor;
import com.luv2code.hibernate.entity.InstructorDetail;
import com.luv2code.hibernate.entity.Review;
import com.luv2code.hibernate.entity.Student;

public class CreateCourseAndStudentsDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			
			Course tempCourse = new Course("Minecraft");
			
			System.out.println("saving the course: " + tempCourse);
			session.save(tempCourse);
			System.out.println("saved the course: " + tempCourse);
			
			Student tempStudent = new Student("Isaac", "Nuflo", "isaac.nuflo@gmail.com");
			Student tempStudent2 = new Student("Carlos", "Nuflo", "carlos.nuflo@gmail.com");
			
			tempCourse.addStudent(tempStudent);
			tempCourse.addStudent(tempStudent2);
			
			System.out.println("saving the students");
			session.save(tempStudent);
			session.save(tempStudent2);
			System.out.println("saved the students");
			
			session.getTransaction().commit();
			System.out.println("done");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
			factory.close();
		}
	}

}
