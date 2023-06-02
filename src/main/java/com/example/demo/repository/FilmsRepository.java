package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Film;

@Repository
public interface FilmsRepository extends JpaRepository<Film, Long>{

	//Buscar película por título
	List<Film> findByTitleContainingIgnoreCase(String title);
	
	//Buscar el listado completo de pelicula
	List<Film> findAll();
	
}
