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

import com.example.demo.domain.Film;
import com.example.demo.error.ErrorBadRequest;
import com.example.demo.error.ErrorNotFound;
import com.example.demo.service.FilmsPersistService;

@RestController
public class FilmsController {

	@Autowired
	FilmsPersistService filmsPersistService;
	

	/*METODOS CON PARÁMETROS*/
	
	@GetMapping(path = "/films") //Buscar películas por título
	List<Film> getFilms(@RequestParam(required = false) String title)
	{
		if(title != null) {
			return filmsPersistService.findByTitleContainingIgnoreCase(title);
		}else {
			return filmsPersistService.findAll();
		}
	}

	
	/*METODOS GET POST PUT Y DELETE*/
	@GetMapping(path = "/films/{id}")	//Obtener una película con un ID
	Film getFilms(@PathVariable Long id) {
		if(filmsPersistService.findById(id).isPresent()) {
			return filmsPersistService.getFilm(id);
		} else {
			throw new ErrorNotFound("Película no encontrada");
		}	
	}
	
	@PostMapping(path = "/films") //Dar de alta una película con ID desconocido
	Long newFilm(@RequestBody Film film) {
		return filmsPersistService.add(film);
	}
	
	@PutMapping(path = "/films/{id}") //Hacer cambios en una película con ID conocido
	Film modifierFilm(@RequestBody Film film, @PathVariable Long id) {
		if(filmsPersistService.findById(id).isPresent()) {
			filmsPersistService.save(id, film);
			return film;
		} else {
			throw new ErrorNotFound("Película no encontrada");
		}	
	}
	
	@DeleteMapping(path = "/films/{id}") //Borrar una película con un ID
	String deleteFilm(@PathVariable Long id) {
		if(filmsPersistService.findById(id).isPresent()) {
			filmsPersistService.delete(id);
			return("Película borrada");
		} else {
			throw new ErrorNotFound("Película no encontrada");
		}
	}
}



