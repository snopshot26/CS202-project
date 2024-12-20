package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.enums.RoomStatus;
import org.example.model.Hotel;
import org.example.model.Room;
import org.example.viewmodel.RoomListViewModel;

import java.time.LocalDate;
import java.util.Optional;

public class RoomListView {
    private final RoomListViewModel viewModel;

    public RoomListView(long hotelId) {
        this.viewModel = new RoomListViewModel(hotelId);
    }

    public void show(Stage stage, Hotel hotel, long guestId, LocalDate initialCheckInDate, LocalDate initialCheckOutDate) {
        // Дата заезда
        DatePicker checkInDatePicker = new DatePicker(initialCheckInDate);
        // Дата выезда
        DatePicker checkOutDatePicker = new DatePicker(initialCheckOutDate);

        // Таблица для отображения комнат
        TableView<Room> table = new TableView<>();
        table.setItems(viewModel.getRooms());

        TableColumn<Room, String> roomNumberColumn = new TableColumn<>("Room Number");
        roomNumberColumn.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(data.getValue().getRoomNumber()));

        TableColumn<Room, String> roomTypeColumn = new TableColumn<>("Room Type");
        roomTypeColumn.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(data.getValue().getRoomType().toString()));

        TableColumn<Room, String> roomStatusColumn = new TableColumn<>("Status");
        roomStatusColumn.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus().toString()));

        TableColumn<Room, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(String.valueOf(data.getValue().getPrice())));

        table.getColumns().addAll(roomNumberColumn, roomTypeColumn, roomStatusColumn, priceColumn);

        // Кнопка для поиска доступных комнат по выбранным датам
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            LocalDate checkIn = checkInDatePicker.getValue();
            LocalDate checkOut = checkOutDatePicker.getValue();

            if (checkIn == null || checkOut == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Dates");
                alert.setHeaderText("Dates not selected");
                alert.setContentText("Please select both check-in and check-out dates.");
                alert.showAndWait();
                return;
            }

            if (!checkOut.isAfter(checkIn)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Dates");
                alert.setHeaderText("Check-Out date must be after Check-In date");
                alert.setContentText("Please select valid dates.");
                alert.showAndWait();
                return;
            }

            // Загрузка доступных комнат
            viewModel.loadAvailableRooms(checkIn, checkOut);
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            // Возвращаемся к списку отелей
            new HotelListView(guestId).show(stage, guestId);
        });

        // Кнопка бронирования
        Button bookButton = new Button("Book Room");
        bookButton.setOnAction(e -> {
            Room selectedRoom = table.getSelectionModel().getSelectedItem();
            if (selectedRoom == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Room Selected");
                alert.setHeaderText("No room selected");
                alert.setContentText("Please select a room to book.");
                alert.showAndWait();
                return;
            }

            // Проверяем статус комнаты.
            if (selectedRoom.getStatus() != RoomStatus.AVAILABLE) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cannot Book");
                alert.setHeaderText("Room not available");
                alert.setContentText("This room cannot be booked as it is not available.");
                alert.showAndWait();
                return;
            }

            // Проверим, что даты были выбраны и корректны
            LocalDate checkIn = checkInDatePicker.getValue();
            LocalDate checkOut = checkOutDatePicker.getValue();
            if (checkIn == null || checkOut == null || !checkOut.isAfter(checkIn)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Dates");
                alert.setHeaderText("Dates not properly selected");
                alert.setContentText("Please select valid check-in and check-out dates.");
                alert.showAndWait();
                return;
            }

            // Переходим к PaymentView
            new PaymentView(guestId, hotel, selectedRoom, checkIn, checkOut).show(stage, guestId, hotel, selectedRoom, checkIn, checkOut);
        });

        VBox layout = new VBox(10,
                new Label("Select Dates:"),
                new HBox(10, new Label("Check-In:"), checkInDatePicker, new Label("Check-Out:"), checkOutDatePicker, searchButton),
                table,
                bookButton,
                backButton
        );
        layout.setStyle("-fx-padding: 20px;");
        
        Scene scene = new Scene(layout, 800, 500);
        stage.setScene(scene);
        stage.setTitle("Rooms in " + hotel.getName());
    }
}
