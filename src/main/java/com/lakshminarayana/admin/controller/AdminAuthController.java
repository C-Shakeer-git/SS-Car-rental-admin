package com.lakshminarayana.admin.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminAuthController {

    private static final String ADMIN_EMAIL = "admin@lntravels.com";
    private static final String ADMIN_PASS = "admin123";

    @GetMapping({"/", "/login"})
    public String loginPage(HttpSession session) {
        if (session.getAttribute("adminUser") != null) {
            return "redirect:/admin/dashboard";
        }
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String email,
                              @RequestParam String password,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        if (ADMIN_EMAIL.equalsIgnoreCase(email.trim()) && ADMIN_PASS.equals(password)) {
            session.setAttribute("adminUser", ADMIN_EMAIL);
            session.setAttribute("adminName", "LNT Operations Manager");
            redirectAttributes.addFlashAttribute("successMessage", "Welcome to Lakshmi Narayana Travels Administration Console!");
            return "redirect:/admin/dashboard";
        }
        redirectAttributes.addFlashAttribute("errorMessage", "Invalid Admin credentials. Please check your staff username/password.");
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("infoMessage", "Admin session ended securely.");
        return "redirect:/login";
    }
}

