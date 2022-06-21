package com.example.springbootapelsin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PaymentDTO {
    private Integer id;

    private BigDecimal amount;

    private Integer invoiceId;
}
