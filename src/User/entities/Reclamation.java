/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User.entities;
/**
 *
 * @author Asus store
 */
public class Reclamation {
      private int id;
     private String message;
     private User users_id;
     
     
   
     
     
     public Reclamation(){}
     public Reclamation(int id,String message){
         this.id=id;
         this.message=message;
     }
     public Reclamation(int id,String message, User user){
         this.id=id;
         this.message=message;
         this.users_id=user;
     }
     
     public Reclamation(String message, User user){
         this.message=message;
         this.users_id=user;
     }
      public Reclamation(String message){
         this.message=message;
     }
  /*    public Reclamation(int id,String message,int users_id){
          this.id=id;
         this.message=message;
         this.user=users_id;
     }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return users_id;
    }

    public void setUser(User user_id) {
        this.users_id = user_id;
    }

    

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", message=" + message + ", user=" + users_id + '}';
    }

  

   
     
    
}
