package com.example.springbootapelsin.service;

import com.example.springbootapelsin.dto.PaymentDTO;
import com.example.springbootapelsin.entity.Invoice;
import com.example.springbootapelsin.entity.Payment;
import com.example.springbootapelsin.repository.InvoiceRepository;
import com.example.springbootapelsin.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    InvoiceRepository invoiceRepository;
    public void getAll(Model model) {
        List<Payment> all = paymentRepository.findAll();
        model.addAttribute("list",all);
    }

    public void add(PaymentDTO paymentDTO, Model model) {
        Payment payment=new Payment();
        Invoice invoice = invoiceRepository.findById(paymentDTO.getInvoiceId()).orElseThrow(() -> new IllegalArgumentException("Not Found"));
        payment.setAmount(paymentDTO.getAmount());
        payment.setInvoice(invoice);
        paymentRepository.save(payment);

        model.addAttribute("list",paymentRepository.findAll());
    }

    public void delete(Integer id,Model model) {
        paymentRepository.deleteById(id);
        model.addAttribute("list",paymentRepository.findAll());
    }

    public void get(Integer id, Model model) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found"));
        PaymentDTO paymentDTO=new PaymentDTO();
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setInvoiceId(payment.getInvoice().getId());
        paymentDTO.setId(payment.getId());
        model.addAttribute("paymentDTO",paymentDTO);
    }

    public void saveEdited(Integer id, PaymentDTO paymentDTO, Model model) {
        Payment payment = paymentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not Found"));
        payment.setAmount(paymentDTO.getAmount());
        payment.setInvoice(invoiceRepository.findById(paymentDTO.getInvoiceId()).orElseThrow(() -> new IllegalArgumentException("Not Found")));
        paymentRepository.save(payment);
        model.addAttribute("list",paymentRepository.findAll());
    }
}
