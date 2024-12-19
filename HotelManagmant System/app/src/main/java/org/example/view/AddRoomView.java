// package org.example.view;

// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.TextField;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;
// import org.example.model.Room;

// import java.util.ArrayList;
// import java.util.List;

// public class AddRoomView {
//     private final Stage stage;
//     private final List<Room> rooms;

//     public AddRoomView(Stage stage) {
//         this.stage = stage;
//         this.rooms = new ArrayList<>(); // Replace with shared data source
//     }

//     public void show() {
//         Label titleLabel = new Label("Add Room");
//         titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

//         TextField roomNumberField = new TextField();
//         roomNumberField.setPromptText("Enter Room Number");

//         TextField roomTypeField = new TextField();
//         roomTypeField.setPromptText("Enter Room Type");

//         TextField priceField = new TextField();
//         priceField.setPromptText("Enter Room Price");

//         Button addButton = new Button("Add Room");
//         addButton.setOnAction(e -> {
//             String roomNumber = roomNumberField.getText();
//             String roomType = roomTypeField.getText();
//             double price;

//             try {
//                 price = Double.parseDouble(priceField.getText());
//                 Room newRoom = new Room(String.valueOf(rooms.size() + 1), roomNumber, roomType, price, "Available");
//                 rooms.add(newRoom);
//                 System.out.println("Room added: " + newRoom);
//             } catch (NumberFormatException ex) {
//                 System.out.println("Invalid price entered.");
//             }
//         });

//         Button backButton = new Button("Back");
//         backButton.setOnAction(e -> new AdministratorMenuView(stage).show());

//         VBox layout = new VBox(10, titleLabel, roomNumberField, roomTypeField, priceField, addButton, backButton);
//         layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

//         Scene scene = stage.getScene();
//         scene.setRoot(layout);
//         stage.setTitle("Add Room");
//     }
// }
