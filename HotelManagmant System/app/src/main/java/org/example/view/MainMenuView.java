package org.example.view;

import org.example.viewmodel.MainMenuViewModel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenuView extends Application {
    private final MainMenuViewModel viewModel = new MainMenuViewModel();

    @Override
    public void start(Stage primaryStage) {
        // Создание элементов интерфейса
        Label titleLabel = new Label("Hotel Management System");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button guestButton = new Button("Guest Menu");
        Button adminButton = new Button("Admin Menu");
        Button receptionistButton = new Button("Receptionist Menu");
        Button housekeepingButton = new Button("Housekeeping Menu");

        guestButton.setMinWidth(200);
        adminButton.setMinWidth(200);
        receptionistButton.setMinWidth(200);
        housekeepingButton.setMinWidth(200);

        // Обработчики нажатий кнопок
        guestButton.setOnAction(e -> {
            viewModel.setSelectedRole("Guest");
            //openGuestMenuView(primaryStage);
        });

        adminButton.setOnAction(e -> {
            viewModel.setSelectedRole("Admin");
            //openAdministratorMenuView(primaryStage);
        });

        receptionistButton.setOnAction(e -> {
            viewModel.setSelectedRole("Receptionist");
            //openReceptionistView(primaryStage);
        });

        housekeepingButton.setOnAction(e -> {
            viewModel.setSelectedRole("Housekeeping");
            //openHousekeepingView(primaryStage);
        });

        // Компоновка элементов
        VBox layout = new VBox(15);
        layout.getChildren().addAll(titleLabel, guestButton, adminButton, receptionistButton, housekeepingButton);
        layout.setStyle("-fx-padding: 50px; -fx-alignment: center;");

        // Настройка сцены
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Методы для открытия конкретных окон
    // private void openGuestMenuView(Stage stage) {
    //     GuestMenuView GuestMenuView = new GuestMenuView();
    //     GuestMenuView.start(new Stage());
    //     stage.close();
    // }

    // private void openAdministratorMenuView(Stage stage) {
    //     AdministratorMenuView AdministratorMenuView = new AdministratorMenuView();
    //     AdministratorMenuView.start(new Stage());
    //     stage.close();
    // }

    // private void openReceptionistView(Stage stage) {
    //     // Вызов View для ресепшиониста
    //     System.out.println("Receptionist View (В процессе разработки)");
    // }

    // private void openHousekeepingView(Stage stage) {
    //     // Вызов View для хаускипинга
    //     System.out.println("Housekeeping View (В процессе разработки)");
    // }

    public static void main(String[] args) {
        launch(args);
    }
}

