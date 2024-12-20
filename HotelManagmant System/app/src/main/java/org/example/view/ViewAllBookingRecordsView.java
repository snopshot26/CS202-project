package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.Booking;
import org.example.viewmodel.ViewAllBookingRecordsViewModel;

public class ViewAllBookingRecordsView {
    private final ViewAllBookingRecordsViewModel viewModel;

    public ViewAllBookingRecordsView() {
        this.viewModel = new ViewAllBookingRecordsViewModel();
    }

    public void show(Stage stage) {
        stage.setTitle("View All Booking Records");

        TableView<Booking> bookingTable = new TableView<>();
        ObservableList<Booking> bookings = FXCollections.observableArrayList(viewModel.getAllBookings());
        bookingTable.setItems(bookings);

        TableColumn<Booking, Long> bookingIdCol = new TableColumn<>("Booking ID");
        bookingIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().geId()).asObject());

        TableColumn<Booking, Long> guestIdCol = new TableColumn<>("Guest ID");
        guestIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getGuest().getId()).asObject());

        TableColumn<Booking, Long> roomIdCol = new TableColumn<>("Room ID");
        roomIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getRoom().getId()).asObject());

        TableColumn<Booking, Long> hotelIdCol = new TableColumn<>("Hotel ID");
        hotelIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getHotel().getId()).asObject());

        TableColumn<Booking, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus().toString()));

        TableColumn<Booking, String> paymentStatusCol = new TableColumn<>("Payment Status");
        paymentStatusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPaymentStatus().toString()));

        bookingTable.getColumns().addAll(bookingIdCol, guestIdCol, roomIdCol, hotelIdCol, statusCol, paymentStatusCol);

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> {
            bookings.setAll(viewModel.getAllBookings());
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, bookingTable, refreshButton, closeButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
