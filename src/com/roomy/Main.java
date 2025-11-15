package com.roomy;

import com.roomy.model.entity.*;
import com.roomy.model.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        client.setNom("Dupont");
        client.setEmail("client@gmail.com");

        Hotel hotel = new Hotel();
        hotel.setNom("Hôtel Paris Centre");

        Chambre chambre = new Chambre();
        chambre.setNumero("101");
        chambre.setType(TypeChambre.DOUBLE);
        chambre.setPrixParNuit(BigDecimal.valueOf(120));

        Reservation res = new Reservation();
        res.setClientId(client.getId());
        res.setHotelId(hotel.getId());
        res.setChambreId(chambre.getId());
        res.setDateDebut(LocalDate.now().plusDays(1));
        res.setDateFin(LocalDate.now().plusDays(3));


        System.out.println("Réservation créée : " + res.getId());
        System.out.println("Statut : " + res.getStatut());
    }
}