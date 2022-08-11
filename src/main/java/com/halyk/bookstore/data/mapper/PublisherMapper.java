package com.halyk.bookstore.data.mapper;

import com.halyk.bookstore.data.representation.PublisherRepresentation;
import com.halyk.bookstore.data.request.PublisherRequest;
import com.halyk.bookstore.data.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PublisherMapper {
    Publisher toEntity(PublisherRequest publisherDto);
    PublisherRepresentation fromEntity(Publisher publisher);

    void updateEntity(@MappingTarget Publisher publisher, PublisherRequest publisherDto);
}
