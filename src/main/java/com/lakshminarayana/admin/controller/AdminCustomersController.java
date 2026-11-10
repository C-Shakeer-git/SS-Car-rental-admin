package com.lakshminarayana.admin.controller;

import com.lakshminarayana.admin.model.Customer;
import com.lakshminarayana.admin.service.DataStoreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/customers")
public class AdminCustomersController {

    @Autowired
    private DataStoreService dataStoreService;

    @GetMapping
    public String listCustomers(@RequestParam(required = false) String search,
                                HttpSession session,
                                Model model) {
        if (session.getAttribute("adminUser") == null) return "redirect:/login";

        List<Customer> customers = dataStoreService.getAllCustomers();
        if (search != null && !search.isBlank()) {
            String s = search.toLowerCase();
            customers = customers.stream()
                    .filter(c -> (c.getFullName() != null && c.getFullName().toLowerCase().contains(s)) ||
                            (c.getEmail() != null && c.getEmail().toLowerCase().contains(s)) ||
                            (c.getPhone() != null && c.getPhone().contains(s)))
                    .toList();
            model.addAttribute("search", search);
        }

        model.addAttribute("customers", customers);
        return "customers";
    }

    @PostMapping("/verify-kyc")
    public String verifyKyc(@RequestParam String customerId,
                            @RequestParam String status,
                            @RequestParam(required = false) String remarks,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        if (session.getAttribute("adminUser") == null) return "redirect:/login";

        dataStoreService.updateCustomerKyc(customerId, status, remarks);
        redirectAttributes.addFlashAttribute("successMessage", "Customer " + customerId + " KYC set to " + status);
        return "redirect:/admin/customers";
    }
}

