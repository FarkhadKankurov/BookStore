package com.halyk.bookstore.service.impl;

import com.halyk.bookstore.data.representation.GenreRepresentation;
import com.halyk.bookstore.data.request.GenreRequest;
import com.halyk.bookstore.data.entity.Genre;
import com.halyk.bookstore.data.mapper.GenreMapper;
import com.halyk.bookstore.data.repository.GenreRepository;
import com.halyk.bookstore.service.GenreService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    private final GenreMapper mapper;

    public GenreServiceImpl(GenreRepository repository, GenreMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public GenreRepresentation getGenreById(Long id) {
        Genre genre = repository.findByIdOrThrowException(id);
        return mapper.fromEntity(genre);
    }

    @Override
    public List<GenreRepresentation> getAllGenre() {
        List<Genre> allGenre = repository.findAll();
        return allGenre.stream().map(mapper::fromEntity).collect(Collectors.toList());
    }

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
