// package org.example.view;

// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;
// import org.example.model.Hotel;

// public class HotelListView {
//     public void showHotelList(Stage primaryStage) {
//         TableView<Hotel> table = new TableView<>();
//         ObservableList<Hotel> hotels = getHotels();

//         TableColumn<Hotel, String> nameColumn = new TableColumn<>("Hotel Name");
//         nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

//         TableColumn<Hotel, String> addressColumn = new TableColumn<>("Address");
//         addressColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAddress()));

//         table.getColumns().addAll(nameColumn, addressColumn);
//         table.setItems(hotels);

//         Button viewRoomsButton = new Button("View Rooms");
//         viewRoomsButton.setOnAction(e -> {
//             Hotel selected = table.getSelectionModel().getSelectedItem();
//             if (selected != null) {
//                 new RoomView().showRooms(primaryStage, selected);
//             }
//         });

//         Button backButton = new Button("Back");
//         backButton.setOnAction(e -> new GuestMenuView().showGuestMenu(primaryStage));

//         VBox layout = new VBox(10, table, viewRoomsButton, backButton);
//         layout.setStyle("-fx-padding: 20px;");
//         Scene scene = new Scene(layout, 600, 400);

//         primaryStage.setScene(scene);
//         primaryStage.setTitle("Available Hotels");
//     }

//     private ObservableList<Hotel> getHotels() {
//         return FXCollections.observableArrayList(
//                 new Hotel("Sunshine Hotel", "123 Sunshine Blvd"),
//                 new Hotel("Mountain Retreat", "456 Mountain Rd")
//         );
//     }
// }
