/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import javax.mail.*;
import javax.mail.internet.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import java.awt.Insets;
import java.net.URL;
import java.security.Provider.Service;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import Entity.Hospitalisation;
import Entity.Services;
import Services.GestionHospitalisation;
import Services.GestionService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import com.google.gson.Gson;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author damak
 */
public class Interface_ajout_hospController implements Initializable {
  private static final String ACCOUNT_SID = "AC796e5b95c56e68e79610b55f33711a32";
    private static final String AUTH_TOKEN = "591ddc270822add63bde5128fefdb30e";
    String FROM_NUMBER = "+12706771370";  // your Twilio phone number

// Recipient information
String TO_NUMBER = "+21652298102";  // recipient's phone number
String MESSAGE = "Hello, we have booked your hospitalisation!";
    @FXML
    private TextField txt_id_hosp;
    @FXML
    private DatePicker date_entre;
    @FXML
    private DatePicker date_sortie;
    @FXML
    private Button add;
    @FXML
    private TableView< Hospitalisation> Thosp;
 
    @FXML
    private TableColumn<Hospitalisation, Date> date_entree;
    @FXML
    private TableColumn<Hospitalisation, Date> date_s;

    @FXML
    private TableColumn<Hospitalisation, Integer> type;
    @FXML
    private TableColumn<Hospitalisation, Integer> id_hospitalisation;
    @FXML
    private Button edit;
    GestionHospitalisation gh = new GestionHospitalisation();

    Hospitalisation h = new Hospitalisation();
    Services s = new Services();
    GestionService gs = new GestionService();
    private ChoiceBox<String> serv_type;
    @FXML
    private ComboBox<Services> id_service;

    /**
     * Initializes the controller class.
     */
   
//------------------------------------INIITIALIZE----------------------------------------------------------------------------------//
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Services> servs = gs.afficher();
        ObservableList<Services> list = FXCollections.observableArrayList(servs);
        id_service.setItems(list);
        id_service.setCellFactory(param -> new ListCell<Services>() {
            @Override
            protected void updateItem(Services item, boolean empty) {
                super.updateItem(item, empty);
 
                if (empty || item == null || item.getType() == null) {
                    setText(null);
                } else {
                    setText(item.getType());
                }
            }
        });

       

        date_s.setCellValueFactory(new PropertyValueFactory<>("date_sortie"));
        date_entree.setCellValueFactory(new PropertyValueFactory<>("date_entree"));
        id_hospitalisation.setCellValueFactory(new PropertyValueFactory<>("id_hospitalisation"));

        type.setCellValueFactory(new PropertyValueFactory<>("S"));

        Thosp.setItems(afficherListeHospitalisation());
        //  txt_type.getItems().addAll("Pediatrie", "Cardiologie", "Neurologie");
        

    }
    
    
//------------------------------------AJOUTER----------------------------------------------------------------------------------//
@FXML
private void btn_ajouter(ActionEvent event) throws SQLException {


    // Check if all fields are filled
    if (txt_id_hosp.getText().isEmpty() || id_service.getSelectionModel().isEmpty() ||
            date_entre.getValue() == null || date_sortie.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("Veuillez remplir tous les champs");
        alert.showAndWait();
        return;
    }

    // Check if id_hospitalisation is an integer
   int id_hospitalisation;
String idString = txt_id_hosp.getText();
// Only allow numbers in txt_id_hosp and limit to 8 digits
idString = idString.replaceAll("[^\\d]", "");
if (idString.length() > 8) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText("ID Hospitalisation doit être composé de 8 chiffres au maximum");
    alert.showAndWait();
    return;
}
// Validate input as integer
try {
    id_hospitalisation = Integer.parseInt(idString);
} catch (NumberFormatException e) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText("ID Hospitalisation doit être un entier");
    alert.showAndWait();
    return;
}

    // Check if date_entree is before date_sortie
    LocalDate dateEntree = date_entre.getValue();
    LocalDate dateSortie = date_sortie.getValue();
    if (dateSortie.isAfter(dateEntree)) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText("La date d'entrée doit être avant la date de sortie");
        alert.showAndWait();
        return;
    }

    // Create a new Hospitalisation object with the user input
    Hospitalisation h = new Hospitalisation();
    h.setDate_entree(Date.valueOf(dateSortie));
    h.setDate_sortie(Date.valueOf(dateEntree));
    h.setId_hospitalisation(id_hospitalisation);
    h.setS(id_service.getSelectionModel().getSelectedItem());

    // Call the ajouter() method to add the new hospitalisation to the database
    GestionHospitalisation gh = new GestionHospitalisation();
    gh.ajouter(h);
                 gh.envoyer("tasnim.hamdi@esprit.tn","Votre hospitalisation a été enregistrée ! ","votre hospitalisation a été bien enregistrer et merci d'avoir choisir notre Clinique");


 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

// Create a message
//Message message = Message.creator(new PhoneNumber(TO_NUMBER), new PhoneNumber(FROM_NUMBER), MESSAGE).create();


    System.out.println("testttt");

    String tel = "+216" + String.valueOf(id_hospitalisation);

       Message message = Message.creator(
                new com.twilio.type.PhoneNumber(tel),
                new com.twilio.type.PhoneNumber(FROM_NUMBER),
                "Hello, we have booked your hospitalisation")
            .create();

        System.out.println(message.getSid());
    
// Check if the message was sent successfully
if (message.getStatus() == Message.Status.FAILED) {
    System.out.println("Message failed to send.");
} else {
    System.out.println("Message sent successfully.");
}



    // Refresh the tableview to show the updated data
    Thosp.refresh();
    Thosp.setItems(afficherListeHospitalisation());
}



//------------------------------------AFFICHER----------------------------------------------------------------------------------//

    public ObservableList<Hospitalisation> afficherListeHospitalisation() {

        List<Hospitalisation> lhosp = gh.afficher();
        ObservableList<Hospitalisation> hopsList = FXCollections.observableArrayList(lhosp);
        return hopsList;
    }
//------------------------------------MODIFIER----------------------------------------------------------------------------------//

@FXML
private void btn_modifier(ActionEvent event) {
    Hospitalisation selectedHosp = Thosp.getSelectionModel().getSelectedItem();
    if (selectedHosp != null) {
        // set the selected hospitalisation's attributes to the corresponding fields
        txt_id_hosp.setText(String.valueOf(selectedHosp.getId_hospitalisation()));
        id_service.getSelectionModel().select(selectedHosp.getS());
        date_entre.setValue(selectedHosp.getDate_entree().toLocalDate());
        date_sortie.setValue(selectedHosp.getDate_sortie().toLocalDate());

        // create a list of attribute options to choose from
        List<String> attributeOptions = Arrays.asList("ID Hospitalisation", "Service", "Date d'entrée", "Date de sortie");

        // create a choice dialog for attribute selection
        ChoiceDialog<String> dialog = new ChoiceDialog<>(attributeOptions.get(0), attributeOptions);
        dialog.setTitle("Modifier un attribut");
        dialog.setHeaderText("Choisir l'attribut à modifier :");
        dialog.setContentText("Attribut : ");

        // show the choice dialog and wait for user input
        Optional<String> result = dialog.showAndWait();

        // if the user has selected an attribute
        if (result.isPresent()) {
            // get the selected attribute
            String selectedAttribute = result.get();

            // create a text dialog for attribute value input
            TextInputDialog valueDialog = new TextInputDialog();
            valueDialog.setTitle("Modifier un attribut");
            valueDialog.setHeaderText("Modifier " + selectedAttribute);
            valueDialog.setContentText(selectedAttribute + " : ");

            // if the selected attribute is a date, open a date picker instead of a text dialog
            if (selectedAttribute.equals("Date d'entrée") || selectedAttribute.equals("Date de sortie")) {
                DatePicker datePicker = new DatePicker();
                valueDialog.getDialogPane().setContent(datePicker);
                valueDialog.getEditor().setEditable(false);
                valueDialog.setResultConverter(dialogButton -> {
                    if (dialogButton == ButtonType.OK) {
                        LocalDate date = datePicker.getValue();
                        return date == null ? "" : date.toString();
                    }
                    return null;
                });
            }

            // show the value dialog and wait for user input
            Optional<String> valueResult = valueDialog.showAndWait();

            // if the user has entered a value
            if (valueResult.isPresent()) {
                // get the entered value
                String newValue = valueResult.get();

                // modify the selected hospitalisation object with the new value
                switch (selectedAttribute) {
                    case "ID Hospitalisation":
                        selectedHosp.setId_hospitalisation(Integer.parseInt(newValue));
                        break;
                    case "Service":
                        selectedHosp.setS(id_service.getSelectionModel().getSelectedItem());
                        break;
                    case "Date d'entrée":
                        selectedHosp.setDate_entree(Date.valueOf(LocalDate.parse(newValue)));
                        break;
                    case "Date de sortie":
                        selectedHosp.setDate_sortie(Date.valueOf(LocalDate.parse(newValue)));
                        break;
                }

                // call the modifier() method to update the database
                GestionHospitalisation gh = new GestionHospitalisation();
                gh.modifier(selectedHosp);

                // refresh the tableview to show the updated data
                Thosp.refresh();
                Thosp.setItems(afficherListeHospitalisation());
            }
        }
    }
}

    @FXML
    private void Retour(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("FrontClient.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Votre Profil");
                stage.show();
    }

    }
  


