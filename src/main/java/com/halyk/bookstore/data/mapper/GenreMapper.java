package com.halyk.bookstore.data.mapper;

import com.halyk.bookstore.data.representation.GenreRepresentation;
import com.halyk.bookstore.data.request.GenreRequest;
import com.halyk.bookstore.data.entity.Genre;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface GenreMapper {

    Genre toEntity(GenreRequest dto);

    GenreRepresentation fromEntity(Genre genre);
}
