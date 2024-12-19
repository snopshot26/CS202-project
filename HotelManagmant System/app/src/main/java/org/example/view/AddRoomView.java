package org.example.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddRoomView {
    private final Stage stage;
    private final Scene scene;

    public AddRoomView(Stage stage) {
        this.stage = stage;
        this.scene = stage.getScene();
    }

    public void show() {
        Label titleLabel = new Label("Add Room");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-padding: 10px;");

        TextField roomNumberField = new TextField();
        roomNumberField.setPromptText("Enter Room Number");

        TextField roomTypeField = new TextField();
        roomTypeField.setPromptText("Enter Room Type");

        TextField priceField = new TextField();
        priceField.setPromptText("Enter Price");

        Button addButton = new Button("Add Room");
        addButton.setOnAction(e -> {
            String roomNumber = roomNumberField.getText();
            String roomType = roomTypeField.getText();
            double price;
            try {
                price = Double.parseDouble(priceField.getText());
                // Add room logic here
                System.out.println("Room Added: " + roomNumber + " (" + roomType + ") - $" + price);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid price input.");
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new AdministratorMenuView(stage).show());

        VBox layout = new VBox(10, titleLabel, roomNumberField, roomTypeField, priceField, addButton, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        scene.setRoot(layout); // Set the root layout
        stage.setTitle("Add Room");
        stage.show();
    }
}
