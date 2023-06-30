module com.breadsb.namechanger {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.breadsb.namechanger to javafx.fxml;
    exports com.breadsb.namechanger;
}