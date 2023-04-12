/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entite.Event;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import service.EventCRUD;

/**
 * FXML Controller class
 *
 * @author aminh
 */
public class AfficherEventsController implements Initializable {

    @FXML
    private TableView<Event> table;
    @FXML
    private TableColumn<Event, String> colId;
    @FXML
    private TableColumn<Event, String> colClient;
    @FXML
    private TableColumn<Event, String> colNumCmd;
    @FXML
    private TableColumn<Event, String> colMntCmd;
    @FXML
    private Label aNom;
    @FXML
    private Label aDate2;
    @FXML
    private Label lblNom;
    @FXML
    private Label lblDated;
    @FXML
    private Label lblDatef;
    @FXML
    private Label aDate1;
     ObservableList<Event> obl = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        getListCmd();
        aNom.setVisible(false);
        aDate2.setVisible(false);
        lblNom.setVisible(false);
        lblDated.setVisible(false);
        lblDatef.setVisible(false);
        aDate1.setVisible(false);
        
        
        


      
    
    }
    
        public void getListCmd() {
        EventCRUD cmd = new EventCRUD();
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colClient.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colNumCmd.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
        colMntCmd.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
    
        
        // Display row data
        List<Event> listEvents = cmd.consulterEvents();
        ObservableList<Event> observableList = FXCollections.observableList(listEvents);
         table.setItems(observableList);
    }
        
        
           @FXML
    private void getDetailCmd(MouseEvent event) {
        EventCRUD c = new EventCRUD();
        Event l = (Event) table.getSelectionModel().getSelectedItem();

        lblNom.setText(l.getNom());
        lblDated.setText(l.getDate_debut().toString());
        lblDatef.setText(l.getDate_fin().toString());
        aNom.setVisible(true);
        aDate2.setVisible(true);
        aDate1.setVisible(true);
     
        
  

    }

  public void updateDeleteEvent () throws IOException {
   Stage stage = new Stage ();
Parent root  = FXMLLoader.load (getClass().getResource("/UpdateDeleteEvent"));
Scene scene = new Scene (root);
stage.setScene(scene);
stage.setTitle("updated");
stage.show();

  }

@FXML
private void ajouterEvent(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEvent.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage popupStage = new Stage();
       popupStage.initModality(Modality.WINDOW_MODAL);
       popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());
       popupStage.setScene(scene);
       popupStage.show();
}

   @FXML
private void refrech(ActionEvent event) {
    EventCRUD cmd = new EventCRUD();
    List<Event> listEvents = cmd.consulterEvents();

    // Update the table view with the new data
    table.getItems().clear();
    table.getItems().addAll(listEvents);
}


    @FXML
    private void ModifyEvent(ActionEvent event)  throws IOException  {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateDeleteEvent.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage popupStage = new Stage();
       popupStage.initModality(Modality.WINDOW_MODAL);
       popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());
       popupStage.setScene(scene);
       popupStage.show();
    }

    @FXML
    private void DonTable(ActionEvent event)throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherDon.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.setScene(scene);
       stage.show();
    }


  
    
}
