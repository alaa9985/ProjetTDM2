package com.github.florent37.materialviewpager.sample;

/**
 * Created by alaa eddine on 27/05/2017.
 */

public class Dossier {

    private String id;
    private String date  ;
    private String photo  ;
    private String video;
    private Adversaire adversaire;
    private boolean exist;
    private String etat ;
    private float montant;



    public Dossier() {

    }


    public Dossier(String id, String date, String photo, String video, Adversaire adversaire, boolean exist, String etat) {

        this.id=id;
        this.date = date;
        this.montant=0;
        this.photo = photo;
        this.video = video;
        this.adversaire = adversaire;
        this.etat=etat;
        this.exist = exist;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Adversaire getAdversaire() {
        return adversaire;
    }

    public void setAdversaire(Adversaire adversaire) {
        this.adversaire = adversaire;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }
}
