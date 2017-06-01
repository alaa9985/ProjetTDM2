package com.github.florent37.materialviewpager.sample;

/**
 * Created by alaa eddine on 27/05/2017.
 */

public class Adversaire {
    private String nom ;
    private String prenom;
    private String MatriculeVehicule;
    private String numeroAssurance ;
    private String numeroPermetConduite;

    public Adversaire(String nom, String prenom, String matriculeVehicule, String numeroAssurance, String numeroPermetConduite) {
        this.nom = nom;
        this.prenom = prenom;
        MatriculeVehicule = matriculeVehicule;
        this.numeroAssurance = numeroAssurance;
        this.numeroPermetConduite = numeroPermetConduite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMatriculeVehicule() {
        return MatriculeVehicule;
    }

    public void setMatriculeVehicule(String matriculeVehicule) {
        MatriculeVehicule = matriculeVehicule;
    }

    public String getNumeroAssurance() {
        return numeroAssurance;
    }

    public void setNumeroAssurance(String numeroAssurance) {
        this.numeroAssurance = numeroAssurance;
    }

    public String getNumeroPermetConduite() {
        return numeroPermetConduite;
    }

    public void setNumeroPermetConduite(String numeroPermetConduite) {
        this.numeroPermetConduite = numeroPermetConduite;
    }
}

