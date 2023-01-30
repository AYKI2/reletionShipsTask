package org.example.repository;

import org.example.config.HibernateConfiguration;
import org.example.models.Course;
import org.example.models.Instructor;
import org.hibernate.HibernateException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class InstructorRepositoryImpl implements InstructorRepository,AutoCloseable {
    private EntityManagerFactory entityManagerFactory = HibernateConfiguration.getSession();
    @Override
    public void saveInstructor(Instructor instructor) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(instructor);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Instructor getInstructorById(Long instructorId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Instructor instructor = entityManager.find(Instructor.class, instructorId);
            entityManager.getTransaction().commit();
            entityManager.close();
            return instructor;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Instructor> getInstructorsByCourseId(Long courseId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            List<Instructor> instructors = entityManager.find(Course.class, courseId).getInstructors();
            entityManager.getTransaction().commit();
            entityManager.close();
            return instructors;
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    @Override
    public List<Instructor> getAllInstructors() {
        List<Instructor> instructorList = new ArrayList<>();
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            instructorList = entityManager.createQuery("SELECT i from Instructor i").getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return instructorList;
    }

    @Override
    public void updateInstructor(Instructor newInstructor, Long instructorId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Instructor instructor = entityManager.find(Instructor.class, instructorId);
            instructor.setFirstName(newInstructor.getFirstName());
            instructor.setLastName(newInstructor.getLastName());
            instructor.setEmail(newInstructor.getEmail());
            instructor.setPhoneNumber(newInstructor.getPhoneNumber());
            entityManager.merge(instructor);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteInstructorById(Long instructorId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Instructor.class, instructorId));
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void assignInstructorToCourse(Long courseId, Long instructorId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, courseId);
            Instructor instructor = entityManager.find(Instructor.class, instructorId);
            course.getInstructors().add(instructor);
            entityManager.persist(course);
            entityManager.persist(instructor);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
