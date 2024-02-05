package com.project.demo;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final SalesJpaRepository salesJpaRepository;

    @GetMapping("/")
    public String homePageGet(Model model) {
        return "home";
    }

    @PostMapping("/")
    public String homePagePost(@RequestParam String userName, Model model, HttpSession session) {
        session.setAttribute("userName", userName);
        return "redirect:workplace";
    }

    @GetMapping("/workplace")
    public String workplaceGet(Model model) {
        List<SalesJpa> sales = salesJpaRepository.findAll();
        model.addAttribute("sales", sales);
        return "workplace";
    }

    @PostMapping("/addSale")
    public String addSale(@RequestParam LocalDate admissionDate,
                          @RequestParam int productId,
                          @RequestParam LocalDate saleDate,
                          @RequestParam double sum,
                          Model model) {
        SalesJpa sale = new SalesJpa();
        sale.setAdmissionDate(admissionDate);
        sale.setProductId(productId);
        sale.setSaleDate(saleDate);
        sale.setSum(sum);
        salesJpaRepository.save(sale);
        return "redirect:workplace";
    }
}
