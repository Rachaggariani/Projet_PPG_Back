package com.example.demo.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Chambre;
import com.example.demo.entities.Hotel;
import com.example.demo.services.HotelService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/hotels")
@CrossOrigin(origins = "*")
public class HotelController {
	@Autowired
	private HotelService hotelService;

	@GetMapping
	public ResponseEntity<List<Hotel>> getAllHotels() {
		List<Hotel> hotels = hotelService.getAllHotels();
		return ResponseEntity.ok().body(hotels); // Auto-sets Content-Type: application/json
	}

	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
		Hotel hotel = hotelService.getHotelById(id);
		return new ResponseEntity<>(hotel, HttpStatus.OK);
	}

	/*
	 * @PostMapping public ResponseEntity<Hotel> createHotel(@RequestBody Hotel
	 * hotel) { if(hotel.getChambres() != null) {
	 * hotel.getChambres().forEach(chambre -> { if(chambre.getHotel() == null) {
	 * chambre.setHotel(hotel); } }); }
	 * 
	 * Hotel savedHotel = hotelService.saveHotel(hotel); return new
	 * ResponseEntity<>(savedHotel, HttpStatus.CREATED); }
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
		Hotel savedHotel = hotelService.saveHotel(hotel);
		return new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
	}

	 @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	    public ResponseEntity<Hotel> createHotel(
	            @RequestParam("nomHotel") String nomHotel,
	            @RequestParam("adresseHotel") String adresseHotel,
	            @RequestParam("rating") int rating,
	            @RequestParam("tel") int tel,
	            @RequestParam("serviceDescription") String serviceDescription,
	            @RequestParam("chambres") String chambresJson,
	            @RequestParam(value = "image", required = false) MultipartFile image) throws IOException {

	        // Parse chambres JSON
	        ObjectMapper objectMapper = new ObjectMapper();
	        Chambre[] chambres = objectMapper.readValue(chambresJson, Chambre[].class);

	        // Create hotel
	        Hotel hotel = new Hotel();
	        hotel.setNomHotel(nomHotel);
	        hotel.setAdresseHotel(adresseHotel);
	        hotel.setRating(rating);
	        hotel.setTel(tel);
	        hotel.setServiceDescription(serviceDescription);
	        hotel.setChambres(Arrays.asList(chambres));

	        // Save hotel
	        Hotel savedHotel = hotelService.saveHotel(hotel);

	   
	        return ResponseEntity.ok(savedHotel);
	    }
	

	/*@PutMapping
	public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel) {
		Hotel updatedHotel = hotelService.updateHotel(hotel);
		return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
	}*/
	/*@PutMapping("/{id}")
	@CrossOrigin(origins = "*", methods = {RequestMethod.PUT})
	public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
	    hotel.setId(id); // S'assurer que l'ID est bien défini
	    Hotel updatedHotel = hotelService.updateHotel(hotel);
	    return new ResponseEntity<>(updatedHotel, HttpStatus.OK);
	}
*/
	@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Hotel> updateHotel(
	    @PathVariable Long id,
	    @RequestPart("hotel") String hotelJson,
	    @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
	    
	    ObjectMapper mapper = new ObjectMapper();
	    Hotel hotel = mapper.readValue(hotelJson, Hotel.class);
	    
	    // Vérifier que l'ID correspond
	    if (!id.equals(hotel.getId())) {
	        return ResponseEntity.badRequest().build();
	    }
	    
	    // Traitement de l'image si elle existe
	    if (image != null && !image.isEmpty()) {
	        String imageName = StringUtils.cleanPath(image.getOriginalFilename());
	        hotel.setImages(imageName);
	        // Sauvegarder le fichier si nécessaire
	    }
	    
	    Hotel updatedHotel = hotelService.updateHotel(hotel);
	    return ResponseEntity.ok(updatedHotel);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteHotel(@PathVariable Long id) {
		String nomHotel = hotelService.deleteHotel(id);
		return ResponseEntity.ok("L'hôtel \"" + nomHotel + "a été supprimé avec succès");
	}

	
}
