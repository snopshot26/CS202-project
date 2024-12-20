package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.enums.RoomStatus;
import org.example.viewmodel.ManageRoomStatusViewModel;

public class ManageRoomStatusView {
    private final ManageRoomStatusViewModel viewModel;

    public ManageRoomStatusView() {
        this.viewModel = new ManageRoomStatusViewModel();
    }

    public void show(Stage stage, long adminId) {
        stage.setTitle("Manage Room Status");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label hotelLabel = new Label("Select Hotel:");
        ComboBox<String> hotelComboBox = new ComboBox<>();
        ObservableList<String> hotelNames = FXCollections.observableArrayList(viewModel.getHotelNames());
        hotelComboBox.setItems(hotelNames);
        hotelComboBox.setPromptText("Choose Hotel");

        Label roomNumberLabel = new Label("Select Room:");
        ComboBox<String> roomNumberComboBox = new ComboBox<>();
        roomNumberComboBox.setPromptText("Choose Room");

        Label statusLabel = new Label("Select Status:");
        ComboBox<RoomStatus> statusComboBox = new ComboBox<>();
        ObservableList<RoomStatus> roomStatuses = FXCollections.observableArrayList(viewModel.getRoomStatuses());
        statusComboBox.setItems(roomStatuses);
        statusComboBox.setPromptText("Choose Status");

        Button updateButton = new Button("Update Status");
        updateButton.setOnAction(e -> {
            String hotelName = hotelComboBox.getValue();
            String roomNumber = roomNumberComboBox.getValue();
            RoomStatus status = statusComboBox.getValue();

            if (hotelName == null || roomNumber == null || status == null) {
                showAlert(Alert.AlertType.WARNING, "Incomplete Information", "Please select hotel, room, and status.");
                return;
            }

            boolean success = viewModel.updateRoomStatus(hotelName, roomNumber, status);
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Room status updated successfully.");
                new AdministratorMenuView(adminId).show(stage);
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to update room status.");
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> new AdministratorMenuView(adminId).show(stage));

        // Обновление номеров комнат при выборе отеля
        hotelComboBox.setOnAction(e -> {
            String hotelName = hotelComboBox.getValue();
            ObservableList<String> roomNumbers = FXCollections.observableArrayList(viewModel.getRoomNumbersByHotel(hotelName));
            roomNumberComboBox.setItems(roomNumbers);
        });

        grid.add(hotelLabel, 0, 0);
        grid.add(hotelComboBox, 1, 0);
        grid.add(roomNumberLabel, 0, 1);
        grid.add(roomNumberComboBox, 1, 1);
        grid.add(statusLabel, 0, 2);
        grid.add(statusComboBox, 1, 2);
        grid.add(updateButton, 0, 3);
        grid.add(cancelButton, 1, 3);

        Scene scene = new Scene(grid, 400, 250);
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
