package com.roomy.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChambreRepository {
    private final Map<UUID, Chambre> chambres = new HashMap<>();

    public void save(Chambre chambre) {
        chambres.put(chambre.getId(), chambre);
    }

    public Chambre findById(UUID id) {
        return chambres.get(id);
    }
}