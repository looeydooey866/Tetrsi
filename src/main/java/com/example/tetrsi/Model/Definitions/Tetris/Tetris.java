package com.example.tetrsi.Model.Definitions.Tetris;


import com.example.tetrsi.Model.Definitions.*;
import com.example.tetrsi.Model.Definitions.Tetris.AttackTables.Modern;
import com.example.tetrsi.Model.Definitions.Tetris.SpinRules.TSpin;

import java.util.List;

//next time, custom mode, the spawn should be board height + 1
public class Tetris implements GameRule {
    private SpinRule spinrule = TSpin.getInstance();
    private AttackTable attacktable = Modern.getInstance();
    public static final List<Block> BlockList = Tetromino.getBlocks();
    public static final int height = 32, width = 10, visibleHeight = 20, visibleWidth = 10;
    public static final int preview = 5;
    public static final Coord spawnLocation = new Coord(4,21);

    @Override
    public int getNumBlocks(){
        return BlockList.size();
    }

    @Override
    public List<Block> getBlockList() {
        return BlockList;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getVisibleHeight() {
        return visibleHeight;
    }

    @Override
    public int getVisibleWidth() {
        return visibleWidth;
    }

    @Override
    public Coord getSpawnLocation() {
        return spawnLocation;
    }

    @Override
    public ClearType getClearType(Board board, Piece piece) {
        if (!piece.isSpun()){
            return ClearType.Normal;
        }
        return spinrule.check(board, piece);
    }

    @Override
    public void setSpinRule(SpinRule spinrule) {
        this.spinrule = spinrule;
    }

    @Override
    public void setAttackTable(AttackTable table) {
        this.attacktable = attacktable;
    }

    @Override
    public String getClearMessage(Piece piece, ClearType cleartype, int cleared) {
        String T = "", Spin = "", Double = "";

        if (cleartype != ClearType.Normal){
            T = piece.getBlock().getName();
            Spin = cleartype.toString();
        }

        if (cleared != 0){
            Double = NumberSuffix.get(cleared);
        }

        String s1 = (!T.isEmpty() && !Spin.isEmpty() ? "-" : "");
        String s2 = (!Spin.isEmpty() && !Double.isEmpty() ? " " : "");

        return T + s1 + Spin + s2 + Double;
    }

    @Override
    public int getAttack(ClearType cleartype, int lines, int b2b, int ren) {
        return attacktable.getAttack(cleartype, lines, b2b, ren);
    }

    @Override
    public int getPreview(){
        return preview;
    }
}