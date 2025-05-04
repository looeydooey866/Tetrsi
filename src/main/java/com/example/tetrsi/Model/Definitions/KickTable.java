package com.example.tetrsi.Model.Definitions;

public class KickTable {
    private Kick clockwise, counterclockwise, oneeighty;

    public KickTable(Kick clockwise, Kick counterclockwise, Kick oneeighty){
        this.clockwise = clockwise;
        this.counterclockwise = counterclockwise;
        this.oneeighty = oneeighty;
    }

    public KickTable copy(){
        return new KickTable(clockwise.copy(), counterclockwise.copy(), oneeighty.copy());
    }

    public Kick getClockwise() {
        return clockwise;
    }

    public Kick getCounterClockwise() {
        return counterclockwise;
    }

    public Kick getOneEighty() {
        return oneeighty;
    }

    public static KickTable getTable(Kick clockwise, Kick counterclockwise, Kick oneeighty){
        return new KickTable(clockwise, counterclockwise, oneeighty);
    }
}
