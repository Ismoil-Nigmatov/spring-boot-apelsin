package com.example.springbootapelsin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "detail")
public class Detail {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "ord_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "pr_id")
    private Product product;

    @Column(nullable = false)
    private Short quantity;
}
