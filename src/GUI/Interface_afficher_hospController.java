/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Entity.Hospitalisation;
import Entity.Services;
import Services.GestionHospitalisation;
import Services.GestionService;
import Services.User.ServiceUser;

/**
 * FXML Controller class
 *
 * @author hamdi
 */
public class Interface_afficher_hospController implements Initializable {

    @FXML
    private TableView<Hospitalisation> Thosp;
    @FXML
    private TableColumn<Hospitalisation, Date> date_entree;
    @FXML
    private TableColumn<Hospitalisation, Date> date_sortie;
    @FXML
    private Button RefreshButton;
    @FXML
    private Button delete;
     @FXML
    private TableColumn<Hospitalisation, Integer> user;
    @FXML
    private TableColumn<Hospitalisation, Integer> id_hospitalisation;
    @FXML
    private TableColumn<Hospitalisation, String> type;
     GestionHospitalisation gh = new GestionHospitalisation();

        Hospitalisation h = new Hospitalisation();
        Services s = new Services();
        GestionService gs = new GestionService();
        ServiceUser su = new ServiceUser();
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        type.setCellValueFactory(new PropertyValueFactory<>("S"));
        date_sortie.setCellValueFactory(new PropertyValueFactory<>("date_sortie"));
        date_entree.setCellValueFactory(new PropertyValueFactory<>("date_entree"));
        id_hospitalisation.setCellValueFactory(new PropertyValueFactory<>("id_hospitalisation"));
           user.setCellValueFactory(new PropertyValueFactory<>("u"));
          Thosp.setItems(afficherListeHospitalisation());
    }    
    
     
     public ObservableList<Hospitalisation> afficherListeHospitalisation() {

    List<Hospitalisation> lhosp = gh.afficher();
    ObservableList<Hospitalisation> hopsList = FXCollections.observableArrayList(lhosp);
    return hopsList;
}

    @FXML
    private void delete(ActionEvent event) {
         Hospitalisation selectedhosp= Thosp.getSelectionModel().getSelectedItem();
if (selectedhosp == null) {
    // afficher une alerte d'avertissement
    return;
}

Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
confirmDialog.setTitle("Confirmation");
confirmDialog.setHeaderText(null);
confirmDialog.setContentText("Are you sure you want to delete this Hospitalisation?");
Optional<ButtonType> result = confirmDialog.showAndWait();

if (result.isPresent() && result.get() == ButtonType.OK) {
    GestionHospitalisation gh = new GestionHospitalisation();
    Hospitalisation v = new Hospitalisation();
    v.setId(selectedhosp.getId());
   v.setDate_entree(selectedhosp.getDate_entree());
    v.setDate_sortie(selectedhosp.getDate_sortie());
    v.setS(selectedhosp.getS());
    
    System.out.println(v);
//    serviceVelo.delete2(v);
gh.supprimer(selectedhosp);
    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
    successAlert.setTitle("Success");
    successAlert.setHeaderText(null);
    successAlert.setContentText("Hospitalisation deleted successfully!");
    
    successAlert.showAndWait();

    Thosp.setItems(afficherListeHospitalisation());
}
    }

    private void sendd(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("sms.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Votre Profil");
                stage.show();
    }

    private void retour(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("DashboardAdmin.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Votre Profil");
                stage.show();
    }
}
