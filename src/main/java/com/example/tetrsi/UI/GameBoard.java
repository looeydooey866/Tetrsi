package com.example.tetrsi.UI;

import com.example.tetrsi.Model.Definitions.Board;
import com.example.tetrsi.Model.Definitions.Coord;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class GameBoard extends Pane {
    private int dimx, dimy;
    private double height, width;
    private double minoSize;
    private Board board;
    private double x,y;

    public GameBoard(double desiredHeight, Board board, double x, double y){
        this.board = board;
        this.dimx = board.getVWidth();
        this.dimy = board.getVHeight();
        this.height = desiredHeight;
        this.width = desiredHeight * dimx / dimy;
        this.minoSize = desiredHeight / dimy;
        this.x = x;
        this.y = y;

        this.setWidth(width);
        this.setHeight(desiredHeight);
        this.setLayoutX(x);
        this.setLayoutY(y);
        for (int i=0;i<dimy;i++){
            for (int j=0;j<dimx;j++){
                this.getChildren().add(Mino.of(minoSize,j*minoSize,(dimy-1-i)*minoSize,"Clear"));
            }
        }
        drawBoard();
    }

    private int getIndex(int x, int y){
        return y * dimx + x;
    }

    public void drawBoard(){
        ObservableList<Node> children = getChildren();
        for (int i=0;i<dimy;i++){
            for (int j=0;j<dimx;j++){
                ((Mino)children.get(getIndex(j,i))).setColor(board.getColorMap().get(board.get(j,i)));
            }
        }
    }
}
