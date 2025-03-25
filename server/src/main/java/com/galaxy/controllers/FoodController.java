package com.galaxy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.galaxy.entities.Food;
import com.galaxy.services.FoodService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/food")
public class FoodController {

    @Autowired
    FoodService foodService;

    @PostMapping("")
    public ResponseEntity<?> createNewOrUpdate(@RequestBody Food f) {

        return foodService.createFood(f);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        return foodService.uploadImage(file);
    }

}
