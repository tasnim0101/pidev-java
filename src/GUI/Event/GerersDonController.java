/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Event;

import Event.entities.Don;
import  Event.entities.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import Services.Event.DonCRUD;
import Services.Event.EventCRUD;


import util.dbconnection;

/**
 * FXML Controller class
 *
 * @author tasnim
 */
public class GerersDonController implements Initializable {


    @FXML
    private TextField nomdo;
    @FXML
    private TextField montantdo;
    @FXML
    private ChoiceBox<String> eventdo;
            private ObservableList<String> eventNamesList;
      
         private Don selectedDon;
          private Connection cnx;
          String query;
          
          
    private Don don;
    
     public GerersDonController() {
        cnx = dbconnection.getInstance().getConnection();
    }


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // Initialize the eventNamesList with the event names from the event table
        eventNamesList = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery("SELECT event.nom FROM event");
            while (res.next()) {
                eventNamesList.add(res.getString("nom"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        // Set the eventd ChoiceBox items to the eventNamesList
        eventdo.setItems(eventNamesList);
    }    
  
  
    


    @FXML
    private void deleteDon(ActionEvent event) throws IOException , ParseException {
           // Show confirmation dialog
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to delete this don?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() != ButtonType.OK) {
        return;
    }

    // Delete the selected event from the database
    DonCRUD.deleteDon(selectedDon.getId());
    }
    
    
    
     public void setSelectedDon(Don selectedDon) {
        this.selectedDon = selectedDon;
        
        nomdo.setText(selectedDon.getNom());
        montantdo.setText(Float.toString(selectedDon.getMontant()));

       
    }
    
    
    
    

@FXML
private void updateDon(ActionEvent event) {
    String txtnom = nomdo.getText();
    String montantStr = montantdo.getText();
    float montant = Float.parseFloat(montantStr);
    String eventName = eventdo.getValue();
 
    Event selectedEvent = EventCRUD.FindEventByName(eventName);
    Don e = new Don();
    e.setId(selectedDon.getId());
    e.setNom(txtnom);
    e.setMontant(montant);
    e.setEvents(selectedEvent);	
    DonCRUD.modifierDon(e);

    // Clear the text fields and reset the choice box selection
    nomdo.clear();
    montantdo.clear();
    eventdo.getSelectionModel().clearSelection();
}


    
}
