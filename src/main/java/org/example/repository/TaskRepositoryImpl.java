package org.example.repository;

import org.example.config.HibernateConfiguration;
import org.example.models.Lesson;
import org.example.models.Task;
import org.hibernate.HibernateException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class TaskRepositoryImpl implements TaskRepository,AutoCloseable{
    private EntityManagerFactory entityManagerFactory = HibernateConfiguration.getSession();
    @Override
    public void saveTask(Task task, Long lessonId) {
        List<Task> taskList = new ArrayList<>();
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Lesson lesson = entityManager.find(Lesson.class, lessonId);
            taskList.addAll(lesson.getTasks());
            taskList.add(task);
            lesson.setTasks(taskList);
            entityManager.persist(task);
            entityManager.merge(lesson);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateTask(Task newTask, Long oldTaskId) {
        Task task = null;
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            task = entityManager.find(Task.class, oldTaskId);
            task.setName(newTask.getName());
            task.setDeadLine(newTask.getDeadLine());
            task.setTask(newTask.getTask());
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Task> getAllTaskByLessonId(Long lessonId) {
        List<Task> taskList = new ArrayList<>();
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            taskList = entityManager.find(Lesson.class, lessonId).getTasks();
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }

    @Override
    public void deleteTaskById(Long lessonId, Long taskId) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Task task = entityManager.find(Task.class, taskId);
            entityManager.find(Lesson.class, lessonId).getTasks().remove(task);
            entityManager.remove(task);
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
