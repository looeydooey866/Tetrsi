package com.example.tetrsi;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/start-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.getIcons().add(new Image(Objects.requireNonNull(Application.class.getResourceAsStream("images/communisttetris.png"))));
        stage.setTitle("Tetrsi");
        stage.setScene(scene);
        if (Taskbar.isTaskbarSupported()){
            var taskbar = Taskbar.getTaskbar();
            if (taskbar.isSupported(Taskbar.Feature.ICON_IMAGE)){
                final Toolkit defaultTooklit = Toolkit.getDefaultToolkit();
                var dockIcon = defaultTooklit.getImage(Application.class.getResource("images/communisttetris.png"));
                taskbar.setIconImage(dockIcon);
            }
        }
        stage.show();
    }

    public static void changeScreen(Stage stage, String filename) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("fxml/".concat(filename)));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.getIcons().add(getImage("communisttetris.png"));
        stage.setScene(scene);
        stage.setTitle("Tetrsi");
        stage.show();
    }

    public static Image getImage(String filename){
        System.err.println("images/".concat(filename));
        return new Image(Objects.requireNonNull(Application.class.getResourceAsStream("images/".concat(filename))));
    }

    public static void main(String[] args) {
        launch();
    }
}