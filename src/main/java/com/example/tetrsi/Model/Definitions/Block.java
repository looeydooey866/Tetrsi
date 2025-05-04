package com.example.tetrsi.Model.Definitions;

import javafx.scene.paint.Color;

public class Block {
    private int blockIndex;
    private BlockOffset blockOffset;
    private KickTable kickTable;
    private RGB color;
    private String name;

    public Block(int blockIndex, BlockOffset blockOffset, KickTable kickTable, RGB color, String name) {
        this.blockIndex = blockIndex;
        this.blockOffset = blockOffset;
        this.kickTable = kickTable;
        this.color = color;
        this.name = name;
    }

    public Block copy(){
        return new Block(blockIndex, blockOffset.copy(), kickTable.copy(), color, name);
    }

    public BlockOffset getBlockOffset() {
        return blockOffset;
    }

    public KickTable getKickTable() {
        return kickTable;
    }

    public RGB getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int getIndex(){
        return blockIndex;
    }
}
