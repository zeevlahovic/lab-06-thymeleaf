package com.zee.controller;


import org.springframework.stereotype.Controller;

@Controller
public class CartController {

    public String seeCart() {



        return "/cart/show-cart";
    }
}
