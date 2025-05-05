package com.example.tetrsi.Controller;

import com.example.tetrsi.Model.Definitions.Block;
import com.example.tetrsi.Model.Definitions.Queue;
import com.example.tetrsi.Model.Definitions.Tetris.Tetris;
import com.example.tetrsi.Model.Definitions.Tetris.Tetromino;
import com.example.tetrsi.UI.HoldBar;
import com.example.tetrsi.UI.PieceBox;
import com.example.tetrsi.UI.QueueBar;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.Random;

public class TetrisGameController {
    @FXML private Pane screen;
    private Scene scene;

    @FXML
    public void initialize(){
        Random random = new Random();
        Platform.runLater(()->{
            Queue q = Queue.ofRule(new Tetris());
            QueueBar que = new QueueBar(400,q,5,500,100);
            screen.getChildren().add(que);

            HoldBar bar = new HoldBar(400, null, 100, 100);
            screen.getChildren().add(bar);

            screen.setOnMouseClicked(e -> {
                Block hold = Tetromino.getBlock(random.nextInt(7));
                q.advance();
                que.advanceQueue();
                //bar.updateHold(hold, random.nextBoolean());
                que.setInfo1("suodbfapsudbfsadufbsiadufbasdpiufbsdaifubsdaf");
                bar.updateCombo(random.nextInt(50));
                bar.updateB2b(random.nextInt(10));
                bar.updateClearMessage("T-Spin Double");

            });
        });
    }
}
