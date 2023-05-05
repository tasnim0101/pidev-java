/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import Entity.Medecin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import org.controlsfx.control.Notifications;
import Entity.Operations;
import tools.MaConnexion;

/**
 *
 * @author chahr
 */

public class MedecinService {
        Connection cnx;
    String sql="";
 public MedecinService() {
        cnx=MaConnexion.getInstance().getCnx();
    }
 
    
/* public void showNotification(String title, String message) {
        Notifications notificationsBuilder = Notifications.create()
                .title(title)
                .text(message)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);

        ImageView logoImage = new ImageView(new Image("/GUI/Assets/doctorlogo.png"));
        logoImage.setFitWidth(50);
        logoImage.setPreserveRatio(true);
        notificationsBuilder.graphic(logoImage);

        notificationsBuilder.show();
    }*/
 
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
        Logger.getLogger(MedecinService.class.getName()).log(Level.SEVERE, null, ex);
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
    public List<String> getListeNomMedecins() {
    List<String> nomMedecins = new ArrayList<>();

    try {
     
        String query = "SELECT nom_med FROM medecin";
        PreparedStatement st = cnx.prepareStatement(query);
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            nomMedecins.add(rs.getString("nom_med"));
        }

        st.close();
        rs.close();
        cnx.close();

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return nomMedecins;
}
    
    
    
public List<Medecin> rechercherParNom(String nomMedecin) {
    List<Medecin> medecins = new ArrayList<>();

    try {
        // Create a prepared statement with a parameter for the nom_med value to be searched
        String sql = "SELECT * FROM medecin WHERE nom_med=?";
        PreparedStatement pstmt = cnx.prepareStatement(sql);
        pstmt.setString(1, nomMedecin);

        // Execute the prepared statement and process the result set
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Medecin medecin = new Medecin(rs.getInt("id"), rs.getString("nom_med"), rs.getString("prenom_med"),
                    rs.getInt("tel_med"), rs.getString("mail_med"), rs.getString("specialite"),
                    rs.getString("diplome"), rs.getString("photo"));
            medecins.add(medecin);
        }

    } catch (SQLException ex) {
        System.err.println("Error while searching for Medecin objects by nom_med value: " + ex.getMessage());
    }

    return medecins;
}




}






