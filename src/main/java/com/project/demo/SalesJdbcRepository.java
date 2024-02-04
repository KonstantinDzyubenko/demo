package com.project.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SalesJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SalesJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void count() {
        System.out.println(jdbcTemplate.queryForObject("SELECT COUNT(*) FROM public.sales;", Integer.class));
    }

    public void getInfoById(int id) {
        SalesJdbc salesJdbc = jdbcTemplate.queryForObject("SELECT * FROM public.sales WHERE id = " + id + ";", new BeanPropertyRowMapper<>(SalesJdbc.class));
        System.out.println("id = " + salesJdbc.getId());
        System.out.println("sum = " + salesJdbc.getSum());
        System.out.println("admission date = " + salesJdbc.getAdmissionDate());
        System.out.println("sale date = " + salesJdbc.getSaleDate());
        System.out.println("product id = " + salesJdbc.getProductId());
    }
}
