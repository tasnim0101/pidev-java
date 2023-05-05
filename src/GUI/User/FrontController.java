/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;

import integration.Integration;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author fadi1
 */
public class FrontController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private Button materielFront;
      @FXML
    private Button userFront;
    @FXML
    private Button eventFront;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    @FXML
    private void GoToFrontmateriel(ActionEvent event) throws IOException {
       /* FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageMateriel.fxml"));
        AffichageMaterielController aec = loader.getController();
        Parent root = loader.load();
        materielback.getScene().setRoot(root); */
       Integration m = new Integration() ;
         m.changeScene("/GUI/Materiel/sample.fxml");
        
        
    }
     @FXML
    private void GoToFrontUser(ActionEvent event) throws IOException {
        
        
               Integration m = new Integration() ;
         m.changeScene("/GUI/User/LoggedInClient.fxml");
        
        
    }

    @FXML
    private void GoToFrontEvent(ActionEvent event) throws IOException {
         Integration m = new Integration() ;
         m.changeScene("/GUI/Event/FrontMain.fxml");
    }
    
}
