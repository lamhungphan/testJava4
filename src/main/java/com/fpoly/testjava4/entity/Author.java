package com.fpoly.testjava4.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="authors")
public class Author {
    @Id
    private String id;
    private String name;
    @OneToMany
    List<Book> bookList = new ArrayList<>();
}
