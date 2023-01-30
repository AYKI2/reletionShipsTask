package org.example.services;

import org.example.models.Lesson;

import java.util.List;

public interface LessonService {
    String saveLesson(Lesson lesson, Long courseId);
    String updateLesson(Lesson newLesson, Long oldLessonId);
    Lesson getLessonById(Long lessonId);
    List<Lesson> getLessonsByCourseId(Long courseId);
}
