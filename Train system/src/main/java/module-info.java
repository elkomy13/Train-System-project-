module com.example.databaseproject22 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.databaseproject22 to javafx.fxml;
    exports com.example.databaseproject22;
}