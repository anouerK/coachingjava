package GUI;


import first_sprint_crud.entities.Commentaire;
import first_sprint_crud.services.CommentaireService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ArticleDetailsController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label title;
    @FXML
    private Label descr;
    @FXML
    private ListView<Commentaire> listv;

    CommentaireService psc = new CommentaireService();
    @FXML
    private TextField commetinput;
    @FXML
    private Button submit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        show();
        
    }
    private void show()
    {
         title.setText(ArticleFrontController.selectedarticle_.getTitre());
        descr.setText(ArticleFrontController.selectedarticle_.getSujet_art());
         Image image = new Image(ArticleFrontController.selectedarticle_.getImage()); // Convert string to Image
                        img.setImage(image);
        List<Commentaire> comments = psc.recupererby_article(ArticleFrontController.selectedarticle_.getId());

        // Create a list cell factory for the comments list view
        listv.setCellFactory(param -> {
            ListCell<Commentaire> cell = new ListCell<Commentaire>() {
                @Override
                protected void updateItem(Commentaire comment, boolean empty) {
                    super.updateItem(comment, empty);
                    if (comment != null) {
                        // Create a label to display the comment content
                        Label contentLabel = new Label(comment.getContenu());

                        // Create a button to delete the comment
                        Button deleteButton = new Button("Delete");

                        // Set the action listener for the delete button
                        deleteButton.setOnAction(event -> {
                            psc.supprimer(comment.getId());
                            listv.getItems().remove(comment);
                        });

                        // Add the content label and delete button to a new HBox
                        HBox hbox = new HBox(contentLabel, deleteButton);

                        // Set the cell's graphic to the HBox
                        setGraphic(hbox);
                    } else {
                        setGraphic(null);
                    }
                }
            };
            return cell;
        });

        // Add each comment to the list view
        for (Commentaire comment : comments) {
            listv.getItems().add(comment);
        }
    }

    @FXML
    private void save(ActionEvent event) {
         if (commetinput.getText().isEmpty() ) {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Creation Error");
            alert.setContentText("Comment must  not be empty !");
         }
         else
         {
              Commentaire c = new Commentaire(commetinput.getText(),ArticleFrontController.selectedarticle_);

            psc.ajouter(c);
            listv.getItems().add(c);
            commetinput.setText("");
         }
    }
}