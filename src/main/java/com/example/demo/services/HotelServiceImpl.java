package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Chambre;
import com.example.demo.entities.Hotel;
import com.example.demo.repositories.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService {
	  @Autowired
	    private HotelRepository hotelRepository;
	/*  public List<Hotel> getAllHotels() {
	        return hotelRepository.findAll();
	    }

	   public Hotel getHotel(Long id) {
	        return hotelRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Hotel not found"));
	    }
	    public Hotel addHotel(Hotel hotel) {
	        return hotelRepository.save(hotel);
	    }

	  

	    public void deleteHotel(Long id) {
	        hotelRepository.deleteById(id);
	    }

	    public List<Chambre> getChambresByHotelId(Long hotelId) {
	        return hotelRepository.findById(hotelId)
	            .orElseThrow(() -> new RuntimeException("Hotel not found"))
	            .getChambres();
	    }*/

	  public Hotel saveHotel(Hotel hotel) {
	        if (hotel.getChambres() != null) {
	            hotel.getChambres().forEach(chambre -> {
	                chambre.setHotel(hotel);
	            });
	        }
	        return hotelRepository.save(hotel);
	    }

	@Override
	public List<Hotel> getAllHotels() {
		 return hotelRepository.findAll();
	}

	@Override
	 public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hôtel non trouvé avec l'id: " + id));
    }

	@Override
	 public Hotel updateHotel(Hotel hotel) {
        Hotel existingHotel = hotelRepository.findById(hotel.getId())
                .orElseThrow(() -> new RuntimeException("Hôtel non trouvé avec l'id: " + hotel.getId()));
        
        existingHotel.setNomHotel(hotel.getNomHotel());
        existingHotel.setAdresseHotel(hotel.getAdresseHotel());
        existingHotel.setImages(hotel.getImages());
        existingHotel.setRating(hotel.getRating());
        existingHotel.setTel(hotel.getTel());
        existingHotel.setServiceDescription(hotel.getServiceDescription());
        
        return hotelRepository.save(existingHotel);
    }

	@Override
	public String deleteHotel(Long id) {
        // Récupérer l'hôtel avant suppression
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hôtel non trouvé avec l'ID: " + id));
        
        String nomHotel = hotel.getNomHotel(); // Récupérer le nom
        
        hotelRepository.delete(hotel); // Supprimer l'hôtel
        
        return nomHotel; // Retourner le nom pour le message
    }
	@Override
	public boolean existsById(Long id) {
	    return hotelRepository.findById(id).isPresent();
	}
}
