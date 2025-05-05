package com.example.tetrsi.UI;

import com.example.tetrsi.Model.Definitions.Block;
import com.example.tetrsi.Model.Definitions.Queue;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.List;


public class QueueBar extends VBox {
    private int preview;
    private PieceBox[] blocks;
    private double pieceBoxHeight;
    private Queue queue;
    private double x,y;
    private int childIndex;
    private InfoBox info1;

    public QueueBar(double desiredHeight, Queue queue, int preview, double x, double y){
        this.x = x;
        this.y = y;
        VBox.setVgrow(this, Priority.ALWAYS);
        this.preview = Math.min(Constants.maxPreview, preview);
        this.queue = queue;
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.setMaxHeight(desiredHeight);
        this.pieceBoxHeight = desiredHeight / Constants.maxBoxes;
        this.setMaxWidth(PieceBox.getWidth(pieceBoxHeight));
        instantiateBlocks();
        instantiateInfo();
    }

    private PieceBox getPieceBox(int position){
        return PieceBox.of(queue.get(position),pieceBoxHeight);
    }

    private void instantiateBlocks(){
        blocks = new PieceBox[preview];
        for (int i=0;i<preview;i++){
            blocks[i] = getPieceBox(i);
        }
        this.getChildren().addAll(blocks);
    }

    private void instantiateInfo(){
        Region empty = new Region();
        this.getChildren().add(empty);
        empty.setPrefHeight(999);
        info1 = InfoBox.of(this.pieceBoxHeight,PieceBox.getWidth(this.pieceBoxHeight),18);
        this.getChildren().add(info1);
    }

    public void setInfo1(String s){
        info1.setText(s);
    }

    public void advanceQueue(){
        for (int i=0;i<this.getChildren().size();i++){
            if (this.getChildren().get(i)==blocks[0]){
                childIndex = i;
                break;
            }
        }
        for (int i=0;i<preview-1;i++){
            blocks[i] = blocks[i+1];
        }
        blocks[preview-1] = getPieceBox(preview-1);
        this.getChildren().remove(childIndex);
        this.getChildren().add(childIndex+preview-1,blocks[preview-1]);
    }
}
