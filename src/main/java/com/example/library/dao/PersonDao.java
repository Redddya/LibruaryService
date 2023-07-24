package com.example.library.dao;

import com.example.library.domain.Book;
import com.example.library.domain.Person;

import java.util.List;

public interface PersonDao extends CRUDDao<Person> {
    public List<Book> findBooksByPersonId(int id);
    public boolean isSamePersinExists(Person person);
}
