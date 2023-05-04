/*
 controller el baaackkkk service (afficher modifier ajouter supprimer ) *
 */
package GUI;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Entity.Services;
import Services.GestionService;
import tools.MaConnexion;


public class Dashboard_admin_tassnimController implements Initializable {

    @FXML
    private TableView<Services> Tservice;
    @FXML
    private TableColumn<Services, Integer> col_id;
    @FXML
    private TableColumn<Services, String> col_type;
    @FXML
    private TableColumn<Services, String> col_description;
    @FXML
    private TableColumn<Services, String> col_chef;
    @FXML
    private TableColumn<Services, Double> col_prix;
    @FXML
    private TextField txt_desc;
    @FXML
    private TextField txt_chef;
    @FXML
    private TextField txt_prix;
    @FXML
    private TextField txt_type;
    @FXML
    private Button btn_ajout;
    @FXML
    private Button btn_mod;
    @FXML
    private Button btn_supp;
    GestionService gs = new GestionService();
    private Connection conn = MaConnexion.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    public ObservableList<Services> afficherListeServices() {

        List<Services> lservices = gs.afficher();
        ObservableList<Services> ServicesList = FXCollections.observableArrayList(lservices);
        return ServicesList;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_chef.setCellValueFactory(new PropertyValueFactory<>("chef_service"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        Tservice.setItems(afficherListeServices());

    }

@FXML
private void btn_ajout(ActionEvent event) throws SQLException {
    // Récupérer les valeurs des champs de texte
    String chef = txt_chef.getText();
    String type = txt_type.getText();
    String description = txt_desc.getText();
    
    // Vérifier que les champs ne sont pas vides
    if (chef.isEmpty() || type.isEmpty() || description.isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }
    
    // Vérifier que les champs ne contiennent que des lettres et des espaces
    if (!chef.matches("[a-zA-Z ]+") || !type.matches("[a-zA-Z ]+") || !description.matches("[a-zA-Z ]+")) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Les champs ne doivent contenir que des lettres et des espaces.");
        alert.showAndWait();
        return;
    }
    
    // Vérifier que le champ prix contient un nombre valide
    Double prix;
    try {
        prix = Double.parseDouble(txt_prix.getText());
    } catch (NumberFormatException e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Veuillez entrer un nombre valide pour le prix.");
        alert.showAndWait();
        return;
    }

    // Créer un nouvel objet Service avec les valeurs saisies
    Services s1 = new Services();
    s1.setDescription(description);
    s1.setChef_service(chef);
    s1.setType(type);
    s1.setPrix(prix);

    // Ajouter le service à la base de données et rafraîchir la table
    gs.ajouter(s1);
    Tservice.refresh();
    Tservice.setItems(afficherListeServices());

    // Afficher un message de confirmation
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setHeaderText("Service ajouté avec succès");
    alert.showAndWait();
// gs.showNotification("Notifification d'ajout", "Un Ajout d'une réclamation était fait.");
    // Effacer les champs de texte
    txt_chef.clear();
    txt_type.clear();
    txt_desc.clear();
    txt_prix.clear();
}

@FXML
private void btn_mod(ActionEvent event) {
    Services s = Tservice.getSelectionModel().getSelectedItem();
    if (s == null) {
        // Show error message if no service is selected
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Aucun service sélectionné");
        alert.setContentText("Veuillez sélectionner un service à modifier.");
        alert.showAndWait();
        return;
    }

    // Create a ChoiceDialog to let user select the attribute to modify
    ChoiceDialog<String> dialog = new ChoiceDialog<>("type", "type", "description", "prix", "chef de service");
    dialog.setTitle("Modification");
    dialog.setHeaderText("Modifier le service: " + s.getType());
    dialog.setContentText("Choisissez l'attribut à modifier:");

    Optional<String> result = dialog.showAndWait();
    if (!result.isPresent()) {
        // User closed the dialog without making a selection
        return;
    }

    String selectedAttribute = result.get();

    // Create an TextInputDialog to get the new value from the user
    TextInputDialog inputDialog = new TextInputDialog();
    inputDialog.setTitle("Modification");
    inputDialog.setHeaderText("Modifier " + selectedAttribute + " du service: " + s.getType());
    inputDialog.setContentText("Nouvelle valeur:");

    Optional<String> newValue = inputDialog.showAndWait();
    if (!newValue.isPresent()) {
        // User closed the dialog without entering a new value
        return;
    }

    // Update the service object based on the selected attribute
   switch (selectedAttribute) {
    case "type":
    case "description":
    case "chef de service":
        // Add input validation to allow only strings for these attributes
        if (newValue.get().matches("^[a-zA-Z]+$")) {
            if (selectedAttribute.equals("type")) {
                s.setType(newValue.get());
            } else if (selectedAttribute.equals("description")) {
                s.setDescription(newValue.get());
            } else {
                s.setChef_service(newValue.get());
            }
        } else {
            // Show error message if the entered value is not a string
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Valeur non valide");
            alert.setContentText("Veuillez entrer une valeur de type chaîne de caractères.");
            alert.showAndWait();
            return;
        }
        break;
    case "prix":
        if (newValue.get().matches("^\\d+(\\.\\d+)?$")) {
            s.setPrix(Double.parseDouble(newValue.get()));
        } else {
            // Show error message if the entered price is not valid
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Prix non valide");
            alert.setContentText("Veuillez entrer un prix valide.");
            alert.showAndWait();
            return;
        }
        break;
    default:
        break;
}
    // Update the table and show success message
    gs.modifier(s);
    Tservice.refresh();
    Tservice.setItems(afficherListeServices());
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("Modification effectuée");
    alert.showAndWait();
}



    @FXML
    private void btn_supp(ActionEvent event) {

        Services selectedService = Tservice.getSelectionModel().getSelectedItem();
        if (selectedService == null) {
            // afficher une alerte d'avertissement
            return;
        }

        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirmation");
        confirmDialog.setHeaderText(null);
        confirmDialog.setContentText("Are you sure you want to delete this service?");
        Optional<ButtonType> result = confirmDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            Services v = new Services();
            v.setId(selectedService.getId());
            v.setChef_service(selectedService.getChef_service());
            v.setType(selectedService.getType());
            v.setDescription(selectedService.getDescription());
            v.setPrix(selectedService.getPrix());
            System.out.println(v);
            gs.supprimer(selectedService);

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("service deleted successfully!");

            successAlert.showAndWait();

            Tservice.setItems(afficherListeServices());
        }

    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
          /*  Parent root = FXMLLoader.load(getClass().getResource("DashboardAdmin.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Votre Profil");
                stage.show();*/
    }

   
}
