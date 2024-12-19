package org.example.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create UI elements for Login
        Label welcomeLabel = new Label("Welcome to the Hotel Management System");
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Fields cannot be empty.");
            } else if (password.length() < 6 || !password.matches(".*[A-Z].*")) {
                errorLabel.setText("Password must be at least 6 characters and contain one uppercase letter.");
            } else {
                errorLabel.setText("");
                System.out.println("Login successful for: " + username);
                new ReceptionistMenuView().start(primaryStage);
            }
        });

        registerButton.setOnAction(e -> showRegisterWindow(primaryStage));

        VBox loginLayout = new VBox(10, welcomeLabel, usernameLabel, usernameField, passwordLabel, passwordField, errorLabel, loginButton, registerButton);
        loginLayout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene loginScene = new Scene(loginLayout, 400, 300);
        primaryStage.setTitle("Login");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void showRegisterWindow(Stage mainStage) {
        Stage registerStage = new Stage();

        // Create UI elements for Register
        Label titleLabel = new Label("Register New Account");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label confirmPasswordLabel = new Label("Confirm Password:");
        PasswordField confirmPasswordField = new PasswordField();

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        Button submitButton = new Button("Submit");
        Button backButton = new Button("Back to Login");

        submitButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
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
                registerStage.close();
                mainStage.show();
            }
        });

        backButton.setOnAction(e -> {
            registerStage.close();
            mainStage.show();
        });

        VBox registerLayout = new VBox(10, titleLabel, emailLabel, emailField, passwordLabel, passwordField, confirmPasswordLabel, confirmPasswordField, errorLabel, submitButton, backButton);
        registerLayout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene registerScene = new Scene(registerLayout, 400, 400);
        registerStage.setTitle("Register");
        registerStage.setScene(registerScene);
        mainStage.hide();
        registerStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
