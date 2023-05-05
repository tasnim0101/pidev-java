/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;

import User.entities.User;
import Services.User.ServiceUser;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.PauseTransition;
import integration.Integration;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Souid
 */
public class SignUpController implements Initializable {
 public String imagePath="";
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField mot_de_passe;
    @FXML
    private PasswordField confirmerMotDePasse;

    /**
     * Initializes the controller class.
     */
     @FXML 
     private Button SignUp ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void addUser(ActionEvent event) throws IOException, SQLException {
        ServiceUser s= new ServiceUser();
         if(ValidateEmptyForm(username,confirmerMotDePasse,mot_de_passe,email)
            && ValidateName(username) && ValidateEmail(email) && ValidateMdp(mot_de_passe))
        {
            
            if (s.isEmailRegistered(email.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cet email est déjà utilisé.", ButtonType.CLOSE);
            alert.show();
            return;
        }
            
                    User user = new User(email.getText(), mot_de_passe.getText(), username.getText());
                    

                    
                    ServiceUser.getInstance().addUser(user);
                     Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("User Ajouter");
             alert.setHeaderText(null);
             alert.setContentText("User Ajouter");
             alert.showAndWait();
             
             FXMLLoader LOADER = new FXMLLoader(getClass().getResource("login.fxml"));
                try {
                    Parent root = LOADER.load();
                    Scene sc = new Scene(root);
                    loginController cntr = LOADER.getController();
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    window.setScene(sc);
                    window.show();
                } catch (IOException ex) {

                }

                   

                 }}
    @FXML
    private void login()throws IOException {
        Integration m = new Integration() ;
         m.changeScene("/gui/User/login.fxml");
        
    }
    
    
      private boolean ValidateEmptyForm(TextField username,TextField email,TextField confirmerMotDePasse, TextField mot_de_passe){
         if (username.getText().equals("")  ||  confirmerMotDePasse.getText().equals("") || mot_de_passe.getText().equals("") || 
                 email.getText().equals("") ){
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
             alert.setContentText(t.getText()+" : username non valide , votre username doit contenir seulement que des lettres");
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
       

   
   
}
       
      
  

