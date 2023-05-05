/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Materiel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.List;
import Materiel.entities.Materiel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.dbconnection;

public class CardViewController implements Initializable {

    @FXML
    private ImageView img;

    @FXML
    private Label EventName;

    @FXML
    private Label Adresse;

    @FXML
    private Label ShowCategory;

    @FXML
    private Label likeLabel;

    @FXML
    private Button likeButton;

    @FXML
    private Button dislikeButton;

    private Materiel materiel;

    private int likeCount;

    private int dislikeCount;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Materiel event) throws SQLException {
        materiel = event;
        EventName.setText(event.getLibelle());
        Adresse.setText(event.getType());

        if (event.getFournisseur_id() == 0) {
            ShowCategory.setText("fournisseur non définie");
            System.out.println("err");
            return;
        }

        // Exécution de la requête SQL pour récupérer le nom de catégorie
        Connection cnx = dbconnection.getInstance().getConnection();
        PreparedStatement ps = cnx.prepareStatement("SELECT nom FROM fournisseur WHERE id = ?");
        ps.setInt(1, event.getFournisseur_id());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String nomCategorie = rs.getString(1);
            ShowCategory.setText(nomCategorie);
        } else {
            ShowCategory.setText("Fournisseur introuvable");
        }

        /*likeCount = event.getLikes();
        dislikeCount = event.getDislikes();
        likeLabel.setText("Likes: " + likeCount);*/
    }

    @FXML
    void handleDislike(ActionEvent event) {
        dislikeCount++;
        dislikeButton.setDisable(true);
        likeButton.setDisable(false);
        updateCountsInDB();
         System.out.println(dislikeCount +"dislike");
    }

    @FXML
    void handleLike(ActionEvent event) {
        likeCount++;
        likeButton.setDisable(true);
        dislikeButton.setDisable(false);
        updateCountsInDB();
        System.out.println(likeCount +"like");
       
    }

    private void updateCountsInDB() {
        try {
             Connection cnx = dbconnection.getInstance().getConnection();
            PreparedStatement ps = cnx.prepareStatement(
                    "UPDATE materiel SET likes = ?, dislikes = ? WHERE id = ?");
            ps.setInt(1, likeCount);
            ps.setInt(2, dislikeCount);
            ps.setInt(3, materiel.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
   /*@FXML
private void refrech(ActionEvent event) {
    Materiel cmd = new Materiel();
    List<Materiel> listEvents = cmd.consulterEvents();

    // Update the table view with the new data
    table.getItems().clear();
    table.getItems().addAll(listEvents);
}*/

}

