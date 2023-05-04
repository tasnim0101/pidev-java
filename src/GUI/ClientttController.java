/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maryem
 */
public class ClientttController implements Initializable {

    @FXML
    private Button afficherservice;
    @FXML
    private Button ajouterhosp;
    @FXML
    private AnchorPane affichage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherservice(ActionEvent event) throws IOException { 
               try {
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/GUI/Interface_afficher_service.fxml"));
        Node node = fxmlLoader2.load();
        affichage.getChildren().setAll(node);
    } catch (IOException ex) {
        // handle the exception
    }
        
    }

    @FXML
    private void affichermedecin(ActionEvent event) throws IOException {
                  try {
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/GUI/AffichageMedecins.fxml"));
        Node node = fxmlLoader2.load();
        affichage.getChildren().setAll(node);
    } catch (IOException ex) {
        // handle the exception
    }
    }
    

    @FXML
    private void afficherlabo(ActionEvent event) {
         try {
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/GUI/front.fxml"));
        Node node = fxmlLoader2.load();
        affichage.getChildren().setAll(node);
    } catch (IOException ex) {
        // handle the exception
    }
    }

    @FXML
    private void ajouterhosp(ActionEvent event) throws IOException {
                         try {
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/GUI/Interface_ajout_hosp.fxml"));
        Node node = fxmlLoader2.load();
        affichage.getChildren().setAll(node);
    } catch (IOException ex) {
        // handle the exception
    }
    }

    @FXML
    private void afficheroperation(ActionEvent event) {
         try {
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/GUI/OpFront.fxml"));
        Node node = fxmlLoader2.load();
        affichage.getChildren().setAll(node);
    } catch (IOException ex) {
        // handle the exception
    }
    }
    
    
}
