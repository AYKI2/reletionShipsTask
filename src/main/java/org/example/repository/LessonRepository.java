package org.example.repository;

import org.example.models.Lesson;

import java.util.List;

public interface LessonRepository {
    void saveLesson(Lesson lesson, Long courseId);
    void updateLesson(Lesson newLesson, Long oldLessonId);
    Lesson getLessonById(Long lessonId);
    List<Lesson> getLessonsByCourseId(Long courseId);
}
