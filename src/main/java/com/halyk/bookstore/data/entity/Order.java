package com.halyk.bookstore.data.entity;


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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();


//    добавить API для заказов пользователей
//
//    Заказ должен быть привязан к пользователю, в заказ входит список книг, заказ имеет статус
//    (4 допустимых значения: создан, в обработке, выполнен, отменен), нужно фиксировать время заказа
//
//    В итоге что должно быть:
//            - в спецификации добавлены CRUD ендпоинты по заказам
//- реализованны добавленные в спецификации методы в OrderContoller
//- сущность и репозиторий для работы с данными заказов из БД
//- OrderService который работает с репозиториями
//- таблица пользователей в БД
//
//    некоторые ограничения для заказа:
//            - В заказе могут быть указаны только актуальные книги (не удаленные)
//- Сумма одного заказа не должна превышать 10К, если превышает то выдать соответсвующую ошибку
//- Заказы могут создавать только не заблокированные пользователи

    // если выполненнно удалять все книги
    // проверить все ручки

}
