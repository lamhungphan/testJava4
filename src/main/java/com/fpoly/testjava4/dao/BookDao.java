package com.fpoly.testjava4.dao;

import com.fpoly.testjava4.entity.Book;

import java.util.List;

public interface BookDao {
    public Book findById(Integer id);

    public List<Book> findAll();

    public Book create(Book entity);

    public Book update(Book entity);

    public Book delete(Integer id);

    public Integer countByAuthor(String authorId);
    public List<Book> findBooksByAuthor(String authorId);

}
