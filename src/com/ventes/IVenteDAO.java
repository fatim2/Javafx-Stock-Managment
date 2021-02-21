package com.ventes;

import java.util.List;

import com.clients.Client;

public interface IVenteDAO {
    public List<Vente> getAll(String des);
    public void addToLigneCommande(LigneCommande ln, Vente vente);
    public List<Vente> getAll();
    public List<LigneCommande> getAllLigne();
    public List<Client> getAllClient();
	public void delete(long id);

}
