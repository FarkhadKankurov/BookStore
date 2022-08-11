package com.halyk.bookstore.service;

import com.halyk.bookstore.data.representation.OrderRepresentation;
import com.halyk.bookstore.data.request.OrderRequest;

import java.util.List;

public interface OrderService {
    OrderRepresentation getOrderById(Long id);

    List<OrderRepresentation> getAllOrder();

    long saveOrder(OrderRequest dto);

    void updateOrder(OrderRequest dto, Long id);

    Long delete(Long id);

    void updateOrderStatus(Long id);
}