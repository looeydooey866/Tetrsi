package com.example.tetrsi.Model.Definitions;

public class RGB {
    private double red, green, blue;

    public RGB(double red, double green, double blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public static double hexToDouble(String s, int index){
        int begin = index * 2;
        int end = index * 2 + 2;
        int intVal = Integer.parseInt(s.substring(begin,end).toLowerCase(),16);
        return intVal / 255.0;
    }

    public RGB(String s){
        if (s.charAt(0) == '#'){
            s = s.substring(1);
        }
        assert s.length() == 6;

        red = hexToDouble(s,0);
        green = hexToDouble(s,1);
        blue = hexToDouble(s,2);
    }

    public static RGB valueOf(String s){
        return new RGB(s);
    }

    public double getRed(){
        return red;
    }

    public double getGreen(){
        return green;
    }

    public double getBlue(){
        return blue;
    }

    public double[] get(){
        return new double[]{red, green, blue};
    }
}
