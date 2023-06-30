package com.breadsb.namechanger.helpers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class DirectoryChooserHelper {

    private final static String INITIAL_DIRECTORY_PATH = "C:\\Users\\Public";

    public File invokeDirectoryChooser(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select directory");
        directoryChooser.setInitialDirectory(new File(INITIAL_DIRECTORY_PATH));
        return directoryChooser.showDialog(stage);
    }
}
