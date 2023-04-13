/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus store
 */
public class AfficherPersonneController implements Initializable {

    @FXML
    private TextField txtnomA;
    @FXML
    private TextField txtprenomA;
    @FXML
    private TextField txtliste;

    public TextField getTxtnomA() {
        return txtnomA;
    }

    public void setTxtnomA(String txtnomA) {
        this.txtnomA.setText(txtnomA);
    }

    public TextField getTxtprenomA() {
        return txtprenomA;
    }

    public void setTxtprenomA(String txtprenomA) {
        this.txtprenomA.setText(txtprenomA);
    }

    public TextField getTxtliste() {
        return txtliste;
    }

    public void setTxtliste(String txtliste) {
        this.txtliste.setText(txtliste);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
