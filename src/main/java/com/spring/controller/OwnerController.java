package com.spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/owner")
public class OwnerController {

    @GetMapping
    public String addOwner(HttpServletRequest request){
        return request.getSession().getId();
    }
}
