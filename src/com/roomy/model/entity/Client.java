package com.roomy.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Client extends Utilisateur {
    private List<UUID> preferees = new ArrayList<>();
    private List<UUID> historiqueReservations = new ArrayList<>();

    public void ajouterFavori(UUID hotelId) {
        if (!preferees.contains(hotelId)) preferees.add(hotelId);
    }

    public void supprimerFavori(UUID hotelId) {
        preferees.remove(hotelId);
    }

    public void ajouterReservation(UUID reservationId) {
        historiqueReservations.add(reservationId);
    }

    // Getters
    public List<UUID> getPreferees() { return preferees; }
    public List<UUID> getHistoriqueReservations() { return historiqueReservations; }
}