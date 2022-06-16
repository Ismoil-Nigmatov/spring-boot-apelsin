package com.example.springbootapelsin.service;

import com.example.springbootapelsin.entity.Category;
import com.example.springbootapelsin.entity.Customer;
import com.example.springbootapelsin.repository.CategoryRepository;
import com.example.springbootapelsin.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    public void save(Customer customer, Model model) {
        customerRepository.save(customer);
        model.addAttribute("list",customerRepository.findAll());
    }

    public void list(Model model) {
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("list",customers);
    }

    public void edit(Integer id, Model model, Customer customer) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer editedCustomer = optionalCustomer.get();
            editedCustomer.setName(customer.getName());
            editedCustomer.setCountry(customer.getCountry());
            editedCustomer.setAddress(customer.getAddress());
            editedCustomer.setPhone(customer.getPhone());
            customerRepository.save(editedCustomer); // malumot qo'shish
        }
        model.addAttribute("list", customerRepository.findAll(Sort.by("id")));
    }
    public void delete(Integer id, Model model) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            customerRepository.deleteById(id);
        }
        model.addAttribute("list", customerRepository.findAll(Sort.by("id")));
    }
}
