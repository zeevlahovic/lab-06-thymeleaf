package com.zee.controller;


import com.zee.model.Product;
import com.zee.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/list")
    public String showProductList(Model model) {

        model.addAttribute("productList",productRepository.findAll());

        return "/product/list";
    }
    @GetMapping("/create-form")
    public String createProductForm(Model model){

        model.addAttribute("product", new Product());

        return "/product/create-product";
    }

    @PostMapping("/create-form")
    public String createProduct(@ModelAttribute Product product){

        productRepository.save(product);

        return "redirect:/list";
    }
}


