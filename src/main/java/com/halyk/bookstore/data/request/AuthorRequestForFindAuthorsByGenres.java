package com.halyk.bookstore.data.request;


import lombok.Data;

import java.util.List;

@Data
public class AuthorRequestForFindAuthorsByGenres {
    private List<Long> list;
}
