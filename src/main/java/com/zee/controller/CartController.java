package com.zee.controller;


import com.zee.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.zee.service.impl.CartServiceImpl.CART;

@Controller
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String seeProductList(Model model){

        model.addAttribute("cartItemList", CART.getCartItemList());
        model.addAttribute("cart", CART);

        return "/cart/show-cart";
    }


}
