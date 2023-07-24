package com.example.library.dao;

import com.example.library.domain.Book;
import com.example.library.domain.Person;

public interface BookDao extends CRUDDao<Book> {
public void freeBook(int id);
}
