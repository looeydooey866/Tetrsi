package com.example.tetrsi.UI;

import com.example.tetrsi.Model.Definitions.GameRule;
import com.example.tetrsi.Model.Definitions.State;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameState extends HBox {
    private QueueBar queue;
    private HoldBar hold;
    private GameBoard board;
    private State state;

    public GameState(double desiredWidth, double desiredHeight, GameRule rule){

    }
}
