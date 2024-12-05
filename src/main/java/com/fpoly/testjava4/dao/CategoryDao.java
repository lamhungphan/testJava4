package com.fpoly.testjava4.dao;

import com.fpoly.testjava4.entity.Category;

import java.util.List;

public interface CategoryDao {
    public Category findById(Integer id);

    public List<Category> findAll();

    public Category create(Category entity);

    public Category update(Category entity);

    public Category delete(String id);
}
