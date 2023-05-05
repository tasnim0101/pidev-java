/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Event;

import Event.entities.Don;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import util.dbconnection;

/**
 * FXML Controller class
 *
 * @author tasnim
 */
public class AjouterDonController implements Initializable {

    @FXML
    private TextField nomd;
    @FXML
    private TextField montantd;
    @FXML
    private ChoiceBox<String> eventd;
          private ObservableList<String> eventNamesList;
         
          
          
              Connection cnx;
         String query;
       public AjouterDonController() {
         cnx = dbconnection.getInstance().getConnection();
    }

    /**
     * Initializes the controller class.
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
    eventd.setItems(eventNamesList);

}

   

@FXML
private void ajouter(ActionEvent event) {
if(nomd.getText().isEmpty() || montantd.getText().isEmpty() || eventd.getValue() == null) {
JOptionPane.showMessageDialog(null, "Vous devez remplir tous les champs !");
} else {
String nom = nomd.getText();
String montantStr = montantd.getText();
String eventName = eventd.getValue();
float montant = 0.0f; // initialize montant to 0
        
   try {
        montant = Float.parseFloat(montantStr); // parse montantd input as float
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Le montant doit être un nombre décimal !");
        return; // stop execution if input is not a float
    }
    
    try {
        PreparedStatement ps = cnx.prepareStatement("INSERT INTO don (events_id, nom, montant) VALUES (?, ?, ?)");
        ps.setString(1, getEventId(eventName));
        ps.setString(2, nom);
        ps.setFloat(3, montant);
        ps.executeUpdate();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    
    // Clear the text fields and reset the choice box selection
    nomd.clear();
    montantd.clear();
    eventd.getSelectionModel().clearSelection();
}
}

private String getEventId(String eventName) {
    try {
        PreparedStatement ps = cnx.prepareStatement("SELECT id FROM event WHERE nom = ?");
        ps.setString(1, eventName);
        ResultSet res = ps.executeQuery();
        if (res.next()) {
            return res.getString("id");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return null;
}


    
}