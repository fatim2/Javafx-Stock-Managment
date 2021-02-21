package com.produits;

public class Categorie {
    Long id;
    private String categorie;
    private Produit[] produits = new Produit[100];

   public void setCategorie(String categorie) {
       this.categorie = categorie;
   }
   public void setProduits(Produit[] produits) {
       this.produits = produits;
   }

   Categorie(Long id){
        this(id, null); 
    }

    Categorie(String  categorie){ 
        this(null, categorie); 
    }

    Categorie(Long id, String categorie) {
         this.id = id;
          this.categorie = categorie; 
        }
}
