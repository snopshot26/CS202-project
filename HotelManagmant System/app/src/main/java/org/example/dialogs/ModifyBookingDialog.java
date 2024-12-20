package org.example.dialogs;

import java.time.LocalDate;
import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ModifyBookingDialog {
    private final Dialog<LocalDate[]> dialog;
    private final DatePicker checkInDatePicker;
    private final DatePicker checkOutDatePicker;

    public ModifyBookingDialog() {
        this.dialog = new Dialog<>();
        dialog.setTitle("Modify Booking Dates");
        dialog.setHeaderText("Select new check-in and check-out dates");

        ButtonType okButton = new ButtonType("OK", ButtonType.OK.getButtonData());
        ButtonType cancelButton = new ButtonType("Cancel", ButtonType.CANCEL.getButtonData());
        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

        checkInDatePicker = new DatePicker();
        checkOutDatePicker = new DatePicker();

        VBox vbox = new VBox(10, new Label("New Check-In Date:"), checkInDatePicker,
                                  new Label("New Check-Out Date:"), checkOutDatePicker);
        vbox.setStyle("-fx-padding: 20px;");

        dialog.getDialogPane().setContent(vbox);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButton) {
                LocalDate[] dates = new LocalDate[2];
                dates[0] = checkInDatePicker.getValue();
                dates[1] = checkOutDatePicker.getValue();
                return dates;
            }
            return null;
        });
    }

    public Optional<LocalDate[]> showAndWait() {
        return dialog.showAndWait();
    }
}
