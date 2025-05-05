package com.example.tetrsi.Controller;

import com.example.tetrsi.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GameMenuController {
    @FXML private Button back, normal, sprint;
    private Stage stage;

    @FXML
    public void initialize(){
        Platform.runLater(() -> {
            stage = (Stage)back.getScene().getWindow();
        });
    }

    @FXML
    private void back() throws IOException {
        Application.changeScreen(stage, "start-menu.fxml");
    }

    @FXML
    private void normal() throws IOException{
        Application.changeScreen(stage, "normal-game.fxml");
    }
}
