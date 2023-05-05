/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Materiel;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author sarah
 */
public interface IServiceFournisseur<T> {
         public void ajouter(T t);
    public List<T> afficher() throws SQLException;
    public void modifier (T t)throws SQLException;
    public boolean supprimer(T t)throws SQLException;
    
}
