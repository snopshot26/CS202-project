package org.example.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.example.viewmodel.HousekeeperRecordsViewModel;

public class HousekeepingMenuView {

    private final HousekeeperRecordsViewModel viewModel;


    public HousekeepingMenuView(long housekeeperId) {
        this.viewModel = new HousekeeperRecordsViewModel(housekeeperId);
    }


    public void show(Stage stage,long housekeeperId) {
        stage.setTitle("Housekeeping Menu");

        // Create buttons for each option
        Button viewPendingTasksButton = new Button("View Pending Housekeeping Tasks");
        Button viewCompletedTasksButton = new Button("View Completed Housekeeping Tasks");
        Button updateTaskStatusButton = new Button("Update Task Status to Completed");
        Button viewCleaningScheduleButton = new Button("View My Cleaning Schedule");

        // Set button actions
        viewPendingTasksButton.setOnAction(e -> {
            ViewPendingTasksView pendingTasksView = new ViewPendingTasksView(housekeeperId);
            pendingTasksView.show(stage);
        });

        viewCompletedTasksButton.setOnAction(e -> {
            ViewCompletedTasksView completedTasksView = new ViewCompletedTasksView(housekeeperId);
            completedTasksView.show(stage);
        });

        updateTaskStatusButton.setOnAction(e -> {
            UpdateTaskStatusView updateTaskStatusView = new UpdateTaskStatusView(housekeeperId);
            updateTaskStatusView.show(stage);
        });

        viewCleaningScheduleButton.setOnAction(e -> {
            ViewCleaningScheduleView cleaningScheduleView = new ViewCleaningScheduleView(housekeeperId);
            cleaningScheduleView.show(stage);
        });

        // Layout setup
        VBox layout = new VBox(15, viewPendingTasksButton, viewCompletedTasksButton, updateTaskStatusButton, viewCleaningScheduleButton);
        layout.setPadding(new Insets(20));
        layout.setPrefWidth(300);

        // Scene setup
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }
}
