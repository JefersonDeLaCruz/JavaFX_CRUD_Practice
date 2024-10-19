module org.ues.lab3_progra3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.ues.lab3_progra3 to javafx.fxml, javafx.base;
    opens org.ues.lab3_progra3.Modelos to javafx.base;
    exports org.ues.lab3_progra3;
}