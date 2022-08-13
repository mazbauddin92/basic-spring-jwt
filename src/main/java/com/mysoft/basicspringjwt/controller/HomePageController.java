package com.mysoft.basicspringjwt.controller;

import com.mysoft.basicspringjwt.config.ApiResponse;
import com.mysoft.basicspringjwt.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomePageController {
    @Autowired
    JwtUserDetailsService jUserDetailsService;

    @RequestMapping({"/home"})
    public ResponseEntity<?> homePage() {
        return new ResponseEntity("You are Authentic",HttpStatus.OK);

    }

    @RequestMapping({"/get"})
    public ResponseEntity<?> get() {
        return new ResponseEntity(new ApiResponse(true, "Data Found", 1),HttpStatus.OK);
    }

    @RequestMapping({"/getall"})
    public ResponseEntity<?> getAll() {
        List<Integer> data =jUserDetailsService.getList();
        return new ResponseEntity(new ApiResponse(true, "Data Found", data),HttpStatus.OK);
    }
}
