package com.breadsb.namechaner;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class UsefulMethods {

    private final static String INITIAL_DIRECTORY_PATH = "C:\\Users\\Public";
    private final static String NEW_LINE = System.lineSeparator();

    public File invokeDirectoryChooser(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select directory");
        directoryChooser.setInitialDirectory(new File(INITIAL_DIRECTORY_PATH));
        return directoryChooser.showDialog(stage);
    }

    public static void showAlert(Alert.AlertType type, String title, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public List<File> renameFiles(List<File> fileList, int charNumbers, Path folderPath) {
        List<File> doneList = new ArrayList<>();
        for (File oldFile : fileList) {
            String renamed = oldFile.getName().substring(charNumbers);
            File renamedFile = new File(folderPath.toUri() + renamed);
            if (!oldFile.renameTo(renamedFile)) {
                doneList.add(oldFile);
            }
        }
        return doneList;
    }

    public String showFilesAsString(List<File> fileList) {
        StringBuilder sb = new StringBuilder();
        for (File file : fileList) {
            sb.append(file.getName()).append(NEW_LINE);
        }
        return sb.toString();
    }

    public String howManyConverted(int size) {
        return (size == 1) ? "Converted " + size + " file" : "Converted " + size + " files";
    }
}
