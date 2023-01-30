package org.example.repository;

import org.example.models.Course;
import org.example.models.Instructor;

import java.util.List;

public interface InstructorRepository {
    void saveInstructor(Instructor instructor);
    Instructor getInstructorById(Long instructorId);
    List<Instructor> getInstructorsByCourseId(Long courseId);
    List<Instructor> getAllInstructors();
    void updateInstructor(Instructor newInstructor, Long instructorId);
    void deleteInstructorById(Long instructorId);
    void assignInstructorToCourse(Long courseId, Long instructorId);
}
