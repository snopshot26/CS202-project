package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HousekeepingMenuView {
    private final Stage stage;
    private final Scene scene;

    public HousekeepingMenuView(Stage stage) {
        this.stage = stage;
        this.scene = stage.getScene();
    }

    public void show() {
        Label titleLabel = new Label("Housekeeping Menu");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

        Button viewTasksButton = new Button("View Housekeeping Tasks");
        viewTasksButton.setOnAction(e -> new HousekeepingTasksView(stage).show());

        Button viewScheduleButton = new Button("View My Cleaning Schedule");
        viewScheduleButton.setOnAction(e -> new CleaningScheduleView(stage).show());

        Button backButton = new Button("Exit");
        backButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, titleLabel, viewTasksButton, viewScheduleButton, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        scene.setRoot(layout); // Переключаем содержимое сцены
        stage.setTitle("Housekeeping Menu");
        stage.show();
    }
}
