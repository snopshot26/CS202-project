package org.example.view;

import org.example.config.DatabaseSeeder;
import org.example.config.HibernateUtil;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainWindow extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hotel Management System");

        // Заполняем базу данных перед запуском интерфейса
        DatabaseSeeder.run();

        // Устанавливаем LoginWindow как начальное окно
        LoginView loginWindow = new LoginView();
        loginWindow.showLoginScene(primaryStage);

        primaryStage.show();
    }

    @Override
    public void stop() {
        if (HibernateUtil.getSessionFactory().isOpen()) {
            HibernateUtil.shutdown();
            System.out.println("Hibernate SessionFactory закрыт.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
