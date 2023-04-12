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
 * @author Fayechi
 */
public interface NewInterface<T> {
    public void ajouter(T t)throws SQLException;
    public List<T> afficher();
    public void supprimer(T t);
    
}
