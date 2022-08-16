package com.halyk.bookstore.data.repository;

import com.halyk.bookstore.data.entity.Author;
import com.halyk.bookstore.data.representation.AuthorRepresentation;
import com.halyk.bookstore.data.request.AuthorRequest;
import com.halyk.bookstore.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    default Author findByIdOrThrowException(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id = " + id + " not found"));
    }

    Long deleteAuthorById(Long id);

    @Query(value = """
                        SELECT *
                        FROM author b,
                             author_genre bg,
                             genre g
                        WHERE b.id = bg.author_id
                          and bg.genre_id = g.id
                          and g.name = :genreName
            """, nativeQuery = true)
    List<AuthorRepresentation> findAllByGenre(String genreName);

//    List<AuthorRepresentation> (String name);
}
