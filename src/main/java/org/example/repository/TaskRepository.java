package org.example.repository;

import org.example.models.Task;

import java.util.List;

public interface TaskRepository {
    void saveTask(Task task, Long lessonId);
    void updateTask(Task newTask, Long oldTaskId);
    List<Task> getAllTaskByLessonId(Long lessonId);
    void deleteTaskById(Long lessonId, Long taskId);
}
