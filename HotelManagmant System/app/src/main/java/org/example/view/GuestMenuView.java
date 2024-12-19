package org.example.view;

import org.example.model.Booking;
import org.example.model.Hotel;
import org.example.model.Room;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuestMenuView extends Application {

    @Override
    public void start(Stage mainStage) {
        Stage guestStage = new Stage();

        Label guestLabel = new Label("Welcome to the Guest Menu");
        guestLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

        Button viewMyBookingsButton = new Button("View My Bookings");
        viewMyBookingsButton.setOnAction(e -> openBookingsUI(guestStage, mainStage));

        Button addBookingButton = new Button("Add Booking");
        addBookingButton.setOnAction(e -> openHotelListUI(guestStage));

        Button backButton = new Button("Exit");
        backButton.setOnAction(e -> {
            guestStage.close();
            mainStage.show();
        });

        VBox layout = new VBox(10, guestLabel, viewMyBookingsButton, addBookingButton, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 300);
        guestStage.setTitle("Guest Menu");
        guestStage.setScene(scene);
        guestStage.show();
    }

    private void openBookingsUI(Stage guestStage, Stage mainStage) {
        Stage bookingsStage = new Stage();

        TableView<Booking> table = new TableView<>();
        ObservableList<Booking> bookings = getBookings();

        TableColumn<Booking, String> startDateColumn = new TableColumn<>("Start Date");
        startDateColumn.setCellValueFactory(data -> data.getValue().startDateProperty());
        startDateColumn.setPrefWidth(100);

        TableColumn<Booking, String> endDateColumn = new TableColumn<>("End Date");
        endDateColumn.setCellValueFactory(data -> data.getValue().endDateProperty());
        endDateColumn.setPrefWidth(100);

        TableColumn<Booking, String> roomIDColumn = new TableColumn<>("Room ID");
        roomIDColumn.setCellValueFactory(data -> data.getValue().roomIDProperty());
        roomIDColumn.setPrefWidth(100);

        TableColumn<Booking, Number> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(data -> data.getValue().priceProperty());
        priceColumn.setPrefWidth(100);

        TableColumn<Booking, Number> roomNumberColumn = new TableColumn<>("Room Number");
        roomNumberColumn.setCellValueFactory(data -> data.getValue().roomNumberProperty());
        roomNumberColumn.setPrefWidth(100);

        TableColumn<Booking, String> roomTypeColumn = new TableColumn<>("Room Type");
        roomTypeColumn.setCellValueFactory(data -> data.getValue().roomTypeProperty());
        roomTypeColumn.setPrefWidth(100);

        TableColumn<Booking, String> hotelIDColumn = new TableColumn<>("Hotel ID");
        hotelIDColumn.setCellValueFactory(data -> data.getValue().hotelIDProperty());
        hotelIDColumn.setPrefWidth(100);

        TableColumn<Booking, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(data -> data.getValue().statusProperty());
        statusColumn.setPrefWidth(100);

        table.getColumns().addAll(startDateColumn, endDateColumn, roomIDColumn, priceColumn, roomNumberColumn, roomTypeColumn, hotelIDColumn, statusColumn);

        table.setItems(bookings);

        Button cancelButton = new Button("Cancel Booking");
        cancelButton.setOnAction(e -> {
            Booking selectedBooking = table.getSelectionModel().getSelectedItem();
            if (selectedBooking != null) {
                selectedBooking.setStatus("Canceled");
                table.refresh();
                System.out.println("Booking canceled: " + selectedBooking);
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            bookingsStage.close();
            guestStage.show();
        });

        VBox layout = new VBox(10, table, cancelButton, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 800, 600);
        bookingsStage.setTitle("My Bookings");
        bookingsStage.setScene(scene);
        guestStage.hide();
        bookingsStage.show();
    }

    private void openHotelListUI(Stage guestStage) {
        Stage hotelListStage = new Stage();

        Label filterLabel = new Label("Filter by Dates:");
        DatePicker startDatePicker = new DatePicker();
        DatePicker endDatePicker = new DatePicker();
        Button filterButton = new Button("Filter");

        TableView<Hotel> hotelTable = new TableView<>();
        ObservableList<Hotel> hotels = getHotels();

        TableColumn<Hotel, String> hotelIDColumn = new TableColumn<>("Hotel ID");
        hotelIDColumn.setCellValueFactory(data -> data.getValue().hotelIDProperty());
        hotelIDColumn.setPrefWidth(100);

        TableColumn<Hotel, String> hotelNameColumn = new TableColumn<>("Hotel Name");
        hotelNameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        hotelNameColumn.setPrefWidth(150);

        TableColumn<Hotel, String> roomTypeColumn = new TableColumn<>("Room Type");
        roomTypeColumn.setCellValueFactory(data -> data.getValue().roomTypeProperty());
        roomTypeColumn.setPrefWidth(150);

        TableColumn<Hotel, Number> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(data -> data.getValue().priceProperty());
        priceColumn.setPrefWidth(100);

        TableColumn<Hotel, Number> freeRoomsColumn = new TableColumn<>("Number Of Free Rooms");
        freeRoomsColumn.setCellValueFactory(data -> data.getValue().freeRoomsProperty());
        freeRoomsColumn.setPrefWidth(150);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            hotelListStage.close();
            guestStage.show();
        });

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getEventType() == MouseEvent.MOUSE_CLICKED && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                    Hotel selectedHotel = hotelTable.getSelectionModel().getSelectedItem();
                    if (selectedHotel!= null) {
                        openRoomsUI(hotelListStage, selectedHotel);
                    }
                }
            }
        };

        hotelTable.addEventHandler(EventType.ROOT, eventHandler);

        hotelTable.getColumns().addAll(hotelIDColumn, hotelNameColumn, roomTypeColumn, priceColumn, freeRoomsColumn);
        hotelTable.setItems(hotels);

        VBox layout = new VBox(10, filterLabel, startDatePicker, endDatePicker, filterButton, hotelTable, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 800, 600);
        hotelListStage.setTitle("Hotel List");
        hotelListStage.setScene(scene);
        guestStage.hide();
        hotelListStage.show();
    }

    private ObservableList<Booking> getBookings() {
        return FXCollections.observableArrayList(
                new Booking("2024-12-01", "2024-12-05", "R101", 300.0, 101, "Single", "H001", "Confirmed"),
                new Booking("2024-12-10", "2024-12-15", "R202", 500.0, 202, "Double", "H002", "Pending")
        );
    }

    private ObservableList<Hotel> getHotels() {
        return FXCollections.observableArrayList(
                new Hotel("H001", "Hotel Sunshine", "New York", 4.5, "Single", 120.0, 5, true),
                new Hotel("H002", "Mountain Retreat", "Denver", 4.8, "Double", 200.0, 2, false),
                new Hotel("H003", "Ocean View", "Miami", 4.2, "Suite", 300.0, 8, true)
        );
    }

    private void openRoomsUI(Stage hotelListStage, Hotel hotel) {
        Stage roomsStage = new Stage();

        TableView<Room> roomTable = new TableView<>();
        ObservableList<Room> rooms = hotel.getRooms();

        TableColumn<Room, String> roomIDColumn = new TableColumn<>("Room ID");
        roomIDColumn.setCellValueFactory(data -> data.getValue().roomIDProperty());
        roomIDColumn.setPrefWidth(100);

        TableColumn<Room, String> roomTypeColumn = new TableColumn<>("Room Type");
        roomTypeColumn.setCellValueFactory(data -> data.getValue().roomTypeProperty());
        roomTypeColumn.setPrefWidth(150);

        TableColumn<Room, Number> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(data -> data.getValue().priceProperty());
        priceColumn.setPrefWidth(100);

        Button chooseButton = new Button("Choose");
        chooseButton.setOnAction(e -> {
            Room selectedRoom = roomTable.getSelectionModel().getSelectedItem();
            if (selectedRoom != null) {
                System.out.println("Room chosen: " + selectedRoom);
                roomsStage.close();
                openPaymentUI(hotelListStage, selectedRoom);
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            roomsStage.close();
            hotelListStage.show();
        });

        roomTable.getColumns().addAll(roomIDColumn, roomTypeColumn, priceColumn);
        roomTable.setItems(rooms);

        VBox layout = new VBox(10, roomTable, chooseButton, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 600, 400);
        roomsStage.setTitle("Rooms List");
        roomsStage.setScene(scene);
        hotelListStage.hide();
        roomsStage.show();
    }

    private void openPaymentUI(Stage hotelListStage, Room selectedRoom) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}