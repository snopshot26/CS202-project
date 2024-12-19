package org.example.view;

import org.example.model.Booking;
import org.example.viewmodel.BookingViewModel;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookingListView {
    private final BookingViewModel viewModel;

    public BookingListView(long guestId) {
        this.viewModel = new BookingViewModel(guestId);
    }

    public void show(Stage stage, long guestId) {
        TableView<Booking> table = new TableView<>();
        table.setItems(viewModel.getBookings());

        TableColumn<Booking, String> checkInColumn = new TableColumn<>("Check-In Date");
        checkInColumn.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(data.getValue().getCheckInDate().toString()));

        TableColumn<Booking, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus().toString()));

        table.getColumns().addAll(checkInColumn, statusColumn);

        Button cancelButton = new Button("Cancel Booking");
        cancelButton.setOnAction(e -> {
            Booking selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                boolean canceled = viewModel.cancelBooking(selected);
                if (!canceled) {
                    // Показать окно ошибки
                    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
                    alert.setTitle("Cancellation Error");
                    alert.setHeaderText("Unable to cancel booking");
                    alert.setContentText("This booking cannot be canceled because it is in the past.");
                    alert.showAndWait();
                } else {
                    table.refresh();
                }
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new GuestMenuView().show(stage,guestId));

        VBox layout = new VBox(10, table, cancelButton, backButton);
        layout.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Booking Management");
    }
}
