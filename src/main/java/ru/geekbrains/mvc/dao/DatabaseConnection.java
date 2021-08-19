package ru.geekbrains.mvc.dao;

import java.util.List;
import java.util.Optional;

public interface DatabaseConnection<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    boolean save(T item);

    boolean delete(T item);

    boolean update(T item);
}
