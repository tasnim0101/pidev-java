/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Materiel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import Services.Materiel.FournisseurService;
import java.util.List;
import Materiel.entities.Materiel;
import Services.Materiel.MaterielService;
import integration.Integration;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author fadi1
 */
public class SampleController implements Initializable {

    @FXML
    private GridPane citiesGrid;
MaterielService s= new MaterielService();
    @FXML
    private Label NbEvents;
    
    @FXML
    private Button BackBT;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            List<Materiel> personnes = s.afficher();
            NbEvents.setText(String.valueOf(personnes.size()));
            int column=0;
            int row = 1;
            
            for (Materiel d :personnes ){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("CardView.fxml"));
               
                    Pane pane = fxmlLoader.load();
               
                CardViewController cardViewController = fxmlLoader.getController();
                cardViewController.setData(d);
                if(column == 3){
                column = 0;
                ++row;
                }
                citiesGrid.add(pane, column++, row);
                GridPane.setMargin(pane,new Insets(20,20,20,20));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    
    
    
   /* @FXML
    private void filter(ActionEvent event) throws SQLException, IOException {
        List<Evenement> personnes = s.afficher();

// sort the list by quantity in descending order
Collections.sort(personnes, new Comparator<Don>() {
    @Override
    public int compare(Materiel d1, Materiel d2) {
        return Date.compare(d2.getDate_debut(), d1.getDate_debut());
    }
});

NbDon.setText(String.valueOf(personnes.size())+" Don Disponible");

// display the sorted Don objects in the CardView
int column = 0;
int row = 1;
for (Don d : personnes) {
    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(getClass().getResource("CardView.fxml"));
    Pane pane = fxmlLoader.load();
    CardViewController cardViewController = fxmlLoader.getController();
    cardViewController.setData(d);
     CardViewController controller = fxmlLoader.getController();
    controller.receiveObject(d);
    if (column == 3) {
        column = 0;
        ++row;
    }
    citiesGrid.add(pane, column++, row);
    GridPane.setMargin(pane,new Insets(20,20,20,20));
}

    }*/
    
    @FXML
    private void GoToBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageMateriel.fxml"));
        AffichageMaterielController aec = loader.getController();
        Parent root = loader.load();
        BackBT.getScene().setRoot(root);
        
        
    }
     @FXML
    private void menufront (ActionEvent event) throws IOException {
       /* FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageMateriel.fxml"));
        AffichageMaterielController aec = loader.getController();
        Parent root = loader.load();
        materielback.getScene().setRoot(root); */

       Integration m = new Integration() ;
         m.changeScene("/gui/User/Front.fxml");
        
        
    }
    
}
