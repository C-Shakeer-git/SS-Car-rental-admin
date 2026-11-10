package com.lakshminarayana.admin.controller;

import com.lakshminarayana.admin.model.Car;
import com.lakshminarayana.admin.service.DataStoreService;
import com.lakshminarayana.admin.service.GoogleDriveService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/cars")
public class AdminFleetController {

    @Autowired
    private DataStoreService dataStoreService;

    @Autowired
    private GoogleDriveService googleDriveService;

    @GetMapping
    public String listCars(HttpSession session, Model model) {
        if (session.getAttribute("adminUser") == null) return "redirect:/login";

        List<Car> cars = dataStoreService.getAllCars();
        model.addAttribute("cars", cars);
        return "cars";
    }

    @PostMapping("/save")
    public String saveCar(@RequestParam(required = false) String id,
                          @RequestParam String name,
                          @RequestParam String brand,
                          @RequestParam String category,
                          @RequestParam int seaters,
                          @RequestParam String fuelType,
                          @RequestParam String transmission,
                          @RequestParam double pricePerDay,
                          @RequestParam(required = false) String imageUrl,
                          @RequestParam(required = false) MultipartFile imageFile,
                          @RequestParam String mileage,
                          @RequestParam int luggageCapacity,
                          @RequestParam String registrationNumber,
                          @RequestParam(required = false) String featuresString,
                          @RequestParam(defaultValue = "true") boolean available,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        if (session.getAttribute("adminUser") == null) return "redirect:/login";

        String finalImageUrl = imageUrl;
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                finalImageUrl = googleDriveService.uploadCarImageOrDoc(
                        imageFile.getOriginalFilename(),
                        imageFile.getContentType(),
                        imageFile.getBytes()
                );
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed uploading car image: " + e.getMessage());
        }

        if (finalImageUrl == null || finalImageUrl.isBlank()) {
            finalImageUrl = "https://images.unsplash.com/photo-1549399542-7e3f8b79c341?auto=format&fit=crop&w=800&q=80";
        }

        Car car = (id != null && !id.isBlank()) ? dataStoreService.getCarById(id).orElse(new Car()) : new Car();
        car.setName(name);
        car.setBrand(brand);
        car.setCategory(category);
        car.setSeaters(seaters);
        car.setFuelType(fuelType);
        car.setTransmission(transmission);
        car.setPricePerDay(pricePerDay);
        car.setImageUrl(finalImageUrl);
        car.setMileage(mileage);
        car.setLuggageCapacity(luggageCapacity);
        car.setRegistrationNumber(registrationNumber);
        car.setFeaturesFromString(featuresString);
        car.setAvailable(available);

        dataStoreService.saveCar(car);

        redirectAttributes.addFlashAttribute("successMessage", "Car '" + name + "' saved successfully!");
        return "redirect:/admin/cars";
    }

    @PostMapping("/delete/{id}")
    public String deleteCar(@PathVariable String id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("adminUser") == null) return "redirect:/login";

        boolean deleted = dataStoreService.deleteCar(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("successMessage", "Vehicle " + id + " removed from fleet.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Vehicle could not be removed.");
        }
        return "redirect:/admin/cars";
    }

    @PostMapping("/toggle/{id}")
    public String toggleAvailability(@PathVariable String id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("adminUser") == null) return "redirect:/login";

        Car car = dataStoreService.getCarById(id).orElse(null);
        if (car != null) {
            car.setAvailable(!car.isAvailable());
            dataStoreService.saveCar(car);
            redirectAttributes.addFlashAttribute("successMessage", "Vehicle " + car.getName() + " status updated to " + (car.isAvailable() ? "Available" : "Maintenance/Unavailable"));
        }
        return "redirect:/admin/cars";
    }
}

