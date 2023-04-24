/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import first_sprint_crud.entities.FavoriteProgrammes;
import first_sprint_crud.entities.Programme;
import first_sprint_crud.services.CoachService;
import first_sprint_crud.services.FavoriteProgrammesService;
import first_sprint_crud.services.ProgrammeService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
/**
 * FXML Controller class
 *
 * @author ksaay
 */
public class ProgFrontController implements Initializable {

    @FXML
    private TextField searchb;
    @FXML
    private ScrollPane panev;
     private Timeline searchTimeline;
     ProgrammeService psm = new ProgrammeService();
     FavoriteProgrammesService psf =  new FavoriteProgrammesService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        show("");
    }    

    @FXML
    private void onSearch(KeyEvent event) {
         if (searchTimeline != null) {
        searchTimeline.stop();
    }

    searchTimeline = new Timeline(new KeyFrame(Duration.millis(300), actionEvent -> {
        String searchText = searchb.getText();
        show(searchText);
    }));

    searchTimeline.play();
    }
     public void show(String search)
    {
         // Retrieve a list of coaches from the service
        
        final List<Programme> programs ;
        if(search.length() == 0)
        programs= psm.recuperer();
        else
        {
            programs = psm.recupererByCriterias(search,0);
        }

    // Create a VBox to hold the rows
    VBox vbox = new VBox();

    // Iterate through the coaches list two at a time
    for (int i = 0; i < programs.size(); i += 2) {
        
        // Create an HBox to hold the coaches
        HBox hbox = new HBox();
        hbox.setSpacing(180);
        
        // Create an ImageView with the static coach image and add it to the HBox
        for (int j = 0; j < 2 && (i + j) < programs.size(); j++) {
              int index = i + j;
            VBox vboxD = new VBox();
             vboxD.setSpacing(50);
             HBox hboxb = new HBox();
             hboxb.setSpacing(20);
            Image image = new Image(programs.get(i + j).getMedia());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(250);
            imageView.setFitWidth(250);

            hbox.getChildren().add(imageView);

            // Create a Label with the coach's name and surname and add it to the HBox
            Label prog_name = new Label("program name : "+programs.get(i + j).getNom());
            Label prog_type = new Label("type : "+programs.get(i + j).getType());
             Label prog_likes = new Label("likes : "+programs.get(i + j).getLikes());
             final Button likeButton = new Button("Like");
             likeButton.setStyle("-fx-background-color: #66ccff;");
             
    likeButton.setOnAction(e -> {
        final Programme prm = new Programme (programs.get(index));
        prm.setLikes(prm.getLikes()+1);
        psm.modifier(prm);
        show("");
         Notifications.create()
    .title("Success")
    .text("like has been added!")
    .show();
        //FavoriteProgrammes fav = new FavoriteProgrammes(programs.get(index)); // use the index variable instead of i + j
        ////psf.ajouter(fav);
        //show("");
        // add your code to handle the like button click here
    });
    
    String favtext = "Add to favorit";
    if(psf.recupererFavoritebyID(programs.get(index).getId())!= 0)
    {
        favtext = "Remove from Favorit";
        
    }
       final Button fav = new Button(favtext);
       fav.setStyle("-fx-background-color: #ea6368;");
       FavoriteProgrammes favop = new FavoriteProgrammes();
       
    fav.setOnAction(e -> {
        
        if(fav.getText() == "Add to favorit")
        {
        FavoriteProgrammes favadd = new FavoriteProgrammes(programs.get(index)); 
        psf.ajouter(favadd);
        show("");
        Notifications.create()
    .title("Success")
    .text("program has been added to your favorites!")
    .show();

        
        }
        else
        {
            System.out.println(psf.recupererFavoritebyProgid(programs.get(index).getId()));
            psf.supprimer(psf.recupererFavoritebyProgid(programs.get(index).getId()));
            show("");
            Notifications.create()
    .title("Success")
    .text("program has been removed from your favorites!")
    .show();
        }
        // add your code to handle the like button click here
    });


            vboxD.getChildren().add(prog_name);
            vboxD.getChildren().add(prog_type);
            vboxD.getChildren().add(prog_likes);
            hboxb.getChildren().add(likeButton);
            hboxb.getChildren().add(fav);
            vboxD.getChildren().add(hboxb);
            hbox.getChildren().add(vboxD);
            
        }

        // Add the HBox to the VBox
        vbox.getChildren().add(hbox);
    }
     // Set the VBox as the content of the scroll pane
    panev.setContent(vbox);
    }
    
}
