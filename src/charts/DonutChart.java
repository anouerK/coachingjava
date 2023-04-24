/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charts;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 *
 * @author ksaay
 */
public class DonutChart extends Application {
@Override
    public void start(Stage stage) throws Exception {
        // Create the data for the chart
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Category 1", 20),
                        new PieChart.Data("Category 2", 30),
                        new PieChart.Data("Category 3", 40),
                        new PieChart.Data("Category 4", 10)
                );

        // Create the chart
        PieChart chart = new PieChart(pieChartData);
        chart.setLegendVisible(false); // Hide the chart legend
        chart.setLabelsVisible(false); // Hide the labels on the chart

        // Create a VBox layout to hold the chart
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().add(chart);

        // Create a Scene and display it
        Scene scene = new Scene(layout, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
