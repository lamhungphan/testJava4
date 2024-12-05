package com.fpoly.testjava4.dao;

import com.fpoly.testjava4.entity.Category;

import java.util.List;

 public interface CategoryDao {
     Category findById(String id);

     List<Category> findAll();

     Category create(Category entity);

     Category update(Category entity);

     Category delete(String id);
}
