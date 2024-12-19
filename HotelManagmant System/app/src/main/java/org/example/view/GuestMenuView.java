package org.example.view;

import org.example.viewmodel.GuestMenuViewModel;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuestMenuView {
    private final GuestMenuViewModel viewModel;

    public GuestMenuView() {
        this.viewModel = new GuestMenuViewModel();
    }

    public void show(Stage stage, Long guestId) {
        Label titleLabel = new Label("Welcome to the Guest Menu");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button viewBookingsButton = new Button("View My Bookings");
        viewBookingsButton.setOnAction(e -> new BookingListView(guestId).show(stage, guestId));

        Button addBookingButton = new Button("Add New Booking");
        addBookingButton.setOnAction(e -> new HotelListView(guestId).show(stage, guestId));

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, titleLabel, viewBookingsButton, addBookingButton, exitButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Guest Menu");
        stage.show();
    }
}
