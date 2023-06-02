package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Cinema;

@Repository
public interface CinemasRepository extends JpaRepository<Cinema, Long>{
	
	//Buscar la lista completa de cines
	List<Cinema> findAll();
	
	//Buscar cine por nombre
	List<Cinema> findByNameContainingIgnoreCase(String name);
	
	//Buscar cines donde se emite una pelicula ordenados por el precio de la entrada
	List<Cinema> findByFilms_titleContainingIgnoreCaseOrderByTicketPrice(String title);
	
	//Buscar cines donde se emite una película, en un determinado código postal
	List<Cinema> findByPostalCodeAndFilms_titleContainingIgnoreCase(Integer postalCode, String title);
	
	
}
