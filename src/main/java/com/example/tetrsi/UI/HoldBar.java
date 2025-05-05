package com.example.tetrsi.UI;

import com.example.tetrsi.Model.Definitions.Block;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class HoldBar extends VBox {
    private double height, width;
    private double pieceBoxHeight;
    private double x,y;
    private InfoBox clear, b2b, combo;
    private InfoBox[] infos;
    private Block hold;
    private PieceBox holdBox;
    private static final int numinfos = 4;
    private boolean held = false;

    public HoldBar(double preferredHeight, Block hold, double x, double y){
        this.height = preferredHeight;
        this.pieceBoxHeight = height / Constants.maxBoxes;
        this.width = PieceBox.getWidth(pieceBoxHeight);
        this.hold = hold;
        this.x = x;
        this.y = y;
        this.setLayoutX(x);
        this.setLayoutY(y);
        instantiateHold();
        instantiateInfos();
        VBox.setVgrow(this, Priority.ALWAYS);
    }

    private void instantiateHold(){
        this.holdBox = PieceBox.of(hold,pieceBoxHeight);
        this.getChildren().add(this.holdBox);
    }

    private void instantiateInfos(){
        Region r = new Region();
        r.setMaxHeight(999);
        this.getChildren().add(r);
        clear = InfoBox.of(pieceBoxHeight,PieceBox.getWidth(pieceBoxHeight),24);
        b2b = InfoBox.of(pieceBoxHeight/2,PieceBox.getWidth(pieceBoxHeight),18);
        combo = InfoBox.of(pieceBoxHeight/2,PieceBox.getWidth(pieceBoxHeight),18);
        infos = new InfoBox[numinfos];
        for (int i=0;i<numinfos;i++){
            infos[i] = InfoBox.of(pieceBoxHeight,PieceBox.getWidth(pieceBoxHeight),18);
        }
        this.getChildren().addAll(clear,b2b,combo);
        this.getChildren().addAll(infos);
    }

    //returns null if no hold
    public Block hold(Block hold){
        held = true;
        if (hold != null) {
            Block res = this.hold;
            int index = -1;
            for (int i = 0; i < this.getChildren().size(); i++) {
                if (this.getChildren().get(i) == this.holdBox) {
                    index = i;
                    break;
                }
            }
            this.getChildren().remove(index);
            this.holdBox = PieceBox.of(hold, pieceBoxHeight);
            this.holdBox.setHold();
            this.getChildren().add(index, this.holdBox);
            return res;
        }
        else {
            this.hold = hold;
            return null;
        }
    }

    public void advance(){
        this.holdBox.unHold();
        held = false;
    }

    public void updateClearMessage(String msg){
        clear.setText(msg);
    }

    public void updateB2b(int b2b){
        String format = "B2B x%d";
        this.b2b.setText(String.format(format, b2b));
    }

    public void updateCombo(int combo){
        String format = "COMBO %d";
        this.combo.setText(String.format(format,combo));
    }

    public void updateInfos(String[] infos){
        assert infos.length == numinfos;
        for (int i=0;i<numinfos;i++){
            this.infos[i].setText(infos[i]);
        }
    }
}
