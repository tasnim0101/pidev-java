/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import tn.esprit.entities.Medecin;
import tn.esprit.services.MedecinService;
import tn.esprit.entities.Operations;
import tn.esprit.services.OperationsService;

import tn.esprit.tools.MaConnexion;

/**
 *
 * @author Fayechi
 */
public class TestMedecin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Connection cnx = MaConnexion.getInstance().getCnx();
    System.out.println(cnx);
// Creating an instance of MedecinService
MedecinService medecinService = new MedecinService();

// Creating a new Medecin object to add
Medecin medecinToAdd = new Medecin("llllllllll", "Hamdi", 123456789, "chahra@example.com", "Cardiologie", "MD", "chahera.jpg");

try {
    // Adding the new Medecin to the database
    medecinService.ajouter(medecinToAdd);
    System.out.println("New Medecin added successfully!");
} catch (SQLException ex) {
    System.out.println("Error while adding new Medecin: " + ex.getMessage());
}

// Retrieving all the Medecin objects from the database and displaying them
  System.out.println("-------------------------------------------------------------------------------");
   System.out.println("Affichage AVANT la Suppression/Modification");
List<Medecin> allMedecins = medecinService.afficher();
for (Medecin medecin : allMedecins) {
    System.out.println(medecin.toString());
 
}
  System.out.println("-------------------------------------------------------------------------------");

    // Create a new instance of Operations with the ID of the operation you want to delete
    Medecin MedecinToDelete = new Medecin();
    MedecinToDelete.setId(56);

    // Call the supprimer method to delete the operation from the database
    medecinService.supprimer(MedecinToDelete);
// Creating a new Medecin object to modify
Medecin medecinToModify = new Medecin(23, "riimmm", "Hamdi2", 1234567, "chahra2@example.com", "Cardiologie2", "MD2", "chahera2.jpg");

// Modifying the Medecin in the database using the modifier method
medecinService.modifier(medecinToModify);
// Retrieving all the remaining Medecin objects from the database and displaying them
List<Medecin> remainingMedecins = medecinService.afficher();
for (Medecin medecin : remainingMedecins) {
    System.out.println(medecin);

}
  System.out.println("-------------------------------------------------------------------------------");
    System.out.println("Affichage APRÈS la Suppression/Modification");
      System.out.println("-------------------------------------------------------------------------------");
    }


    
    
}
