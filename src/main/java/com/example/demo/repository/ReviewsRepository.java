package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Review;

@Repository
public interface ReviewsRepository extends JpaRepository<Review, Long>{
	
	//Buscar la lista completa de reviews
	List<Review> findAll();
	
	//Buscar reviews asociadas a una película ordenadas de mayor a menor puntuacion
	List<Review> findByFilm_titleContainingIgnoreCaseOrderByPunctuationDesc(String title);
}
