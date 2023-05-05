/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Materiel;

import Materiel.entities.Materiel;
import Materiel.entities.Fournisseur;
import Services.Materiel.MaterielService;

//import com.twilio.rest.lookups.v1.PhoneNumber;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import util.dbconnection;

/**
 *
 * @author fadi1
 */
public class AddMaterielController implements Initializable {
     public static final String ACCOUNT_SID = "ACf491e8b69c540e8680a9ce7e54a18d94";
    public static final String AUTH_TOKEN = "31c2b47957386a90396c6636d3c6676a";
    
    @FXML
    private Label label;
     @FXML
    private TextField tfEventAddNom;
  
    @FXML
    private TextField tfEventAddLieu;
    
    @FXML
    private TextField tfEventAddNbP;
    @FXML
    private Button EventAddButton;
    
    @FXML
    private Button showEventsBT;
    @FXML
    private Button eventAddChoose;
    private File file; 
    private String lien="";
    @FXML
    private ComboBox<Fournisseur> cbEventAdd;
    @FXML
    private Button EventAddCancel;
      private int uid=4;
      
        Connection cnx = dbconnection.getInstance().getConnection();
      @Override
    public void initialize(URL url, ResourceBundle rb) {
     
     
       List<Fournisseur> fournisseurs = new ArrayList<>();
        String s = "select * from fournisseur";
         try {
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Fournisseur e = new Fournisseur();
            e.setNom(rs.getString("nom"));
           
            e.setId(rs.getInt("id"));
            fournisseurs.add(e);
            
        }
         } catch (SQLException ex) {
        Logger.getLogger(AddMaterielController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         cbEventAdd.setItems(FXCollections.observableArrayList(fournisseurs));
cbEventAdd.setConverter(new StringConverter<Fournisseur>() {
    @Override
    public String toString(Fournisseur fournisseur) {
        return fournisseur.getNom();
    }
    @Override
    public Fournisseur fromString(String nom) {
        return null; // Ne pas utiliser pour la conversion vers une chaîne de caractères
    }
        });


    }  
      
   

    @FXML
    private void AjouterMateriel(ActionEvent event) throws IOException {
        
        if(ValidateEmptyForm(tfEventAddNom,tfEventAddLieu,tfEventAddNbP)
             && ValidateName(tfEventAddNom))
        {
            int fournisseur =cbEventAdd.getSelectionModel().getSelectedItem().getId();
        
            MaterielService se = new MaterielService();
            
           Materiel p = new Materiel();
            p.setLibelle(tfEventAddNom.getText());
            p.setType(tfEventAddLieu.getText());
           p.setPrix(Integer.parseInt(tfEventAddNbP.getText()));
                 p.setFournisseur_id(fournisseur);
                System.out.println(cbEventAdd);
   
            se.ajouter(p);
            JOptionPane.showMessageDialog(null, "Materiel ajouté !");
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageMateriel.fxml"));
        AddMaterielController aec = loader.getController();
        Parent root = loader.load();
        EventAddButton.getScene().setRoot(root);
            
          
           
    
    }
    }
   @FXML

    public void showMateriel (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageMateriel.fxml"));
        AddMaterielController aec = loader.getController();
        Parent root = loader.load();
        showEventsBT.getScene().setRoot(root);
    }
        
    
    
    
    private boolean ValidateEmptyForm(TextField nom, TextField lieu, TextField nbp){
         if (nom.getText().equals("")  || lieu.getText().equals("") || 
                nbp.getText().equals("") ) {
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
             alert.setContentText(t.getText()+" : nom non valide");
             alert.showAndWait();
             
             return false;
         }
     }
    
  
    
    
      
    
}
    
