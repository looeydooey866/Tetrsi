package com.example.tetrsi.Model.Definitions.Tetris;

import com.example.tetrsi.Model.Definitions.*;

import java.util.List;

public enum Tetromino{
    S( "[0,0],[-1,0],[0,1],[1,1]", SRS.CommonCW,SRS.CommonCCW,SRS.None,"Green","S"),
    Z("[0,0],[-1,1],[0,1],[1,0]", SRS.CommonCW,SRS.CommonCCW,SRS.None,"Red","Z"),
    I( "[0,0],[-1,0],[1,0],[2,0]", SRS.ICW, SRS.ICCW, SRS.None, "Blue", "I"),
    O( "[0,0],[1,0],[0,1],[1,1]", SRS.None, SRS.None, SRS.None, "Yellow", "O"),
    L( "[0,0],[-1,0],[1,0],[1,1]", SRS.CommonCW, SRS.CommonCCW, SRS.CommonFlip, "Orange", "L"),
    T("[0,0],[-1,0],[1,0],[0,1]", SRS.CommonCW, SRS.CommonCCW, SRS.CommonFlip, "Purple", "T"),
    J( "[0,0],[-1,1],[-1,0],[1,0]", SRS.CommonCW, SRS.CommonCCW, SRS.CommonFlip, "DarkBlue", "J");
    private final Block block;
    Tetromino(String offset, Kick clockwise, Kick counterclockwise, Kick oneeighty, String color, String name){
        this.block = new Block(ordinal(), BlockOffset.get(offset), KickTable.getTable(clockwise,counterclockwise,oneeighty), color,name, 1.0);
    }
    public static List<Block> getBlocks(){
        return List.of(S.block,Z.block,I.block,O.block,L.block,T.block,J.block);
    }

    public static Block getBlock(int index){
        return getBlocks().get(index);
    }

    public Block getBlock(){
        return block.copy();
    }

    public int getIndex(){
        return ordinal();
    }
}


