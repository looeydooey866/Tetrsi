package com.example.tetrsi.Model.Definitions;

import java.util.List;

public class State {
    private GameRule rule;
    private Board board;
    private int b2b = 0;
    private int ren = 0;
    private int piecesPlaced = 0;
    private int attacksSent = 0;
    private int lastAttackSent;
    private int lastClearLines;
    private Queue queue;
    private Block hold;
    private GameRule.ClearType lastClear;
    private String lastClearMessage;

    public State(GameRule gamerule){
        this.rule = gamerule;
        this.board = new Board(gamerule);
        this.queue = Queue.ofRule(gamerule);
    }

    public void place(Piece p){
        piecesPlaced++;
        lastClear = rule.getClearType(board,p);
        p.place(board);
        int lines = board.getFullLines();
        board.clearLines();
        if (lines != 0){
            ren++;
            if (lastClear != GameRule.ClearType.Normal){
                b2b++;
            }
            else {
                b2b = 0;
            }
        }
        else {
            ren = 0;
        }
        lastClearLines = lines;
        lastAttackSent = rule.getAttack(lastClear,lines, b2b, ren);
        lastClearMessage = rule.getClearMessage(p, lastClear, lines);
    }

    public Block hold(Block hold){
        if (this.hold == null){
            this.hold = hold;
            Block temp = queue.getFront();
            queue.advance();
            return temp;
        }
        else {
            Block temp = this.hold;
            this.hold = hold;
            return temp;
        }
    }

    public Block getNext(){
        Block res = queue.getFront();
        queue.advance();
        return res;
    }

    public void print(){
        PrettyPrint formatter = new PrettyPrint(2,0);
        System.out.println(board);
        StringBuilder res = new StringBuilder();
        res.append(lastClearMessage).append('\n');
        res.append("Queue: ");
        for (int i=0;i<rule.getPreview();i++){
            res.append(queue.getQueue().get(i).getName());
            if (i != rule.getPreview()-1){
                res.append(", ");
            }
        }
        res.append('\n');
        String hold;
        if (this.hold != null){
            hold = this.hold.getName();
        }
        else {
            hold = "None";
        }
        res.append(String.format("Hold: %s",hold));
        System.out.println(formatter.format(res));
    }

    public Board getBoard(){ return board; }
    public int getB2b(){ return b2b; }
    public int getB2bCounter(){return b2b - 1;}
    public int getRen(){ return ren; }
    public int getPiecesPlaced(){ return piecesPlaced; }
    public int getAttacksSent(){ return attacksSent ;}
    public List<Block> getQueue(){return queue.getQueue();}
    public Block getHold(){return hold;}
    public int getLastAttack(){return lastAttackSent;}
    public int getLinesCleared(){return lastClearLines;}
    public GameRule.ClearType getLastClear(){return lastClear;}
    public String getClearMessage(){return lastClearMessage;}
}
