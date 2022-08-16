package com.halyk.bookstore.data.repository;

import com.halyk.bookstore.data.entity.Genre;
import com.halyk.bookstore.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    default Genre findByIdOrThrowException(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id = " + id + " not found"));
    }

    List<Genre> findGenreByGenreName(String genreName);
    List<Genre> findByIdIn(List<Long> ids);

    Long deleteGenreById(Long id);

}
