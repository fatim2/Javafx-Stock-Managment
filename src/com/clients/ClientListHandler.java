package com.clients;

import java.util.ArrayList;
import java.util.List;

public class ClientListHandler {
    ClientListWindow listWindow = null;

    public ClientListHandler(ClientListWindow listWindow){
        this.listWindow = listWindow;
    }

 
    public void updateClientListWindows(){
        
        IClientDAO pdao = new ClientDaolmpl();
        List<Client> list = pdao.getAll();
        listWindow.clientObservableList.addAll(list);

    }


	public void delete(Client client) {
        IClientDAO pdao = new ClientDaolmpl();
        pdao.delete(client.getId());
        listWindow.clientObservableList.clear();
        updateClientListWindows();
	}


	public void searchClient(String nom) {
        IClientDAO pdao = new ClientDaolmpl();
        List<Client> list = new ArrayList<>();
        list = pdao.getAll(nom);
        listWindow.clientObservableList.clear();
        listWindow.clientObservableList.addAll(list);
    }
    
    public void updateClient(){
        IClientDAO pdao = new ClientDaolmpl();
        List<Client> list = pdao.getAll();
        
    }

    
}
