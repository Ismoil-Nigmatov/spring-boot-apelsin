package com.example.springbootapelsin.controller;

import com.example.springbootapelsin.entity.Category;
import com.example.springbootapelsin.entity.Customer;
import com.example.springbootapelsin.repository.CustomerRepository;
import com.example.springbootapelsin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/list")
    public String getAll(Model model) {
        customerService.list(model);
        return "customer/list"; //page nomi -> list.html templates
    }

    @GetMapping("/add")
    public String add(){
        return "customer/add";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer,Model model) {
        customerService.save(customer, model);
        return "customer/list";
    }

    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Integer id, Model model) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id not found!" + id));
        model.addAttribute("customer", customer);
        return "category/edit";
    }

    @PostMapping("/edit/{id}")
    public String editCustomer(@PathVariable Integer id, @ModelAttribute Customer customer,Model model){
        customerService.edit(id,customer,model);
        return "customer/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id,Model model){
        customerService.delete(id,model);
        return "customer/list";
    }
}
