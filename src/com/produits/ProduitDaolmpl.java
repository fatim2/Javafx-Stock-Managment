package com.produits;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mysql.cj.jdbc.result.UpdatableResultSet;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
public class ProduitDaolmpl extends AbstractDAO implements IProduitDAO {
    FormProduitWindow formProduit = null;
    @Override
    public void add(Produit obj) {
        PreparedStatement pst = null;
        String sql="INSERT INTO produit (designation,categorie,qte_produit,prix_produit,date_produit) values(?,?,?,?,?)";
        try {
            pst = connection.prepareStatement(sql);
            
            pst.setString(1, obj.getDesignation());
            pst.setString(2, obj.getCategorie());
            pst.setInt(3, obj.getQte());
            pst.setDouble(4, obj.getPrix());
            Date date = Date.valueOf(obj.getDate());
            pst.setDate(5, date);
            System.out.println("success");
            pst.executeUpdate();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ajouter nouveau Produit");
           
            alert.setContentText("Ajouté avec succes!");
            alert.showAndWait();
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }        
    }

    @Override
    public void delete(long id) {
        PreparedStatement pst = null;
        String sql="DELETE  FROM produit WHERE id_produit=?";
        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Supprimer");
            alert.setContentText("Voulez-vous supprimer ce Produit?");
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

    public Categorie getCategorie(){
        PreparedStatement pst = null;
        ResultSet rs;
        String sql="SELECT * FROM Categorie ";
        try {
            pst = connection.prepareStatement(sql);
            
            System.out.println("categorie success");
            rs= pst.executeQuery();
            if (rs.next()) {
                //System.out.println(rs.getLong("id")+" "+rs.getString("designation"));
                
                return new Categorie(rs.getLong("id_categorie"),rs.getString("nom_categorie"));
            }
        } catch (SQLException exp) {
            //TODO: handle exception
            System.out.println(exp.getMessage());
        }
        return null;
    }

    @Override
    public Produit getOne(long id) {
        PreparedStatement pst = null;
        ResultSet rs;
        String sql="SELECT * FROM produit WHERE id_produit=?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            System.out.println("success");
            rs= pst.executeQuery();
            if (rs.next()) {
                //System.out.println(rs.getLong("id")+" "+rs.getString("designation"));
                Date date = rs.getDate("date_produit");
                return new Produit(rs.getLong("id_produit"),rs.getString("designation"),rs.getString("categorie"),rs.getInt("qte_produit"),rs.getDouble("prix_produit"),date.toLocalDate());
            }
        } catch (SQLException exp) {
            //TODO: handle exception
            System.out.println(exp.getMessage());
        }
        return null;
    }

    @Override
    public List<Produit> getAll() {
        List<Produit> list = new ArrayList<Produit>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql="SELECT * FROM produit";
        try {
            pst = connection.prepareStatement(sql);
            System.out.println("success");
            rs= pst.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getLong("id")+" "+rs.getString("designation"));
                Date date = rs.getDate("date_produit");
                list.add(new Produit(rs.getLong("id_produit"),rs.getString("designation"),rs.getString("categorie"),rs.getInt("qte_produit"),rs.getDouble("prix_produit"),date.toLocalDate()));
            }
        } catch (SQLException exp) {
            //TODO: handle exception
            System.out.println(exp.getMessage());
        }
        return list;
    }

    @Override
    public List<Produit> getAll(String des) {
        System.out.println("chercher..");
        List<Produit> list = new ArrayList<Produit>();
        PreparedStatement pst = null;
        ResultSet rs;
        String sql="SELECT * FROM produit WHERE designation like ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, des+"%");
            System.out.println("success");
            rs= pst.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getLong("id")+" "+rs.getString("designation"));
                Date date = rs.getDate("date_produit");
                list.add(new Produit(rs.getLong("id_produit"),rs.getString("designation"),rs.getString("categorie"),rs.getInt("qte_produit"),rs.getDouble("prix_produit"),date.toLocalDate()));
            }
        } catch (SQLException exp) {
            //TODO: handle exception
            System.out.println(exp.getMessage());
        }
        return list;
    }

    public void updateDesignation(String des,long id){
        PreparedStatement pst = null;
        String sql="UPDATE Produit SET designation = ? WHERE id_produit = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(2, id);
            pst.setString(1, des);
            System.out.println("Update Success");
            int r = pst.executeUpdate();
            System.out.println(r);
        } catch (SQLException exp) {
            //TODO: handle exception
            System.out.println(exp.getMessage());
        }
        
    }

    public void updateQte(int qte, long id){
        PreparedStatement pst = null;
        int r;
        String sql="UPDATE Produit SET qte_produit = ? WHERE id_produit = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(2, id);
            pst.setInt(1, qte);
            System.out.println("Update qte Success");
            r=pst.executeUpdate();
            System.out.println(r + "qte");
        } catch (SQLException exp) {
            //TODO: handle exception
            System.out.println(exp.getMessage());

        }
        
    }
    public void updatePrix(double prix,long id){
        PreparedStatement pst = null;
        int r;
        String sql="UPDATE Produit SET prix_produit = ? WHERE id_produit = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(2, id);
            pst.setDouble(1, prix);
            System.out.println("Update prix Success");
            r=pst.executeUpdate();
            System.out.println(r + "prix");
        } catch (SQLException exp) {
            //TODO: handle exception
            System.out.println(exp.getMessage());

        }
    }

    public void updateCategorie(String cat,long id){
        PreparedStatement pst = null;
        int r;
        String sql="UPDATE Produit SET categorie = ? WHERE id_produit = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, cat);
            pst.setLong(2, id);
            System.out.println("Update categorie Success");
            r=pst.executeUpdate();
            System.out.println(r);
        } catch (SQLException exp) {
            //TODO: handle exception
            System.out.println(exp.getMessage());

        }
    }

}