package com.halyk.bookstore.service.impl;

import com.halyk.bookstore.data.entity.Book;
import com.halyk.bookstore.data.repository.BookRepository;
import com.halyk.bookstore.data.representation.AuthorRepresentation;
import com.halyk.bookstore.data.request.AuthorRequest;
import com.halyk.bookstore.data.entity.Author;
import com.halyk.bookstore.data.mapper.AuthorMapper;
import com.halyk.bookstore.data.repository.AuthorRepository;
import com.halyk.bookstore.data.request.BookRequest;
import com.halyk.bookstore.service.AuthorService;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.function.Supplier;
import java.util.stream.Collectors;




@Service
@Data
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final AuthorMapper mapper;

    private CriteriaBuilder builder;
    private Map<String, Supplier<?>> map;
    private final EntityManager em;


    private void initMap(AuthorRequest dto) {
        if (map == null)
            map = new HashMap<>();

        map.put("firstName", dto::getFirstName);
        map.put("lastName", dto::getLastName);
        map.put("patronymicName", dto::getPatronymicName);
    }

    private CriteriaQuery<Author> buildCriteria(AuthorRequest dto) {
        builder = em.getCriteriaBuilder();
        CriteriaQuery<Author> criteria = builder.createQuery(Author.class);
        Root<Author> authorRoot = criteria.from(Author.class);
        initMap(dto);
        List<Predicate> predicatesList = map.entrySet().stream()
                .filter(pair -> pair.getValue().get() != null)
                .map(pair -> builder.equal(authorRoot.get(pair.getKey()), pair.getValue().get()))
                .toList();
        Predicate[] predicates = new Predicate[predicatesList.size()];
        predicatesList.toArray(predicates);
        criteria.where(predicates);
        return criteria;
    }
//
////    @Override
    public List<Author> allByTerms(AuthorRequest dto) {
        CriteriaQuery<Author> criteria = buildCriteria(dto);
        TypedQuery<Author> query = em.createQuery(criteria);
        return query.getResultList();
    }

    @Transactional
    @Override
    public AuthorRepresentation getAuthorById(Long id) {
        Author author = authorRepository.findByIdOrThrowException(id);
        return mapper.fromEntity(author);
    }

    @Transactional
    @Override
    public List<AuthorRepresentation> getAllAuthor() {
        List<Author> list = authorRepository.findAll();
        return list.stream().map(mapper::fromEntity).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Long saveAuthor(AuthorRequest dto) {
        Author author = mapper.toEntity(dto);
        Author save = authorRepository.save(author);
        return save.getId();
    }


    @Transactional
    @Override
    public List<AuthorRepresentation> getAuthorByName(AuthorRequest dto) {
        List<Author> list = allByTerms(dto);
        List<AuthorRepresentation> result = new ArrayList<>();
        for (Author author : list) {
            result.add(mapper.fromEntity(author));
        }
        return result;
    }

    @Transactional
    @Override
    public Long delete(Long id) {
        return authorRepository.deleteAuthorById(id);
    }

    @Transactional
    @Override
    public List<AuthorRepresentation> getAuthorsByGenreName(String genreName) {
//        return authorRepository.findAuthorsByGenresEquals(genreName);  //todo проверить почему не работает
        return authorRepository.findAllByGenre(genreName);
    }

    @Transactional
    @Override
    public void updateAuthor(AuthorRequest dto, Long id) {
        Author author = authorRepository.findByIdOrThrowException(id);
        mapper.updateEntity(author, dto);
        authorRepository.save(author);
    }


}
