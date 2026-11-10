package com.lakshminarayana.admin.controller;

import com.lakshminarayana.admin.model.Booking;
import com.lakshminarayana.admin.service.DataStoreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/bookings")
public class AdminBookingsController {

    @Autowired
    private DataStoreService dataStoreService;

    @GetMapping
    public String listBookings(@RequestParam(required = false) String status,
                               @RequestParam(required = false) String startDate,
                               @RequestParam(required = false) String endDate,
                               HttpSession session,
                               Model model) {
        if (session.getAttribute("adminUser") == null) return "redirect:/login";

        List<Booking> bookings = dataStoreService.filterBookings(status, startDate, endDate);
        model.addAttribute("bookings", bookings);
        model.addAttribute("selectedStatus", status);
        model.addAttribute("selectedStartDate", startDate);
        model.addAttribute("selectedEndDate", endDate);
        model.addAttribute("totalCount", bookings.size());
        return "bookings";
    }

    @PostMapping("/status")
    public String updateStatus(@RequestParam String bookingId,
                               @RequestParam String status,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        if (session.getAttribute("adminUser") == null) return "redirect:/login";

        dataStoreService.updateBookingStatus(bookingId, status);
        redirectAttributes.addFlashAttribute("successMessage", "Order #" + bookingId + " status updated to " + status);
        return "redirect:/admin/bookings";
    }

    @GetMapping("/invoice/{id}")
    public String viewInvoice(@PathVariable String id, HttpSession session, Model model) {
        if (session.getAttribute("adminUser") == null) return "redirect:/login";

        Booking booking = dataStoreService.getBookingById(id).orElse(null);
        model.addAttribute("booking", booking);
        return "invoice-view";
    }
}

