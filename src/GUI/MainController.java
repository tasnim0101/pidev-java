/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author chahr
 */
public class MainController implements Initializable {

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tlTel;
    @FXML
    private TextField tfMail;
    @FXML
    private TextField tfSpecialite;
    @FXML
    private TextField tfDiplome;
    @FXML
    private TextField tfPhoto;
    @FXML
    private TableView<?> tvMed;
  
    @FXML
    private TableColumn<?, ?> colNom;
    @FXML
    private TableColumn<?, ?> Colprenom;
    @FXML
    private TableColumn<?, ?> colTel;
    @FXML
    private TableColumn<?, ?> colMail;
    @FXML
    private TableColumn<?, ?> colSpacialite;
    @FXML
    private TableColumn<?, ?> colDiplome;
    @FXML
    private TableColumn<?, ?> colPhoto;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btfsupprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
    
}
