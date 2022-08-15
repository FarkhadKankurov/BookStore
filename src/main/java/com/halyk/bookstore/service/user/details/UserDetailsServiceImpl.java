package com.halyk.bookstore.service.user.details;

import com.halyk.bookstore.data.entity.user.User;
import com.halyk.bookstore.data.repository.user.UserRepository;
import com.halyk.bookstore.data.user.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        User user1 = userRepository.findById(id).get();
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
