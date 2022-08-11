package com.halyk.bookstore.data.mapper;


import com.halyk.bookstore.data.representation.OrderRepresentation;
import com.halyk.bookstore.data.request.OrderRequest;
import com.halyk.bookstore.data.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface OrderMapper {
    Order toEntity(OrderRequest dto);
    OrderRepresentation fromEntity(Order order);

    void updateEntity(@MappingTarget Order order, OrderRequest dto);
}
