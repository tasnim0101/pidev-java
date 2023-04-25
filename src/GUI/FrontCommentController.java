/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.comment;
import Entity.reply;
import Services.commentService;
import Services.replyService;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Maryem
 */
public class FrontCommentController implements Initializable {

    @FXML
    private ScrollPane commentsScrollPane;
    @FXML
    private VBox commentsVBox;
    @FXML
    private TextField auteur;
    @FXML
    private TextField mail;
    @FXML
    private TextField comment;
    @FXML
    private Label date;
    @FXML
    private Button save;

    /**
     * Initializes the controller class.
     */
    
     private commentService cc = new commentService();
    private replyService rr = new replyService();
    
    public void displayComments() {

        List<comment> commentsList = cc.afficher();
        commentsVBox.getChildren().clear();

        for (comment comment : commentsList) {

            VBox commentVBox = new VBox();
            commentVBox.setPadding(new Insets(10));
            commentVBox.setSpacing(10);

            Label commentTextLabel = new Label("Commentaire de " + comment.getAuteur() +" le "+comment.getDate_c().toString()+" :");
            Label commentLabel = new Label(comment.getComment());

            commentVBox.getChildren().add(commentTextLabel);
            commentVBox.getChildren().add(commentLabel);
            commentLabel.setStyle("-fx-font-weight: bold;");

            System.out.println(comment.toString());

            reply r = rr.id_reply(comment.getId());
            if (r != null) {
                VBox replyVBox = new VBox();
                replyVBox.setPadding(new Insets(10));
                replyVBox.setSpacing(5);

                Label replyTextLabel = new Label("réponse : "+r.getReponse());
                replyTextLabel.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                replyVBox.getChildren().add(replyTextLabel);

                commentVBox.getChildren().add(replyVBox);
            } else {
                Label noReplyLabel = new Label("Nous vous répondrons bientôt :) ");
                noReplyLabel.setStyle("-fx-background-color: red; -fx-text-fill: white;");

                commentVBox.getChildren().add(noReplyLabel);
            }

            commentsVBox.getChildren().add(commentVBox);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalDate currentDate = LocalDate.now();
        date.setText(currentDate.toString());
        displayComments();
    }    

    @FXML
    private void addComment(ActionEvent event) {
        
        
        
        
    }
    
    public void effacer() {
        labelNom.setText("");
        labelR.setText("");
        commentErreur.setText("");
        nomErreur.setText("");
        reponseErreur.setText("");
        coment_box.setValue(null);
        displayComments();
        

    }

    
    
    
    
}
