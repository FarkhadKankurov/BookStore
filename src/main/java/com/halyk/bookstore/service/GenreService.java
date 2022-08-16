package com.halyk.bookstore.service;

import com.halyk.bookstore.data.representation.GenreRepresentation;
import com.halyk.bookstore.data.request.GenreRequest;

import java.util.List;

public interface GenreService {
    GenreRepresentation getGenreById(Long id);

    List<GenreRepresentation> getAllGenre();

    List<GenreRepresentation> getGenreByName(String name);

    long saveGenre(GenreRequest dto);

    Long delete(Long id);

    void updateGenre(GenreRequest dto, Long id);
}
