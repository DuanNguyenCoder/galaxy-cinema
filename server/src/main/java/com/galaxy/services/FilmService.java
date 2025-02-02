package com.galaxy.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.galaxy.entities.Film;
import com.galaxy.entities.Rating;
import com.galaxy.enums.ResponseTitle;
import com.galaxy.payoads.ApiResponse;
import com.galaxy.repositories.FilmRepo;

@Service
public class FilmService {
	@Autowired
	FilmRepo filmRepo;
	private static final String UPLOAD_DIR_FILM = "server/src/main/resources/static/uploads/films/";

	public ResponseEntity<ApiResponse<?>> createNewFilm(Film f) {
		try {
			// Nếu là update có id và có ảnh mới
			if (f.getId() != 0 && f.getImage() != null) {
				deleteFilmImage(f.getId());
			}

			Film filmCreated = filmRepo.save(f);
			return ResponseEntity.status(HttpStatus.CREATED.value())
					.body(new ApiResponse<Film>(ResponseTitle.SUCCESS, "Film created/updated successfully!",
							HttpStatus.CREATED.value(),
							filmCreated));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<String>(ResponseTitle.ERROR, "Error: " + e.getMessage(),
							HttpStatus.INTERNAL_SERVER_ERROR.value()));
		}
	}

	public void deleteFilmImage(int FilmId) {
		try {
			Film oldFilm = filmRepo.findById(FilmId).get();
			if (oldFilm.getImage() != null) {
				// Xóa ảnh cũ
				String oldImagePath = oldFilm.getImage().replace("images/films/", "");
				Path path = Paths.get(UPLOAD_DIR_FILM + oldImagePath);
				if (Files.exists(path)) {
					Files.delete(path);
				}
			}

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	public ResponseEntity<ApiResponse<?>> deleteFilm(int id) {
		deleteFilmImage(id);
		filmRepo.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK.value())
				.body(new ApiResponse<String>(ResponseTitle.SUCCESS, "Film deleted successfully!",
						HttpStatus.OK.value()));
	}

	public ResponseEntity<ApiResponse<?>> uploadImage(MultipartFile file) {
		try {
			// Lấy tên file gốc
			String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
			Path path = Paths.get(UPLOAD_DIR_FILM + fileName);

			// Lưu file vào thư mục
			Files.createDirectories(path.getParent());
			Files.write(path, file.getBytes());

			// Trả về đường dẫn ảnh
			String image = "images/films/" + fileName;

			return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<String>(ResponseTitle.SUCCESS,
					"Upload succes!", HttpStatus.CREATED.value(), image));

		} catch (IOException e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<String>(ResponseTitle.ERROR, "Upload failed: " + e.getMessage(),
							HttpStatus.INTERNAL_SERVER_ERROR.value()));
		}
	}

	public List<Rating> getAllRatingOfFilm(int id) {
		System.err.println(filmRepo.findById(id).get().getRating().size());

		return filmRepo.findById(id).get().getRating();
	}

	public ResponseEntity<ApiResponse<?>> getAllFilm(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK.value())
				.body(new ApiResponse<Page<Film>>(ResponseTitle.SUCCESS, "Get all film successfully!",
						HttpStatus.OK.value(), filmRepo.findAll(pageable)));
	}

	public ResponseEntity<ApiResponse<?>> getFilmActive() {
		return ResponseEntity.status(HttpStatus.OK.value())
				.body(new ApiResponse<List<Film>>(ResponseTitle.SUCCESS, "Get all film successfully!",
						HttpStatus.OK.value(), filmRepo.findByActive(1)));
	}

}
