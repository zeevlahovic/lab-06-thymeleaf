package com.zee.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ProductController {

    @GetMapping("/list")
    public String showProductList() {

        return "/product/list";
    }
}


