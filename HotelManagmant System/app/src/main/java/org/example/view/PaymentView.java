package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.enums.PaymentMethod;
import org.example.model.Hotel;
import org.example.model.Room;
import org.example.viewmodel.PaymentViewModel;

import java.time.LocalDate;

public class PaymentView {

    private final PaymentViewModel viewModel;

    public PaymentView(long guestId, Hotel hotel, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.viewModel = new PaymentViewModel(guestId, hotel, room, checkInDate, checkOutDate);
    }

    public void show(Stage stage, long guestId, Hotel hotel, Room room, LocalDate checkInDate, LocalDate checkOutDate) {

        Label infoLabel = new Label("Choose payment method:");

        ComboBox<PaymentMethod> paymentMethodCombo = new ComboBox<>();
        paymentMethodCombo.getItems().addAll(PaymentMethod.values());
        paymentMethodCombo.setValue(PaymentMethod.CREDIT_CARD);

        Button proceedButton = new Button("Proceed");
        proceedButton.setOnAction(e -> {
            PaymentMethod selectedMethod = paymentMethodCombo.getValue();
            if (selectedMethod == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Payment Method Selected");
                alert.setHeaderText("Payment method not selected");
                alert.setContentText("Please choose a payment method.");
                alert.showAndWait();
                return;
            }

            boolean success = viewModel.proceedPayment(selectedMethod);
            if (success) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Booking Confirmed");
                successAlert.setHeaderText("Booking successful");
                if (selectedMethod == PaymentMethod.CASH) {
                    successAlert.setContentText("Your booking is confirmed. Payment is pending and will be done on arrival.");
                } else {
                    successAlert.setContentText("Your booking is confirmed and payment is completed.");
                }
                successAlert.showAndWait();

                // Вернёмся, например, к BookingView или GuestMenu
                new GuestMenuView().show(stage, guestId);
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Booking Error");
                errorAlert.setHeaderText("Unable to proceed with booking");
                errorAlert.setContentText("An error occurred during the booking/payment process.");
                errorAlert.showAndWait();
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            // Возвращаемся к RoomListView или GuestMenu
            new RoomListView(hotel.getId()).show(stage, hotel, guestId, checkInDate, checkOutDate);
        });

        VBox layout = new VBox(10, infoLabel, paymentMethodCombo, proceedButton, cancelButton);
        layout.setStyle("-fx-padding: 20px;");
        Scene scene = new Scene(layout, 400, 200);

        stage.setScene(scene);
        stage.setTitle("Payment");
    }
}
