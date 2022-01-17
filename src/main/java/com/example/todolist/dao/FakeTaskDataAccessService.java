package com.example.todolist.dao;

import com.example.todolist.model.Task;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository("fakeDao")
public class FakeTaskDataAccessService implements TaskDao{

    private static List<Task> DB = new ArrayList<>();

    @Override
    public int addTask(UUID id, Task task) {
        DB.add(new Task(id, task.getDescription(), task.getImportance()));
        return 1;
    }

    @Override
    public List<Task> getAll() {
        return DB;
    }

    @Override
    public Optional<Task> getById(UUID id) {

        return DB.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteById(UUID id) {
        Optional<Task> task = getById(id);
        if (task.isEmpty()) return 0;
        DB.remove(task.get());
        return 1;
    }

    @Override
    public int updateById(UUID id, Task task) {
        return getById(id).map(t -> {
            int indexToUpdate = DB.indexOf(t);
            if (indexToUpdate >= 0){
                DB.set(indexToUpdate, new Task(id, task.getDescription(), task.getImportance()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }

    @Override
    public List<Task> getByImportance(int importance) {
        List<Task> taskList = getAll();
        List<Task> resultList = taskList.stream()
                .filter(task -> task.getImportance() == importance)
                .collect(Collectors.toList());

        return resultList;
    }

    public List<Task> sortByImportance(){
        List<Task> taskList = getAll();
        taskList = taskList.stream()
                .sorted(Comparator.comparing(Task::getImportance).reversed())
                .collect(Collectors.toList());
        return taskList;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void fillDb(){
        addTask(new Task(UUID.randomUUID(), "task 1", 2));
        addTask(new Task(UUID.randomUUID(), "task 2", 1));
        addTask(new Task(UUID.randomUUID(), "task 3", 5));
        addTask(new Task(UUID.randomUUID(), "task 4", 3));
        addTask(new Task(UUID.randomUUID(), "task 5", 4));
        addTask(new Task(UUID.randomUUID(), "task 6", 5));
    }
}
