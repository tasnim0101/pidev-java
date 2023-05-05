/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

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
import javafx.util.Duration;
//import org.controlsfx.control.Notifications;
import Entity.Medecin;
import Entity.Operations;
import tools.MaConnexion;

/**
 *
 * @author chahr
 */
public class OperationsService implements IOperation{
    
   public OperationsService() {
        cnx = MaConnexion.getInstance().getCnx();
    }
    Connection cnx;
    String sql = "";

 
  
public List<Operations> afficher() {
    List<Operations> operationsList = new ArrayList<>();
    try {
        String sql = "SELECT o.id, o.date, o.lieu, o.equipe, o.description, o.image, m.id AS medecin_id, m.nom_med AS nom_med\n" +
                     "FROM operations o\n" +
                     "INNER JOIN medecin m ON o.medecin_id = m.id";
                
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
            med.setId(rs.getInt("medecin_id"));
            med.setNom_med(rs.getString("nom_med"));
            o.setM(med);
            operationsList.add(o);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return operationsList;
}



/*
public void ajouter(Operations o) {
  
       Statement stmt = cnx.createStatement();
       String sql = "INSERT INTO operations(date, lieu, equipe, description, image, medecin_id) "
               + "VALUES ('" + o.getDate() + "', '" + o.getLieu() + "', '" + o.getEquipe() + "', '" + o.getDescription() + "', '" + o.getImage() + "', (SELECT id FROM medecin WHERE nom_med = '" + o.getM().getId() + "'))";
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
   } 
}*/

/* public void showNotification(String title, String message) {
        Notifications notificationsBuilder = Notifications.create()
                .title(title)
                .text(message)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT);

        ImageView logoImage = new ImageView(new Image("/GUI/Assets/operationlogo.jpg"));
        logoImage.setFitWidth(50);
        logoImage.setPreserveRatio(true);
        notificationsBuilder.graphic(logoImage);

        notificationsBuilder.show();
    }
*/

@Override
public void ajouter(Operations o){
     try {
        sql = "insert into operations(date, lieu, equipe, description, image, medecin_id) values (?,?,?,?,?,?)";
        PreparedStatement ste = cnx.prepareStatement(sql);

        ste.setString(1, o.getDate());
        ste.setString(2, o.getLieu());
        ste.setString(3,o.getEquipe());
        ste.setString(4,o.getDescription());
        ste.setString(5, o.getImage());
         ste.setInt(6, o.getM().getId());

        ste.executeUpdate();

        System.out.println("operation Ajoutée !!");
     }
        catch (SQLException ex) {
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
    public Medecin getMedecinByName(String nom) {
    try {
        String query = "SELECT * FROM medecin WHERE nom=?";
        PreparedStatement ps = cnx.prepareStatement(query);
        ps.setString(1, nom);
        ResultSet rs = ps.executeQuery();

        Medecin medecin = null;
        if (rs.next()) {
            medecin = new Medecin(rs.getInt("id"), rs.getString("nom"), rs.getString("specialite"));
        }

        rs.close();
        ps.close();
        cnx.close();

        return medecin;
    } catch (SQLException ex) {
        System.out.println("Error while getting medecin by name: " + ex.getMessage());
        return null;
    }
}


    

 public List<Operations> getListeNomMedecins() {
    List<Operations> operationsList = new ArrayList<>();
    try {
        String sql = "SELECT o.id, o.date, o.lieu, o.equipe, o.description, o.image, m.id AS medecin_id, m.nom_med AS nom_med\n" +
                     "FROM operations o\n" +
                     "INNER JOIN medecin m ON o.medecin_id = m.id";
                
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
            med.setId(rs.getInt("medecin_id"));
            med.setNom_med(rs.getString("nom_med"));
            o.setM(med);
            operationsList.add(o);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return operationsList;
}
public Medecin getMedecinByNom(String nom) {
    // Your database query to retrieve the Medecin object by name
    // Assuming the Medecin object has a field named "nom"
    String query = "SELECT * FROM medecin WHERE nom=?";
    try {
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setString(1, nom);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            Medecin medecin = new Medecin();
            medecin.setId(resultSet.getInt("id"));
            medecin.setNom_med(resultSet.getString("nom_med"));
            // Set other fields of the medecin object as needed
            return medecin;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return null;
}

}
