package com.halyk.bookstore.data.repository;

import com.halyk.bookstore.data.entity.Author;
import com.halyk.bookstore.data.request.AuthorRequest;
import com.halyk.bookstore.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
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

    default Author findByIdOrThrowException(Long id){
        return findById(id).orElseThrow(()->new EntityNotFoundException("Entity with id = " + id + " not found"));
    }

    Author findAuthorByLastNameAndFirstNameAndPatronymicName(String surname, String name, String patron);


}
