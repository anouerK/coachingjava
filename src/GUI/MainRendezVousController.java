/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import first_sprint_crud.entities.Coach;
import first_sprint_crud.entities.RendezVous;


import first_sprint_crud.services.RendezvousService;
import first_sprint_crud.services.CoachService;
import java.io.IOException;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MainRendezVousController implements Initializable {

    @FXML
    private TextField searchtxt;
    @FXML
    private VBox ChosenProdCard;
    @FXML
    private Button Add;
    @FXML
    private Label prodvide;
    @FXML
    private GridPane grid;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenomm;
    @FXML
    private ComboBox<String> coach;
    @FXML
    private TextField contact;
    
RendezvousService psm = new RendezvousService();
CoachService psc = new CoachService();

 ObservableMap<String, Coach> coachMap = FXCollections.observableHashMap();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         if (psm.recuperer().size()==0)
        {
            prodvide.setText("Rendez Vous vide");
             
        }
            else
         prodvide.setText("Rendez Vous:");
         
      
for (Coach c : psc.recuperer()) {
    String key = c.getNom() + " " + c.getPrenom();
    coachMap.put(key, c);
}
coach.setItems(FXCollections.observableArrayList(coachMap.keySet()));
            
        show();
    }   
    
    
    public void show()
    {
        int column=0;
        int row=1;
        int x=0;
       
         try {
        for(RendezVous pan : psm.recuperer())
        {
           
                FXMLLoader fxmlloader = new  FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("MainRendezVousItem.fxml"));
                AnchorPane pane = fxmlloader.load();
                 
                MainRendezVousItemController items = fxmlloader.getController();
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
        
        if (nom.getText().isEmpty() || prenomm.getText().isEmpty() || coach.getSelectionModel().getSelectedItem() == null || contact.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Add Error");
            alert.setContentText("all fields must  not be empty !");

            alert.showAndWait();
        } else{
            
            String selectedCoach = coach.getSelectionModel().getSelectedItem();
            Coach c = coachMap.get(selectedCoach);
            
        
            RendezVous r = new RendezVous(nom.getText(),prenomm.getText(),c,contact.getText());
            psm.ajouter(r);
            nom.setText("");
            prenomm.setText("");
            contact.setText("");
            
            show();
            
        }
        
    }

    @FXML
    private void coachcomboaction(ActionEvent event) {
    }
    
}
