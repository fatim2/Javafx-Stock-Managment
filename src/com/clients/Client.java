package com.clients;

import java.io.Serializable;

import com.paiements.Server.Compte;

public class Client implements Serializable {
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private Compte compte;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setCompte(Compte compte) {
        this.compte = compte;
    }
    public Compte getCompte() {
        return compte;
    }


    @Override
    public String toString() {
        return nom + "\t" + prenom + "\t" + email + "\t" + telephone + "\t" + adresse;

    }

    public Client(long id,String nom,String prenom,String telephone,String email,String adresse){
        setId(id);
        setAdresse(adresse);
        setEmail(email);
        setNom(nom);
        setPrenom(prenom);
        setTelephone(telephone);
    }
    public Client(){};
}
