package com.breadsb.namechaner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;

import java.nio.file.Path;

public class ApplicationController {

    @FXML
    private Button chooseDirectoryButton;
    Path directoryPath;

    @FXML
    private void onChooseDirectoryButton() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
    }
}