package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HousekeepingTasksView {
    private final Stage stage;
    private final Scene scene;

    public HousekeepingTasksView(Stage stage) {
        this.stage = stage;
        this.scene = stage.getScene();
    }

    public void show() {
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
        backButton.setOnAction(e -> new HousekeepingMenuView(stage).show());

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

        scene.setRoot(layout); // Переключаем содержимое сцены
        stage.setTitle("Housekeeping Tasks");
        stage.show();
    }
}
