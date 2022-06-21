package com.example.springbootapelsin.controller;

import com.example.springbootapelsin.dto.OrderDTO;
import com.example.springbootapelsin.repository.CustomerRepository;
import com.example.springbootapelsin.repository.DetailRepository;
import com.example.springbootapelsin.repository.OrderRepository;
import com.example.springbootapelsin.repository.ProductRepository;
import com.example.springbootapelsin.service.OrderServise;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    OrderServise orderServise;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final DetailRepository detailRepository;


    @GetMapping
    public String getOrder(Model model){
       model.addAttribute("list",orderRepository.findAll());
        return "order/list";
    }
    @GetMapping("/add")
    public String addOrderPage(Model model){
        model.addAttribute("customerList",customerRepository.findAll());
        model.addAttribute("productList",productRepository.findAll());
        return "order/add";
    }
    @PostMapping("/add")
    public String addOrder(@ModelAttribute OrderDTO orderDTO, Model model){
        orderServise.addOrders(orderDTO,model);
        return "redirect:";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Integer id, Model model){
        orderServise.delete(id,model);
        return "order/list";
    }

}
