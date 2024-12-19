package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.enums.RoomStatus;
import org.example.model.Hotel;
import org.example.model.Room;
import org.example.viewmodel.RoomListViewModel;

import java.time.LocalDate;

public class RoomListView {
    private final RoomListViewModel viewModel;

    public RoomListView(long hotelId) {
        this.viewModel = new RoomListViewModel(hotelId);
    }

    public void show(Stage stage, Hotel hotel, long guestId, LocalDate checkInDate, LocalDate checkOutDate) {
        // Загрузим доступные комнаты на указанный период
        viewModel.loadAvailableRooms(checkInDate, checkOutDate);

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

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            // Возвращаемся к списку отелей с уже выбранными датами
            new HotelListView(guestId).show(stage, guestId);
        });

        // Новая кнопка для бронирования комнаты
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

            // Переходим к PaymentView, передаем даты, отель, комнату и guestId
            new PaymentView(guestId, hotel, selectedRoom, checkInDate, checkOutDate).show(stage, guestId, hotel, selectedRoom, checkInDate, checkOutDate);
        });

        VBox layout = new VBox(10, table, bookButton, backButton);
        layout.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Rooms in " + hotel.getName());
    }
}
