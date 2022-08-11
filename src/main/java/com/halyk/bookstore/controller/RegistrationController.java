package com.halyk.bookstore.controller;

import com.halyk.bookstore.data.entity.user.User;
import com.halyk.bookstore.data.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@RestController
public class RegistrationController {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @GetMapping("/user")
    public User getUser(){
        return userRepository.findById(1L).get();
    }

    @Transactional
    @PostMapping("/api/register")
    public ResponseEntity<?> regUser(@Validated @RequestBody User user) {
        Optional<User> checkUser = userRepository.findByUsername(user.getUsername());
        if (checkUser.isPresent())
            throw new EntityExistsException("Username: " + user.getUsername() + " is already exist");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setIsBlocked(Boolean.FALSE);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
