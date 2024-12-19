// package org.example.view;

// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.ComboBox;
// import javafx.scene.control.Label;
// import javafx.scene.control.ListView;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;
// import org.example.model.Room;

// import java.util.ArrayList;
// import java.util.List;

// public class ManageRoomStatusView {
//     private final Stage stage;
//     private final List<Room> rooms;

//     public ManageRoomStatusView(Stage stage) {
//         this.stage = stage;
//         this.rooms = new ArrayList<>(); // Replace with shared data source
//     }

//     public void show() {
//         Label titleLabel = new Label("Manage Room Status");
//         titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

//         ListView<String> roomListView = new ListView<>();
//         rooms.forEach(room -> roomListView.getItems().add(room.toString()));

//         ComboBox<String> statusComboBox = new ComboBox<>();
//         statusComboBox.getItems().addAll("Available", "Booked", "Maintenance", "Out of Service");
//         statusComboBox.setPromptText("Select New Status");

//         Button updateButton = new Button("Update Status");
//         updateButton.setOnAction(e -> {
//             String selectedRoom = roomListView.getSelectionModel().getSelectedItem();
//             String newStatus = statusComboBox.getValue();

//             if (selectedRoom != null && newStatus != null) {
//                 rooms.stream()
//                         .filter(room -> room.toString().equals(selectedRoom))
//                         .forEach(room -> room.setStatus(newStatus));
//                 System.out.println("Updated room status to: " + newStatus);
//             }
//         });

//         Button backButton = new Button("Back");
//         backButton.setOnAction(e -> new AdministratorMenuView(stage).show());

//         VBox layout = new VBox(10, titleLabel, roomListView, statusComboBox, updateButton, backButton);
//         layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

//         Scene scene = stage.getScene();
//         scene.setRoot(layout);
//         stage.setTitle("Manage Room Status");
//     }
// }
