package com.halyk.bookstore.data.repository;

import com.halyk.bookstore.data.entity.Book;
import com.halyk.bookstore.data.entity.Order;
import com.halyk.bookstore.exception.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    default Order findByIdOrThrowException(Long id){
        return findById(id).orElseThrow(()->new EntityNotFoundException("Entity with id = " + id + " not found"));
    }

    Long deleteOrderById(Long id);
}
