package com.example.tetrsi.Model.Definitions;

public class Coord {
    private int x;
    private int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coord(Coord c){
        this(c.x, c.y);
    }

    public Coord(String s){
        String[] split = s.split("[, ]+");
        this.x = Integer.parseInt(split[0]);
        this.y = Integer.parseInt(split[1]);
    }

    public int getX(){ return x; }
    public int getY(){ return y; }
    public Coord plus(Coord c){ return new Coord(this.x + c.x, this.y + c.y); }
    public Coord minus(Coord c){ return new Coord(this.x - c.x, this.y - c.y); }
    public Coord scalarPlus(int x){ return new Coord(this.x + x, this.y + x); }
    public Coord scalarMinus(int x){ return new Coord(this.x - x, this.y - x); }
    public Coord scalarTimes(int x){ return new Coord(this.x * x, this.y * x); }
    public void setX(int x){ this.x = x; }
    public void changeX(int x){ this.x += x; }
    public void setY(int y){ this.y = y; }
    public void changeY(int y){ this.y += y; }
    public Coord rotateClockwise(){return new Coord(this.y, -this.x);}
    public Coord rotateCounterclockwise(){return new Coord(-this.y, this.x);}
    public Coord rotateOneEighty(){return new Coord(-this.x, -this.y);}
    public Coord copy(){return new Coord(this.x, this.y);}
    public String toString(){return String.format("(%d,%d)", this.x, this.y);}
}
