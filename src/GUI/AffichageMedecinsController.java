/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Entity.Medecin;
import Services.MedecinService;

/**
 * FXML Controller class
 *
 * @author damak
 */
public class AffichageMedecinsController implements Initializable {

   @FXML
    private TableView<Medecin> TableMedecin;
    @FXML
    private TableColumn<Medecin, String> col_nom;
    @FXML
    private TableColumn<Medecin, String> col_prenom;
    @FXML
    private TableColumn<Medecin, Integer> col_tel;

    @FXML
    private TableColumn<Medecin, String> col_mail;
    @FXML
    private TableColumn<Medecin, String> col_specialite;
    @FXML
    private TableColumn<Medecin, String> col_dip;
    @FXML
    private TableColumn<Medecin, String> col_photo;
    @FXML
    private Button refreshButton;
Medecin m = new Medecin();
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_med"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_med"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("tel_med"));
        col_mail.setCellValueFactory(new PropertyValueFactory<>("mail_med"));
        col_specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        col_dip.setCellValueFactory(new PropertyValueFactory<>("diplome"));
        col_photo.setCellValueFactory(new PropertyValueFactory<>("photo"));

        TableMedecin.setItems(afficherListMedecins());
        refreshButton.setOnAction(e -> {
      ObservableList<Medecin> MedecinList = afficherListMedecins();

        TableMedecin.setItems(MedecinList);
    });
    }

    private ObservableList<Medecin> afficherListMedecins() {
MedecinService ms=new MedecinService();
        List<Medecin> Medecins = ms.afficher();
      // ms.showNotification("Liste des medecins", "Voici le tableau de tout nos medecins");
        ObservableList<Medecin> MedecinList = FXCollections.observableArrayList(Medecins);
    
        
        return MedecinList;
    }   
    
}
