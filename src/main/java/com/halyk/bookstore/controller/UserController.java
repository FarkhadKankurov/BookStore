package com.halyk.bookstore.controller;

import com.halyk.bookstore.data.entity.user.User;
import com.halyk.bookstore.data.repository.user.UserRepository;
import com.halyk.bookstore.service.user.details.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.Optional;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserDetailsServiceImpl userService;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder encoder, UserDetailsServiceImpl userService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/user/{id}")
    public Optional<User> getUser(@PathVariable Long id){
        return userRepository.findById(id);
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

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/ublockuser/{login}")
    public ResponseEntity<?> UnblockUser(@Validated @PathVariable String login) {
        User user = userRepository.findUserByUsername(login);
        user.setRole("ROLE_USER");
        user.setIsBlocked(Boolean.FALSE);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/blockuser/{login}")
    public ResponseEntity<?> blockUser(@Validated @PathVariable String login) {
        User user = userRepository.findUserByUsername(login);
        user.setRole("ROLE_USERBLOCKED");
        user.setIsBlocked(Boolean.TRUE);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("api/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user){
        userService.updateUser(id, user);
        return "Success";
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("api/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return "Success";
    }
}
