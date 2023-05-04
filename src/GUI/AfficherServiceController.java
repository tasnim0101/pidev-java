/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hamdi
 */
public class AfficherServiceController implements Initializable {

    @FXML
    private TextField txtid;
    @FXML
    private TextField txttype;
    @FXML
    private TextField txtdescription;
    @FXML
    private TextField tctchef;
    @FXML
    private TextField txtprix;
    @FXML
    private TableView<?> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public TextField getTxtid() {
        return txtid;
    }

  
    public TextField getTxttype() {
        return txttype;
    }

    public void setTxttype(String  txttype) {
        this.txttype.setText(txttype);
    }

    public TextField getTxtdescription() {
        return txtdescription;
    }

    public void setTxtdescription(String txtdescription) {
        this.txtdescription.setText(txtdescription);
    }

    public TextField getTctchef() {
        return tctchef;
    }

    public void setTctchef(String  tctchef) {
        this.tctchef.setText(tctchef);
    }

    public TextField getTxtprix() {
        return txtprix;
    }

   public void setTxtid(int id) {
   this.txtid.setText(Integer.toString(id));
}

public void setTxtprix(Double prix) {
   this.txtprix.setText(Double.toString(prix));
}
   

    public TableView<?> getList() {
        return list;
    }

    public void setList(TableView<?> list) {
        this.list = list;
    }
    
}
