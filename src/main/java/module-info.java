module com.breadsb.namechaner {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.breadsb.namechaner to javafx.fxml;
    exports com.breadsb.namechaner;
}