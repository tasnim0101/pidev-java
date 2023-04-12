/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Don;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import service.DonCRUD;

import util.DataSource;

/**
 * FXML Controller class
 *
 * @author aminh
 */
public class GerersDonController implements Initializable {

    @FXML
    private TextField idD;
    @FXML
    private TextField nomdo;
    @FXML
    private TextField montantdo;
    @FXML
    private ChoiceBox<Integer> eventdo;
      private ObservableList<Integer> eventsIdList;
      
      
          private Connection cnx;
          String query;
          
          
    private Don don;
    
     public GerersDonController() {
        cnx = DataSource.getInstance().getCnx();
    }


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        eventdo.setItems(eventsIdList);
    }    
  
    @FXML
    private void getDon(ActionEvent event) throws IOException , ParseException {
            String sid = idD.getText();
        int id = Integer.parseInt(sid);
        Don d = DonCRUD.findDonById(id);
        nomdo.setText(d.getNom());
         montantdo.setText(Float.toString(d.getMontant())); 
 
    }
    
       public void setDon(Don don) {
        this.don = don;
        nomdo.setText(don.getNom());
        montantdo.setText(Float.toString(don.getMontant()));
        eventdo.setValue(Integer.parseInt(don.getEvents()));
    }

    @FXML
    private void deleteDon(ActionEvent event) throws IOException , ParseException {
            String sid = idD.getText();
         int id = Integer.parseInt(sid);
         DonCRUD.deleteDon(id);
    }

@FXML
private void updateDon(ActionEvent event) {
    int id = Integer.parseInt(idD.getText());
   String nom = nomdo.getText();
    String montantStr = montantdo.getText();
    int eventsId = eventdo.getValue();
    float montant = Float.parseFloat(montantStr);

    try {
        PreparedStatement ps = cnx.prepareStatement("UPDATE don SET events_id=?, nom=?, montant=? WHERE id=?");
        ps.setInt(1, eventsId);
        ps.setString(2, nom);
        ps.setFloat(3, montant);
        // Here, you'll need to set the value of the ID field of the 'don' table to the ID of the record you want to modify.
        ps.setInt(4,id);
        ps.executeUpdate();
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }

    // Clear the text fields and reset the choice box selection
    nomdo.clear();
    montantdo.clear();
    eventdo.getSelectionModel().clearSelection();
}

    
}
