package com.example.tetrsi.Model.Definitions.Tetris.SpinRules;

import com.example.tetrsi.Model.Definitions.*;

public class AllSpin implements SpinRule {
    AllSpin(){}
    private static final AllSpin INSTANCE = new AllSpin();

    public static boolean immobileCheck(Board board, Piece piece) {
        Coord[] translations = {
                new Coord(-1,0),
                new Coord(1,0),
                new Coord(0,1)
        };

        for (Coord c : translations) {
            if (!piece.translate(c).getOccupied(board)){
                return false;
            }
        }
        return true;
    }

    public GameRule.ClearType check(Board board, Piece piece) {
        if (immobileCheck(board, piece)) {
            return GameRule.ClearType.Spin;
        }
        else {
            return GameRule.ClearType.Normal;
        }
    }

    public static AllSpin getInstance(){
        return INSTANCE;
    }
}
