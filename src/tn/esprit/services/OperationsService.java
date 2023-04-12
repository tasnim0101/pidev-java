/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entities.Medecin;
import tn.esprit.entities.Operations;
import tn.esprit.tools.MaConnexion;

/**
 *
 * @author chahr
 */
public class OperationsService implements IOperation {
    
   public OperationsService() {
        cnx = MaConnexion.getInstance().getCnx();
    }
    Connection cnx;
    String sql = "";

 
  @Override
public List<Operations> afficher() {
    List<Operations> operationsList = new ArrayList<>();
    try {
        String sql = "SELECT * FROM operations INNER JOIN medecin ON operations.medecin_id = medecin.id";
        Statement stmt = cnx.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Operations o = new Operations();
            o.setId(rs.getInt("id"));
            o.setDate(rs.getString("date"));
            o.setLieu(rs.getString("lieu"));
            o.setEquipe(rs.getString("equipe"));
            o.setDescription(rs.getString("description"));
            o.setImage(rs.getString("image"));
            Medecin med = new Medecin();
            med.setId(rs.getInt("medecin.id"));
            med.setNom_med(rs.getString("nom_med"));
            o.setM(med);
            operationsList.add(o);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return operationsList;
}


@Override
public void ajouter(Operations o) {
   try {
       Statement stmt = cnx.createStatement();
       String sql = "INSERT INTO operations(date, lieu, equipe, description, image, medecin_id) "
               + "VALUES ('" + o.getDate() + "', '" + o.getLieu() + "', '" + o.getEquipe() + "', '" + o.getDescription() + "', '" + o.getImage() + "', (SELECT id FROM medecin WHERE nom_med = '" + o.getM().getNom_med() + "'))";
       int rows = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);

       if (rows == 1) {
           ResultSet rs = stmt.getGeneratedKeys();
           if (rs.next()) {
               o.setId(rs.getInt(1));
           }
           System.out.println("Opération ajoutée avec succès !");
       } else {
           System.out.println("Erreur lors de l'ajout de l'opération.");
       }
   } catch (SQLException ex) {
       Logger.getLogger(OperationsService.class.getName()).log(Level.SEVERE, null, ex);
   }
}


    @Override
  public void modifier(Operations o)  {
       try {
           String sql = "UPDATE operations SET date=?, lieu=?, equipe=?, description=?, image=? WHERE id=?";
           PreparedStatement pstmt = cnx.prepareStatement(sql);
           pstmt.setString(1, o.getDate());
           pstmt.setString(2, o.getLieu());
           pstmt.setString(3, o.getEquipe());
           pstmt.setString(4, o.getDescription());
           pstmt.setString(5, o.getImage());
           pstmt.setInt(6, o.getId());
           int nbRowsUpdated = pstmt.executeUpdate();
           if (nbRowsUpdated > 0) {
               System.out.println("Opération modifiée avec succès !");
           } else {
               System.out.println("Aucune opération n'a été modifiée.");
           }
       } catch (SQLException ex) {
           Logger.getLogger(OperationsService.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    @Override
    public void supprimer(Operations o)  {
       try {
           sql = "DELETE FROM operations WHERE id=?";
           PreparedStatement pstmt = cnx.prepareStatement(sql);
           pstmt.setInt(1, o.getId());
           int nbRowsDeleted = pstmt.executeUpdate();
           if (nbRowsDeleted > 0) {
               System.out.println("Opération supprimée avec succès !");
           } else {
               System.out.println("Aucune opération n'a été supprimée.");
           }
       } catch (SQLException ex) {
           Logger.getLogger(OperationsService.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

   

 

}
