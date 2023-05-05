/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;

import User.entities.User;
import Services.User.ServiceUser;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import integration.Integration;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import util.SessionManager;

/**
 *
 * @author Souid
 */
public class loginController {

    @FXML
    private Label label;
    @FXML
    private Button signupBtn;
    @FXML
    private Button ResetPassword;
    
    
    public loginController(){
        
    }
    
    private Button exitBtn;
   
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    
    @FXML 
    private Button login_button ;
    private SessionManager session;
    
    
            
public void exit(ActionEvent event)throws IOException{
exitBtn.setOnAction(e -> Platform.exit());
} 
  
    
    @FXML
    public void userLogin(ActionEvent event) throws IOException, SQLException, InterruptedException{
        checkLogin();
    }
    @FXML
     public void userSignUp(ActionEvent event) throws IOException{
        checkSignUp();
    }
private void checkLogin() throws IOException, SQLException, InterruptedException {
    Integration m = new Integration();
    User user = ServiceUser.getInstance().searchUserByEmail(usernameField.getText(),passwordField.getText());
    if(user != null){
        integration.Integration.user=user ;
        System.out.println("User found: " + user.getEmail() + ", blocked: " + user.getBlocked());
        session.setId(user.getId());
       
        if(user.getBlocked() != null && user.getBlocked()) {
            m.changeScene("/GUI/User/UserBlocked.fxml");
        } else if("ROLE_USER".equals(user.getRoles())) {
            m.changeScene("/GUI/clienttt.fxml");
        } else if("ROLE_ADMIN".equals(user.getRoles())) {
            m.changeScene("/GUI/back.fxml");
        }
    } else {
        // if the user is not found, display an alert message and disable the login_button for 20 seconds
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Failed");
        alert.setHeaderText("Invalid email or password!");
        alert.setContentText("Please check your email and password and try again.");
        alert.showAndWait();

        login_button.setDisable(true);
        Thread.sleep(10000);
        login_button.setDisable(false);
    }
}




        
        
    
    private void checkSignUp() throws IOException{
        
            Integration m = new Integration();
             m.changeScene("/GUI/User/captcha.fxml");
           
    }
    @FXML
    private void ResetPassword() throws IOException{
        
            Integration m = new Integration();
             m.changeScene("/GUI/User/ResetPassword.fxml");
           
    }
    
    
    
    
    

}

