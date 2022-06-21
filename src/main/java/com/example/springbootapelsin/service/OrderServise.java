package com.example.springbootapelsin.service;

import com.example.springbootapelsin.dto.OrderDTO;
import com.example.springbootapelsin.entity.Detail;
import com.example.springbootapelsin.entity.Invoice;
import com.example.springbootapelsin.entity.Order;
import com.example.springbootapelsin.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServise {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    DetailRepository detailRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    InvoiceRepository invoiceRepository;
    public void addOrders(OrderDTO orderDTO, Model model) {
        Order order=new Order();
        order.setCustomer(customerRepository.getById(orderDTO.getCustomerId()));
        order.setDate(new Date());
        Order save = orderRepository.save(order);

        Detail detail=new Detail();
        detail.setOrder(save);
        detail.setProduct(productRepository.getById(orderDTO.getProductId()));
        detail.setQuantity(orderDTO.getAmount());
        detailRepository.save(detail);

        Invoice invoice=new Invoice();
        invoice.setOrder(save);
        BigDecimal price = detail.getProduct().getPrice();
        Short quantity = detail.getQuantity();
        invoice.setAmount(price.multiply(BigDecimal.valueOf(quantity)));
        invoice.setDue(new Date());
        invoiceRepository.save(invoice);
        model.addAttribute("list",orderRepository.findAll());
    }

    public void delete(Integer id, Model model) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not Found"));
        Invoice invoice = order.getInvoice();
        List<Detail> detailList = order.getDetail();
        Detail detail = detailList.get(0);
        detailRepository.delete(detail);
        invoiceRepository.delete(invoice);
        orderRepository.deleteById(id);
        model.addAttribute("list",orderRepository.findAll());
    }
}
