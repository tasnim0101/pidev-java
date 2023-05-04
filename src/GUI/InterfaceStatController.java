/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import Entity.Operations;
import Services.OperationsService;

/**
 * FXML Controller class
 *
 * @author chahr
 */
public class InterfaceStatController implements Initializable {

    @FXML
    private PieChart PieChart;

    /**
     * Initializes the controller class.
     */
 
    
    @Override
public void initialize(URL url, ResourceBundle rb) {
    // Récupérer les données des opérations depuis la base de données
    OperationsService op=new OperationsService();
    List<Operations> operations = op.afficher();
                  //  op.showNotification("Les statistiques sont affichées", "voici notre pie chart des equipes avec les operations");

    // Calculer le nombre d'opérations réalisées par chaque équipe
    Map<String, Integer> operationsByEquipe = new HashMap<>();
    for (Operations operation : operations) {
        String equipe = operation.getEquipe();
        if (operationsByEquipe.containsKey(equipe)) {
            operationsByEquipe.put(equipe, operationsByEquipe.get(equipe) + 1);
        } else {
            operationsByEquipe.put(equipe, 1); 
        }
    }

    // Ajouter les données au PieChart
    for (Map.Entry<String, Integer> entry : operationsByEquipe.entrySet()) {
        PieChart.Data slice = new PieChart.Data(entry.getKey(), entry.getValue());
        PieChart.getData().add(slice);
    }
}

    @FXML
    private void btn_retour(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("Votre Profil");
                stage.show();
    }
    
}
