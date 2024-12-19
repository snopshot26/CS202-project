package org.example.view;

import org.example.enums.UserType;
import org.example.viewmodel.LoginViewModel;
import org.example.viewmodel.RegistrateViewModel;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RegistrateView {

    private final RegistrateViewModel viewModel;

    public RegistrateView() {
        this.viewModel = new RegistrateViewModel();
    }

    public void showRegisterScene(Stage primaryStage) {
        // UI элементы
        Label titleLabel = new Label("Register New Account");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label phoneNumberLabel = new Label("Phone number:");
        TextField phoneNumberField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label confirmPasswordLabel = new Label("Confirm Password:");
        PasswordField confirmPasswordField = new PasswordField();

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        Button submitButton = new Button("Submit");
        Button backButton = new Button("Back to Login");

        // Логика кнопки "Submit"
        submitButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            String name = nameField.getText();
            String phoneNumber = phoneNumberField.getText();

            if ( phoneNumber.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                errorLabel.setText("Fields cannot be empty.");
            } else if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                errorLabel.setText("Invalid email format.");
            } else if (password.length() < 6 || !password.matches(".*[A-Z].*")) {
                errorLabel.setText("Password must be at least 6 characters and contain one uppercase letter.");
            } else if (!password.equals(confirmPassword)) {
                errorLabel.setText("Passwords do not match.");
            } else {
                errorLabel.setText("");
                System.out.println("Registration successful for: " + email);
                viewModel.registrateUser(name, email, password, UserType.GUEST, phoneNumber);
                new LoginView().showLoginScene(primaryStage);
            }
        });

        // Логика кнопки "Back"
        backButton.setOnAction(e -> new LoginView().showLoginScene(primaryStage));

        VBox layout = new VBox(10, titleLabel,nameLabel,nameField, emailLabel, emailField,phoneNumberLabel,phoneNumberField, passwordLabel, passwordField,
                               confirmPasswordLabel, confirmPasswordField, errorLabel, submitButton, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
    }
}
