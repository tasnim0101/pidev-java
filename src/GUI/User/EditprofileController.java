

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;

import User.entities.User;
import integration.Integration;
import Services.User.ServiceUser;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Souid
 */
public class EditprofileController implements Initializable {
String imagePath="";
    @FXML
    private TextField username;
    @FXML
    private TextField email;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
         User user = integration.Integration.user;

        username.setText(user.getUsername());
        email.setText(user.getEmail());
    }    
   
    @FXML
     private void editUserProfile(ActionEvent event) throws IOException, SQLException {
       
           
           
           if(ValidateEmptyForm(username,email)
            && ValidateName(username) && ValidateEmail(email) )
        {
           
            User userx = new User(email.getText(),integration.Integration.user.getPassword(), username.getText());
           System.out.println("***************"+userx);
            
               
                ServiceUser.getInstance().editUserProfile(userx);
                
                
                //  FXMLLoader loader =  new FXMLLoader(getClass().getResource("../Views/Login.fxml"));
            

            
        }
       
                
    }
     
      @FXML
    private void back()throws IOException {
        Integration m = new Integration() ;
         m.changeScene("/GUI/User/LoggedInClient.fxml");
        
    }
    
      private boolean ValidateEmptyForm(TextField username ,TextField email){
         if (username.getText().equals("")  ||
                 email.getText().equals("")  ){
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText("Veuillez remplir tous les champs");
             alert.showAndWait();
             
             return false;  
        } else {
             return true;  
         }
     }
    
    private boolean ValidateName(TextField t){
         Pattern p = Pattern.compile("[a-zA-Z]+");
         Matcher m = p.matcher(t.getText());
         if (m.find() && m.group().equals(t.getText())){
             return true;
             
         }else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText(t.getText()+" : nom non valide , votre nom doit contenir seulement que des lettres");
             alert.showAndWait();
             
             return false;
         }
     }
    
     private boolean ValidateEmail(TextField t){
         Pattern p = Pattern.compile("^(.+)@(.+)$");
         Matcher m = p.matcher(t.getText());
         if (m.find() && m.group().equals(t.getText())){
             return true;
             
         }else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText(t.getText()+" : email non valide , le format standard exemple@exemple.exemple");
             alert.showAndWait();
             
             return false;
         }
     }
    
     private boolean ValidateMdp(TextField t){
         Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
         Matcher m = p.matcher(t.getText());
         if (m.find() && m.group().equals(t.getText())){
             return true;
             
         }else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText(t.getText()+" : password non valide , format standard : \n\r"
                     + "a digit must occur at least once \n\r"
                     + "a lower case letter must occur at least once \n\r"
                     + "an upper case letter must occur at least once \n\r"
                     + "a special character must occur at least once \n\r"
                     + "no whitespace allowed in the entire string \n\r"
                     + "8-16 character password, both inclusive \n\r");
             alert.showAndWait();
             
             return false;
         }
     }
       
       private boolean ValidateNumTel(TextField t){
         Pattern p = Pattern.compile("^\\d{8}$");
         Matcher m = p.matcher(t.getText());
         if (m.find() && m.group().equals(t.getText())){
             return true;
             
         }else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText(t.getText()+" : Numero non valide , format standard :/n/r"
                     + "+216 11 11 11 11");
             alert.showAndWait();
             
             return false;
         }
     }
    
}
