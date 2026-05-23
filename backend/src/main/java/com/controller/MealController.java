package com.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController               // "This class handles HTTP requests and returns data"
@RequestMapping("/api/meals") // "All routes in here start with /api/meals"
@CrossOrigin(origins = "http://localhost:5173") // Allow your React dev server to call this
public class MealController {

    @GetMapping         // "This method handles GET /api/meals"
    public List<String> getMeals() {
        // Later this will query your PostgreSQL DB.
        // For now, return dummy data.
        return List.of("Grilled Chicken", "Caesar Salad", "Pasta Primavera");
    }
}