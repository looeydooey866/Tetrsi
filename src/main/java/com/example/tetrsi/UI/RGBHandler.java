package com.example.tetrsi.UI;


import com.example.tetrsi.Model.Definitions.RGB;
import javafx.scene.paint.Color;

public class RGBHandler {
    public static Color convertRGB(RGB rgb){
        return new Color(rgb.getRed(),rgb.getGreen(),rgb.getBlue(), 1);
    }
}
