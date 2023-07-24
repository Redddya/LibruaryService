package com.example.library.dao;

import java.util.List;

public interface CRUDDao<T> {
    public T findById(int id);
    public void save(T entity);
    public void update(T entity);
    public void deleteById(int id);
    public List<T> findAll();
}
