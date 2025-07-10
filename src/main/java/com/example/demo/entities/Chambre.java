package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
public class Chambre {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @JoinColumn(name = "numero")
	    private String numero;
	    @JoinColumn(name = "description")
	    private String description;
	    @JoinColumn(name = "image_chambre")
	    private String imageChambre;
	    @JoinColumn(name = "type")
	    private String type;
	    @JoinColumn(name = "prix")
	    private float prix;
	    @JoinColumn(name = "disponible")
	    private boolean disponible;
	    @JoinColumn(name = "capacite")
	    private int capacite;
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "hotel_id", nullable = false) 
	    @JsonIgnore // Empêche la sérialisation de la référence à l'hôtel
	    private Hotel hotel;

	    
	    public Chambre() {}
	    
		

		public Chambre(Long id, String numero, String description, String imageChambre, String type, float prix,boolean disponible,
				int capacite, Hotel hotel) {
			this.id = id;
			this.numero = numero;
			this.description = description;
			this.imageChambre = imageChambre;
			this.type = type;
			this.prix = prix;
			this.disponible = disponible;
			this.capacite = capacite;
			this.hotel = hotel;
		}


		public String getNumero() {
			return numero;
		}

		public void setNumero(String numero) {
			this.numero = numero;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public float getPrix() {
			return prix;
		}

		public void setPrix(float prix) {
			this.prix = prix;
		}

		public boolean isDisponible() {
			return disponible;
		}

		public void setDisponible(boolean disponible) {
			this.disponible = disponible;
		}

		public Hotel getHotel() {
			return hotel;
		}

		public void setHotel(Hotel hotel) {
			this.hotel = hotel;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public int getCapacite() {
			return capacite;
		}

		public void setCapacite(int capacite) {
			this.capacite = capacite;
		}

		public String getImageChambre() {
			return imageChambre;
		}



		public void setImageChambre(String imageChambre) {
			this.imageChambre = imageChambre;
		}
	    
	    
}
