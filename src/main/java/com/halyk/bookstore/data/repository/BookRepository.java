package com.halyk.bookstore.data.repository;

import com.halyk.bookstore.data.entity.Book;
import com.halyk.bookstore.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    default Book findByIdOrThrowException(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id = " + id + " not found"));
    }

    Long deleteBookById(Long id);

    List<Book> findBookByName(String name);

    List<Book> findByIdIn(List<Long> bookList);

    void deleteBooksByIdIn(List<Long> booksIDInEntity);

    List<Book> findBookByNameStartingWith(String namePart);

    @Query(value = """
                        SELECT *
                        FROM Book b,
                             Book_Genre bg,
                             Genre g
                        WHERE b.id = bg.book_id
                          and bg.genre_id = g.id
                          and g.genre_name = :genreName
            """, nativeQuery = true)
    Book findAllByGenre(@Param("genreName") String genreName);

}
