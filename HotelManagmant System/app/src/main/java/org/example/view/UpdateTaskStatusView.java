package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.HousekeepingTask;
import org.example.viewmodel.HousekeepingMenuViewModel;

import java.util.List;

public class UpdateTaskStatusView {
    private final HousekeepingMenuViewModel viewModel;
    private final long housekeeperId;

    /**
     * Constructor to initialize the view with the housekeeper's ID.
     *
     * @param housekeeperId The ID of the housekeeper.
     */
    public UpdateTaskStatusView(long housekeeperId) {
        this.viewModel = new HousekeepingMenuViewModel();
        this.housekeeperId = housekeeperId;
    }

    /**
     * Displays the Update Task Status View.
     *
     * @param stage The stage to display the view.
     */
    public void show(Stage stage) {
        stage.setTitle("Update Task Status to Completed");

        // Fetch pending tasks
        List<HousekeepingTask> pendingTasks = viewModel.getPendingTasks(housekeeperId);
        ObservableList<HousekeepingTask> observableTasks = FXCollections.observableArrayList(pendingTasks);

        // Create ComboBox to select a task
        ComboBox<HousekeepingTask> taskComboBox = new ComboBox<>(observableTasks);
        taskComboBox.setPromptText("Select a Task to Complete");

        // Customize ComboBox display
        taskComboBox.setCellFactory(param -> new ListCell<HousekeepingTask>() {
            @Override
            protected void updateItem(HousekeepingTask task, boolean empty) {
                super.updateItem(task, empty);
                if (empty || task == null) {
                    setText(null);
                } else {
                    setText("Task ID: " + task.getId() +
                            " | Description: " + task.getTaskDescription() +
                            " | Scheduled: " + task.getTaskDate().toString());
                }
            }
        });

        taskComboBox.setButtonCell(new ListCell<HousekeepingTask>() {
            @Override
            protected void updateItem(HousekeepingTask task, boolean empty) {
                super.updateItem(task, empty);
                if (empty || task == null) {
                    setText(null);
                } else {
                    setText("Task ID: " + task.getId() +
                            " | Description: " + task.getTaskDescription() +
                            " | Scheduled: " + task.getTaskDate().toString());
                }
            }
        });

        // Complete button
        Button completeButton = new Button("Mark as Completed");
        completeButton.setOnAction(e -> {
            HousekeepingTask selectedTask = taskComboBox.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                // Confirm action
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirm Completion");
                confirmationAlert.setHeaderText(null);
                confirmationAlert.setContentText("Are you sure you want to mark Task ID " + selectedTask.getId() + " as completed?");
    
                ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
    
                confirmationAlert.getButtonTypes().setAll(yesButton, noButton);
    
                confirmationAlert.showAndWait().ifPresent(type -> {
                    if (type == yesButton) {
                        boolean success = viewModel.completeTask(selectedTask.getId());
                        if (success) {
                            showAlert(Alert.AlertType.INFORMATION, "Success", "Task marked as completed.");
                            observableTasks.remove(selectedTask);
                            taskComboBox.getSelectionModel().clearSelection();
                        } else {
                            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update task status.");
                        }
                    }
                });
            } else {
                showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a task to mark as completed.");
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new HousekeepingMenuView(housekeeperId).show(stage, housekeeperId));
        // Layout setup
        VBox layout = new VBox(15, taskComboBox, completeButton, backButton);
        layout.setPadding(new Insets(20));

        // Scene setup
        Scene scene = new Scene(layout, 500, 200);
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
