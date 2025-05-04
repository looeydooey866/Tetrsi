package com.example.tetrsi.Model.Definitions;

import java.util.ArrayList;
import java.util.Arrays;

public class Row {
    private Coord[] list;

    public static Coord[] copyCoordArray(Coord... array){
        Coord[] result = new Coord[array.length];
        for (int i=0;i<array.length;i++){
            result[i] = array[i].copy();
        }
        return result;
    }

    public Row(int i){
        this.list = new Coord[i];
    }

    public Row(Coord... list){
        this.list = list;
    }

    public Row(Row r){
        this.list = copyCoordArray(r.list);
    }

    public Row(String s){
        ArrayList<String> res = new ArrayList<>();
        String current = null;
        for (int i=0;i<s.length();i++){
            if (s.charAt(i) == ' '){
                continue;
            }
            if (s.charAt(i) == ']'){
                res.add(current);
                current = null;
            }
            else {
                if (current == null){
                    if (s.charAt(i) == '['){
                        current = "";
                    }
                }
                else {
                    current += s.charAt(i);
                }
            }
        }
        ArrayList<Coord> result = new ArrayList<>();
        for (int i=0;i<res.size();i++){
            result.add(new Coord(res.get(i)));
        }
        this.list = result.toArray(new Coord[0]);
    }

    public Row copy(){
        return new Row(this);
    }

    public Coord[] getList(){
        return list;
    }

    public void setList(Coord[] list){
        this.list = list;
    }

    public Coord get(int i){
        return this.list[i];
    }

    public void set(Coord c, int i){
        this.list[i] = c;
    }

    public Row rotateClockwise(){
        Row result = new Row(this.list.length);
        for (int i=0;i<this.list.length;i++){
            result.set(get(i).rotateClockwise(),i);
        }
        return result;
    }

    public Row rotateCounterClockwise(){
        Row result = new Row(this.list.length);
        for (int i=0;i<this.list.length;i++){
            result.set(get(i).rotateCounterclockwise(),i);
        }
        return result;
    }

    public Row rotateOneEighty(){
        Row result = new Row(this.list.length);
        for (int i=0;i<this.list.length;i++){
            result.set(get(i).rotateOneEighty(),i);
        }
        return result;
    }

    public Row plus(Row other){
        Row result = new Row(this.list.length);
        for (int i=0;i<this.list.length;i++){
            result.set(this.get(i).plus(other.get(i)),i);
        }
        return result;
    }

    public Row minus(Row other){
        Row result = new Row(this.list.length);
        for (int i=0;i<this.list.length;i++){
            result.set(this.get(i).minus(other.get(i)),i);
        }
        return result;
    }

    public int size(){
        return this.list.length;
    }

    public Row get(int... i){
        Row result = new Row(i.length);

        for (int j=0;j<i.length;j++){
            result.set(get(i[j]).copy(), j);
        }
        return result;
    }
}
