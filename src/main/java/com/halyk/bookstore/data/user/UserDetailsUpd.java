package com.halyk.bookstore.data.user;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsUpd extends UserDetails {
    Long getId();
}
