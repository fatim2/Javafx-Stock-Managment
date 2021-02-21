package com.produits;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProduitsListHandler {
    ProduitsListWindow listWindow = null;

    public ProduitsListHandler(ProduitsListWindow listWindow){
        this.listWindow = listWindow;
    }
 
    private void calculerTotal(){
        double total = 0;
        for (Produit p:listWindow.produitObservableList) total+=p.getTotal();
        listWindow.totLabelVaLue.setText(total+"");
    }

    public void searchProduit(String des) {
        IProduitDAO pdao = new ProduitDaolmpl();
        List<Produit> list = new ArrayList<>();
        list = pdao.getAll(des);
        listWindow.produitObservableList.clear();
        listWindow.produitObservableList.addAll(list);
        calculerTotal();
    }
    

    public void delete(Produit p){
        IProduitDAO pdao = new ProduitDaolmpl();
        pdao.delete(p.getId());
        listWindow.produitObservableList.clear();
        updateProduitsListWindows();
    }

    public void updateProduitsListWindows(){
        IProduitDAO pdao = new ProduitDaolmpl();
        List<Produit> list = pdao.getAll();
        listWindow.produitObservableList.addAll(list);
        calculerTotal();

    }
    public void updateProduits(){
        IProduitDAO pdao = new ProduitDaolmpl();
        List<Produit> list = pdao.getAll();
        calculerTotal();
    }

}
