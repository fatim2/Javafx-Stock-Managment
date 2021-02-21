package com.ventes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.clients.Client;
import com.clients.ClientDaolmpl;
import com.clients.IClientDAO;
import com.produits.*;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddVenteHandler {
	IVenteDAO vdao = new VenteDaolmpl();
	AddVenteWindow venteWindow = null;

	public AddVenteHandler(AddVenteWindow venteWindow) {
		this.venteWindow = venteWindow;
	}
	private void calculerTotal(){
        double total =0;
        for (Vente v:venteWindow.venteProduitObservableList) total+=v.getTotal();
        
    }
	
	public void updateProduitsVenteWindows(){
        IProduitDAO dao = new ProduitDaolmpl();
		List<Produit> list = dao.getAll();
		venteWindow.ProduitObservableList.clear();
		venteWindow.ProduitObservableList.addAll(list);
	}
	public void updateClientsVenteWindows(){
        IClientDAO cao = new ClientDaolmpl();
		List<Client> list = cao.getAll();
		
		venteWindow.clientObservableList.addAll(list);
		System.out.println(list);
	}

	public void updateVenteWindows(){
		IVenteDAO dao = new VenteDaolmpl();
		List<Vente> listVente = dao.getAll() ;
		venteWindow.venteProduitObservableList.addAll(listVente);
		System.out.println(listVente);
		venteWindow.venteProduitObservableList.clear();
		venteWindow.venteProduitObservableList.addAll(listVente);
		calculerTotal();
	}
	public void addToLigneCommande() {
		int qte = Integer.valueOf(venteWindow.venteQteTextField.getText());
		double prix = Double.valueOf(venteWindow.prixVenteTextField.getText());
		long idProduit = Long.valueOf(venteWindow.idTextField.getText());
		LigneCommande lc = new LigneCommande(0, qte, prix,idProduit);
		
		LocalDate date = venteWindow.datePickerVente.getValue();
		long idClient = Long.valueOf(venteWindow.idClientTextField.getText());
		Vente vente = new Vente(0, lc.getId(), date, idClient);
		vdao.addToLigneCommande(lc,vente);
		venteWindow.ProduitObservableList.clear();
		updateProduitsVenteWindows();
	}


	public void delete(Vente vente) {
		IVenteDAO vdao = new VenteDaolmpl();
        vdao.delete(vente.getId());
        venteWindow.venteProduitObservableList.clear();
		updateVenteWindows();
		updateProduitsVenteWindows();
	}
	public void payerVente(Vente vente) {
		
	}

	
}
