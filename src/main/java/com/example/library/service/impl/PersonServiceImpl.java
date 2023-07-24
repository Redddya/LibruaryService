package com.example.library.service.impl;

import com.example.library.dao.PersonDao;
import com.example.library.domain.Book;
import com.example.library.domain.Person;
import com.example.library.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
    private PersonDao dao;
    @Override
    public Person findById(int id) {
        return dao.findById(id);
    }
    public Person findByAllFields(Person person){
        return dao.findByAllFields(person);
    }
    @Override
    public void save(Person entity) {
        dao.save(entity);
    }

    @Override
    public void update(Person entity) {
        dao.update(entity);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public List<Person> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Book> findBooksByPersonId(int id) {
        return dao.findBooksByPersonId(id);
    }
}
