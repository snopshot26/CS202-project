package org.example.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

        Button viewHousekeepersButton = new Button("View All Housekeepers Records and Their Availability");
        viewHousekeepersButton.setOnAction(e -> System.out.println("View All Housekeepers Records clicked."));

        Button backButton = new Button("Exit");
        backButton.setOnAction(e -> {
            receptionistStage.close();
            mainStage.show();
        });

        VBox layout = new VBox(10, receptionistLabel, addBookingButton, viewBookingsButton, assignHousekeepingTaskButton, viewHousekeepersButton, backButton);
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

        ListView<String> roomListView = new ListView<>();
        ObservableList<String> allRooms = FXCollections.observableArrayList("Room 101 - $100", "Room 102 - $150", "Room 103 - $200", "Room 104 - $120", "Room 105 - $90");
        roomListView.setItems(allRooms);

        Label dateFilterLabel = new Label("Filter by Dates (yyyy-mm-dd):");
        TextField startDateField = new TextField();
        startDateField.setPromptText("Start Date");
        TextField endDateField = new TextField();
        endDateField.setPromptText("End Date");

        Label priceFilterLabel = new Label("Filter by Price:");
        TextField minPriceField = new TextField();
        minPriceField.setPromptText("Min Price");
        TextField maxPriceField = new TextField();
        maxPriceField.setPromptText("Max Price");

        Button applyFilterButton = new Button("Apply Filter");
        applyFilterButton.setOnAction(e -> {
            try {
                double minPrice = minPriceField.getText().isEmpty() ? 0 : Double.parseDouble(minPriceField.getText());
                double maxPrice = maxPriceField.getText().isEmpty() ? Double.MAX_VALUE : Double.parseDouble(maxPriceField.getText());

                LocalDate startDate = startDateField.getText().isEmpty() ? LocalDate.MIN : LocalDate.parse(startDateField.getText(), DateTimeFormatter.ISO_DATE);
                LocalDate endDate = endDateField.getText().isEmpty() ? LocalDate.MAX : LocalDate.parse(endDateField.getText(), DateTimeFormatter.ISO_DATE);

                ObservableList<String> filteredRooms = allRooms.filtered(room -> {
                    String[] parts = room.split(" - \\$", 2);
                    double price = Double.parseDouble(parts[1]);
                    // Placeholder logic for dates; replace with actual room availability data
                    return price >= minPrice && price <= maxPrice && !startDate.isAfter(endDate);
                });

                roomListView.setItems(filteredRooms);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid price format.");
            } catch (DateTimeParseException ex) {
                System.out.println("Invalid date format.");
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            roomBookingStage.close();
            receptionistStage.show();
        });

        Button payButton = new Button("Pay");
        payButton.setOnAction(e -> {
            String selectedRoom = roomListView.getSelectionModel().getSelectedItem();
            if (selectedRoom != null) {
                showPaymentUI(receptionistStage, roomBookingStage, selectedRoom);
            } else {
                System.out.println("No room selected.");
            }
        });

        VBox filterLayout = new VBox(10, dateFilterLabel, startDateField, endDateField, priceFilterLabel, minPriceField, maxPriceField, applyFilterButton);
        VBox layout = new VBox(10, titleLabel, filterLayout, roomListView, new HBox(10, backButton, payButton));
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 500, 400);
        roomBookingStage.setTitle("Room Booking");
        roomBookingStage.setScene(scene);
        receptionistStage.hide();
        roomBookingStage.show();
    }

    private void showBookingListUI(Stage receptionistStage) {
        Stage bookingListStage = new Stage();

        Label bookingListLabel = new Label("Booking List");
        bookingListLabel.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");

        ListView<String> bookingListView = new ListView<>();
        ObservableList<String> bookings = FXCollections.observableArrayList(
            "Room 101 - $100 - Booked, Unpaid",
            "Room 102 - $150 - Booked, Paid",
            "Room 103 - $200 - Booked, Unpaid"
        );
        bookingListView.setItems(bookings);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            bookingListStage.close();
            receptionistStage.show();
        });

        Button payButton = new Button("Pay");
        payButton.setOnAction(e -> {
            String selectedBooking = bookingListView.getSelectionModel().getSelectedItem();
            if (selectedBooking != null && selectedBooking.contains("Unpaid")) {
                showPaymentUI(receptionistStage, bookingListStage, selectedBooking);
            } else {
                System.out.println("No unpaid booking selected.");
            }
        });

        VBox layout = new VBox(10, bookingListLabel, bookingListView, new HBox(10, backButton, payButton));
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 500, 400);
        bookingListStage.setTitle("Booking List");
        bookingListStage.setScene(scene);
        receptionistStage.hide();
        bookingListStage.show();
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

        VBox layout = new VBox(10, housekeepingLabel, roomField, new HBox(10, addTaskButton, backButton));
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 200);
        housekeepingTaskStage.setTitle("Assign Housekeeping Task");
        housekeepingTaskStage.setScene(scene);
        receptionistStage.hide();
        housekeepingTaskStage.show();
    }

    private void showPaymentUI(Stage receptionistStage, Stage previousStage, String selectedRoom) {
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
}
