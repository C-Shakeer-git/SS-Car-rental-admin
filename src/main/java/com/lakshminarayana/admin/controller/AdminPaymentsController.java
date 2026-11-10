package com.lakshminarayana.admin.controller;

import com.lakshminarayana.admin.model.Payment;
import com.lakshminarayana.admin.service.DataStoreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/payments")
public class AdminPaymentsController {

    @Autowired
    private DataStoreService dataStoreService;

    @GetMapping
    public String listPayments(HttpSession session, Model model) {
        if (session.getAttribute("adminUser") == null) return "redirect:/login";

        List<Payment> payments = dataStoreService.getAllPayments();
        double totalRevenue = payments.stream()
                .filter(p -> "SUCCESS".equalsIgnoreCase(p.getStatus()))
                .mapToDouble(Payment::getAmount)
                .sum();

        model.addAttribute("payments", payments);
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("totalTransactions", payments.size());
        return "payments";
    }
}

