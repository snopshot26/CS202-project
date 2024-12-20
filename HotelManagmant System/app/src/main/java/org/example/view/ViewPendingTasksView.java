package org.example.view;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.HousekeepingTask;
import org.example.viewmodel.HousekeepingMenuViewModel;

import java.util.List;

public class ViewPendingTasksView {
    private final HousekeepingMenuViewModel viewModel;
    private final long housekeeperId;
    
    public ViewPendingTasksView(long housekeeperId) {
        this.viewModel = new HousekeepingMenuViewModel();
        this.housekeeperId = housekeeperId;
    }

    public void show(Stage stage) {
        stage.setTitle("Pending Housekeeping Tasks");

        // Retrieve pending tasks
        List<HousekeepingTask> pendingTasks = viewModel.getPendingTasks(housekeeperId);
        ObservableList<HousekeepingTask> observableTasks = FXCollections.observableArrayList(pendingTasks);

        // Create TableView to display pending tasks
        TableView<HousekeepingTask> tableView = new TableView<>(observableTasks);

        // Define columns
        TableColumn<HousekeepingTask, Long> taskIdCol = new TableColumn<>("Task ID");
        taskIdCol.setCellValueFactory(data -> new SimpleLongProperty(data.getValue().getId()).asObject());

        TableColumn<HousekeepingTask, String> roomNumberCol = new TableColumn<>("Room Number");
        roomNumberCol.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getRoom().getRoomNumber()));

        TableColumn<HousekeepingTask, String> roomTypeCol = new TableColumn<>("Room Type");
        roomTypeCol.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getRoom().getRoomType().toString()));

        TableColumn<HousekeepingTask, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(data -> new SimpleDoubleProperty(
                data.getValue().getRoom().getPrice()).asObject());

        TableColumn<HousekeepingTask, String> scheduledDateCol = new TableColumn<>("Scheduled Date");
        scheduledDateCol.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getTaskDate().toString()));

        // Add columns to TableView
        tableView.getColumns().addAll(taskIdCol, roomNumberCol, roomTypeCol, priceCol, scheduledDateCol);
        
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new HousekeepingMenuView(housekeeperId).show(stage, housekeeperId));
        // Layout setup
        VBox layout = new VBox(10, tableView, backButton);
        layout.setPadding(new Insets(20));

        // Scene setup
        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
