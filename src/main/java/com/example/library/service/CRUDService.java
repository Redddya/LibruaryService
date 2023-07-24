package com.example.library.service;

import java.util.List;

public interface CRUDService<T> {
    public T findById(int id);
    public void save(T entity);
    public void update(T entity);
    public void deleteById(int id);
    public List<T> findAll();
}
