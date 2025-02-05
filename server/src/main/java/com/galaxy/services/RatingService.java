package com.galaxy.services;

import java.util.ArrayList;
import java.util.Collections;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxy.DTO.AlgorithmDataResDTO;
import com.galaxy.DTO.RatingDTO;
import com.galaxy.DTO.ReviewReqDTO;
import com.galaxy.entities.Customer;
import com.galaxy.entities.Film;
import com.galaxy.entities.Rating;

import com.galaxy.repositories.CustomerRepo;
import com.galaxy.repositories.FilmRepo;
import com.galaxy.repositories.RatingRepo;

@Service
public class RatingService {

    @Autowired
    private CustomerRepo cusRepo;

    @Autowired
    private RatingRepo ratingRepo;

    @Autowired
    private FilmRepo MovieRe;

    @Autowired
    private FilmRepo filmRepo;

    public RatingDTO getFilmRatingsWithAverage(int filmId) {
        List<Rating> ratings = ratingRepo.findByFilmId(filmId);
        Film film = filmRepo.findById(filmId).orElseThrow();

        double averageRating = ratings.stream()
                .mapToInt(Rating::getRate)
                .average()
                .orElse(0.0);

        return new RatingDTO(
                film.getName(),
                averageRating,
                ratings);
    }

    public boolean insertUserReview(int cusId, ReviewReqDTO review) {
        try {
            Film f = filmRepo.findById(review.getFilmID()).get(); // get film by id
            Customer c = cusRepo.findById(cusId).get();
            System.out.println(f.getName());
            System.out.println(c.getName());
            Rating rating = new Rating();
            rating.setFilm(f);
            rating.setCustomer(c);
            rating.setRate(review.getRate());
            rating.setRateDate(review.getRateDate());
            rating.setComment(review.getComment());
            ratingRepo.save(rating);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    ///// COLLABORATIVE FITERING /////////////////////////////

    public AlgorithmDataResDTO rec(int IdCus) {

        AlgorithmDataResDTO dataRes = new AlgorithmDataResDTO();
        /// lấy ra danh sách tất cả người dùng
        List<Customer> customers = cusRepo.findAll();
        System.out.println(customers.size());

        for (Customer item : customers) {
            dataRes.getNameCus().add(item.getName());
        }
        /// lấy ra danh sách các film còn hoạt động
        List<Film> films = MovieRe.findAll();

        for (Film f : films) {
            dataRes.getNameItem().add(f.getName());
        }

        int numCustomers = customers.size();
        int numFilms = films.size();

        double[][] initialMatrix = new double[numCustomers][numFilms];

        for (int i = 0; i < numCustomers; i++) {
            Customer customer = customers.get(i);

            List<Rating> ratings = customer.getRating();

            for (int j = 0; j < numFilms; j++) {
                Film film = films.get(j);
                // Hàm tìm giá trị đánh giá từ danh sách đánh giá của khách hàng
                double ratingValue = findRatingValue(ratings, film);

                initialMatrix[i][j] = ratingValue;
            }
        }

        /// set ma tran khoi tao tra ve
        dataRes.setInitMatrix(initialMatrix);

        // Tính độ tương tự giữa các cặp người dùng
        double[][] similarityMatrix = new double[numCustomers][numCustomers];

        for (int i = 0; i < numCustomers; i++) {
            for (int j = 0; j < numCustomers; j++) {
                List<Rating> ratingsA = customers.get(i).getRating();
                List<Rating> ratingsB = customers.get(j).getRating();

                double similarity = cal(ratingsA, ratingsB); // Hàm tính độ tương tự (ví dụ: cosine similarity)

                similarityMatrix[i][j] = similarity;
            }
        }
        /// set ma tran consine tra ve
        dataRes.setSimilarMatrix(similarityMatrix);

        printSimilarityMatrix(initialMatrix);

        printSimilarityMatrix(similarityMatrix);

        printSimilarityScores(IdCus, similarityMatrix, customers, dataRes);

        /// set phim goi y de tra ve
        dataRes.setDataFilm(recommendMovies(IdCus, similarityMatrix));

        // return recommendMovies(IdCus,similarityMatrix);
        return dataRes;

    }

    private double findRatingValue(List<Rating> ratings, Film film) {
        for (Rating rating : ratings) {
            if (rating.getFilm().equals(film)) {
                return rating.getRate(); // Giả sử ratingValue là thuộc tính trong lớp Rating để lưu giá trị đánh giá
            }
        }
        return 0.0; // Trả về 0.0 nếu không tìm thấy giá trị đánh giá cho bộ phim
    }

    public void printSimilarityMatrix(double[][] similarityMatrix) {
        int numRows = similarityMatrix.length;
        int numCols = similarityMatrix[0].length;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                String formattedValue = String.format("%.2f", similarityMatrix[i][j]);
                System.out.print(formattedValue + " ");
            }
            System.out.println(); // Xuống dòng sau khi in xong một hàng
        }
    }

    private double cal(List<Rating> ratingsA, List<Rating> ratingsB) {
        int numRatingsA = ratingsA.size();
        int numRatingsB = ratingsB.size();

        int maxLength = Math.max(numRatingsA, numRatingsB);

        double[] ratingsArrayA = new double[maxLength];
        double[] ratingsArrayB = new double[maxLength];

        // Gán giá trị mặc định 0 cho các phần tử thiếu của danh sách ngắn hơn
        for (int i = 0; i < maxLength; i++) {
            if (i < numRatingsA) {
                ratingsArrayA[i] = ratingsA.get(i).getRate();
            } else {
                ratingsArrayA[i] = 0;
            }

            if (i < numRatingsB) {
                ratingsArrayB[i] = ratingsB.get(i).getRate();
            } else {
                ratingsArrayB[i] = 0;
            }
        }

        // Tiến hành tính toán độ tương tự cosine
        double dotProduct = 0;
        double normA = 0;
        double normB = 0;

        for (int i = 0; i < maxLength; i++) {
            dotProduct += ratingsArrayA[i] * ratingsArrayB[i];
            normA += Math.pow(ratingsArrayA[i], 2);
            normB += Math.pow(ratingsArrayB[i], 2);
        }

        double similarity = dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
        return similarity;
    }

    public void printSimilarityScores(int targetUserId, double[][] similarityMatrix, List<Customer> userList,
            AlgorithmDataResDTO dataRes) {
        int targetUserIndex = -1;

        // Tìm chỉ số của targetUser trong similarityMatrix
        for (int i = 0; i < userList.size(); i++) {
            Customer user = userList.get(i);
            if (user.getId() == targetUserId) {
                targetUserIndex = i;
                break;
            }
        }

        if (targetUserIndex == -1) {
            // Người dùng không tồn tại trong danh sách
            return;
        }

        double[] similarityScores = similarityMatrix[targetUserIndex];

        // Duyệt qua mức độ tương tự và in ra điểm tương tự của từng cặp người dùng
        for (int i = 0; i < similarityScores.length; i++) {
            if (i != targetUserIndex) {
                double similarity = similarityScores[i];
                Customer otherUser = userList.get(i);
                dataRes.getCoupleSimilar().add(otherUser.getName() + ": " + +similarity);
                System.out.println(
                        "Similarity score between current user and User " + otherUser.getName() + ": " + similarity);
            }
        }
    }

    //////////////////////// goi y phim///////////////////////////
    public Set<Film> recommendMovies(int userId, double[][] similarityMatrix) {
        Set<Film> recommendedMovies = new HashSet<>();

        // Tìm người dùng có độ tương đồng cao với người dùng hiện tại
        List<Integer> similarUsers = findSimilarUsers(userId, similarityMatrix);

        // Lấy danh sách các bộ phim mà những người dùng có độ tương đồng cao đã đánh
        // giá
        Set<Film> ratedMovies = getRatedMovies(userId);

        // Gợi ý các bộ phim hàng đầu từ những người dùng tương tự
        for (int user : similarUsers) {
            Set<Film> userRatedMovies = getRatedMovies(user);
            userRatedMovies.removeAll(ratedMovies); // Loại bỏ những bộ phim đã được người dùng hiện tại đánh giá
            recommendedMovies.addAll(userRatedMovies);
        }

        return recommendedMovies;
    }

    public Set<Film> getRatedMovies(int userId) {
        Customer user = cusRepo.findById(userId).orElse(null);
        if (user == null) {
            return Collections.emptySet(); // Trả về danh sách rỗng nếu không tìm thấy người dùng
        }

        List<Rating> ratings = ratingRepo.findByCustomerId(user.getId());
        Set<Film> ratedMovies = new HashSet<>();

        for (Rating rating : ratings) {
            ratedMovies.add(rating.getFilm());
        }

        return ratedMovies;
    }

    private List<Integer> findSimilarUsers(int userId, double[][] similarityMatrix) {
        List<Integer> similarUsers = new ArrayList<>();

        // Tìm người dùng có độ tương đồng cao hơn ngưỡng
        double threshold = 0.5; // Ngưỡng độ tương đồng
        try {
            for (int i = 0; i < similarityMatrix[userId].length; i++) {
                if (similarityMatrix[userId][i] > threshold) {
                    similarUsers.add(i);
                }
            }

        } catch (Exception e) {
        }

        return similarUsers;
    }

}
