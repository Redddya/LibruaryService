package com.example.library.service.impl;

import com.example.library.dao.BookDao;
import com.example.library.domain.Book;
import com.example.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookDao dao;
    @Override
    public void freeBook(int id) {
        dao.freeBook(id);
    }

    @Override
    public Book findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void save(Book entity) {
        dao.save(entity);
    }

    @Override
    public void update(Book entity) {
        dao.update(entity);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public List<Book> findAll() {
        return dao.findAll();
    }
}
