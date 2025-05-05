package com.example.tetrsi.Model.Definitions;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private int[][] board;
    private final int height, width;
    private final int numBlocks;
    private final int EMPTY;
    private final int GARBAGE;
    private int vHeight, vWidth;
    private Map<Integer, String> colorMap;

    public Board(GameRule g){
        this.height = g.getHeight();
        this.width = g.getWidth();
        this.board = new int[height][width];
        this.numBlocks = g.getNumBlocks();
        this.EMPTY = numBlocks;
        this.GARBAGE = numBlocks + 1;
        this.vHeight = g.getVisibleHeight();
        this.vWidth = g.getVisibleWidth();
        empty();
        initializeColorMap(g);
    }

    public Board(Board other){
        this.height = other.height;
        this.width = other.width;
        this.numBlocks = other.numBlocks;
        this.board = new int[height][width];
        this.EMPTY = other.EMPTY;
        this.GARBAGE = other.GARBAGE;
        for (int i=0;i<board.length;i++){
            for (int j=0;j<board[i].length;j++){
                board[i][j] = other.board[i][j];
            }
        }
        this.colorMap = other.colorMap;
    }

    public void empty(){
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                board[i][j] = EMPTY;
            }
        }
    }

    private void initializeColorMap(GameRule g){
        this.colorMap = new HashMap<>();
        for (int i=0;i<numBlocks;i++){
            colorMap.put(i, g.getBlockList().get(i).getColor());
        }
        colorMap.put(EMPTY,"Clear");
        colorMap.put(GARBAGE,"Garbage");
    }

    public Map<Integer, String> getColorMap(){
        return colorMap;
    }

    public int[][] getBoard(){
        return board;
    }

    public int get(int x, int y){
        return board[y][x];
    }

    public boolean getBound(int x, int y){
        return (x >= 0 && x < width && y >= 0 && y < height);
    }

    public int get(Coord c){
        return get(c.getX(), c.getY());
    }

    public boolean getFilled(int x, int y){
        if (!getBound(x,y)){
            return true;
        }

        return (get(x,y) != numBlocks);
    }

    public boolean getFilled(Coord c){
        return getFilled(c.getX(), c.getY());
    }

    public void set(int val, int x, int y){
        board[y][x] = val;
    }

    public void set(int val, Coord c){
        set(val, c.getX(), c.getY());
    }

    public boolean rowIsFull(int row){
        for (int i=0;i<width;i++){
            if (board[row][i] == EMPTY){
                return false;
            }
        }
        return true;
    }

    public int getFullLines(){
        int res = 0;
        for (int i=0;i<height;i++){
            if (rowIsFull(i)){
                res++;
            }
        }
        return res;
    }

    public void clearLines(){
        for (int i=height-1;i>=0;i--){
            if (rowIsFull(i)){
                for (int j=i;j+1<height;j++){
                    board[j] = board[j+1].clone();
                }
            }
        }
        for (int j=0;j<width;j++){
            board[height-1][j] = EMPTY;
        }
    }

    public void garbage(int column, int garbage){
        int[] row = new int[width];
        for (int i=0;i<width;i++){
            if (i != column){
                row[i] = GARBAGE;
            }
            else {
                row[i] = EMPTY;
            }
        }

        for (int i=height-1;i>=garbage;i--){
            board[i] = board[i-garbage].clone();
        }
        for (int i=garbage-1;i>=0;i--){
            board[i] = row.clone();
        }
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        for (int i=height-1;i>=0;i--){
            res.append(String.format("%2d|", i));
            for (int j=0;j<width;j++){
                res.append(get(j, i) != numBlocks ? "[]" : "  ");
            }
            res.append("|");
            if (i != 0)
                res.append("\n");
        }
        return res.toString();
    }

    public Board copy(){
        return new Board(this);
    }

    public int getVHeight(){
        return vHeight;
    }

    public int getVWidth(){
        return vWidth;
    }

    public int getNumBlocks(){
        return numBlocks;
    }
}