package com.example.tetrsi.Model.Definitions.Tetris.SpinRules;

import com.example.tetrsi.Model.Definitions.Board;
import com.example.tetrsi.Model.Definitions.GameRule;
import com.example.tetrsi.Model.Definitions.Piece;
import com.example.tetrsi.Model.Definitions.SpinRule;

public class NoSpin implements SpinRule {
    public GameRule.ClearType check(Board board, Piece piece) {
        return GameRule.ClearType.Normal;
    }
}
