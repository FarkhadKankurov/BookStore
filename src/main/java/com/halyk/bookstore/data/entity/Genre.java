package com.halyk.bookstore.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Жанр
 */

@Entity
@Table(name = "Genre")
@Data
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "genre_name")
    private String genreName;

    @ManyToMany
    private List<Book> books = new ArrayList<>();

    @ManyToMany
    private List<Author> authors = new ArrayList<>();


}
