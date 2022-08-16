package com.halyk.bookstore.data.entity.user;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(min = 8)
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "is_blocked")
    private Boolean isBlocked;

}

