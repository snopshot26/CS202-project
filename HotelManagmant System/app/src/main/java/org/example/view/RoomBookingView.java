package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.example.model.Room;

import javafx.scene.Scene;

public class RoomBookingView {
    private final Stage stage;

    public RoomBookingView(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        Label titleLabel = new Label("Room Booking");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");

        ListView<Room> roomListView = new ListView<>();
        ObservableList<Room> allRooms = FXCollections.observableArrayList(
            new Room("101", 100, "Room 101", "Single", "Available"),
            new Room("102", 150, "Room 102", "Double", "Booked"),
            new Room("103", 200, "Room 103", "Suite", "Available")
        );
        roomListView.setItems(allRooms.filtered(room -> "Available".equals(room.getStatus())));

        TextField filterField = new TextField();
        filterField.setPromptText("Filter by Room Number");
        filterField.textProperty().addListener((obs, oldText, newText) -> {
            roomListView.setItems(allRooms.filtered(room -> room.getRoomNumber().contains(newText) && "Available".equals(room.getStatus())));
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new ReceptionistMenuView(stage).showMenu());

        VBox layout = new VBox(10, titleLabel, filterField, roomListView, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        ((Scene) stage.getScene()).setRoot(layout); // Переключаем содержимое сцены
        stage.show();
    }
}
