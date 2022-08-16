package com.halyk.bookstore.data.mapper;

import com.halyk.bookstore.data.representation.AuthorRepresentation;
import com.halyk.bookstore.data.representation.author_representation.AuthorRepresentationForGenreName;
import com.halyk.bookstore.data.request.AuthorRequest;
import com.halyk.bookstore.data.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;


@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AuthorMapper {
    Author toEntity(AuthorRequest dto);

    AuthorRepresentation fromEntity(Author author);
    AuthorRepresentationForGenreName fromEntityGenre(Author author);

    void updateEntity(@MappingTarget Author author, AuthorRequest dto);

}
