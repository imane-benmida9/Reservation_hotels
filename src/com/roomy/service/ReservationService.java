package com.roomy.service;

import com.roomy.model.entity.*;
import com.roomy.model.enums.StatutReservation;
import com.roomy.model.enums.StatutTechniqueChambre;
import com.roomy.repository.ChambreRepository;
import com.roomy.repository.HotelRepository;
import com.roomy.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.UUID;

public class ReservationService {
    private final HotelRepository hotelRepo = new HotelRepository();
    private final ChambreRepository chambreRepo = new ChambreRepository();
    private final ReservationRepository reservationRepo = new ReservationRepository();

    public Reservation initierReservation(UUID clientId, UUID chambreId,
                                          LocalDate dateDebut, LocalDate dateFin) {
        Chambre chambre = chambreRepo.findById(chambreId);
        if (chambre == null) throw new IllegalArgumentException("Chambre introuvable");
        if (chambre.getStatutTechnique() != StatutTechniqueChambre.DISPONIBLE) {
            throw new IllegalStateException("Chambre en maintenance ou nettoyage");
        }

        // CORRIGÉ ICI : on utilise la méthode publique
        if (!reservationRepo.estChambreDisponible(chambreId, dateDebut, dateFin)) {
            throw new IllegalStateException("Chambre déjà réservée à ces dates");
        }

        Reservation reservation = new Reservation();
        reservation.setClientId(clientId);
        reservation.setChambreId(chambreId);
        reservation.setHotelId(chambre.getHotelId());
        reservation.setDateDebut(dateDebut);
        reservation.setDateFin(dateFin);
        reservation.setStatut(StatutReservation.PENDING);

        reservationRepo.save(reservation);
        return reservation;
    }

    public void confirmerReservation(UUID reservationId) {
        Reservation res = reservationRepo.findById(reservationId);
        if (res == null) throw new IllegalArgumentException("Réservation introuvable");
        res.confirmer();
    }

    public void annulerReservation(UUID reservationId) {
        Reservation res = reservationRepo.findById(reservationId);
        if (res == null) throw new IllegalArgumentException("Réservation introuvable");
        res.annuler();
    }

    // Getters pour tests
    public HotelRepository getHotelRepo() { return hotelRepo; }
    public ChambreRepository getChambreRepo() { return chambreRepo; }
    public ReservationRepository getReservationRepo() { return reservationRepo; }
}