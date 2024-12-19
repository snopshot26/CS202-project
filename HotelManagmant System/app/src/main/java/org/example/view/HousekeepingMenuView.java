// package org.example.view;

// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ListView;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;
// import org.example.model.Task;

// import java.util.ArrayList;
// import java.util.List;

// public class HousekeepingMenuView {
//     private final Stage stage;
//     private final List<Task> tasks;

//     public HousekeepingMenuView(Stage stage) {
//         this.stage = stage;
//         this.tasks = new ArrayList<>(); // Replace with shared data source for tasks
//     }

//     public void show() {
//         Label titleLabel = new Label("Housekeeping Menu");
//         titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

//         Button viewTasksButton = new Button("View Tasks");
//         viewTasksButton.setOnAction(e -> new ViewTasksView(stage, tasks).show());

//         Button backButton = new Button("Back to Administrator Menu");
//         backButton.setOnAction(e -> new AdministratorMenuView(stage).show());

//         VBox layout = new VBox(10, titleLabel, viewTasksButton, backButton);
//         layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

//         Scene scene = stage.getScene();
//         scene.setRoot(layout);
//         stage.setTitle("Housekeeping Menu");
//     }
// }
