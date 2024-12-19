package org.example.view;

import java.util.ArrayList;
import java.util.List;

import org.example.model.Room;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeleteRoomView {
    private final Stage stage;
    private final List<Room> rooms;

    public DeleteRoomView(Stage stage) {
        this.stage = stage;
        this.rooms = new ArrayList<>(); // Replace with shared data source
    }

    public void show() {
        Label titleLabel = new Label("Delete Room");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 10px;");

        ListView<String> roomListView = new ListView<>();
        rooms.forEach(room -> roomListView.getItems().add(room.toString()));

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            String selectedRoom = roomListView.getSelectionModel().getSelectedItem();
            if (selectedRoom != null) {
                rooms.removeIf(room -> room.toString().equals(selectedRoom));
                roomListView.getItems().remove(selectedRoom);
                System.out.println("Room deleted: " + selectedRoom);
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> new AdministratorMenuView(stage).show());

        VBox layout = new VBox(10, titleLabel, roomListView, deleteButton, backButton);
        layout.setStyle("-fx-padding: 20px; -fx-alignment: center;");

        Scene scene = stage.getScene();
        scene.setRoot(layout);
        stage.setTitle("Delete Room");
    }
}
