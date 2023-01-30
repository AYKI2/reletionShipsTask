package org.example.services;

import org.example.models.Course;

import java.util.List;

public interface CourseService {
    String saveCourse(Course course);
    Course getCourseById(Long courseId);
    List<Course> getAllCourses(String ascOrDesc);
    String updateCourse(Course newCourse, Long courseId);
    String deleteCourseById(Long courseId);
    Course getCourseByName(String courseName);
}
