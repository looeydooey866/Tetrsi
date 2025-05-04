package com.example.tetrsi.Model.Definitions.Tetris.SpinRules;

import com.example.tetrsi.Model.Definitions.*;

public class AllMini implements SpinRule {
    private AllMini(){}
    private static final AllMini INSTANCE = new AllMini();

    public GameRule.ClearType check(Board board, Piece piece) {
        if (AllSpin.immobileCheck(board,piece)){
            return GameRule.ClearType.SpinMini;
        }
        else {
            return GameRule.ClearType.Normal;
        }
    }

    public static AllMini getInstance(){
        return INSTANCE;
    }
}
