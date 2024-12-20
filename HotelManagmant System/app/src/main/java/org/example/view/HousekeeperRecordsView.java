package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.model.Housekeeping;
import org.example.model.HousekeepingTask;
import org.example.viewmodel.HousekeeperRecordsViewModel;

import java.time.LocalDate;
import java.util.List;

public class HousekeeperRecordsView {
    private final HousekeeperRecordsViewModel viewModel;

    public HousekeeperRecordsView(long housekeeperId) {
        this.viewModel = new HousekeeperRecordsViewModel(housekeeperId);
    }
    

    public void show(Stage stage, Long receptionistId) {
        stage.setTitle("Housekeeper Records and Availability");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Label and ComboBox for selecting housekeeper
        Label housekeeperLabel = new Label("Select Housekeeper:");
        ComboBox<Housekeeping> housekeeperCombo = new ComboBox<>();
        List<Housekeeping> housekeepers = viewModel.getHousekeepingService().getAllHousekeepers();
        housekeeperCombo.setItems(FXCollections.observableArrayList(housekeepers));
        housekeeperCombo.setPromptText("Choose Housekeeper");

        // TableView for displaying tasks
        TableView<HousekeepingTask> taskTable = new TableView<>();
        ObservableList<HousekeepingTask> tasks = FXCollections.observableArrayList();
        taskTable.setItems(tasks);

        TableColumn<HousekeepingTask, String> descriptionCol = new TableColumn<>("Task Description");
        descriptionCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTaskDescription()));
        descriptionCol.setPrefWidth(200);

        TableColumn<HousekeepingTask, String> dateCol = new TableColumn<>("Task Date");
        dateCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTaskDate().toString()));
        dateCol.setPrefWidth(150);

        taskTable.getColumns().addAll(descriptionCol, dateCol);

        // Label and DatePicker for checking availability
        Label availabilityLabel = new Label("Check Availability on Date:");
        DatePicker availabilityDatePicker = new DatePicker();
        availabilityDatePicker.setValue(LocalDate.now());

        Button checkAvailabilityButton = new Button("Check Availability");
        checkAvailabilityButton.setOnAction(e -> {
            Housekeeping selectedHousekeeper = housekeeperCombo.getValue();
            LocalDate date = availabilityDatePicker.getValue();

            if (selectedHousekeeper == null || date == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Incomplete Information");
                alert.setHeaderText(null);
                alert.setContentText("Please select a housekeeper and a date.");
                alert.showAndWait();
                return;
            }

            // Check availability: if there are no tasks on the selected date
            List<HousekeepingTask> tasksOnDate = viewModel.getHousekeepingService().getHousekeeperTasks(selectedHousekeeper.getId());
            boolean isAvailable = tasksOnDate.stream().noneMatch(task -> task.getTaskDate().equals(date));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Availability Status");
            alert.setHeaderText(null);
            if (isAvailable) {
                alert.setContentText(selectedHousekeeper.getName() + " is available on " + date.toString() + ".");
            } else {
                alert.setContentText(selectedHousekeeper.getName() + " is not available on " + date.toString() + ".");
            }
            alert.showAndWait();
        });

        // Button to refresh tasks
        Button refreshButton = new Button("Refresh Tasks");
        refreshButton.setOnAction(e -> {
            Housekeeping selectedHousekeeper = housekeeperCombo.getValue();
            if (selectedHousekeeper == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Housekeeper Selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select a housekeeper.");
                alert.showAndWait();
                return;
            }

            List<HousekeepingTask> housekeeperTasks = viewModel.getTasksForHousekeeper(selectedHousekeeper.getId());
            tasks.setAll(housekeeperTasks);
        });

        // Button to close the view
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> new ReceptionistMenuView(receptionistId).show(stage, receptionistId));

        // Adding components to the grid
        grid.add(housekeeperLabel, 0, 0);
        grid.add(housekeeperCombo, 1, 0);
        grid.add(refreshButton, 2, 0);

        grid.add(new Label("Tasks:"), 0, 1);
        grid.add(taskTable, 0, 2, 3, 1); // Add the TableView spanning multiple columns

        grid.add(availabilityLabel, 0, 3);
        grid.add(availabilityDatePicker, 1, 3);
        grid.add(checkAvailabilityButton, 2, 3);

        grid.add(closeButton, 0, 4, 3, 1);

        Scene scene = new Scene(grid, 600, 600);
        stage.setScene(scene);
        stage.show();
    }
}
