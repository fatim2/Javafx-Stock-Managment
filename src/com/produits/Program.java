package com.produits;
import java.time.LocalDate;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        IProduitDAO pdao = new ProduitDaolmpl();

        /*System.out.println("------------delete(5)");
        pdao.delete(5);*/

        System.out.println("------------add()");
        Produit prd = new Produit(1,"Galaxy 9","telephone", 3, 8500, LocalDate.now());
        pdao.add(prd);

        System.out.println("------------getAll()");
        List<Produit> list=pdao.getAll();
        for(Produit p:list)
            System.out.println(p);

        System.out.println("------------getOne()");
        Produit p=pdao.getOne(1);
        if(p!=null)
            System.out.println(p);
        else
            System.out.println("produit non existant");

        System.out.println("------------getAll(s)");
        List<Produit> list2=pdao.getAll("burau"); //starting of the designation
        for(Produit pr:list2)
            System.out.println(pr);
    }
}

/*ProduitDataAccess produitDataAccess = new ProduitDataAccess();
        List<Produit> list = produitDataAccess.getProduitByKeyWord("galaxy");

        for (Produit p:list) {
            System.out.println(p);
        }*/