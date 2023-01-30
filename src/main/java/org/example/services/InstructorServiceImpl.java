package org.example.services;

import org.example.models.Instructor;
import org.example.repository.InstructorRepository;
import org.example.repository.InstructorRepositoryImpl;

import java.util.List;

public class InstructorServiceImpl implements InstructorService {
    private InstructorRepository instructorRepository = new InstructorRepositoryImpl();
    @Override
    public String saveInstructor(Instructor instructor) {
        instructorRepository.saveInstructor(instructor);
        return "Successfully saved!";
    }

    @Override
    public Instructor getInstructorById(Long instructorId) {
        return instructorRepository.getInstructorById(instructorId);
    }

    @Override
    public List<Instructor> getInstructorsByCourseId(Long courseId) {
        return instructorRepository.getInstructorsByCourseId(courseId);
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.getAllInstructors();
    }

    @Override
    public String updateInstructor(Instructor newInstructor, Long instructorId) {
        instructorRepository.updateInstructor(newInstructor,instructorId);
        return "Successfully updated!";
    }

    @Override
    public String deleteInstructorById(Long instructorId) {
        instructorRepository.deleteInstructorById(instructorId);
        return "Successfully deleted!";
    }

    @Override
    public String assignInstructorToCourse(Long courseId, Long instructorId) {
        instructorRepository.assignInstructorToCourse(courseId,instructorId);
        return "Assigned successfully";
    }
}
