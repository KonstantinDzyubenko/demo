package com.project.demo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "sales")
public class SalesJpa {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sum", nullable = false)
    private double sum;
    @Column(name = "admission_date", nullable = false)
    private LocalDate admissionDate;
    @Column(name = "sale_date", nullable = false)
    private LocalDate saleDate;
    @Column(name = "product_id", nullable = false)
    private int productId;
}
