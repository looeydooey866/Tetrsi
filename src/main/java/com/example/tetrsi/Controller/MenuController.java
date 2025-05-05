package com.example.tetrsi.Controller;

import com.example.tetrsi.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    private Stage stage;
    @FXML private Text title;
    @FXML private Button play, config, about;

    @FXML
    public void initialize(){
        DropShadow ds = new DropShadow();
        ds.setColor(Color.AQUA);
        title.setEffect(ds);
        Platform.runLater(() -> {
            stage = (Stage) config.getScene().getWindow();
        });
    }

    @FXML
    private void openMenu() throws IOException{
        Application.changeScreen(stage, "play-menu.fxml");
    }

    @FXML
    private void openConfig() throws IOException {
        Application.changeScreen(stage, "config-menu.fxml");
    }

    @FXML
    private void openAbout() throws IOException{
        Application.changeScreen(stage, "about-menu.fxml");
    }

}
