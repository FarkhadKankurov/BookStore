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
    private OrderStatusEnum status;

    @CreationTimestamp
    @Column(name = "create_dt")
    private OffsetDateTime dateTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
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
//- Сумма одного заказа не должна привышать 10К, если превышает то выдать соответсвующую ошибку
//- Заказы могут создавать только не заблокированные пользователи

    //todo в таблице книга создать внешний ключ ордер_айди
    //todo создать enum статус заказа, гуглить как сохранить enum в поле ентити;
    // добавить поле date в ордер (сохранить дату созданию) offsetdatetime (now)

    //todo когда приходит список айдиКниг достать из базы каждую книгу, и записать туда ордер айди если оно пусто,
    // если не пусто кинуть ошибку, во время этого изменять  сумму (общую за все книги), если больше 10к > ошибка

    //todo в сущность ордер добавить поля user_id (из security context holder либо через базу) -----

    // если выполненнно удалять все книги
    // проверить все ручки

}
