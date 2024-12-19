package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.Room;

import java.util.List;

public class ViewBookingsView {
    private final Stage stage;
    private final List<Room> bookings;

    public ViewBookingsView(Stage stage, List<Room> bookings) {
        this.stage = stage;
        this.bookings = bookings;
    }

    public void show() {
        Label titleLabel = new Label("View Bookings");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

        ListView<String> bookingListView = new ListView<>();
        bookings.forEach(booking -> bookingListView.getItems().add(booking.toString()));

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new ReceptionistMenuView(stage).show());

        VBox layout = new VBox(10, titleLabel, bookingListView, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = stage.getScene();
        scene.setRoot(layout);
        stage.setTitle("View Bookings");
    }
}
