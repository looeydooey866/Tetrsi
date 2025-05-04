package com.example.tetrsi.Model.Definitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Piece {
    private Block block;
    private Coord position;
    private int rotation;
    private boolean spun = false;

    public Piece(Block block, Coord position, int rotation) {
        this.block = block;
        this.position = position;
        this.rotation = rotation;
        this.spun = false;
    }

    public Piece(Block block, Coord position) {
        this(block, position, 0);
    }

    public Piece(Block block, GameRule gamerule){
        this(block, gamerule.getSpawnLocation(), 0);
    }

    public Piece(int index, GameRule gamerule){
        this(gamerule.getBlockList().get(index), gamerule.getSpawnLocation());
    }

    public Piece copy(){
        return new Piece(block.copy(), position.copy(), rotation);
    }

    public Block getBlock(){
        return block;
    }

    public Coord getPosition(){
        return position;
    }

    public boolean isSpun(){
        return spun;
    }

    public int getRotation(){
        return rotation;
    }

    public Row getCoordinates(){
        Row result = new Row();
        List<Coord> res = new ArrayList<>();
        for (Coord r : block.getBlockOffset().getOffsets(rotation).getList()){
            res.add(r.plus(position).copy());
        }
        result.setList(res.toArray(new Coord[0]));
        return result;
    }

    public static Row getCorners(){
        return new Row(
                new Coord(-1,1),
                new Coord(1,1),
                new Coord(1,-1),
                new Coord(-1,-1)
        );
    }

    public static int getCW(int rotation){
        return (rotation + 1) % 4;
    }

    public static int getCCW(int rotation){
        return (rotation + 3) % 4;
    }

    public static int getOneEighty(int rotation){
        return (rotation + 2) % 4;
    }

    public static Row getFacing(int i1){
        int i2 = getCW(i1);
        Row corners = getCorners();
        return corners.get(i1, i2);
    }

    public Piece translate(Coord c){
        Piece copy = copy();
        copy.position = copy.position.plus(c);
        return copy;
    }

    public boolean getOccupied(Board board){
        for (Coord c : getCoordinates().getList()){
            if (board.getFilled(c)){
                return true;
            }
        }
        return false;
    }

    public boolean translateAndCheck(Coord translation, int rotation, Board board){
        Piece copy = translate(translation);
        rotation = (rotation % 4 + 4) % 4;
        copy.rotation = (copy.rotation + rotation) % 4;
        if (!copy.getOccupied(board)){
            return true;
        }
        return false;
    }

    public boolean validateTransition(Coord translation, int rotation, Board board){
        if (translateAndCheck(translation, rotation, board)){
            position = position.plus(translation);
            return true;
        }
        else {
            return false;
        }
    }

    public void left(Board board){
        validateTransition(new Coord(-1,0),0, board);
    }

    public void right(Board board){
        validateTransition(new Coord(1,0),0, board);
    }

    public void drop(Board board){
        validateTransition(new Coord(0,-1),0,board);
    }

    static Coord drop = new Coord(0,-1);
    public void softDrop(Board board){
        while (validateTransition(drop, 0,board)){}
    }

    static Coord left = new Coord(-1,0);
    public void dasLeft(Board board){
        while (validateTransition(left, 0,board)){}
    }

    static Coord right = new Coord(1,0);
    public void dasRight(Board board){
        while (validateTransition(right, 0,board)){}
    }

    public void cw(Board board){
        for (Coord kick : block.getKickTable().getClockwise().get(rotation).getList()){
            if (translateAndCheck(kick, 1, board)){
                rotation = getCW(rotation);
                position = position.plus(kick);
                spun = true;
                return;
            }
        }
    }

    public void ccw(Board board){
        for (Coord kick : block.getKickTable().getCounterClockwise().get(rotation).getList()){
            if (translateAndCheck(kick, -1, board)){
                rotation = getCCW(rotation);
                position = position.plus(kick);
                spun = true;
                return;
            }
        }
    }

    public void oneEighty(Board board){
        for (Coord kick : block.getKickTable().getOneEighty().get(rotation).getList()){
            if (translateAndCheck(kick, 2, board)){
                rotation = getOneEighty(rotation);
                position = position.plus(kick);
                spun = true;
                return;
            }
        }
    }

    public void place(Board board){
        for (Coord c : getCoordinates().getList()){
            board.set(block.getIndex(), c);
        }
    }

    public void left(State state){left(state.getBoard());}
    public void right(State state){right(state.getBoard());}
    public void dasLeft(State state){dasLeft(state.getBoard());}
    public void dasRight(State state){dasRight(state.getBoard());}
    public void cw(State state){cw(state.getBoard());}
    public void ccw(State state){ccw(state.getBoard());}
    public void oneEighty(State state){oneEighty(state.getBoard());}
    public void softDrop(State state){softDrop(state.getBoard());}

    private void doInstruction(String s, Board board){
        switch(s){
            case "dasleft" -> dasLeft(board);
            case "dasright" -> dasRight(board);
            case "left" -> dasLeft(board);
            case "right" -> dasRight(board);
            case "cw" -> cw(board);
            case "ccw" -> ccw(board);
            case "180" -> oneEighty(board);
            case "sd" -> softDrop(board);
        }
    }

    public void doInstructions(String s, Board board){
        String[] queries = s.split(",");
        for (int i=0;i<queries.length;i++){
            doInstruction(queries[i].trim(),board);
        }
    }
}
