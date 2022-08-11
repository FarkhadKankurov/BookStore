package com.halyk.bookstore.service.impl;

import com.halyk.bookstore.data.entity.Book;
import com.halyk.bookstore.data.enums.OrderStatusEnum;
import com.halyk.bookstore.data.repository.BookRepository;
import com.halyk.bookstore.data.representation.OrderRepresentation;
import com.halyk.bookstore.data.request.OrderRequest;
import com.halyk.bookstore.data.entity.Order;
import com.halyk.bookstore.data.mapper.OrderMapper;
import com.halyk.bookstore.data.repository.OrderRepository;
import com.halyk.bookstore.exception.ExceedTotalCost;
import com.halyk.bookstore.service.OrderService;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    private final OrderMapper mapper;

    private final BookRepository bookRepository;

    @Transactional
    @Override
    public OrderRepresentation getOrderById(Long id) {
        Order order = repository.findByIdOrThrowException(id);
        return mapper.fromEntity(order);
    }

    @Transactional
    @Override
    public List<OrderRepresentation> getAllOrder() {
        List<Order> allOrder = repository.findAll();
        return allOrder.stream().map(mapper::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public long saveOrder(OrderRequest dto) {
        int totalSum = 0;
        //todo когда приходит список айдиКниг достать из базы каждую книгу, и записать туда ордер айди если оно пусто,
        // если не пусто кинуть ошибку, во время этого изменять  сумму (общую за все книги), если больше 10к > ошибка
        for (Book book : dto.getList()) {
            Book bookFromDB = bookRepository.findByIdOrThrowException(book.getId());
            totalSum += bookFromDB.getCost();
            if (totalSum > 10000)
                throw new ExceedTotalCost("Общая сумма: " + totalSum + ", превышает 10 000.");
            

        }
//        for (CartItemRequest c: dto.getList()) {
//            Book bookFromDB = bookRepository.findByIdOrThrowException(c.getBookId());
//            if (bookFromDB.getCountOfSameBooks() < c.getBookCount()) {
//                throw new AllowLimitBookException("Колличество запрашиваемых книг: " + c.getBookCount()
//                        + " превышает колличество доступных " + bookFromDB.getCountOfSameBooks());
//            }
//        }

        Order order = mapper.toEntity(dto);
        Order save = repository.save(order);
        return save.getId();
    }

    @Transactional
    @Override
    public void updateOrder(OrderRequest dto, Long id) {
        Order order = repository.findByIdOrThrowException(id);
        mapper.updateEntity(order, dto);
        repository.save(order);
    }

    @Transactional
    @Override
    public Long delete(Long id) {
        return repository.deleteOrderById(id);
    }

    @Transactional
    @Override
    public void updateOrderStatus(Long id) {
        Order order = repository.findByIdOrThrowException(id);
        order.setStatus(OrderStatusEnum.PROCESS);
//        order.getCartItems().forEach(cartItem -> {
//            Book book = bookRepository.findByIdOrThrowException(cartItem.getBook().getId());
//            book.setCountOfSameBooks(book.getCountOfSameBooks() - cartItem.getCountOfBooks());
//            bookRepository.save(book);
//        });

        repository.save(order);
    }

}
