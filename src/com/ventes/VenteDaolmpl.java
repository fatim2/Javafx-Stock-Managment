package com.ventes;

import com.clients.ClientDaolmpl;
import com.produits.ProduitDaolmpl;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.clients.Client;
import java.sql.Statement;
public class VenteDaolmpl extends AbstractDAO implements IVenteDAO {
    AddVenteWindow venteWindow = null;
    
   


    @Override
    public void addToLigneCommande(LigneCommande ln,Vente vente) {
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sql="INSERT INTO lignecommande (qte_vendu,prix_vente,total,id_produit) values(?,?,?,?)";
        String sql2 = "INSERT INTO vente (date_vente,id_ligneCommande,id_client) values (?,?,?)";
        try {
            if( new ProduitDaolmpl().getOne(ln.getidProduit()).getQte() >= (ln.getQteVendu()) ){
                pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pst.setDouble(2, ln.getPrixVente());
                pst.setDouble(3, ln.getTotal());
                pst.setLong(4, ln.getidProduit());
                pst.setInt(1, ln.getQteVendu());
                new ProduitDaolmpl().updateQte(new ProduitDaolmpl().getOne(ln.getidProduit()).getQte()-ln.getQteVendu(), ln.getidProduit());
                System.out.println("updated");
                pst.executeUpdate();
                rs= pst.getGeneratedKeys();
                rs.next();
                long lasInsertedId = rs.getLong(1);
                PreparedStatement pst1 = null;
                pst1 = connection.prepareStatement(sql2);
                Date date = Date.valueOf(vente.getDate());
                pst1.setDate(1, date); 
                pst1.setLong(2, lasInsertedId);
                pst1.setLong(3, vente.getIdClient());
                pst1.executeUpdate();
                System.out.println("success vente");
            }
            if (new ProduitDaolmpl().getOne(ln.getidProduit()).getQte() < (ln.getQteVendu()) ) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Quantité insuportable");
                alert.setHeaderText(null);
                alert.setContentText("la quantité souhaité dépasse celle en stock!");
                alert.showAndWait();
            }
            
            System.out.println("success");
            
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }        
        
    }

    @Override
    public List<Vente> getAll(String des) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Vente> getAll() {
        List<Vente> list=new ArrayList<Vente>();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sql=("Select vente.id_vente, client.nom_client,lignecommande.id_produit,Client.prenom_client, vente.date_vente, vente.id_client,lignecommande.qte_vendu,lignecommande.prix_vente,lignecommande.total from vente JOIN client ON vente.id_client = client.id_client JOIN lignecommande ON lignecommande.id_ligne = vente.id_lignecommande");
		 try {
			pst=connection.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()) {
                Vente v = new Vente();
             v.setId(rs.getLong("id_vente"));
             v.setClient(new ClientDaolmpl().getOne(rs.getLong("id_client")));
             v.setProduit(new ProduitDaolmpl().getOne(rs.getLong("id_produit")));
             v.setDate(rs.getDate("date_vente").toLocalDate());
             v.setQtevendu(rs.getInt("qte_vendu"));
             v.setTotal(rs.getDouble("total"));
             v.setPrixVente(rs.getDouble("prix_vente"));
             list.add(v);
             System.out.println(v);
			}
			return list;
		 } catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public List<Client> getAllClient() {
        List<Client> list=new ArrayList<Client>();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sql=("Select produit.designation, ,client.nom_client,Client.prenom_client, vente.date_vente, vente.id_client,lignecommande.qte_vendu,lignecommande.prix_vente,lignecommande.total from vente JOIN client ON vente.id_client = client.id_client JOIN lignecommande ON lignecommande.id_ligne = vente.is_lignecommande JOIN produit ON lignecommande.id_produit = produit.id_produit");
		 try {
			pst=connection.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()) {
                Vente v = new Vente();
                LigneCommande ln = new LigneCommande();
                Client client = new Client();
                
                v.setDate(rs.getDate("date_vente").toLocalDate());
                ln.setPrixVente(rs.getDouble("prix_vente"));
                ln.setQteVendu(rs.getInt("qte_vendu"));
                client.setNom(rs.getString("nom_client"));
                client.setPrenom(rs.getString("prenom_client"));
                
                list.add(client);
			}
			return list;
		 } catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }
    public List<LigneCommande> getAllLigne() {
        List<LigneCommande> list=new ArrayList<LigneCommande>();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sql=("Select client.nom_client,Client.prenom_client, vente.date_vente, vente.id_client,lignecommande.qte_vendu,lignecommande.prix_vente,lignecommande.total from vente JOIN client ON vente.id_client = client.id_client JOIN lignecommande ON lignecommande.id_ligne = vente.is_lignecommande");
		 try {
			pst=connection.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()) {
                Vente v = new Vente();
                LigneCommande ln = new LigneCommande();
                Client client = new Client();
                
                v.setDate(rs.getDate("date_vente").toLocalDate());
                ln.setPrixVente(rs.getDouble("prix_vente"));
                ln.setQteVendu(rs.getInt("qte_vendu"));
                client.setNom(rs.getString("nom_client"));
                client.setPrenom(rs.getString("prenom_client"));
                
                list.add(ln);
                
			}
			return list;
		 } catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public List<Vente> getVente() {
        List<Vente> list=new ArrayList<Vente>();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String sql=("Select client.nom_client,Client.prenom_client, vente.date_vente, vente.id_client,lignecommande.qte_vendu,lignecommande.prix_vente,lignecommande.total from vente JOIN client ON vente.id_client = client.id_client JOIN lignecommande ON lignecommande.id_ligne = vente.is_lignecommande");
		 try {
			pst=connection.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()) {
                Vente v = new Vente();
                LigneCommande ln = new LigneCommande();
                Client client = new Client();
                
                v.setDate(rs.getDate("date_vente").toLocalDate());
                ln.setPrixVente(rs.getDouble("prix_vente"));
                ln.setQteVendu(rs.getInt("qte_vendu"));
                client.setNom(rs.getString("nom_client"));
                client.setPrenom(rs.getString("prenom_client"));
                
                //list.add(ln);
                
			}
			return list;
		 } catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }

    @Override
    public void delete(long id) {
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        PreparedStatement pst3 = null;
        String sql="DELETE  FROM vente WHERE id_vente=?";
        String sql3="DELETE lignecommande FROM lignecommande JOIN vente on vente.id_ligneCommande = lignecommande.id_ligne WHERE vente.id_vente = ?";
        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Supprimer");
            alert.setContentText("Voulez-vous supprimer ce Produit?");
            Optional<ButtonType> result = alert.showAndWait();
            if(!result.isPresent() || result.get() != ButtonType.OK) {
                return;
            } else {
            
            System.out.println("success");
            String sql2 ="UPDATE produit JOIN lignecommande ON lignecommande.id_produit = produit.id_produit JOIN vente ON vente.id_ligneCommande=lignecommande.id_ligne SET produit.qte_produit=produit.qte_produit+lignecommande.qte_vendu WHERE vente.id_vente = ?";
            pst2 = connection.prepareStatement(sql2);
            pst2.setLong(1, id);
            pst2.executeUpdate();
            pst = connection.prepareStatement(sql);
            pst.setLong(1, id);
            pst.executeUpdate();
            
            pst3 = connection.prepareStatement(sql3);
            pst3.setLong(1, id);
            pst3.executeUpdate();
            }
        } catch (SQLException exp) {
            System.out.println(exp.getMessage());
        }

    }
    public void updatePrixVente(double prixVente,long id){
        PreparedStatement pst = null;
        int r;
        String sql="UPDATE lignecommande JOIN vente ON vente.id_ligneCommande = lignecommande.id_ligne SET prix_vente = ? WHERE vente.id_vente = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setLong(2, id);
            pst.setDouble(1, prixVente);
            System.out.println("Update prix Success");
            r=pst.executeUpdate();
            System.out.println(r + "prix");
        } catch (SQLException exp) {
            //TODO: handle exception
            System.out.println(exp.getMessage());

        }
    }

	public void updateQteVendu(Integer qte, long id) {
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        PreparedStatement pst3 = null;
        ResultSet rs = null;
        
        try {
            String sql="UPDATE lignecommande JOIN vente ON vente.id_ligneCommande = lignecommande.id_ligne SET lignecommande.qte_vendu = ? WHERE vente.id_vente = ?";
            String sql2 ="SELECT produit.qte_produit from produit join lignecommande on lignecommande.id_produit = produit.id_produit join vente on vente.id_ligneCommande = lignecommande.id_ligne WHERE vente.id_vente = ?";
            String sql3 = "UPDATE produit JOIN lignecommande ON lignecommande.id_produit = produit.id_produit JOIN vente ON vente.id_ligneCommande = lignecommande.id_ligne SET produit.qte_produit = produit.qte_produit+(lignecommande.qte_vendu - ?) WHERE vente.id_vente = ?";
            pst2 = connection.prepareStatement(sql2);
            pst2.setLong(1, id);
            rs=pst2.executeQuery();
            while(rs.next()) {
            if( rs.getInt("produit.qte_produit")>= qte ){
                pst3 = connection.prepareStatement(sql3);
                pst3.setInt(1, qte);
                pst3.setLong(2, id);
                pst3.executeUpdate();
                pst = connection.prepareStatement(sql);
                pst.setLong(2, id);
                pst.setInt(1, qte);
                pst.executeUpdate();
                
                System.out.println("updated");
                
            }
            if (rs.getInt("produit.qte_produit")< qte ) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Quantité insuportable");
                alert.setHeaderText(null);
                alert.setContentText("la quantité souhaité dépasse celle en stock!");
                alert.showAndWait();
            }}
            
        } catch (SQLException exp) {
            //TODO: handle exception
            System.out.println(exp.getMessage());

        }
	}
    

    
}
