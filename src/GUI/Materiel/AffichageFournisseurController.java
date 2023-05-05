/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Materiel;

import Materiel.entities.Fournisseur;
import Materiel.entities.Materiel;
import Services.Materiel.FournisseurService;
import integration.Integration;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author fadi1
 */
public class AffichageFournisseurController implements Initializable {
    
    
     @FXML
    private TableView<Fournisseur> CategorieETv;
    @FXML
    private TableColumn<Fournisseur, String> nomTv;
    @FXML
    private TableColumn<Fournisseur, Button> delete;
    @FXML
    private Button btnCategEAdd;
    @FXML
    private Button EventBt;
     @FXML
    private Button menu;
    
    
    
        FournisseurService ps = new FournisseurService();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
         
             List<Fournisseur> personnes = ps.afficher();
                         ObservableList<Fournisseur> o = FXCollections.observableArrayList(personnes);

        
              nomTv.setCellValueFactory(new PropertyValueFactory("nom"));
              this.deleteC();
               CategorieETv.setItems(o);
              
               } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        
    }    
    
      @FXML
    private void GoToAddCategE(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddFournisseur.fxml"));
        AddMaterielController aec = loader.getController();
        Parent root = loader.load();
        btnCategEAdd.getScene().setRoot(root);
        
        
    }
    @FXML
     private void GoToEvents(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageMateriel.fxml"));
        AffichageMaterielController aec = loader.getController();
        Parent root = loader.load();
        EventBt.getScene().setRoot(root);
        
        
    }
         @FXML
    private void menuback (ActionEvent event) throws IOException {
       /* FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageMateriel.fxml"));
        AffichageMaterielController aec = loader.getController();
        Parent root = loader.load();
        materielback.getScene().setRoot(root); */
       Integration m = new Integration() ;
         m.changeScene("/gui/User/back.fxml");
        
        
    }
    
    
    public void deleteC() {
        delete.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("supprimer");
                        b.setOnAction((event) -> {
                            try {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de suppression");
                            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cet événement ?");
                            

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                if (ps.supprimer(CategorieETv.getItems().get(getIndex()))) {
                                    updateTable();
                                }
                            }
                            
                            } catch (SQLException ex) {
                                System.out.println("erreor:" + ex.getMessage());

                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });

    }
     public void updateTable() throws SQLException {
    ObservableList<Fournisseur> list = FXCollections.observableArrayList(ps.afficher());
    CategorieETv.setItems(list);
}
}
