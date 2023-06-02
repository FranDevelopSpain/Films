package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Review;
import com.example.demo.error.ErrorBadRequest;
import com.example.demo.error.ErrorNotFound;
import com.example.demo.service.ReviewsPersistService;

@RestController
public class ReviewsController {

	@Autowired
	ReviewsPersistService reviewsPersistService;
	
	/*CONSTRUCTOR*/
	public ReviewsController() {
		super();
	}
	
	/*METODOS CON PARÁMETROS*/
	
	//Buscar reviews asociadas a una pelicula y ordenadarlas por puntuacion
	@GetMapping(path = "/reviews")
	List<Review> getReviews(@RequestParam(required = false) String title) {
		if(title != null) {
			return reviewsPersistService.findByFilm_titleContainingIgnoreCaseOrderByPunctuationDesc(title);
		} else {
			return reviewsPersistService.findAll();
		}
	}
		
	
	/*METODOS GET POST PUT Y DELETE*/
	
	@GetMapping(path = "/reviews/{id}")
	Review getReviews(@PathVariable Long id) {
		if(reviewsPersistService.findById(id).isPresent()) {
			return reviewsPersistService.getReview(id);
		} else {
			throw new ErrorNotFound("Review no encontrada");
		}
	}
	
	@PostMapping(path = "/reviews")
	Long newReview(@RequestBody Review review) {
		return reviewsPersistService.add(review);
	}
	
	@PutMapping(path = "/reviews/{id}")
	Review modifierReview(@RequestBody Review review, @PathVariable Long id) {
		if(reviewsPersistService.findById(id).isPresent()) {
			reviewsPersistService.save(id, review);
			return review;
		} else {
			throw new ErrorNotFound("Review no encontrada");
		}
	}
	
	@DeleteMapping(path = "/reviews/{id}") 
	String deleteReview(@PathVariable Long id) {
		if(reviewsPersistService.findById(id).isPresent()) {
			reviewsPersistService.delete(id);
			return("Review borrada");
		} else {
			throw new ErrorNotFound("Review no encontrada");
		}
	}
}
