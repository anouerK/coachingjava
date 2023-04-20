/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;


import first_sprint_crud.entities.Reclamation;
import first_sprint_crud.entities.Reponse;


import first_sprint_crud.services.ReclamationService;
import first_sprint_crud.services.ReponseService;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author arij
 */
public class MainReclamationController implements Initializable {

    @FXML
    private TextField searchtxt;
    @FXML
    private VBox ChosenProdCard;
    @FXML
    private TextField sujet;
    @FXML
    private TextArea desc;
    @FXML
    private Button Add;
    @FXML
    private Label prodvide;
    @FXML
    private GridPane grid;
    
    ReclamationService psrec = new ReclamationService(); 
    ReponseService psrep = new ReponseService();
    @FXML
    private Button ret;
    private Stage stage;
    private Scene scene;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (!psrec.recuperer().isEmpty())
        {
            prodvide.setText("Reclamation vide");
             
        }
            else
         prodvide.setText("Reclamation:");
        
         show();
    } 
    
    
     public void show()
    {
        int column=0;
        int row=1;
        int x=0;
       
         try {
        for(Reclamation pan : psrec.recuperer())
        {
            
           
                FXMLLoader fxmlloader = new  FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("MainReclamationItem.fxml"));
                AnchorPane pane = fxmlloader.load();
                 
               MainReclamationItemController items = fxmlloader.getController();
                items.setData(pan);
                
                if(column == 1 )
                {
                    column = 0;
                    row++;
                }
                
                grid.add(pane,column++,row);
                
                //width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                
                //height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                
                
                
                GridPane.setMargin(pane, new Insets(10));
    
        }
        } catch (IOException ex) {
                Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }

    @FXML
    private void searchtxtaction2(KeyEvent event) {
    }

    @FXML
    private void searchtxtaction(ActionEvent event) {
    }

    @FXML
    private void AddToCart(ActionEvent event) {
             if (sujet.getText().isEmpty() || desc.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Add Error");
            alert.setContentText("all fields must  not be empty !");

            alert.showAndWait();
        } else{
            
           
            
        
            Reclamation r = new Reclamation(sujet.getText(),desc.getText());
            psrec.ajouter(r);
            sujet.setText("");
            desc.setText("");
            
            
            show();
            
        }
    }

    @FXML
    private void ret(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
    }
    
}
