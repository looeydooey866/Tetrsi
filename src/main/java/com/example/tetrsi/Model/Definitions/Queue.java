package com.example.tetrsi.Model.Definitions;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Queue {
    //can either randomly generate on our own, or the server will send over a queue (for state syncing)
    private ArrayList<Block> queue = new ArrayList<>();
    private Block hold;
    private ArrayList<Block> bag;
    private int numBlocks;
    private boolean selfRng;

    private Queue(GameRule rule){
        selfRng = true;
        bag = new ArrayList<>(rule.getBlockList());
        for (int i=0;i<10;i++){
            Collections.shuffle(bag);
            queue.addAll(bag);
        }
    }

    private Queue(List<Block> queue){
        selfRng = false;
        this.queue.addAll(queue);
    }

    private void rng(){
        if (selfRng) {
            if (queue.size() < numBlocks * 2) {
                for (int i = 0; i < 5; i++) {
                    Collections.shuffle(bag);
                    queue.addAll(bag);
                }
            }
        }
    }

    public void advance(){
        queue.remove(0);
        rng();
    }

    public void hold(){
        if (hold == null){
            hold = queue.remove(0);
            rng();
        }
        else {
            Block temp = hold;
            hold = queue.get(0);
            queue.set(0, temp);
        }
    }

    public Block getFront(){
        return queue.get(0);
    }

    public ArrayList<Block> getQueue(){
        return queue;
    }

    public Block getHold(){
        return hold;
    }

    /**
     * @param rule this function creates a queue that will randomly generate blocks
     * @return returns a new Queue object
     */
    public static Queue ofRule(GameRule rule){
        return new Queue(rule);
    }

    public static Queue ofQueue(List<Block> queue){
        return new Queue(queue);
    }
}
