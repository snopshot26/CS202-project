package org.example.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.enums.PaymentMethod;
import org.example.model.Guest;
import org.example.model.Hotel;
import org.example.model.Room;
import org.example.viewmodel.PaymentViewModel;

import java.time.LocalDate;
import java.util.Optional;

public class PaymentView {

    private final PaymentViewModel viewModel;

    public PaymentView(long userId, Hotel hotel, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        this.viewModel = new PaymentViewModel(userId, hotel, room, checkInDate, checkOutDate);
    }

    public void show(Stage stage, long userId, Hotel hotel, Room room, LocalDate checkInDate, LocalDate checkOutDate) {

        Label infoLabel = new Label("Choose payment method:");
        infoLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        ComboBox<PaymentMethod> paymentMethodCombo = new ComboBox<>();
        paymentMethodCombo.getItems().addAll(PaymentMethod.values());
        paymentMethodCombo.setValue(PaymentMethod.CREDIT_CARD);

        // Если пользователь — ресепшионист, добавляем поля для ввода данных гостя
        VBox guestInfoBox = new VBox(10);
        // Используем final массивы для хранения ссылок на поля ввода
        final TextField[] nameField = new TextField[1];
        final TextField[] emailField = new TextField[1];
        final TextField[] phoneField = new TextField[1];
        final PasswordField[] passwordField = new PasswordField[1];

        if (viewModel.isReceptionist()) {
            Label guestInfoLabel = new Label("Enter Guest Information:");
            guestInfoLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            nameField[0] = new TextField();
            nameField[0].setPromptText("Name");

            emailField[0] = new TextField();
            emailField[0].setPromptText("Email");

            phoneField[0] = new TextField();
            phoneField[0].setPromptText("Phone Number");

            passwordField[0] = new PasswordField();
            passwordField[0].setPromptText("Password");

            guestInfoBox.getChildren().addAll(
                    guestInfoLabel,
                    new Label("Name:"), nameField[0],
                    new Label("Email:"), emailField[0],
                    new Label("Phone Number:"), phoneField[0],
                    new Label("Password:"), passwordField[0]
            );
        }

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

            // Если пользователь — ресепшионист, собираем данные гостя и создаём его
            if (viewModel.isReceptionist()) {
                if (nameField[0] == null || emailField[0] == null || phoneField[0] == null || passwordField[0] == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Internal Error");
                    alert.setHeaderText("Missing Fields");
                    alert.setContentText("Guest information fields are not properly initialized.");
                    alert.showAndWait();
                    return;
                }

                String name = nameField[0].getText().trim();
                String email = emailField[0].getText().trim();
                String phone = phoneField[0].getText().trim();
                String password = passwordField[0].getText().trim();

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Incomplete Information");
                    alert.setHeaderText("Missing Guest Information");
                    alert.setContentText("Please fill in all guest information fields.");
                    alert.showAndWait();
                    return;
                }

                // Создаём нового гостя
                Guest newGuest = viewModel.createGuest(name, email, phone, password);
                if (newGuest == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Creation Error");
                    alert.setHeaderText("Unable to create guest");
                    alert.setContentText("An error occurred while creating the guest.");
                    alert.showAndWait();
                    return;
                }
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

                // Вернёмся к GuestMenuView или BookingView
                new GuestMenuView().show(stage, userId);
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
            // Возвращаемся к RoomListView или GuestMenuView
            new RoomListView(hotel.getId()).show(stage, hotel, userId, checkInDate, checkOutDate);
        });

        VBox layout = new VBox(15, infoLabel, paymentMethodCombo);
        if (viewModel.isReceptionist()) {
            layout.getChildren().add(guestInfoBox);
        }
        layout.getChildren().addAll(proceedButton, cancelButton);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f0f0f0;");

        Scene scene = new Scene(layout, 400, viewModel.isReceptionist() ? 500 : 300);
        stage.setScene(scene);
        stage.setTitle("Payment");
        stage.show();
    }
}
