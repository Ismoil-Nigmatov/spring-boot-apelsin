package com.example.springbootapelsin.controller;

import com.example.springbootapelsin.dto.PaymentDTO;
import com.example.springbootapelsin.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @GetMapping("/list")
    public String paymentList(Model model){
        paymentService.getAll(model);
        return "payment/list";
    }

    @GetMapping("/add")
    public String addPayment(){
        return "payment/add";
    }

    @PostMapping("/add")
    public String addPayment(@ModelAttribute PaymentDTO paymentDTO,Model model){
        paymentService.add(paymentDTO,model);
        return "payment/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Integer id,Model model){
        paymentService.delete(id,model);
        return "payment/list";
    }

    @GetMapping("/edit/{id}")
    public String editPayment(@PathVariable Integer id,Model model){
        paymentService.get(id,model);
        return "payment/edit";
    }

    @PostMapping("/edit/{id}")
    public String saveEditPayment(@PathVariable Integer id,@ModelAttribute PaymentDTO paymentDTO,Model model){
        paymentService.saveEdited(id,paymentDTO,model);
        return "payment/list";
    }
}
