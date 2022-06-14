package com.example.springbootapelsin.repository;

import com.example.springbootapelsin.entity.Order;
import com.example.springbootapelsin.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {

}
