/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Event;


import Event.entities.Event;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import Services.Event.EventCRUD;

/**
 * FXML Controller class
 *
 * @author tasnim
 */
public class UpdateDeleteEventController implements Initializable {

  
    @FXML
    private TextField nom;

    
     private Event selectedEvent;
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

    private void getEvent(ActionEvent event) throws IOException , ParseException {
        
    
 
    }

@FXML
private void deleteEvent(ActionEvent event) throws IOException {
    // Show confirmation dialog
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to delete this event?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() != ButtonType.OK) {
        return;
    }

    // Delete the selected event from the database
    EventCRUD.deleteEvent(selectedEvent.getId());

  
}


    
    
  public void setSelectedEvent(Event selectedEvent) {
    this.selectedEvent = selectedEvent;
    
    nom.setText(selectedEvent.getNom());
    datedebut.setValue(selectedEvent.getDate_debut().toLocalDate());
    datefin.setValue(selectedEvent.getDate_fin().toLocalDate());
}

    


@FXML
private void updateEvent(ActionEvent event) throws IOException, ParseException {
String txtnom = nom.getText();
LocalDate localDateDebut = datedebut.getValue();
LocalDate localDateFin = datefin.getValue();
if (txtnom.isEmpty() || localDateDebut == null || localDateFin == null) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("Vous devez remplir tous les champs!");
    alert.showAndWait();
    return;
}

Date dateDebut = Date.from(localDateDebut.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
Date dateFin = Date.from(localDateFin.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

// Check if the dates meet the required conditions
LocalDate today = LocalDate.now();
if (localDateDebut.isBefore(today)) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("La date de début doit être supérieure à aujourd'hui!");
    alert.showAndWait();
    return;
} else if (localDateFin.isBefore(localDateDebut)) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText(null);
    alert.setContentText("La date de fin doit être supérieure à la date de début!");
    alert.showAndWait();
    return;
}

Event e = new Event();
e.setId(selectedEvent.getId());
e.setNom(txtnom);
e.setDate_debut(new java.sql.Date(dateDebut.getTime()));
e.setDate_fin(new java.sql.Date(dateFin.getTime()));

EventCRUD.modifierEvent(e);

Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Succès");
alert.setHeaderText(null);
alert.setContentText("Event modifié!");
alert.showAndWait();
}





  
    
    
    
    
    
    
    
    
    
}
