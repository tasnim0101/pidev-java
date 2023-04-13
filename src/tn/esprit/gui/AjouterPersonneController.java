/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import tn.esprit.entities.Users;
import tn.esprit.services.UsersService;

/**
 * FXML Controller class
 *
 * @author Asus store
 */
public class AjouterPersonneController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addPerson(ActionEvent event) {
        int age = Integer.valueOf(txtage.getText());
        String nom= txtnom.getText();
        String prenom= txtprenom.getText();
        Users p = new Users(age,nom,prenom);
        UsersService ps = new UsersService();
        try {
            ps.ajouter(p);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherPersonne.fxml"));
            Parent root = loader.load();
            AfficherPersonneController ac = loader.getController();
            ac.setTxtnomA(nom);
            ac.setTxtprenomA(prenom);
            txtage.getScene().setRoot(root);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }
    
}
