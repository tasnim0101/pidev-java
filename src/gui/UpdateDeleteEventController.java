/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Event;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import service.EventCRUD;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author aminh
 */
public class UpdateDeleteEventController implements Initializable {

    @FXML
    private TextField idE;
    @FXML
    private TextField nom;
    @FXML
    private TextField datede;
    @FXML
    private TextField datefi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void getEvent(ActionEvent event) throws IOException , ParseException {
        
        String sid = idE.getText();
        int id = Integer.parseInt(sid);
        Event e = EventCRUD.findEventById(id);
        nom.setText(e.getNom());
        datede.setText(e.getDate_debut().toString());
         datefi.setText(e.getDate_debut().toString());
 
    }

    @FXML
    private void deleteEvent(ActionEvent event)throws IOException {
        String sid = idE.getText();
         int id = Integer.parseInt(sid);
         EventCRUD.deleteEvent(id);
    }

    @FXML
    private void updateEvent(ActionEvent event) throws IOException, ParseException {
           String sid = idE.getText();
             int id = Integer.parseInt(sid);
             String txtnom = nom.getText();
              String txtdated = datede.getText();
               String txtdatef = datefi.getText();
               
                 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date dateDebut = dateFormat.parse(txtdated);
    Date dateFin = dateFormat.parse(txtdatef);
               Event e = new Event();
               e.setId(id);
               e.setNom(txtnom);

               java.sql.Date sqlDateDebut = new java.sql.Date(dateDebut.getTime());
java.sql.Date sqlDateFin = new java.sql.Date(dateFin.getTime());

e.setDate_debut(sqlDateDebut);
e.setDate_fin(sqlDateFin);
                 EventCRUD.modifierEvent(e);
               
               
                  }
    
}
