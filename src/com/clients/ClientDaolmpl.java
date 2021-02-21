package com.clients;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Handler;

import com.paiements.Server.Compte;
import com.produits.ProduitAddHandler;
import com.ventes.Vente;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class ClientDaolmpl extends AbstractDAO implements IClientDAO {
    FormClientsWindow formClient = null;
    
        
    @Override
    public void add(Client obj) {
        PreparedStatement pst = null;
        String sql = "INSERT INTO client (nom_client,prenom_client,tel_client,email,adresse) values(?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, obj.getNom());
            pst.setString(2, obj.getPrenom());
            pst.setString(3, obj.getTelephone());
            pst.setString(4, obj.getEmail());
            pst.setString(5, obj.getAdresse());
            System.out.println("success");
            pst.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setContentText("Ajouté avec succes!");
            alert.showAndWait();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        PreparedStatement pst = null;
        String sql = "DELETE  FROM client WHERE id_client=?";
        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Supprimer");
            alert.setContentText("Voulez-vous supprimer ce Client?");
            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent() || result.get() != ButtonType.OK) {
                return;
            } else {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            System.out.println("success");
            pst.executeUpdate();
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }
    }

    @Override
    public Client getOne(long id) {
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "SELECT * FROM client WHERE id_client=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            System.out.println("success");
            rs = pst.executeQuery();
            if (rs.next()) {
                return new Client(rs.getLong("id_client"), rs.getString("nom_client"), rs.getString("prenom_client"),rs.getString("tel_client"),rs.getString("email"),rs.getString("adresse"));
            }
        } catch (SQLException exp) {
            // TODO: handle exception
            System.out.println(exp.getMessage());
        }
        return null;
    }
    
    @Override
    public List<Client> getAll() {
        List<Client> list = new ArrayList<Client>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "SELECT * FROM client";
        try {
            pst = connection.prepareStatement(sql);
            System.out.println("success");
            rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Client(rs.getLong("id_client"), rs.getString("nom_client"), rs.getString("prenom_client"),rs.getString("tel_client"),rs.getString("email"),rs.getString("adresse")));
            }
        } catch (SQLException exp) {
            // TODO: handle exception
            System.out.println(exp.getMessage());
        }
        return list;
    }

    @Override
    public List<Client> getAll(String nom) {
        System.out.println("chercher..");
        List<Client> list = new ArrayList<Client>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql = "SELECT * FROM client WHERE nom_client LIKE ? ";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, "%" +nom + "%");
            System.out.println("success");
            rs = pst.executeQuery();
            while (rs.next()) 
                list.add(new Client(rs.getLong("id_client"), rs.getString("nom_client"), rs.getString("prenom_client"),rs.getString("tel_client"),rs.getString("email"),rs.getString("adresse")));
        } 
        catch (SQLException exp){
            System.out.println(exp.getMessage());
        }
        return list;
    }
    public void updateNom(String nom,long id){
        PreparedStatement pst = null;
        String sql="UPDATE Client SET nom_client = ? WHERE id_client = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(2, id);
            pst.setString(1, nom);
            System.out.println("Update nom Success");
            int r = pst.executeUpdate();
            System.out.println(r);
        } catch (SQLException exp) {
            //TODO: handle exception
            System.out.println(exp.getMessage());
        }
        
    }
    public void updatePrenom(String prenom,long id){
        PreparedStatement pst = null;

        String sql="UPDATE Client SET prenom_client = ? WHERE id_client = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(2, id);
            pst.setString(1, prenom);
            System.out.println("Update prenom Success");
            int r = pst.executeUpdate();
            System.out.println(r);
        } catch (SQLException exp) {
            //TODO: handle exception
            System.out.println(exp.getMessage());
        }
        
    }
    public void updateEmail(String email,long id){
        PreparedStatement pst = null;
        ClientAddHandler handler = new ClientAddHandler();
        if(handler.validateEmail()){
            String sql="UPDATE Client SET email = ? WHERE id_client = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(2, id);
            pst.setString(1, email);
            System.out.println("Update email Success");
            int r = pst.executeUpdate();
            System.out.println(r);
        } catch (SQLException exp) {
            //TODO: handle exception
            System.out.println(exp.getMessage());
        }
        }
    }
    public void updateAdresse(String adresse,long id){
        PreparedStatement pst = null;
        String sql="UPDATE Client SET adresse = ? WHERE id_client = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(2, id);
            pst.setString(1, adresse);
            System.out.println("Update adresse Success");
            int r = pst.executeUpdate();
            System.out.println(r);
        } catch (SQLException exp) {
            //TODO: handle exception
            System.out.println(exp.getMessage());
        }
        
    }
    public void updateTelephone(String tel,long id){
        PreparedStatement pst = null;
        ClientAddHandler handler = new ClientAddHandler();
        if(handler.validateNumber(tel)){
            String sql="UPDATE Client SET tel_client = ? WHERE id_client = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(2, id);
            pst.setString(1, tel);
            System.out.println("Update tel Success");
            int r = pst.executeUpdate();
            System.out.println(r);
        } catch (SQLException exp) {
            //TODO: handle exception
            System.out.println(exp.getMessage());
        }
        
    }
        }
    
        
        
}