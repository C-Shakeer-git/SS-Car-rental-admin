package com.lakshminarayana.admin.controller;

import com.lakshminarayana.admin.model.Booking;
import com.lakshminarayana.admin.model.Car;
import com.lakshminarayana.admin.model.Payment;
import com.lakshminarayana.admin.service.DataStoreService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/admin/export")
public class AdminExportController {

    @Autowired
    private DataStoreService dataStoreService;

    @GetMapping("/bookings")
    public void exportBookingsCsv(HttpSession session, HttpServletResponse response) throws IOException {
        if (session.getAttribute("adminUser") == null) {
            response.sendRedirect("/login");
            return;
        }

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"LNT_Bookings_Report.csv\"");

        PrintWriter writer = response.getWriter();
        writer.println("BookingID,CustomerID,CustomerName,CustomerEmail,CustomerPhone,CarName,PickupDate,DropDate,PickupLocation,DropLocation,TotalDays,BaseAmount,AddOnAmount,GSTAmount,TotalAmount,Status,PaymentStatus,PaymentMethod,TransactionRef,CreatedAt");

        List<Booking> bookings = dataStoreService.getAllBookings();
        for (Booking b : bookings) {
            writer.printf("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",%d,%.2f,%.2f,%.2f,%.2f,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"\n",
                    escape(b.getId()), escape(b.getCustomerId()), escape(b.getCustomerName()),
                    escape(b.getCustomerEmail()), escape(b.getCustomerPhone()), escape(b.getCarName()),
                    escape(b.getPickupDate()), escape(b.getDropDate()), escape(b.getPickupLocation()),
                    escape(b.getDropLocation()), b.getTotalDays(), b.getBaseAmount(), b.getAddOnAmount(),
                    b.getGstAmount(), b.getTotalAmount(), escape(b.getStatus()), escape(b.getPaymentStatus()),
                    escape(b.getPaymentMethod()), escape(b.getTransactionId()), escape(b.getCreatedAt())
            );
        }
        writer.flush();
    }

    @GetMapping("/payments")
    public void exportPaymentsCsv(HttpSession session, HttpServletResponse response) throws IOException {
        if (session.getAttribute("adminUser") == null) {
            response.sendRedirect("/login");
            return;
        }

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"LNT_Payments_Ledger.csv\"");

        PrintWriter writer = response.getWriter();
        writer.println("PaymentID,BookingID,CustomerName,CustomerEmail,Amount,PaymentMethod,TransactionRef,PaymentDate,Status");

        List<Payment> payments = dataStoreService.getAllPayments();
        for (Payment p : payments) {
            writer.printf("\"%s\",\"%s\",\"%s\",\"%s\",%.2f,\"%s\",\"%s\",\"%s\",\"%s\"\n",
                    escape(p.getId()), escape(p.getBookingId()), escape(p.getCustomerName()),
                    escape(p.getCustomerEmail()), p.getAmount(), escape(p.getPaymentMethod()),
                    escape(p.getTransactionRef()), escape(p.getPaymentDate()), escape(p.getStatus())
            );
        }
        writer.flush();
    }

    @GetMapping("/fleet")
    public void exportFleetCsv(HttpSession session, HttpServletResponse response) throws IOException {
        if (session.getAttribute("adminUser") == null) {
            response.sendRedirect("/login");
            return;
        }

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"LNT_Fleet_Inventory.csv\"");

        PrintWriter writer = response.getWriter();
        writer.println("CarID,Name,Brand,Category,Seaters,FuelType,Transmission,PricePerDay,Mileage,RegistrationNumber,Available,Rating,TripsCompleted");

        List<Car> cars = dataStoreService.getAllCars();
        for (Car c : cars) {
            writer.printf("\"%s\",\"%s\",\"%s\",\"%s\",%d,\"%s\",\"%s\",%.2f,\"%s\",\"%s\",%b,%.1f,%d\n",
                    escape(c.getId()), escape(c.getName()), escape(c.getBrand()), escape(c.getCategory()),
                    c.getSeaters(), escape(c.getFuelType()), escape(c.getTransmission()), c.getPricePerDay(),
                    escape(c.getMileage()), escape(c.getRegistrationNumber()), c.isAvailable(), c.getRating(), c.getTripsCompleted()
            );
        }
        writer.flush();
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\"", "\"\"");
    }
}

