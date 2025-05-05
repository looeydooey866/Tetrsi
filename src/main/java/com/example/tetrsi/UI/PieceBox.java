package com.example.tetrsi.UI;

import com.example.tetrsi.Model.Definitions.Block;
import com.example.tetrsi.Model.Definitions.Coord;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class PieceBox extends StackPane {
    private double height;
    private double width;
    private double minoSize;
    private Group minos;
    private Block block;
    private String color;
    //classic ratio is 3:5 btw
    private static final double ratio = 5.0 / 3;

    public PieceBox(Block block, double desiredHeight){
        this.block = block;
        this.setAlignment(Pos.CENTER);
        this.height = desiredHeight;
        this.width = getWidth(height);
        this.setMinHeight(height);
        this.setMinWidth(width);
        if (block != null) {
            this.minoSize = this.height / 3 * block.getScaling();
            this.color = block.getColor();

            minos = new Group();
            double size = minoSize * block.getScaling();
            for (Coord c : block.getBlockOffset().getOffsets(0).getList()) {
                minos.getChildren().add(Mino.of(size, c.getX() * size, -c.getY() * size, color));
            }
            this.getChildren().add(minos);
        }

    }

    public Group getMinos(){
        return minos;
    }

    public static PieceBox of(Block block, double desiredHeight){
        return new PieceBox(block,desiredHeight);
    }

    public static double getWidth(double desiredHeight){
        return desiredHeight * ratio;
    }

    public void setHold(){
        for (Node m : minos.getChildren()){
            ((Mino)m).setColor("Garbage");
        }
    }

    public void unHold(){
        for (Node m : minos.getChildren()){
            ((Mino)m).setColor(color);
        }
    }
}
