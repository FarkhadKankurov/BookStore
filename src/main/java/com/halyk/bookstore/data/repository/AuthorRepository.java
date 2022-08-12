package com.halyk.bookstore.data.repository;

import com.halyk.bookstore.data.entity.Author;
import com.halyk.bookstore.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    default Author findByIdOrThrowException(Long id){
        return findById(id).orElseThrow(()->new EntityNotFoundException("Entity with id = " + id + " not found"));
    }

    Author findAuthorByLastNameAndFirstNameAndPatronymicName(String surname, String name, String patron);
}
