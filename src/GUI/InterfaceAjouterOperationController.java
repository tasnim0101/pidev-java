/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.awt.Desktop;
import java.io.File;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import Entity.Medecin;
import Entity.Operations;
import Services.MedecinService;
import Services.OperationsService;

/**
 * FXML Controller class
 *
 * @author chahr
 */
public class InterfaceAjouterOperationController implements Initializable {

    @FXML
    private TextField lieu;
    @FXML
    private TextField equipe;
    @FXML
    private TextField description;
    @FXML
    private Button btn_update;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_supprimer;
    @FXML
    private Button btn_modifier;
    @FXML
    private TableView<Operations> table_op;
    @FXML
    private TableColumn<Operations, Integer> col_id;
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

    OperationsService os = new OperationsService();

    private String filePath;
    private String imagePath;
    private String photoPath;

    @FXML
    private DatePicker date;
    @FXML
    private ImageView photo_field;

    /**
     * Initializes the controller class.
     */
    MedecinService ms = new MedecinService();
    @FXML
    private ComboBox<Medecin> id_med;
    @FXML
    private Label file_path;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Medecin> medecins = ms.afficher();
        ObservableList<Medecin> list = FXCollections.observableArrayList(medecins);
        id_med.setItems(list);

        id_med.setCellFactory(param -> new ListCell<Medecin>() {
            @Override
            protected void updateItem(Medecin item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.toString() == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                }
            }
        });

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        col_equipe.setCellValueFactory(new PropertyValueFactory<>("equipe"));
        col_des.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_photo.setCellValueFactory(new PropertyValueFactory<>("image"));
        col_med.setCellValueFactory(new PropertyValueFactory<>("M"));

        table_op.setItems(afficherListOperations());

    }

    @FXML
    private void clic_refresh(ActionEvent event) {
    }
@FXML
private void clic_ajout(ActionEvent event) {
    // Check that all input fields have valid values
    if (date.getValue() == null) {
        // Display an error message if the date field is empty
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Veuillez sélectionner une date");
        alert.showAndWait();
        return;
    }
    
   String lieuText = lieu.getText();
    if (lieuText.isEmpty()) {
        // Display an error message if the lieu field is empty
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Veuillez saisir le lieu de l'opération");
        alert.showAndWait();
        return;
    } else if (!lieuText.matches("^[a-zA-Z]+$")) {
        // Display an error message if the lieu field contains non-letter characters
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Le lieu ne doit contenir que des lettres");
        alert.showAndWait();
        return;
    }
       if (!equipe.getText().matches("^[a-zA-Z]+$")) {
        // Display an error message if the equipe field is not a valid string
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Veuillez saisir une chaîne de caractères pour l'équipe");
        alert.showAndWait();
        return;
    }

    
    if (!description.getText().matches("^[a-zA-Z]+$")) {
        // Display an error message if the description field is not a valid string
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Veuillez saisir une chaîne de caractères pour la description de l'opération");
        alert.showAndWait();
        return;
    }


    Operations op = new Operations();
    op.setDate(date.getValue().toString());
    op.setLieu(lieu.getText());
    op.setEquipe(equipe.getText());
    op.setDescription(description.getText());
    op.setImage(file_path.getText());
 
            System.out.println(op.getImage());
    Medecin m = id_med.getSelectionModel().getSelectedItem();

    op.setM(m);

    os.ajouter(op);
                  //  os.showNotification("Un ajout est fait", "merci pour l'ajout");

    // Refresh the TableView to show the new added operation
    table_op.setItems(afficherListOperations());
}


    @FXML
private void clic_supp(ActionEvent event) {
    Operations selectedOperation = table_op.getSelectionModel().getSelectedItem();
    if (selectedOperation == null) {
        // Display a warning alert if no operation is selected
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Veuillez sélectionner une opération à supprimer");
        alert.showAndWait();
        return;
    }

    Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
    confirmDialog.setTitle("Confirmation");
    confirmDialog.setHeaderText(null);
    confirmDialog.setContentText("Êtes-vous sûr de vouloir supprimer cette opération ?");
    Optional<ButtonType> result = confirmDialog.showAndWait();

    if (result.isPresent() && result.get() == ButtonType.OK) {
        OperationsService os = new OperationsService();
        os.supprimer(selectedOperation);
               //     os.showNotification("Une suppression est faite", "merci pour la suppression");

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Succès");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Opération supprimée avec succès !");
        successAlert.showAndWait();

        table_op.setItems(afficherListOperations());
    }
}


    @FXML
private void clic_mod(ActionEvent event) {

    Operations o = table_op.getSelectionModel().getSelectedItem();
    if (o == null) {
        // Show error message if no operation is selected
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Aucune opération sélectionnée");
        alert.setContentText("Veuillez sélectionner une opération à modifier.");
        alert.showAndWait();
        return;
    }

    // Create a ChoiceDialog to let user select the attribute to modify
    ChoiceDialog<String> dialog = new ChoiceDialog<>("Lieu", "Equipe", "Description", "Photo", "Date", "Médecin");
    dialog.setTitle("Modification");
    dialog.setHeaderText("Modifier l'opération");
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

    // Update the header text based on the selected attribute
    switch (selectedAttribute) {
        case "Lieu":
            inputDialog.setHeaderText("Modifier " + selectedAttribute + " de l'opération");
            break;
        case "Equipe":
            inputDialog.setHeaderText("Modifier " + selectedAttribute + " de l'opération");
            break;
        case "Description":
            inputDialog.setHeaderText("Modifier " + selectedAttribute + " de l'opération");
            break;
        case "Photo":
            inputDialog.setHeaderText("Modifier " + selectedAttribute + " de l'opération");
            break;
        case "Date":
            inputDialog.setHeaderText("Modifier " + selectedAttribute + " de l'opération (format: yyyy-MM-dd)");
            break;
        case "Médecin":
            inputDialog.setHeaderText("Modifier le médecin de l'opération (nom du médecin)");
            break;
        default:
            break;
    }

    inputDialog.setContentText("Nouvelle valeur:");

    Optional<String> newValue = inputDialog.showAndWait();
    if (!newValue.isPresent()) {
        // User closed the dialog without entering a new value
        return;
    }

    // Update the operation object based on the selected attribute
    switch (selectedAttribute) {
        case "Lieu":
            o.setLieu(newValue.get());
            break;
        case "Equipe":
            o.setEquipe(newValue.get());
            break;
        case "Description":
            o.setDescription(newValue.get());
            break;
        case "Photo":
            o.setImage(newValue.get());
            break;
        case "Date":
            o.setDate(newValue.get());
            break;
      
       
        default:
            break;
    }



    // Update the table and show success message
    os.modifier(o);
                       // os.showNotification("Une modification est faite", "merci pour la modification");

    table_op.refresh();
    table_op.setItems(afficherListOperations());
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText("Modification effectuée");
    alert.showAndWait();

}
  /*  private void displayPhotoImageView(Medecin m, ImageView imageView) throws SQLException, IOException {
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
*/
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

    private ObservableList<Operations> afficherListOperations() {

        List<Operations> ops = os.afficher();
        ObservableList<Operations> OpList = FXCollections.observableArrayList(ops);
        return OpList;
    }@FXML
public void exportToPDF(ActionEvent event) throws IOException, DocumentException {
    
    // Create a new PDF document
        com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
    //Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("Operations.pdf"));
    doc.open();

    // Get all operations from the database
    OperationsService so = new OperationsService();
    List<Operations> operations = so.afficher();

    // Add operations information to the PDF
    doc.add(new Paragraph("Liste des OPERATIONS :"));
    doc.add(new Paragraph("------------------------\n"));

    // Add table of operations to document
    PdfPTable table = new PdfPTable(6);
    table.setWidthPercentage(100);
    table.setSpacingBefore(10f);
    table.setSpacingAfter(10f);

    // Add column headers to table
    PdfPCell cell1 = new PdfPCell(new Paragraph("ID"));
    PdfPCell cell2 = new PdfPCell(new Paragraph("Date"));
    PdfPCell cell3 = new PdfPCell(new Paragraph("Lieu"));
    PdfPCell cell4 = new PdfPCell(new Paragraph("Equipe"));
    PdfPCell cell5 = new PdfPCell(new Paragraph("Description"));
    PdfPCell cell6 = new PdfPCell(new Paragraph("Image"));
    table.addCell(cell1);
    table.addCell(cell2);
    table.addCell(cell3);
    table.addCell(cell4);
    table.addCell(cell5);
    table.addCell(cell6);

    // Add operations to table
    for (Operations operation : operations) {
        table.addCell(String.valueOf(operation.getId()));
        table.addCell(operation.getDate());
        table.addCell(operation.getLieu());
        table.addCell(operation.getEquipe());
        table.addCell(operation.getDescription());
        table.addCell(operation.getImage());
    }

    doc.add(table);
    doc.close();

    // Open the PDF file automatically
    if (Desktop.isDesktopSupported()) {
        File file = new File("Operations.pdf");
        Desktop.getDesktop().open(file);
    }
}


@FXML
public void exportToExcel(ActionEvent event) throws IOException {
    // Create new workbook
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Operations");

    // Define column headers
    String[] headers = {"ID", "Date", "Lieu", "Equipe", "Description", "Image"};
    Row headerRow = sheet.createRow(0);
    for (int i = 0; i < headers.length; i++) {
        Cell cell = headerRow.createCell(i);
        cell.setCellValue(headers[i]);
    }

    // Get all operations from the database
    OperationsService so = new OperationsService();
    List<Operations> operations = so.afficher();

    // Populate data rows
    int rowNum = 1;
    for (Operations operation : operations) {
        Row row = sheet.createRow(rowNum++);

        row.createCell(0).setCellValue(operation.getId());
        row.createCell(1).setCellValue(operation.getDate());
        row.createCell(2).setCellValue(operation.getLieu());
        row.createCell(3).setCellValue(operation.getEquipe());
        row.createCell(4).setCellValue(operation.getDescription());
        row.createCell(5).setCellValue(operation.getImage());
    }

    // Autosize columns for better readability
    for (int i = 0; i < headers.length; i++) {
        sheet.autoSizeColumn(i);
    }

    // Write workbook to file and open it
    try (FileOutputStream outputStream = new FileOutputStream("Operations.xlsx")) {
        workbook.write(outputStream);
        Desktop.getDesktop().open(new File("Operations.xlsx"));
    } catch (IOException e) {
        e.printStackTrace();
    }
}



}
