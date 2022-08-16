package com.halyk.bookstore.data.mapper;

import com.halyk.bookstore.data.request.BookRequest;
import com.halyk.bookstore.data.entity.Book;
import com.halyk.bookstore.data.representation.BookRepresentation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BookMapper {
    @Mapping(target = "publisher.id", source = "publisherId")
    Book toEntity(BookRequest dto);

    BookRepresentation fromEntity(Book book);

    @Mapping(target = "publisher.id", source = "publisherId")
    void updateEntity(@MappingTarget Book book, BookRequest dto);
}
