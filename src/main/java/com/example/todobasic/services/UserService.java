package com.example.todobasic.services;

import com.example.todobasic.entity.User;
import com.example.todobasic.repository.UserRepository;
import com.example.todobasic.dto.Usedto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created on February, 2021
 *
 * @author tolga
 */

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(Usedto usedto) {
        User user = new User();
        user.setUsername(usedto.getUsername());
        user.setPassword(passwordEncoder.encode(usedto.getPassword()));
        userRepository.save(user);
    }

    public Optional<String> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return Optional.of(authentication.getName());
        }
        return  Optional.empty();
    }

    public Optional<Long> getCurrentUserId() {
        Optional<String> currentUser = getCurrentUser();
        if (currentUser.isPresent()) {
            return userRepository.getByUsername(currentUser.get()).map(User::getId);
        }
        return Optional.empty();
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.getByUsername(username);
    }
}
