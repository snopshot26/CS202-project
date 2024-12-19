// package org.example.view;

// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;
// import org.example.model.Task;

// import java.util.List;

// public class AddTaskView {
//     private final Stage stage;
//     private final List<Task> tasks;

//     public AddTaskView(Stage stage, List<Task> tasks) {
//         this.stage = stage;
//         this.tasks = tasks;
//     }

//     public void show() {
//         Label titleLabel = new Label("Add Task");
//         titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

//         TextField roomNumberField = new TextField();
//         roomNumberField.setPromptText("Enter Room Number");

//         TextField descriptionField = new TextField();
//         descriptionField.setPromptText("Enter Task Description");

//         Button addButton = new Button("Add Task");
//         addButton.setOnAction(e -> {
//             String roomNumber = roomNumberField.getText();
//             String description = descriptionField.getText();

//             if (!roomNumber.isEmpty() && !description.isEmpty()) {
//                 Task newTask = new Task(String.valueOf(tasks.size() + 1), description, roomNumber, "Pending");
//                 tasks.add(newTask);
//                 System.out.println("Task added: " + newTask);
//             }
//         });

//         Button backButton = new Button("Back");
//         backButton.setOnAction(e -> new HousekeepingMenuView(stage).show());

//         VBox layout = new VBox(10, titleLabel, roomNumberField, descriptionField, addButton, backButton);
//         layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

//         Scene scene = stage.getScene();
//         scene.setRoot(layout);
//         stage.setTitle("Add Task");
//     }
// }
