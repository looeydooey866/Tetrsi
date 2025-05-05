package com.example.tetrsi.Model.Test;
import com.example.tetrsi.Model.Definitions.*;
import com.example.tetrsi.Model.Definitions.Tetris.Tetris;
import com.example.tetrsi.Model.Definitions.Tetris.Tetromino;

import java.util.Arrays;

public class RowTest {
    public static void main(String[] args) {
        Tetris tetris = new Tetris();
        State s = new State(tetris);
        Piece p = new Piece(Tetromino.L.getBlock(), tetris);
        p.doInstructions("cw,dasleft,sd",s.getBoard());
        s.place(p);
        p = new Piece(Tetromino.I.getBlock(),tetris);
        p.doInstructions("sd",s.getBoard());
        s.place(p);
        p = new Piece(Tetromino.Z.getBlock(),tetris);
        p.doInstructions("sd",s.getBoard());
        s.place(p);
        p = new Piece(Tetromino.S.getBlock(),tetris);
        p.doInstructions("cw,dasright,sd,dasleft",s.getBoard());
        s.place(p);
        p = new Piece(Tetromino.O.getBlock(),tetris);
        p.doInstructions("dasright,sd",s.getBoard());
        s.place(p);
        p = new Piece(Tetromino.T.getBlock(),tetris);
        p.doInstructions("dasleft,cw,sd,cw",s.getBoard());
        s.place(p);
        s.print();
        s.hold(Tetromino.T.getBlock());
        s.print();
    }

    public static void getBoardWithPiece(Board board, Piece piece) {
        Board copy = new Board(board);
        piece.place(copy);
        System.out.println(copy);
    }
}
