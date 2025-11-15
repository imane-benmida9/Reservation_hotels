package com.roomy.repository;

import com.roomy.model.enums.StatutReservation;

import java.time.LocalDate;
import java.time.LocalDateTime;  // AJOUTÉ ICI
import java.util.*;
import java.util.stream.Collectors;

/**
 * Repository en mémoire pour les réservations.
 * Gère le stockage, la recherche et les filtres avancés.
 */
public class ReservationRepository {

    private final Map<UUID, Reservation> reservations = new HashMap<>();
    private final Map<UUID, List<Reservation>> reservationsParChambre = new HashMap<>();

    public void save(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
        reservationsParChambre
                .computeIfAbsent(reservation.getChambreId(), k -> new ArrayList<>())
                .add(reservation);
    }

    public Reservation findById(UUID id) {
        return reservations.get(id);
    }

    public List<Reservation> findAll() {
        return new ArrayList<>(reservations.values());
    }

    public List<Reservation> findByClientId(UUID clientId) {
        return reservations.values().stream()
                .filter(r -> r.getClientId().equals(clientId))
                .collect(Collectors.toList());
    }

    public List<Reservation> findByHotelId(UUID hotelId) {
        return reservations.values().stream()
                .filter(r -> r.getHotelId().equals(hotelId))
                .collect(Collectors.toList());
    }

    public List<Reservation> findByChambreId(UUID chambreId) {
        return reservationsParChambre.getOrDefault(chambreId, new ArrayList<>());
    }

    public boolean estChambreDisponible(UUID chambreId, LocalDate debut, LocalDate fin) {
        return findByChambreId(chambreId).stream()
                .filter(r -> r.getStatut() == StatutReservation.CONFIRMED ||
                        r.getStatut() == StatutReservation.PENDING)
                .noneMatch(r -> !fin.isBefore(r.getDateDebut()) && !debut.isAfter(r.getDateFin()));
    }

    public List<Reservation> findPendingExpired(int minutes) {
        LocalDateTime seuil = LocalDateTime.now().minusMinutes(minutes);
        return reservations.values().stream()
                .filter(r -> r.getStatut() == StatutReservation.PENDING)
                .filter(r -> r.getDateCreation().isBefore(seuil))
                .collect(Collectors.toList());
    }

    public void delete(UUID reservationId) {
        Reservation res = reservations.get(reservationId);
        if (res != null) {
            reservations.remove(reservationId);
            List<Reservation> liste = reservationsParChambre.get(res.getChambreId());
            if (liste != null) {
                liste.removeIf(r -> r.getId().equals(reservationId));
            }
        }
    }

    public Map<UUID, Reservation> getReservations() {
        return Collections.unmodifiableMap(reservations);
    }
}