package com.fpoly.testjava4.dao;

import com.fpoly.testjava4.entity.Product;

import java.util.List;

public interface ProductDao {
    public Product findById(Integer id);

    public List<Product> findAll();

    public Product create(Product entity);

    public Product update(Product entity);

    public Product delete(Integer id);

    public Integer countByCategory(String id);

    public Long countAll();

    public List<Product> getProductsByCategory(String categoryId);
}
