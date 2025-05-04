package com.example.tetrsi.Model.Definitions;

public class Kick {
    private Row[] table;

    public static Row[] copyRowArray(Row... array){
        Row[] result = new Row[array.length];
        for (int i=0;i<array.length;i++){
            result[i] = array[i].copy();
        }
        return result;
    }

    public Kick(Row... table) {
        this.table = table;
    }

    public Kick(String... list){
        table = new Row[list.length];
        for (int i=0;i<list.length;i++){
            table[i] = new Row(list[i]);
        }
    }

    public Kick(int i){
        this.table = new Row[i];
    }

    public Kick(Kick k){
        this.table = copyRowArray(k.table);
    }

    public Row get(int i) {
        return this.table[i];
    }

    public void set(Row row, int i){
        this.table[i] = row;
    }

    public Kick copy(){
        return new Kick(this);
    }

    // This is for converting offset data to kicks, see SRS rotation system on harddrop wiki
    public Kick foldStride(int stride){
        stride %= table.length;
        stride += table.length;
        stride %= table.length;
        Kick result = new Kick(this.table.length);
        for (int i=0,j=stride;i<this.table.length;i++,j=(j+1)%stride){
            result.table[i] = table[i].minus(table[j]);
        }
        return result;
    }

    public Row[] getTable(){
        return table;
    }
}
