package com.example.tetrsi.Controller;

import com.example.tetrsi.Application;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

public class ConfigController {
    private Set<String> takenKeybinds = new HashSet<>();
    Config config = Config.getInstance();
    private boolean hasChanged;

    private final static String[] panels = {"Handling","Controls","Account"};
    private final static int panelNum = 3;
    private final static double panelDistance = 1000;
    private int currentScreen = 0;
    @FXML private Circle unsaved_circle;
    @FXML private Button exit, save, navleft, navright;
    @FXML private Group handling, controls, account;
    @FXML private Slider das_slider, arr_slider, sdf_slider, dly_slider;
    @FXML private TextField das_value, arr_value, sdf_value, dly_value;
    @FXML private Button c_left, c_right, c_cw, c_ccw, c_180, c_sd, c_hd, c_hold, c_reset, c_exit;
    private Stage stage;
    private Group[] configPanels;
    private Button[] keybinds;

    @FXML
    public void initialize(){
        setNavButtons();
        Platform.runLater(() -> {
            configPanels = new Group[]{handling, controls, account};
            keybinds = new Button[]{c_left,c_right,c_cw,c_ccw,c_180,c_sd,c_hd,c_hold,c_reset,c_exit};
            instantiateSliderValues();
            instantiateControls();
            stage = (Stage)exit.getScene().getWindow();
        });
    }

    @FXML
    private void exit() throws IOException {
        Application.changeScreen(stage, "start-menu.fxml");
    }

    @FXML
    private void saveConfig() throws IOException {
        config.save();
        saved();
    }

    private void markChanged(){
        hasChanged = true;
        unsaved_circle.setVisible(true);
        save.setDisable(false);
    }

    private void saved(){
        hasChanged = false;
        unsaved_circle.setVisible(false);
        save.setDisable(true);
    }

    private boolean isChanged(){
        return hasChanged;
    }

    private void setNavButtons(){
        boolean l_vis = (currentScreen != 0), r_vis = (currentScreen != panelNum - 1);
        navleft.setVisible(l_vis);
        navright.setVisible(r_vis);
        if (l_vis){
            navleft.setText(panels[currentScreen-1]);
        }
        if (r_vis){
            navright.setText(panels[currentScreen+1]);
        }
    }

    private void shiftPanels(int index){
        //so basically, we need all of the panels to end up at the correct location. simply
        //so the panel at index msut be at the center, and all otehrs should be minused
        //so we get the index times -1000 and add that to get the final translate x

        ParallelTransition plt = new ParallelTransition();
        for (int i=0;i<panelNum;i++){
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5));
            tt.setNode(configPanels[i]);
            tt.setToX((i-index)*panelDistance);
            plt.getChildren().add(tt);
        }
        setNavButtons();
        plt.play();
    }

    @FXML
    private void navigateLeft(){
        if (currentScreen == 0){
            return;
        }
        shiftPanels(--currentScreen);
    }

    @FXML
    private void navigateRight(){
        if (currentScreen == panelNum-1){
            return;
        }
        shiftPanels(++currentScreen);
    }



    private static void setSliderText(int i, TextField text){
        text.setText(String.valueOf(i));
    }

    private static void setSliderText(double d, TextField text){
        String deciFormat = "%.1f";
        text.setText(String.format(deciFormat,d));
    }

    private void setSDFText(int d){
        if (d == 0){
            sdf_value.setText("inf");
        }
        else {
            setSliderText(d,sdf_value);
        }
    }

    private double das, arr;
    private int sdf, dly;

    @FXML
    private void handleDasChange(){
        das = das_slider.getValue();
        config.setValue(Config.DAS, das);
        setSliderText(das, das_value);
        markChanged();
    }

    @FXML
    private void handleDasText(){
        System.out.println("Changed!");
        das = Double.parseDouble(das_value.getText());
        config.setValue(Config.DAS, das);
        das_slider.setValue(das);
        markChanged();
    }

    @FXML
    private void handleArrChange(){
        arr = arr_slider.getValue();
        config.setValue(Config.ARR, arr);
        setSliderText(arr,arr_value);
        markChanged();
    }

    @FXML
    private void handleArrText(){
        arr = Double.parseDouble(arr_value.getText());
        config.setValue(Config.ARR, arr);
        arr_slider.setValue(arr);
        markChanged();
    }

    @FXML
    private void handleSdfChange(){
        sdf = (int)sdf_slider.getValue();
        config.setValue(Config.SDF, sdf);
        setSDFText(sdf);
        markChanged();
    }


    @FXML
    private void handleSdfText(){
        sdf = Integer.parseInt(sdf_value.getText());
        config.setValue(Config.SDF, sdf);
        sdf_slider.setValue(sdf);
        setSDFText(sdf);
        markChanged();
    }

    @FXML
    private void handleDlyChange(){
        dly = (int)dly_slider.getValue();
        config.setValue(Config.DELAY, dly);
        setSliderText(dly, dly_value);
        markChanged();
    }

    @FXML
    private void handleDlyText(){
        dly = Integer.parseInt(dly_value.getText());
        config.setValue(Config.DELAY, dly);
        dly_slider.setValue(dly);
        markChanged();
    }

    private void instantiateSliderValues(){
        das = (double)config.getValue(Config.DAS);
        arr = (double)config.getValue(Config.ARR);
        sdf = (int)config.getValue(Config.SDF);
        dly = (int)config.getValue(Config.DELAY);

        das_slider.setValue(das);
        arr_slider.setValue(arr);
        sdf_slider.setValue(sdf);
        dly_slider.setValue(dly);
        setSliderText(das, das_value);
        setSliderText(arr,arr_value);
        setSDFText(sdf);
        setSliderText(dly,dly_value);
    }

    private void instantiateControls(){
        String left = (String) config.getValue(Config.LEFT);
        c_left.setText(left);
        String right = (String) config.getValue(Config.RIGHT);
        c_right.setText(right);
        String cw = (String) config.getValue(Config.CW);
        c_cw.setText(cw);
        String ccw = (String) config.getValue(Config.CCW);
        c_ccw.setText(ccw);
        String oneeighty = (String) config.getValue(Config.ONEEIGHTY);
        c_180.setText(oneeighty);
        String sd = (String) config.getValue(Config.SOFTDROP);
        c_sd.setText(sd);
        String hd = (String) config.getValue(Config.HARDDROP);
        c_hd.setText(hd);
        String hold = (String) config.getValue(Config.HOLD);
        c_hold.setText(hold);
        String reset = (String) config.getValue(Config.RESET);
        c_reset.setText(reset);
        String exit = (String) config.getValue(Config.EXIT);
        c_exit.setText(exit);
        takenKeybinds.addAll(List.of(left,right,cw,ccw,oneeighty,sd,hd,hold,reset,exit));
        for (Button b : keybinds){
            b.setFocusTraversable(true);
        }
    }

    private boolean keybindTaken(String keybind){
        return takenKeybinds.contains(keybind);
    }

    private void removeKeybind(String keybind){
        takenKeybinds.remove(keybind);
    }

    private void addKeybind(String keybind){
        takenKeybinds.add(keybind);
    }

    private void setListening(Button button, String configure){
        button.setText("Hit a key");
        button.requestFocus();
        button.setOnKeyPressed((var e)->{
            System.out.println(e.getCode().getName());
            if (!keybindTaken(e.getCode().getName())) {
                config.setValue(configure, e.getCode().getName());
                button.setText(e.getCode().getName());
                markChanged();
            }
            else {
                button.setText((String)config.getValue(configure));
            }
        });
    }

    @FXML
    private void handleLeft(){
        setListening(c_left, Config.LEFT);
    }

    @FXML
    private void handleRight(){
        setListening(c_right, Config.RIGHT);
    }

    @FXML
    private void handleCW(){
        setListening(c_cw, Config.CW);
    }
    @FXML
    private void handleCCW(){
        setListening(c_ccw, Config.CCW);
    }
    @FXML
    private void handle180(){
        setListening(c_180, Config.ONEEIGHTY);
    }
    @FXML
    private void handleSD(){
        setListening(c_sd, Config.SOFTDROP);
    }
    @FXML
    private void handleHD(){
        setListening(c_hd, Config.HARDDROP);
    }
    @FXML
    private void handleHold(){
        setListening(c_hold, Config.HOLD);
    }
    @FXML
    private void handleReset(){
        setListening(c_reset, Config.RESET);
    }
    @FXML
    private void handleExit(){
        setListening(c_exit, Config.EXIT);
    }
}

