package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue
	Long id; 

	
	/*ATRIBUTOS DE LA ENTIDAD REVIEW*/
	@Column
	String description;
	@Column
	int punctuation;
	
	//Referencia a la entidad FILM
	@ManyToOne 	//foreign key, relación N a 1
	@JsonBackReference
	Film film;
	
	/*CONSTRUCTOR SIN PARÁMETROS*/
	public Review() {
		super();
	}


	/*GETTERS AND SETTERS*/

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPunctuation() {
		return punctuation;
	}
	public void setPunctuation(int punctuation) {
		this.punctuation = punctuation;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
}
