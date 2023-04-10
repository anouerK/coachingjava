/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package first_sprint_crud.services;


import first_sprint_crud.entities.Programme;

import first_sprint_crud.util.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class ProgrammeService implements IService<Programme> {

    Connection cnx;

    public ProgrammeService() {
        cnx = MyDB.getInstance().getConnection();
    }
    
    @Override
    public void ajouter(Programme p) {
        try {
   String req = "insert into programme(nom,type,media,dure,likes)"
   + "values( '" + p.getNom()+ "' ,   '" + p.getType()+  "', '" + p.getMedia()+  "', '" + p.getDure()+  "', '" + p.getLikes()+  "' )";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Programme Added");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

    @Override
    public void modifier(Programme p) {
        try {
        // Create the SQL query string with placeholders for the parameters
        String sql = "UPDATE programme SET nom = ?, type = ?, media = ? , dure = ?, likes = ?  WHERE id = ?";

        // Create a prepared statement with the SQL query string
        PreparedStatement ps = cnx.prepareStatement(sql);

        // Set the parameters for the prepared statement
        ps.setString(1, p.getNom());
        ps.setString(2, p.getType());
        ps.setString(3, p.getMedia());
        ps.setInt(4, p.getDure());
        ps.setInt(5, p.getLikes());
        ps.setInt(6, p.getId());
        
          ps.executeUpdate();
            System.out.println("Programme Updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

    }
    }

    @Override
    public void supprimer(int id) {
         try {
            String req = "delete from programme  where id=" + " '"+ id + "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();


            System.out.println("Programme deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List recuperer() {
         List<Programme> programmes = new ArrayList<>();
    try {
        
    
      
        String sql = "SELECT * FROM programme";

       
        Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);

   
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String type = rs.getString("type");
            String media = rs.getString("media");
            media = MyDB.url_upload + media;
            int dure = rs.getInt("dure");
            int likes = rs.getInt("likes");
          System.out.println(media);

            Programme programme = new Programme(id,nom,type,media,dure,likes);
            programmes.add(programme);
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des programmes : " + ex.getMessage());
    }
    return programmes;
    }
    
    public Programme recupererById(int idF) {
         Programme  programme = null;
    try {
        
        String sql = "SELECT * FROM programme where id=" + " '"+ idF + "'  ";
     Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);

   
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String type = rs.getString("type");
            String media = rs.getString("media");
            int dure = rs.getInt("dure");
            int likes = rs.getInt("likes");
          

             programme = new Programme(id,nom,type,media,dure,likes);
            
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des programmes : " + ex.getMessage());
    }
    return programme;
    }
    
    public List recupererNomTypeOrderByLikes(String nomF , String typeF ,int p) {
        List<Programme> programmes = new ArrayList<>();
        String sql="SELECT * FROM programme";
        
    try {
           if(nomF!="" && typeF=="" && p==1)
      {
          sql = "SELECT * FROM programme where nom  LIKE '" + "%" +nomF+ "%" +"'order by likes ";
      }
       
        if(nomF!="" && typeF=="" && p==2)
      {
          sql = "SELECT * FROM programme where nom  LIKE '" + "%" +nomF+ "%" +"'order by likes desc ";
      }
        
         if(nomF!="" && typeF=="" && p==0)
      {
          sql = "SELECT * FROM programme where nom  LIKE '" + "%" +nomF+ "%" +"' ";
      }
         
         
         if(nomF=="" && typeF!="" && p==1)
      {
          sql = "SELECT * FROM programme where type  LIKE '" + "%" +typeF+ "%" +"'order by likes ";
      }
       
        if(nomF=="" && typeF!="" && p==2)
      {
          sql = "SELECT * FROM programme where type  LIKE '" + "%" +typeF+ "%" +"'order by likes desc ";
      }
        
         if(nomF=="" && typeF!="" && p==0)
      {
          sql = "SELECT * FROM programme where type  LIKE '" + "%" +typeF+ "%" +"' ";
      }
         
         
         
         if(nomF!="" && typeF!="" && p==1)
      {
          sql = "SELECT * FROM programme where nom  LIKE '" + "%" +nomF+ "%" + "' and type like '"  + "%" +typeF+ "%" +"'order by likes ";
      }
       
        if(nomF!="" && typeF!="" && p==2)
      {
          sql = "SELECT * FROM programme where nom  LIKE '" + "%" +nomF+ "%" + "' and type like '"  + "%" +typeF+ "%" +"'order by likes desc ";
      }
        
         if(nomF!="" && typeF!="" && p==0)
      {
          sql = "SELECT * FROM programme where nom  LIKE '" + "%" +nomF+ "%" + "' and type like '"  + "%" +typeF+ "%" +"' ";
      }
         
         
        
         if(nomF=="" && typeF=="" && p==1)
      {
         sql = "SELECT * FROM programme order by likes";
      }
         
            if(nomF=="" && typeF=="" && p==2)
      {
         sql = "SELECT * FROM programme order by likes desc";
      }
     Statement stmt = cnx.createStatement();

       
        ResultSet rs = stmt.executeQuery(sql);

   
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String type = rs.getString("type");
            String media = rs.getString("media");
            int dure = rs.getInt("dure");
            int likes = rs.getInt("likes");
          

            Programme programme = new Programme(id,nom,type,media,dure,likes);
            programmes.add(programme);
        }
        } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération des programmes : " + ex.getMessage());
    }
    return programmes;
    }
    
}
