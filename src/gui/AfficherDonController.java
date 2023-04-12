/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entite.Don;
import java.io.IOException;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.DonCRUD;

/**
 * FXML Controller class
 *
 * @author aminh
 */
public class AfficherDonController implements Initializable {

    @FXML
    private TableView<Don> table;
    @FXML
    private TableColumn<Don, String> colId;
    @FXML
    private TableColumn<Don, String> colClient;
    @FXML
    private TableColumn<Don, String> colNumCmd;
    @FXML
    private TableColumn<Don, String> colMntCmd;
    
        ObservableList<Don> obl = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getListDon();
        // TODO
    }    
    
            public void getListDon() {
        DonCRUD cmd = new DonCRUD();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colNumCmd.setCellValueFactory(new PropertyValueFactory<>("nom"));
                  colMntCmd.setCellValueFactory(new PropertyValueFactory<>("montant"));
        colClient.setCellValueFactory(new PropertyValueFactory<>("events"));
    
  
    
        
        // Display row data
        List<Don> listDon = cmd.consulterDon();
        ObservableList<Don> observableList = FXCollections.observableList(listDon);
         table.setItems(observableList);
    }
    
    

    @FXML
    private void getDetailCmd(MouseEvent event) {
    }

    @FXML
    private void refrechdon(ActionEvent event) {
           DonCRUD cmd = new DonCRUD();
    List<Don> listDon = cmd.consulterDon();

    // Update the table view with the new data
    table.getItems().clear();
    table.getItems().addAll(listDon);
    }

    @FXML
    private void ajouterdon(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterDon.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage popupStage = new Stage();
       popupStage.initModality(Modality.WINDOW_MODAL);
       popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());
       popupStage.setScene(scene);
       popupStage.show();
    }

    @FXML
    private void modifierdon(ActionEvent event) throws IOException {
        
          FXMLLoader loader = new FXMLLoader(getClass().getResource("GererDon.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage popupStage = new Stage();
       popupStage.initModality(Modality.WINDOW_MODAL);
       popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());
       popupStage.setScene(scene);
       popupStage.show();
    }

    @FXML
    private void eventtable(ActionEvent event) throws IOException {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEvent.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.setScene(scene);
       stage.show();
    }
   
}
