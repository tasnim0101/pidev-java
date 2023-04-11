/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Tasnim
 */
public interface IGestionService<T> {
     void ajouter(T t)throws SQLException;
     List<T> afficher();
     void supprimer(T t);
    void  modifier(T t);
}
