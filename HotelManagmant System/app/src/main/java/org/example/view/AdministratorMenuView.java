// package org.example.view;

// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;

// public class AdministratorMenuView {
//     private final Stage stage;

//     public AdministratorMenuView(Stage stage) {
//         this.stage = stage;
//     }

//     public void show() {
//         Label titleLabel = new Label("Administrator Menu");
//         titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

//         Button addRoomButton = new Button("Add Room");
//         addRoomButton.setOnAction(e -> new AddRoomView(stage).show());

//         Button deleteRoomButton = new Button("Delete Room");
//         deleteRoomButton.setOnAction(e -> new DeleteRoomView(stage).show());

//         Button manageRoomButton = new Button("Manage Room Status");
//         manageRoomButton.setOnAction(e -> new ManageRoomStatusView(stage).show());

//         Button backButton = new Button("Back to Login");
//         backButton.setOnAction(e -> new LoginWindow().start(stage));

//         VBox layout = new VBox(10, titleLabel, addRoomButton, deleteRoomButton, manageRoomButton, backButton);
//         layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

//         Scene scene = stage.getScene();
//         scene.setRoot(layout);
//         stage.setTitle("Administrator Menu");
//     }
// }
