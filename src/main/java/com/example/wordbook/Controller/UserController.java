package com.example.wordbook.Controller;


import com.example.wordbook.Domain.User;
import com.example.wordbook.Exception.AuthException.ResourceNotFoundException;
import com.example.wordbook.Repository.jparepository.UserRepository;
import com.example.wordbook.Security.Oauth2.CurrentUser;
import com.example.wordbook.Security.Oauth2.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}