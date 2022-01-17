package com.example.todolist.dao;

import com.example.todolist.model.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskDao {

    int addTask(UUID id, Task task);

    default int addTask(Task task){
        UUID id = UUID.randomUUID();
        return addTask(id, task);
    }

    List<Task> getAll();

    Optional<Task> getById(UUID id);

    int deleteById(UUID id);

    int updateById(UUID id, Task task);

    List<Task> getByImportance(int importance);

    List<Task> sortByImportance();
}
