package com.fpoly.testjava4.dao.Impl;

import com.fpoly.testjava4.dao.ProductDao;
import com.fpoly.testjava4.entity.Product;
import com.fpoly.testjava4.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    EntityManager entityManager = JpaUtil.getEntityManager();

    @Override
    public Product findById(Integer id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        String jpql = "SELECT p FROM Product p";
        return entityManager.createQuery(jpql, Product.class).getResultList();
    }

    @Override
    public Product create(Product entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error creating product", e);
        }
    }

    @Override
    public Product update(Product entity) {
        try {
            entityManager.getTransaction().begin();
            Product updatedCategory = entityManager.merge(entity);
            entityManager.getTransaction().commit();
            return updatedCategory;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error updating product", e);
        }
    }

    @Override
    public Product delete(Integer id) {
        try {
            entityManager.getTransaction().begin();
            Product product = entityManager.find(Product.class, id);
            if (product != null) {
                entityManager.remove(product);
            }
            entityManager.getTransaction().commit();
            return product;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException("Error deleting product", e);
        }
    }

    public Integer countByCategory(String categoryId) {
        String jpql = "SELECT COUNT(p) FROM Product p WHERE p.category.id = :categoryId";
        return ((Long) entityManager.createQuery(jpql)
                .setParameter("categoryId", categoryId)
                .getSingleResult()).intValue();
    }

    @Override
    public Long countAll() {
        String jpql = "SELECT COUNT(p) FROM Product p";
        return (Long) entityManager.createQuery(jpql).getSingleResult();
    }

    @Override
    public List<Product> getProductsByCategory(String categoryId) {
        String jpql = "SELECT p FROM Product p WHERE p.category.id = :categoryId";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
        query.setParameter("categoryId", categoryId);
        return query.getResultList();
    }
}
