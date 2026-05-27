package com.flatironstudios.plately.meal;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController             
@RequestMapping("/api/meals") 
@CrossOrigin(origins = "http://localhost")
public class MealController {

    @GetMapping
    public List<String> getMeals() {
        return List.of("Grilled Chicken", "Caesar Salad", "Pasta Primavera");
    }
}