/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Event;

import Event.entities.Event;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Services.Event.EventCRUD;
import integration.Integration;

/**
 * FXML Controller class
 *
 * @author tasnim
 */
public class FrontEventController implements Initializable {

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
 private ObservableList<Event> observableList;
    


     ObservableList<Event> obl = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Event, Button> colButton;
    @FXML
    private Button menu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      getListCmd();
    }    
    
    
    
        
public void getListCmd() {
    EventCRUD cmd = new EventCRUD();
    colId.setCellValueFactory(new PropertyValueFactory<>("id"));
    colClient.setCellValueFactory(new PropertyValueFactory<>("nom"));
    colNumCmd.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
    colMntCmd.setCellValueFactory(new PropertyValueFactory<>("date_fin"));

    // Button cell for like/unlike
colButton.setCellFactory(column -> {
    return new TableCell<Event, Button>() {
        final Button likeButton = new Button("Like");
        final Button unlikeButton = new Button("Unlike");

        {
            likeButton.setOnAction(event -> {
                Event eventObj = getTableView().getItems().get(getIndex());
                eventObj.setLiked(true);
                cmd.modifierLikes(eventObj); // Update the event in the database
                setGraphic(unlikeButton);
            });

            unlikeButton.setOnAction(event -> {
                Event eventObj = getTableView().getItems().get(getIndex());
                eventObj.setLiked(false);
                cmd.modifierLikes(eventObj); // Update the event in the database
                setGraphic(likeButton);
            });
        }

@Override
protected void updateItem(Button item, boolean empty) {
    super.updateItem(item, empty);
    if (empty) {
        setGraphic(null);
        setText(null);
    } else {
        Event eventObj = getTableView().getItems().get(getIndex());
        if (eventObj.isLiked()) {
            unlikeButton.setOnAction(event -> {
                eventObj.setLiked(false);
                cmd.modifierLikes(eventObj); // Update the event in the database
                setGraphic(likeButton);
            });
            setGraphic(unlikeButton);
        } else {
            likeButton.setOnAction(event -> {
                eventObj.setLiked(true);
                cmd.modifierLikes(eventObj); // Update the event in the database
                setGraphic(unlikeButton);
            });
            setGraphic(likeButton);
        }
        setText(null);
        
        // Set default state based on boolean value in the database
        if (eventObj.isLiked()) {
            setGraphic(unlikeButton);
        } else {
            setGraphic(likeButton);
        }
    }
}


    };
});



    // Display row data
    List<Event> listEvents = cmd.consulterEvents();
    observableList = FXCollections.observableList(listEvents);
    table.setItems(observableList);
}

    @FXML
    private void getDetailCmd(MouseEvent event) {
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
    private void DonTable(ActionEvent event)throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontDon.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.setScene(scene);
       stage.show();
    }

    @FXML
    private void calendareve(ActionEvent event) throws IOException {
        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Calendar.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.setScene(scene);
       stage.show();
    }
      @FXML
    private void gotoback(ActionEvent event) throws IOException {
        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEvent.fxml"));
    
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.setScene(scene);
       stage.show();
        
       
    }

    @FXML
    private void menu(ActionEvent event) throws IOException {
//        Integration m = new Integration() ;
      //   m.changeScene("/gui/User/Front.fxml");
    }

}
