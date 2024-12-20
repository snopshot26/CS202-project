package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.User;
import org.example.viewmodel.ViewUserAccountsViewModel;

public class ViewUserAccountsView {
    private final ViewUserAccountsViewModel viewModel;

    public ViewUserAccountsView() {
        this.viewModel = new ViewUserAccountsViewModel();
    }

    public void show(Stage stage, long adminId) {
        stage.setTitle("View User Accounts");

        TableView<User> userTable = new TableView<>();
        ObservableList<User> users = FXCollections.observableArrayList(viewModel.getAllUsers());
        userTable.setItems(users);

        TableColumn<User, Long> idCol = new TableColumn<>("User ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getId()).asObject());

        TableColumn<User, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));

        TableColumn<User, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));

        TableColumn<User, String> phoneCol = new TableColumn<>("Phone Number");
        phoneCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPhoneNumber()));

        TableColumn<User, String> userTypeCol = new TableColumn<>("User Type");
        userTypeCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getUserType().toString()));

        userTable.getColumns().addAll(idCol, nameCol, emailCol, phoneCol, userTypeCol);

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> {
            users.setAll(viewModel.getAllUsers());
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> new AdministratorMenuView(adminId).show(stage));

        VBox layout = new VBox(10, userTable, refreshButton, closeButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
