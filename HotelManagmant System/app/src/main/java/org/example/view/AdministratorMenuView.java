package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdministratorMenuView {
    public void start(Stage mainStage) {
        Stage adminStage = new Stage();
        Label adminLabel = new Label("Administrator Menu");
        adminLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button addRoomButton = new Button("Add Room");
        Button deleteRoomButton = new Button("Delete Room");
        Button manageRoomStatusButton = new Button("Manage Room Status");
        Button addUserAccountButton = new Button("Add User Account");
        Button viewUserAccountsButton = new Button("View User Accounts");
        Button generateRevenueReportButton = new Button("Generate Revenue Report");
        Button viewAllBookingRecordsButton = new Button("View All Booking Records");
        Button viewHousekeepingRecordsButton = new Button("View All Housekeeping Records");
        Button viewMostBookedRoomTypesButton = new Button("View Most Booked Room Types");
        Button viewEmployeesWithRolesButton = new Button("View All Employees with Their Roles");
        Button backButton = new Button("Back to Main Menu");

        addRoomButton.setOnAction(e -> System.out.println("Add Room selected."));
        deleteRoomButton.setOnAction(e -> System.out.println("Delete Room selected."));
        manageRoomStatusButton.setOnAction(e -> System.out.println("Manage Room Status selected."));
        addUserAccountButton.setOnAction(e -> System.out.println("Add User Account selected."));
        viewUserAccountsButton.setOnAction(e -> System.out.println("View User Accounts selected."));
        generateRevenueReportButton.setOnAction(e -> System.out.println("Generate Revenue Report selected."));
        viewAllBookingRecordsButton.setOnAction(e -> System.out.println("View All Booking Records selected."));
        viewHousekeepingRecordsButton.setOnAction(e -> System.out.println("View All Housekeeping Records selected."));
        viewMostBookedRoomTypesButton.setOnAction(e -> System.out.println("View Most Booked Room Types selected."));
        viewEmployeesWithRolesButton.setOnAction(e -> System.out.println("View All Employees with Their Roles selected."));

        backButton.setOnAction(e -> {
            adminStage.close();
            mainStage.show();
        });

        VBox layout = new VBox(10, adminLabel, addRoomButton, deleteRoomButton, manageRoomStatusButton, addUserAccountButton,
                viewUserAccountsButton, generateRevenueReportButton, viewAllBookingRecordsButton,
                viewHousekeepingRecordsButton, viewMostBookedRoomTypesButton, viewEmployeesWithRolesButton, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 500);
        adminStage.setTitle("Administrator Menu");
        adminStage.setScene(scene);
        mainStage.hide();
        adminStage.show();
    }
}
