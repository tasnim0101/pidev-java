/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Event;

import Event.entities.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import Services.Event.EventCRUD;

/**
 * FXML Controller class
 *
 * @author tasnim
 */
public class AjouterEventController implements Initializable {

    @FXML
    private TextField txnom;
    @FXML
    private Button btn;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


@FXML
private void ajouter(ActionEvent event) throws IOException {
if (txnom.getText().isEmpty() || datedebut.getValue() == null || datefin.getValue() == null) {
Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Erreur");
alert.setHeaderText(null);
alert.setContentText("Vous devez remplir tous les champs!");
alert.showAndWait();
} else {
try {

// Attempt to parse the datedebut and datefin fields as dates
LocalDate localDateDebut = datedebut.getValue();
Date dateDebut = Date.valueOf(localDateDebut);
LocalDate localDateFin = datefin.getValue();
Date dateFin = Date.valueOf(localDateFin);
        // Check if the dates meet the required conditions
        LocalDate today = LocalDate.now();
        if (localDateDebut.isBefore(today)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La date de début doit être supérieure à aujourd'hui!");
            alert.showAndWait();
        } else if (localDateFin.isBefore(localDateDebut)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("La date de fin doit être supérieure à la date de début!");
            alert.showAndWait();
        } else {
            // If the dates meet the required conditions, create and add the event
            EventCRUD sec = new EventCRUD();
            Event se = new Event(txnom.getText(), dateDebut, dateFin);
            sec.AjouterEvent(se);
        
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Event ajouté!");
            alert.showAndWait();
        }
    } catch (DateTimeParseException | IllegalArgumentException e) {
        // If parsing fails, display an error message to the user
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("La date doit être au format YYYY-MM-DD!");
        alert.showAndWait();
    }
}
}



    
}
