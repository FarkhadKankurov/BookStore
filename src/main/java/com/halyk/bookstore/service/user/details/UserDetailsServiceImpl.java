package com.halyk.bookstore.service.user.details;

import com.halyk.bookstore.data.entity.user.User;
import com.halyk.bookstore.data.repository.user.UserRepository;
import com.halyk.bookstore.data.user.UserDetailsImpl;
import com.halyk.bookstore.exception.IncorrectlyID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Objects;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("Not Found " + username);
        }
        return new UserDetailsImpl(userOpt.get());
    }

    public void updateUser(Long id, User user) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User usr = null;
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            usr = userRepository.findUserByUsername(username);     // из контекста получаем user

        }
        if (!Objects.equals(usr, null)) {
            if (!Objects.equals(getUserIDFromContext(), id)) {
                throw new IncorrectlyID("Некорректно введен id");
            }
        }

        User user1 = userRepository.findById(id).get();
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private Long getUserIDFromContext() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user;
        Long userId = null;
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            user = userRepository.findUserByUsername(username);     //получаем userID
            userId = user.getId();
        }
        return userId;
    }

}
