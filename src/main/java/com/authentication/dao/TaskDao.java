package com.authentication.dao;

import com.authentication.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao extends CrudRepository<Task, Long> {
    List<Task> getTasksByAuthor(String author);
}
