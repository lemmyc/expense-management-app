package com.lemmyc.em_app.controller;


import com.lemmyc.em_app.dto.JwtResponse;
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



        userDetailsService.addUser(user);
        return ResponseEntity.ok("User register successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
                )
        );
        if (authentication.isAuthenticated()){
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
            final String token = jwtService.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");

    }

}
