package com.produits;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProduitDataAccess {
    String db="magasin_db";
    String user="root";
    String pwd = "";
    String url="jdbc:mysql://localhost:3306/"+db;
    Connection connection = null;
    public ProduitDataAccess(){
        try {
            connection = DriverManager.getConnection(url, user,pwd);
            System.out.println("conn");
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

    }
    List<Produit> getAll(){
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

    List<Produit> getProduitByKeyWord(String des){
            List<Produit> list = new ArrayList<Produit>();
            PreparedStatement pst = null;
            ResultSet rs;
            String sql="SELECT * FROM produit WHERE designation like ? ";
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
}
