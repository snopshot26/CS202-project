// AssignHousekeepingTaskView.java (Updated)
package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.model.Housekeeping;
import org.example.model.Room;
import org.example.viewmodel.AssignHousekeepingTaskViewModel;

import java.time.LocalDate;
import java.util.List;

public class AssignHousekeepingTaskView {
    private final AssignHousekeepingTaskViewModel viewModel;

    public AssignHousekeepingTaskView(long receptionistId) {
        this.viewModel = new AssignHousekeepingTaskViewModel(receptionistId);
    }

    public void show(Stage stage, Long receptionistId) {
        stage.setTitle("Assign Housekeeping Task");

        // Create a grid layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(15);
        grid.setHgap(10);

        // Select Housekeeper
        Label housekeeperLabel = new Label("Select Housekeeper:");
        ComboBox<Housekeeping> housekeeperComboBox = new ComboBox<>();
        List<Housekeeping> housekeepers = viewModel.getHousekeepersByHotel();
        housekeeperComboBox.setItems(FXCollections.observableArrayList(housekeepers));
        housekeeperComboBox.setPromptText("Choose Housekeeper");

        // **Set Custom Cell Factory for Housekeeper ComboBox**
        housekeeperComboBox.setCellFactory(comboBox -> new ListCell<>() {
            @Override
            protected void updateItem(Housekeeping housekeeper, boolean empty) {
                super.updateItem(housekeeper, empty);
                if (empty || housekeeper == null) {
                    setText(null);
                } else {
                    setText(housekeeper.getName() + " (ID: " + housekeeper.getId() + ")");
                }
            }
        });

        // **Set Button Cell to Display Selected Item Properly**
        housekeeperComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Housekeeping housekeeper, boolean empty) {
                super.updateItem(housekeeper, empty);
                if (empty || housekeeper == null) {
                    setText(null);
                } else {
                    setText(housekeeper.getName() + " (ID: " + housekeeper.getId() + ")");
                }
            }
        });

        // Select Room
        Label roomLabel = new Label("Select Room:");
        ComboBox<Room> roomComboBox = new ComboBox<>();
        List<Room> rooms = viewModel.getRoomsByHotel();
        roomComboBox.setItems(FXCollections.observableArrayList(rooms));
        roomComboBox.setPromptText("Choose Room");

        // **Set Custom Cell Factory for Room ComboBox**
        roomComboBox.setCellFactory(comboBox -> new ListCell<>() {
            @Override
            protected void updateItem(Room room, boolean empty) {
                super.updateItem(room, empty);
                if (empty || room == null) {
                    setText(null);
                } else {
                    setText("Room " + room.getRoomNumber() + " (" + room.getRoomType() + ")");
                }
            }
        });

        // **Set Button Cell for Room ComboBox**
        roomComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Room room, boolean empty) {
                super.updateItem(room, empty);
                if (empty || room == null) {
                    setText(null);
                } else {
                    setText("Room " + room.getRoomNumber() + " (" + room.getRoomType() + ")");
                }
            }
        });

        // Task Description
        Label taskDescriptionLabel = new Label("Task Description:");
        TextField taskDescriptionField = new TextField();
        taskDescriptionField.setPromptText("Enter task description");

        // Task Date
        Label taskDateLabel = new Label("Task Date:");
        DatePicker taskDatePicker = new DatePicker();
        taskDatePicker.setValue(LocalDate.now());

        // Assign and Cancel Buttons
        Button assignButton = new Button("Assign Task");
        Button cancelButton = new Button("Cancel");

        // Add components to the grid
        grid.add(housekeeperLabel, 0, 0);
        grid.add(housekeeperComboBox, 1, 0);
        grid.add(roomLabel, 0, 1);
        grid.add(roomComboBox, 1, 1);
        grid.add(taskDescriptionLabel, 0, 2);
        grid.add(taskDescriptionField, 1, 2);
        grid.add(taskDateLabel, 0, 3);
        grid.add(taskDatePicker, 1, 3);
        grid.add(assignButton, 0, 4);
        grid.add(cancelButton, 1, 4);

        // Event Handler for Assign Button
        assignButton.setOnAction(e -> {
            Housekeeping selectedHousekeeper = housekeeperComboBox.getValue();
            Room selectedRoom = roomComboBox.getValue();
            String taskDescription = taskDescriptionField.getText().trim();
            LocalDate taskDate = taskDatePicker.getValue();

            // Validate inputs
            if (selectedHousekeeper == null || selectedRoom == null || taskDescription.isEmpty() || taskDate == null) {
                showAlert(Alert.AlertType.WARNING, "Incomplete Information", "Please fill in all fields.");
                return;
            }

            // Assign task via ViewModel
            boolean success = viewModel.assignTask(
                    selectedHousekeeper.getId(),
                    taskDescription,
                    taskDate,
                    selectedRoom
            );

            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Task Assigned",
                        "Task successfully assigned to " + selectedHousekeeper.getName() +
                                " for room " + selectedRoom.getRoomNumber() + ".");
                // Navigate back to Receptionist Menu
                new ReceptionistMenuView(receptionistId).show(stage, receptionistId);
            } else {
                showAlert(Alert.AlertType.ERROR, "Assignment Failed", "Failed to assign task. Please try again.");
            }
        });

        // Event Handler for Cancel Button
        cancelButton.setOnAction(e -> new ReceptionistMenuView(receptionistId).show(stage, receptionistId));

        // Set up the scene and display
        Scene scene = new Scene(grid, 500, 350);
        stage.setScene(scene);
        stage.show();
    }
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
