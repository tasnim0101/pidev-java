package GUI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class DashboardController implements Initializable {

    @FXML
    private Button Gservice;
    @FXML
    private Button Glabo;
    @FXML
    private Button Gmedecin;
     @FXML
    private Button Gmateriel;
      @FXML
    private Button Guser;
       @FXML
    private Button Gevent;
    @FXML
    private TabPane AffichageAdmin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Gservice(ActionEvent event) {

        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/tn.esprit.gui/Interface_afficher_service.fxml"));
            AnchorPane fileRoot1 = fxmlLoader1.load();

            Tab tab1 = new Tab("Service");
            tab1.setContent(fileRoot1);

            FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/tn.esprit.gui/Interface_afficher_hosp.fxml"));
            AnchorPane fileRoot2 = fxmlLoader2.load();

            Tab tab2 = new Tab("hospitalisation");
            tab2.setContent(fileRoot2);

            AffichageAdmin.getTabs().clear();
            AffichageAdmin.getTabs().addAll(tab1, tab2);

        } catch (IOException e) {
           
        }
    }

    @FXML
    private void Glabo(ActionEvent event) {
        // TODO: implement this method
    }

    @FXML
    private void Gmedecin(ActionEvent event) {
        // TODO: implement this method
    }

}
