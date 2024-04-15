package com.zee.controller;


import com.zee.model.Product;
import com.zee.repository.ProductRepository;
import com.zee.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping("/list")
    public String showProductList(Model model) {

        model.addAttribute("productList", productService.listProducts());

        return "product/list";
    }

    @GetMapping("/create-form")
    public String createProductForm(Model model) {

        model.addAttribute("product", new Product());

        return "product/create-product";
    }

    @PostMapping("/create-product")
    public String createProduct(@ModelAttribute Product product, Model model) {

        productService.productCreate(product);
        // productRepository.save(product);
        model.addAttribute("productList", productService.listProducts());

        return "product/list";
    }

}


