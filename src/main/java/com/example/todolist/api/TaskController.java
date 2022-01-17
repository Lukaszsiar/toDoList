package com.example.todolist.api;


import com.example.todolist.model.Task;
import com.example.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api")
@RestController
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public void addTask(@RequestBody Task task){
        taskService.addTask(task);
    }

    @GetMapping("list")
    public List<Task> getTask(){
        return taskService.getAll();
    }

    @GetMapping("/list/id")
    public Optional<Task> getTaskById(@RequestParam UUID id){
        return taskService.getById(id);
    }

    @GetMapping("/list/sort")
    public List<Task> sortByImportance() {
        return taskService.sortByImportance();
    }

    @DeleteMapping("/delete")
    public int deleteTask(@RequestParam UUID id){
        return taskService.deleteTask(id);
    }

    @PutMapping("/update")
    public int updateTask(@RequestParam UUID id, @RequestBody Task task){
        return taskService.updateTask(id, task);
    }

}
