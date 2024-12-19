package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReceptionistMenuView {
    private final Stage stage;
    private final Scene scene;

    public ReceptionistMenuView(Stage stage) {
        this.stage = stage;
        this.scene = new Scene(new VBox(), 400, 300); // Создаём общую сцену
        this.stage.setScene(scene);
        this.stage.setTitle("Receptionist Menu");
    }

    public void showMenu() {
        Label receptionistLabel = new Label("Receptionist Menu");
        receptionistLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

        Button addBookingButton = new Button("Add Booking");
        addBookingButton.setOnAction(e -> new RoomBookingView(stage).show());

        Button viewBookingsButton = new Button("View Bookings");
        viewBookingsButton.setOnAction(e -> new BookingListView(stage).show());

        Button assignHousekeepingTaskButton = new Button("Assign Housekeeping Task");
        assignHousekeepingTaskButton.setOnAction(e -> new HousekeepingTaskView(stage).show());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, receptionistLabel, addBookingButton, viewBookingsButton, assignHousekeepingTaskButton, exitButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        scene.setRoot(layout); // Переключаем содержимое сцены
        stage.show();
    }
}
