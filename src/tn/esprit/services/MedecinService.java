/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;
import tn.esprit.entities.Medecin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entities.Operations;
import tn.esprit.tools.MaConnexion;

/**
 *
 * @author chahr
 */

public class MedecinService implements IMedecin {
        Connection cnx;
    String sql="";
 public MedecinService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
 

public void ajouter(Medecin m) throws SQLException {
    sql = "INSERT INTO medecin(nom_med, prenom_med, tel_med, mail_med, specialite, diplome, photo) VALUES (?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement ste = cnx.prepareStatement(sql);
    ste.setString(1, m.getNom_med());
    ste.setString(2, m.getPrenom_med());
    ste.setInt(3, m.getTel_med());
    ste.setString(4, m.getMail_med());
    ste.setString(5, m.getSpecialite());
    ste.setString(6, m.getDiplome());
    ste.setString(7, m.getPhoto());
    ste.executeUpdate();
      System.out.println("-----");
     System.out.println("Medecin ajouté avec succès");
   
}


 public List<Medecin> afficher() {
    List<Medecin> medecins = new ArrayList<>();
    try {
        sql = "SELECT * FROM medecin";
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery(sql);
        while (rs.next()) {
            Medecin m = new Medecin(
                rs.getInt("id"),
                rs.getString("nom_med"),
                rs.getString("prenom_med"),
                rs.getInt("tel_med"),
                rs.getString("mail_med"),
                rs.getString("specialite"),
                rs.getString("diplome"),
                rs.getString("photo")
            );
            medecins.add(m);          
        }
         System.out.println("-----");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return medecins;
}
   public void modifier(Medecin m) {
    try {
         sql = "UPDATE medecin SET nom_med = ?, prenom_med = ?, tel_med = ?, mail_med = ?, specialite = ?, diplome = ?, photo = ? WHERE id = ?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setString(1, m.getNom_med());
        ste.setString(2, m.getPrenom_med());
        ste.setInt(3, m.getTel_med());
        ste.setString(4, m.getMail_med());
        ste.setString(5, m.getSpecialite());
        ste.setString(6, m.getDiplome());
        ste.setString(7, m.getPhoto());
        ste.setInt(8, m.getId());
        int rowsUpdated = ste.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Le Medecin avec l'ID " + m.getId() + " a été modifié avec succès !");
                    System.out.println("-----");
        } else {
            System.out.println("Aucun Medecin n'a été modifié !");
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la modification du Medecin : " + ex.getMessage());
    }
}


    public void supprimer(Medecin o)  {
       try {
           sql = "DELETE FROM medecin WHERE id=?";
           PreparedStatement pstmt = cnx.prepareStatement(sql);
           pstmt.setInt(1, o.getId());
           int nbRowsDeleted = pstmt.executeUpdate();
           if (nbRowsDeleted > 0) {
               System.out.println("Medecon supprimée avec succès !");
           } else {
               System.out.println("Aucune opération n'a été supprimée.");
           }
       } catch (SQLException ex) {
           Logger.getLogger(OperationsService.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
}

     





}
