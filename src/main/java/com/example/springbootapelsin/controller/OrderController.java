package com.example.springbootapelsin.controller;

import com.example.springbootapelsin.dto.OrderDTO;
import com.example.springbootapelsin.repository.CustomerRepository;
import com.example.springbootapelsin.repository.OrderRepository;
import com.example.springbootapelsin.repository.ProductRepository;
import com.example.springbootapelsin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
     OrderRepository orderRepository;
    @Autowired
     CustomerRepository customerRepository;
    @Autowired
     ProductRepository productRepository;
    @Autowired
     OrderService orderService;


    public void OrderService(){

    }

    @GetMapping
    public String getListPage(Model model) {
        model.addAttribute("list", orderRepository.findAll());
        return "order/list";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("customerList", customerRepository.findAll());
        model.addAttribute("productList", productRepository.findAll());
        return "order/add";
    }
    @PostMapping("/add")
    public String savePage(Model model,OrderDTO order){
        orderService.add(order,model);
        model.addAttribute("list",orderRepository.findAll());
        return "redirect:";
    }

    @GetMapping("/delete/{id}")
    public String deletePage(@PathVariable Integer id, Model model){
        orderService.delete(id,model);
        return "order/list";
    }
}
