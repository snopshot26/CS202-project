// package org.example.view;

// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ListView;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;
// import org.example.model.Room;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// public class ViewMostBookedRoomTypesView {
//     private final Stage stage;
//     private final List<Room> rooms;

//     public ViewMostBookedRoomTypesView(Stage stage, List<Room> rooms) {
//         this.stage = stage;
//         this.rooms = rooms;
//     }

//     public void show() {
//         Label titleLabel = new Label("Most Booked Room Types");
//         titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

//         ListView<String> roomTypeListView = new ListView<>();

//         Button generateButton = new Button("Generate");
//         generateButton.setOnAction(e -> {
//             Map<String, Integer> roomTypeCount = new HashMap<>();

//             for (Room room : rooms) {
//                 if (room.getStatus().equals("Booked")) {
//                     roomTypeCount.put(room.getRoomType(), roomTypeCount.getOrDefault(room.getRoomType(), 0) + 1);
//                 }
//             }

//             roomTypeListView.getItems().clear();
//             roomTypeCount.forEach((type, count) -> roomTypeListView.getItems().add(type + ": " + count + " bookings"));
//         });

//         Button backButton = new Button("Back");
//         backButton.setOnAction(e -> new AdministratorMenuView(stage).show());

//         VBox layout = new VBox(10, titleLabel, generateButton, roomTypeListView, backButton);
//         layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

//         Scene scene = stage.getScene();
//         scene.setRoot(layout);
//         stage.setTitle("Most Booked Room Types");
//     }
// }
