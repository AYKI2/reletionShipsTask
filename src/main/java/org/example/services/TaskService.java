package org.example.services;

import org.example.models.Task;

import java.util.List;

public interface TaskService {
    String saveTask(Task task, Long lessonId);
    String updateTask(Task newTask, Long oldTaskId);
    List<Task> getAllTaskByLessonId(Long lessonId);
    String deleteTaskById(Long lessonId, Long taskId);
}
