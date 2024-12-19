package org.example.view;

import org.example.model.Booking;
import org.example.viewmodel.BookingViewModel;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookingView {
    private final BookingViewModel viewModel;

    public BookingView() {
        this.viewModel = new BookingViewModel();
    }

    public void show(Stage stage) {
        TableView<Booking> table = new TableView<>();
        table.setItems(viewModel.getBookings());

        TableColumn<Booking, String> checkInColumn = new TableColumn<>("Check-In Date");
        checkInColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCheckInDate().toString()));

        TableColumn<Booking, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus().toString()));

        table.getColumns().addAll(checkInColumn, statusColumn);

        Button cancelButton = new Button("Cancel Booking");
        cancelButton.setOnAction(e -> {
            Booking selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                viewModel.cancelBooking(selected); // Отмена бронирования
                table.refresh(); // Обновляем таблицу
            }
        });

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> {
            viewModel.loadBookings();
            table.refresh();
        });

        VBox layout = new VBox(10, table, cancelButton, refreshButton);
        layout.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Booking Management");
    }
}
