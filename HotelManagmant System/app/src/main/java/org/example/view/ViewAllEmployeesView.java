package org.example.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.model.Employee;
import org.example.viewmodel.ViewAllEmployeesViewModel;

public class ViewAllEmployeesView {
    private final ViewAllEmployeesViewModel viewModel;

    public ViewAllEmployeesView() {
        this.viewModel = new ViewAllEmployeesViewModel();
    }

    public void show(Stage stage) {
        stage.setTitle("View All Employees");

        TableView<Employee> employeeTable = new TableView<>();
        ObservableList<Employee> employees = FXCollections.observableArrayList(viewModel.getAllEmployees());
        employeeTable.setItems(employees);

        TableColumn<Employee, Long> employeeIdCol = new TableColumn<>("Employee ID");
        employeeIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getId()).asObject());

        TableColumn<Employee, Long> hotelIdCol = new TableColumn<>("Hotel ID");
        hotelIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getHotel().getId()).asObject());

        TableColumn<Employee, Long> userIdCol = new TableColumn<>("User ID");
        userIdCol.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getUser().getId()).asObject());

        TableColumn<Employee, String> roleCol = new TableColumn<>("Role");
        roleCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getRole().toString()));

        employeeTable.getColumns().addAll(employeeIdCol, hotelIdCol, userIdCol, roleCol);

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> {
            employees.setAll(viewModel.getAllEmployees());
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(10, employeeTable, refreshButton, closeButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = new Scene(layout, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
