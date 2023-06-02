package com.example.demo.web;

import java.net.http.HttpRequest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Cinema;
import com.example.demo.error.ErrorBadRequest;
import com.example.demo.error.ErrorNotFound;
import com.example.demo.error.ErrorUnauthorized;
import com.example.demo.service.CinemasPersistService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class CinemasController {

	@Autowired
	CinemasPersistService cinemasPersistService;
	

	/*METODOS CON PARÁMETROS*/
	@GetMapping(path = "/cines") //Obtener un cine por nombre
	List<Cinema> getCines(@RequestParam(required = false) String name,
						  @RequestParam(required = false) String title,
						  @RequestParam(required = false) Integer postalCode) {
		
		if(name != null) {
			return cinemasPersistService.findByNameContainingIgnoreCase(name);
		} else if(title != null) { 
			if(postalCode != null) {
				return cinemasPersistService.findByPostalCodeAndFilms_titleContainingIgnoreCase(postalCode, title);
			}
				return cinemasPersistService.findByFilms_titleContainingIgnoreCaseOrderByTicketPrice(title);
			} else {
				return cinemasPersistService.findAll();
			}
	}
	
	/*METODOS GET POST PUT Y DELETE*/
	@GetMapping(path = "/cines/{id}") //Obtener un cine por su id
	Cinema getCines(@PathVariable Long id) {
		if(cinemasPersistService.findById(id).isPresent()) {
			return cinemasPersistService.findById(id).get();
		} else {
			throw new ErrorNotFound("Cine no encontrado");
		}
	}
	
	
	@PostMapping(path = "/cines")
	Long newCine(@RequestBody Cinema cine, HttpServletRequest request) {
		if(request.isUserInRole("ROLE_CINECHECKER") || request.isUserInRole("ROLE_ADMIN")) {
			return cinemasPersistService.add(cine);
		} else {
			throw new ErrorUnauthorized("No tienes permisos");
		}	
	}
	
	@PutMapping(path = "/cines/{id}")
	Cinema modifierCine(@RequestBody Cinema cine, @PathVariable Long id,  HttpServletRequest request) {
		if(request.isUserInRole("ROLE_CINECHECKER") || request.isUserInRole("ROLE_ADMIN")) {
			if(cinemasPersistService.findById(id).isPresent()) {
				cinemasPersistService.save(id, cine);
				return cine;
			} else {
				throw new ErrorNotFound("Cine no encontrado");
			} 
		}
		throw new ErrorUnauthorized("No tienes permisos");
	}
	
	@DeleteMapping(path = "/cines/{id}")
	String deleteCine(@PathVariable Long id) {
		if(cinemasPersistService.findById(id).isPresent()) {
			cinemasPersistService.delete(id);
			return("Cine borrado");
		} else {
			throw new ErrorNotFound("Cine no encontrado");
		}
	}
}

