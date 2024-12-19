package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.Hotel;
import org.example.viewmodel.HotelListViewModel;

import java.time.LocalDate;

public class HotelListView {

    private final HotelListViewModel viewModel;

    public HotelListView(long guestId) {
        this.viewModel = new HotelListViewModel(guestId);
    }

    public void show(Stage primaryStage, long guestId) {
        TableView<Hotel> table = new TableView<>();
        table.setItems(viewModel.getHotels());

        TableColumn<Hotel, String> nameColumn = new TableColumn<>("Hotel Name");
        nameColumn.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        TableColumn<Hotel, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(data.getValue().getAddress()));

        table.getColumns().addAll(nameColumn, addressColumn);

        DatePicker checkInDatePicker = new DatePicker(LocalDate.now().plusDays(1));
        DatePicker checkOutDatePicker = new DatePicker(LocalDate.now().plusDays(2));

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            LocalDate checkIn = checkInDatePicker.getValue();
            LocalDate checkOut = checkOutDatePicker.getValue();

            if (checkIn == null || checkOut == null || !checkOut.isAfter(checkIn)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Dates");
                alert.setHeaderText("Invalid Dates Selected");
                alert.setContentText("Check-out date must be after check-in date.");
                alert.showAndWait();
                return;
            }

            viewModel.loadAvailableHotels(checkIn, checkOut);
            table.refresh();
        });

        Button viewRoomsButton = new Button("View Rooms");
        viewRoomsButton.setOnAction(e -> {
            Hotel selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                // Предположим, есть RoomListView для отображения комнат
                new RoomListView(selected.getId()).show(primaryStage, selected, guestId, checkInDatePicker.getValue(), checkOutDatePicker.getValue());
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Hotel Selected");
                alert.setHeaderText("No hotel selected");
                alert.setContentText("Please select a hotel first.");
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            // Возврат в GuestMenuView или другое меню
            new GuestMenuView().show(primaryStage, guestId);
        });

        VBox layout = new VBox(10,
            new Label("Select Dates:"), checkInDatePicker, checkOutDatePicker, searchButton,
            table, viewRoomsButton, backButton
        );
        layout.setStyle("-fx-padding: 20px;");
        Scene scene = new Scene(layout, 600, 500);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Available Hotels");

        // Изначально можно загрузить все отели или оставить пустым
        viewModel.loadHotels();
    }
}
