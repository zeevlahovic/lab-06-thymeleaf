package com.zee.controller;


import com.zee.service.CartService;
import com.zee.service.impl.CartServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.UUID;

import static com.zee.service.impl.CartServiceImpl.CART;

@Controller
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String seeProductList(Model model) {

        model.addAttribute("cart", CART);

        return "/cart/show-cart";
    }


    //addCart
    @GetMapping("/addToCart/{productId}/{quantity}")
    public String addToCart(@PathVariable UUID productId, @PathVariable Integer quantity,Model model) {
        cartService.addToCart(productId, quantity);
        model.addAttribute("cart", CART);
        return "redirect:/cart";
    }

    @GetMapping("/delete/{productId}")
    public String deleteCart(@PathVariable UUID productId, Model model) {
        cartService.deleteFromCart(productId);

        return "redirect:/cart";
    }
}
