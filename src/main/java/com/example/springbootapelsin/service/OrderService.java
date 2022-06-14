package com.example.springbootapelsin.service;

import com.example.springbootapelsin.dto.OrderDTO;
import com.example.springbootapelsin.entity.Detail;
import com.example.springbootapelsin.entity.Invoice;
import com.example.springbootapelsin.entity.Order;
import com.example.springbootapelsin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DetailRepository detailRepository;
    @Autowired
    InvoiceRepository invoiceRepository;

    public void add(OrderDTO orderDTO, Model model) {
        //zakazni bazga yozish kk Invoice Detail Order
        Order order = new Order();
        order.setCustomer(customerRepository.getById(orderDTO.getCustomerId()));
        order.setDate(new Date());
        Order save = orderRepository.save(order);

        Detail detail = new Detail();
        detail.setOrder(save);
        detail.setProduct(productRepository.getById(orderDTO.getProductId()));
        detail.setQuantity(orderDTO.getAmount());
        Detail detail1 = detailRepository.save(detail);

        Invoice invoice = new Invoice();
        invoice.setOrder(save);
        BigDecimal price = detail1.getProduct().getPrice();
        Short quantity = detail1.getQuantity();
        invoice.setAmount(price.multiply(BigDecimal.valueOf(quantity)));
        invoice.setDue(new Date());
        invoiceRepository.save(invoice);
    }

    public void delete(Integer id,Model model) {
        Optional<Order> optionalCategory = orderRepository.findById(id);
        if (optionalCategory.isPresent()) {
            orderRepository.deleteById(id);
        }
        model.addAttribute("list",orderRepository.findAll());

    }
}
