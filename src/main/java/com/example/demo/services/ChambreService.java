package com.example.demo.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.entities.Chambre;

public interface ChambreService {
	/*
	 * public List<Chambre> getChambresByHotel(Long hotelId); public Chambre
	 * addChambreToHotel(Long hotelId, Chambre chambre); public void
	 * deleteChambre(Long id);
	 */
	/*
	 * public List<Chambre> getChambresByHotelAndType(Long hotelId, String type);
	 * 
	 * public List<Chambre> getAvailableChambresByHotelAndType(Long hotelId, String
	 * type);
	 */
	
	/*
	 * List<Chambre> getChambresByHotel(Long hotelId); List<Chambre>
	 * getChambresByHotelAndType(Long hotelId, String type); List<Chambre>
	 * getAvailableChambresByHotelAndType(Long hotelId, String type); Long
	 * countAvailableChambresByType(Long hotelId, String type); Optional<Chambre>
	 * getChambreById(Long id); Chambre saveChambre(Chambre chambre); void
	 * deleteChambre(Long id); Optional<Chambre> updateDisponibilite(Long id,
	 * boolean disponible); public boolean existsById(Long id);
	 */
    public List<Chambre> getChambresByHotel(Long hotelId);
	 public Map<String, Boolean> getDisponibiliteParType(Long hotelId);
	 public Optional<String> getFirstRoomImage(Long hotelId, String roomType);
	 public Map<String, Boolean> updateDisponibilite(Long hotelId, String type, boolean disponible);
	  public Chambre createRoomType(Chambre roomType);
}
