package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HousekeepingMenuView {
    public void start(Stage mainStage) {
        Stage housekeepingStage = new Stage();

        // Create UI elements for Housekeeping Menu
        Label titleLabel = new Label("Housekeeping Menu");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

        Button viewTasksButton = new Button("View Housekeeping Tasks");
        viewTasksButton.setOnAction(e -> showHousekeepingTasks(housekeepingStage));

        Button viewScheduleButton = new Button("View My Cleaning Schedule");
        viewScheduleButton.setOnAction(e -> showCleaningSchedule(housekeepingStage));

        Button backButton = new Button("Exit");
        backButton.setOnAction(e -> {
            housekeepingStage.close();
            mainStage.show();
        });

        // Layout
        VBox layout = new VBox(10, titleLabel, viewTasksButton, viewScheduleButton, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 300);
        housekeepingStage.setTitle("Housekeeping Menu");
        housekeepingStage.setScene(scene);
        mainStage.hide();
        housekeepingStage.show();
    }

    private void showCleaningSchedule(Stage housekeepingStage) {
        Stage scheduleStage = new Stage();

        Label scheduleLabel = new Label("My Cleaning Schedule");
        scheduleLabel.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");

        ListView<String> scheduleListView = new ListView<>();
        // Placeholder items, replace with database items later
        scheduleListView.getItems().addAll("Room 101 - 10:00 AM", "Room 102 - 11:30 AM", "Room 103 - 1:00 PM");

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            scheduleStage.close();
            housekeepingStage.show();
        });

        VBox layout = new VBox(10, scheduleLabel, scheduleListView, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 300);
        scheduleStage.setTitle("Cleaning Schedule");
        scheduleStage.setScene(scene);
        housekeepingStage.hide();
        scheduleStage.show();
    }

    private void showHousekeepingTasks(Stage housekeepingStage) {
        Stage tasksStage = new Stage();

        Label tasksLabel = new Label("Housekeeping Tasks");
        tasksLabel.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");

        Label pendingLabel = new Label("Pending Tasks");
        ListView<String> pendingTasksList = new ListView<>();
        // Placeholder items
        pendingTasksList.getItems().addAll("Room 201", "Room 202", "Room 203");

        Label completedLabel = new Label("Completed Tasks");
        ListView<String> completedTasksList = new ListView<>();
        // Placeholder items
        completedTasksList.getItems().addAll("Room 101", "Room 102");

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            tasksStage.close();
            housekeepingStage.show();
        });

        Button updateTaskButton = new Button("Update Task Status to Completed");
        updateTaskButton.setOnAction(e -> {
            String selectedTask = pendingTasksList.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                pendingTasksList.getItems().remove(selectedTask);
                completedTasksList.getItems().add(selectedTask);
            }
        });

        HBox listsLayout = new HBox(20, new VBox(10, pendingLabel, pendingTasksList), new VBox(10, completedLabel, completedTasksList));
        VBox layout = new VBox(10, tasksLabel, listsLayout, updateTaskButton, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 600, 400);
        tasksStage.setTitle("Housekeeping Tasks");
        tasksStage.setScene(scene);
        housekeepingStage.hide();
        tasksStage.show();
    }
}
