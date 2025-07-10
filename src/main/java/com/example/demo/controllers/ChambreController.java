package com.example.demo.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Chambre;
import com.example.demo.services.ChambreService;

@RestController
@RequestMapping("/chambres")
@CrossOrigin(origins = "*")
public class ChambreController {
		@Autowired
	    private ChambreService chambreService;
		@Autowired
	    private ChambreService hotelService;

		/*
		 * // 1. Récupérer toutes les chambres d'un hôtel
		 * 
		 * @GetMapping("/hotel/{hotelId}") public ResponseEntity<List<Chambre>>
		 * getChambresByHotel(@PathVariable Long hotelId) { List<Chambre> chambres =
		 * chambreService.getChambresByHotel(hotelId); return
		 * ResponseEntity.ok(chambres); }
		 * 
		 * // 2. Récupérer les chambres par type pour un hôtel spécifique
		 * 
		 * @GetMapping("/hotel/{hotelId}/type/{type}") public
		 * ResponseEntity<List<Chambre>> getChambresByHotelAndType(
		 * 
		 * @PathVariable Long hotelId,
		 * 
		 * @PathVariable String type) { List<Chambre> chambres =
		 * chambreService.getChambresByHotelAndType(hotelId, type); return
		 * ResponseEntity.ok(chambres); }
		 * 
		 * // 3. Récupérer les chambres disponibles par type pour un hôtel
		 * 
		 * @GetMapping("/hotel/{hotelId}/type/{type}/disponibles") public
		 * ResponseEntity<List<Chambre>> getAvailableChambresByHotelAndType(
		 * 
		 * @PathVariable Long hotelId,
		 * 
		 * @PathVariable String type) { List<Chambre> chambres =
		 * chambreService.getAvailableChambresByHotelAndType(hotelId, type); return
		 * ResponseEntity.ok(chambres); }
		 * 
		 * // 4. Compter les chambres disponibles par type
		 * 
		 * @GetMapping("/hotel/{hotelId}/type/{type}/count") public ResponseEntity<Long>
		 * countAvailableChambresByType(
		 * 
		 * @PathVariable Long hotelId,
		 * 
		 * @PathVariable String type) { Long count =
		 * chambreService.countAvailableChambresByType(hotelId, type); return
		 * ResponseEntity.ok(count); }
		 * 
		 * // 5. Récupérer une chambre spécifique
		 * 
		 * @GetMapping("/{id}") public ResponseEntity<Chambre>
		 * getChambreById(@PathVariable Long id) { return
		 * chambreService.getChambreById(id) .map(ResponseEntity::ok)
		 * .orElse(ResponseEntity.notFound().build()); }
		 * 
		 * // 6. Créer une nouvelle chambre
		 * 
		 * @PostMapping public ResponseEntity<Chambre> createChambre(@RequestBody
		 * Chambre chambre) { Chambre savedChambre =
		 * chambreService.saveChambre(chambre); return ResponseEntity.ok(savedChambre);
		 * }
		 * 
		 * @PutMapping("/{id}") public ResponseEntity<?> updateChambre(
		 * 
		 * @PathVariable Long id,
		 * 
		 * @RequestBody Chambre chambreUpdate) {
		 * 
		 * // 1. Vérifier que la chambre existe if (!chambreService.existsById(id)) {
		 * return ResponseEntity.notFound().build(); }
		 * 
		 * // 2. Obtenir la chambre existante Chambre existingChambre =
		 * chambreService.getChambreById(id).get();
		 * 
		 * // 3. Si l'hôtel est fourni dans la requête, vérifier son existence if
		 * (chambreUpdate.getHotel() != null && chambreUpdate.getHotel().getId() !=
		 * null) { if (chambreService.existsById(chambreUpdate.getHotel().getId())) {
		 * return ResponseEntity.badRequest() .body("L'hôtel avec ID " +
		 * chambreUpdate.getHotel().getId() + " n'existe pas"); } }
		 * 
		 * // 4. Mise à jour partielle if (chambreUpdate.getNumero() != null) {
		 * existingChambre.setNumero(chambreUpdate.getNumero()); } if
		 * (chambreUpdate.getType() != null) {
		 * existingChambre.setType(chambreUpdate.getType()); }
		 * existingChambre.setDisponible(chambreUpdate.isDisponible()); if
		 * (chambreUpdate.getPrix() > 0) {
		 * existingChambre.setPrix(chambreUpdate.getPrix()); } if
		 * (chambreUpdate.getDescription() != null) {
		 * existingChambre.setDescription(chambreUpdate.getDescription()); }
		 * 
		 * // 5. Sauvegarder return
		 * ResponseEntity.ok(chambreService.saveChambre(existingChambre)); }
		 */
		 @GetMapping("/{hotelId}/chambres")
		    public ResponseEntity<List<Chambre>> getChambresByHotel(@PathVariable Long hotelId) {
		        return ResponseEntity.ok(chambreService.getChambresByHotel(hotelId));
		    }

		    @GetMapping("/{hotelId}/disponibilite")
		    public ResponseEntity<Map<String, Boolean>> checkDisponibilite(@PathVariable Long hotelId) {
		        return ResponseEntity.ok(chambreService.getDisponibiliteParType(hotelId));
		    }
		    
		    @GetMapping("/{hotelId}/image/{roomType}")
		    public ResponseEntity<String> getRoomImage(
		            @PathVariable Long hotelId,
		            @PathVariable String roomType) {
		        
		        Optional<String> imageUrl = chambreService.getFirstRoomImage(hotelId, roomType);
		        
		        return imageUrl
		                .map(ResponseEntity::ok)
		                .orElseGet(() -> ResponseEntity.notFound().build());
		    }
		    
		    @PostMapping
		    public ResponseEntity<Chambre> createRoomType(@RequestBody Chambre roomType) {
		        Chambre createdRoomType = chambreService.createRoomType(roomType);
		        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoomType);
		    }
		    @PutMapping("/hotels/{hotelId}/disponibilite")
		    public ResponseEntity<Map<String, Boolean>> updateDisponibilite(
		            @PathVariable Long hotelId,
		            @RequestParam String type,
		            @RequestParam boolean disponible) {
		        
		        try {
		            Map<String, Boolean> disponibilite = chambreService.updateDisponibilite(hotelId, type, disponible);
		            return ResponseEntity.ok(disponibilite);
		        } catch (IllegalArgumentException e) {
		            return ResponseEntity.badRequest().body(null);
		        } catch (Exception e) {
		            return ResponseEntity.internalServerError().body(null);
		        }
		    }
}
