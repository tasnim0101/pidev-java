/*
*/
package GUI;
import com.itextpdf.text.BaseColor;
import com.teamdev.jxbrowser.dom.Document;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Entity.Services;
import Services.GestionService;
import tools.MaConnexion;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * FXML Controller class
 *
 * @author hamdi
 */
public class Interface_afficher_serviceController implements Initializable {

 
    @FXML
    private TableColumn<Services, String>  description;
    @FXML
    private TableColumn<Services, String>  type;
    @FXML
  private TableColumn<Services, Double>  prix;
    @FXML
  private TableColumn<Services, String> chef_service;
    private final int ROWS_PER_PAGE = 10; // Number of rows per page
  //  private GestionService gs = new GestionService();

    /**
     * Initializes the controller class.
     */   Services s1 = new Services();
         GestionService gs = new GestionService();
private Connection conn =MaConnexion.getInstance().getCnx();
    @FXML
    private TableView<Services> Tservice;
    @FXML
    private Button refreshButton;
    private int rowsPerPage = 10;
    @FXML
    private TextField txt_rechercher;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        chef_service.setCellValueFactory(new PropertyValueFactory<>("chef_service"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
   
        Tservice.setItems(afficherListeServices());
         refreshButton.setOnAction(e -> {
        ObservableList<Services> servicesList = afficherListeServices();
      
        
        Tservice.setItems(servicesList);
    });
    }
    
 public ObservableList<Services> afficherListeServices() {

    List<Services> lservices = gs.afficher();
    ObservableList<Services> ServicesList = FXCollections.observableArrayList(lservices);
   
    return ServicesList;
    
}
@FXML
private void exportToPDF() {
    try {
        // Get the current list of services
        ObservableList<Services> servicesList = Tservice.getItems();
        
        // Create a new PDF document
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        PdfWriter.getInstance(document, new FileOutputStream("services.pdf"));
        document.open();
        
        // Add a title to the document
        document.add(new Paragraph("Liste des services"));
        
        // Add a table to the document
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        
        // Add column headers to the table
        PdfPCell header1 = new PdfPCell(new Phrase("Type"));
        header1.setBackgroundColor(new BaseColor(0, 128, 255)); // bleu
        table.addCell(header1);
        
        PdfPCell header2 = new PdfPCell(new Phrase("Description"));
       header1.setBackgroundColor(new BaseColor(0, 128, 255)); 
        table.addCell(header2);
        
        PdfPCell header3 = new PdfPCell(new Phrase("Chef de service"));
       header1.setBackgroundColor(new BaseColor(0, 128, 255)); 
        table.addCell(header3);
        
        PdfPCell header4 = new PdfPCell(new Phrase("Prix"));
       header1.setBackgroundColor(new BaseColor(0, 128, 255)); 
        table.addCell(header4);
        
        // Add rows to the table
        for (Services service : servicesList) {
            table.addCell(new PdfPCell(new Phrase(service.getType())));
            table.addCell(new PdfPCell(new Phrase(service.getDescription())));
            table.addCell(new PdfPCell(new Phrase(service.getChef_service())));
            table.addCell(new PdfPCell(new Phrase(Double.toString(service.getPrix()))));
        }
        
        // Add the table to the document
        document.add(table);
        
        // Close the document
        document.close();
        
        // Show a success message
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Export to PDF");
        alert.setHeaderText(null);
        alert.setContentText("Les services ont été exportés avec succès en PDF.");
        alert.showAndWait();
    } catch (Exception e) {
        // Show an error message if something goes wrong
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Export to PDF");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur s'est produite lors de l'exportation en PDF.");
        alert.showAndWait();
        e.printStackTrace();
    }
}

@FXML
private void exportToExcel() {
    try {
        // Get the current list of services
        ObservableList<Services> servicesList = Tservice.getItems();

        // Create a new workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create a new sheet
        XSSFSheet sheet = workbook.createSheet("Services");

        // Add column headers to the sheet
        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Type");
        headerRow.createCell(1).setCellValue("Description");
        headerRow.createCell(2).setCellValue("Chef de service");
        headerRow.createCell(3).setCellValue("Prix");

        // Add rows to the sheet
        int rowCount = 1;
        for (Services service : servicesList) {
            XSSFRow row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(service.getType());
            row.createCell(1).setCellValue(service.getDescription());
            row.createCell(2).setCellValue(service.getChef_service());
            row.createCell(3).setCellValue(service.getPrix());
        }

       
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the workbook to a file
        FileOutputStream outputStream = new FileOutputStream("services.xlsx");
        workbook.write(outputStream);
        workbook.close();


        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Export to Excel");
        alert.setHeaderText(null);
        alert.setContentText("Les services ont été exportés avec succès en Excel.");
        alert.showAndWait();
    } catch (Exception e) {
        // Show an error message if something goes wrong
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Export to Excel");
        alert.setHeaderText(null);
        alert.setContentText("Une erreur s'est produite lors de l'exportation en Excel.");
        alert.showAndWait();
        e.printStackTrace();
    }
}


    @FXML
    private void clic_asc(ActionEvent event) {
          ObservableList<Services> data = Tservice.getItems();
    data.sort((m1, m2) -> m1.getPrix().compareTo(m2.getPrix()));
    Tservice.setItems(data);
    }

    @FXML
    private void clic_desc(ActionEvent event) {
            ObservableList<Services> data = Tservice.getItems();
    data.sort((m1, m2) -> m2.getPrix().compareTo(m1.getPrix()));
    Tservice.setItems(data);
    }
    
   @FXML
private void rechercher_chef(ActionEvent event) {
    String chef = txt_rechercher.getText().trim();
    ObservableList<Services> allServices = afficherListeServices();
    ObservableList<Services> filteredServices = FXCollections.observableArrayList();
    for (Services service : allServices) {
        if (service.getChef_service().equalsIgnoreCase(chef)) {
            filteredServices.add(service);
        }
    }
    Tservice.setItems(filteredServices);
}


@FXML
private void rechercher_type(ActionEvent event) {
    String type = txt_rechercher.getText().trim();
    ObservableList<Services> allServices = afficherListeServices();
    ObservableList<Services> filteredServices = FXCollections.observableArrayList();
    for (Services service : allServices) {
        if (service.getType().equalsIgnoreCase(type)) {
            filteredServices.add(service);
        }
    }
    Tservice.setItems(filteredServices);
}

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("FrontClient.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Votre Profil");
                stage.show();
    }

    
    

}
