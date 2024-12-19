package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.User;

import java.util.List;

public class ViewEmployeesWithRolesView {
    private final Stage stage;
    private final List<User> users;

    public ViewEmployeesWithRolesView(Stage stage, List<User> users) {
        this.stage = stage;
        this.users = users;
    }

    public void show() {
        Label titleLabel = new Label("View Employees with Their Roles");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

        ListView<String> employeeListView = new ListView<>();
        users.forEach(user -> employeeListView.getItems().add(user.getUsername() + " - " + user.getRole()));

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new AdministratorMenuView(stage).show());

        VBox layout = new VBox(10, titleLabel, employeeListView, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = stage.getScene();
        scene.setRoot(layout);
        stage.setTitle("View Employees with Their Roles");
    }
}
