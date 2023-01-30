package org.example.repository;

import org.example.models.Course;

import java.util.List;

public interface CourseRepository {
    void saveCourse(Course course);
    Course getCourseById(Long courseId);
    List<Course> getAllCourses(String ascOrDesc);
    void updateCourse(Course newCourse, Long courseId);
    void deleteCourseById(Long courseId);
    Course getCourseByName(String courseName);


}
