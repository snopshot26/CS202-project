package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuestMenuView{
    public void start(Stage mainStage) {
        Stage guestStage = new Stage();

        Label guestLabel = new Label("Welcome to the Guest Menu");
        guestLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

        Button addBookingButton = new Button("Add Booking");
        addBookingButton.setOnAction(e -> System.out.println("Add Booking clicked."));

        Button viewMyBookingsButton = new Button("View My Bookings");
        viewMyBookingsButton.setOnAction(e -> System.out.println("View My Bookings clicked."));

        Button backButton = new Button("Exit");
        backButton.setOnAction(e -> {
            guestStage.close();
            mainStage.show();
        });

        VBox layout = new VBox(10, guestLabel, addBookingButton, viewMyBookingsButton, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 300);
        guestStage.setTitle("Guest Menu");
        guestStage.setScene(scene);
        mainStage.hide();
        guestStage.show();
    }
}
