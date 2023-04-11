/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author hamdi
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root =FXMLLoader.load(getClass().getResource("service.fxml"));
          Parent root2 = FXMLLoader.load(getClass().getResource("AfficherService.fxml"));
Scene scene2 = new Scene(root2);
Stage afficherServiceStage = new Stage();

            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            afficherServiceStage.setTitle("Afficher Service");
afficherServiceStage.setScene(scene2);
            primaryStage.show();
            afficherServiceStage.show();
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
