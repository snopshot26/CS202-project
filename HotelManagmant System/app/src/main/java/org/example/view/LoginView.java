package org.example.view;

import org.example.enums.UserType;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.example.model.User;
import org.example.viewmodel.LoginViewModel;

public class LoginView {

    private final LoginViewModel viewModel;

    public LoginView() {
        this.viewModel = new LoginViewModel();
    }

    public void showLoginScene(Stage primaryStage) {
        // UI элементы
        Label welcomeLabel = new Label("Welcome to the Hotel Management System");
        welcomeLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        // Логика кнопки "Login"
        loginButton.setOnAction(e -> {
            String email = emailField.getText();
            String password = passwordField.getText();

            if (email.isEmpty() || password.isEmpty()) {
                errorLabel.setText("Fields cannot be empty.");
            } else if (!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                errorLabel.setText("Invalid email format.");
            } else {
                User authenticatedUser= viewModel.authenticateUser(email, password);
                if (authenticatedUser != null)  {
                    errorLabel.setTextFill(Color.GREEN);
                    errorLabel.setText("Login successful!");
                    System.out.println("Login successful for: " + email);
                    switch (authenticatedUser.getUserType()) {
                        case UserType.ADMINISTRATOR:
                            new AdministratorMenuView(authenticatedUser.getId()).show(primaryStage);
                            break;
                        case UserType.RECEPTIONIST:
                            new ReceptionistMenuView(authenticatedUser.getId()).show(primaryStage,authenticatedUser.getId());
                            break;
                        case UserType.GUEST:
                            new GuestMenuView().show(primaryStage,authenticatedUser.getId());
                            break;
                        case UserType.HOUSEKEEPING:
                            new HousekeepingMenuView(authenticatedUser.getId()).show(primaryStage,authenticatedUser.getId());
                            break;
                        default:
                            throw new AssertionError();
                    }
                    
                } else {
                    errorLabel.setText("Invalid email or password.");
                }
            }
        });

        // Логика кнопки "Register"
        registerButton.setOnAction(e -> new RegistrateView().showRegisterScene(primaryStage));

        VBox layout = new VBox(10, welcomeLabel, emailLabel, emailField, passwordLabel,
                passwordField, errorLabel, loginButton, registerButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
    }
}
