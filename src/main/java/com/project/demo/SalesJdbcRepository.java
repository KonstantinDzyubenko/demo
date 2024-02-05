package com.project.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        System.out.println(salesJdbc.toString());
    }

    public void getInfoByGreaterSum(double sum) {
        List<SalesJdbc> list = jdbcTemplate.query("SELECT * FROM public.sales WHERE sum > " + sum + ";", new BeanPropertyRowMapper<>(SalesJdbc.class));
        for (SalesJdbc sale : list) {
            System.out.println(sale.toString());
        }
    }

    public void addSaleInfo(SalesJdbc sale) {
        jdbcTemplate.execute("INSERT INTO public.sales (admission_date, product_id, sale_date, sum) VALUES (" +
                "'" + sale.getAdmissionDate().getYear() + "-" + sale.getAdmissionDate().getMonthValue() + "-" + sale.getAdmissionDate().getDayOfMonth() + "', " +
                sale.getProductId() + ", " +
                "'" + sale.getSaleDate().getYear() + "-" + sale.getSaleDate().getMonthValue() + "-" + sale.getSaleDate().getDayOfMonth() + "', " +
                sale.getSum() + ");");
    }
}
