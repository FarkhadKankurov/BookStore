package com.halyk.bookstore.service.impl;

import com.halyk.bookstore.data.entity.Book;
import com.halyk.bookstore.data.enums.OrderStatusEnum;
import com.halyk.bookstore.data.repository.BookRepository;
import com.halyk.bookstore.data.representation.OrderRepresentation;
import com.halyk.bookstore.data.request.OrderRequest;
import com.halyk.bookstore.data.entity.Order;
import com.halyk.bookstore.data.mapper.OrderMapper;
import com.halyk.bookstore.data.repository.OrderRepository;
import com.halyk.bookstore.exception.BookReservedAnotherOrder;
import com.halyk.bookstore.exception.ExceedTotalCost;
import com.halyk.bookstore.service.OrderService;
import lombok.Data;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Data
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    private final OrderMapper mapper;

    private final BookRepository bookRepository;

//    private final SecurityContextHolder

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


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
        }//todo добавить в userRepository метод findUserByUserName, так мы можем посмотреть его статус если isBlocked тогда кидаю ошибку что он заблокирован
        int totalSum = 0;
        Order order = new Order();
        order.setStatus(OrderStatusEnum.CREATED);
        Order save = repository.save(order);

        List<Book> books = bookRepository.findByIdIn(dto.getList());
        List<String> exists = new ArrayList<>();
        for (Book book : books) {
            totalSum += book.getCost();
            if (totalSum > 10000)
                throw new ExceedTotalCost("Общая сумма: " + totalSum + ", превышает 10000.");
            if (Objects.nonNull(book.getOrder())) {
                exists.add(book.getName());
            } else {
                book.setOrder(save);
            }
        }
        if (!exists.isEmpty()) {
            String collect = String.join(", ", exists);
            throw new BookReservedAnotherOrder("Книги: " + collect + ", зарезервированы в другом заказе");
        }
        bookRepository.saveAll(books);
        return save.getId();
    }

    @Transactional
    @Override
    public void updateOrder(OrderRequest dto, Long id) {
        Order order = repository.findByIdOrThrowException(id);
        List<Long> booksIDInEntity = order.getBooks().stream().map(Book::getId).toList();
        List<Long> booksIDInRequest = dto.getList();
        //todo сделать два списка один для удаления другой для добавления
        //todo айди из booksIDInRequest которых нету в booksIDInEntity нужно
        // добавить, а наоборот нужно удалить
        repository.save(order);
        //todo добавить такую же логику как и в сейф, если чувак удаляет книгу нужно найти эту книгу и засесить в поле
        // order_id NULL

        //todo удалить countOftheSameBooks
    }

    @Transactional
    @Override
    public Long delete(Long id) {
        return repository.deleteOrderById(id);
        //todo книги почистить order_id занулить
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
//todo создать две ручки апрув статус (книги удалить) и кансел(процесс) из всех книг ордер_айди стереть, (или одну ручки со свитчом)
    //todo сделать как у Рата в гите поиск по ФИО
}
