package com.example.tetrsi.UI;

import com.example.tetrsi.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;

public class Mino extends ImageView {
    private static Map<String, Image> cache = new HashMap<>();

    public Mino(double size, double x, double y, String color){
        setColor(color);
        this.setFitHeight(size);
        this.setFitWidth(size);
        this.setX(x);
        this.setY(y);
    }

    public static Mino of(double size, double x, double y, String color){
        return new Mino(size,x,y,color);
    }

    public void setColor(String color){
        if (cache.containsKey(color)){
            this.setImage(cache.get(color));
        }
        else {
            Image i = Application.getImage(color.concat(".png"));
            cache.put(color,i);
            this.setImage(i);
        }
    }
}
