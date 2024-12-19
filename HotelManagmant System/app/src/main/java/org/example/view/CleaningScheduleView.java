// package org.example.view;

// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ListView;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;

// public class CleaningScheduleView {
//     private final Stage stage;
//     private final Scene scene;

//     public CleaningScheduleView(Stage stage) {
//         this.stage = stage;
//         this.scene = stage.getScene();
//     }

//     public void show() {
//         Label scheduleLabel = new Label("My Cleaning Schedule");
//         scheduleLabel.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");

//         ListView<String> scheduleListView = new ListView<>();
//         // Placeholder items, replace with database items later
//         scheduleListView.getItems().addAll("Room 101 - 10:00 AM", "Room 102 - 11:30 AM", "Room 103 - 1:00 PM");

//         Button backButton = new Button("Back");
//         backButton.setOnAction(e -> new HousekeepingMenuView(stage).show());

//         VBox layout = new VBox(10, scheduleLabel, scheduleListView, backButton);
//         layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

//         scene.setRoot(layout); // Переключаем содержимое сцены
//         stage.setTitle("Cleaning Schedule");
//         stage.show();
//     }
// }
