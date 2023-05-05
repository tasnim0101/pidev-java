/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Materiel;

import Materiel.entities.Materiel;
import Materiel.entities.Fournisseur;
import Services.Materiel.MaterielService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import util.dbconnection;

/**
 * FXML Controller class
 *
 * @author fadi1
 */
public class EditMaterielController implements Initializable {
   @FXML
    private TextField tfEventEditNom;
    @FXML
    private TextArea tfEventEditDesc;
    @FXML
    private TextField tfEventEditLieu;

    @FXML
    private TextField tfEventEditNbP;
    @FXML
    private Button showEventsBT;
    @FXML
    private Button EventEditButton;
    @FXML
    private ImageView eventEditImg;
    @FXML
    private Button eventEditChoose;
    private File file; 
    private String lien="";
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @FXML
    private ComboBox<Fournisseur> cbEventEdit;
    @FXML
    private Button EventEditCancel;
   Connection cnx = dbconnection.getInstance().getConnection();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       List<Fournisseur> fournisseurs = new ArrayList<>();
        String s = "select * from fournisseur";
         try {
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Fournisseur e = new Fournisseur();
            e.setNom(rs.getString("nom"));
           
            e.setId(rs.getInt("id"));
            fournisseurs.add(e);
            
        }
         } catch (SQLException ex) {
        Logger.getLogger(AddMaterielController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         cbEventEdit.setItems(FXCollections.observableArrayList(fournisseurs));
cbEventEdit.setConverter(new StringConverter<Fournisseur>() {
        @Override
    public String toString(Fournisseur fournisseur) {
        return fournisseur.getNom();
    }
    @Override
    public Fournisseur fromString(String nom) {
        return null; // Ne pas utiliser pour la conversion vers une chaîne de caractères
    }
        });


        ///////number validator//////////
        
       
        ////////price validator//////////
        
       /* UnaryOperator<TextFormatter.Change> filter = new UnaryOperator<TextFormatter.Change>() {

            @Override
            public TextFormatter.Change apply(TextFormatter.Change t) {

                if (t.isReplaced()) 
                    if(t.getText().matches("[^0-9]"))
                        t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));
                
                if (t.isAdded()) {
                    if (t.getControlText().contains(".")) {
                        if (t.getText().matches("[^0-9]")) {
                            t.setText("");
                        }
                    } else if (t.getText().matches("[^0-9.]")) {
                        t.setText("");
                    }
                }
                
                return t;
            }
        };
        tfEventEditPrix.setTextFormatter(new TextFormatter<>(filter));
    }    */
}    

    public void init(Materiel e){
        tfEventEditNom.setText(e.getLibelle());
        
        tfEventEditLieu.setText(e.getType());
       
        tfEventEditNbP.setText(String.valueOf(e.getPrix()));
       
        id = e.getId();
    }
    
    @FXML
    private void ModifierMateriel(ActionEvent event) throws IOException {
        
        if(ValidateEmptyForm(tfEventEditNom,tfEventEditLieu,tfEventEditNbP)
             && ValidateName(tfEventEditNom))
        {
            int fournisseur =cbEventEdit.getSelectionModel().getSelectedItem().getId();
            MaterielService se = new MaterielService();
             Materiel p = new Materiel();
            p.setLibelle(tfEventEditNom.getText());
            p.setType(tfEventEditLieu.getText());
            p.setPrix(Integer.parseInt(tfEventEditNbP.getText()));
            
            p.setFournisseur_id(fournisseur);
            p.setId(id);
       
            se.modifier(p);

            JOptionPane.showMessageDialog(null, "Materiel modifié !");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageMateriel.fxml"));
        AddMaterielController aec = loader.getController();
        Parent root = loader.load();
        EventEditButton.getScene().setRoot(root);
            
           

           
        
    }
    }

    

    @FXML
    public void showMateriel (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("affichageMateriel.fxml"));
        AddMaterielController aec = loader.getController();
        Parent root = loader.load();
        showEventsBT.getScene().setRoot(root);
    }
    
    
     private boolean ValidateEmptyForm(TextField nom, TextField lieu,TextField nbp){
         if (nom.getText().equals("")  || lieu.getText().equals("") || 
                nbp.getText().equals("")  ) {
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText("Veuillez remplir tous les champs");
             alert.showAndWait();
             
             return false;  
        } else {
             return true;  
         }
     }
    
    private boolean ValidateName(TextField t){
         Pattern p = Pattern.compile("[a-zA-Z]+");
         Matcher m = p.matcher(t.getText());
         if (m.find() && m.group().equals(t.getText())){
             return true;
             
         }else{
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Erreur");
             alert.setHeaderText(null);
             alert.setContentText(t.getText()+" : nom non valide");
             alert.showAndWait();
             
             return false;
         }
     }
    
   
    
}
