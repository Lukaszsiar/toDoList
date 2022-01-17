package com.example.todolist.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Task {

    private  UUID id;
    private  String description;
    private  int importance;

    public Task(@JsonProperty("id") UUID id,
               @JsonProperty("description") String description,
               @JsonProperty("importance") int importance) {
        this.id = id;
        this.description = description;
        this.importance = importance;
    }

    public Task() {
    }

    public int getImportance() {
        return importance;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
