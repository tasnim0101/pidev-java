/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Materiel;

import Materiel.entities.Materiel;

import Services.Materiel.MaterielService;
import GUI.Materiel.EditMaterielController;
import GUI.Materiel.AddMaterielController;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import integration.Integration ;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import util.dbconnection;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author fadi1
 */
public class AffichageMaterielController implements Initializable {

    @FXML
    private TableView<Materiel> EventsTv;
    @FXML
    private TableColumn<Materiel, String> nomTv;
    @FXML
    private TableColumn<Materiel, String> LieuTv;
     @FXML
    private TableColumn<Materiel, String> prixTv;
      @FXML
    private TableColumn<Materiel, String> prixTv1;
       @FXML
    private TableColumn<Materiel, String> prixTv11;
    
   
    @FXML
    private TableColumn<Materiel, Button> pdf;
    @FXML
    private Label welcomeLb;
        @FXML

    private Label eventShowNom;
        
        @FXML

    private ImageView eventShowImg;
     @FXML
    private Button btnEventAdd;
      @FXML
    private Button btnEventDelete;
      @FXML
    private TextField eventSearch;
  
    @FXML
    private Label eventShowCategory;
      
        


    // instance database Service 
    MaterielService ps = new MaterielService();
    @FXML
    private TableColumn<Materiel, Button> delete;
    @FXML
    private Button modifier;
    @FXML
    private Button btnSearch;
    @FXML
    private Button CategBT;
    
    @FXML
    private Button FrontBT;
     @FXML
    private Button menu;
    
 
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            List<Materiel> personnes = ps.afficher();
            ObservableList<Materiel> olp = FXCollections.observableArrayList(personnes);
             
            
          nomTv.setCellValueFactory(new PropertyValueFactory("libelle"));
            LieuTv.setCellValueFactory(new PropertyValueFactory("type"));
            prixTv.setCellValueFactory(new PropertyValueFactory("prix"));
            prixTv1.setCellValueFactory(new PropertyValueFactory("like"));
            prixTv11.setCellValueFactory(new PropertyValueFactory("dislike"));
             this.deleteMateriel();
            try {
                this.pdf();
            } catch (DocumentException ex) {
                Logger.getLogger(AffichageMaterielController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WriterException ex) {
                Logger.getLogger(AffichageMaterielController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AffichageMaterielController.class.getName()).log(Level.SEVERE, null, ex);
            }
             EventsTv.setItems(olp);
             
              //search//
        FilteredList<Materiel> filter = new FilteredList<>(olp, b->true);
        eventSearch.textProperty().addListener((observable, oldValue, newValue )-> {

        filter.setPredicate(materiel -> {
            if(newValue.isEmpty() || newValue==null ) {
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(materiel.getLibelle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            } else if (materiel.getType().toLowerCase().contains(lowerCaseFilter)){
                return true;
            }else if (Double.toString(materiel.getPrix()).contains(lowerCaseFilter)){
                
                
                return true;
                
            
            }else 
            return false;
        });
        });
        SortedList<Materiel> sort = new SortedList<>(filter);
        sort.comparatorProperty().bind(EventsTv.comparatorProperty());
        
        EventsTv.setItems(sort);
        
            
             
            //this.delete();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        //modifier.setVisible(false);
        modifier.setDisable(true);
        
        
        

    }    
        
    
        @FXML
    private void GoToFournisseur(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageFournisseur.fxml"));
        AffichageFournisseurController aec = loader.getController();
        Parent root = loader.load();
        CategBT.getScene().setRoot(root);
        
        
    }
     @FXML
    private void menuback (ActionEvent event) throws IOException {
       /* FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageMateriel.fxml"));
        AffichageMaterielController aec = loader.getController();
        Parent root = loader.load();
        materielback.getScene().setRoot(root); */
       Integration m = new Integration() ;
         m.changeScene("/gui/User/back.fxml");
        
        
    }
    

   @FXML
    private void GoToAddMateriel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddMateriel.fxml"));
        AddMaterielController aec = loader.getController();
        Parent root = loader.load();
        btnEventAdd.getScene().setRoot(root);
        
        
    }
    
    
    @FXML
    private void GoToFront(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        
        Parent root = loader.load();
        SampleController c = loader.getController();
        FrontBT.getScene().setRoot(root);
          
    }
    
    
  
    @FXML
    private void GoToEditMateriel(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("editMateriel.fxml"));
        
        Parent root = loader.load();
        modifier.getScene().setRoot(root);
        
        EditMaterielController eec = loader.getController();
        Materiel e = EventsTv.getSelectionModel().getSelectedItem();
        
        eec.init(e);  
        
    }
 
 
     public void deleteMateriel() {
        delete.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("supprimer");
                        b.setOnAction((event) -> {
                            try {
                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmation de suppression");
                            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cet événement ?");
                            

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                if (ps.supprimer(EventsTv.getItems().get(getIndex()))) {
                                    updateTable();
                                }
                            }
                            
                            } catch (SQLException ex) {
                                System.out.println("erreor:" + ex.getMessage());

                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });

    }
     
     public void updateTable() throws SQLException {
    ObservableList<Materiel> list = FXCollections.observableArrayList(ps.afficher());
    EventsTv.setItems(list);
}
    
     
      @FXML
    void showSelectedMateriel(MouseEvent event) throws SQLException {
        Materiel e = EventsTv.getSelectionModel().getSelectedItem();
     
        if (e != null) {
            
            eventShowNom.setText(String.valueOf(e.getLibelle()));
        
      
if (e.getFournisseur_id() == 0) {
    eventShowCategory.setText("Fournisseur n'existe pas");
    System.out.println("err");
    return;
}

// Exécution de la requête SQL pour récupérer le nom de catégorie
Connection cnx = dbconnection.getInstance().getConnection();
PreparedStatement ps = cnx.prepareStatement("SELECT nom FROM fournisseur WHERE id = ?");
ps.setInt(1, e.getFournisseur_id());
ResultSet rs = ps.executeQuery();
if (rs.next()) {
    String nomCategorie = rs.getString(1);
    eventShowCategory.setText(nomCategorie);
} else {
    eventShowCategory.setText("fournisseur introuvable");
}
         
            
        //    eventShowCategory.setVisible(true);
           
             //modifier.setVisible(true);
        modifier.setDisable(false);
           // if(e.getUserId() == uid)
            {
               // btnEventDelete.setVisible(true);
                //btnEventDelete.setDisable(false);
               // btnEventEdit.setVisible(true);
               // btnEventEdit.setDisable(false);
         //   }else{
               /* btnEventDelete.setVisible(false);
                btnEventDelete.setDisable(true);
                btnEventEdit.setVisible(false);
                btnEventEdit.setDisable(true);*/
           // }
            
            //EventPieChart.setVisible(false);

        }
    }
    }
    
    /*
    public void qrcode() throws SQLException {

Materiel data = EventsTv.getSelectionModel().getSelectedItem();
        

        // Create a button
        // Set an event handler for the button
        // Convert the data to a string
        StringBuilder stringBuilder = new StringBuilder();
        //lawej ala lutilite mtaa apend w kifech tejbed les donees w kifech tnajm tbadelha liste adhika mtaa data
            stringBuilder.append(data);
            System.out.println(data);
            stringBuilder.append("\n");
        
        String dataString = stringBuilder.toString().trim();

        // Generate the QR code image
        Image qrCodeImage = generateQRCode(dataString);

        // Display the QR code image
        ImageView imageView = new ImageView(qrCodeImage);
        VBox vbox = new VBox(imageView);
        Scene scene = new Scene(vbox);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }*/

   /* private Image generateQRCode(String data) {
        try {
            // Create a QR code writer
            QRCodeWriter writer = new QRCodeWriter();

            // Create a BitMatrix from the data and set the size
            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 200, 200);

            // Create a BufferedImage from the BitMatrix
            BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);

            // Create a JavaFX Image from the BufferedImage
            return SwingFXUtils.toFXImage(image, null);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

*/
   
   
   
     public void pdf() throws FileNotFoundException, DocumentException, WriterException, IOException {
        pdf.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("imprimer");
                       
                        
                        b.setOnAction((event) -> {
                            System.out.println("gggg");
                            // Get the selected Activite object from the TableView
                            Materiel selectedEvenement = (Materiel) getTableView().getItems().get(getIndex());
                            // Create a new Document
                            Document document = new Document();
 try {
                              PdfWriter  writer = PdfWriter.getInstance(document, new FileOutputStream("Materiel.pdf"));
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(AffichageMaterielController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (DocumentException ex) {
                                Logger.getLogger(AffichageMaterielController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        

// Open the Document
                            document.open();
                        // Set the font styles
    Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
    Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
    Font bodyFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);


                           
try{
                            
                                  document.setPageSize(PageSize.A4);
    document.setMargins(20, 20, 20, 20);
    // Create a new Rectangle object
Rectangle border = new Rectangle(document.getPageSize());

// Set the border color and width
border.setBorder(Rectangle.BOX);
border.setBorderWidth(3);
border.setBorderColor(BaseColor.GREEN);

// Add the border to the document
document.add(border);
String imagePath = "C:\\Users\\fadi1\\Desktop\\GestionMateriel\\src\\Gui\\logoequipe.png";
com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance(imagePath);
logo.setAlignment(Element.ALIGN_LEFT);
logo.scaleAbsolute(80f, 80f);
document.add(logo);
    // Add a header to the document
    Paragraph header = new Paragraph("Facture", titleFont);
    header.setAlignment(Element.ALIGN_CENTER);
    document.add(header);
    

    // Add the event name and date to the document
   /* Paragraph eventName = new Paragraph(selectedEvenement.getLibelle(), subtitleFont);
    eventName.setAlignment(Element.ALIGN_CENTER);
    document.add(eventName);**/
 Paragraph LibelleTitle = new Paragraph("Libelle:", subtitleFont);
    LibelleTitle.setAlignment(Element.ALIGN_LEFT);
    document.add(LibelleTitle);

    Paragraph Libelle = new Paragraph(selectedEvenement.getLibelle().toString(), bodyFont);
    Libelle.setAlignment(Element.ALIGN_LEFT);
    document.add(Libelle);

    // Add the event location to the document
    Paragraph eventLocationTitle = new Paragraph("Type:", subtitleFont);
    eventLocationTitle.setAlignment(Element.ALIGN_LEFT);
    document.add(eventLocationTitle);

    Paragraph eventLocation = new Paragraph(selectedEvenement.getType().toString(), bodyFont);
    eventLocation.setAlignment(Element.ALIGN_LEFT);
    document.add(eventLocation);
         Paragraph PrixTitle = new Paragraph("Prix:", subtitleFont);
    PrixTitle.setAlignment(Element.ALIGN_LEFT);
    document.add(PrixTitle);

    Paragraph prix = new Paragraph(String.valueOf(selectedEvenement.getPrix()), bodyFont);
    prix.setAlignment(Element.ALIGN_LEFT);
    document.add(prix);
    Paragraph scan = new Paragraph("Scannez pour plus d'informations ! ");
    // Add the QR code to the document
 // Generate the QR code image
                            String eventData = selectedEvenement.toString();
//                            Image qrCodeImage = generateQRCode(eventData);

                            // Convert the QR code image to a com.itextpdf.text.Image object
ByteArrayOutputStream baos = new ByteArrayOutputStream();
//ImageIO.write(SwingFXUtils.fromFXImage(qrCodeImage, null), "png", baos);
com.itextpdf.text.Image pdfImage = com.itextpdf.text.Image.getInstance(baos.toByteArray());  
pdfImage.setAlignment(com.itextpdf.text.Element.ALIGN_LEFT);
pdfImage.scaleAbsolute(100f, 100f);
document.add(scan);
    document.add(pdfImage);
    

    // Add a footer to the document
    Paragraph footer = new Paragraph("Sauvies vous remercie .", bodyFont);
    footer.setAlignment(Element.ALIGN_CENTER);
    footer.setSpacingBefore(20f);
    document.add(footer);
String imagePath1 = "C:\\Users\\fadi1\\Desktop\\GestionMateriel\\src\\Gui\\Signature.png";
com.itextpdf.text.Image sig = com.itextpdf.text.Image.getInstance(imagePath1);
sig.setAlignment(Element.ALIGN_RIGHT);
sig.scaleAbsolute(60f, 60f);
document.add(sig);
 Paragraph id = new Paragraph("ID Facture: " +selectedEvenement.getId());
    id.setAlignment(Element.ALIGN_LEFT);
    id.setSpacingBefore(10f);
    document.add(id);

    // Set the background color of the document
/*   PdfContentByte canvas = writer.getDirectContent();
    Rectangle rect = new Rectangle(document.getPageSize());
    rect.setBackgroundColor(new BaseColor(214, 214, 214));
    canvas.rectangle(rect);*/



                            
                               
                            } catch (DocumentException ex) {
                                Logger.getLogger(AffichageMaterielController.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(AffichageMaterielController.class.getName()).log(Level.SEVERE, null, ex);
                            }

// Close the Document
                            document.close();

                            if (Desktop.isDesktopSupported()) {
                                Desktop desktop = Desktop.getDesktop();
                                try {
                                    desktop.open(new File("Materiel.pdf"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        });
                        setGraphic(b);
                    }
                }
            };

        });

    
}
}
