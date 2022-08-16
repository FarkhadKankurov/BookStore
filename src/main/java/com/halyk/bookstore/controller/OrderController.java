package com.halyk.bookstore.controller;

import com.halyk.bookstore.data.enums.OrderStatusEnum;
import com.halyk.bookstore.data.representation.OrderRepresentation;
import com.halyk.bookstore.data.request.OrderRequest;
import com.halyk.bookstore.service.OrderService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Data
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService service;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<OrderRepresentation> getOrderById(@PathVariable Long id) {
        OrderRepresentation dto = service.getOrderById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<OrderRepresentation>> getAllOrder() {
        List<OrderRepresentation> list = service.getAllOrder();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Long> saveOrder(@RequestBody OrderRequest dto) {
        long id = service.saveOrder(dto);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @RequestBody OrderRequest dto) {
        service.updateOrder(dto, id);
        return ResponseEntity.ok("Success");
    }

    @PutMapping("/changeStatus/{id}/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> changeOrderStatus(@PathVariable Long id, @PathVariable OrderStatusEnum status) {
        service.updateOrderStatus(id, status);
        return ResponseEntity.ok("Success");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Long> deleteOrder(@PathVariable Long id) {
        Long count = service.delete(id);
        return ResponseEntity.ok(count);
    }

}
