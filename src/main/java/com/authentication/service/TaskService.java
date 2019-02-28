package com.authentication.service;

import com.authentication.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasksByAuthor(String author);

    void addNote(Task task);
}
