package org.example.TrabalhoChoma.service;

import org.example.TrabalhoChoma.model.Task;
import org.example.TrabalhoChoma.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task updatedTask = existingTask.get();
            updatedTask.setTitle(task.getTitle());
            updatedTask.setDescription(task.getDescription());
            updatedTask.setPriority(task.getPriority());
            return taskRepository.save(updatedTask);
        }
        return null;
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public boolean moveTask(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            switch (task.getStatus()) {
                case "A Fazer":
                    task.setStatus("Em Progresso");
                    break;
                case "Em Progresso":
                    task.setStatus("Concluído");
                    break;
                case "Concluído":
                    return false;
                default:
                    return false;
            }
            taskRepository.save(task);
            return true;
        }
        return false;
    }
}
