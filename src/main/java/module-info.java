module com.aghajari.piano {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens com.aghajari.piano to javafx.fxml;
    exports com.aghajari.piano;
}