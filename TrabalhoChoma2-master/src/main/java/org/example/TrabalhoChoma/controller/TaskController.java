package org.example.TrabalhoChoma.controller;

import org.example.TrabalhoChoma.model.Task;
import org.example.TrabalhoChoma.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setStatus("A Fazer");
        return taskService.saveTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @PostMapping("/{id}/move")
    public String moveTask(@PathVariable Long id) {
        boolean moved = taskService.moveTask(id);
        if (moved) {
            return "Tarefa movida com sucesso!";
        }
        return "Não foi possível mover a tarefa. Verifique o status atual.";
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
