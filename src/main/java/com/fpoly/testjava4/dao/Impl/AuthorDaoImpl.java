package com.fpoly.testjava4.dao.Impl;

import com.fpoly.testjava4.dao.AuthorDao;
import com.fpoly.testjava4.entity.Author;
import com.fpoly.testjava4.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    EntityManager entityManager = JpaUtil.getEntityManager();

    @Override
    public Author findById(String id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public List<Author> findAll() {
        String jpql = "SELECT p FROM Author p";
        return entityManager.createQuery(jpql, Author.class).getResultList();
    }

    @Override
    public Author create(Author entity) {
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
    public Author update(Author entity) {
        try {
            entityManager.getTransaction().begin();
            Author updatedCategory = entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return updatedCategory;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error updating Author", e);
        }
    }

    @Override
    public Author delete(String id) {
        try {
            entityManager.getTransaction().begin();
            Author author = entityManager.find(Author.class, id);
            if (author != null) {
                entityManager.remove(author);
            }
            entityManager.getTransaction().commit();
            return author;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error deleting Author", e);
        }
    }

    public Integer countAuthors(String authorId) {
        String jpql = "SELECT COUNT(a) FROM Author a WHERE a.id = :authorId";
        return ((Long) entityManager.createQuery(jpql)
                .setParameter("authorId", authorId)
                .getSingleResult()).intValue();
    }

    @Override
    public Long countAll() {
        String jpql = "SELECT COUNT(p) FROM Author p";
        return (Long) entityManager.createQuery(jpql).getSingleResult();
    }

}
