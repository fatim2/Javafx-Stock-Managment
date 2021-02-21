package com.produits;

import java.time.LocalDate;

import javafx.scene.control.Button;

public class Produit {
    private long id;
    private String designation;
    private int qte;
    private double prix;
    private LocalDate date;
    private double total;
    private String categorie;
    public Produit(){
    }
    
    public long getId(){
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getDesignation() {
        return designation;
    }
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public int getQte() {
        return qte;
    }
    public void setQte(int qte) {
        this.qte = qte;
    }
    public double getTotal() {
        return this.qte*this.prix;
    }
    public String getCategorie() {
        return categorie;
    }
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    @Override
    public String toString() {
        return designation + "\t" + categorie + "\t" + qte + "\t" + prix + "\t" + date;
    }
    public Produit(long id, String designation, String categorie,  int qte, double prix,LocalDate date){
        this.id = id;
        this.designation = designation;
        this.categorie = categorie;
        setQte(qte);
        setPrix(prix);
        this.date = date;
        this.total=getTotal();
    }
}

