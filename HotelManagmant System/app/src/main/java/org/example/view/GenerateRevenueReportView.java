package org.example.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.viewmodel.GenerateRevenueReportViewModel;

public class GenerateRevenueReportView {
    private final GenerateRevenueReportViewModel viewModel;

    public GenerateRevenueReportView() {
        this.viewModel = new GenerateRevenueReportViewModel();
    }

    public void show(Stage stage) {
        stage.setTitle("Generate Revenue Report");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label selectPeriodLabel = new Label("Select Period:");
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setPromptText("Start Date");
        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setPromptText("End Date");

        Button generateButton = new Button("Generate Report");
        generateButton.setOnAction(e -> {
            var startDate = startDatePicker.getValue();
            var endDate = endDatePicker.getValue();

            if (startDate == null || endDate == null) {
                showAlert(Alert.AlertType.WARNING, "Incomplete Information", "Please select both start and end dates.");
                return;
            }

            double revenue = viewModel.generateRevenueReport(startDate, endDate);
            showAlert(Alert.AlertType.INFORMATION, "Revenue Report", "Total Revenue from " + startDate + " to " + endDate + " is $" + revenue);
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        layout.getChildren().addAll(selectPeriodLabel, startDatePicker, endDatePicker, generateButton, closeButton);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
