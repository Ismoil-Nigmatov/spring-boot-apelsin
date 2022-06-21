package com.example.springbootapelsin.service;

import com.example.springbootapelsin.dto.ProductDTO;
import com.example.springbootapelsin.entity.Category;
import com.example.springbootapelsin.entity.Product;
import com.example.springbootapelsin.repository.CategoryRepository;
import com.example.springbootapelsin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public void getAll(Model model) {
        List<Product> productList = productRepository.findAll();
        model.addAttribute("list", productList);
    }

    public void add(Model model, ProductDTO productDto) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setName(productDto.getName());
        product.setPrice(BigDecimal.valueOf(productDto.getPrice()));
        //1-variant
//        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCatId());
//        if (optionalCategory.isPresent()) {
//            Category category = optionalCategory.get();
//            product.setCategory(category);
//        }
        //yogini tanlay olmagani un mumkin
        product.setCategory(categoryRepository.getById(productDto.getCatId()));

        productRepository.save(product);

        model.addAttribute("list", productRepository.findAll());
    }

    public Product getById(int id) {
        return productRepository.findById(id).orElseThrow();
    }

    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

    public void edit(Integer id, Model model, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product editedProduct = optionalProduct.get();
            editedProduct.setName(product.getName());
            productRepository.save(editedProduct); // malumot qo'shish
        }
        model.addAttribute("list", productRepository.findAll(Sort.by("id")));
    }

    public void delete(Integer id, Model model) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
        }
        model.addAttribute("list",productRepository.findAll(Sort.by("id")));
    }
}

