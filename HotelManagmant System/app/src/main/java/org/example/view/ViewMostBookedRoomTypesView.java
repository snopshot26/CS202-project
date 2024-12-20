package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.viewmodel.ViewMostBookedRoomTypesViewModel;

import java.util.Map;

public class ViewMostBookedRoomTypesView {
    private final ViewMostBookedRoomTypesViewModel viewModel;

    public ViewMostBookedRoomTypesView() {
        this.viewModel = new ViewMostBookedRoomTypesViewModel();
    }

    public void show(Stage stage) {
        stage.setTitle("Most Booked Room Types");

        PieChart pieChart = new PieChart();
        final Map<String, Integer> roomTypeBookings = viewModel.getMostBookedRoomTypes();

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        roomTypeBookings.forEach((roomType, count) -> {
            pieChartData.add(new PieChart.Data(roomType, count));
        });

        pieChart.setData(pieChartData);
        pieChart.setTitle("Room Type Bookings");

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> {
            Map<String, Integer> updatedRoomTypeBookings = viewModel.getMostBookedRoomTypes();
            pieChartData.clear();
            updatedRoomTypeBookings.forEach((roomType, count) -> {
                pieChartData.add(new PieChart.Data(roomType, count));
            });
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, pieChart, refreshButton, closeButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 600, 500);
        stage.setScene(scene);
        stage.show();
    }
}
