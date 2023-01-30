package org.example.repository;

import org.example.config.HibernateConfiguration;
import org.example.models.Course;
import org.example.models.Lesson;
import org.hibernate.HibernateException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class LessonRepositoryImpl implements LessonRepository,AutoCloseable{
    private EntityManagerFactory entityManagerFactory = HibernateConfiguration.getSession();
    @Override
    public void saveLesson(Lesson lesson, Long courseId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(lesson);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateLesson(Lesson newLesson, Long oldLessonId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Lesson lesson = entityManager.find(Lesson.class, oldLessonId);
            lesson.setName(newLesson.getName());
            lesson.setVideoLink(newLesson.getVideoLink());
            lesson.setCourse(lesson.getCourse());
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Lesson getLessonById(Long lessonId) {
        Lesson lesson = null;
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            lesson = entityManager.find(Lesson.class,lessonId);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return lesson;
    }

    @Override
    public List<Lesson> getLessonsByCourseId(Long courseId) {
        List<Lesson> lessonList = new ArrayList<>();
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            lessonList = entityManager.find(Course.class,courseId).getLessons();
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return lessonList;
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
