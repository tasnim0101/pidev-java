/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import User.entities.User;
import util.dbconnection;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;



/**
 *
 * @author fadi1
 */
public class Integration extends Application {
    
    private static Stage stg ;
    public static User user=null ;
     @Override
    public void start(Stage stage) throws Exception{
        stg = stage;
       // primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/User/login.fxml"));
        stage.setTitle("Sauvies");
        stage.setScene(new Scene(root, 1200, 800));
        stage.show();
    }
 public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);        
    }
    
}