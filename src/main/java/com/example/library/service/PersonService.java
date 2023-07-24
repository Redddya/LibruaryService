package com.example.library.service;

import com.example.library.domain.Book;
import com.example.library.domain.Person;

import java.util.List;

public interface PersonService extends CRUDService<Person> {
    public List<Book> findBooksByPersonId(int id);
    public boolean isSamePersinExists(Person person);
}
