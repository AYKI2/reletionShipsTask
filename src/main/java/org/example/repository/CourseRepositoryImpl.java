package org.example.repository;

import org.example.config.HibernateConfiguration;
import org.example.models.Course;
import org.hibernate.HibernateException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepository, AutoCloseable{
    private EntityManagerFactory entityManagerFactory = HibernateConfiguration.getSession();
    @Override
    public void saveCourse(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(course);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Course getCourseById(Long courseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Course course = entityManager.find(Course.class, courseId);
        entityManager.getTransaction().commit();
        entityManager.close();
        return course;
    }

    @Override
    public List<Course> getAllCourses(String ascOrDesc) {
        List<Course> courseList = new ArrayList<>();
        String query = "";
        if(ascOrDesc.equals("asc")) {
            query = "SELECT c FROM Course c ORDER BY createdAt";
        } else if (ascOrDesc.equals("desc")) {
            query = "SELECT c FROM Course c ORDER BY createdAt desc";
        }else {
            throw new HibernateException("No sorting");
        }
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            courseList = entityManager.createQuery(query, Course.class).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return courseList;
    }

    @Override
    public void updateCourse(Course newCourse, Long courseId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            course.setCourseName(newCourse.getCourseName());
            course.setDuration(newCourse.getDuration());
            course.setCreatedAt(newCourse.getCreatedAt());
            course.setImageLink(newCourse.getImageLink());
            course.setDescription(newCourse.getDescription());
            entityManager.merge(course);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteCourseById(Long courseId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Course.class, courseId));
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Course getCourseByName(String courseName) {
        Course course = null;
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            course = entityManager.createQuery("select l from Course  l where l.courseName=:name", Course.class).setParameter("name", courseName).getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return course;
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
