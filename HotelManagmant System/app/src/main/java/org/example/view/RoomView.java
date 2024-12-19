// package org.example.view;

// import org.example.enums.RoomStatus;
// import org.example.enums.RoomType;
// import org.example.model.Hotel;
// import org.example.model.Room;

// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;

// public class RoomView {
//     public void showRooms(Stage primaryStage, Hotel hotel) {
//         TableView<Room> table = new TableView<>();
//         ObservableList<Room> rooms = getRooms(hotel);

//         TableColumn<Room, String> roomNumberColumn = new TableColumn<>("Room Number");
//         roomNumberColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRoomNumber()));

//         TableColumn<Room, RoomType> roomTypeColumn = new TableColumn<>("Room Type");
//         roomTypeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getRoomType()));

//         TableColumn<Room, Double> priceColumn = new TableColumn<>("Price");
//         priceColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getPrice()));

//         table.getColumns().addAll(roomNumberColumn, roomTypeColumn, priceColumn);
//         table.setItems(rooms);

//         Button backButton = new Button("Back");
//         backButton.setOnAction(e -> new HotelListView().showHotelList(primaryStage));

//         VBox layout = new VBox(10, table, backButton);
//         layout.setStyle("-fx-padding: 20px;");
//         Scene scene = new Scene(layout, 600, 400);

//         primaryStage.setScene(scene);
//         primaryStage.setTitle("Rooms in " + hotel.getName());
//     }

//     private ObservableList<Room> getRooms(Hotel hotel) {
//         return FXCollections.observableArrayList(
//                 new Room(hotel, "101", RoomType.SINGLE, 100, RoomStatus.AVAILABLE),
//                 new Room(hotel, "102", RoomType.DOUBLE, 150, RoomStatus.AVAILABLE)
//         );
//     }
// }
