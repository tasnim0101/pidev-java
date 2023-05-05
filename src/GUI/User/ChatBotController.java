/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI.User;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import integration.Integration;

/**
 *
 * @author Souid
 */
public class ChatBotController implements Initializable{
      @FXML
    private Label lblTitle;

    @FXML
    private TextField txtInput;

    @FXML
    private Button btnSend;

    @FXML
    private Label lblOutput;

     @Override
    public void initialize(URL url, ResourceBundle rb){
        // Set button action
        btnSend.setOnAction(event -> {
            String input = txtInput.getText();
            System.out.println("input"+input);
            String response = null;
            try {
                response = getResponse(input);
            } catch (IOException ex) {
                Logger.getLogger(ChatBotController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(response);
            lblOutput.setText(response);
            txtInput.clear();
        });
    }
    
    private static final String[] RESPONSES = {
            "Hello",
            "How are you?",
            "Nice to meet you!",
            "What can I help you with today?",
            "I'm sorry, I don't understand. Can you please rephrase your question?"
    };

   

    private String getResponse(String input) throws IOException {
        // Loop through responses and return a random one

        for (String response : RESPONSES) {
            if (input.toLowerCase().contains("salut")) {
                return "salut comment je peux t aider aujourd'hui ?";
            }
            if (input.toLowerCase().contains("forgot my password")) {
                Integration m = new Integration() ;
         m.changeScene("/GUI/User/ResetPassword.fxml");
            }
          
            if (input.toLowerCase().contains("s") || input.toLowerCase().contains("sante") || input.toLowerCase().contains("maladie") ) {
                return "Les cosignes doivent être remises au surveillant de garde qui doit les remettre au service désigné \n" + " par l’administration pour gérer les consignes en fin de la séance de garde selon le cas.\n "
                        + "Les cosignes sont remises à l’intendant(e) ou au surveillant du service \n" + " ou séjourne le malade, qui doivent les remettre au service désigné par l’administration pour gérer les consignes";
            }
             if (input.toLowerCase().contains("j'ai des questions")) {
                return "je ferai mon mieux pour vous aider";
            }
            
            if (input.toLowerCase().contains(input.toLowerCase())) {
                return response;
            }
            if (input.toLowerCase().contains(input.toLowerCase())) {
                return response;
            }
        }
        return RESPONSES[RESPONSES.length];
    }
    
     @FXML
    private void back()throws IOException {
        Integration m = new Integration() ;
         m.changeScene("/GUI/back.fxml");
        
    }
}
