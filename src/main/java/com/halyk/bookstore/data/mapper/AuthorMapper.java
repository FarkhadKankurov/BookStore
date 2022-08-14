package com.halyk.bookstore.data.mapper;

import com.halyk.bookstore.data.representation.AuthorRepresentation;
import com.halyk.bookstore.data.request.AuthorRequest;
import com.halyk.bookstore.data.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;



@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AuthorMapper {
    Author toEntity(AuthorRequest dto);
    AuthorRepresentation fromEntity(Author author);
//    List<AuthorRepresentation> fromEntityList(List<Author> authorList); //todo додедлать правильно
}
