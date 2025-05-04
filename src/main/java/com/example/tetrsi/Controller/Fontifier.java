package com.example.tetrsi.Controller;

import com.example.tetrsi.Application;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Fontifier {
    public static Font getFont(double size){
        return Font.loadFont(Application.class.getResourceAsStream("fonts/fixedsys.ttf"),size);
    }

    public static void fontify(Text t){
        double size = t.getFont().getSize();
        t.setFont(getFont(size));
    }
}
