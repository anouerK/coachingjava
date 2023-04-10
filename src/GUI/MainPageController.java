/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafxpi.JavafxPi;

/**
 * FXML Controller class
 *
 * @author ksaay
 */
public class MainPageController implements Initializable {

    @FXML
    private Button coach;
    @FXML
    private Button prog;
    @FXML
    private Pane view;
    @FXML
    private Button produit;
    @FXML
    private Button livreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void coachview(ActionEvent event) throws FileNotFoundException {
        URL fileUrl = JavafxPi.class.getResource("/GUI/MainCoach.fxml");
        try {
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException(" Fxml file not found ");
            }
            else
            {
                   FXMLLoader loader = new FXMLLoader(fileUrl);
            Pane pane = loader.load();
            view.getChildren().setAll(pane);
            }
        }
        
       
    
    catch(Exception e ){
System.out.println("no file found");
    }
}

@FXML
private void progview(ActionEvent event) {
     URL fileUrl = JavafxPi.class.getResource("/GUI/MainProgramme.fxml");
        try {
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException(" Fxml file not found ");
            }
            else
            {
                   FXMLLoader loader = new FXMLLoader(fileUrl);
            Pane pane = loader.load();
            view.getChildren().setAll(pane);
            }
        }
        
       
    
    catch(Exception e ){
System.out.println("no file found");
    }
    }

    @FXML
    private void produitview(ActionEvent event) {
        
             URL fileUrl = JavafxPi.class.getResource("/GUI/MainProduit.fxml");
        try {
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException(" Fxml file not found ");
            }
            else
            {
                   FXMLLoader loader = new FXMLLoader(fileUrl);
            Pane pane = loader.load();
            view.getChildren().setAll(pane);
            }
        }
        
       
    
    catch(Exception e ){
System.out.println("no file found");
    }
    }

    @FXML
    private void livreurview(ActionEvent event) {
               URL fileUrl = JavafxPi.class.getResource("/GUI/MainLivreur.fxml");
        try {
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException(" Fxml file not found ");
            }
            else
            {
                   FXMLLoader loader = new FXMLLoader(fileUrl);
            Pane pane = loader.load();
            view.getChildren().setAll(pane);
            }
        }
        
       
    
    catch(Exception e ){
System.out.println("no file found");
    }
        
    }
    
}
