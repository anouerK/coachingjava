/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;



import first_sprint_crud.entities.Programme;
import first_sprint_crud.services.ProgrammeService;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author ksaay
 */
public class MainProgrammeController implements Initializable {

    @FXML
    private Button remove;
    @FXML
    private TableView<Programme> tabview;
    @FXML
    private TableColumn<Programme, String> nomT;
    @FXML
    private TableColumn<Programme, String> typeT;
    @FXML
    private TableColumn<Programme, Integer> dureeT;
    @FXML
    private TableColumn<Programme, Integer> likesT;
    @FXML
    private TableColumn<Programme, String> MediaT;
    @FXML
    private TableColumn<Programme, Integer> idT;

    ProgrammeService psm = new ProgrammeService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        show();
    }    

    @FXML
    private void save(ActionEvent event) {
    }

    @FXML
    private void onremove(ActionEvent event) {
    }
    public void show()
    {
       
          

       MediaT.setCellFactory(param -> {
    //Set up the ImageView
    final ImageView imageview = new ImageView();
    imageview.setFitHeight(70);
    imageview.setFitWidth(100);

    //Set up the Table
    TableCell<Programme, String> cell = new TableCell<Programme, String>() {
        public void updateItem(String item, boolean empty) {
            if (item != null) {
                Image image = new Image(item); // Convert string to Image
                imageview.setImage(image);
            }
        }
    };
    // Attach the imageview to the cell
    cell.setGraphic(imageview);
    return cell;
});
    MediaT.setCellValueFactory(new PropertyValueFactory<Programme, String>("media"));
      idT.setCellValueFactory(new PropertyValueFactory<Programme,Integer>("id"));
       nomT.setCellValueFactory(new PropertyValueFactory<Programme,String>("nom"));
       typeT.setCellValueFactory(new PropertyValueFactory<Programme,String>("type"));
       dureeT.setCellValueFactory(new PropertyValueFactory<Programme,Integer>("duree"));
       likesT.setCellValueFactory(new PropertyValueFactory<Programme,Integer>("likes"));
       
        //tabB.setItems(list);
        ObservableList<Programme> items = FXCollections.observableArrayList(psm.recuperer());
        tabview.setItems(items);

    }
    
}
