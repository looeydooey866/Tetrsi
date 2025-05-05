package com.example.tetrsi.Model.Definitions;

import javafx.scene.paint.Color;

public class Block {
    private int blockIndex;
    private BlockOffset blockOffset;
    private KickTable kickTable;
    private String color;
    private String name;
    private double scaling;

    public Block(int blockIndex, BlockOffset blockOffset, KickTable kickTable, String color, String name, double scaling) {
        this.blockIndex = blockIndex;
        this.blockOffset = blockOffset;
        this.kickTable = kickTable;
        this.color = color;
        this.name = name;
        this.scaling = scaling;
    }

    public Block copy(){
        return new Block(blockIndex, blockOffset.copy(), kickTable.copy(), color, name, scaling);
    }

    public BlockOffset getBlockOffset() {
        return blockOffset;
    }

    public KickTable getKickTable() {
        return kickTable;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int getIndex(){
        return blockIndex;
    }

    public double getScaling(){
        return scaling;
    }
}
