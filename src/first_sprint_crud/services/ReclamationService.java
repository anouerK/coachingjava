/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.services;


import first_sprint_crud.entities.Reclamation;

import first_sprint_crud.util.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 *
 * @author USER
 */
public class ReclamationService implements IService<Reclamation> {
    Connection cnx;

    public ReclamationService() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Reclamation r) {
              DateTimeFormatter date =   DateTimeFormatter.ofPattern("yyyy-MM-dd");
              LocalDateTime now =  LocalDateTime.now();
              
               try {
   String req = "insert into reclamation(date_rec,sujet,description)"
   + "values( '" + date.format(now) + "' ,   '" + r.getSujet()+  "', '" + r.getDescription()+ "' )";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reclamation Added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    
    }

    @Override
    public void modifier(Reclamation r) {
 Reclamation  reclamation = null;
    try {
        
        String sql1 = "SELECT * FROM reclamation where id=" + " '"+ r.getId() + "'  ";
         Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql1);

   
        while (rs.next()) {
            int id = rs.getInt("id");
            Date date_rec = rs.getDate("date_rec");
            String sujet = rs.getString("sujet");
            String description = rs.getString("description");
            
              
           
                   
        DateTimeFormatter date =   DateTimeFormatter.ofPattern("yyyy-MM-dd");
          LocalDate now =  LocalDate.parse(date_rec.toString());
                   
        // Create the SQL query string with placeholders for the parameters
        String sql = "UPDATE reclamation SET date_rec = ?, sujet = ?, description = ?  WHERE id = ?";

        // Create a prepared statement with the SQL query string
        PreparedStatement ps = cnx.prepareStatement(sql);

        // Set the parameters for the prepared statement
         
        ps.setString(1, date.format(now));
        ps.setString(2, r.getSujet());
        ps.setString(3, r.getDescription());
        ps.setInt(4, r.getId());
        
          ps.executeUpdate();
            System.out.println("Reclamation Updated");
               
    }
            
            
        } catch (SQLException ex) {
        System.out.println("Erreur  " + ex.getMessage());
    }
    
    }
    
    

    @Override
    public void supprimer(int id) {
try {
            String req = "delete from reclamation  where id=" + " '"+ id + "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();
         
          
            System.out.println("Reclamation deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }      }

    @Override
    public List<Reclamation> recuperer() {
return null;
    }
}
