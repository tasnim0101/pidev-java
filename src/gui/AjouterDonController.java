/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Don;
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
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author aminh
 */
public class AjouterDonController implements Initializable {

    @FXML
    private TextField nomd;
    @FXML
    private TextField montantd;
    @FXML
    private ChoiceBox<Integer> eventd;
        private ObservableList<Integer> eventsIdList;
         
          
          
              Connection cnx;
         String query;
       public AjouterDonController() {
         cnx = DataSource.getInstance().getCnx();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Initialize the eventsIdList with the events_id values from the event table
        eventsIdList = FXCollections.observableArrayList();
        try {
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery("SELECT id FROM event");
            while (res.next()) {
                eventsIdList.add(res.getInt("id"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        // Set the eventsIdChoiceBox items to the eventsIdList
        eventd.setItems(eventsIdList);
    }    

    @FXML
    private void ajouter(ActionEvent event) {
           if(nomd.getText().isEmpty() || montantd.getText().isEmpty()  )
        {
            JOptionPane.showMessageDialog(null, "vous devez remplir tous les champs !");
        }
        else {
        
         String nom = nomd.getText();
        String montantStr = montantd.getText();
        int eventsId = eventd.getValue();
        float montant = Float.parseFloat(montantStr);
        
        try {
            PreparedStatement ps = cnx.prepareStatement("INSERT INTO don (events_id, nom, montant) VALUES (?, ?, ?)");
            ps.setInt(1, eventsId);
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
    
}