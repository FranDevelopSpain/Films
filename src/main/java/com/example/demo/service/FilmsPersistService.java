package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Film;
import com.example.demo.repository.FilmsRepository;

@Service
public class FilmsPersistService {

	FilmsRepository filmsRepository;

	public FilmsPersistService(FilmsRepository filmsRepository) {	//Inyectamos el repositorio
		this.filmsRepository = filmsRepository;
	}
	
	/*CREAMOS LOS METODOS*/
	public Film getFilm(Long id) {	//obtener una pelicula por su id
		return filmsRepository.getReferenceById(id);
	}
	
	public Long add(Film film) {	//añadir una pelicula
		Film filmSaved = filmsRepository.save(film);
		return filmSaved.getId();
	}
	
	public List<Film> findAll(){ 	//obtener el listado completo de películas
		return filmsRepository.findAll();
	}
	
	public void save(Long id, Film film) {
		Film filmSaved = filmsRepository.getReferenceById(id);
		filmSaved.setDirector(film.getDirector());
		filmSaved.setPremiereDate(film.getPremiereDate());
		filmSaved.setPunctuation(film.getPunctuation());
		filmSaved.setSynopsis(film.getSynopsis());
		filmSaved.setTitle(film.getTitle());
		filmsRepository.save(filmSaved);
	}
	
	public void delete(Long id) {
		filmsRepository.delete(filmsRepository.getReferenceById(id));
	}
	
	public Optional<Film> findById(Long id) {
		return filmsRepository.findById(id);
	}
	
	//Buscar películas con un título (o título que contenga la cadena)
	public List<Film> findByTitleContainingIgnoreCase(String title){	//método de la interfaz
		return filmsRepository.findByTitleContainingIgnoreCase(title);
	}

}
