package org.example.view;

import java.time.LocalDate;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import org.example.viewmodel.ReceptionistMenuViewModel;

public class ReceptionistMenuView {
    private final ReceptionistMenuViewModel viewModel;
    private final Long receptionistId;

    public ReceptionistMenuView(Long receptionistId) {
        this.receptionistId = receptionistId;
        this.viewModel = new ReceptionistMenuViewModel(receptionistId);
    }

    public void show(Stage stage,Long receptionistId) {
        Label titleLabel = new Label("Receptionist Menu");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Кнопка Add New Booking
        Button addBookingButton = new Button("Add New Booking");
        addBookingButton.setOnAction(e -> {
            new RoomListView(this.viewModel.getHotel().getId()).show(stage, this.viewModel.getHotel(), receptionistId, LocalDate.now(), LocalDate.now().plusDays(1));
        });

        // Кнопка Bookings (просмотр всех бронирований)
        Button bookingsButton = new Button("Bookings");
        bookingsButton.setOnAction(e -> {
            new ReceptionistBookingListView(receptionistId).show(stage, receptionistId);
        });

        // Кнопка Assign Housekeeping Task
        Button assignTaskButton = new Button("Assign Housekeeping Task");
        assignTaskButton.setOnAction(e -> {
            new AssignHousekeepingTaskView(receptionistId).show(stage, receptionistId);
        });

        // Кнопка View All Housekeepers Records and Their Availability
        Button viewHousekeepersButton = new Button("View All Housekeepers Records and Their Availability");
        viewHousekeepersButton.setOnAction(e -> {
            new HousekeeperRecordsView(receptionistId).show(stage, receptionistId);
        });


        VBox layout = new VBox(10, titleLabel, addBookingButton, bookingsButton, assignTaskButton, viewHousekeepersButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Receptionist Menu");
        stage.show();
    }
}
