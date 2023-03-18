module com.gestionmdp.gestionmdp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json.chargebee;
    requires com.fasterxml.jackson.databind;
    requires java.sql;


    opens com.gestionmdp.gestionmdp to javafx.fxml;
    exports com.gestionmdp.gestionmdp;
}