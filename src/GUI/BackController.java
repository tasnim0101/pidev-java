/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maryem
 */
public class BackController implements Initializable {

    @FXML
    private Button analyse;
    @FXML
    private Button service;
    @FXML
    private Button medecin;
    private TabPane affichage;
    @FXML
    private TabPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void analyseButton(MouseEvent event) throws IOException {
        try {
//analyses
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/GUI/addAnalyse.fxml"));
            AnchorPane fileRoot1 = fxmlLoader1.load();
            Tab tab1 = new Tab("Ajouter Analyse");
            tab1.setContent(fileRoot1);

            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/GUI/analyses.fxml"));
            AnchorPane fileRoot2 = fxmlLoader2.load();
            Tab tab2 = new Tab("Consulter Analyse");
            tab2.setContent(fileRoot2);

            //labo
            FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("/GUI/addLabo.fxml"));
            AnchorPane fileRoot3 = fxmlLoader3.load();
            Tab tab3 = new Tab("Ajouter Labo");
            tab3.setContent(fileRoot3);

            FXMLLoader fxmlLoader4 = new FXMLLoader(getClass().getResource("/GUI/labo.fxml"));
            AnchorPane fileRoot4 = fxmlLoader4.load();
            Tab tab4 = new Tab("Consulter Labo");
            tab4.setContent(fileRoot4);

            //rating
            FXMLLoader fxmlLoader5 = new FXMLLoader(getClass().getResource("/GUI/rating.fxml"));
            AnchorPane fileRoot5 = fxmlLoader5.load();

            Tab tab5 = new Tab("Rating");
            tab5.setContent(fileRoot5);

            //stat
            FXMLLoader fxmlLoader6 = new FXMLLoader(getClass().getResource("/GUI/stat.fxml"));
            AnchorPane fileRoot6 = fxmlLoader6.load();

            Tab tab6 = new Tab("Statistiques");
            tab6.setContent(fileRoot6);

            //commentaire 
            FXMLLoader fxmlLoader7 = new FXMLLoader(getClass().getResource("/GUI/comment.fxml"));
            AnchorPane fileRoot7 = fxmlLoader7.load();

            Tab tab7 = new Tab("Commentaires");
            tab7.setContent(fileRoot7);

            //local
            FXMLLoader fxmlLoader8 = new FXMLLoader(getClass().getResource("/GUI/local.fxml"));
            AnchorPane fileRoot8 = fxmlLoader8.load();

            Tab tab8 = new Tab("Localiser");
            tab8.setContent(fileRoot8);

            anchorPane.getTabs().clear();

            anchorPane.getTabs().add(tab1);
            anchorPane.getTabs().add(tab2);
            anchorPane.getTabs().add(tab3);
            anchorPane.getTabs().add(tab4);
            anchorPane.getTabs().add(tab5);
            anchorPane.getTabs().add(tab6);
            anchorPane.getTabs().add(tab7);
            anchorPane.getTabs().add(tab8);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void serviceButton(MouseEvent event) throws IOException {
        try {
//service ajout modifier supprimer
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/GUI/dashboard_admin_tassnim_1.fxml"));
            AnchorPane fileRoot1 = fxmlLoader1.load();
            Tab tab1 = new Tab(" Service");
            tab1.setContent(fileRoot1);
//hospitalisation 
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/GUI/Interface_afficher_hosp.fxml"));
            AnchorPane fileRoot2 = fxmlLoader2.load();
            Tab tab2 = new Tab("Consulter Hospitalisation");
            tab2.setContent(fileRoot2);

            anchorPane.getTabs().clear();

            anchorPane.getTabs().add(tab1);
            anchorPane.getTabs().add(tab2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void medecinButton(MouseEvent event) throws IOException {

        try {
//medecin ajout modifier supprimer
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/GUI/InterfaceajouterMedcin.fxml"));
            AnchorPane fileRoot1 = fxmlLoader1.load();
            Tab tab1 = new Tab(" Medecin");
            tab1.setContent(fileRoot1);
//operation 
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/GUI/InterfaceAjouterOperation.fxml"));
            AnchorPane fileRoot2 = fxmlLoader2.load();
            Tab tab2 = new Tab("Operation");
            tab2.setContent(fileRoot2);

            anchorPane.getTabs().clear();

            anchorPane.getTabs().add(tab1);
            anchorPane.getTabs().add(tab2);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void user(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/GUI/User/LoggedIn.fxml"));
            AnchorPane fileRoot1 = fxmlLoader1.load();
            Tab tab1 = new Tab(" PROFIL");
            tab1.setContent(fileRoot1);
            anchorPane.getTabs().clear();
 anchorPane.getTabs().add(tab1);
    }

    @FXML
    private void materiel(MouseEvent event) {

        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/GUI/Materiel/AddFournisseur.fxml"));
            AnchorPane fileRoot1 = fxmlLoader1.load();
            Tab tab1 = new Tab(" Ajouter Fournisseur");
            tab1.setContent(fileRoot1);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/GUI/Materiel/AddMateriel.fxml"));
            AnchorPane fileRoot2 = fxmlLoader2.load();
            Tab tab2 = new Tab("Ajouter Materiel");
            tab2.setContent(fileRoot2);
            FXMLLoader fxmlLoader3 = new FXMLLoader(getClass().getResource("/GUI/Materiel/affichageMateriel.fxml"));
            AnchorPane fileRoot3 = fxmlLoader3.load();
            Tab tab3 = new Tab("Affichage materiels");
            tab3.setContent(fileRoot3);
            FXMLLoader fxmlLoader4 = new FXMLLoader(getClass().getResource("/GUI/Materiel/AffichageFournisseur.fxml"));
            AnchorPane fileRoot4 = fxmlLoader4.load();
            Tab tab4 = new Tab("Affichage Fournisseurs");
            tab4.setContent(fileRoot4);

            anchorPane.getTabs().clear();

            anchorPane.getTabs().add(tab1);
            anchorPane.getTabs().add(tab2);
           anchorPane.getTabs().add(tab3);
            anchorPane.getTabs().add(tab4);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void event(MouseEvent event) throws IOException {
         try { FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/GUI/Event/AfficherEvent.fxml"));
            AnchorPane fileRoot1 = fxmlLoader1.load();
            Tab tab1 = new Tab(" Afficher event");
            tab1.setContent(fileRoot1);
            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/GUI/Event/AfficherDon.fxml"));
            AnchorPane fileRoot2 = fxmlLoader2.load();
            Tab tab2 = new Tab("Afficher Don");
            tab2.setContent(fileRoot2);
             anchorPane.getTabs().clear();

            anchorPane.getTabs().add(tab1);
            anchorPane.getTabs().add(tab2);
    
  } catch (IOException e) {
            e.printStackTrace();
        }
}
}