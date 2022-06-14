package com.example.springbootapelsin.repository;

import com.example.springbootapelsin.entity.Invoice;
import com.example.springbootapelsin.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {

}
