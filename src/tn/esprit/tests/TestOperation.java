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
 * @author chahr
 */
public class TestOperation {
    
    public static void main(String[] args) throws SQLException {
           Connection cnx = MaConnexion.getInstance().getCnx();
    System.out.println(cnx);
    
     // Créer un objet OperationsService
  // create a new instance of OperationsService
    OperationsService service = new OperationsService();
    
    // create a new Operations object
    Operations operation = new Operations();
    operation.setDate("2022-06-15");
    operation.setLieu("ssssssssssss");
    operation.setEquipe("Equipe 1");
    operation.setDescription("Operation de la cataracte");
    operation.setImage("cataracte.jpg");
    
    // create a new Medecin object
    Medecin medecin = new Medecin();
    medecin.setNom_med("Dr. Ahmed Ben Salah");
    
    // set the Medecin object in the Operations object
    operation.setM(medecin);
    
    // call the ajouter method to add the operation to the database
    service.ajouter(operation);
    System.out.println(operation);
    
    
    // Create a new instance of Operations with the ID of the operation you want to delete
    Operations operationToDelete = new Operations();
    operationToDelete.setId(34);

    // Call the supprimer method to delete the operation from the database
    service.supprimer(operationToDelete);

    
// create an instance of Operations with the id of the operation you want to modify
Operations operationToModify = new Operations();
operationToModify.setId(36);

// set the new values for the operation
operationToModify.setDate("2023-04-06");
operationToModify.setLieu("mmmmmmm");
operationToModify.setEquipe("Team B");
operationToModify.setDescription("This is a modified description.");
operationToModify.setImage("new_image.jpg");

// call the modifier method to update the operation
service.modifier(operationToModify);
System.out.println(operationToModify);
}
}