package com.produits;

import java.util.List;

public interface IProduitDAO extends IDAO<Produit> {
    public List<Produit> getAll(String des);
}
