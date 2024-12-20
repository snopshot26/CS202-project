package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.dao.RoomDAO;
import org.example.model.Room;
import org.example.viewmodel.DeleteRoomViewModel;

import java.util.List;

public class DeleteRoomView {
    private final DeleteRoomViewModel viewModel;
    public DeleteRoomView(long adminId) {
        this.viewModel = new DeleteRoomViewModel(adminId);
    }

    public void show(Stage stage) {
        stage.setTitle("Delete Room");

        // Create ListView to display rooms
        ListView<Room> roomListView = new ListView<>();

        // Fetch available rooms
        List<Room> rooms = viewModel.getAllRooms();
        ObservableList<Room> observableRooms = FXCollections.observableArrayList(rooms);
        roomListView.setItems(observableRooms);

        // Customize ListView to display room details
        roomListView.setCellFactory(param -> new ListCell<Room>() {
            @Override
            protected void updateItem(Room room, boolean empty) {
                super.updateItem(room, empty);
                if (empty || room == null) {
                    setText(null);
                } else {
                    // Display room number, type, price, and hotel name
                    setText("Room " + room.getRoomNumber() +
                            " | Type: " + room.getRoomType() +
                            " | Price: $" + room.getPrice() +
                            " | Hotel: " + room.getHotel().getName());
                }
            }
        });

        // Delete button
        Button deleteButton = new Button("Delete Selected Room");
        deleteButton.setOnAction(e -> {
            Room selectedRoom = roomListView.getSelectionModel().getSelectedItem();
            if (selectedRoom != null) {
                // Confirm deletion
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirm Deletion");
                confirmationAlert.setHeaderText(null);
                confirmationAlert.setContentText("Are you sure you want to delete Room " + selectedRoom.getRoomNumber() + "?");

                ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

                confirmationAlert.getButtonTypes().setAll(yesButton, noButton);

                confirmationAlert.showAndWait().ifPresent(type -> {
                    if (type == yesButton) {
                        boolean success = viewModel.deleteRoom(selectedRoom.getId());
                        if (success) {
                            showAlert(Alert.AlertType.INFORMATION, "Success", "Room deleted successfully.");
                            observableRooms.remove(selectedRoom);
                        } else {
                            showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete room.");
                        }
                    }
                });
            } else {
                showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a room to delete.");
            }
        });

        Button cancelButton = new Button("Back");
        cancelButton.setOnAction(e -> {
            // Возвращаемся к RoomListView или GuestMenuView
            new AdministratorMenuView(this.viewModel.getAdminId()).show(stage);
        });
        // Layout setup
        VBox layout = new VBox(10, roomListView, deleteButton, cancelButton);
        layout.setPadding(new Insets(20));

        // Scene setup
        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
