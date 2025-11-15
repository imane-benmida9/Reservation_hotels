package com.roomy.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Hotel {
    private UUID id;
    private String nom;
    private String description;
    private int etoiles;
    private UUID gestionnaireId;
    private Adresse adresse;
    private List<String> amenities = new ArrayList<>();
    private List<UUID> chambres = new ArrayList<>();

    public Hotel() {
        this.id = UUID.randomUUID();
    }

    public void ajouterChambre(UUID chambreId) {
        chambres.add(chambreId);
    }

    // Getters & Setters
    public UUID getId() { return id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getEtoiles() { return etoiles; }
    public void setEtoiles(int etoiles) { this.etoiles = etoiles; }
    public UUID getGestionnaireId() { return gestionnaireId; }
    public void setGestionnaireId(UUID gestionnaireId) { this.gestionnaireId = gestionnaireId; }
    public Adresse getAdresse() { return adresse; }
    public void setAdresse(Adresse adresse) { this.adresse = adresse; }
    public List<String> getAmenities() { return amenities; }
    public List<UUID> getChambres() { return chambres; }
}