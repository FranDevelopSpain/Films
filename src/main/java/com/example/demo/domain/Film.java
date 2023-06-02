package com.example.demo.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;


	/*ATRIBUTOS DE LA ENTIDAD FILM*/
	@Column
	String title;
	@Column
	int punctuation;
	@Column
	String synopsis;
	@Column
	String director;
	@Column
	Date premiereDate;

	
	//Relacion N a M con Cinema
	@ManyToMany(mappedBy = "films")
	@JsonIgnoreProperties({"films"}) //campo que no queremos que serialice
	List<Cinema> cines;
	
	
	//Relacion 1 a N con reviews
	@OneToMany(mappedBy = "film")
	@JsonManagedReference
	List<Review> reviews;
	
	
	/*CONSTRUCTOR SIN PARÁMETROS*/
	public Film() {
		super();
	
	}

	/*GETTERS AND SETTERS*/
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPunctuation() {
		return punctuation;
	}
	public void setPunctuation(int punctuation) {
		this.punctuation = punctuation;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public Date getPremiereDate() {
		return premiereDate;
	}
	public void setPremiereDate(Date premiereDate) {
		this.premiereDate = premiereDate;
	}
}
