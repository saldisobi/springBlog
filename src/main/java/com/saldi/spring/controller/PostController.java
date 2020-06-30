package com.saldi.spring.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @PostMapping
    public void createPost(@RequestMapping BlogRequest blogRequest){

    }
}
