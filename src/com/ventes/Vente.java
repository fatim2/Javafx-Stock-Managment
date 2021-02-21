package com.ventes;
import com.clients.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.produits.Produit;

public class Vente {
    private long id;
    private LocalDate date;
    private double total;
    private long ligneCommandeId;
    private LigneCommande ligneComande;
    private long idClient;
    private Client client;
    private List<LigneCommande> ligneCommande = new ArrayList<>();
    private Produit produit;
    private int qtevendu;
    private double prix;
    
    public Vente(long id, long ligneCommandeId, LocalDate date, long idClient ){
        this.id = id;
        this.date = date;
        this.ligneCommandeId = ligneCommandeId;
        this.idClient = idClient;
    }
    public void setQtevendu(int qtevendu) {
        this.qtevendu = qtevendu;
    }
    public int getQtevendu() {
        return qtevendu;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public double getTotal() {
        return this.qtevendu * this.prix;
    }
    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    public Produit getProduit() {
        return produit;
    }
    public LigneCommande getLigneComande() {
        return ligneComande;
    }
    public void setLigneComande(LigneCommande ligneComande) {
        this.ligneComande = ligneComande;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }
    public long getIdClient() {
        return idClient;
    }
    public List<LigneCommande> getLigneCommande() {
        return ligneCommande;
    }
    
    public void setLigneCommande(List<LigneCommande> ligneCommande) {
        this.ligneCommande = ligneCommande;
    }
    public void setLigneCommandeId(long ligneCommandeId) {
        this.ligneCommandeId = ligneCommandeId;
    }
    
    public long getLigneCommandeId() {
        return ligneCommandeId;
    }
  

    public Vente() {
	}
	public void setId(long id) {
        this.id = id;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public long getId() {
        return id;
    }
    public LocalDate getDate() {
        return date;
    }
	public void setPrixVente(double prix) {
        this.prix = prix;
    }
    public double getPrixVente() {
        return prix;
    }
    
    public Vente(long id, Client client, Produit produit, LocalDate date, int qte , double total, double prix){
        this.client = client;
        this.id = id;
        this.produit = produit;
        this.date = date;
        this.qtevendu = qte;
        this.total = total;
        this.prix = prix;
    }
    
    
    
}
