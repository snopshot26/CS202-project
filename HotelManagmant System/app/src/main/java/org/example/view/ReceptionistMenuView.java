// package org.example.view;

// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ListView;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;
// import org.example.model.Room;

// import java.util.ArrayList;
// import java.util.List;

// public class ReceptionistMenuView {
//     private final Stage stage;
//     private final List<Room> bookings;

//     public ReceptionistMenuView(Stage stage) {
//         this.stage = stage;
//         this.bookings = new ArrayList<>(); // Replace with shared data source for bookings
//     }

//     public void show() {
//         Label titleLabel = new Label("Receptionist Menu");
//         titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

//         Button addBookingButton = new Button("Add Booking");
//         addBookingButton.setOnAction(e -> new AddBookingView(stage, bookings).show());

//         Button viewBookingsButton = new Button("View Bookings");
//         viewBookingsButton.setOnAction(e -> new ViewBookingsView(stage, bookings).show());

//         Button backButton = new Button("Back to Administrator Menu");
//         backButton.setOnAction(e -> new AdministratorMenuView(stage).show());

//         VBox layout = new VBox(10, titleLabel, addBookingButton, viewBookingsButton, backButton);
//         layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

//         Scene scene = stage.getScene();
//         scene.setRoot(layout);
//         stage.setTitle("Receptionist Menu");
//     }
// }
