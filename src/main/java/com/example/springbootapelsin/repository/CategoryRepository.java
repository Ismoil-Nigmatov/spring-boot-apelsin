package com.example.springbootapelsin.repository;

import com.example.springbootapelsin.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    //native queary -> sql
    //jpa query -> jpql

}
