/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.User;

import Services.User.ServiceReclamation;
import User.entities.Reclamation;
import User.entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import integration.Integration;
import util.SessionManager;

/**
 * FXML Controller class
 *
 * @author Asus store
 */
public class ReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> rec_table;
    @FXML
    private TableColumn<Reclamation, String> rec_col;
    @FXML
    private TableColumn<Reclamation, String> email_col;
    ServiceReclamation ps = new ServiceReclamation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Reclamation> personnes = ps.getReclamationList();
            ObservableList<Reclamation> olp = FXCollections.observableArrayList(personnes);
             
            System.out.println("elli connecta "+ SessionManager.getId());
            
          rec_col.setCellValueFactory(new PropertyValueFactory("message"));
           // email_col.setCellValueFactory(new PropertyValueFactory("users_id"));
        /*    email_col.setCellValueFactory(cellData -> {
            User user =cellData.getValue().getUser();
            return new SimpleIntegerProperty(user.getId()).asObject();
          
            }); */
        
        email_col.setCellValueFactory(cellData -> {
    User user = cellData.getValue().getUser();
    return new SimpleStringProperty(user.getEmail());
});
            
             rec_table.setItems(olp);
    }    

    @FXML
    private void back(ActionEvent event) throws IOException {
        Integration m = new Integration() ;
         m.changeScene("/GUI/back.fxml");
   
    }
}
