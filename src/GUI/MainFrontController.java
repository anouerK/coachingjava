/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafxpi.JavafxPi;

/**
 * FXML Controller class
 *
 * @author ksaay
 */
public class MainFrontController implements Initializable {

    @FXML
    private ImageView coachb;
    @FXML
    private ImageView progb;
    @FXML
    private AnchorPane panev;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        URL fileUrl = JavafxPi.class.getResource("/GUI/CoachFront.fxml");
        try {
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException(" Fxml file not found ");
            }
            else
            {
                   FXMLLoader loader = new FXMLLoader(fileUrl);
            Pane pane = loader.load();
            pane.setPrefSize(700, 700);
            pane.setMaxHeight(700);
            pane.setMaxWidth(700);
                 
            panev.getChildren().setAll(pane);
            }
        }
          catch(Exception e ){
System.out.println("no file found");
    }
    }    

    @FXML
    private void coachv(MouseEvent event) {
    }

    @FXML
    private void progv(MouseEvent event) {
    }
    
}
