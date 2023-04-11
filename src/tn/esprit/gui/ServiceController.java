/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import tn.esprit.entities.Services;
import tn.esprit.services.GestionService;

/**
 * FXML Controller class
 *
 * @author hamdi
 */
public class ServiceController implements Initializable {

   
    @FXML
    private TextField txttype;
    @FXML
    private TextField txtdescription;
    @FXML
    private TextField txtchef;
    @FXML
    private TextField txtprix;
    @FXML
    private Button txtajouter;
@FXML
    private TableView list;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addService(ActionEvent event) {
        try {
          
            String description=txtdescription.getText();
            String type = txttype.getText();
            String chef_service = txtchef.getText();
            Double prix = Double.valueOf(txtprix.getText());
            
            
            Services s = new Services(  type,  description, chef_service, prix);
            GestionService serve = new GestionService();
            serve.ajouter(s);
            FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficherService.fxml"));
            Parent root = loader.load();
             
            AfficherServiceController ac = loader.getController();
            
            ac.setTctchef(chef_service);
            ac.setTxtdescription(description);
            ac.setTxttype(type);
     
            ac.setTxtprix(prix); // add this line to set the prix
            
            ac.setList(list);
               
                    
            
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
