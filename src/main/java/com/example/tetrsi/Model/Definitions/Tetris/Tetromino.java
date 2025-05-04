package com.example.tetrsi.Model.Definitions.Tetris;

import com.example.tetrsi.Model.Definitions.*;

import java.util.List;

public enum Tetromino{
    S( "[0,0],[-1,0],[0,1],[1,1]", SRS.CommonCW,SRS.CommonCCW,SRS.None,"32CD32","S"),
    Z("[0,0],[-1,1],[0,1],[1,0]", SRS.CommonCW,SRS.CommonCCW,SRS.None,"#C5464C","Z"),
    I( "[0,0],[-1,0],[1,0],[2,0]", SRS.ICW, SRS.ICCW, SRS.None, "59CB9B", "I"),
    O( "[0,0],[1,0],[0,1],[1,1]", SRS.None, SRS.None, SRS.None, "FFDA03", "O"),
    L( "[0,0],[-1,0],[1,0],[1,1]", SRS.CommonCW, SRS.CommonCCW, SRS.CommonFlip, "FFA500", "L"),
    T("[0,0],[-1,0],[1,0],[0,1]", SRS.CommonCW, SRS.CommonCCW, SRS.CommonFlip, "B100CD", "T"),
    J( "[0,0],[-1,1],[-1,0],[1,0]", SRS.CommonCW, SRS.CommonCCW, SRS.CommonFlip, "#2234A8", "J");
    private final Block block;
    Tetromino(String offset, Kick clockwise, Kick counterclockwise, Kick oneeighty, String color, String name){
        this.block = new Block(ordinal(), BlockOffset.get(offset), KickTable.getTable(clockwise,counterclockwise,oneeighty), RGB.valueOf(color),name);
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


