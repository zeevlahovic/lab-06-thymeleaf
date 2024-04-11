package com.zee.service.impl;

import com.zee.model.Cart;
import com.zee.model.CartItem;
import com.zee.model.Product;
import com.zee.service.CartService;
import com.zee.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    public static Cart CART = new Cart(BigDecimal.ZERO, new ArrayList<>());

    private final ProductService productService;

    public CartServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Cart addToCart(UUID productId, Integer quantity) {
        Product product = productService.findProductById(productId);
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setTotalAmount(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        CART.getCartItemList().add(cartItem);
        CART.setCartTotalAmount(CART.getCartTotalAmount().add(cartItem.getTotalAmount()));
        return CART;
    }

    @Override
    public boolean deleteFromCart(UUID productId) {
        CartItem itemToRemove = CART.getCartItemList().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findAny().orElseThrow();
        CART.setCartTotalAmount(CART.getCartTotalAmount().subtract(itemToRemove.getTotalAmount()));
       return CART.getCartItemList().remove(itemToRemove);

    }
}
