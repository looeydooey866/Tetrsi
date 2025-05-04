package com.example.tetrsi.Model.Definitions;

import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.List;

public interface GameRule {
    int getNumBlocks();
    List<Block> getBlockList();
    int getHeight();
    int getWidth();
    int getVisibleHeight();
    int getVisibleWidth();
    Coord getSpawnLocation();
    ClearType getClearType(Board board, Piece piece);
    void setSpinRule(SpinRule spinrule);
    void setAttackTable(AttackTable table);
    String getClearMessage(Piece piece, ClearType cleartype, int cleared);
    int getAttack(GameRule.ClearType cleartype, int lines, int b2b, int ren);
    int getPreview();

    public static final List<String> NumberSuffix = List.of(
            "Zero",
            "Single",
            "Double",
            "Triple",
            "Quadruple",
            "Penta",
            "Hexa",
            "Hepta",
            "Octa",
            "Nona",
            "Deca",
            "Hendeca",
            "Dodeca",
            "Triskaideca",
            "Tetrakaideca",
            "Pentakaideca",
            "Hexakaideca",
            "Heptakaideca",
            "Octakaideca",
            "Nonakaideca",
            "Icos"
    );

    enum ClearType{
        Normal,
        SpinMini,
        Spin
    }
}

