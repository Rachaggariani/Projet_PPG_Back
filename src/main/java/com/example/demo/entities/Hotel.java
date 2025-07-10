package com.example.demo.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "nom_hotel")
    private String nomHotel;
    @JoinColumn(name = "adresse_hotel")
    private String adresseHotel;
    @JoinColumn(name = "images")
    private String images;
    @JoinColumn(name = "rating")
    private int rating;
    @JoinColumn(name = "serviceDescription")
    private String serviceDescription;
    @JoinColumn(name = "tel")
    private int tel;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chambre> chambres = new ArrayList<>();
    
    // Méthode helper pour gérer la relation
    public void addChambre(Chambre chambre) {
        chambres.add(chambre);
        chambre.setHotel(this); // Établit la relation
    }
    public Hotel() {}

	public Hotel(Long id, String nomHotel, String adresseHotel, String images, int rating, String serviceDescription, int tel, List<Chambre> chambres) {
		this.id = id;
		this.nomHotel = nomHotel;
		this.adresseHotel = adresseHotel;
		this.images = images;
		this.rating = rating;
		this.serviceDescription = serviceDescription;
		this.tel = tel;
		this.chambres = chambres;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomHotel() {
		return nomHotel;
	}

	public void setNomHotel(String nomHotel) {
		this.nomHotel = nomHotel;
	}

	public String getAdresseHotel() {
		return adresseHotel;
	}

	public void setAdresseHotel(String adresseHotel) {
		this.adresseHotel = adresseHotel;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	

	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}	
	public String getServiceDescription() {
		return serviceDescription;
	}
	
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	public List<Chambre> getChambres() {
		return chambres;
	}

	public void setChambres(List<Chambre> chambres) {
		this.chambres = chambres;
	}


	public String getImages() {
		return images;
	}


	public void setImages(String images) {
		this.images = images;
	}
    
    
    
    
    
    
    
    
    
    
}
