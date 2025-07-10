package com.example.demo.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Chambre;
import com.example.demo.entities.Hotel;
import com.example.demo.repositories.ChambreRepository;
import com.example.demo.repositories.HotelRepository;

@Service
public class ChambreServiceImpl implements ChambreService {
	@Autowired
	private ChambreRepository chambreRepository;
	@Autowired
	private HotelRepository hotelRepository;

	/*
	 * public List<Chambre> getChambresByHotel(Long hotelId) { return
	 * chambreRepository.findByHotelId(hotelId); }
	 * 
	 * public Chambre addChambreToHotel(Long hotelId, Chambre chambre) { Hotel hotel
	 * = hotelRepository.findById(hotelId) .orElseThrow(() -> new
	 * RuntimeException("Hotel not found")); chambre.setHotel(hotel); return
	 * chambreRepository.save(chambre); }
	 * 
	 * public void deleteChambre(Long id) { chambreRepository.deleteById(id); }
	 */
	/*
	 * public List<Chambre> getChambresByHotelAndType(Long hotelId, String type) {
	 * return chambreRepository.findByHotelIdAndType(hotelId, type); }
	 * 
	 * public List<Chambre> getAvailableChambresByHotelAndType(Long hotelId, String
	 * type) { return
	 * chambreRepository.findByHotelIdAndTypeAndDisponibleTrue(hotelId, type); }
	 */
	/*
	 * @Override public List<Chambre> getChambresByHotel(Long hotelId) { return
	 * chambreRepository.findByHotelId(hotelId); }
	 * 
	 * @Override public List<Chambre> getChambresByHotelAndType(Long hotelId, String
	 * type) { return chambreRepository.findByHotelIdAndType(hotelId, type); }
	 * 
	 * @Override public List<Chambre> getAvailableChambresByHotelAndType(Long
	 * hotelId, String type) { return
	 * chambreRepository.findByHotelIdAndTypeAndDisponibleTrue(hotelId, type); }
	 * 
	 * @Override public Long countAvailableChambresByType(Long hotelId, String type)
	 * { return chambreRepository.countByHotelIdAndTypeAndDisponibleTrue(hotelId,
	 * type); }
	 * 
	 * @Override public Optional<Chambre> getChambreById(Long id) { return
	 * chambreRepository.findById(id); }
	 * 
	 * @Override public Chambre saveChambre(Chambre chambre) { return
	 * chambreRepository.save(chambre); }
	 * 
	 * @Override public void deleteChambre(Long id) {
	 * chambreRepository.deleteById(id); }
	 * 
	 * @Override public Optional<Chambre> updateDisponibilite(Long id, boolean
	 * disponible) { return chambreRepository.findById(id).map(chambre -> {
	 * chambre.setDisponible(disponible); return chambreRepository.save(chambre);
	 * }); }
	 * 
	 * @Override public boolean existsById(Long id) { return
	 * chambreRepository.existsById(id); }
	 */
	 // Méthode pour obtenir toutes les chambres d'un hôtel
    public List<Chambre> getChambresByHotel(Long hotelId) {
        return chambreRepository.findByHotelId(hotelId);
    }

    // Méthode existante pour la disponibilité
    public Map<String, Boolean> getDisponibiliteParType(Long hotelId) {
        Map<String, Boolean> disponibilite = new HashMap<>();
        
        disponibilite.put("simple", !chambreRepository
            .findByHotelIdAndTypeAndDisponibleTrue(hotelId, "simple").isEmpty());
            
        disponibilite.put("double", !chambreRepository
            .findByHotelIdAndTypeAndDisponibleTrue(hotelId, "double").isEmpty());
            
        disponibilite.put("suite", !chambreRepository
            .findByHotelIdAndTypeAndDisponibleTrue(hotelId, "suite").isEmpty());
            
        return disponibilite;
    }
    public Optional<String> getFirstRoomImage(Long hotelId, String roomType) {
        List<Chambre> chambres = chambreRepository.findByHotelIdAndTypeAndDisponibleTrue(hotelId, roomType);
        return chambres.stream()
                .filter(c -> c.getImageChambre() != null && !c.getImageChambre().isEmpty())
                .map(Chambre::getImageChambre)
                .findFirst();
    }
    public Chambre createRoomType(Chambre roomType) {
        if (chambreRepository.existsByType(roomType.getType())) {
            throw new IllegalArgumentException("Room type " + roomType.getType() + " already exists");
        }
        return chambreRepository.save(roomType);
    }
    public Map<String, Boolean> updateDisponibilite(Long hotelId, String type, boolean disponible) {
        // 1. Valider le type de chambre
        List<String> typesValides = List.of("simple", "double", "suite");
        if (!typesValides.contains(type)) {
            throw new IllegalArgumentException("Type de chambre invalide");
        }

        // 2. Trouver et mettre à jour les chambres
        List<Chambre> chambres = chambreRepository.findByHotelIdAndType(hotelId, type);
        chambres.forEach(chambre -> {
            chambre.setDisponible(disponible);
            chambreRepository.save(chambre);
        });

        // 3. Retourner le nouvel état
        return getDisponibiliteParType(hotelId);
    }
} 


