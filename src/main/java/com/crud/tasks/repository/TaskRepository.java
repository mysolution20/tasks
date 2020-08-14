package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();

    /**
     * 19.2 Zadanie: wyszukiwanie pojedynczego zadania
     * Optional<Task> findTaskById(Long id);
     */

    @Override
    Optional<Task> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    Task save(Task task);

    @Override
    long count();
}