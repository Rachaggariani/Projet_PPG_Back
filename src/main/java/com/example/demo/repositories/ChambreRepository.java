package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Chambre;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
	/* List<Chambre> findByHotelId(Long hotelId); */
	/*
	 * List<Chambre> findByHotelId(Long hotelId);
	 * 
	 * List<Chambre> findByHotelIdAndType(Long hotelId, String type);
	 * 
	 * List<Chambre> findByHotelIdAndTypeAndDisponibleTrue(Long hotelId, String
	 * type);
	 */
	/*
	 * List<Chambre> findByHotelId(Long hotelId); List<Chambre>
	 * findByHotelIdAndType(Long hotelId, String type); List<Chambre>
	 * findByHotelIdAndTypeAndDisponibleTrue(Long hotelId, String type); Long
	 * countByHotelIdAndTypeAndDisponibleTrue(Long hotelId, String type); boolean
	 * existsById(Long id);
	 */
	 // Ajoutez cette méthode
    @Query("SELECT c FROM Chambre c WHERE c.hotel.id = :hotelId")
    List<Chambre> findByHotelId(@Param("hotelId") Long hotelId);
    
    // Méthode existante
    List<Chambre> findByHotelIdAndTypeAndDisponibleTrue(Long hotelId, String type);
    boolean existsByType(String type);
    // Ajoutez cette méthode
    @Query("SELECT c FROM Chambre c WHERE c.hotel.id = :hotelId AND c.type = :type")
    List<Chambre> findByHotelIdAndType(@Param("hotelId") Long hotelId, @Param("type") String type);
}
