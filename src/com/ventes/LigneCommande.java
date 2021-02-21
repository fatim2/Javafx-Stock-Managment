package com.ventes;

import java.util.ArrayList;
import java.util.List;

import com.produits.Produit;

public class LigneCommande {
    private long id;
    private int qteVendu;
    private double prixVente;
    private double total;
    private long idProduit;
    private List<Produit> produits = new ArrayList<Produit>();

    public long getId() {
        return id;
    }
    public long getidProduit() {
        return idProduit;
    }
    public void setidProduit(long idProduit) {
        this.idProduit = idProduit;
    }

    public double getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public List<Produit> getProduits() {
        return produits;
    }
    public int getQteVendu() {
        return qteVendu;
    }
    public double getTotal() {
        return this.qteVendu*this.prixVente;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
    public void setQteVendu(int qteVendu) {
        this.qteVendu = qteVendu;
    }
    public LigneCommande(long id, int qteVendu, double prixVente, long idProduit){
        this.id = id;
        this.qteVendu = qteVendu;
        this.prixVente = prixVente;
        this.idProduit = idProduit;
        this.total = getTotal();
    }
    public LigneCommande(){};
}
