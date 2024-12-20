package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.enums.UserType;
import org.example.viewmodel.AddUserAccountViewModel;

public class AddUserAccountView {
    private final AddUserAccountViewModel viewModel;

    public AddUserAccountView() {
        this.viewModel = new AddUserAccountViewModel();
    }

    public void show(Stage stage) {
        stage.setTitle("Add User Account");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter name");

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        emailField.setPromptText("Enter email");

        Label phoneLabel = new Label("Phone Number:");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Enter phone number");

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        Label userTypeLabel = new Label("User Type:");
        ComboBox<UserType> userTypeComboBox = new ComboBox<>();
        ObservableList<UserType> userTypes = FXCollections.observableArrayList(viewModel.getUserTypes());
        userTypeComboBox.setItems(userTypes);
        userTypeComboBox.setPromptText("Choose User Type");

        Button addButton = new Button("Add User");
        addButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phoneNumber = phoneField.getText().trim();
            String password = passwordField.getText().trim();
            UserType userType = userTypeComboBox.getValue();

            if (name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || password.isEmpty() || userType == null) {
                showAlert(Alert.AlertType.WARNING, "Incomplete Information", "Please fill in all fields.");
                return;
            }

            boolean success = viewModel.addUser(name, email, phoneNumber, password, userType);
            if (success) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "User account created successfully.");
                stage.close();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to create user account. Email might already exist.");
            }
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> stage.close());

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(emailLabel, 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(phoneLabel, 0, 2);
        grid.add(phoneField, 1, 2);
        grid.add(passwordLabel, 0, 3);
        grid.add(passwordField, 1, 3);
        grid.add(userTypeLabel, 0, 4);
        grid.add(userTypeComboBox, 1, 4);
        grid.add(addButton, 0, 5);
        grid.add(cancelButton, 1, 5);

        Scene scene = new Scene(grid, 400, 350);
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
