package com.example.tetrsi.Model.Definitions.Tetris.SpinRules;

import com.example.tetrsi.Model.Definitions.*;
import com.example.tetrsi.Model.Definitions.Tetris.Tetromino;

public class TSpin implements SpinRule {
    private TSpin(){}
    private static final TSpin INSTANCE = new TSpin();

    public GameRule.ClearType threeCornerCheck(Board board, Piece piece) {
        int count = 0;
        for (Coord corner : Piece.getCorners().getList()){
            if (board.getFilled(piece.getPosition().plus(corner))){
                count++;
            }
        }

        if (count < 3){
            return GameRule.ClearType.Normal;
        }

        if (count == 4){
            return GameRule.ClearType.Spin;
        }

        int facing = 0;
        for (Coord block : Piece.getFacing(piece.getRotation()).getList()){
            if (board.getFilled(piece.getPosition().plus(block))){
                facing++;
            }
        }
        if (facing == 1){
            return GameRule.ClearType.SpinMini;
        }

        return GameRule.ClearType.Spin;
    }

    public GameRule.ClearType check(Board board, Piece piece) {
        if(piece.getBlock().getIndex() == Tetromino.T.getIndex()){
            return threeCornerCheck(board, piece);
        }
        else {
            return GameRule.ClearType.Normal;
        }
    }

    public static TSpin getInstance(){
        return INSTANCE;
    }
}
