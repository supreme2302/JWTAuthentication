package com.authentication.service.impl;

import com.authentication.dao.TaskDao;
import com.authentication.model.Task;
import com.authentication.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "taskService")
public class TaskServiseImpl implements TaskService {

    @Autowired
    TaskDao taskDao;

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Task> getTasksByAuthor(String author) {
        return taskDao.getTasksByAuthor(author);
    }

    @Override
    public void addNote(Task task) {
        taskDao.save(task);
//        jdbc.update("INSERT INTO task (body, title, name) VALUES (?, ?, ?)",
//                task.getBody(), task.getTitle(), task.getAuthor());
    }
}
