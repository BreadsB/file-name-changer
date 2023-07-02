package com.breadsb.namechanger.helpers;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryChooserHelper {

    private final static String INITIAL_DIRECTORY_PATH = System.getenv("userprofile")+"\\documents";

    public File invokeDirectoryChooser(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select directory");
        directoryChooser.setInitialDirectory(new File(INITIAL_DIRECTORY_PATH));
        return directoryChooser.showDialog(stage);
    }

    public List<File> getAllRegularFilesFromDirectory(Path directoryPath) {
        try (Stream<Path> paths = Files.walk(directoryPath)) {
            return paths.filter(Files::isRegularFile)
                    .map(path -> new File(path.toUri()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, "Error", "Wrong Path");
            return null;
        }
    }
}
