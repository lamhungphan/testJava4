package com.fpoly.testjava4.dao;

import com.fpoly.testjava4.entity.Category;
import com.fpoly.testjava4.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    EntityManager entityManager = JpaUtil.getEntityManager();

    @Override
    public Category findById(Integer id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public List<Category> findAll() {
        String jpql = "SELECT c FROM Category c";
        return entityManager.createQuery(jpql, Category.class).getResultList();
    }

    @Override
    public Category create(Category entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error creating category", e);
        }
    }

    @Override
    public Category update(Category entity) {
        try {
            entityManager.getTransaction().begin();
            Category updatedCategory = entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return updatedCategory;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error updating category", e);
        }
    }

    @Override
    public Category delete(String id) {
        try {
            entityManager.getTransaction().begin();
            Category category = entityManager.find(Category.class, id);
            if (category != null) {
                entityManager.remove(category);
            }
            entityManager.getTransaction().commit();
            return category;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error deleting category", e);
        }
    }
}
