package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Chambre;
import com.example.demo.entities.Hotel;

public interface HotelService {
	Hotel saveHotel(Hotel hotel);

	List<Hotel> getAllHotels();

	Hotel getHotelById(Long id);

	Hotel updateHotel(Hotel hotel);

	public String deleteHotel(Long id);
    public boolean existsById(Long id);
}
