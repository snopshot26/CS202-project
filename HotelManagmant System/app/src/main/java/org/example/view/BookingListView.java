package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.example.model.Room;

import javafx.scene.Scene;

public class BookingListView {
    private final Stage stage;

    public BookingListView(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        Label titleLabel = new Label("Booking List");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");

        ListView<Room> bookingListView = new ListView<>();
        ObservableList<Room> bookings = FXCollections.observableArrayList(
            new Room("201", 200, "Room 201", "Suite", "Booked"),
            new Room("203", 150, "Room 203", "Double", "Available")
        );
        bookingListView.setItems(bookings);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new ReceptionistMenuView(stage).showMenu());

        VBox layout = new VBox(10, titleLabel, bookingListView, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        ((Scene) stage.getScene()).setRoot(layout); // Переключаем содержимое сцены
        stage.show();
    }
}
