package com.produits;
import java.sql.Date;
import java.time.LocalDate;

public class ProduitAddHandler {
    IProduitDAO pdao = new ProduitDaolmpl();
    FormProduitWindow formProduit = null;
    public ProduitAddHandler(FormProduitWindow formProduit){
        this.formProduit = formProduit;
    }
    public void addClick(){
        String designation =  formProduit.produitDesignationTextField.getText();
        String categorie =  formProduit.produitCategorieTextField.getText();
        int qte = Integer.valueOf(formProduit.produitQteTextField.getText());
        double prix = Double.valueOf(formProduit.produitPrixTextField.getText());
        LocalDate date = formProduit.produitPickDatePicker.getValue();
        //Date dateSql = Date.valueOf(date);
        Produit p = new Produit(0,designation,categorie, qte, prix, date);
        pdao.add(p);
        
    }
}
