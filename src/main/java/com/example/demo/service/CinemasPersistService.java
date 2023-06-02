package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Cinema;
import com.example.demo.repository.CinemasRepository;

@Service
public class CinemasPersistService {

	CinemasRepository cinemaRepository;

	public CinemasPersistService(CinemasRepository cinemaRepository) { //inyectamos el repositorio
		this.cinemaRepository = cinemaRepository;
	}
	
	/*CREAMOS LOS MÉTODOS*/
	public Cinema getCine(Long id) {	//obtenemos un cine por su id
		return cinemaRepository.getReferenceById(id);
	}
	
	public Long add(Cinema cine) {	//añadir un nuevo cine
		Cinema cineSave = cinemaRepository.save(cine);
		return cineSave.getId();
	}
	
	public List<Cinema> findAll() {
		return cinemaRepository.findAll();
	}
	
	public void save(Long id, Cinema cine) {
		Cinema cineSave = cinemaRepository.getReferenceById(id);
		cineSave.setName(cine.getName());
		cineSave.setPostalCode(cine.getPostalCode());
		cineSave.setProvince(cine.getProvince());
		cineSave.setTicketPrice(cine.getTicketPrice());
		cineSave.setTown(cine.getTown());
		cinemaRepository.save(cineSave);
	}
	
	public void delete(Long id) {
		cinemaRepository.delete(cinemaRepository.getReferenceById(id));
	}
	
	public Optional<Cinema> findById(Long id) {
		return cinemaRepository.findById(id);
	}
	
	//BUSCAR CINES POR NOMBRE
	public List<Cinema> findByNameContainingIgnoreCase(String name) {	//método de la interfaz
		return cinemaRepository.findByNameContainingIgnoreCase(name);
	}
	
	//BUSCAR CINES POR TITULO DE PELICULA ORDENADOS POR PRECIO
	public List<Cinema> findByFilms_titleContainingIgnoreCaseOrderByTicketPrice(String title) {
		return cinemaRepository.findByFilms_titleContainingIgnoreCaseOrderByTicketPrice(title);
	}

	//BUSCAR CINES POR TITULO DE PELICULA Y CODIGO POSTAL
	public List<Cinema> findByPostalCodeAndFilms_titleContainingIgnoreCase(Integer postalCode, String title) {
		return cinemaRepository.findByPostalCodeAndFilms_titleContainingIgnoreCase(postalCode, title);
	}

	
}
