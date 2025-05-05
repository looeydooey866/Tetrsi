package com.example.tetrsi.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InfoBox extends StackPane{
    private Text text;

    public InfoBox(double height, double width, int size){
        this.setMinWidth(width);
        this.setMinHeight(height);
        this.setPadding(new Insets(0,0,0,size/2.0));
        this.setAlignment(Pos.CENTER_LEFT);
        text = new Text();
        text.setFont(new Font("System",size));
        this.getChildren().add(text);
    }

    private Text getText(){
        return (Text)this.getChildren().get(0);
    }

    public void setText(String text){
        getText().setText(text);
    }

    public static InfoBox of(double height, double width, int size){
        return new InfoBox(height,width,size);
    }
}
