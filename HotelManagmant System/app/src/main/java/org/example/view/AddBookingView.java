package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.Room;

import java.util.List;

public class AddBookingView {
    private final Stage stage;
    private final List<Room> bookings;

    public AddBookingView(Stage stage, List<Room> bookings) {
        this.stage = stage;
        this.bookings = bookings;
    }

    public void show() {
        Label titleLabel = new Label("Add Booking");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

        TextField roomNumberField = new TextField();
        roomNumberField.setPromptText("Enter Room Number");

        Button addButton = new Button("Add Booking");
        addButton.setOnAction(e -> {
            String roomNumber = roomNumberField.getText();
            if (!roomNumber.isEmpty()) {
                Room newBooking = new Room(String.valueOf(bookings.size() + 1), roomNumber, "Booked Room", 0, "Booked");
                bookings.add(newBooking);
                System.out.println("Booking added: " + newBooking);
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new ReceptionistMenuView(stage).show());

        VBox layout = new VBox(10, titleLabel, roomNumberField, addButton, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = stage.getScene();
        scene.setRoot(layout);
        stage.setTitle("Add Booking");
    }
}
