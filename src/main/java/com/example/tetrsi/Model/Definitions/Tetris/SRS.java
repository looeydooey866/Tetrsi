package com.example.tetrsi.Model.Definitions.Tetris;

import com.example.tetrsi.Model.Definitions.Kick;
import com.example.tetrsi.Model.Definitions.Row;

public class SRS {
    private SRS(){}

    public static final Row[] CommonOffsetData = new Row[]{
            new Row("[0,0],[0,0],[0,0],[0,0],[0,0]"),
            new Row("[0,0],[1,0],[1,-1],[0,2],[1,2]"),
            new Row("[0,0],[0,0],[0,0],[0,0],[0,0]"),
            new Row("[0,0],[-1,0],[-1,-1],[0,2],[-1,2]")
    };

    public static final Row[] IOffsetData = new Row[]{
            new Row("[0,0],[-1,0],[2,0],[-1,0],[2,0]"),
            new Row("[-1,0],[0,0],[0,0],[0,1],[0,-2]"),
            new Row("[-1,1],[1,1],[-2,1],[1,0],[-2,0]"),
            new Row("[0,1],[0,1],[0,1],[0,-1],[0,2]")
    };

    public static final Kick CommonCW = new Kick(Kick.copyRowArray(CommonOffsetData)).foldStride(1);
    public static final Kick CommonCCW = new Kick(Kick.copyRowArray(CommonOffsetData)).foldStride(-1);
    public static final Kick ICW = new Kick(Kick.copyRowArray(IOffsetData)).foldStride(1);
    public static final Kick ICCW = new Kick(Kick.copyRowArray(IOffsetData)).foldStride(-1);
    public static Kick None = new Kick("[0,0]","[0,0]","[0,0]","[0,0]");

    public static final Kick CommonFlip = new Kick(
            "[0, 0], [0, 1], [1, 1],[-1, 1], [1, 0],[-1, 0]",
            "[0, 0],[ 1, 0],[ 1, 2],[ 1, 1],[ 0, 2], [0, 1]",
            "[0, 0],[ 0,-1],[-1,-1],[ 1,-1],[-1, 0], [1, 0]",
            "[0, 0],[-1, 0],[-1, 2],[-1, 1],[ 0, 2], [0, 1]"
    );
}
