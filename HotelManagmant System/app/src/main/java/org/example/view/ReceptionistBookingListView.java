package org.example.view;

import java.time.LocalDate;
import java.util.Optional;

import org.example.dialogs.ModifyBookingDialog;
import org.example.model.Booking;
import org.example.viewmodel.ReceptionistBookingListViewModel;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReceptionistBookingListView {
    private final ReceptionistBookingListViewModel viewModel;

    public ReceptionistBookingListView(long receptionistId) {
        this.viewModel = new ReceptionistBookingListViewModel(receptionistId);
    }

    public void show(Stage stage, long receptionistId) {
        TableView<Booking> table = new TableView<>();
        table.setItems(viewModel.getBookings());

        TableColumn<Booking, String> checkInColumn = new TableColumn<>("Check-In Date");
        checkInColumn.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(data.getValue().getCheckInDate().toString()));

        TableColumn<Booking, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(data ->
            new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus().toString()));

        table.getColumns().addAll(checkInColumn, statusColumn);

        Button cancelButton = new Button("Cancel Booking");
        cancelButton.setOnAction(e -> {
            Booking selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                boolean canceled = viewModel.cancelBooking(selected);
                if (!canceled) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cancellation Error");
                    alert.setHeaderText("Unable to cancel booking");
                    alert.setContentText("This booking cannot be canceled because it is in the past.");
                    alert.showAndWait();
                } else {
                    table.refresh();
                }
            }
        });
        
        Button modifyButton = new Button("Modify");
        modifyButton.setOnAction(e -> {
            Booking selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                // Открываем диалог ввода дат
                ModifyBookingDialog dialog = new ModifyBookingDialog();
                Optional<LocalDate[]> result = dialog.showAndWait();

                if (result.isPresent()) {
                    LocalDate[] dates = result.get();
                    LocalDate newCheckIn = dates[0];
                    LocalDate newCheckOut = dates[1];

                    boolean modified = viewModel.modifyBooking(selected, newCheckIn, newCheckOut);
                    if (!modified) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Modification Error");
                        alert.setHeaderText("Unable to modify booking");
                        alert.setContentText("Room not available for these dates or booking is not modifiable.");
                        alert.showAndWait();
                    } else {
                        table.refresh();
                    }
                }
            }
        });


        Button payButton = new Button("Pay");
        payButton.setOnAction(e -> {
            Booking selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                // Проверим условия: бронирование не отменено, не оплачено, 
                // и дата заезда ещё не наступила.
                if (selected.getStatus() == org.example.enums.BookingStatus.CANCELED) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Payment Error");
                    alert.setHeaderText("Cannot pay for canceled booking");
                    alert.setContentText("This booking is canceled. Payment not possible.");
                    alert.showAndWait();
                    return;
                }
        
                if (selected.getPaymentStatus() == org.example.enums.PaymentStatus.COMPLETED) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Payment Error");
                    alert.setHeaderText("Already paid");
                    alert.setContentText("This booking is already paid.");
                    alert.showAndWait();
                    return;
                }
        
                if (!selected.getCheckInDate().isAfter(java.time.LocalDate.now())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Payment Error");
                    alert.setHeaderText("Cannot pay for a past or current booking");
                    alert.setContentText("Payment only possible for future bookings.");
                    alert.showAndWait();
                    return;
                }
        
                // Если все условия выполнены, открываем окно оплаты
                long guestId = selected.getGuest().getId(); // Предполагаем, что getId() есть у guest
                PaymentView paymentView = new PaymentView(
                    guestId,
                    selected.getHotel(),
                    selected.getRoom(),
                    selected.getCheckInDate(),
                    selected.getCheckOutDate()
                );
                paymentView.show(stage, guestId, selected.getHotel(), selected.getRoom(), selected.getCheckInDate(), selected.getCheckOutDate());
            }
        });
        

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new ReceptionistMenuView(receptionistId).show(stage,receptionistId));

        VBox layout = new VBox(10, table, cancelButton, modifyButton, payButton, backButton);
        layout.setStyle("-fx-padding: 20px;");

        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Booking Management");
    }
}
