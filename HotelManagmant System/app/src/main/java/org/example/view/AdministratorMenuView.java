package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdministratorMenuView {
    private final Stage stage;
    private final Scene scene;

    public AdministratorMenuView(Stage stage) {
        this.stage = stage;
        this.scene = stage.getScene();
    }

    public void show() {
        Label titleLabel = new Label("Administrator Menu");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

        Button addRoomButton = new Button("Add Room");
        addRoomButton.setOnAction(e -> new AddRoomView(stage).show());

        Button deleteRoomButton = new Button("Delete Room");
        deleteRoomButton.setOnAction(e -> new DeleteRoomView(stage).show());

        Button manageRoomStatusButton = new Button("Manage Room Status");
        manageRoomStatusButton.setOnAction(e -> new ManageRoomStatusView(stage).show());

        Button addUserButton = new Button("Add User Account");
        addUserButton.setOnAction(e -> new AddUserView(stage).show());

        Button viewUsersButton = new Button("View User Accounts");
        viewUsersButton.setOnAction(e -> new ViewUserAccountsView(stage).show());

        Button revenueReportButton = new Button("Generate Revenue Report");
        revenueReportButton.setOnAction(e -> new RevenueReportView(stage).show());

        Button viewBookingsButton = new Button("View All Booking Records");
        viewBookingsButton.setOnAction(e -> new BookingRecordsView(stage).show());

        Button housekeepingRecordsButton = new Button("View All Housekeeping Records");
        housekeepingRecordsButton.setOnAction(e -> new HousekeepingRecordsView(stage).show());

        Button mostBookedRoomTypesButton = new Button("View Most Booked Room Types");
        mostBookedRoomTypesButton.setOnAction(e -> new MostBookedRoomTypesView(stage).show());

        Button viewEmployeesButton = new Button("View All Employees with Their Roles");
        viewEmployeesButton.setOnAction(e -> new EmployeesWithRolesView(stage).show());

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, titleLabel, addRoomButton, deleteRoomButton, manageRoomStatusButton, 
                               addUserButton, viewUsersButton, revenueReportButton, viewBookingsButton, 
                               housekeepingRecordsButton, mostBookedRoomTypesButton, viewEmployeesButton, backButton);
                               
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        scene.setRoot(layout); // Set the root layout
        stage.setTitle("Administrator Menu");
        stage.show();
    }
}
