package com.example.tetrsi.Model.Definitions;

public interface SpinRule {
    GameRule.ClearType check(Board board, Piece piece);
}
