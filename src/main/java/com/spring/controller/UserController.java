package com.spring.controller;

import com.spring.entity.AppUser;
import com.spring.payload.LoginDto;
import com.spring.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(
            @RequestBody AppUser appUser
            ){
        return userService.createUser(appUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @RequestBody LoginDto loginDto
            ){
        String token = userService.loginUser(loginDto);
        if(token!=null){
            return new ResponseEntity<>(token, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid username/Password", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping
    public String dummy(){
        return "dummy";
    }

}
