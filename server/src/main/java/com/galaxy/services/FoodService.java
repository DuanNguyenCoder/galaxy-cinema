package com.galaxy.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.galaxy.entities.Food;
import com.galaxy.enums.ResponseTitle;
import com.galaxy.payoads.ApiResponse;
import com.galaxy.repositories.FoodRepo;

@Service
public class FoodService {

    @Autowired
    FoodRepo foodRepo;

    private static final String UPLOAD_DIR_FOOD = "server/src/main/resources/static/uploads/foods/";

    public ResponseEntity<ApiResponse<?>> createFood(Food food) {
        ApiResponse<Food> res;
        try {
            food.setCreateDay(new Date(System.currentTimeMillis()));
            food.setUpdateDay(new Date(System.currentTimeMillis()));
            foodRepo.save(food);
            res = new ApiResponse<Food>(ResponseTitle.SUCCESS, "created successfuly!", HttpStatus.CREATED.value(),
                    food);
            return ResponseEntity.status(HttpStatus.CREATED).body(res);

        } catch (Exception e) {
            res = new ApiResponse<Food>(ResponseTitle.ERROR, "created fail!", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }

    public ResponseEntity<ApiResponse<?>> uploadImage(MultipartFile file) {
        ApiResponse<String> res;
        try {
            // Lấy tên file gốc
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR_FOOD + fileName);

            // Lưu file vào thư mục
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            // Trả về đường dẫn ảnh
            String image = "images/foods/" + fileName;
            res = new ApiResponse<String>(ResponseTitle.SUCCESS, "created successfuly!", HttpStatus.CREATED.value(),
                    image);
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch (IOException e) {
            res = new ApiResponse<String>(ResponseTitle.ERROR, "created fail!",
                    HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(res);
        }
    }
}
