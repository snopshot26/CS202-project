package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.Room;
import org.example.model.Task;

import java.util.List;

public class GenerateReportView {
    private final Stage stage;
    private final List<Room> rooms;
    private final List<Task> tasks;

    public GenerateReportView(Stage stage, List<Room> rooms, List<Task> tasks) {
        this.stage = stage;
        this.rooms = rooms;
        this.tasks = tasks;
    }

    public void show() {
        Label titleLabel = new Label("Generate Report");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

        TextArea reportArea = new TextArea();
        reportArea.setEditable(false);

        Button generateButton = new Button("Generate Report");
        generateButton.setOnAction(e -> {
            StringBuilder report = new StringBuilder();
            report.append("Room Report:\n");
            for (Room room : rooms) {
                report.append(room.toString()).append("\n");
            }

            report.append("\nTask Report:\n");
            for (Task task : tasks) {
                report.append(task.toString()).append("\n");
            }

            reportArea.setText(report.toString());
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new AdministratorMenuView(stage).show());

        VBox layout = new VBox(10, titleLabel, generateButton, reportArea, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = stage.getScene();
        scene.setRoot(layout);
        stage.setTitle("Generate Report");
    }
}
