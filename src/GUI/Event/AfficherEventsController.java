/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Event;


import Event.entities.Event;
import java.io.File;
import java.io.FileOutputStream;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Services.Event.EventCRUD;
import integration.Integration;
import java.io.File;
import java.io.FileOutputStream;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;





/**
 * FXML Controller class
 *
 * @author tasnim
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
    
 private ObservableList<Event> observableList;
    


     ObservableList<Event> obl = FXCollections.observableArrayList();
    @FXML
    private Button exportButton;
    @FXML
    private Button menu;
    
    @FXML
    private TableColumn<Event, String> colMntLikes;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        getListCmd();
    
        
               table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                // Get selected item
                Event selectedEvent = table.getSelectionModel().getSelectedItem();
                if (selectedEvent != null) {
                    // Open new window for editing the selected row
                    openEditWindow(selectedEvent);
                }
            }
        });


    }
    
    
private void openEditWindow(Event selectedEvent) {
    try {
        // Load the FXML file for the edit window
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateDeleteEvent.fxml"));
        Parent root = loader.load();

        // Get the controller instance
        UpdateDeleteEventController controller = loader.getController();

        // Call a method on the controller to pass the selected Event
        controller.setSelectedEvent(selectedEvent);

        // Create a new scene and stage for the edit window
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Edit Event");
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}





    
public void getListCmd() {
    EventCRUD cmd = new EventCRUD();
    colId.setCellValueFactory(new PropertyValueFactory<>("id"));
    colClient.setCellValueFactory(new PropertyValueFactory<>("nom"));
    colNumCmd.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
    colMntCmd.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
    colMntLikes.setCellValueFactory(new PropertyValueFactory<>("likes"));

   


    // Display row data
    List<Event> listEvents = cmd.consulterEvents();
    observableList = FXCollections.observableList(listEvents);
    table.setItems(observableList);
}


        
        
           @FXML
    private void getDetailCmd(MouseEvent event) {
   
     
        
  

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
    private void DonTable(ActionEvent event)throws IOException {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherDon.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.setScene(scene);
       stage.show();
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
        
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Stat.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage popupStage = new Stage();
       popupStage.initModality(Modality.WINDOW_MODAL);
       popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());
       popupStage.setScene(scene);
       popupStage.show();
    }

   @FXML
private void exportToExcel(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();

    // Set extension filter for Excel files
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel files (*.xlsx)", "*.xlsx");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show save file dialog
    File file = fileChooser.showSaveDialog(exportButton.getScene().getWindow());

    if (file != null) {
        try {
            // Create new Excel workbook and sheet
           Workbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet("Events");

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Nom");
            headerRow.createCell(2).setCellValue("Date_debut");
            headerRow.createCell(3).setCellValue("Date_fin");

            // Add data rows
            for (int i = 0; i < observableList.size(); i++) {
                Row row = sheet.createRow(i+1);
                row.createCell(0).setCellValue(observableList.get(i).getId());
                row.createCell(1).setCellValue(observableList.get(i).getNom());
                row.createCell(2).setCellValue(observableList.get(i).getDate_debut());
                row.createCell(3).setCellValue(observableList.get(i).getDate_fin());
            }

            // Write to file
            FileOutputStream fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

            // Show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export Successful");
            alert.setHeaderText(null);
            alert.setContentText("Events exported to Excel file.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Export Failed");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while exporting events to Excel file.");
            alert.showAndWait();
        }
    }
}

    private void gotofront(ActionEvent event) throws IOException {
        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontMain.fxml"));
    
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.setScene(scene);
       stage.show();
        
       
    }
    
    private void menu(ActionEvent event) throws IOException {
        
              /*  FXMLLoader loader = new FXMLLoader(getClass().getResource("FrontMain.fxml"));
    
       Parent root = loader.load();
       Scene scene = new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.setScene(scene);
       stage.show();*/
//                Integration m = new Integration() ;
        // m.changeScene("/gui/User/back.fxml");
        
       
    }

    @FXML
    private void menuEvent(ActionEvent event) throws IOException {
         //Integration m = new Integration() ;
        // m.changeScene("/gui/User/back.fxml");
    }
        
   
                  }



