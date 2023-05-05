/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Materiel;

import Materiel.entities.Fournisseur;
import Materiel.entities.Materiel;
import Services.Materiel.FournisseurService;
import Services.Materiel.MaterielService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author fadi1
 */
public class AddFournisseurController implements Initializable {

    @FXML
    private TextField tfCategEAddNom;
    @FXML
    private Button CategEAddButton;
    @FXML
    private Button showCategEBT;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjoutFournisseur(ActionEvent event) throws IOException {
        
         FournisseurService se = new FournisseurService();
                       Fournisseur p = new Fournisseur();

        p.setNom(tfCategEAddNom.getText());
            
            se.ajouter(p);
            JOptionPane.showMessageDialog(null, "Fournisseur ajouté !");
    }
     @FXML
   private void showFournisseur(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageFournisseur.fxml"));
        AddMaterielController aec = loader.getController();
        Parent root = loader.load();
        showCategEBT.getScene().setRoot(root);
       
   }
   
   }

   

    
    

