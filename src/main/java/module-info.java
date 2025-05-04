module com.example.tetrsi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.yaml.snakeyaml;
    requires java.sql;

    exports com.example.tetrsi;
    exports com.example.tetrsi.Controller to javafx.fxml, javafx.graphics;
    opens com.example.tetrsi.Controller to javafx.fxml, javafx.graphics;
}