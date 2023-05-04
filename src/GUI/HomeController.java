/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class HomeController implements Initializable {

    @FXML
    private TabPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

 

}
