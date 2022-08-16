package com.halyk.bookstore.data.repository;

import com.halyk.bookstore.data.entity.Publisher;
import com.halyk.bookstore.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    default Publisher findByIdOrThrowException(Long id) {
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id = " + id + " not found"));
    }

    Publisher findPublisherByName(String name);

    Long deletePublisherById(Long id);

    List<Publisher> findPublisherByNameStartingWith(String name);
}

