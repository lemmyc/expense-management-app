package com.lemmyc.em_app.controller;


import com.lemmyc.em_app.dto.LoginRequest;
import com.lemmyc.em_app.model.User;
import com.lemmyc.em_app.security.JwtService;
import com.lemmyc.em_app.service.UserDetailsServiceImpl;
import com.lemmyc.em_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){



        Map<String, Object> response = new HashMap<>();
        try {
            userDetailsService.addUser(user);
            response.put("status", "OK");
            response.put("message", "User registered successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("status", "FAILURE");
            response.put("message", "User registration failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
        Map<String, Object> response = new HashMap<>();
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            if (authentication.isAuthenticated()){
                final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
                final String token = jwtService.generateToken(userDetails);

                response.put("status", "OK");
                response.put("token", token);
                return ResponseEntity.ok(response);
            }
            response.put("status", "FAILURE");
            response.put("message", "Incorrect username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }catch (Exception e){
            response.put("status", "FAILURE");
            response.put("message", "Authentication failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

}
