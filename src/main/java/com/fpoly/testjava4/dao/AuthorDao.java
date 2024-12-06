package com.fpoly.testjava4.dao;

import com.fpoly.testjava4.entity.Author;

import java.util.List;

public interface AuthorDao {
    public Author findById(String id);

    public List<Author> findAll();

    public Author create(Author entity);

    public Author update(Author entity);

    public Author delete(String id);

    public Integer countAuthors(String id);

    public Long countAll();

}
