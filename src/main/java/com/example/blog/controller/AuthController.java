package com.example.blog.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"AUTH API"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
}