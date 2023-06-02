package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Review;
import com.example.demo.repository.ReviewsRepository;

@Service
public class ReviewsPersistService {
	
	ReviewsRepository reviewsRepository;

	public ReviewsPersistService(ReviewsRepository reviewsRepository) { //inyectamos el repositorio
		this.reviewsRepository = reviewsRepository;
	}
	
	/*CREAMOS LOS METODOS*/
	public Review getReview(Long id) {
		return reviewsRepository.getReferenceById(id);
	}
	
	public Long add(Review review) {
		Review reviewSave = reviewsRepository.save(review);
		return reviewSave.getId();
	}
	
	public List<Review> findAll(){
		return reviewsRepository.findAll();
	}
	
	public void save(Long id, Review review) {
		Review reviewSave = reviewsRepository.getReferenceById(id);
		reviewSave.setDescription(review.getDescription());
		reviewSave.setPunctuation(review.getPunctuation());
		reviewsRepository.save(reviewSave);
	}
	
	public void delete(Long id) {
		reviewsRepository.delete(reviewsRepository.getReferenceById(id));
	}
	
	public Optional<Review> findById(Long id) {
		return reviewsRepository.findById(id);
	}
	
	public List<Review> findByFilm_titleContainingIgnoreCaseOrderByPunctuationDesc(String title) {
		return reviewsRepository.findByFilm_titleContainingIgnoreCaseOrderByPunctuationDesc(title);
	}

}
