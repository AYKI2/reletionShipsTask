package org.example.services;

import org.example.models.Instructor;

import java.util.List;

public interface InstructorService {
    String saveInstructor(Instructor instructor);
    Instructor getInstructorById(Long instructorId);
    List<Instructor> getInstructorsByCourseId(Long courseId);
    List<Instructor> getAllInstructors();
    String updateInstructor(Instructor newInstructor, Long instructorId);
    String deleteInstructorById(Long instructorId);
    String assignInstructorToCourse(Long courseId, Long instructorId);
}
