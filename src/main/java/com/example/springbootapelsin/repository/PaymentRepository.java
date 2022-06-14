package com.example.springbootapelsin.repository;

import com.example.springbootapelsin.entity.Payment;
import com.example.springbootapelsin.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

}
