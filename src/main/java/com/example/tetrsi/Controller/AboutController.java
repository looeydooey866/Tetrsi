package com.example.tetrsi.Controller;

import com.example.tetrsi.Application;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class AboutController {
    @FXML private Text title, text;
    @FXML private Button back;
    private Stage stage;

    @FXML
    public void initialize(){
        Platform.runLater(()->{
            ParallelTransition t = new ParallelTransition();
            FadeTransition textFade = new FadeTransition(Duration.seconds(3));
            textFade.setNode(title);
            textFade.setToValue(0);
            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(120));
            translateTransition.setToY(-3600);
            translateTransition.setNode(text);
            t.getChildren().addAll(textFade, translateTransition);
            t.setOnFinished(actionEvent -> {
                back.setDisable(false);
            });
            stage = (Stage)title.getScene().getWindow();
            t.play();
        });
    }

    @FXML
    private void exit() throws IOException {
        Application.changeScreen(stage, "fxml/start-menu.fxml");
    }
}
