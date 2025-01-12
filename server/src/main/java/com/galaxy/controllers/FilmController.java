package com.galaxy.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.galaxy.entities.Film;

import com.galaxy.services.FilmService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/film")
public class FilmController {

    @Autowired
    FilmService filmSer;

    @GetMapping("")
    public ResponseEntity<?> getFilm(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return filmSer.getAllFilm(PageRequest.of(page, size));
    }

    @GetMapping("/active")
    public ResponseEntity<?> getFilmActive() {
        return filmSer.getFilmActive();
    }

    @PostMapping("")
    public ResponseEntity<?> postFilm(@RequestBody Film entity) {
        return filmSer.createNewFilm(entity);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        return filmSer.uploadImage(file);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFilm(@PathVariable("id") int id) {
        return filmSer.deleteFilm(id);
    }
}
