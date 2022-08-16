package com.halyk.bookstore.service;

import com.halyk.bookstore.data.representation.PublisherRepresentation;
import com.halyk.bookstore.data.request.PublisherRequest;

import java.util.List;

public interface PublisherService {

    PublisherRepresentation getPublisherById(Long id);

    List<PublisherRepresentation> getAllPublisher();

    Long savePublisher(PublisherRequest publisherDto);

    PublisherRepresentation getPublisherByName(String name);

    void updatePublisher(Long id, PublisherRequest dto);

    Long delete(Long id);

    List<PublisherRepresentation> getAuthorByPartName(String name);
}
