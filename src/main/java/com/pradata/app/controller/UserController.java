package com.pradata.app.controller;


import com.pradata.app.dto.RegistrationDto;
import com.pradata.app.model.User;
import com.pradata.app.service.JwtService;
import com.pradata.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;

    @PostMapping("/register")
    public User register(@RequestBody RegistrationDto dto) {
        return userService.saveUserWithRole(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginDto) {
        String username = loginDto.getUsername();
        String role = userService.getRole(username);
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, loginDto.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(username, role);
        }
        return "Login Failed";
    }
}
