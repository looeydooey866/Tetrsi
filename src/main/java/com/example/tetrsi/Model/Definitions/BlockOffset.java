package com.example.tetrsi.Model.Definitions;

public class BlockOffset {
    private Row[] offsets;

    public static Row[] copyRowArray(Row... row){
        return Kick.copyRowArray(row);
    }

    public BlockOffset(Row[] offsets){
        this.offsets = offsets;
    }

    public BlockOffset(Row originalState){
        offsets = new Row[4];
        offsets[0] = originalState;
        for (int i=1;i<4;i++){
            offsets[i] = offsets[i-1].rotateClockwise();
        }
    }

    public BlockOffset(String[] offsets){
        this.offsets = new Row[4];
        for (int i=0;i<4;i++){
            this.offsets[i] = new Row(offsets[i]);
        }
    }

    public BlockOffset(String offset){
        this.offsets = new Row[4];
        offsets[0] = new Row(offset);
        for (int i=1;i<4;i++){
            offsets[i] = offsets[i-1].rotateClockwise();
        }
    }

    public BlockOffset(BlockOffset other){
        this.offsets = copyRowArray(other.offsets);
    }

    public BlockOffset copy(){
        return new BlockOffset(this);
    }

    public Row[] getAllOffsets(){
        return offsets;
    }

    public Row getOffsets(int i){
        return offsets[i];
    }

    public Row getNegOffsets(int i){
        Row result = new Row(offsets[i].size());
        for (int j=0;j<offsets[i].size();j++){
            result.set(offsets[i].get(j).scalarTimes(-1),j);
        }
        return result;
    }

    public static BlockOffset get(String s){
        return new BlockOffset(s);
    }
}
