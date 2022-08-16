package com.halyk.bookstore.data.entity;


import com.halyk.bookstore.data.entity.user.User;
import com.halyk.bookstore.data.enums.OrderStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status = OrderStatusEnum.CREATED;

    @CreationTimestamp
    @Column(name = "create_dt")
    private OffsetDateTime dateTime;

    @Column(name = "user_id")
    private Long userID;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();



}
