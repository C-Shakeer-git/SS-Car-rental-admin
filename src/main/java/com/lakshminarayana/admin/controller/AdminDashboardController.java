package com.lakshminarayana.admin.controller;

import com.lakshminarayana.admin.service.AdminDashboardService;
import com.lakshminarayana.admin.service.GoogleSheetsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class AdminDashboardController {

    @Autowired
    private AdminDashboardService dashboardService;

    @Autowired
    private GoogleSheetsService googleSheetsService;

    @GetMapping("/admin/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("adminUser") == null) {
            return "redirect:/login";
        }

        Map<String, Object> metrics = dashboardService.getDashboardMetrics();
        model.addAllAttributes(metrics);
        model.addAttribute("googleSheetsConnected", googleSheetsService.isConnected());
        model.addAttribute("spreadsheetId", googleSheetsService.getSpreadsheetId());

        return "dashboard";
    }
}

