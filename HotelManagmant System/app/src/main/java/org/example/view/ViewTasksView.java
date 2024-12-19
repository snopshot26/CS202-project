package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.Task;

import java.util.List;

public class ViewTasksView {
    private final Stage stage;
    private final List<Task> tasks;

    public ViewTasksView(Stage stage, List<Task> tasks) {
        this.stage = stage;
        this.tasks = tasks;
    }

    public void show() {
        Label titleLabel = new Label("View Tasks");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

        ListView<String> taskListView = new ListView<>();
        tasks.forEach(task -> taskListView.getItems().add(task.toString()));

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new HousekeepingMenuView(stage).show());

        VBox layout = new VBox(10, titleLabel, taskListView, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = stage.getScene();
        scene.setRoot(layout);
        stage.setTitle("View Tasks");
    }
}
