package com.example.tetrsi.Model.Definitions;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Queue {
    //can either randomly generate on our own, or the server will send over a queue (for state syncing)
    private ArrayList<Block> queue = new ArrayList<>();
    private ArrayList<Block> bag;
    private int numBlocks;
    private boolean selfRng;

    private Queue(GameRule rule){
        selfRng = true;
        numBlocks = rule.getNumBlocks();
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

    public Block getFront(){
        return queue.get(0);
    }

    public ArrayList<Block> getQueue(){
        return queue;
    }

    /**
     * This function creates a Queue object based on a rule.
     * It will randomly generate blocks by itself.
     * @param rule This function creates a queue that will randomly generate blocks
     * @return returns a new Queue object
     */
    public static Queue ofRule(GameRule rule){
        return new Queue(rule);
    }

    /**
     * This function creates a Queue object based on an external queue.
     * Note that the queue will not remove blocks from the external queue, but from its
     * own internal queue. It must be reminded that the user must input additional blocks.
     * @param queue Takes in a queue object to be used as a list.
     * @return Returns a new Queue object that won't generate pieces on its own.
     */
    public static Queue ofQueue(List<Block> queue){
        return new Queue(queue);
    }

    public Block get(int i){
        return queue.get(i);
    }

    public void add(Block b){
        this.queue.add(b);
    }

    public void addAll(List<Block> b){
        this.queue.addAll(b);
    }
}
