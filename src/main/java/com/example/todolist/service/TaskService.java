package com.example.todolist.service;

import com.example.todolist.dao.TaskDao;
import com.example.todolist.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskDao taskDao;

    @Autowired
    public TaskService(@Qualifier("fakeDao") TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public int addTask(Task task){
        return taskDao.addTask(task);
    }

    public List<Task> getAll(){
        return taskDao.getAll();
    }

    public Optional<Task>getById(UUID id){
        return taskDao.getById(id);
    }

    public int deleteTask(UUID id){
        return taskDao.deleteById(id);
    }

    public int updateTask(UUID id, Task task){
        return taskDao.updateById(id, task);
    }

    public List<Task> sortByImportance(){
        return taskDao.sortByImportance();
    }
}
