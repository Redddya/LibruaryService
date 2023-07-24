package com.example.library.service;

import com.example.library.domain.Book;
import com.example.library.domain.Person;

public interface BookService extends CRUDService<Book> {
public void freeBook(int id);
}
