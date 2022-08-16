package com.halyk.bookstore.data.repository;

import com.halyk.bookstore.data.entity.Author;
import com.halyk.bookstore.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    default Author findByIdOrThrowException(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id = " + id + " not found"));
    }

    Long deleteAuthorById(Long id);

    @Query(value = """
                        SELECT *
                        FROM Author a ,
                             Book_Author ba,
                             Book_Genre bg,
                             Genre g
                        WHERE a.id = ba.author_id
                          and bg.genre_id= g.id
                          and ba.book_id= bg.book_id
                          and g.genre_name in :genreList
            """, nativeQuery = true)
    List<Author> getAuthorByGenreList(@Param("genreList") List<String> genreList);

}
