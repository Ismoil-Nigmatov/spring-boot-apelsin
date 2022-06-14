package com.example.springbootapelsin.controller;

import com.example.springbootapelsin.dto.ProductDTO;
import com.example.springbootapelsin.repository.CategoryRepository;
import com.example.springbootapelsin.repository.ProductRepository;
import com.example.springbootapelsin.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public String getAll(Model model) {
        productService.getAll(model);
        return "product/list";// page nomi list.html templates ni ichida
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("categoryList", categoryRepository.findAll());
        return "product/add"; //page nomi -> list.html templates
    }

    @PostMapping("/add")
    public String getSavePage(@ModelAttribute ProductDTO productDto, Model model) {
        productService.add(model, productDto);
        return "product/list";
    }
}
