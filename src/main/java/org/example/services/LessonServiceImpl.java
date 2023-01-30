package org.example.services;

import org.example.models.Lesson;
import org.example.repository.LessonRepository;
import org.example.repository.LessonRepositoryImpl;

import java.util.List;

public class LessonServiceImpl implements LessonService {
    private LessonRepository lessonRepository = new LessonRepositoryImpl();
    @Override
    public String saveLesson(Lesson lesson, Long courseId) {
        lessonRepository.saveLesson(lesson,courseId);
        return "Successfully saved!";
    }

    @Override
    public String updateLesson(Lesson newLesson, Long oldLessonId) {
        lessonRepository.updateLesson(newLesson, oldLessonId);
        return "Successfully updated!";
    }

    @Override
    public Lesson getLessonById(Long lessonId) {
        return lessonRepository.getLessonById(lessonId);
    }

    @Override
    public List<Lesson> getLessonsByCourseId(Long courseId) {
        return lessonRepository.getLessonsByCourseId(courseId);
    }
}
