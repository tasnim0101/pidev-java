/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import service.EventCRUD;

/**
 * FXML Controller class
 *
 * @author aminh
 */
public class AjouterEventController implements Initializable {

    @FXML
    private TextField txnom;
    @FXML
    private TextField txdated;
    @FXML
    private TextField txtdatef;
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

@FXML
private void ajouter(ActionEvent event) throws IOException {
    if (txnom.getText().isEmpty() || txdated.getText().isEmpty() || txtdatef.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Vous devez remplir tous les champs!");
    } else {
        try {
            // Attempt to parse the txdated field as a date
            Date date = Date.valueOf(txdated.getText());
            
            // If parsing succeeds, create and add the event
            EventCRUD sec = new EventCRUD();
            Event se = new Event(txnom.getText(), date, Date.valueOf(txtdatef.getText()));
            sec.AjouterEvent(se);
            JOptionPane.showMessageDialog(null, "Event ajouté!");
        } catch (IllegalArgumentException e) {
            // If parsing fails, display an error message to the user
            JOptionPane.showMessageDialog(null, "La date doit être au format YYYY-MM-DD!");
        }
    }
}

    
}
