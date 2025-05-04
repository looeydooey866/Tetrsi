package com.example.tetrsi.Controller;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Config {
    public final static String DAS = "das";
    public final static String ARR = "arr";
    public final static String SDF = "sdf";
    public final static String DELAY = "delay";
    public final static String LEFT = "left";
    public final static String RIGHT = "right";
    public final static String SOFTDROP = "softdrop";
    public final static String HARDDROP = "harddrop";
    public final static String CW = "clockwise";
    public final static String CCW = "counterclockwise";
    public final static String ONEEIGHTY = "oneeighty";
    public final static String HOLD = "hold";
    public final static String RESET = "reset";
    public final static String EXIT = "exit";
    private static final Map<String, Object> defaults = Map.ofEntries(
            Map.entry(DAS, 10.0),
            Map.entry(ARR, 10.0),
            Map.entry(SDF, 20),
            Map.entry(DELAY, 0),
            Map.entry(LEFT, KeyCode.LEFT.getName()),
            Map.entry(RIGHT, KeyCode.RIGHT.getName()),
            Map.entry(SOFTDROP, KeyCode.DOWN.getName()),
            Map.entry(HARDDROP, KeyCode.SPACE.getName()),
            Map.entry(CW, KeyCode.UP.getName()),
            Map.entry(CCW, KeyCode.S.getName()),
            Map.entry(ONEEIGHTY, KeyCode.A.getName()),
            Map.entry(HOLD, KeyCode.D.getName()),
            Map.entry(RESET, KeyCode.G.getName()),
            Map.entry(EXIT,KeyCode.ESCAPE.getName())
    );
    private static final Config instance = new Config();
    private Config(){
        try {
            setConfigLocation();
            readConfig();
            writeConfig();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    private Yaml config;
    private Map<String, Object> fields;
    private File configFile;

    private void setConfigLocation() throws Exception{
        String userdir = System.getProperty("user.home");

        String filepath;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")){
            filepath = System.getenv("APPDATA") + File.separator + "Tetrsi";
        }
        else if (os.contains("mac")){
            filepath = userdir + "/Library/Application Support/Tetrsi";
        }
        else {
            filepath = userdir + "/.config/tetrsi";
        }

        File dir = new File(filepath);
        if (!dir.exists()){
            dir.mkdirs();
        }

        configFile = new File(dir, "config.yaml");
        if (!configFile.exists()){
            configFile.createNewFile();
        }
    }

    private void setDefaults(){
        if (fields == null){
            fields = new HashMap<>();
        }
        for (Map.Entry<String, Object> e : defaults.entrySet()){
            fields.putIfAbsent(e.getKey(),e.getValue());
        }
        try {
            writeConfig();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void readConfig() throws Exception {
        config = new Yaml();
        InputStream in = new FileInputStream(configFile);
        fields = config.load(in);
        setDefaults();
    }

    private void writeConfig() throws IOException {
        config = new Yaml();
        StringWriter writer = new StringWriter();
        config.dump(fields, writer);
        FileWriter filewriter = new FileWriter(configFile);
        filewriter.write(writer.toString());
        filewriter.close();
    }

    public static Config getInstance(){
        return instance;
    }

    public void setValue(String s, Object value){
        fields.put(s,value);
    }

    public Object getValue(String s){
        return fields.get(s);
    }

    public void save() throws IOException {
        writeConfig();
    }
}
