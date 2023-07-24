package com.example.library.dao.impl;

import com.example.library.dao.PersonDao;
import com.example.library.domain.Book;
import com.example.library.domain.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
@Transactional
@Repository
public class PersonDaoImpl implements PersonDao {
    @PersistenceContext
    public EntityManager em;
    @Override
    public Person findById(int id) {
        return em.find(Person.class, id);
    }
    public Person findByAllFields(Person person){
        TypedQuery<Person> query = em.createQuery("from Person p " +
                "WHERE p.firstName = :prsn_fn AND p.lastName = :prsn_ln " +
                "AND p.dateOfBirth = :prsn_dob", Person.class);
        query.setParameter("prsn_fn", person.getFirstName());
        query.setParameter("prsn_ln", person.getLastName());
        query.setParameter("prsn_dob", person.getDateOfBirth());
        return query.getResultList().get(0);
    }
    @Override
    public void save(Person entity) {
        em.persist(entity);
    }

    @Override
    public void update(Person entity) {
        Person person = em.merge(entity);
        em.persist(person);
    }

    @Override
    public void deleteById(int id) {
        freeAllBooksByPersonId(id);
        Query query = em.createQuery("delete Person p WHERE id = :prsn_id");
        query.setParameter("prsn_id", id);
        query.executeUpdate();
    }

    @Override
    public List<Person> findAll() {
        TypedQuery<Person> query = em.createQuery("from Person p", Person.class);
        return query.getResultList();
    }

    @Override
    public List<Book> findBooksByPersonId(int id) {
        TypedQuery<Book> query = em.createQuery("from Book b WHERE b.person.id = :prsn_id", Book.class);
        query.setParameter("prsn_id", id);
        return query.getResultList();
    }
    private void freeAllBooksByPersonId(int id){
        Query query = em.createQuery("UPDATE Book b set b.person = null WHERE b.person.id = :prsn_id");
        query.setParameter("prsn_id", id);
        query.executeUpdate();
    }
}
