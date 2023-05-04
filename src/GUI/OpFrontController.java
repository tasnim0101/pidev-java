/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Entity.Operations;
import Services.OperationsService;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author chahr
 */
public class OpFrontController implements Initializable {

  @FXML
    private TableView<Operations> table_op;
  
    @FXML
    private TableColumn<Operations, String> col_date;
    @FXML
    private TableColumn<Operations, String> col_lieu;
    @FXML
    private TableColumn<Operations, String> col_equipe;
    @FXML
    private TableColumn<Operations, String> col_des;
    @FXML
    private TableColumn<Operations, String> col_photo;
    @FXML
    private TableColumn<Operations, Integer> col_med;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
    col_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
    col_equipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
    col_des.setCellValueFactory(new PropertyValueFactory<>("description"));
    col_photo.setCellValueFactory(new PropertyValueFactory<>("image"));
    col_med.setCellValueFactory(new PropertyValueFactory<>("M"));
   
    table_op.setItems(afficherListOperations());

    }  
      private ObservableList<Operations> afficherListOperations() {
  OperationsService os=new OperationsService();
        List<Operations> ops = os.afficher();
               os.showNotification("Liste des operations", "Voici le tableau de tout nos operations");

        ObservableList<Operations> OpList = FXCollections.observableArrayList(ops);
        return OpList;
      }
       
  
    
}
