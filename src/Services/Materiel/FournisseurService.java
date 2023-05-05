/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Materiel;

import Materiel.entities.Fournisseur;
import Materiel.entities.Materiel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.dbconnection;



/**
 *
 * @author sarah
 */
public class FournisseurService implements IServiceFournisseur<Fournisseur> {
Connection cnx = dbconnection.getInstance().getConnection();
    @Override
    public void ajouter(Fournisseur t) {
         String qry ="INSERT INTO `fournisseur`(`nom`) VALUES ('"+t.getNom()+"')";
        try {
            Statement stm = cnx.createStatement();
       
        stm.executeUpdate(qry);
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }
    }

    @Override
    public List<Fournisseur> afficher() throws SQLException {
        List<Fournisseur> categorie = new ArrayList<>();
        String s = "select * from fournisseur";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Fournisseur e = new Fournisseur();
            e.setNom(rs.getString("nom"));
            e.setId(rs.getInt("id"));
            categorie.add(e);
            
        }
        return categorie;
    }

    @Override
    public void modifier(Fournisseur t) throws SQLException {
       try {
            String requete = "UPDATE fournisseur SET nom = ? WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, t.getId());
            pst.setString(1, t.getNom());
           
            
            
            pst.executeUpdate();
            System.out.println("fournisseur modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }   
    }

    @Override
    public boolean supprimer(Fournisseur t) throws SQLException {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from fournisseur where id = ? ");
            req.setInt(1, t.getId());
            req.executeUpdate();
            ok = true;
        } catch (SQLException ex) {
            System.out.println("error in delete " + ex);
        }
        return ok;  
    }
 
    
}
