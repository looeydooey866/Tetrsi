package com.example.tetrsi.Model.Definitions.Tetris.AttackTables;

import com.example.tetrsi.Model.Definitions.AttackTable;
import com.example.tetrsi.Model.Definitions.GameRule;

public class Modern implements AttackTable {
    private static Modern INSTANCE = new Modern();
    private Modern(){}
    private static int getNormalBase(int lines){
        return (lines < 2 ? lines : (lines - 2) * 2);
    }
    private static int getMiniBase(int lines){
        return lines;
    }
    private static int getSpinBase(int lines){
        return lines * 2;
    }
    //b2b is the raw amount of moves (AFTER the current clear) that the user has done
    private static int getB2bBonus(int b2b){
        int[] counts = {2, 4, 9, 25, 68, 186, 505, 1371, 3723, 10111, 27459};
        for (int i=0;i<counts.length;i++){
            if (b2b < counts[i]){
                return i;
            }
        }
        return -1;
    }
    private static int getBase(GameRule.ClearType cleartype, int lines, int b2b){
        int raw = switch(cleartype){
            case Normal -> getNormalBase(lines);
            case SpinMini -> getMiniBase(lines);
            case Spin -> getSpinBase(lines);
        };
        return raw + getB2bBonus(b2b);
    }

    //the combo is the combo before the clear?? so that means if you just cleared a line the combo is still 0
    @Override
    public int getAttack(GameRule.ClearType cleartype, int linesCleared, int b2b, int ren) {
        ren--;
        int base = getBase(cleartype, linesCleared, b2b);
        if (base == 0){
            if (ren >= 2){
                return (int)Math.log(1+1.25*ren);
            }
            else {
                return 0;
            }
        }
        else {
            return (int) (base * (1 + 0.25 * ren));
        }
    }

    public static Modern getInstance(){
        return INSTANCE;
    }
}
