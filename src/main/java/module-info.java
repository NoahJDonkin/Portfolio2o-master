module org.example.portfolio2o {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;  // Tilføj denne linje

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.portfolio2o to javafx.fxml;
    exports org.example.portfolio2o;
}