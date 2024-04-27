package com.diallo.mockExam.controller;


import ch.qos.logback.core.subst.Token;
import com.diallo.mockExam.dto.Credential;
import com.diallo.mockExam.model.User;
import com.diallo.mockExam.security.JwtService;
import com.diallo.mockExam.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/v1/users/auth")
@Slf4j
public class UserController {


    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        log.info("Adding user: " + user);
        System.out.println("Test");
        System.out.println(user);
        return ResponseEntity.ok(userService.adduser(user));
    }


    @PostMapping("/login")
    public ResponseEntity<Credential> loginUser(@RequestBody User user) {
//        System.out.println("-- user");
//        System.out.print(user);
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                user.getUsername(), user.getPassword()
                        ));
        if(authentication.isAuthenticated()) {
            Credential credential = new Credential(jwtService.generateToken(user.getUsername()));
            return  ResponseEntity.ok(credential);
        }

        else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

    }



}
