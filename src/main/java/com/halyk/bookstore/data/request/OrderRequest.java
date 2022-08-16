package com.halyk.bookstore.data.request;


import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private List<Long> list;
}
