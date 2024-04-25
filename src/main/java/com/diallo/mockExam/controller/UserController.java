package com.diallo.mockExam.controller;


import ch.qos.logback.core.subst.Token;
import com.diallo.mockExam.dto.Credential;
import com.diallo.mockExam.model.User;
import com.diallo.mockExam.security.JwtService;
import com.diallo.mockExam.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {


    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
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
