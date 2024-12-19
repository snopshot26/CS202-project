package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReceptionistMenuView {

    private static class Room {
        private final String roomID;
        private double price;
        private final String roomNumber;
        private String roomType;
        private String status;

        public Room(String roomID, double price, String roomNumber, String roomType, String status) {
            this.roomID = roomID;
            this.price = price;
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.status = status;
        }

        @Override
        public String toString() {
            return roomNumber + " - " + roomType + " - $" + price + " - " + status;
        }

        public String getRoomID() {
            return roomID;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public String getRoomType() {
            return roomType;
        }

        public void setRoomType(String roomType) {
            this.roomType = roomType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public void start(Stage mainStage) {
        Stage receptionistStage = new Stage();

        Label receptionistLabel = new Label("Receptionist Menu");
        receptionistLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

        Button addBookingButton = new Button("Add Booking");
        addBookingButton.setOnAction(e -> showRoomBookingUI(receptionistStage));

        Button viewBookingsButton = new Button("View Bookings");
        viewBookingsButton.setOnAction(e -> showBookingListUI(receptionistStage));

        Button assignHousekeepingTaskButton = new Button("Assign Housekeeping Task");
        assignHousekeepingTaskButton.setOnAction(e -> showAssignHousekeepingTaskUI(receptionistStage));

        Button backButton = new Button("Exit");
        backButton.setOnAction(e -> {
            receptionistStage.close();
            mainStage.show();
        });

        VBox layout = new VBox(10, receptionistLabel, addBookingButton, viewBookingsButton, assignHousekeepingTaskButton, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 300);
        receptionistStage.setScene(scene);
        receptionistStage.setTitle("Receptionist Menu");
        mainStage.hide();
        receptionistStage.show();
    }

    private void showRoomBookingUI(Stage receptionistStage) {
        Stage roomBookingStage = new Stage();

        Label titleLabel = new Label("Room Booking");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");

        ListView<Room> roomListView = new ListView<>();
        ObservableList<Room> allRooms = FXCollections.observableArrayList(
            new Room("101", 100, "Room 101", "Single", "Available"),
            new Room("102", 150, "Room 102", "Double", "Booked"),
            new Room("103", 200, "Room 103", "Suite", "Available"),
            new Room("104", 120, "Room 104", "Family", "Maintenance"),
            new Room("105", 90, "Room 105", "Single", "Available")
        );
        roomListView.setItems(allRooms.filtered(room -> "Available".equals(room.getStatus())));

        TextField filterField = new TextField();
        filterField.setPromptText("Filter by Room Number");
        filterField.textProperty().addListener((obs, oldText, newText) -> {
            roomListView.setItems(allRooms.filtered(room -> room.getRoomNumber().contains(newText) && "Available".equals(room.getStatus())));
        });

        Button payButton = new Button("Pay");
        payButton.setOnAction(e -> {
            Room selectedRoom = roomListView.getSelectionModel().getSelectedItem();
            if (selectedRoom != null) {
                showPaymentUI(selectedRoom, roomBookingStage, receptionistStage);
            } else {
                System.out.println("No room selected for booking.");
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            roomBookingStage.close();
            receptionistStage.show();
        });

        VBox layout = new VBox(10, titleLabel, filterField, roomListView, new HBox(10, payButton, backButton));
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 400);
        roomBookingStage.setScene(scene);
        roomBookingStage.setTitle("Room Booking");
        receptionistStage.hide();
        roomBookingStage.show();
    }

    private void showBookingListUI(Stage receptionistStage) {
        Stage bookingListStage = new Stage();

        Label titleLabel = new Label("Booking List");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");

        ListView<Room> bookingListView = new ListView<>();
        ObservableList<Room> bookings = FXCollections.observableArrayList(
            new Room("201", 200, "Room 201", "Suite", "Booked"),
            new Room("202", 150, "Room 202", "Double", "Available"),
            new Room("203", 100, "Room 203", "Single", "Booked")
        );
        bookingListView.setItems(bookings);

        TextField filterField = new TextField();
        filterField.setPromptText("Filter by Room Number");
        filterField.textProperty().addListener((obs, oldText, newText) -> {
            bookingListView.setItems(bookings.filtered(room -> room.getRoomNumber().contains(newText)));
        });

        Button payButton = new Button("Pay");
        payButton.setOnAction(e -> {
            Room selectedRoom = bookingListView.getSelectionModel().getSelectedItem();
            if (selectedRoom != null && "Booked".equals(selectedRoom.getStatus())) {
                showPaymentUI(selectedRoom, bookingListStage, receptionistStage);
            } else {
                System.out.println("No valid booking selected for payment.");
            }
        });

        Button modifyButton = new Button("Modify");
        modifyButton.setOnAction(e -> {
            Room selectedRoom = bookingListView.getSelectionModel().getSelectedItem();
            if (selectedRoom != null) {
                showRoomModificationUI(selectedRoom, bookingListStage, receptionistStage);
            } else {
                System.out.println("No room selected for modification.");
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            bookingListStage.close();
            receptionistStage.show();
        });

        VBox layout = new VBox(10, titleLabel, filterField, bookingListView, new HBox(10, payButton, modifyButton, backButton));
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 400);
        bookingListStage.setScene(scene);
        bookingListStage.setTitle("Booking List");
        receptionistStage.hide();
        bookingListStage.show();
    }
    private void showPaymentUI(Room selectedRoom, Stage previousStage, Stage receptionistStage) {
        Stage paymentStage = new Stage();

        Label paymentLabel = new Label("Payment");
        paymentLabel.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");

        Label roomDetails = new Label("Selected Room: " + selectedRoom);
        Label paymentMethodLabel = new Label("Choose Payment Method:");

        RadioButton cardButton = new RadioButton("Credit/Debit Card");
        RadioButton cashButton = new RadioButton("Cash");
        ToggleGroup paymentGroup = new ToggleGroup();
        cardButton.setToggleGroup(paymentGroup);
        cashButton.setToggleGroup(paymentGroup);

        TextField cardNumberField = new TextField();
        cardNumberField.setPromptText("Card Number");
        TextField expiryDateField = new TextField();
        expiryDateField.setPromptText("Expiry Date (MM/YY)");
        TextField cvvField = new TextField();
        cvvField.setPromptText("CVV");

        VBox cardDetailsBox = new VBox(10, cardNumberField, expiryDateField, cvvField);
        cardDetailsBox.setVisible(false);

        cardButton.setOnAction(e -> cardDetailsBox.setVisible(true));
        cashButton.setOnAction(e -> cardDetailsBox.setVisible(false));

        Button confirmButton = new Button("Confirm Payment");
        confirmButton.setOnAction(e -> {
            if (cardButton.isSelected()) {
                System.out.println("Paid with Card: " + cardNumberField.getText());
            } else if (cashButton.isSelected()) {
                System.out.println("Paid with Cash.");
            }
            paymentStage.close();
            previousStage.close();
            receptionistStage.show();
        });

        VBox layout = new VBox(10, paymentLabel, roomDetails, paymentMethodLabel, cardButton, cashButton, cardDetailsBox, confirmButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 400);
        paymentStage.setTitle("Payment");
        paymentStage.setScene(scene);
        previousStage.hide();
        paymentStage.show();
    }

    private void showRoomModificationUI(Room selectedRoom, Stage previousStage, Stage receptionistStage) {
        Stage modificationStage = new Stage();

        Label modificationLabel = new Label("Modify Room Details");
        modificationLabel.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");

        TextField priceField = new TextField(String.valueOf(selectedRoom.getPrice()));
        priceField.setPromptText("Enter new price");

        TextField typeField = new TextField(selectedRoom.getRoomType());
        typeField.setPromptText("Enter new room type");

        Button saveButton = new Button("Save Changes");
        saveButton.setOnAction(e -> {
            try {
                double newPrice = Double.parseDouble(priceField.getText());
                String newType = typeField.getText();

                if (!newType.isEmpty()) {
                    selectedRoom.setPrice(newPrice);
                    selectedRoom.setRoomType(newType);
                    System.out.println("Room details updated: " + selectedRoom);
                } else {
                    System.out.println("Room type cannot be empty.");
                }

                modificationStage.close();
                previousStage.show();
            } catch (NumberFormatException ex) {
                System.out.println("Invalid price entered.");
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            modificationStage.close();
            previousStage.show();
        });

        VBox layout = new VBox(10, modificationLabel, priceField, typeField, new HBox(10, saveButton, backButton));
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 300);
        modificationStage.setScene(scene);
        modificationStage.setTitle("Modify Room");
        previousStage.hide();
        modificationStage.show();
    }

    private void showAssignHousekeepingTaskUI(Stage receptionistStage) {
        Stage housekeepingTaskStage = new Stage();

        Label housekeepingLabel = new Label("Assign Housekeeping Task");
        housekeepingLabel.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");

        TextField roomField = new TextField();
        roomField.setPromptText("Enter Room Number");

        Button addTaskButton = new Button("Add Task");
        addTaskButton.setOnAction(e -> {
            String roomNumber = roomField.getText();
            if (!roomNumber.isEmpty()) {
                System.out.println("Housekeeping task added for room: " + roomNumber);
                roomField.clear();
            } else {
                System.out.println("Room number cannot be empty.");
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            housekeepingTaskStage.close();
            receptionistStage.show();
        });

        VBox layout = new VBox(10, housekeepingLabel, roomField, addTaskButton, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 300);
        housekeepingTaskStage.setTitle("Assign Housekeeping Task");
        housekeepingTaskStage.setScene(scene);
        receptionistStage.hide();
        housekeepingTaskStage.show();
    }
}