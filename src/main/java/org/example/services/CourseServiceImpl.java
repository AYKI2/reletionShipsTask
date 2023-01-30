package org.example.services;

import org.example.models.Course;
import org.example.repository.CourseRepository;
import org.example.repository.CourseRepositoryImpl;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository = new CourseRepositoryImpl();
    @Override
    public String saveCourse(Course course) {
        courseRepository.saveCourse(course);
        return "Successfully saved!";
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.getCourseById(courseId);
    }

    @Override
    public List<Course> getAllCourses(String ascOrDesc) {
        return courseRepository.getAllCourses(ascOrDesc);
    }

    @Override
    public String updateCourse(Course newCourse, Long courseId) {
        courseRepository.updateCourse(newCourse, courseId);
        return "Successfully updated!";
    }

    @Override
    public String deleteCourseById(Long courseId) {
        courseRepository.deleteCourseById(courseId);
        return "Successfully deleted!";
    }

    @Override
    public Course getCourseByName(String courseName) {
        return courseRepository.getCourseByName(courseName);
    }
}
