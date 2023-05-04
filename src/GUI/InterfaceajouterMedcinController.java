/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.swing.text.Document;
import Entity.Medecin;
import Services.MedecinService;
import tools.MaConnexion;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

/**
 * FXML Controller class
 *
 * @author damak
 */
public class InterfaceajouterMedcinController implements Initializable {

    @FXML
    private TextField prenom;
    @FXML
    private TextField mail;
    @FXML
    private TextField tel;
    @FXML
    private TextField nom;
    @FXML
    private TextField specialite;
    @FXML
    private TextField txt_rechercher;
    @FXML
    private TextField diplome;
    @FXML
    private ImageView photo_field;
    @FXML
    private Button btn_ajouter;
    @FXML
    private TableView<Medecin> TableMedecin;
    @FXML
    private TableColumn<Medecin, String> col_nom;
    @FXML
    private TableColumn<Medecin, String> col_prenom;
    @FXML
    private TableColumn<Medecin, Integer> col_tel;
    @FXML
    private TableColumn<Medecin, Integer> col_id;
    @FXML
    private TableColumn<Medecin, String> col_mail;
    @FXML
    private TableColumn<Medecin, String> col_specialite;
    @FXML
    private TableColumn<Medecin, String> col_dip;
    @FXML
    private TableColumn<Medecin, String> col_photo;

    private String filePath;
    private String imagePath;
    private String photoPath;
    Connection cnx;
    String sql = "";

    /**
     * Initializes the controller class.
     */
    MedecinService ms = new MedecinService();
    Medecin m = new Medecin();
    @FXML
    private Label file_path;
    @FXML
    private Button btnphotomed1;
    @FXML
    private Pagination pagination;
    private ObservableList<Medecin> MedecinList;
    private int rowsPerPage = 10;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_med"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom_med"));
        col_tel.setCellValueFactory(new PropertyValueFactory<>("tel_med"));
        col_mail.setCellValueFactory(new PropertyValueFactory<>("mail_med"));
        col_specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        col_dip.setCellValueFactory(new PropertyValueFactory<>("diplome"));
        col_photo.setCellValueFactory(new PropertyValueFactory<>("photo"));

        TableMedecin.setItems(afficherListMedecins());
 MedecinList = afficherListMedecins();
 
    pagination.setPageFactory(this::createPage);
    int pageCount = (int) Math.ceil((double) MedecinList.size() / rowsPerPage);
    pagination.setPageCount(pageCount);
    pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) ->
        pagination.setCurrentPageIndex(newIndex.intValue()));

    }


private ObservableList<Medecin> afficherListMedecins() {
    List<Medecin> Medecins = ms.afficher();
    return FXCollections.observableArrayList(Medecins);
}

private Node createPage(int pageIndex) {
    int fromIndex = pageIndex * rowsPerPage;
    int toIndex = Math.min(fromIndex + rowsPerPage, MedecinList.size());
    TableMedecin.setItems(FXCollections.observableArrayList(MedecinList.subList(fromIndex, toIndex)));
    return TableMedecin;
}

    @FXML
    private void ajout_med(ActionEvent event) {
        // Vérifier que tous les champs sont remplis
        if (nom.getText().isEmpty() || prenom.getText().isEmpty() || tel.getText().isEmpty()
                || mail.getText().isEmpty() || specialite.getText().isEmpty() || diplome.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs vides");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }

        // Vérifier que le champ téléphone contient bien un nombre
        if (!isNumeric(tel.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Téléphone invalide");
            alert.setHeaderText(null);
            alert.setContentText("Le numéro de téléphone doit être un nombre !");
            alert.showAndWait();
            return;
        }
        // Vérifier que le champ téléphone contient bien un nombre et a 8 chiffres
        if (!tel.getText().matches("\\d{8}")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Téléphone invalide");
            alert.setHeaderText(null);
            alert.setContentText("Le numéro de téléphone doit être un nombre de 8 chiffres !");
            alert.showAndWait();
            return;
        }

        // Vérifier que le champ email contient bien une adresse email valide
        if (!isValidEmailAddress(mail.getText())) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Email invalide");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez entrer une adresse email valide !");
            alert.showAndWait();
            return;
        }

        Medecin h = new Medecin();
        h.setNom_med(nom.getText());
        h.setPrenom_med(prenom.getText());
        h.setTel_med(Integer.parseInt(tel.getText()));
        h.setMail_med(mail.getText());
        h.setSpecialite(specialite.getText());
        h.setDiplome(diplome.getText());
       
            h.setPhoto(file_path.getText());
            System.out.println(h.getPhoto());
        

        try {
            ms.ajouter(h);
                //    ms.showNotification("Un ajout etait fait", "vous avez fait un ajout d'un medecin cher admin");
            // show success message
            TableMedecin.refresh();
            TableMedecin.setItems(afficherListMedecins());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout de médecin");
            alert.setHeaderText(null);
            alert.setContentText("Médecin ajouté avec succès!");
            alert.showAndWait();
        } catch (SQLException ex) {
            // show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur lors de l'ajout du médecin");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'ajout du médecin: " + ex.getMessage());
            alert.showAndWait();
        }
    }

// Vérifier si une chaîne de caractères est un nombre entier
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

// Vérifier si une chaîne de caractères est une adresse email valide
    private boolean isValidEmailAddress(String email) {
        if (email == null) {
            return false;
        }
        return email.contains("@") && email.contains(".");
    }

    private void displayPhotoImageView(Medecin m, ImageView imageView) throws SQLException, IOException {
        // Get the photo bytes from the database Blob
        String photoPath = m.getPhoto();

        // Check if the bis object is not null before creating an Image object
        if (photoPath != null && !photoPath.isEmpty()) {
            File photoFile = new File(photoPath);
            // Display image in the ImageView
            if (photoFile.exists()) {
                Image image = new Image(photoFile.toURI().toString());
                imageView.setImage(image);
            }

        }
    }

    @FXML
    private void ChoosePhoto(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image file");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg, *.png, *.gif)", "*.jpg", "*.jpeg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(null);

        if (file == null) {
            // User didn't select a file
            return;
        }
        else{
            String fileName = file.getName();
            String targetPath = "C:/xampp/htdocs/img/" + fileName;
            Files.copy(Paths.get(file.getAbsolutePath()), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);

            file_path.setText(fileName);
            Image image = new Image(file.toURI().toString(), 200, 200, false, true);
            photo_field.setImage(image);
        
        }

        // Stockez le chemin d'accès du fichier sélectionné
        photoPath = file.getAbsolutePath();

        // Affichez l'image sélectionnée dans l'ImageView
        Image image = new Image(file.toURI().toString());
        photo_field.setImage(image);
    }
// med.setPhoto(photoPath);

    @FXML
    private void btn_modifier(ActionEvent event) {
        Medecin m = TableMedecin.getSelectionModel().getSelectedItem();
        if (m == null) {
            // Show error message if no medecin is selected
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun médecin sélectionné");
            alert.setContentText("Veuillez sélectionner un médecin à modifier.");
            alert.showAndWait();
            return;
        }

        // Create a ChoiceDialog to let user select the attribute to modify
        ChoiceDialog<String> dialog = new ChoiceDialog<>("nom", "nom", "prenom", "tel", "mail", "specialite", "diplome", "photo");
        dialog.setTitle("Modification");
        dialog.setHeaderText("Modifier " + m.getNom_med() + " " + m.getPrenom_med());
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
        inputDialog.setHeaderText("Modifier " + selectedAttribute + " de " + m.getNom_med() + " " + m.getPrenom_med());
        inputDialog.setContentText("Nouvelle valeur:");

        Optional<String> newValue = inputDialog.showAndWait();
        if (!newValue.isPresent()) {
            // User closed the dialog without entering a new value
            return;
        }

        // Update the medecin object based on the selected attribute
        switch (selectedAttribute) {
            case "nom":
                m.setNom_med(newValue.get());
                break;
            case "prenom":
                m.setPrenom_med(newValue.get());
                break;
            case "tel":
                m.setTel_med(Integer.parseInt(newValue.get()));
                break;
            case "mail":
                m.setMail_med(newValue.get());
                break;
            case "specialite":
                m.setSpecialite(newValue.get());
                break;
            case "diplome":
                m.setDiplome(newValue.get());
                break;
            case "photo":
                m.setPhoto(newValue.get());
                break;
            default:
                break;
        }

        // Update the table and show success message
        ms.modifier(m);
      //  ms.showNotification("Une modification était faite", "Une modification du medecin etait faite");
        TableMedecin.refresh();
        TableMedecin.setItems(afficherListMedecins());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Modification effectuée");
        alert.showAndWait();
    }

    @FXML
    private void btn_supprimer(ActionEvent event) {

        Medecin selectedMedecin = TableMedecin.getSelectionModel().getSelectedItem();
        if (selectedMedecin == null) {
            // afficher une alerte d'avertissement
            return;
        }

        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirmation");
        confirmDialog.setHeaderText(null);
        confirmDialog.setContentText("Are you sure you want to delete this doctor?");
        Optional<ButtonType> result = confirmDialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            m.setId(selectedMedecin.getId());

            System.out.println(m);
//    ms.delete2(v);
            ms.supprimer(selectedMedecin);
           // ms.showNotification("Une suppression etait faite", "Vous avez supprimé un medecin cher admin");
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("doctor deleted successfully!");

            successAlert.showAndWait();

            TableMedecin.setItems(afficherListMedecins());
        }

    }

 @FXML
private void btn_rechercher(ActionEvent event) {
    String nomMedecin = txt_rechercher.getText();

    if (nomMedecin.isEmpty()) {
        // Show all Medecin objects if search field is empty
        TableMedecin.setItems(afficherListMedecins());
    } else {
        // Search for Medecin object by nom_med value
        List<Medecin> medecins = ms.rechercherParNom(nomMedecin);
      //  ms.showNotification("Une recherche etait fait", "vous avez fait une recherche cher admin");

        if (medecins.isEmpty()) {
            // Display error message if no results found
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Recherche de médecin");
            alert.setHeaderText("Aucun médecin trouvé");
            alert.setContentText("Aucun médecin trouvé pour le nom \"" + nomMedecin + "\".");
            alert.showAndWait();
        } else {
            // Display results in table view if at least one result found
            ObservableList<Medecin> medecinList = FXCollections.observableArrayList(medecins);
            TableMedecin.setItems(medecinList);
        }
    }
}


@FXML
private void tri_desc(ActionEvent event) {
    ObservableList<Medecin> data = TableMedecin.getItems();
    data.sort((m1, m2) -> m2.getNom_med().compareTo(m1.getNom_med()));
    TableMedecin.setItems(data);
}


 @FXML
private void tri_asc(ActionEvent event) {
    ObservableList<Medecin> data = TableMedecin.getItems();
    data.sort((m1, m2) -> m1.getNom_med().compareTo(m2.getNom_med()));
    TableMedecin.setItems(data);
}
    @FXML
private void refresh(ActionEvent event) {
    // Clear the existing items in the TableMedecin
    TableMedecin.getItems().clear();

    // Repopulate the TableMedecin with updated data from the database
    TableMedecin.setItems(afficherListMedecins());
}

   @FXML
private void clic_pdf(ActionEvent event) throws DocumentException {
    // Create a new PDF document
    com.itextpdf.text.Document document = new com.itextpdf.text.Document();
    try {
        PdfWriter.getInstance(document, new FileOutputStream("medecin.pdf"));
        document.open();

        // Add title to document
        Paragraph title = new Paragraph("Liste des Medecins");
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Add table of medecins to document
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Add column headers to table
        PdfPCell cell1 = new PdfPCell(new Paragraph("ID"));
        PdfPCell cell2 = new PdfPCell(new Paragraph("Nom"));
        PdfPCell cell3 = new PdfPCell(new Paragraph("Prenom"));
        PdfPCell cell4 = new PdfPCell(new Paragraph("Telephone"));
        PdfPCell cell5 = new PdfPCell(new Paragraph("Email"));
        PdfPCell cell6 = new PdfPCell(new Paragraph("Specialite"));
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);

        // Get all medecins from the database
        MedecinService medecinService = new MedecinService();
        List<Medecin> medecins = medecinService.afficher();

        // Add medecins to table
        for (Medecin medecin : medecins) {
            table.addCell(String.valueOf(medecin.getId()));
            table.addCell(medecin.getNom_med());
            table.addCell(medecin.getPrenom_med());
            table.addCell(String.valueOf(medecin.getTel_med()));
            table.addCell(medecin.getMail_med());
            table.addCell(medecin.getSpecialite());
        }

        document.add(table);
    } catch (FileNotFoundException | DocumentException e) {
        e.printStackTrace();
    } finally {
        document.close();
    }
}





}
