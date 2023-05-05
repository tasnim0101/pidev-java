/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import Entity.Hospitalisation;
import Entity.Services;
import User.entities.User;
import tools.MaConnexion;
import javax.mail.PasswordAuthentication;


/**
 *
 * @author hamdi
 */
public class GestionHospitalisation implements IGestionHospital<Hospitalisation>{

     Connection cnx;
    String sql="";

    public GestionHospitalisation() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    

@Override
public void ajouter(Hospitalisation h) throws SQLException {
    String sql = "INSERT INTO hospitalisation (date_entree, date_sortie, id_hospitalisation, service_id,user)VALUES(?,?,?,?,?)";
    PreparedStatement statement = cnx.prepareStatement(sql);
    statement.setDate(1, new java.sql.Date(h.getDate_entree().getTime()));
    statement.setDate(2, new java.sql.Date(h.getDate_sortie().getTime()));
    statement.setInt(3, h.getId_hospitalisation());
    statement.setInt(4, h.getS().getId());
    statement.setInt(5, h.getU().getId());
    
    statement.executeUpdate();
    System.out.println("Hospitalisation ajoutée !");
}




    

@Override
public List<Hospitalisation> afficher() {
    List<Hospitalisation> hospitalisation = new ArrayList<>();
    try {
        sql = "SELECT h.id, h.date_entree, h.date_sortie, h.id_hospitalisation, s.id AS service_id, s.type AS type, u.id AS user\n" +
              "FROM hospitalisation h\n" +
              "INNER JOIN services s ON h.service_id = s.id\n" +
              "INNER JOIN users u ON h.user = u.id";
        Statement ste = cnx.createStatement();
        ResultSet rs = ste.executeQuery(sql);
        while (rs.next()) {
            Hospitalisation hos = new Hospitalisation();
            hos.setId(rs.getInt("id"));
            hos.setDate_entree(rs.getDate("date_entree"));
            hos.setDate_sortie(rs.getDate("date_sortie"));
            hos.setId_hospitalisation(rs.getInt("id_hospitalisation"));
            Services service = new Services();
            service.setId(rs.getInt("service_id"));
            service.setType(rs.getString("type"));
            hos.setS(service);
            User user = new User();
            user.setId(rs.getInt("user"));
            
            hos.setU(user);
            hospitalisation.add(hos);
        }
        System.out.println("Liste des hospitalisations :");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return hospitalisation;
}



    @Override
    public void supprimer(Hospitalisation t) {
         try {
             sql = "DELETE FROM hospitalisation WHERE id = ?";
             PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setInt(1, t.getId());
             ste.executeUpdate(); 
             System.out.println("Hospitalisation supprimé !");
         } catch (SQLException ex) {
             Logger.getLogger(GestionHospitalisation.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void modifier(Hospitalisation t) {
         try {
             sql = "UPDATE hospitalisation SET date_entree = ?, date_sortie = ?, id_hospitalisation  = ? WHERE id = ?";
             PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setDate(1, t.getDate_entree());
             ste.setDate(2, t.getDate_sortie());
             ste.setInt(3, t.getId_hospitalisation());
             //ste.setInt(4, t.getS().getId());
             ste.setInt(4, t.getId());
             ste.executeUpdate();
             System.out.println("Hospitalisation modifié !");
         } catch (SQLException ex) {
             Logger.getLogger(GestionHospitalisation.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    

    //qcimysrwmixugsbk

  
private String Mail = "hamditasnim416@gmail.com";
private String Password = "qcimysrwmixugsbk";

public void envoyer(String recepient,String subj,String textmsg) {
Properties properies = new Properties();
properies.put("mail.smtp.host", "smtp.gmail.com");
properies.put("mail.smtp.port", "465");
properies.put("mail.smtp.auth", "true");
properies.put("mail.smtp.starttls.enable", "true");
properies.put("mail.smtp.starttls.required", "true");
properies.put("mail.smtp.ssl.protocols", "TLSv1.2");
properies.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
Session session = Session.getInstance(properies,
new javax.mail.Authenticator() {
    
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(Mail, Password);
}
});
try {
Message message = new MimeMessage(session);
message.setFrom(new InternetAddress("hamditasnim416@gmail.com"));
message.setRecipients(Message.RecipientType.TO,
InternetAddress.parse(recepient));
message.setSubject(subj);
message.setText(textmsg);
// Etape 3 : Envoyer le message
Transport.send(message);
System.out.println("Message_envoye");
} catch (MessagingException e) {
      System.out.println("error" + e.toString());
} } 
}
