package com.halyk.bookstore.service.impl;

import com.halyk.bookstore.data.entity.Book;
import com.halyk.bookstore.data.representation.GenreRepresentation;
import com.halyk.bookstore.data.request.GenreRequest;
import com.halyk.bookstore.data.entity.Genre;
import com.halyk.bookstore.data.mapper.GenreMapper;
import com.halyk.bookstore.data.repository.GenreRepository;
import com.halyk.bookstore.service.GenreService;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Data
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    private final GenreMapper mapper;

    @Transactional
    @Override
    public GenreRepresentation getGenreById(Long id) {
        Genre genre = repository.findByIdOrThrowException(id);
        return mapper.fromEntity(genre);
    }

    @Transactional
    @Override
    public List<GenreRepresentation> getAllGenre() {
        List<Genre> allGenre = repository.findAll();
        return allGenre.stream().map(mapper::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<GenreRepresentation> getGenreByName(String name) {
        List<Genre> allGenre = repository.findGenreByGenreName(name);
        return allGenre.stream().map(mapper::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public long saveGenre(GenreRequest dto) {
        Genre genre = mapper.toEntity(dto);
        Genre save = repository.save(genre);
        return save.getId();
    }

    @Transactional
    @Override
    public Long delete(Long id) {
        return repository.deleteGenreById(id);
    }

    @Transactional
    @Override
    public void updateGenre(GenreRequest dto, Long id) {
        Genre genre = repository.findByIdOrThrowException(id);
        mapper.updateEntity(genre, dto);
        repository.save(genre);
    }


}
