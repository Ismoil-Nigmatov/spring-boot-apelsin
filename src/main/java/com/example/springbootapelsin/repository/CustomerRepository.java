package com.example.springbootapelsin.repository;

import com.example.springbootapelsin.entity.Customer;
import com.example.springbootapelsin.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
