package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.enums.RoomType;
import org.example.viewmodel.AddRoomViewModel;

public class AddRoomView {
    private final AddRoomViewModel viewModel;

    public AddRoomView() {
        this.viewModel = new AddRoomViewModel();
    }

    public void show(Stage stage, long adminId) {
        stage.setTitle("Add New Room");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label hotelLabel = new Label("Select Hotel:");
        ComboBox<String> hotelComboBox = new ComboBox<>();
        ObservableList<String> hotelNames = FXCollections.observableArrayList(viewModel.getHotelNames());
        hotelComboBox.setItems(hotelNames);
        hotelComboBox.setPromptText("Choose Hotel");

        Label roomNumberLabel = new Label("Room Number:");
        TextField roomNumberField = new TextField();
        roomNumberField.setPromptText("e.g., 101");

        Label roomTypeLabel = new Label("Room Type:");
        ComboBox<RoomType> roomTypeComboBox = new ComboBox<>();
        ObservableList<RoomType> roomTypes = FXCollections.observableArrayList(viewModel.getRoomTypes());
        roomTypeComboBox.setItems(roomTypes);
        roomTypeComboBox.setPromptText("Choose Room Type");

        Label priceLabel = new Label("Price:");
        TextField priceField = new TextField();
        priceField.setPromptText("e.g., 150.00");

        Button addButton = new Button("Add Room");
        addButton.setOnAction(e -> {
            String hotelName = hotelComboBox.getValue();
            String roomNumber = roomNumberField.getText().trim();
            RoomType roomType = roomTypeComboBox.getValue();
            String priceText = priceField.getText().trim();

            if (hotelName == null || roomNumber.isEmpty() || roomType == null || priceText.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Incomplete Information", "Please fill in all fields.");
                return;
            }

            try {
                double price = Double.parseDouble(priceText);
                boolean success = viewModel.addRoom(hotelName, roomNumber, roomType, price);
                if (success) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Room added successfully.");
                    new AdministratorMenuView(adminId).show(stage);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to add room. It might already exist.");
                }
            } catch (NumberFormatException ex) {
                showAlert(Alert.AlertType.ERROR, "Invalid Input", "Price must be a valid number.");
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> new AdministratorMenuView(adminId).show(stage));

        grid.add(hotelLabel, 0, 0);
        grid.add(hotelComboBox, 1, 0);
        grid.add(roomNumberLabel, 0, 1);
        grid.add(roomNumberField, 1, 1);
        grid.add(roomTypeLabel, 0, 2);
        grid.add(roomTypeComboBox, 1, 2);
        grid.add(priceLabel, 0, 3);
        grid.add(priceField, 1, 3);
        grid.add(addButton, 0, 4);
        grid.add(cancelButton, 1, 4);

        Scene scene = new Scene(grid, 400, 300);
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
