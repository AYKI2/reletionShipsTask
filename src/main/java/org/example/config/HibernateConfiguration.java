package org.example.config;

import jakarta.persistence.EntityManagerFactory;
import org.example.models.Course;
import org.example.models.Instructor;
import org.example.models.Lesson;
import org.example.models.Task;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

public class HibernateConfiguration {
    public static EntityManagerFactory getSession(){
        try {
            Properties properties = new Properties();
            properties.put(Environment.DRIVER, "org.postgresql.Driver");
            properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/java8");
            properties.put(Environment.USER, "postgres");
            properties.put(Environment.PASS, "admin123");

            properties.put(Environment.HBM2DDL_AUTO, "update");
            properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            properties.put(Environment.SHOW_SQL, "true");
            properties.put(Environment.FORMAT_SQL, "true");

            Configuration configuration = new Configuration();
            configuration.addProperties(properties);
            configuration.addAnnotatedClass(Course.class);
            configuration.addAnnotatedClass(Instructor.class);
            configuration.addAnnotatedClass(Lesson.class);
            configuration.addAnnotatedClass(Task.class);
            System.out.println("Successfully connected!");
            return configuration.buildSessionFactory().unwrap(EntityManagerFactory.class);
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
