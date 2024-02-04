package com.project.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final SalesJdbcRepository salesJdbcRepository;
    private final SalesJpaRepository salesJpaRepository;

    @Autowired
    public Controller(SalesJdbcRepository salesJdbcRepository, SalesJpaRepository salesJpaRepository) {
        this.salesJdbcRepository = salesJdbcRepository;
        this.salesJpaRepository = salesJpaRepository;
    }

    @GetMapping("/countJdbc")
    public void countJdbc() {
        salesJdbcRepository.count();
    }

    @GetMapping("/countJpa")
    public void countJpa() {
        System.out.println(salesJpaRepository.count());
    }

    @GetMapping("/byIdJdbc")
    public void byIdJdbc(@RequestParam int id) {
        salesJdbcRepository.getInfoById(id);
    }

    @GetMapping("/byIdJpa")
    public void byIdJpa(@RequestParam int id) {
        SalesJpa salesJpa = salesJpaRepository.getReferenceById(id);
        System.out.println("id = " + salesJpa.getId());
        System.out.println("sum = " + salesJpa.getSum());
        System.out.println("admission date = " + salesJpa.getAdmissionDate());
        System.out.println("sale date = " + salesJpa.getSaleDate());
        System.out.println("product id = " + salesJpa.getProductId());
    }
}
