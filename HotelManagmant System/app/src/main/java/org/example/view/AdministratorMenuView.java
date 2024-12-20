package org.example.view;

import org.example.viewmodel.AdministratorMenuViewModel;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdministratorMenuView {

    private final AdministratorMenuViewModel viewModel;

    public AdministratorMenuView(long adminId) {
        this.viewModel = new AdministratorMenuViewModel(adminId);
    }

    public void show(Stage stage) {
        stage.setTitle("Administrator Menu");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Button addRoomButton = new Button("Add Room");
        addRoomButton.setOnAction(e -> {
            AddRoomView addRoomView = new AddRoomView();
            addRoomView.show(stage,viewModel.getAdminId());
        });

        Button deleteRoomButton = new Button("Delete Room");
        deleteRoomButton.setOnAction(e -> {
            DeleteRoomView deleteRoomView = new DeleteRoomView(viewModel.getAdminId());
            deleteRoomView.show(stage);
        });

        Button manageRoomStatusButton = new Button("Manage Room Status");
        manageRoomStatusButton.setOnAction(e -> {
            ManageRoomStatusView manageRoomStatusView = new ManageRoomStatusView();
            manageRoomStatusView.show(stage, viewModel.getAdminId());
        });

        // Добавьте другие кнопки меню администратора здесь

        Button viewUserAccountsButton = new Button("View User Accounts");
        viewUserAccountsButton.setOnAction(e -> {
            ViewUserAccountsView viewUserAccountsView = new ViewUserAccountsView();
            viewUserAccountsView.show(stage, viewModel.getAdminId());
        });

        // Кнопка выхода
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> {
            // Логика выхода из системы
            stage.close();
        });

        layout.getChildren().addAll(
            addRoomButton,
            deleteRoomButton,
            manageRoomStatusButton,
            viewUserAccountsButton,
            logoutButton
        );

        Scene scene = new Scene(layout, 300, 400);
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
