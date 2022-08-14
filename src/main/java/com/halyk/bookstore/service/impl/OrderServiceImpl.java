package com.halyk.bookstore.service.impl;

import com.halyk.bookstore.data.entity.Book;
import com.halyk.bookstore.data.enums.OrderStatusEnum;
import com.halyk.bookstore.data.repository.BookRepository;
import com.halyk.bookstore.data.repository.user.UserRepository;
import com.halyk.bookstore.data.representation.OrderRepresentation;
import com.halyk.bookstore.data.request.OrderRequest;
import com.halyk.bookstore.data.entity.Order;
import com.halyk.bookstore.data.mapper.OrderMapper;
import com.halyk.bookstore.data.repository.OrderRepository;
import com.halyk.bookstore.exception.BookReservedAnotherOrder;
import com.halyk.bookstore.exception.ExceedTotalCost;
import com.halyk.bookstore.exception.OrderStatusNotCorrect;
import com.halyk.bookstore.service.OrderService;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.halyk.bookstore.data.enums.OrderStatusEnum.*;

@Service
@Data
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper mapper;

    private final BookRepository bookRepository;

    private final UserRepository userRepository;

//    private final SecurityContextHolder



    @Transactional
    @Override
    public OrderRepresentation getOrderById(Long id) {
        Order order = orderRepository.findByIdOrThrowException(id);
        return mapper.fromEntity(order);
    }

    @Transactional
    @Override
    public List<OrderRepresentation> getAllOrder() {
        List<Order> allOrder = orderRepository.findAll();
        return allOrder.stream().map(mapper::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public long saveOrder(OrderRequest dto) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user;
//        if(principal instanceof UserDetails) {
//            String username = ((UserDetails) principal).getUsername();
//            user = userRepository.findUserByUsername(username);     //в случае использования isBloked
//            if (user.getIsBlocked()){
//                throw new Exception();
//
//            }
//        }

        int totalSum = 0;
        Order order = new Order();
        order.setStatus(OrderStatusEnum.CREATED);
        Order save = orderRepository.save(order);

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
        //todo посчитать totalSum 10000 (подумай)

        //todo сделать как в сейв методе учет зарезервированных книг
        Order order = orderRepository.findByIdOrThrowException(id);
        List<Long> booksIDInEntity = order.getBooks().stream().map(Book::getId).toList();
        List<Long> booksIDInRequest = dto.getList();
        List<Long> booksDelete = deleteNonExists(booksIDInEntity, booksIDInRequest);
        List<Long> booksUpdate = addNonExists(booksIDInRequest, booksIDInEntity);

        deleteOrderFromBooks(booksDelete);

        for (Long aLong : booksUpdate) {
            Book bookUpd = bookRepository.findByIdOrThrowException(aLong);
            bookUpd.setOrder(order);
            bookRepository.save(bookUpd);
        }
        orderRepository.save(order);
    }

    @Transactional
    @Override
    public Long delete(Long id) {
        Order order = orderRepository.findByIdOrThrowException(id);
        List<Long> booksIDInEntity = order.getBooks().stream().map(Book::getId).toList();
        deleteOrderFromBooks(booksIDInEntity);
        return orderRepository.deleteOrderById(id);
    }

    @Transactional
    @Override
    public void updateOrderStatus(Long id, OrderStatusEnum status) {
        Order order = orderRepository.findByIdOrThrowException(id);
        List<Long> booksIDInEntity = order.getBooks().stream().map(Book::getId).toList();
        switch (status) {
            case PROCESS -> order.setStatus(PROCESS);
            case EXECUTED -> {
                order.setStatus(EXECUTED);
                for (Long aLong : booksIDInEntity) {
                    bookRepository.deleteBookById(aLong); //todo сделать метод для листа deleteAllbookById(List<Long> list)
                }
            }
            case CANCELED -> {
                order.setStatus(CANCELED);
                deleteOrderFromBooks(booksIDInEntity);
            }
            default -> throw new OrderStatusNotCorrect("Статус: " + status + ", введен не корректно");
        }
        orderRepository.save(order);
    }

    private void deleteOrderFromBooks(List<Long> bookList) {
        for (Long aLong : bookList) {
            Book orderDel = bookRepository.findByIdOrThrowException(aLong);
            orderDel.setOrder(null);
            bookRepository.save(orderDel);
        }
    }

    private  List<Long> deleteNonExists(List<Long> bookIdOfExistOrder, List<Long> updatedList) {
        List<Long> result = new ArrayList<>();

        for (Long aLong : bookIdOfExistOrder) {
            if (!updatedList.contains(aLong)) {
                result.add(aLong);
            }   //todo создать из них один метод
        }
        return result;
    }

    private  List<Long> addNonExists(List<Long> updatedList, List<Long> bookIdOfExistOrder){
        List<Long> resultList = new ArrayList<>();

        for(int i = 0; i < bookIdOfExistOrder.size(); i++){
            if (!updatedList.contains(bookIdOfExistOrder.get(i))) {
                resultList.add(bookIdOfExistOrder.get(i));
            }
        }
        return resultList;
    }
}
