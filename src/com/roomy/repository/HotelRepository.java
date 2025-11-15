package com.roomy.repository;

import com.roomy.model.entity.Hotel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HotelRepository {
    private final Map<UUID, Hotel> hotels = new HashMap<>();

    public void save(Hotel hotel) {
        hotels.put(hotel.getId(), hotel);
    }

    public Hotel findById(UUID id) {
        return hotels.get(id);
    }
}