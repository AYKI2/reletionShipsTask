package org.example.services;

import org.example.models.Task;
import org.example.repository.TaskRepository;
import org.example.repository.TaskRepositoryImpl;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository = new TaskRepositoryImpl();
    @Override
    public String saveTask(Task task, Long lessonId) {
        taskRepository.saveTask(task,lessonId);
        return "Successfully saved";
    }

    @Override
    public String updateTask(Task newTask, Long oldTaskId) {
        taskRepository.updateTask(newTask,oldTaskId);
        return "Successfully updated!";
    }

    @Override
    public List<Task> getAllTaskByLessonId(Long lessonId) {
        return taskRepository.getAllTaskByLessonId(lessonId);
    }

    @Override
    public String deleteTaskById(Long lessonId, Long taskId) {
        taskRepository.deleteTaskById(lessonId,taskId);
        return "Successfully deleted!";
    }
}
