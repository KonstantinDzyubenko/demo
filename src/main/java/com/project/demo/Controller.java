package com.project.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

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
        System.out.println(salesJpa.toString());
    }

    @GetMapping("/bySumJdbc")
    public void bySumJdbc(@RequestParam double sum) {
        salesJdbcRepository.getInfoByGreaterSum(sum);
    }

    @GetMapping("/bySumJpa")
    public void bySumJpa(@RequestParam double sum) {
        List<SalesJpa> list = salesJpaRepository.findAll();
        for (SalesJpa sale : list) {
            if (sale.getSum() > sum) {
                System.out.println(sale.toString());
            }
        }
    }

    @PostMapping("/insertJdbc")
    public void insertJdbc(@RequestParam LocalDate admissionDate,
                           @RequestParam int productId,
                           @RequestParam LocalDate saleDate,
                           @RequestParam double sum) {
        SalesJdbc sale = new SalesJdbc();
        sale.setAdmissionDate(admissionDate);
        sale.setProductId(productId);
        sale.setSaleDate(saleDate);
        sale.setSum(sum);
        salesJdbcRepository.addSaleInfo(sale);
    }

    @PostMapping("/insertJpa")
    public void insertJpa(@RequestParam LocalDate admissionDate,
                          @RequestParam int productId,
                          @RequestParam LocalDate saleDate,
                          @RequestParam double sum) {
        SalesJpa sale = new SalesJpa();
        sale.setAdmissionDate(admissionDate);
        sale.setProductId(productId);
        sale.setSaleDate(saleDate);
        sale.setSum(sum);
        salesJpaRepository.save(sale);
    }
}
