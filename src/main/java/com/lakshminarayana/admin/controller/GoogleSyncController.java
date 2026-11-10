package com.lakshminarayana.admin.controller;

import com.lakshminarayana.admin.service.DataStoreService;
import com.lakshminarayana.admin.service.GoogleDriveService;
import com.lakshminarayana.admin.service.GoogleSheetsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/google-sync")
public class GoogleSyncController {

    @Autowired
    private GoogleSheetsService googleSheetsService;

    @Autowired
    private GoogleDriveService googleDriveService;

    @Autowired
    private DataStoreService dataStoreService;

    @GetMapping
    public String syncCenter(HttpSession session, Model model) {
        if (session.getAttribute("adminUser") == null) return "redirect:/login";

        model.addAttribute("sheetsEnabled", googleSheetsService.isEnabled());
        model.addAttribute("sheetsConnected", googleSheetsService.isConnected());
        model.addAttribute("driveConnected", googleDriveService.isConnected());
        model.addAttribute("spreadsheetId", googleSheetsService.getSpreadsheetId());
        model.addAttribute("credentialsPath", googleSheetsService.getCredentialsPath());

        return "google-sync";
    }

    @PostMapping("/trigger")
    public String triggerSync(HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("adminUser") == null) return "redirect:/login";

        boolean success = dataStoreService.syncAllToGoogleSheets();
        if (success) {
            redirectAttributes.addFlashAttribute("successMessage", "Data successfully synchronized to Google Sheets in real-time!");
        } else {
            redirectAttributes.addFlashAttribute("infoMessage", "Data is synchronized with the shared database engine. To connect live to Google Sheets, set google.sheets.enabled=true and add credentials.json.");
        }
        return "redirect:/admin/google-sync";
    }
}

