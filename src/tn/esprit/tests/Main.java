package tn.esprit.tests;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entities.Hospitalisation;
import tn.esprit.entities.Services;
import tn.esprit.services.GestionHospitalisation;
import tn.esprit.services.GestionService;
import tn.esprit.tools.MaConnexion;

public class Main {

    public static void main(String[] args) throws SQLException {

  
            MaConnexion cnx = MaConnexion.getInstance();

            System.out.println(cnx);
GestionHospitalisation gestionHospitalisation = new GestionHospitalisation();



Hospitalisation h = new Hospitalisation();
h.setId(1);
h.setDate_entree(Date.valueOf("2022-01-01"));
h.setDate_sortie(Date.valueOf("2022-01-05"));
h.setId_hospitalisation(12345);

Services service = new Services();
service.setId(1);
service.setType("Cardiology");

h.setS(service);

System.out.println(h);
                 
    }
}
