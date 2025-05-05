package com.example.tetrsi.Model.Definitions;

import java.util.ArrayList;
import java.util.Scanner;

public class PrettyPrint {
    private int wpad, hpad;
    private static final char topLeft = '┌';
    private static final char topRight = '┐';
    private static final char bottomLeft = '└';
    private static final char bottomRight = '┘';
    private static final char vert = '│';
    private static final char hori = '─';

    public PrettyPrint(int wpad, int hpad){
        this.wpad = wpad;
        this.hpad = hpad;
    }

    private static String repeatHori(int times){
        return new String(new char[times]).replace("\0",String.valueOf(hori));
    }

    private static String repeatSpace(int times){
        return new String(new char[times]).replace("\0"," ");
    }

    private String getRoof(int width){
        return topLeft +
                repeatHori(width + wpad * 2) +
                topRight + '\n';
    }

    private String getFloor(int width){
        return bottomLeft +
                repeatHori(width + wpad * 2) +
                bottomRight;
    }

    private String getLine(String line, int width){
        String whitespace = repeatSpace(width-line.length());
        String padding = repeatSpace(wpad);
        return vert +
                padding +
                line +
                whitespace +
                padding +
                vert + '\n';
    }

    private String getHPad(int width){
        String whitespace = repeatSpace(width + wpad * 2);
        return (vert +
                whitespace +
                vert + '\n').repeat(hpad);
    }

    public String format(String s){
        Scanner x = new Scanner(s);
        ArrayList<String> lines = new ArrayList<>();
        int maxWidth = 0;
        while (x.hasNextLine()){
            String line = x.nextLine();
            if (line.length() > maxWidth){
                maxWidth = line.length();
            }
            lines.add(line);
        }

        StringBuilder result = new StringBuilder();
        result.append(getRoof(maxWidth));
        result.append(getHPad(maxWidth));
        for (String line : lines){
            result.append(getLine(line, maxWidth));
        }
        result.append(getHPad(maxWidth));
        result.append(getFloor(maxWidth));
        return result.toString();
    }

    public String format(Object o){
        return format(o.toString());
    }
}
