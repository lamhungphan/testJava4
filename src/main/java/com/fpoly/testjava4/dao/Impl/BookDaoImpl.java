package com.fpoly.testjava4.dao.Impl;

import com.fpoly.testjava4.dao.BookDao;
import com.fpoly.testjava4.entity.Book;
import com.fpoly.testjava4.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class BookDaoImpl implements BookDao {
    EntityManager entityManager = JpaUtil.getEntityManager();

    @Override
    public Book findById(Integer id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> findAll() {
        String jpql = "SELECT p FROM Book p";
        return entityManager.createQuery(jpql, Book.class).getResultList();
    }

    @Override
    public Book create(Book entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error creating Books", e);
        }
    }

    @Override
    public Book update(Book entity) {
        try {
            entityManager.getTransaction().begin();
            Book updatedCategory = entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return updatedCategory;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error updating Book", e);
        }
    }

    @Override
    public Book delete(Integer id) {
        try {
            entityManager.getTransaction().begin();
            Book book = entityManager.find(Book.class, id);
            if (book != null) {
                entityManager.remove(book);
            }
            entityManager.getTransaction().commit();
            return book;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error deleting book", e);
        }
    }

    @Override
    public Integer countByAuthor(String authorId) {
        String jpql = "SELECT COUNT(b) FROM Book b WHERE b.author.id = :authorId";
        return ((Long) entityManager.createQuery(jpql)
                .setParameter("authorId", authorId)
                .getSingleResult()).intValue();
    }

    @Override

    public List<Book> findBooksByAuthor(String authorId) {
        String jpql = "SELECT b FROM Book b WHERE b.author.id = :authorId";
        return entityManager.createQuery(jpql, Book.class)
                .setParameter("authorId", authorId)
                .getResultList();
    }
}
