package com.breadsb.namechaner.helpers;

import javafx.scene.control.Alert;

public class AlertHelper {
    public static void showAlert(Alert.AlertType type, String title, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
