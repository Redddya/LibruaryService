package com.example.library.dao.impl;

import com.example.library.dao.BookDao;
import com.example.library.domain.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
@Transactional
@Repository
public class BookDaoImpl implements BookDao {
    @PersistenceContext
    public EntityManager em;

    @Override
    public void freeBook(int id) {
        Query query = em.createQuery("UPDATE Book b set b.person = null WHERE b.id = :bk_id");
        query.setParameter("bk_id", id);
        query.executeUpdate();
    }

    @Override
    public Book findById(int id) {
        return em.find(Book.class, id);
    }

    @Override
    public void save(Book entity) {
        em.persist(entity);
    }

    @Override
    public void update(Book entity) {
        Book book = em.merge(entity);
        em.persist(book);
    }

    @Override
    public void deleteById(int id) {
        Query query = em.createQuery("delete Book b WHERE id = :b_id");
        query.setParameter("b_id", id);
        query.executeUpdate();
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("from Book b", Book.class);
        return query.getResultList();
    }

}
