package com.project.demo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SalesJdbc {
    private int id;
    private double sum;
    private LocalDate admissionDate;
    private LocalDate saleDate;
    private int productId;
}
